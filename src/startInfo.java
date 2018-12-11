/**
 *
 * @author Satrajit
 *
 * This class is to show the information which the user started
 * with, as well as showing their current level after any promotions,
 * by getting their information from the documentation and putting
 * it in a readable and accessible fashion.
 *
 */

import javax.swing.*;
import java.awt.*;

public class startInfo extends JFrame {

    // Constructor method
    public startInfo() {

        // Sets the basic content information to be in scale with the rest of the components of the application
        getContentPane().setBackground(new Color(255, 243, 160));
        setSize(550, 450);
        setTitle("Modify data");

        // Sets up a grid layout
        setLayout(new GridLayout(9,1));

        add(new JLabel()); //1
        
        JLabel title = new JLabel("Compare from where you started!", SwingConstants.CENTER); //2
        title.setFont(new Font("Courier", Font.BOLD,18));
        add (title);

        // Adds all of the components to the GUI frame
        add(new JLabel("Username: " + Main.currentUser.getUsername(), SwingConstants.CENTER)); //3
        add(new JLabel("Age: " + Main.currentUser.getAge(), SwingConstants.CENTER)); //4
        add(new JLabel("Height: " + Main.currentUser.getHeight() + " cm", SwingConstants.CENTER)); //5
        add(new JLabel("Weight: " + Main.currentUser.getWeight() + " kg", SwingConstants.CENTER)); //6
        add(new JLabel("", SwingConstants.CENTER)); //7
        add(new JLabel("Current level: " + Main.currentUser.getLevel(), SwingConstants.CENTER)); //8

        add(new JLabel()); //9

        // Makes the application not resizable to make sure the user does not do anything weird with the application
        setResizable(false);
        // Makes the Frame visible
        setVisible(true);

    }

}
