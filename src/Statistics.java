import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Statistics extends JFrame implements MouseListener {

    public Statistics() {

        PieDataset dataSet = createDataSet();
        JFreeChart chart = createChart(dataSet, "Pie Chart!");
        ChartPanel myChart = new ChartPanel(chart);
        myChart.setMouseWheelEnabled(true);
        setLayout(new java.awt.BorderLayout());
        add(myChart, BorderLayout.CENTER);
        validate();

        setVisible(true);
        // setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
        setSize(1440, 900);
        this.setLocation(Main.dim.width/2-this.getSize().width/2, Main.dim.height/2-this.getSize().height/2);
        repaint();

    }

    private PieDataset createDataSet() {
        DefaultPieDataset result = new DefaultPieDataset();
        result.setValue("Arm", 100);
        result.setValue("Legs", 20);
        result.setValue("Chest", 51);
        return result;
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
        plot.setForegroundAlpha(0.5f);
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
}
