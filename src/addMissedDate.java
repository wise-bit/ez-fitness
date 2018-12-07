import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.awt.Color;
import java.io.*;
import java.util.*;


import static java.lang.Character.isLetter;

public class addMissedDate extends JFrame implements ActionListener {
    JLabel Question[] = new JLabel[6];
    JLabel error = new JLabel("already have a same name");
    JComboBox yearBox = new JComboBox();
    JComboBox monthBox = new JComboBox();
    JComboBox dayBox = new JComboBox();

    TextField reps = new TextField();
    TextField weight = new TextField();

    JButton returns = new JButton("Return");
    JButton enter = new JButton("enter");
    boolean levelpass = false;
    boolean pass = true;

    private int year = 0;
    private int month = 0;
    private int day = 0;


    public addMissedDate() {
        setSize(350, 450);
        setTitle("Add Missed Date");
        setResizable(false);
        setLayout(null);

        reps.setBounds(50, 75, 100, 20);
        reps.setVisible(true);
        reps.addActionListener(this);
        add(reps);

        weight.setBounds(50, 225, 100, 20);
        weight.setVisible(true);
        weight.addActionListener(this);
        add(weight);

        for (int i = 0; i < 2; i++) {
            Question[i] = new JLabel();
            Question[i].setBounds(50, 50 + 50 * i, 100, 20);
            add(Question[i]);
        }

        Question[0].setText("Reps:");
        Question[1].setText("Weight:");

        enter.setBounds(200, 350, 80, 50);
        enter.addActionListener(this);
        enter.setBackground(Color.RED);
        enter.setForeground(Color.WHITE);
        add(enter);

        returns.setBounds(50, 350, 80, 50);
        returns.addActionListener(this);
        returns.setBackground(Color.WHITE);
        returns.setForeground(Color.BLACK);
        add(returns);

        setVisible(true);
    }

    public Boolean checkint (String b){
        for (int i = 0; i < b.length(); i++) {
            if (isLetter(b.charAt(i)))
                return false;
        }
        return true;
    }

    public String[] daysGenerator (){

        int temp = dayBox.getSelectedIndex();
        String[] daysTemp;

        if (temp == 1) {
            if(isLeapyearBox())
                daysTemp = new String[29];
            else
                daysTemp = new String[28];
        } else if (bigmonth((temp + 1))){
            daysTemp = new String[31];
        } else {
            daysTemp = new String[30];
        }

        for (int i = 1; i <= daysTemp.length; i++) {
            daysTemp[i - 1] = Integer.toString(i);
        }

        return daysTemp;
    }

    public boolean bigmonth(int month) {
        int[] arr = {1, 3, 5, 7, 8, 10, 12};
        for (int n : arr) {
            if (month == n) {
                return true;
            }
        }
        return false;
    }

    public boolean isLeapyearBox() {
        if (year%4 == 0) {
            if (year % 100 == 0) {
                if (year % 400 == 0)
                    return true;
                return false;
            }
            return true;
        }
        return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}