package sk.Peter_Tokovics.API_currency;

import javax.swing.*;
import java.awt.*;

public class gui extends JPanel {

    private static String USD = "USD: ";

    private JFrame frame;
    private JFormattedTextField formattedTextField;
    private JMenu menu;
    private JButton button;
    private JTextField vysledok;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                vystvorGui();
            }
        });
    }

    public void BorderLayout() {

        frame = new JFrame("Currency exchange");
        formattedTextField = new JFormattedTextField();
        menu = new JMenu("EUR");
        button = new JButton("Exchange");
        vysledok = new JTextField();


        JPanel left = new JPanel(new GridLayout(0,1));
        left.add(formattedTextField);
        left.add(menu);
        JPanel center = new JPanel(new GridLayout(0,1));
        center.add(button);
        JPanel right = new JPanel(new GridLayout(0,1));
        right.add(vysledok);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(left, BorderLayout.LINE_START);
        add(center, BorderLayout.CENTER);
        add(right, BorderLayout.LINE_END);
    }

    private static void vystvorGui() {
        //Create and set up the window.
        JFrame frame = new JFrame("FormattedTextFieldDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add contents to the window.
        frame.add(new pripravGui());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
}
