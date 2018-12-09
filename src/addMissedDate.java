import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.util.*;


import static java.lang.Character.isLetter;

public class addMissedDate extends JFrame implements ActionListener {
    JLabel Question[] = new JLabel[6];
    JLabel error = new JLabel("Entry already exists!");

    String[] monthsArray = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

    JComboBox yearBox = new JComboBox(yearsGenerator());
    JComboBox monthBox = new JComboBox(monthsArray);
    JComboBox dayBox = new JComboBox(daysGenerator());


    TextField reps = new TextField();
    TextField weight = new TextField();

    JButton returns = new JButton("Return");
    JButton enter = new JButton("enter");
    boolean levelpass = false;
    boolean pass = true;

    private int year = new Date().getYear() + 1900;
    private int month = new Date().getMonth();
    private int day = new Date().getDay();


    public addMissedDate() {
        setSize(650, 450);
        setTitle("Add Missed Date");

        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(255, 243, 160));
        setSize(650, 450);
        setTitle("Modify data");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        yearBox.setBounds(50, 75, 100, 20);
        yearBox.setVisible(true);
        yearBox.addActionListener(this);
        yearBox.setSelectedIndex(9);
        add(yearBox);

        monthBox.setBounds(175, 75, 100, 20);
        monthBox.setVisible(true);
        monthBox.addActionListener(this);
        monthBox.setSelectedIndex(new Date().getMonth());
        add(monthBox);

        dayBox.setBounds(300, 75, 100, 20);
        dayBox.setVisible(true);
        dayBox.setSelectedIndex(new Date().getDay()+1);
        dayBox.addActionListener(this);
        add(dayBox);

        // Adds days to the day JComboBox

        
        reps.setBounds(50, 175, 100, 20);
        reps.setVisible(true);
        reps.addActionListener(this);
        add(reps);

        weight.setBounds(50, 225, 100, 20);
        weight.setVisible(true);
        weight.addActionListener(this);
        add(weight);

        for (int i = 0; i < 3; i++) {
            Question[i] = new JLabel();
            Question[i].setBounds(50, 50 + 50 * i, 100, 20);
            add(Question[i]);
        }

        Question[0].setText("Date:");
        Question[1].setText("Reps:");
        Question[2].setText("Weight:");

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

    public String[] yearsGenerator() {
        int currentYear = new Date().getYear();
        String[] years = new String[10];

        int pos = 0;
        for (int i = currentYear - 9; i <= currentYear; i++) {
            years[pos] = Integer.toString(i + 1900);
            pos++;
        }

        return years;
    }

    public String[] daysGenerator (){

        int temp = monthBox.getSelectedIndex();

        String[] daysTemp;

        if (temp == 1) {
            if(isLeapyear())
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

    public boolean isLeapyear() {
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

    public void resetDaysBox() {

        day = 1;
        dayBox.setSelectedIndex(0);
        // dayBox.setMo
        dayBox.addActionListener(this);
        add(dayBox);
        // dayBox.

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == yearBox) {
            year = Integer.parseInt(yearBox.getSelectedItem().toString());
            //resetDaysBox();
        }
        if (e.getSource() == monthBox) {
            month = monthBox.getSelectedIndex() + 1;
            //resetDaysBox();
        }
        if (e.getSource() == dayBox) {
            System.out.println(Integer.parseInt(dayBox.getSelectedItem().toString()));
            day = dayBox.getSelectedIndex() + 1;
        }

        System.out.printf(String.format("%d-%d-%d\n", year, month, day));

    }
}