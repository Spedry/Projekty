package sk.Peter_Tokovics.API_currency;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import static sk.Peter_Tokovics.API_currency.Exchange.exchangeTo;

public class ExchangeApp {
    private JPanel exchangeFrame;
    private JButton exchange;
    private JTextArea show;
    private JComboBox toWhat;
    private JFormattedTextField currencyInput;

    public ExchangeApp() {
        exchange.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            double value, output, minus;
            DecimalFormat df = new DecimalFormat("#.##");
            String to;
            value = Double.parseDouble(currencyInput.getText());
            to = (String) toWhat.getSelectedItem();
            minus = exchangeTo(to);
            output = value * minus;
            show.setText("USD= " + value + "\n" + to + "= " + df.format(output));
            }
        });
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("Currance exchange");
        frame.setResizable(false);
        frame.setContentPane(new ExchangeApp().exchangeFrame);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
