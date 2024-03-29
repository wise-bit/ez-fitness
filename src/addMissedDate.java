/**
 *
 * @author Satrajit
 *
 * This feature allows for the user to add information regarding a day which they may have forgotten
 * to use the application.
 *
 * The date selection is designed in such a way that it only allows actual real dates which are
 * possible, and even accounts for leap years.
 *
 * The entry is placed appropriately in the database to ensure data is in order.
 *
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

// These imports are important for the new JNumericTextField used for this module
import static java.awt.event.KeyEvent.VK_BACK_SPACE;
import static java.awt.event.KeyEvent.VK_DELETE;
import static java.lang.Character.isLetter;

/**
 *
 * Class declaration
 *
 * @extends JFrame
 * @implements ActionListener
 *
 */
public class addMissedDate extends JFrame implements ActionListener {
    JLabel error = new JLabel("Entry already exists!");

    // Sets up all of the names of the months
    String[] monthsArray = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

    // Declares all of the required JComboBoxes
    JComboBox exercise;
    JComboBox yearBox;
    JComboBox monthBox;
    JComboBox dayBox;

    // Checks if changes were done or not, to ensure too many changes were not made by accident
    boolean done = false;

    // Sets up the JNumberTextFields, explained in the end, where the method is created
    JNumberTextField reps = new JNumberTextField();
    JNumberTextField weight = new JNumberTextField();

    private int year = new Date().getYear() + 1900;
    private int month = new Date().getMonth();
    private int day = new Date().getDate();

    JButton add = new JButton("Add!");

    /**
     * Constructor method for adding any missed date
     */
    public addMissedDate() {
        String[] byDefault = {"1"};

        // Sets up a borderLayout for the class
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(255, 243, 160));
        setSize(550, 450);
        setTitle("Add new entry");

        String[] exercisesForCombo = new String[Main.allExercises.size()];

        int index = 0;
        for (Exercise e : Main.allExercises) {
            exercisesForCombo[index] = e.getName();
            index++;
        }
        // Sorts the array which is to be used for the JComboBox to make sure that the users can
        // easily find their exercises alphabetically
        Arrays.sort(exercisesForCombo);

        // Makes four combo boxes, three for the date components, and one for choosing the exericse
        yearBox = new JComboBox(yearsGenerator());
        monthBox = new JComboBox(monthsArray);
        dayBox = new JComboBox(byDefault);
        exercise = new JComboBox(exercisesForCombo);

        // Adds actionlisteners to all of them, to ensure the page is updated properly
        yearBox.addActionListener(this);
        monthBox.addActionListener(this);
        dayBox.addActionListener(this);
        exercise.addActionListener(this);

        // The boxes are all visible
        yearBox.setVisible(true);
        monthBox.setVisible(true);
        dayBox.setVisible(true);
        exercise.setVisible(true);

        // A new JPanel for everything, which allows better customization of the layout of the components
        JPanel everything = new JPanel(new GridLayout(3,1));
        everything.setBackground(new Color(255, 243, 160));

        // This puts in the intro JLabel asking for a new entry
        JLabel intro = new JLabel("Enter new entry!", SwingConstants.CENTER);
        intro.setFont(new Font(intro.getName(), Font.PLAIN, 24));
        intro.setVisible(true);
        everything.add(intro);

        // Sets up a new JPanel in the form of a boxlayout form
        JPanel form = new JPanel();
        form.setLayout(new BoxLayout(form, BoxLayout.PAGE_AXIS));

        // A new JPanel inside the BoxLayout with a FlowLayout to make the comboboxes organized horizontally
        JPanel comboBoxes = new JPanel(new FlowLayout());
        comboBoxes.add(yearBox);
        comboBoxes.add(monthBox);
        comboBoxes.add(dayBox);
        comboBoxes.setBackground(new Color(255, 220, 76));

        // Adds the date comboboxes at once
        form.add(comboBoxes);

        // Adds the exercise combobox separately to put it below the others
        form.add(exercise);

        // A flowlayout makes the placement of objects much easier and flexible
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

    // Checks if a string is a proper number or not
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

    // Generates the arraylist for the days JComboBox depending on the year and the month, only allowing possible dates of the calendar

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

    // Checks if a month has 31 days or not

    public boolean bigmonth(int month) {
        int[] arr = {1, 3, 5, 7, 8, 10, 12};
        for (int n : arr) {
            if (month == n) {
                return true;
            }
        }
        return false;
    }

    // Checks if the selected year is a leap year

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

    // Resets the days JComboBox

    public void resetDaysBox() {

        day = 1;
        dayBox.addActionListener(this);
        DefaultComboBoxModel model = new DefaultComboBoxModel( daysGenerator() );
        dayBox.setModel(model);
        dayBox.setSelectedIndex(0);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // All three fo them makes sure that the user cannot enter an invalid date

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


        // The logic inside this checks if the input box is blank, and adds the new entry places appropriately in the right location

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

            // Disposes of screens not being used, while refreshing the info screen at the same time
            Main.info.dispose();
            Main.info = new InfoScreen2();
            this.dispose();

        }

    }


    // A very specifically designed JTextField which prevents the program from crashing from bad input
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