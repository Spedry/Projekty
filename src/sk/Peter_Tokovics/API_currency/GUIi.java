package sk.Peter_Tokovics.API_currency;

import com.sun.deploy.panel.JreDialog;

import javax.swing.*;
import java.awt.*;

class GUIi {
    public static void main(String args[]){
        //JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("Currency exchange");
        JFormattedTextField formattedTextField = new JFormattedTextField();
        JMenu menu = new JMenu("EUR");
        JButton button = new JButton("Exchange");
        JTextField vysledok = new JTextField();

        //FRAME
        frame.setSize(510, 270);
        frame.setLayout(null);
        //FORMATEDTEXTFIELD



        formattedTextField.setBounds(50, 50, 100, 50);
        menu.setBounds(50, 120, 100, 50);
        button.setBounds(170, 80, 150, 60);
        vysledok.setBounds(340, 50, 120, 120);

        frame.add(formattedTextField);
        frame.add(menu);
        frame.add(button);
        frame.add(vysledok);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}