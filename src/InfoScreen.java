import java.awt.BorderLayout;
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
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.Timer;
import javax.swing.border.Border;

public class InfoScreen extends JFrame implements ActionListener, MouseListener {

    public static Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

    private JPanel panel = new JPanel();

    private JLabel body = new JLabel();

    private JLabel armTitle = new JLabel("Biceps");
    private JLabel arm2Title = new JLabel("Triceps");
    private JLabel chestTitle = new JLabel("Chest & Shoulders");
    private JLabel legTitle = new JLabel("Legs");
    private JLabel backTitle = new JLabel("Back");


    // private Timer timer = new Timer(100, this);

    private JPanel arm = new JPanel();
    private JPanel chest = new JPanel();
    private JPanel back = new JPanel();
    private JPanel arm2 = new JPanel();
    private JPanel leg = new JPanel();

    // Box is unused
    private JPanel armBox = new JPanel();
    private JPanel chestBox = new JPanel();
    private JPanel backBox = new JPanel();
    private JPanel arm2Box = new JPanel();
    private JPanel legBox = new JPanel();

    // stores exercises details
    private String armInfo = "";
    private String chestInfo = "";
    private String backInfo = "";
    private String arm2Info = "";
    private String legInfo = "";

    private ArrayList<String> legsList = new ArrayList<String>();
    private ArrayList<String> chestList = new ArrayList<String>();
    private ArrayList<String> backList = new ArrayList<String>();
    private ArrayList<String> shouldersList = new ArrayList<String>();
    private ArrayList<String> bicepsList = new ArrayList<String>();
    private ArrayList<String> tricepsList = new ArrayList<String>();

    // shows exercises
    private JList<String> armText = new JList<String>();
    private JList<String> chestText = new JList<String>();
    private JList<String> backText = new JList<String>();
    private JList<String> arm2Text = new JList<String>();
    private JList<String> shouldersText = new JList<String>();
    private JList<String> legText = new JList<String>();

    private JButton showHistory = new JButton("Show History");

    public static int width = (int) dim.getWidth();

    public static int height = (int) dim.getHeight();

    private int x = 0;

    private int y = 100;

    boolean inside = false;

    Border border = BorderFactory.createLineBorder(Color.BLACK, 5);

    public InfoScreen() {

        try {
            Scanner input = new Scanner(new File("res/exersises.csv"));
            // input.useDelimiter(",");

            while (input.hasNextLine()) {

                String[] temp = input.nextLine().split(",");

                if (temp[1].equals("legs")) {

                    legsList.add(temp[0]);
                    System.out.println("legs added " + temp[0]);

                }
                if (temp[1].equals("back")) {

                    backList.add(temp[0]);
                    System.out.println("back added " + temp[0]);

                }
                if (temp[1].equals("shoulders")) {

                    shouldersList.add(temp[0]);
                    System.out.println("shoulders added " + temp[0]);

                }
                if (temp[1].equals("chest")) {

                    chestList.add(temp[0]);
                    System.out.println("chest added " + temp[0]);

                }
                if (temp[1].equals("biceps")) {

                    bicepsList.add(temp[0]);
                    System.out.println("bisceps added " + temp[0]);

                }
                if (temp[1].equals("triceps")) {

                    tricepsList.add(temp[0]);
                    System.out.println("triceps added " + temp[0]);

                }

            }
            input.close();

        } catch (FileNotFoundException e) {

            System.out.println("File not found.");
        }

        // timer.start();

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

        arm.setBounds(((width / 2) - 270) + 60, ((height / 2) - 400) + 180, 120, 225);
        chest.setBounds(((width / 2) - 270) + 180, ((height / 2) - 400) + 130, 180, 100);
        back.setBounds(((width / 2) - 270) + 180, ((height / 2) - 400) + 250, 180, 100);
        arm2.setBounds(((width / 2) - 270) + 360, ((height / 2) - 400) + 180, 120, 225);
        leg.setBounds(((width / 2) - 270) + 180, ((height / 2) - 400) + 420, 190, 165);

//		chest.setLayout(new BorderLayout());
//		JPanel chestRight = new JPanel();
//		JPanel chestLeft = new JPanel();
//		chestRight.setBorder(border);
//		chestLeft.setBorder(border);
//		chest.add(chestLeft, BorderLayout.WEST);
//		chest.add(chestRight, BorderLayout.EAST);

        arm.setForeground(Color.RED);
        chest.setForeground(Color.RED);
        back.setForeground(Color.RED);
        arm2.setForeground(Color.RED);
        leg.setForeground(Color.RED);

        arm.setBorder(border);
        chest.setBorder(border);
        back.setBorder(border);
        arm2.setBorder(border);
        leg.setBorder(border);

        arm.addMouseListener(this);
        chest.addMouseListener(this);
        back.addMouseListener(this);
        arm2.addMouseListener(this);
        leg.addMouseListener(this);

        panel.add(arm);
        panel.add(chest);
        panel.add(back);
        panel.add(arm2);
        panel.add(leg);

        /*
         * armBox.setBounds(((width / 2) - 270) + 60, ((height / 2) - 400) + 180, 120,
         * 225); chestBox.setBounds(((width / 2) - 270) + 180, ((height / 2) - 400) +
         * 130, 180, 100); backBox.setBounds(((width / 2) - 270) + 180, ((height / 2) -
         * 400) + 250, 180, 100); arm2Box.setBounds(((width / 2) - 270) + 180, ((height
         * / 2) - 400) + 380, 190, 185); legBox.setBounds(((width / 2) - 270) + 180,
         * ((height / 2) - 400) + 580, 190, 165);
         */

        armBox.setBorder(border);
        chestBox.setBorder(border);
        backBox.setBorder(border);
        arm2Box.setBorder(border);
        legBox.setBorder(border);

        showHistory.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 26));
        showHistory.setBounds((width / 10) * 8, (height / 10) * 8, width / 9, height / 11);
        showHistory.addActionListener(this);
        add(showHistory);

        // .addMouseListener(this);

        setVisible(true);

        armText = new JList<String>(toNormalArray(bicepsList));
        shouldersText = new JList<String>(toNormalArray(shouldersList));
        chestText = new JList<String>(toNormalArray(chestList));
        backText = new JList<String>(toNormalArray(backList));
        arm2Text = new JList<String>(toNormalArray(tricepsList));
        legText = new JList<String>(toNormalArray(legsList));


        System.out.println("string is " + Arrays.toString(toNormalArray(bicepsList)));
//		armText.setFont(new Font("Arial",Font.BOLD,18));
//		armText.setBounds( 0, 0, 200, 200);
//		armText.setVisible(true);
//		panel.add(armText);


        System.out.println("screen built");

    }



    public void actionPerformed(ActionEvent e) {

        // if(e.getSource() == timer) {
        //
        // x += 10;
        //
        // if(x <= (int) dim.getWidth()) {
        //
        // repaint();
        //
        // }
        // }

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

        // code for mouse entering

        if (e.getSource() == arm) {
            //arm.setBounds(((width / 2) - 270) - (3 * (width / 10)) - 20, ((height / 2) - 400) - (1 * (height / 10)),200 + (3 * (width / 10)), 200 + (3 * (height / 10)));
            arm.setBounds(((width / 2) - 270) + 60 - 600, ((height / 2) - 400) + 180 - 200, 120 + 600, 225 + 200);


            armText.setFont(new Font("Arial",Font.BOLD,40));
            armText.setVisible(true);
            armTitle.setFont(new Font("Arial",Font.BOLD,40));
            armTitle.setVisible(true);
            arm.add(armTitle, BorderLayout.PAGE_START);
            arm.add(armText, BorderLayout.CENTER);
            arm.revalidate();
            arm.repaint();

            System.out.println("in arm");

            //arm.add(armText);
            //armText.setBounds( 0, 0, 200, 200);
        }
        if (e.getSource() == chest) {
            chest.setBounds(((width / 2) - 270) + (1 * (width / 10)) - 20, ((height / 2) - 400) - (1 * (height / 10)),180 + (3 * (width / 10)), 250 + (3 * (height / 10)));
            //chest.setBounds(((width / 2) - 270) + 180, ((height / 2) - 400) + 130, 180, 100);
            System.out.println("in chest");

            chestText.setFont(new Font("Arial",Font.BOLD,40));
            chestText.setVisible(true);
            shouldersText.setFont(new Font("Arial",Font.BOLD,40));
            shouldersText.setVisible(true);
            chestTitle.setFont(new Font("Arial",Font.BOLD,40));
            chestTitle.setVisible(true);

            chest.add(chestTitle, BorderLayout.LINE_START);
            chest.add(chestText, BorderLayout.WEST);
            chest.add(shouldersText, BorderLayout.EAST);

            chest.revalidate();
            chest.repaint();


        }
        if (e.getSource() == back) {
            //back.setBounds(((width / 2) - 270) + 180, ((height / 2) - 400) + 250, 180, 100);
            back.setBounds(((width / 2) - 270) + (1 * (width / 10)) - 20,((height / 2) - 400) - (2 * (height / 10) - 250), 180 + (3 * (width / 10)),250 + (3 * (height / 10)));
            System.out.println("in back");


            backText.setFont(new Font("Arial",Font.BOLD,40));
            backText.setVisible(true);
            backTitle.setFont(new Font("Arial",Font.BOLD,40));
            backTitle.setVisible(true);
            back.add(backTitle, BorderLayout.LINE_START);
            back.add(backText, BorderLayout.CENTER);
            back.revalidate();
            back.repaint();

            //backText.setBounds( 0, 0, 200, 200);

        }
        if (e.getSource() == arm2) {
            arm2.setBounds(((width / 2) - 270) + 360, ((height / 2) - 400), 280 + (2 * (width / 10)),270 + (2 * (height / 10)));
            //arm2.setBounds(((width / 2) - 270) + 360, ((height / 2) - 400) + 180, 120, 225);
            System.out.println("in arm2");


            arm2Text.setFont(new Font("Arial",Font.BOLD,40));
            arm2Text.setVisible(true);
            arm2Title.setFont(new Font("Arial",Font.BOLD,40));
            arm2Title.setVisible(true);
            arm2.add(arm2Title, BorderLayout.LINE_START);
            arm2.add(arm2Text, BorderLayout.CENTER);
            arm2.revalidate();
            arm2.repaint();

            //arm2Text.setBounds( 0, 0, 200, 200);

            /*
             * arm2Box.setBounds(((width / 2) - 270) - (3 * (width / 10))+ 20, ((height / 2)
             * - 400) + (3 * (height / 10)), 180 + (3 * (width / 10)), 300 + (3 * (height /
             * 10))); //.setBounds(((width / 2) - 270) - (3 * (width / 10)), ((height / 2) -
             * 400) + (5 * (height / 10)), 180 + (3 * (width / 10)) + 10, 300 + (3 * (height
             * / 10))); arm2Box.setBorder(border); arm2Box.setVisible(true);
             * arm2Box.addMouseListener(this); panel.add(arm2Box); revalidate(); repaint();
             */



        }
        if (e.getSource() == leg) {
            System.out.println("in leg");
            leg.setBounds(((width / 2) - 270) + 180, ((height / 2) - 400) + 420, 190 + (2 * (width / 10)),180 + (2 * (height / 10)));

            legText.setBounds( 0, 0, 200, 200);
            legText.setFont(new Font("Arial",Font.BOLD,40));
            legText.setVisible(true);
            legTitle.setFont(new Font("Arial",Font.BOLD,40));
            legTitle.setVisible(true);
            leg.add(legTitle, BorderLayout.LINE_START);
            leg.add(legText, BorderLayout.CENTER);
            leg.revalidate();
            leg.repaint();


        } /*
         * if (e.getSource() == arm2Box){ inside = true; }
         */

    }

    @Override
    public void mouseExited(MouseEvent e) {

        // code for mouse exiting

        if (e.getSource() == arm) {
            System.out.println("out arm");
            arm.setBounds(((width / 2) - 270) + 60, ((height / 2) - 400) + 180, 120, 225);

            arm.remove(armText);
            arm.remove(armTitle);
            arm.revalidate();
            arm.repaint();

        }
        if (e.getSource() == chest) {
            chest.setBounds(((width / 2) - 270) + 180, ((height / 2) - 400) + 130, 180, 100);
            System.out.println("out chest");

            chest.remove(chestText);
            chest.remove(shouldersText);
            chest.remove(chestTitle);
            chest.revalidate();
            chest.repaint();

        }
        if (e.getSource() == back) {
            back.setBounds(((width / 2) - 270) + 180, ((height / 2) - 400) + 250, 180, 100);
            System.out.println("out back");

            back.remove(backText);
            back.remove(backTitle);
            back.revalidate();
            back.repaint();

        }
        if (e.getSource() == arm2) {

            arm2.setBounds(((width / 2) - 270) + 360, ((height / 2) - 400) + 180, 120, 225);
            System.out.println("out arm");

            arm2.remove(arm2Text);
            arm2.remove(arm2Title);
            arm2.revalidate();
            arm2.repaint();

            /*
             * if (inside) { }else { inside = false; panel.remove(arm2Box); revalidate();
             * repaint(); }
             */

        }
        if (e.getSource() == leg) {

            leg.setBounds(((width / 2) - 270) + 180, ((height / 2) - 400) + 420, 190, 165);
            System.out.println("out leg");

            leg.remove(legText);
            leg.remove(legTitle);
            leg.revalidate();
            leg.repaint();

        }

        /*
         * if (e.getSource() == arm2Box) { panel.remove(arm2Box); inside = false;
         * revalidate(); repaint(); }
         */

    }

    public String[] toNormalArray(ArrayList<String> list) {
        String[] arr = new String[list.size()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }

    // https://www.youtube.com/watch?v=DVWpgrCaAjA

    // public void paint(Graphics g) {
    //
    // super.paint(g);
    // Graphics2D g2 = (Graphics2D)g;
    // Font font = new Font("Tahoma", Font.BOLD + Font.PLAIN, 100);
    // g2.setFont(font);
    // g2.setColor(Color.red);
    // g2.drawString("TEST", x, y);
    //
    // try {Thread.sleep(1000);}catch(Exception ex) {}
    // x += 10;
    //
    // if(x <= 900) {
    //
    // repaint();
    //
    // }
    //
    //
    //
    //
    //
    //
    // }

}
