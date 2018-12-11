/**
 * Author: Satrajit
 */

import javax.swing.*;
import java.awt.*;

public class startInfo extends JFrame {

    public startInfo() {

        getContentPane().setBackground(new Color(255, 243, 160));
        setSize(550, 450);
        setTitle("Modify data");

        setLayout(new GridLayout(9,1));

        add(new JLabel()); //1
        
        JLabel title = new JLabel("Compare from where you started!", SwingConstants.CENTER); //2
        title.setFont(new Font("Courier", Font.BOLD,18));
        add (title);

        add(new JLabel("Username: " + Main.currentUser.getUsername(), SwingConstants.CENTER)); //3
        add(new JLabel("Age: " + Main.currentUser.getAge(), SwingConstants.CENTER)); //4
        add(new JLabel("Height: " + Main.currentUser.getHeight() + " cm", SwingConstants.CENTER)); //5
        add(new JLabel("Weight: " + Main.currentUser.getWeight() + " kg", SwingConstants.CENTER)); //6
        add(new JLabel("", SwingConstants.CENTER)); //7
        add(new JLabel("Current level: " + Main.currentUser.getLevel(), SwingConstants.CENTER)); //8

        add(new JLabel()); //9

        setResizable(false);
        setVisible(true);

    }

}
