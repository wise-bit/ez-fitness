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

public class InfoScreen2 extends JFrame implements ActionListener, MouseListener {

    public static Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

    private JPanel panel = new JPanel();
    private JPanel panel2 = new JPanel();

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

    public static int width = (int) dim.getWidth() - 400;

    public static int height = (int) dim.getHeight();

    public static int width1 = (int) dim.getWidth();


    private int x = 0;

    private int y = 100;

    boolean inside = false;

    Border border = BorderFactory.createLineBorder(Color.BLACK, 5);

    public InfoScreen2() {

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
        setSize(width1, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        panel.setBounds(0, 0, width1 - 400, height);
        panel.setBackground(Color.WHITE);
        panel.setLayout(null);
        panel.setBorder(border);
        add(panel);

        panel2.setBounds(width1 - 400, 0, 390, height - 400);
        panel2.setBackground(Color.WHITE);
        panel2.setLayout(new BorderLayout());
        panel2.setBorder(border);
        add(panel2);


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
        showHistory.setBounds((width / 10) * 1, (height / 10) * 8, width / 5, height / 11);
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

        System.out.println("screen built");

    }



    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == arm) {

            panel2.removeAll();

            armText.setFont(new Font("Arial",Font.BOLD,40));
            armText.setVisible(true);
            armTitle.setFont(new Font("Arial",Font.BOLD,40));
            armTitle.setVisible(true);
            panel2.add(armTitle, BorderLayout.NORTH);
            panel2.add(armText, BorderLayout.CENTER);
            panel2.revalidate();
            panel2.repaint();

            System.out.println("in arm");

        }
        if (e.getSource() == chest) {

            panel2.removeAll();

            System.out.println("in chest");

            chestText.setFont(new Font("Arial",Font.BOLD,40));
            chestText.setVisible(true);
            shouldersText.setFont(new Font("Arial",Font.BOLD,40));
            shouldersText.setVisible(true);
            chestTitle.setFont(new Font("Arial",Font.BOLD,40));
            chestTitle.setVisible(true);

            panel2.add(chestTitle, BorderLayout.NORTH);
            panel2.add(chestText, BorderLayout.CENTER);
            panel2.add(shouldersText, BorderLayout.SOUTH);

            panel2.revalidate();
            panel2.repaint();


        }
        if (e.getSource() == back) {

            panel2.removeAll();
            System.out.println("in back");

            backText.setFont(new Font("Arial",Font.BOLD,40));
            backText.setVisible(true);
            backTitle.setFont(new Font("Arial",Font.BOLD,40));
            backTitle.setVisible(true);
            panel2.add(backTitle, BorderLayout.NORTH);
            panel2.add(backText, BorderLayout.CENTER);
            panel2.revalidate();
            panel2.repaint();


        }
        if (e.getSource() == arm2) {

            panel2.removeAll();
            System.out.println("in arm2");


            arm2Text.setFont(new Font("Arial",Font.BOLD,40));
            arm2Text.setVisible(true);
            arm2Title.setFont(new Font("Arial",Font.BOLD,40));
            arm2Title.setVisible(true);
            panel2.add(arm2Title, BorderLayout.NORTH);
            panel2.add(arm2Text, BorderLayout.CENTER);
            panel2.revalidate();
            panel2.repaint();

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

            panel2.removeAll();

            System.out.println("in leg");

            legText.setBounds( 0, 0, 200, 200);
            legText.setFont(new Font("Arial",Font.BOLD,40));
            legText.setVisible(true);
            legTitle.setFont(new Font("Arial",Font.BOLD,40));
            legTitle.setVisible(true);
            panel2.add(legTitle, BorderLayout.NORTH);
            panel2.add(legText, BorderLayout.CENTER);
            panel2.revalidate();
            panel2.repaint();


        }

        if (e.getSource() == armText) {

            armText.setFont(new Font("Arial",Font.BOLD,40));
            armText.setVisible(true);
            armTitle.setFont(new Font("Arial",Font.BOLD,40));
            armTitle.setVisible(true);
            arm.add(armTitle, BorderLayout.PAGE_START);
            arm.add(armText, BorderLayout.CENTER);
            arm.revalidate();
            arm.repaint();

            System.out.println("in armText");
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

    public String[] toNormalArray(ArrayList<String> list) {
        String[] arr = new String[list.size()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }

}
