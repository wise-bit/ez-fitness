import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Login extends JFrame {

    private JLabel appTitle = new JLabel("EZFitness Pro", SwingConstants.CENTER);
    private Font font = new Font("Sylfaen", Font.BOLD, 80); // Freestyle Script, Matura MT Script Capitals, French Script MT

    public Login() throws IOException {
        //Makes frame and sets size and color (full-screen)
        setLayout(null);
        setBounds(0, 0, 1440, 900);
        this.setTitle("EZFitness Pro");
        // setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("EZFitness/res/background.jpg")))));
        this.setBackground(Color.LIGHT_GRAY);

        //Closes program if the exit option is clicked.
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        appTitle.setForeground(Color.BLUE);
        appTitle.setFont(font);
        appTitle.setSize(1440,(int) (900/5));
        add(appTitle);
        appTitle.setVisible(true);

        setVisible(true);
        // setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
        setSize(1440, 900);
        this.setLocation(Main.dim.width/2-this.getSize().width/2, Main.dim.height/2-this.getSize().height/2);
        repaint();

    }

}
