//import org.jfree.chart.ChartFactory;
//import org.jfree.chart.ChartPanel;
//import org.jfree.chart.JFreeChart;
//import org.jfree.chart.plot.PiePlot3D;
//import org.jfree.chart.plot.PlotOrientation;
//import org.jfree.data.category.DefaultCategoryDataset;
//import org.jfree.data.general.DefaultPieDataset;
//import org.jfree.data.general.PieDataset;
//import org.jfree.util.Rotation;
//
//import javax.swing.*;
//import javax.swing.border.Border;
//import java.awt.*;
//import java.awt.event.*;
//import java.io.*;
//import java.util.ArrayList;
//import java.util.Scanner;
//
//public class Statistics extends JFrame implements MouseListener, ActionListener {
//
//    JLabel appTitle = new JLabel("Statistics and History", SwingConstants.CENTER);
//    private Font font = new Font("Consolas", Font.BOLD, 80); // Freestyle Script, Matura MT Script Capitals, French Script MT
//    private JComboBox<String> cb;
//    String currentExercise = "Overall";
//    String currentOption = "Reps";
//    JFreeChart lineChart;
//    JPanel chartArea;
//    ChartPanel chartPanel;
//    JList list;
//
//    private JButton exercise_button;
//    private JButton time_button;
//    private JButton reps_button;
//    private JButton weight_button;
//    private JButton back;
//
//    public Statistics() throws IOException {
//
//        this.getContentPane().setBackground(new Color(255, 243, 160));
//        setLayout(new BorderLayout());
//        this.setTitle("Statistics and History");
//        // setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("EZFitness/res/background.jpg")))));
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        Border titleBorder = BorderFactory.createLineBorder(Color.DARK_GRAY, 2);
//
//        //////////////////////////////////////////////////////
//        appTitle.setForeground(Color.WHITE);
//        appTitle.setBackground(Color.BLACK);
//        appTitle.setFont(font);
//        // appTitle.setSize(this.getWidth()-20,(int) (900/10));
//        appTitle.setBorder(titleBorder);
//        appTitle.setOpaque(true);
//        add(appTitle, BorderLayout.PAGE_START);
//        //////////////////////////////////////////////////////
//
//        chartArea = new JPanel();
//        chartArea.setLayout(new BorderLayout());
//
//        PieDataset dataSet = createExerciseDataSet();
//        JFreeChart chart = createChart(dataSet, "Part Of Body Involved"); // TODO: UPDATE
//        ChartPanel myChart = new ChartPanel(chart);
//        myChart.setMouseWheelEnabled(true);
//        chartArea.add(myChart, BorderLayout.NORTH);
//
//        lineChartMaker();
//
//        add(chartArea, BorderLayout.EAST);
//        validate(); // TODO: UPDATE
//
//        JPanel leftPanel = new JPanel(new BorderLayout());
//        JLabel question = new JLabel("Please select an optiton to show its graph:", SwingConstants.CENTER);
//        question.setFont(new Font("Arial",Font.BOLD,18));
//
//        // chartArea.setBackground(new Color(255, 243, 160));
//
////        JPanel userOptions = new JPanel();
////        cb = new JComboBox<String>(choices);
////        cb.setBounds(30, 20, 500, 15);
////        cb.setSize(2000, cb.getPreferredSize().height);
////        userOptions.add(cb, BorderLayout.CENTER);
////        cb.setVisible(true);
////        cb.addActionListener(this);
////        leftPanel.add(userOptions, BorderLayout.CENTER);
//
//        String[] choices =  generateChoices();
//        list = new JList(choices);
//        list.addMouseListener(this);
//        list.setFont(new Font("Arial",Font.BOLD,18));
//        JScrollPane scrollableList = new JScrollPane(list);
//        // scrollableList.getViewport().addChangeListener();
//        scrollableList.setVisible(true);
//
//        leftPanel.setBackground(new Color(255, 243, 160));
//
//        leftPanel.add(question, BorderLayout.NORTH);
//        leftPanel.add(scrollableList, BorderLayout.CENTER);
//
//        /////////////////////////////////////////////
//
//        JPanel buttonsPanel = new JPanel();
//        buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 10));
//
////        exercise_button = new JButton("EXERCISE");
////        exercise_button.addActionListener(this);
////        exercise_button.setBackground(Color.WHITE);
////        exercise_button.setBorderPainted(false);
////        buttonsPanel.add(exercise_button);
//
//        time_button = new JButton("TIME");
//        time_button.addActionListener(this);
//        time_button.setBackground(Color.WHITE);
//        time_button.setBorderPainted(false);
//        buttonsPanel.add(time_button);
//
//        reps_button = new JButton("REPS");
//        reps_button.addActionListener(this);
//        reps_button.setBackground(Color.WHITE);
//        reps_button.setBorderPainted(false);
//        buttonsPanel.add(reps_button);
//
//        weight_button = new JButton("WEIGHT");
//        weight_button.addActionListener(this);
//        weight_button.setBackground(Color.WHITE);
//        weight_button.setBorderPainted(false);
//        buttonsPanel.add(weight_button);
//
//        buttonsPanel.add(Box.createRigidArea(new Dimension(20,0)));
//
//        back = new JButton("BACK");
//        back.addActionListener(this);
//        back.setBackground(Color.BLACK);
//        back.setForeground(Color.WHITE);
//        back.setBorderPainted(false);
//        buttonsPanel.add(back);
//
//        /////////////////////////////////////////////
//
//        leftPanel.add(buttonsPanel, BorderLayout.PAGE_END);
//        add(leftPanel, BorderLayout.CENTER);
//        buttonsPanel.setBackground(Color.red);
//
//        /////////////////////////////////////////////
//
//        setVisible(true);
//        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
////        this.setLocation(Main.dim.width/2-this.getSize().width/2, Main.dim.height/2-this.getSize().height/2);
//        repaint();
//
//    }
//
//    public void lineChartMaker() {
//        try {
//            chartArea.remove(chartPanel);
//        } catch (Exception e) {
//
//        }
//        lineChart = ChartFactory.createLineChart("Line Graph For: " + currentExercise, "Date", currentOption , createDataset(), PlotOrientation.VERTICAL, true,true,false);
//        chartPanel = new ChartPanel( lineChart );
//        // chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
//        // setContentPane(chartPanel);
//        chartArea.add(chartPanel);
//    }
//
//
//    //// Helper classes
//
//    public String[] generateChoices() throws IOException {
//        String[] list = new String[countLines("res/database/exercises.csv")];
//        File exerciseList = new File("res/database/exercises.csv");
//        Scanner reader = new Scanner(exerciseList);
//        int pos = 0;
//        while (reader.hasNextLine()){
//            String[] current = reader.nextLine().split(",");
//            list[pos] = current[0];
//            pos++;
//        }
//        return list;
//    }
//
//    public static int countLines(String filename) throws IOException {
//        InputStream is = new BufferedInputStream(new FileInputStream(filename));
//        try {
//            byte[] c = new byte[1024];
//            int count = 0;
//            int readChars = 0;
//            boolean empty = true;
//            while ((readChars = is.read(c)) != -1) {
//                empty = false;
//                for (int i = 0; i < readChars; ++i) {
//                    if (c[i] == '\n') {
//                        ++count;
//                    }
//                }
//            }
//            return (count == 0 && !empty) ? 1 : count;
//        } finally {
//            is.close();
//        }
//    }
//
//    //// Important classes
//
//    private PieDataset createExerciseDataSet() {
//        DefaultPieDataset result = new DefaultPieDataset();
//        result.setValue("Arm", 100);
//        result.setValue("Low Leg", 20);
//        result.setValue("High Leg", 20);
//        result.setValue("Chest", 51);
//        result.setValue("Abs", 51);
//        return result;
//    }
//
//    private DefaultCategoryDataset createDataset( ) {
//        DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
//        dataset.addValue( 15 , "schools" , "1970" );
//        dataset.addValue( 30 , "schools" , "1980" );
//        dataset.addValue( 60 , "schools" ,  "1990" );
//        dataset.addValue( 120 , "schools" , "2000" );
//        dataset.addValue( 240 , "schools" , "2010" );
//        dataset.addValue( 300 , "schools" , "2014" );
//        return dataset;
//    }
//
//    private JFreeChart createChart(PieDataset dataset, String title) {
//
//        JFreeChart chart = ChartFactory.createPieChart3D(
//                title,                  // chart title
//                dataset,                // data
//                true,                   // include legend
//                true,
//                false
//        );
//
//        PiePlot3D plot = (PiePlot3D) chart.getPlot();
//        plot.setStartAngle(290);
//        plot.setDirection(Rotation.CLOCKWISE);
//        plot.setForegroundAlpha(0.3f);
//        return chart;
//
//    }
//
//    @Override
//    public void mouseClicked(MouseEvent e) {
//        if (e.getSource() == list) {
//            currentExercise = list.getSelectedValue().toString();
//            lineChartMaker();
//            chartArea.validate();
//        }
//    }
//
//    @Override
//    public void mousePressed(MouseEvent e) {
//
//    }
//
//    @Override
//    public void mouseReleased(MouseEvent e) {
//
//    }
//
//    @Override
//    public void mouseEntered(MouseEvent e) {
//
//    }
//
//    @Override
//    public void mouseExited(MouseEvent e) {
//
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//
//        if (e.getSource() == cb){
//            currentExercise = cb.getEditor().getItem().toString();
//            System.out.println(currentExercise);
//            validate();
//        }
//
//        if (e.getSource() == time_button) {
//            currentOption = "Time";
//            lineChartMaker();
//            chartArea.validate();
//        }
//        if (e.getSource() == reps_button) {
//            currentOption = "Reps";
//            lineChartMaker();
//            chartArea.validate();
//        }
//        if (e.getSource() == weight_button) {
//            currentOption = "Weight";
//            lineChartMaker();
//            chartArea.validate();
//        }
//
//        if (e.getSource() == back) {
//            this.setVisible(false);
//            new InfoScreen();
//        }
//
//    }
//
//    public void refresh() {
//        invalidate();
//        validate();
//        repaint();
//    }
//
//
//}
