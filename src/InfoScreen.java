import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;
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

    private JLabel armInfo = new JLabel();
    private JLabel chestInfo = new JLabel();
    private JLabel absInfo = new JLabel();
    private JLabel upLegInfo = new JLabel();
    private JLabel lowLegInfo = new JLabel();

    private JButton showHistory = new JButton("Show History");
    private JButton exit = new JButton("EXIT");

    public static int width = (int) dim.getWidth();

    public static int height = (int) dim.getHeight();

    int x = MouseInfo.getPointerInfo().getLocation().x;

    int y = MouseInfo.getPointerInfo().getLocation().y;

    boolean inside = false;

    Border border = BorderFactory.createLineBorder(Color.BLACK, 5);

    public InfoScreen() {

        setBackground(new Color(57, 173, 189));
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // setLayout(null);

        panel.setBounds(0, 0, width, height);
        panel.setBackground(Color.WHITE);
        panel.setLayout(null);
        add(panel);

        body.setIcon(new ImageIcon(new ImageIcon("res/FitBody.png").getImage().getScaledInstance(543, 800, 0)));
        body.setBounds((width / 2) - 270, (height / 2) - 400, 543, 800);
        panel.add(body);

        // change location to match image

        arm.setBounds(((width / 2) - 270) + 60, ((height / 2) - 400) + 180, 120, 225);
        chest.setBounds(((width / 2) - 270) + 180, ((height / 2) - 400) + 130, 180, 100);
        abs.setBounds(((width / 2) - 270) + 180, ((height / 2) - 400) + 250, 180, 100);
        upLeg.setBounds(((width / 2) - 270) + 130, ((height / 2) - 400) + 380, 190, 185);
        lowLeg.setBounds(((width / 2) - 270) + 180, ((height / 2) - 400) + 580, 190, 165);

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

        /*armInfo.setBounds(((width / 2) - 270) + 60, ((height / 2) - 400) + 180, 120, 225);
        chestInfo.setBounds(((width / 2) - 270) + 180, ((height / 2) - 400) + 130, 180, 100);
        absInfo.setBounds(((width / 2) - 270) + 180, ((height / 2) - 400) + 250, 180, 100);
        upLegInfo.setBounds(((width / 2) - 270) + 180, ((height / 2) - 400) + 380, 190, 185);
        lowLegInfo.setBounds(((width / 2) - 270) + 180, ((height / 2) - 400) + 580, 190, 165);
        */


        armInfo.setBorder(border);
        chestInfo.setBorder(border);
        absInfo.setBorder(border);

        lowLegInfo.setBorder(border);

        // TODO: Backup

        JPanel buttons = new JPanel();
        buttons.setLayout(new FlowLayout(FlowLayout.CENTER, (int) (Main.dim.getWidth()/2-300), 10));
        buttons.setBackground(Color.WHITE);

        exit.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 26));
        exit.setPreferredSize(new Dimension(220, 50));
        exit.setMinimumSize(new Dimension(220, 50));
        exit.setMaximumSize(new Dimension(220, 50));
        exit.setBackground(Color.RED);
        exit.setForeground(Color.WHITE);
        exit.setBorderPainted(false);
        exit.addActionListener(this);
        buttons.add(exit);

        showHistory.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 26));
        showHistory.setPreferredSize(new Dimension(220, 50));
        showHistory.setMinimumSize(new Dimension(220, 50));
        showHistory.setMaximumSize(new Dimension(220, 50));
        showHistory.setBackground(Color.RED);
        showHistory.setForeground(Color.WHITE);
        showHistory.setBorderPainted(false);
        showHistory.addActionListener(this);
        buttons.add(showHistory);

        add(buttons, BorderLayout.PAGE_END);

        setVisible(true);
        setVisible(true);
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
        repaint();

        System.out.println("screen built");

    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == showHistory) {
            try {
                this.setVisible(false);
                new Statistics();
            } catch (IOException e1) { e1.printStackTrace(); }
        }
        if (e.getSource() == exit) {
            try {
                this.setVisible(false);
                new Login();
            } catch (IOException e1) { e1.printStackTrace(); }
        }

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
            arm.setBounds(((width / 2) - 270) - (3 * (width / 10)) - 20, ((height / 2) - 400) - (2 * (height / 10)), 180 + (3 * (width / 10)), 300 + (3 * (height / 10)));
            System.out.println("hi");

        }
        if (e.getSource() == chest) {
            chest.setBounds(((width / 2) - 270) + (1 * (width / 10)) - 20, ((height / 2) - 400) - (2 * (height / 10) ), 180 + (3 * (width / 10)), 250 + (3 * (height / 10)));
            System.out.println("hi");

        }
        if (e.getSource() == abs) {
            System.out.println("hi");
            abs.setBounds(((width / 2) - 270) + (1 * (width / 10)) - 20, ((height / 2) - 400) - (2 * (height / 10) - 250), 180 + (3 * (width / 10)), 250 + (3 * (height / 10)));


        }
        if (e.getSource() == upLeg) {

            upLeg.setBounds(((width / 2) - 270) - 420, ((height / 2) - 400) + 380,280 + (2 * (width / 10)), 270 + (2 * (height / 10)));

            /*
            upLegInfo.setBounds(((width / 2) - 270) - (3 * (width / 10))+ 20, ((height / 2) - 400) + (3 * (height / 10)), 180 + (3 * (width / 10)), 300 + (3 * (height / 10)));
                    //.setBounds(((width / 2) - 270) - (3 * (width / 10)), ((height / 2) - 400) + (5 * (height / 10)), 180 + (3 * (width / 10)) + 10, 300 + (3 * (height / 10)));
            upLegInfo.setBorder(border);
            upLegInfo.setVisible(true);
            upLegInfo.addMouseListener(this);
            panel.add(upLegInfo);
            revalidate();
            repaint();
            */


            System.out.println("upLeg");

        }
        if (e.getSource() == lowLeg) {
            System.out.println("hi");

            lowLeg.setBounds(((width / 2) - 270) + 180, ((height / 2) - 400) + 580,190 + (2 * (width / 10)), 150 + (2 * (height / 10)));


        }/*if (e.getSource() == upLegInfo){
            inside = true;
        }*/

    }

    @Override
    public void mouseExited(MouseEvent e) {

        //code for mouse exiting

        if (e.getSource() == arm) {
            arm.setBounds(((width / 2) - 270) + 60, ((height / 2) - 400) + 180, 120, 225);

        }
        if (e.getSource() == chest) {
            chest.setBounds(((width / 2) - 270) + 180, ((height / 2) - 400) + 130, 180, 100);
        }
        if (e.getSource() == abs) {
            abs.setBounds(((width / 2) - 270) + 180, ((height / 2) - 400) + 250, 180, 100);

        }
        if (e.getSource() == upLeg) {

            upLeg.setBounds(((width / 2) - 270) + 130, ((height / 2) - 400) + 380, 190, 185);

            /*if (inside) {


            }else {
                inside = false;
                panel.remove(upLegInfo);
                revalidate();
                repaint();
            }*/


        }
        if (e.getSource() == lowLeg) {

            lowLeg.setBounds(((width / 2) - 270) + 180, ((height / 2) - 400) + 580, 190, 165);

        }

        /*if (e.getSource() == upLegInfo) {
            panel.remove(upLegInfo);
            inside = false;
            revalidate();
            repaint();


        }*/


    }

    public String[] toNormalArray(ArrayList<String> list) {
        String[] arr = new String[list.size()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = list.get(i);
        }


        System.out.println("string is " + Arrays.toString(toNormalArray(new ArrayList<>())));



        return arr;
    }



    //https://www.youtube.com/watch?v=DVWpgrCaAjA

    public void paint(Graphics g) {

        super.paint(g);



    }


}
