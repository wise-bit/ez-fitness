import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;

import javax.swing.*;

import static java.lang.Character.isDigit;
import static java.lang.Character.isLetter;

public class NewUser extends JFrame implements ActionListener {
    JLabel Question[] = new JLabel[5];
    TextField name = new TextField();
    TextField age = new TextField();
    TextField height = new TextField();
    TextField weight = new TextField();
    JButton level[] = new JButton[3];
    JButton returns = new JButton("Return");
    JButton start = new JButton("Start");

    public NewUser() {
        setSize(350, 450);
        setTitle("Registration Page");
        setResizable(false);
        setLayout(null);
        name.setBounds(50, 75, 100, 20);
        name.setVisible(true);
        name.addActionListener(this);
        add(name);
        age.setBounds(50, 125, 100, 20);
        age.setVisible(true);
        age.addActionListener(this);
        add(age);
        height.setBounds(50, 175, 100, 20);
        height.setVisible(true);
        height.addActionListener(this);
        add(height);
        weight.setBounds(50, 225, 100, 20);
        weight.setVisible(true);
        weight.addActionListener(this);
        add(weight);

        for (int i = 0; i < 5; i++) {
            Question[i] = new JLabel();
            Question[i].setBounds(50, 50 + 50 * i, 100, 20);
            add(Question[i]);
        }
        Question[0].setText("Username:");
        Question[1].setText("Age:");
        Question[2].setText("Height:");
        Question[3].setText("Weight:");
        Question[4].setText("Level:");

        for (int i = 0; i < 3; i++) {
            level[i] = new JButton();
            level[i].setBounds(50 + 85 * i, 275, 90, 30);
            add(level[i]);
            level[i].addActionListener(this);
            level[i].setBackground(Color.WHITE);
        }
        level[0].setText("Beginner");
        level[1].setText("Medium");
        level[2].setText("Experiesed");

        level[0].setFont(level[0].getFont().deriveFont(9f));
        level[1].setFont(level[1].getFont().deriveFont(9f));
        level[2].setFont(level[2].getFont().deriveFont(9f));

        start.setBounds(200, 350, 80, 50);
        start.addActionListener(this);
        start.setBackground(Color.RED);
        start.setForeground(Color.WHITE);
        add(start);

        returns.setBounds(50, 350, 80, 50);
        returns.addActionListener(this);
        returns.setBackground(Color.WHITE);
        returns.setForeground(Color.BLACK);
        add(returns);
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        if (arg0.getSource() == level[0]) {
            level[0].setBackground(Color.RED);
            level[0].setForeground(Color.WHITE);
            level[1].setBackground(Color.WHITE);
            level[1].setForeground(Color.BLACK);
            level[2].setBackground(Color.WHITE);
            level[2].setForeground(Color.BLACK);
        }
        if (arg0.getSource() == level[1]) {
            level[1].setBackground(Color.RED);
            level[1].setForeground(Color.WHITE);
            level[0].setBackground(Color.WHITE);
            level[0].setForeground(Color.BLACK);
            level[2].setBackground(Color.WHITE);
            level[2].setForeground(Color.BLACK);
        }
        if (arg0.getSource() == level[2]) {
            level[2].setBackground(Color.RED);
            level[2].setForeground(Color.WHITE);
            level[1].setBackground(Color.WHITE);
            level[1].setForeground(Color.BLACK);
            level[0].setBackground(Color.WHITE);
            level[0].setForeground(Color.BLACK);
        }

        if (arg0.getSource() == start) {
            if (name.getText().length() == 0) {
                name.setBackground(Color.RED);
            } else
                name.setBackground(Color.WHITE);
            if (checkint(age.getText())||name.getText().length() != 0) {
                age.setBackground(Color.WHITE);
            } else
                age.setBackground(Color.RED);


        }
    }
    public Boolean checkint (String b){
        for (int i = 0; i < b.length(); i++) {
            if (isLetter(b.charAt(i)))
                return false;
        }
        return true;
    }


}