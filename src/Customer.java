import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class Customer extends JFrame {
    private JLabel itemLabel;
    private JLabel timerLabel;
    private Timer timer;

    private JLabel bidderLabel;
    private JTextField bidAmountField;
    private JTextField bidderNameField;
    private JButton placeBidButton;

    public Customer() {
        setTitle("Customer Interface");
        setSize(300, 200);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(6, 1));

        itemLabel = new JLabel("Item Name: ");
        timerLabel = new JLabel("Time Left: ");
        bidderLabel = new JLabel("Bidder Name: ");
        bidAmountField = new JTextField();
        bidderNameField = new JTextField();
        placeBidButton = new JButton("Place Bid");

        add(itemLabel);
        add(timerLabel);
        add(bidderLabel);
        add(bidderNameField);
        add(bidAmountField);
        add(placeBidButton);

        placeBidButton.addActionListener(e -> {
            String itemName = itemLabel.getText().substring(11); // Extract item name from label text
            int bidAmount = Integer.parseInt(bidAmountField.getText());
            String bidderName = bidderNameField.getText();
            //Admin.bids.add(new Bid(itemName, bidderName, bidAmount)); // Adding bid to the list
            Admin.bids.add(new Bid(bidderName, bidAmount, itemName));
            JOptionPane.showMessageDialog(this, "Bid placed: $" + bidAmount + " by " + bidderName);
        });
    }

    public void startTimer(int seconds) {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            int count = seconds;

            @Override
            public void run() {
                if (count >= 0) {
                    timerLabel.setText("Time Left: " + count + " seconds");
                    count--;
                } else {
                    timer.cancel();
                    timerLabel.setText("Time's up!");
                }
            }
        }, 0, 1000);
    }

    public void updateItemName(String itemName) {
        itemLabel.setText("Item Name: " + itemName);
    }

    public void launchCustomerInterface() {
        if (Admin.items.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No items available for auction.");
            return;
        }

        String itemName = Admin.items.get(0).getName(); // Display the first item for simplicity
        updateItemName(itemName);
        startTimer(60); // Start timer for 10 seconds, you can modify the duration as needed

        SwingUtilities.invokeLater(() -> {
            setVisible(true);
        });
    }

    // Close the timer when the window is closed
    @Override
    public void dispose() {
        if (timer != null) {
            timer.cancel();
        }
        super.dispose();
    }
}
