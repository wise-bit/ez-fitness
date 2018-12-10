/**
 * Author: Satrajit
 */

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;


import static java.awt.event.KeyEvent.VK_BACK_SPACE;
import static java.awt.event.KeyEvent.VK_DELETE;
import static java.lang.Character.isLetter;

public class addMissedDate extends JFrame implements ActionListener {
    JLabel error = new JLabel("Entry already exists!");

    String[] monthsArray = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

    JComboBox exercise;
    JComboBox yearBox;
    JComboBox monthBox;
    JComboBox dayBox;

    boolean done = false;

    JNumberTextField reps = new JNumberTextField();
    JNumberTextField weight = new JNumberTextField();

    private int year = new Date().getYear() + 1900;
    private int month = new Date().getMonth();
    private int day = new Date().getDate();

    JButton add = new JButton("Add!");


    public addMissedDate() {
        String[] byDefault = {"1"};

        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(255, 243, 160));
        setSize(550, 450);
        setTitle("Add new entry");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        String[] exercisesForCombo = new String[Main.allExercises.size()];

        int index = 0;
        for (Exercise e : Main.allExercises) {
            exercisesForCombo[index] = e.getName();
            index++;
        }
        Arrays.sort(exercisesForCombo);

        yearBox = new JComboBox(yearsGenerator());
        monthBox = new JComboBox(monthsArray);
        dayBox = new JComboBox(byDefault);
        exercise = new JComboBox(exercisesForCombo);

        yearBox.addActionListener(this);
        monthBox.addActionListener(this);
        dayBox.addActionListener(this);
        exercise.addActionListener(this);

        yearBox.setVisible(true);
        monthBox.setVisible(true);
        dayBox.setVisible(true);
        exercise.setVisible(true);

        JPanel everything = new JPanel(new GridLayout(3,1));
        everything.setBackground(new Color(255, 243, 160));

        JLabel intro = new JLabel("Enter new entry!", SwingConstants.CENTER);
        intro.setFont(new Font(intro.getName(), Font.PLAIN, 24));
        intro.setVisible(true);
        everything.add(intro);

        JPanel form = new JPanel();
        form.setLayout(new BoxLayout(form, BoxLayout.PAGE_AXIS));

        JPanel comboBoxes = new JPanel(new FlowLayout());
        comboBoxes.add(yearBox);
        comboBoxes.add(monthBox);
        comboBoxes.add(dayBox);
        comboBoxes.setBackground(new Color(255, 220, 76));

        form.add(comboBoxes);

        form.add(exercise);

        JPanel questions = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        questions.add(new JLabel("Reps: "));
        questions.add(reps);
        reps.setColumns(7);
        reps.setVisible(true);
        questions.add(new JLabel("Weight (kg): "));
        questions.add(weight);
        weight.setColumns(7);
        weight.setVisible(true);
        questions.setBackground(new Color(255, 220, 76));

        form.setBackground(new Color(255, 220, 76));
        form.add(questions);

        everything.add(form);

        add.setBackground(Color.WHITE);
        add.addActionListener(this);
        everything.add(add);

        add(everything);

        yearBox.setSelectedIndex(9);
        int monthIndex = 0;
        for (int i = 0; i < 12; i++) {
            if (monthsArray[i].equals(month));
                monthIndex = i;
        }
        monthBox.setSelectedIndex(monthIndex);
        day = new Date().getDate();
        dayBox.setSelectedIndex(day);

        setResizable(false);
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
        dayBox.addActionListener(this);
        DefaultComboBoxModel model = new DefaultComboBoxModel( daysGenerator() );
        dayBox.setModel(model);
        dayBox.setSelectedIndex(0);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == yearBox) {
            year = Integer.parseInt(yearBox.getSelectedItem().toString());
            resetDaysBox();
        }
        if (e.getSource() == monthBox) {
            month = monthBox.getSelectedIndex() + 1;
            resetDaysBox();
        }
        if (e.getSource() == dayBox) {
            day = dayBox.getSelectedIndex() + 1;
        }

        if (e.getSource() == add) {

            if (!reps.getText().equals("") && !weight.getText().equals("")) {

                System.out.printf(String.format("%d-%d-%d\n", year, month, day));
                String date = String.format("%d-%d-%d", year, month, day);

                try {

                    String filePath = "res/users/" + Main.currentUser.getUsername() + "/" + exercise.getSelectedItem().toString() + ".txt";
                    System.out.println(filePath);

                    Scanner scanner = new Scanner(new File(filePath));
                    Scanner scanner2 = new Scanner(new File(filePath));

                    StringBuilder buffer = new StringBuilder();
                    while(scanner.hasNextLine()) {

                        String current = scanner.nextLine();

                        if (scanner2.hasNextLine()) {

                            String current2 = scanner2.nextLine();
                            String yearFromFile = current2.split(",")[0].split("-")[0];
                            String monthFromFile = current2.split(",")[0].split("-")[1];
                            String dateFromFile = current2.split(",")[0].split("-")[2];

                            System.out.println(Integer.parseInt(yearBox.getSelectedItem().toString()) + " : " + Integer.parseInt(yearFromFile));

                            if (current.split(",")[0].equals(date) && !done) {

                                JOptionPane.showMessageDialog(null, "Entry already exists! Please modify if required.");
                                buffer.append(current);
                                done = true;

                            } else if (Integer.parseInt(yearBox.getSelectedItem().toString()) <= Integer.parseInt(yearFromFile) && !done) {

                                if (Integer.parseInt(yearBox.getSelectedItem().toString()) < Integer.parseInt(yearFromFile) && !done) {

                                    buffer.append(date + "," + (Integer.parseInt(reps.getText())) + "," + (Integer.parseInt(weight.getText())) + ",\n");
                                    done = true;

                                } else if (month <= Integer.parseInt(monthFromFile) && !done) {

                                    if (month < Integer.parseInt(monthFromFile) && !done) {

                                        buffer.append(date + "," + (Integer.parseInt(reps.getText())) + "," + (Integer.parseInt(weight.getText())) + ",\n");
                                        done = true;

                                    } else if (Integer.parseInt(dayBox.getSelectedItem().toString()) < Integer.parseInt(dateFromFile) && !done) {

                                        buffer.append(date + "," + (Integer.parseInt(reps.getText())) + "," + (Integer.parseInt(weight.getText())) + ",\n");
                                        done = true;

                                    }

                                }

                                buffer.append(current);

                            } else
                                buffer.append(current);

                        } else {
                            buffer.append(current);
                        }


                        if(scanner.hasNextLine())
                            buffer.append("\n");

                    }
                    scanner.close();
                    scanner2.close();
                    PrintWriter printer = new PrintWriter(new File(filePath));
                    printer.print(buffer);
                    printer.close();

                } catch (IOException error) {
                    error.printStackTrace();
                }

            } else {
                JOptionPane.showMessageDialog(null, "Please input all fields!");
            }

            Main.info.dispose();
            Main.info = new InfoScreen2();
            this.dispose();

        }

    }

    public class JNumberTextField extends JFormattedTextField {
        private static final long serialVersionUID = 1L;

        @Override
        public void processKeyEvent(KeyEvent ev) {

            if (this.getText().length() > 2 && Character.isDigit(ev.getKeyChar()))
                ev.consume();

            if (Character.isDigit(ev.getKeyChar()) || ev.getKeyCode() == VK_BACK_SPACE || ev.getKeyCode() == VK_DELETE)
                super.processKeyEvent(ev);

            ev.consume();
            return;
        }

        public Long getNumber() {
            Long result = null;
            String text = getText();
            if (text != null && !"".equals(text)) {
                result = Long.valueOf(text);
            }
            return result;
        }
    }

}