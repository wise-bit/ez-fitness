import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Login extends JFrame {

    private JLabel appTitle = new JLabel("EZFitness Pro", SwingConstants.CENTER);
    private Font font = new Font("Sylfaen", Font.BOLD, 80); // Freestyle Script, Matura MT Script Capitals, French Script MT

    public Login() throws IOException {

        setLayout(new BorderLayout());
        this.setTitle("EZFitness Pro");
        // setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("EZFitness/res/background.jpg")))));
        this.setBackground(Color.WHITE);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        appTitle.setForeground(Color.BLUE);
        appTitle.setFont(font);
        appTitle.setSize((int) (Main.dim.getWidth()*3.0/4), (int) (Main.dim.getWidth()*3.0/5));
        add(appTitle, BorderLayout.PAGE_START);
        appTitle.setBorder( new MatteBorder(0, 0, 5, 0, Color.black));
        appTitle.setVisible(true);

        ImageIcon link = new ImageIcon("res/mainScreenImage.JPG");

        JLabel image = new JLabel(link);
        // image.setSize((int) (Main.dim.getWidth()*3.0/8), image.getSize().height);
        image.setMaximumSize(new Dimension((int) (Main.dim.getWidth()*3.0/8), image.getSize().height));
        this.getContentPane().add(image, BorderLayout.LINE_START);

        JPanel form = new JPanel();
        form.add(new JLabel("aaaa"));
        this.getContentPane().add(form, BorderLayout.CENTER);

        add(new JLabel("bbbb"), BorderLayout.LINE_END);

        setVisible(true);
        // setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
        setSize((int) (Main.dim.getWidth()*3.0/4), (int) (Main.dim.getHeight()*3.0/4));
        this.setLocation(Main.dim.width/2-this.getSize().width/2, Main.dim.height/2-this.getSize().height/2);
        repaint();

    }

}
