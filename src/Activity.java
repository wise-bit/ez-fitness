import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Activity extends JFrame{

    private JLabel appTitle = new JLabel("Activity Page", SwingConstants.CENTER);
    private Font font = new Font("Sylfaen", Font.BOLD, 80); // Freestyle Script, Matura MT Script Capitals, French Script MT

    public Activity() throws IOException {

        setLayout(null);
        setBounds(0, 0, 1440, 900);
        this.setTitle("EZFitness Pro");
        // setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("EZFitness/res/background.jpg")))));
        setBackground(Color.RED);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        appTitle.setForeground(Color.BLUE);
        appTitle.setFont(font);
        appTitle.setSize(800,(int) (900/5));
        add(appTitle);
        appTitle.setVisible(true);


        // Desktop.getDesktop().open(new File("the.mp4"));

        Border border = BorderFactory.createLineBorder(Color.BLACK, 5);

        JLabel gif = new JLabel(new ImageIcon("res/bench-press.gif"));
        gif.setBounds(1440/5, 900-900/4, 1440 - 4*1440/5, 900 - 4*900/5); // migrate to layoutManager
        gif.setBorder(border);
        add(gif);

        JLabel group = new JLabel(new ImageIcon("res/muscleGroups/chest.jpg"));
        group.setBounds(20, 900-900/3, 1440 - 4*1440/5, 900 - 5*900/8); // migrate to layoutManager
        add(group);


        JLabel clockface = new JLabel(new ImageIcon("res/clockface.jpg"));
        clockface.setBounds(3*1440/7, 900-5*900/6, 1440 - 2*1440/5, 900 - 2*900/5);
        add(clockface);


        setVisible(true);
        // setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
        setSize(1440, 900);
        this.setLocation(Main.dim.width/2-this.getSize().width/2, Main.dim.height/2-this.getSize().height/2);
        repaint();

    }

}
