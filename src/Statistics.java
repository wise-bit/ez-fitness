import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.AbstractRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.data.xy.XYZDataset;
import org.jfree.util.Rotation;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Statistics extends JFrame implements MouseListener, ActionListener {

    JLabel appTitle = new JLabel("Statistics and History", SwingConstants.CENTER);
    private Font font = new Font("Consolas", Font.BOLD, 80); // Freestyle Script, Matura MT Script Capitals, French Script MT
    private JComboBox<String> cb;
    String currentExercise = "Overall";
    String currentOption = "Reps";
    JFreeChart lineChart;
    JPanel chartArea;
    ChartPanel chartPanel;
    JList list;

    private JButton reps_button;
    private JButton weight_button;
    private JButton back;

    public Statistics() throws IOException {

        this.getContentPane().setBackground(new Color(255, 243, 160));
        setLayout(new BorderLayout());
        this.setTitle("Statistics and History");
        // setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("EZFitness/res/background.jpg")))));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Border titleBorder = BorderFactory.createLineBorder(Color.DARK_GRAY, 2);

        //////////////////////////////////////////////////////
        appTitle.setForeground(Color.WHITE);
        appTitle.setBackground(Color.BLACK);
        appTitle.setFont(font);
        // appTitle.setSize(this.getWidth()-20,(int) (900/10));
        appTitle.setBorder(titleBorder);
        appTitle.setOpaque(true);
        add(appTitle, BorderLayout.PAGE_START);
        //////////////////////////////////////////////////////

        chartArea = new JPanel();
        chartArea.setLayout(new BorderLayout());

        pieChartMaker();

        lineChartMaker();

        add(chartArea, BorderLayout.EAST);
        validate();

        JPanel leftPanel = new JPanel(new BorderLayout());
        JLabel question = new JLabel("Please select an optiton to show its graph:", SwingConstants.CENTER);
        question.setFont(new Font("Arial",Font.BOLD,18));

        // chartArea.setBackground(new Color(255, 243, 160));

//        JPanel userOptions = new JPanel();
//        cb = new JComboBox<String>(choices);
//        cb.setBounds(30, 20, 500, 15);
//        cb.setSize(2000, cb.getPreferredSize().height);
//        userOptions.add(cb, BorderLayout.CENTER);
//        cb.setVisible(true);
//        cb.addActionListener(this);
//        leftPanel.add(userOptions, BorderLayout.CENTER);

        String[] choices =  generateChoices();
        list = new JList(choices);
        list.addMouseListener(this);
        list.setFont(new Font("Arial",Font.BOLD,18));
        JScrollPane scrollableList = new JScrollPane(list);
        // scrollableList.getViewport().addChangeListener();
        scrollableList.setVisible(true);

        leftPanel.setBackground(new Color(255, 243, 160));

        leftPanel.add(question, BorderLayout.NORTH);
        leftPanel.add(scrollableList, BorderLayout.CENTER);

        /////////////////////////////////////////////

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 10));

//        exercise_button = new JButton("EXERCISE");
//        exercise_button.addActionListener(this);
//        exercise_button.setBackground(Color.WHITE);
//        exercise_button.setBorderPainted(false);
//        buttonsPanel.add(exercise_button);

        reps_button = new JButton("REPS");
        reps_button.addActionListener(this);
        reps_button.setBackground(Color.WHITE);
        reps_button.setBorderPainted(false);
        buttonsPanel.add(reps_button);

        weight_button = new JButton("WEIGHT");
        weight_button.addActionListener(this);
        weight_button.setBackground(Color.WHITE);
        weight_button.setBorderPainted(false);
        buttonsPanel.add(weight_button);

        buttonsPanel.add(Box.createRigidArea(new Dimension(20,0)));

        back = new JButton("BACK");
        back.addActionListener(this);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setBorderPainted(false);
        buttonsPanel.add(back);

        /////////////////////////////////////////////

        leftPanel.add(buttonsPanel, BorderLayout.PAGE_END);
        add(leftPanel, BorderLayout.CENTER);
        buttonsPanel.setBackground(Color.red);

        /////////////////////////////////////////////

        setVisible(true);
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
//        this.setLocation(Main.dim.width/2-this.getSize().width/2, Main.dim.height/2-this.getSize().height/2);
        repaint();

    }

    public void pieChartMaker() throws IOException {
        PieDataset dataSet = createExerciseDataSet();
        JFreeChart chart = createChart(dataSet, "Part Of Body Involved");
        ChartPanel myChart = new ChartPanel(chart);
        myChart.setMouseWheelEnabled(true);
        chartArea.add(myChart, BorderLayout.NORTH);
    }

    public void lineChartMaker() throws IOException {
//        try {
//            chartArea.remove(chartPanel);
//        } catch (Exception e) { }
//        lineChart = ChartFactory.createLineChart("Line Graph For: " + currentExercise, "Date", currentOption , createDataset(), PlotOrientation.VERTICAL, true,true,false);
//        chartPanel = new ChartPanel( lineChart );
//        chartArea.add(chartPanel);

        final JFreeChart chart = ChartFactory.createXYLineChart(
                "Line Graph For: " + currentExercise,
                "Day",
                currentOption,
                createDataset(),
                PlotOrientation.VERTICAL,
                true,
                true,
                false);

        chart.setBackgroundPaint(Color.white);
        final XYPlot plot1 = chart.getXYPlot();
        plot1.setBackgroundPaint(Color.lightGray);
        plot1.setDomainGridlinePaint(Color.white);
        plot1.setRangeGridlinePaint(Color.white);

        XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) plot1.getRenderer();
        renderer.setBaseShapesVisible(true);
        renderer.setBaseShapesFilled(true);
        renderer.setBaseStroke(new BasicStroke(2));
        renderer.setSeriesPaint(0, Color.BLACK);
        ((AbstractRenderer)renderer).setAutoPopulateSeriesStroke(false);

        chartPanel = new ChartPanel( chart );
        chartArea.add(chartPanel);

    }


    //// Helper classes

    public String[] generateChoices() throws IOException {
        String[] list = new String[countLines("res/database/exercises.csv")];
        File exerciseList = new File("res/database/exercises.csv");
        Scanner reader = new Scanner(exerciseList);
        int pos = 0;

        for (Exercise e : Main.allExercises) {
            if (!e.getName().equals("Overall")) {
                list[pos] = e.getName();
                pos++;
            }
        }

//        while (reader.hasNextLine()){
//            String[] current = reader.nextLine().split(",");
//            if (!current[0].equals("Overall")) {
//                list[pos] = current[0];
//                pos++;
//            }
//        }
        Arrays.sort(list);
        return list;
    }

    public static int countLines(String filename) throws IOException {
        InputStream is = new BufferedInputStream(new FileInputStream(filename));
        try {
            byte[] c = new byte[1024];
            int count = 0;
            int readChars = 0;
            boolean empty = true;
            while ((readChars = is.read(c)) != -1) {
                empty = false;
                for (int i = 0; i < readChars; ++i) {
                    if (c[i] == '\n') {
                        ++count;
                    }
                }
            }
            return (count == 0 && !empty) ? 1 : count;
        } finally {
            is.close();
        }
    }

    //// Important classes

    private PieDataset createExerciseDataSet() throws IOException {
        DefaultPieDataset result = new DefaultPieDataset();

        Scanner input = new Scanner(new File("res/database/exercises.csv"));
        int legsVal = 0;
        int backVal = 0;
        int shouldersVal = 0;
        int chestVal = 0;
        int bicepsVal = 0;
        int tricepsVal = 0;

        while (input.hasNextLine()) {

            String[] temp = input.nextLine().split(",");

            if (temp[1].equals("legs")) {
                String filePath = "res/users/" + Main.currentUser.getUsername() + "/" + temp[0] + ".txt";
                legsVal += countLines(filePath);
            }
            if (temp[1].equals("back")) {
                String filePath = "res/users/" + Main.currentUser.getUsername() + "/" + temp[0] + ".txt";
                backVal += countLines(filePath);
            }
            if (temp[1].equals("shoulders")) {
                String filePath = "res/users/" + Main.currentUser.getUsername() + "/" + temp[0] + ".txt";
                shouldersVal += countLines(filePath);
            }
            if (temp[1].equals("chest")) {
                String filePath = "res/users/" + Main.currentUser.getUsername() + "/" + temp[0] + ".txt";
                chestVal += countLines(filePath);
            }
            if (temp[1].equals("biceps")) {
                String filePath = "res/users/" + Main.currentUser.getUsername() + "/" + temp[0] + ".txt";
                bicepsVal += countLines(filePath);
            }
            if (temp[1].equals("triceps")) {
                String filePath = "res/users/" + Main.currentUser.getUsername() + "/" + temp[0] + ".txt";
                tricepsVal += countLines(filePath);
            }

        }

        result.setValue("Legs", legsVal);
        result.setValue("Back", backVal);
        result.setValue("Shoulders", shouldersVal);
        result.setValue("Chest", chestVal);
        result.setValue("Biceps", bicepsVal);
        result.setValue("Triceps", tricepsVal);

        return result;
    }

    // Creating dataset
    private XYDataset createDataset( ) throws IOException {
        XYSeries series = new XYSeries("Data");
        XYSeriesCollection dataset = new XYSeriesCollection();

        String filePath = "res/users/" + Main.currentUser.getUsername()+ "/" + this.currentExercise + ".txt";
        Scanner input = new Scanner(new File(filePath));

        int pos = 1;

        while (input.hasNextLine()) {
            String[] current = input.nextLine().split(",");
            if (currentOption.equals("Reps"))
                series.add(pos, Integer.parseInt(current[1]));
            else if (currentOption.equals("Weight"))
                series.add(pos, Integer.parseInt(current[2]));
            pos++;
        }

        dataset.addSeries(series);

        return dataset;
    }

    private JFreeChart createChart(PieDataset dataset, String title) {

        JFreeChart chart = ChartFactory.createPieChart3D(
                title,                  // chart title
                dataset,                // data
                true,                   // include legend
                true,
                false
        );

        PiePlot3D plot = (PiePlot3D) chart.getPlot();
        plot.setStartAngle(290);
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setForegroundAlpha(0.3f);
        return chart;

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == list) {
            currentExercise = list.getSelectedValue().toString();
            try {
                lineChartMaker();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            chartArea.validate();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == cb){
            currentExercise = cb.getEditor().getItem().toString();
            System.out.println(currentExercise);
            validate();
        }

        if (e.getSource() == reps_button) {
            currentOption = "Reps";
            try {
                lineChartMaker();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            chartArea.validate();
        }
        if (e.getSource() == weight_button) {
            currentOption = "Weight";
            try {
                lineChartMaker();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            chartArea.validate();
        }

        if (e.getSource() == back) {
            this.dispose();
            new InfoScreen2();
        }

    }

    public void refresh() {
        invalidate();
        validate();
        repaint();
    }


}
