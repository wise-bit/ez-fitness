import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Statistics extends JFrame implements MouseListener, ActionListener {

    JLabel appTitle = new JLabel("Statistics and History", SwingConstants.CENTER);
    private Font font = new Font("Consolas", Font.BOLD, 80); // Freestyle Script, Matura MT Script Capitals, French Script MT
    private JComboBox<String> cb;
    String currentExercise = "Overall";
    String currentOption = "Reps";
    JFreeChart lineChart;
    JPanel chartArea;

    private JButton exercise_button;
    private JButton time_button;
    private JButton reps_button;
    private JButton weight_button;

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

        PieDataset dataSet = createExerciseDataSet();
        JFreeChart chart = createChart(dataSet, "Pie Chart!"); // TODO: UPDATE
        ChartPanel myChart = new ChartPanel(chart);
        myChart.setMouseWheelEnabled(true);
        chartArea.add(myChart, BorderLayout.NORTH);

        lineChartMaker(currentOption);

        add(chartArea, BorderLayout.EAST);
        validate(); // TODO: UPDATE

        JPanel leftPanel = new JPanel(new BorderLayout());

        // chartArea.setBackground(new Color(255, 243, 160));

        JPanel userOptions = new JPanel();
        String[] choices =  generateChoices();
        cb = new JComboBox<String>(choices);
        cb.setVisible(true);
        cb.setSize(2000, cb.getPreferredSize().height);
        userOptions.add(cb);
        cb.addActionListener(this);
        leftPanel.add(userOptions, BorderLayout.NORTH);


        /////////////////////////////////////////////

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 10));

        exercise_button = new JButton("EXERCISE");
        exercise_button.addActionListener(this);
        exercise_button.setBackground(Color.WHITE);
        exercise_button.setBorderPainted(false);
        buttonsPanel.add(exercise_button);

        time_button = new JButton("TIME");
        time_button.addActionListener(this);
        time_button.setBackground(Color.WHITE);
        time_button.setBorderPainted(false);
        buttonsPanel.add(time_button);

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

    public void lineChartMaker(String yAxis) {
        lineChart = ChartFactory.createLineChart("Line Graph For Specific Exercise", "Date", yAxis , createDataset(), PlotOrientation.VERTICAL, true,true,false);
        ChartPanel chartPanel = new ChartPanel( lineChart );
        // chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
        // setContentPane(chartPanel);
        chartArea.add(chartPanel);
    }


    //// Helper classes

    public String[] generateChoices() throws IOException {
        String[] list = new String[countLines("res/exercises.txt")];
        File exerciseList = new File("res/exercises.txt");
        Scanner reader = new Scanner(exerciseList);
        int pos = 0;
        while (reader.hasNextLine()){
            String current = reader.nextLine();
            list[pos] = current;
            pos++;
        }
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

    private PieDataset createExerciseDataSet() {
        DefaultPieDataset result = new DefaultPieDataset();
        result.setValue("Arm", 100);
        result.setValue("Low Leg", 20);
        result.setValue("High Leg", 20);
        result.setValue("Chest", 51);
        result.setValue("Abs", 51);
        return result;
    }

    private DefaultCategoryDataset createDataset( ) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
        dataset.addValue( 15 , "schools" , "1970" );
        dataset.addValue( 30 , "schools" , "1980" );
        dataset.addValue( 60 , "schools" ,  "1990" );
        dataset.addValue( 120 , "schools" , "2000" );
        dataset.addValue( 240 , "schools" , "2010" );
        dataset.addValue( 300 , "schools" , "2014" );
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

        if (e.getSource() == time_button) {
            currentOption = "Time";
            validate();
        }
        if (e.getSource() == reps_button) {
            currentOption = "Reps";
            validate();
        }
        if (e.getSource() == weight_button) {
            currentOption = "Weight";
            lineChartMaker(currentOption);
        }

    }

    public void refresh() {
        invalidate();
        validate();
        repaint();
    }


}
