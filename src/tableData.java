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
     *
     * @throws IOException
     */

    public tableData() throws IOException {

        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(255, 243, 160));
        setSize(550, 450);
        setTitle("Your data");

        JLabel title = new JLabel("Look at where your data comes from!", SwingConstants.CENTER);
        title.setFont(new Font(title.getFont().toString(), Font.BOLD,18));
        add(title, BorderLayout.PAGE_START);

        String[] exerciseList = new String[Main.allExercises.size()+1];
        exerciseList[0] = "Select";
        for (int i = 0; i < Main.allExercises.size(); i++) {
            exerciseList[i+1] = Main.allExercises.get(i).getName();
        }
        Arrays.sort(exerciseList);
        exerciseSelection = new JComboBox(exerciseList);
        exerciseSelection.addActionListener(this);

        dataTable = new JTable(makeData(), columnNames);
        JScrollPane scrollableTable = new JScrollPane(dataTable);
        dataTable.setDefaultEditor(Object.class, null);

        JPanel comps = new JPanel();
        comps.setLayout(new BoxLayout(comps, BoxLayout.PAGE_AXIS));

        comps.add(exerciseSelection);
        comps.add(scrollableTable);
        add(comps);


        setResizable(false);
        setVisible(true);

    }

    public String[][] makeData() throws IOException {

        System.out.println(exerciseSelection.getSelectedItem().toString());

        if (exerciseSelection.getSelectedItem().toString().equals("Select")) {
            String[][] temp = {{"", "", ""}};
            return temp;
        }

        String path = "res/users/" + Main.currentUser.getUsername() + "/" + exerciseSelection.getSelectedItem().toString() + ".txt";
        String[][] temp = new String[Statistics.countLines(path)+1][3];
        Scanner file = new Scanner(new File(path));

        int index = 0;

        while (file.hasNextLine()) {
            String s = file.nextLine();
            System.out.println(s);
            temp[index] = s.split(",");
            Arrays.toString(temp[index]);
            index++;
        }

        return temp;

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == exerciseSelection) {
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
