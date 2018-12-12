/**
 *
 * This method displays a table filled with raw data for each individual user
 * @author Satrajit
 *
 */

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class tableData extends JFrame implements ActionListener {

    JComboBox exerciseSelection;
    JTable dataTable;
    String[] columnNames = { "Exercise", "Reps", "Weight" };

    /**
     * Constructor method
     * @throws IOException
     */

    public tableData() throws IOException {

        // Sets the layout for the program
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(255, 243, 160));
        setSize(550, 450);
        setTitle("Your data");

        // Adds the title, which must strike out from the other features
        JLabel title = new JLabel("Look at where your data comes from!", SwingConstants.CENTER);
        title.setFont(new Font(title.getFont().toString(), Font.BOLD,18));
        add(title, BorderLayout.PAGE_START);

        // Makes a list for adding all of the exercises available to it, using the exercise object ArrayList
        String[] exerciseList = new String[Main.allExercises.size()+1];
        exerciseList[0] = "Select";
        for (int i = 0; i < Main.allExercises.size(); i++) {
            exerciseList[i+1] = Main.allExercises.get(i).getName();
        }

        // Makes sure the list is easily lexicographically comprehensible
        Arrays.sort(exerciseList);

        // Makes a combobox using the exerciseList, which has an actionListener
        // Instead of depending on yet another button
        exerciseSelection = new JComboBox(exerciseList);
        exerciseSelection.addActionListener(this);

        // Initializes the JTable using a method described later
        dataTable = new JTable(makeData(), columnNames);
        JScrollPane scrollableTable = new JScrollPane(dataTable);
        dataTable.setDefaultEditor(Object.class, null);

        // Makes a panel, making placement easier
        JPanel comps = new JPanel();
        // Uses BoxLayout
        comps.setLayout(new BoxLayout(comps, BoxLayout.PAGE_AXIS));

        // Adds all components
        comps.add(exerciseSelection);
        comps.add(scrollableTable);
        add(comps);

        // Not resizable, but visible
        setResizable(false);
        setVisible(true);

    }

    /**
     *
     * @return data which is to be used by the table
     * @throws IOException
     */

    public String[][] makeData() throws IOException {

        System.out.println(exerciseSelection.getSelectedItem().toString());

        if (exerciseSelection.getSelectedItem().toString().equals("Select")) {
            String[][] temp = {{"", "", ""}};
            return temp;
        }

        // Defines the path to the right place
        String path = "res/users/" + Main.currentUser.getUsername() + "/" +
                exerciseSelection.getSelectedItem().toString() + ".txt";
        String[][] temp = new String[Statistics.countLines(path)+1][3];
        Scanner file = new Scanner(new File(path));

        int index = 0;

        // Independent from the global objects, since this functionality is built for viewing raw data
        while (file.hasNextLine()) {
            String s = file.nextLine();
            System.out.println(s);
            temp[index] = s.split(",");
            Arrays.toString(temp[index]);
            index++;
        }

        return temp;

    }

    /**
     * Action performed
     * @param e
     */

    @Override
    public void actionPerformed(ActionEvent e) {

        // If the exerciseSelection undergoes any changes
        if (e.getSource() == exerciseSelection) {
            // Makes a new model, which is able to modify the data for the table
            DefaultTableModel model = null;
            try {
                model = new DefaultTableModel(makeData(), columnNames);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            dataTable.setModel(model);
        }

    }
}
