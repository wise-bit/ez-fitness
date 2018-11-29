import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.Flow;

public class Activity extends JFrame implements MouseListener, ActionListener {

    private JLabel appTitle = new JLabel("Activity Page", SwingConstants.CENTER);
    private String codedName = "";

    private Font font = new Font("Consolas", Font.BOLD, 80); // Freestyle Script, Matura MT Script Capitals, French Script MT
    private JLabel clockface;

    private long start = System.currentTimeMillis();
    private long temp = start;
    private long restingTime = 0;

    private Timer timer=new Timer(1000, this);
    private int extraTimer = 0;
    JLabel time;

    private JButton sec15;
    private JButton sec30;
    private JButton sec60;
    private JButton reset;

    private boolean clockRunning = false;

    int clock_x = 800;
    int clock_y = 900-5*900/6;
    int clock_width = 500;
    int clock_height = 900 - 2*900/5;

    public Activity(String exerciseName) throws IOException {

        codedName = String.join("-", exerciseName.toLowerCase().split(" "));
        System.out.println(codedName);
        timer.start();

        setLayout(new java.awt.BorderLayout());
        setBounds(0, 0, (int) Main.dim.getWidth(), (int) Main.dim.getHeight());

        this.getContentPane().setBackground(new Color(255, 243, 160));

        this.setTitle("EZFitness Pro");
        // setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("EZFitness/res/background.jpg")))));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Border border = BorderFactory.createLineBorder(Color.BLACK, 5);
        Border titleBorder = BorderFactory.createLineBorder(Color.DARK_GRAY, 2);

        ///////////////////////////////////////////////////////////////////////////////////
        appTitle.setForeground(Color.WHITE);
        appTitle.setBackground(Color.BLACK);
        appTitle.setFont(font);
        // appTitle.setSize(this.getWidth()-20,(int) (900/10));
        appTitle.setBorder(titleBorder);
        appTitle.setOpaque(true);
        add(appTitle, BorderLayout.PAGE_START);
        // appTitle.setVisible(true);
        ///////////////////////////////////////////////////////////////////////////////////

        ///////////////////////////////////////////////////////////////////////////////////
        JTextArea edit = new JTextArea(10, 100);
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(new File("res/descriptions/" + codedName + ".txt")));
            String str;
            while ((str = in.readLine()) != null) {
                edit.append("\n"+str);
            }
        } catch (IOException e) {
        } finally {
            try { in.close(); } catch (Exception ex) { }
        }
        // JScrollPane desc = new JScrollPane(edit);
        edit.setBounds(100,200,300,300);
        edit.setEditable(false);
        edit.setFont(edit.getFont().deriveFont(18f));
        edit.setBorder(border);
        getContentPane().add(edit);
        ///////////////////////////////////////////////////////////////////////////////////

        ///////////////////////////////////////////////////////////////////////////////////
        // Desktop.getDesktop().open(new File("the.mp4"));
        JLabel gif = new JLabel(new ImageIcon("res/bench-press.gif"));
        gif.setBounds(1440/5, 900-900/4, 1440 - 4*1440/5, 900 - 4*900/5); // migrate to layoutManager
        gif.setBorder(border);
        add(gif);
        ///////////////////////////////////////////////////////////////////////////////////

        ///////////////////////////////////////////////////////////////////////////////////
        JLabel group = new JLabel(new ImageIcon("res/muscleGroups/chest.jpg"));
        group.setBounds(20, 5*900/8 - 30, 1440 - 4*1440/5, 900 - 5*900/8); // migrate to layoutManager
        add(group);
        ///////////////////////////////////////////////////////////////////////////////////

        ///////////////////////////////////////////////////////////////////////////////////
        clockface = new JLabel(new ImageIcon("res/clockface.jpg"));
        // clockface.setBounds(clock_x, clock_y, clock_width, clock_height);
        clockface.addMouseListener(this);
        // clockface.setBorder(border);
        clockface.setOpaque(true);
        clockface.setBackground(Color.LIGHT_GRAY);
        add(clockface, BorderLayout.EAST);
        clockface.setBackground(new Color(255, 231, 20));
        ///////////////////////////////////////////////////////////////////////////////////


        ///////////////////////////////////////////////////////////////////////////////////
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 0));

        sec15 = new JButton("15 seconds");
        sec15.addActionListener(this);
        sec15.setBackground(Color.WHITE);
        sec15.setBorderPainted(false);
        buttonsPanel.add(sec15);

        sec30 = new JButton("30 seconds");
        sec30.addActionListener(this);
        sec30.setBackground(Color.WHITE);
        sec30.setBorderPainted(false);
        buttonsPanel.add(sec30);

        sec60 = new JButton("60 seconds");
        sec60.addActionListener(this);
        sec60.setBackground(Color.WHITE);
        sec60.setBorderPainted(false);
        buttonsPanel.add(sec60);

        time = new JLabel(String.format("%02d", extraTimer/60) + ":" + String.format("%02d", extraTimer%60));
        time.setFont(new Font("Courier New", Font.BOLD, 50));
        buttonsPanel.add(time);

        add(buttonsPanel, BorderLayout.PAGE_END);
        buttonsPanel.setBackground(Color.red);

        reset = new JButton("RESET");
        reset.addActionListener(this);
        reset.setBackground(Color.WHITE);
        reset.setBorderPainted(false);
        buttonsPanel.add(reset);

        ///////////////////////////////////////////////////////////////////////////////////

        setVisible(true);
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
        // setSize((int) Main.dim.getWidth(), (int) Main.dim.getHeight());
        // this.setLocation(Main.dim.width/2-this.getSize().width/2, Main.dim.height/2-this.getSize().height/2);
        repaint();

    }

    public void paint(Graphics g) {
        super.paint(g);  // fixes the immediate problem.
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(4));

        g.setColor(Color.RED);
        int secondHand_x = clockface.getX() + clock_width/2 + 10;
        int secondHand_y = clockface.getY() + clock_height/2 + 135;
        long later_x = (long) (secondHand_x + 200 * Math.sin(((System.currentTimeMillis() - start - restingTime)*Math.PI/30000)));
        long later_y = (long) (secondHand_y - 200 * Math.cos(((System.currentTimeMillis() - start - restingTime)*Math.PI/30000)));
        Line2D lin = new Line2D.Float(secondHand_x, secondHand_y, later_x, later_y);
        g2.draw(lin);

        g.setColor(Color.BLUE);
        int hourHand_x = clockface.getX() + clock_width/2 + 10;
        int hourHand_y = clockface.getY() + clock_height/2 + 135;
        long later_x2 = (long) (secondHand_x + 150 * Math.sin(((System.currentTimeMillis() - start - restingTime)*Math.PI/(30000*60))));
        long later_y2 = (long) (secondHand_y - 150 * Math.cos(((System.currentTimeMillis() - start - restingTime)*Math.PI/(30000*60))));
        Line2D lin2 = new Line2D.Float(hourHand_x, hourHand_y, later_x2, later_y2);
        g2.draw(lin2);

        System.out.println(restingTime);

        // System.out.println(Math.sin((System.currentTimeMillis() - start)*Math.PI/30000) + ": (" + later_x + "," + later_y + ")" + " --> " + (System.currentTimeMillis() - start)/1000);



//        Thread background = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i <= 50; i++) {
//                    try {
//                        Thread.sleep(1000);
//                    } catch(InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    System.out.println(i);
//                    // count.setText(Integer.toString(i));
//                }
//                // button.setEnabled(true); //click-able after the counter ends
//            }
//        });
//        background.start();

    }

    public void actionPerformed(ActionEvent ev) {
        if (ev.getSource() == timer && clockRunning) {
            if (extraTimer > 0)
                extraTimer--;
            time.setText(String.format("%02d", extraTimer/60) + ":" + String.format("%02d", extraTimer%60));
            repaint(); // this will call at every 1 second
        }
        if (ev.getSource() == sec15) {
            extraTimer += 15;
        }
        if (ev.getSource() == sec30) {
            extraTimer += 30;
        }
        if (ev.getSource() == sec60) {
            extraTimer += 60;
        }
        if (ev.getSource() == reset) {
            extraTimer = 0;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() == clockface) {
            // JOptionPane.showMessageDialog(null, "\"Stop hittin' me\" - Mr. Clock", "Info", JOptionPane.INFORMATION_MESSAGE);
            if (clockRunning) {
                temp = System.currentTimeMillis();
            } else {
                restingTime += System.currentTimeMillis() - temp;
            }
            clockRunning = !clockRunning;
        }
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
