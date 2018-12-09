import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

import static java.awt.event.KeyEvent.VK_BACK_SPACE;
import static java.awt.event.KeyEvent.VK_DELETE;

public class modifyData extends JFrame implements ActionListener {

    JComboBox exercise;
    JComboBox date;

    JNumberTextField reps = new JNumberTextField();
    JNumberTextField weight = new JNumberTextField();

    String[] allDates;

    JButton modify = new JButton("Modify!");

    public modifyData() throws IOException {

        String[] byDefault = {"Select"};

        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(255, 243, 160));
        setSize(550, 450);
        setTitle("Modify data");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        String[] exercisesForCombo = new String[Statistics.countLines("res/database/exercises.csv")];

        int index = 0;
        for (Exercise e : Main.allExercises) {
            exercisesForCombo[index] = e.getName();
            index++;
        }
        Arrays.sort(exercisesForCombo);

        exercise = new JComboBox(exercisesForCombo);

        date = new JComboBox(byDefault);
        date.setVisible(false);

        exercise.addActionListener(this);
        date.addActionListener(this);

        exercise.setVisible(true);
        date.setVisible(true);

        JPanel everything = new JPanel(new GridLayout(3,1));
        everything.setBackground(new Color(255, 243, 160));

        JLabel intro = new JLabel("Modify a data entry!", SwingConstants.CENTER);
        intro.setFont(new Font(intro.getName(), Font.PLAIN, 24));
        intro.setVisible(true);
        everything.add(intro);

        JPanel form = new JPanel();
        form.setLayout(new BoxLayout(form, BoxLayout.PAGE_AXIS));

        JPanel comboBoxes = new JPanel(new FlowLayout());
        comboBoxes.add(exercise);
        comboBoxes.add(date);
        comboBoxes.setBackground(new Color(255, 220, 76));

        form.add(comboBoxes);

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

        modify.setBackground(Color.WHITE);
        modify.addActionListener(this);
        everything.add(modify);

        add(everything);

        setResizable(false);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == exercise) {

            try {

                allDates = new String[Statistics.countLines("res/users/" + Main.currentUser.getUsername() + "/" + exercise.getSelectedItem().toString() + ".txt") + 1];

                System.out.println("res/users/" + Main.currentUser.getUsername() + "/" + exercise.getSelectedItem().toString() + ".txt");
                Scanner file = new Scanner(new File("res/users/" + Main.currentUser.getUsername() + "/" + exercise.getSelectedItem().toString() + ".txt"));

                int index = 0;
                while (file.hasNextLine()) {

                    try {
                        allDates[index] = file.nextLine().split(",")[0];
                        index++;
                    } catch (ArrayIndexOutOfBoundsException error) {
                        System.out.println(error.getMessage());
                    }

                }

            } catch (IOException e1) {
                e1.printStackTrace();
            }

            DefaultComboBoxModel model = new DefaultComboBoxModel( allDates );

            date.setModel(model);
            date.setVisible(true);

            date.repaint();

        }

        if (e.getSource() == date) {


        }

        if (e.getSource() == modify) {
            if (!date.getSelectedItem().toString().equals("Select") && !date.getSelectedItem().toString().equals("null") && !reps.getText().equals("") && !weight.getText().equals("")) {
                System.out.println(date.getSelectedItem());

                try {

                    String filePath = "res/users/" + Main.currentUser.getUsername() + "/" + exercise.getSelectedItem().toString() + ".txt";
                    System.out.println(filePath);

                    Scanner scanner = new Scanner(new File(filePath));
                    StringBuilder buffer = new StringBuilder();
                    while(scanner.hasNext()) {
                        String current = scanner.nextLine();
                        if (current.split(",")[0].equals(date.getSelectedItem().toString())) {
                            System.out.println(Main.date + "," + (Integer.parseInt(reps.getText()) + Integer.parseInt(current.split(",")[1])) + "," + (Integer.parseInt(weight.getText()) + Integer.parseInt(current.split(",")[1])) + ",");
                            buffer.append(Main.date + "," + (Integer.parseInt(reps.getText()) + Integer.parseInt(current.split(",")[1])) + "," + (Integer.parseInt(weight.getText()) + Integer.parseInt(current.split(",")[1])) + ",");
                        }
                        else
                            buffer.append(current);
                        if(scanner.hasNext())
                            buffer.append("\n");
                    }
                    scanner.close();
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

            if (this.getText().length() > 1 && Character.isDigit(ev.getKeyChar()))
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
