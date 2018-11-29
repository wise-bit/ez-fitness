import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.MouseInfo;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class InfoScreen extends JFrame implements ActionListener, MouseListener {

    public static Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

    private JPanel panel = new JPanel();

    private JLabel body = new JLabel();

    private JLabel arm = new JLabel();
    private JLabel chest = new JLabel();
    private JLabel abs = new JLabel();
    private JLabel upLeg = new JLabel();
    private JLabel lowLeg = new JLabel();

    private JButton showHistory = new JButton("Show History");

    public static int width = (int) dim.getWidth();

    public static int height = (int) dim.getHeight();

    int x = MouseInfo.getPointerInfo().getLocation().x;

    int y = MouseInfo.getPointerInfo().getLocation().y;

    public InfoScreen() {

        Border border = BorderFactory.createLineBorder(Color.BLACK, 5);

        setBackground(new Color(57, 173, 189));
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        panel.setBounds(0, 0, width, height);
        panel.setBackground(Color.WHITE);
        panel.setLayout(null);
        add(panel);

        body.setIcon(new ImageIcon(new ImageIcon("res/FitBody.png").getImage().getScaledInstance(543, 800, 0)));
        body.setBounds((width / 2) - 270, (height / 2) - 400, 543, 800);
        panel.add(body);

        // change location to match image

        arm.setBounds(((width / 2) - 270) + 150, ((height / 2) - 400) + 300, 75, 75);
        chest.setBounds(((width / 2) - 270) + 250, ((height / 2) - 400) + 300, 75, 75);
        abs.setBounds(((width / 2) - 270) + 200, ((height / 2) - 400) + 400, 150, 100);
        upLeg.setBounds(((width / 2) - 270) + 200, ((height / 2) - 400) + 500, 150, 75);
        lowLeg.setBounds(((width / 2) - 270) + 200, ((height / 2) - 400) + 600, 150, 75);

        arm.setForeground(Color.RED);
        chest.setForeground(Color.RED);
        abs.setForeground(Color.RED);
        upLeg.setForeground(Color.RED);
        lowLeg.setForeground(Color.RED);

        arm.setBorder(border);
        chest.setBorder(border);
        abs.setBorder(border);
        upLeg.setBorder(border);
        lowLeg.setBorder(border);

        arm.addMouseListener(this);
        chest.addMouseListener(this);
        abs.addMouseListener(this);
        upLeg.addMouseListener(this);
        lowLeg.addMouseListener(this);

        panel.add(arm);
        panel.add(chest);
        panel.add(abs);
        panel.add(upLeg);
        panel.add(lowLeg);

        showHistory.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 26));
        showHistory.setBounds((width / 10) * 8, (height / 10) * 8, width / 9, height / 11);
        showHistory.addActionListener(this);
        add(showHistory);

        // .addMouseListener(this);

        setVisible(true);

        System.out.println("hi");

    }

    public void actionPerformed(ActionEvent e) {

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

        //code for mouse entering

        if (e.getSource() == arm) {
            System.out.println("hi");

        }
        if (e.getSource() == chest) {
            System.out.println("hi");

        }
        if (e.getSource() == abs) {
            System.out.println("hi");

        }
        if (e.getSource() == upLeg) {
            System.out.println("hi");

        }
        if (e.getSource() == lowLeg) {
            System.out.println("hi");

        }

    }

    @Override
    public void mouseExited(MouseEvent e) {

        //code for mouse exiting

        if (e.getSource() == arm) {

        }
        if (e.getSource() == chest) {

        }
        if (e.getSource() == abs) {

        }
        if (e.getSource() == upLeg) {

        }
        if (e.getSource() == lowLeg) {

        }

    }

}
