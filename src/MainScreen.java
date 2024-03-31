import javax.swing.*;
import java.awt.*;

public class MainScreen extends JFrame {
    private JButton adminButton;
    private JButton customerButton;

    public MainScreen() {
        setTitle("E-Auction System");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(2, 1));

        adminButton = new JButton("Admin");
        customerButton = new JButton("Customer");

        add(adminButton);
        add(customerButton);

        adminButton.addActionListener(e -> {
            Admin admin = new Admin();
            admin.launchAdminInterface();
        });

        customerButton.addActionListener(e -> {
            Customer customer = new Customer();
            customer.launchCustomerInterface();
        });
    }

    public void launchMainScreen() {
        SwingUtilities.invokeLater(() -> {
            setVisible(true);
        });
    }
}
