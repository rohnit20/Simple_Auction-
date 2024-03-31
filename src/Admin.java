import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Admin extends JFrame {
    private JTextField itemNameField;
    private JLabel itemLabel;
    private JButton launchItemButton;
    public static ArrayList<Item> items = new ArrayList<>();
    public static ArrayList<Bid> bids = new ArrayList<>();

    public Admin() {
        setTitle("Admin Interface");
        setSize(300, 200);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(3, 1));

        itemNameField = new JTextField();
        launchItemButton = new JButton("Launch Item");
        itemLabel = new JLabel();

        add(new JLabel("Item Name:"));
        add(itemNameField);
        add(launchItemButton);

        launchItemButton.addActionListener(e -> {
            String itemName = itemNameField.getText();
            items.add(new Item(itemName));
            JOptionPane.showMessageDialog(this, "Item launched: " + itemName);
            startAuctionTimer(itemName);
        });
    }

    public void launchAdminInterface() {
        SwingUtilities.invokeLater(() -> {
            setVisible(true);
        });
    }

    private void startAuctionTimer(String itemName) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            int count = 60; // Countdown starts from 10 seconds

            @Override
            public void run() {
                count--;
                if (count == 0) {
                    timer.cancel();
                    endAuction(itemName);
                }
            }
        }, 0, 1000);
    }

    private void endAuction(String itemName) {
        Bid highestBid = null;
        for (Bid bid : bids) {
            if (bid.getItemName().equals(itemName)) {
                if (highestBid == null || bid.getBidAmount() > highestBid.getBidAmount()) {
                    highestBid = bid;
                }
            }
        }

        if (highestBid != null) {
            JOptionPane.showMessageDialog(this, "Auction for item '" + itemName + "' has ended. Item sold for $" + highestBid.getBidAmount() + " to " + highestBid.getBidderName());
        } else {
            JOptionPane.showMessageDialog(this, "Auction for item '" + itemName + "' has ended. No bids were placed.");
        }
    }
}

class Bid {
    private String bidderName;
    private int bidAmount;
    private String itemName;

    public Bid(String bidderName, int bidAmount, String itemName) {
        this.bidderName = bidderName;
        this.bidAmount = bidAmount;
        this.itemName = itemName;
    }

    public String getBidderName() {
        return bidderName;
    }

    public int getBidAmount() {
        return bidAmount;
    }

    public String getItemName() {
        return itemName;
    }
}
