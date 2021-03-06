package sk.Spedry.API_currency;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import static sk.Spedry.API_currency.Exchange.exchangeTo;

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
            String getText, to;
            DecimalFormat df = new DecimalFormat("#.##");
            getText = currencyInput.getText();
            if (getText.equals("")) show.setText("ZADAJTE \n SUMU");
            else {
                value = Double.parseDouble(getText);
                to = (String) toWhat.getSelectedItem();
                minus = exchangeTo(to);
                output = value * minus;
                show.setText("USD= " + value + "\n" + to + "= " + df.format(output));
            }
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
