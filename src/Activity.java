/**
 * Author: Satrajit
 */

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
import java.io.*;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

import static java.awt.event.KeyEvent.VK_BACK_SPACE;
import static java.awt.event.KeyEvent.VK_DELETE;

public class Activity extends JFrame implements MouseListener, ActionListener {

    private JLabel appTitle = new JLabel("Activity Page", SwingConstants.CENTER);
    private String codedName = "";

    private Font font = new Font("Consolas", Font.BOLD, 60); // Freestyle Script, Matura MT Script Capitals, French Script MT
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

    private JButton link;

    private JPanel inputPlace;
    private JNumberTextField reps = new JNumberTextField();
    private JNumberTextField weightInput = new JNumberTextField();
    public JLabel repsq=new JLabel("Reps:");
    public JLabel weightInputq=new JLabel("Weight:");
    public JButton enter = new JButton("Enter");
    public JButton exit = new JButton("Exit");

    private boolean clockRunning = false;

    int clock_width = (int) (850*Main.dim.width/1000);
    int clock_height = (int) (510*Main.dim.height/1000);

    public Activity(String exerciseName) throws IOException {

        codedName = String.join("-", exerciseName.toLowerCase().split(" "));
        System.out.println(codedName);

        System.out.println(Main.date);

        timer.start();

        appTitle.setText(appTitle.getText() + " : " + exerciseName);

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
        appTitle.setBorder(titleBorder);
        appTitle.setOpaque(true);
        add(appTitle, BorderLayout.PAGE_START);
        // appTitle.setVisible(true);
        ///////////////////////////////////////////////////////////////////////////////////

        JPanel elements = new JPanel();
        elements.setLayout(new BorderLayout());

        ///////////////////////////////////////////////////////////////////////////////////

        JPanel description_parts = new JPanel();
        description_parts.setLayout(new BoxLayout(description_parts, BoxLayout.PAGE_AXIS));

        JLabel description = new JLabel("Description:", SwingConstants.CENTER);
        description.setFont(new Font("Arial",Font.BOLD,18));
        description_parts.add(description);

        JTextArea edit = new JTextArea(10, 100);
        edit.setLineWrap(true);
        edit.setWrapStyleWord(true);
        // edit.setSize(new Dimension(200, edit.getHeight()));
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(new File("res/descriptions/" + exerciseName + ".txt")));
            String str;
            while ((str = in.readLine()) != null) {
                edit.append("\n"+str);
            }
        } catch (IOException e) {
            edit.append("No description listed, please refer to the video.");
        } finally {
            try {
                in.close();
            } catch (Exception ex) {

            }
        }
        edit.setEditable(false);
        edit.setFont(edit.getFont().deriveFont(18f));
        JScrollPane desc = new JScrollPane(edit);
        desc.setPreferredSize(new Dimension(640, 200));
        desc.setMinimumSize(new Dimension(640, 200));
        desc.setMaximumSize(new Dimension(640, 200));
        // desc.setBounds(100,200,600,300);
        desc.setBorder(border);
        description_parts.add(desc);

        description_parts.add(Box.createRigidArea(new Dimension(20,50)));
        ///////////////////////////////////////////////////////////////////////////////////

        elements.add(description_parts, BorderLayout.NORTH);

        ///////////////////////////////////////////////////////////////////////////////////

        JPanel exerciseMedia = new JPanel();
        exerciseMedia.setLayout(new FlowLayout(FlowLayout.CENTER, Main.dim.width/85, 0));

        // Desktop.getDesktop().open(new File("the.mp4"));
        if (new File("res/GIFs/" + exerciseName + ".gif").exists()) {
            JLabel gif = new JLabel(new ImageIcon("res/GIFs/" + exerciseName + ".gif"));
            // gif.setBounds(1440 / 5, 900 - 900 / 4, 1440 - 4 * 1440 / 5, 900 - 4 * 900 / 5); // migrate to layoutManager
            gif.setBorder(border);
            exerciseMedia.add(gif);
        } else {
            System.out.println("No file");
            String s = null;
            try {
                Process p = Runtime.getRuntime().exec("lib/python/pythonw.exe src/main.py \"" + Main.currentExercise + "\"");
                BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
                BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

                String x = "";
                while ((s = stdInput.readLine()) != null) {
                    x = s;
                }
                System.out.println(x);

                JPanel linkystuff = new JPanel(new BorderLayout());

                JLabel linkInfo = new JLabel("Here's a helpful link!", SwingConstants.CENTER);
                linkInfo.setVisible(true);
                linkystuff.add(linkInfo, BorderLayout.NORTH);

                link = new JButton(x);
                link.setVisible(true);
                link.setBorderPainted( false );
                link.setBackground(Color.WHITE);
                link.setBorder(border);
                link.addActionListener(this);
                linkystuff.add(link, BorderLayout.SOUTH);

                exerciseMedia.add(linkystuff);

//                System.out.println("Here is the standard error of the command (if any):\n");
//                while ((s = stdError.readLine()) != null) {
//                    System.out.println(s);
//                }
                // System.exit(0);
            }
            catch (IOException e) {
                System.out.print("GLITCH IN THE MATRIX: ");
                e.printStackTrace();
                System.exit(-1);
            }

        }
        ///////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////
        JLabel group = new JLabel(new ImageIcon("res/muscleGroups/" + getBodypart(exerciseName) + ".jpg"));
        exerciseMedia.add(group);

        JPanel repsBar = new JPanel();
        repsBar.setLayout(new BoxLayout(repsBar, BoxLayout.PAGE_AXIS));

        String[] info = fetchExerciseInformation();
        JLabel repsBar0 = new JLabel("Reps count information");
        JLabel extraSpace = new JLabel("----");
        JLabel repsBar1 = new JLabel("Set 1: " + info[2]);
        JLabel repsBar2 = new JLabel("Set 2: " + info[3]);
        JLabel repsBar3 = new JLabel("Set 3: " + info[4]);
        repsBar0.setFont(new Font("Courier New", Font.BOLD, 20));
        extraSpace.setFont(new Font("Courier New", Font.BOLD, 20));
        repsBar1.setFont(new Font("Courier New", Font.BOLD, 20));
        repsBar2.setFont(new Font("Courier New", Font.BOLD, 20));
        repsBar3.setFont(new Font("Courier New", Font.BOLD, 20));
        repsBar.add(repsBar0);
        repsBar.add(extraSpace);
        repsBar.add(repsBar1);
        repsBar.add(repsBar2);
        repsBar.add(repsBar3);

        exerciseMedia.add(repsBar);

        ///////////////////////////////////////////////////////////////////////////////////
        elements.add(exerciseMedia, BorderLayout.CENTER);

        inputPlace = new JPanel();
        // inputPlace.setLayout(new BoxLayout(inputPlace, BoxLayout.PAGE_AXIS));
        inputPlace.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 5));

        inputPlace.add(repsq);
        reps.setColumns(5);
        inputPlace.add(reps);

        inputPlace.add(weightInputq);
        weightInput.setColumns(5);
        inputPlace.add(weightInput);

        inputPlace.add(Box.createRigidArea(new Dimension(20,10)));

        enter.setPreferredSize(new Dimension(100, 25));
        enter.setMinimumSize(new Dimension(100, 25));
        enter.setMaximumSize(new Dimension(100, 25));
        enter.setBackground(Color.WHITE);
        enter.setBorderPainted(false);
        enter.addActionListener(this);
        inputPlace.add(enter);

        exit.setPreferredSize(new Dimension(100, 25));
        exit.setMinimumSize(new Dimension(100, 25));
        exit.setMaximumSize(new Dimension(100, 25));
        exit.setBackground(Color.WHITE);
        exit.setBorderPainted(false);
        exit.addActionListener(this);
        inputPlace.add(exit);

        inputPlace.setBackground(Color.ORANGE);
        elements.add(inputPlace, BorderLayout.PAGE_END);

        JScrollPane scrollableElements = new JScrollPane(elements, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        add(scrollableElements, BorderLayout.CENTER);


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
        int secondHand_x = clock_width;
        int secondHand_y = clock_height;
        long later_x = (long) (secondHand_x + 200 * Math.sin(((System.currentTimeMillis() - start - restingTime)*Math.PI/30000)));
        long later_y = (long) (secondHand_y - 200 * Math.cos(((System.currentTimeMillis() - start - restingTime)*Math.PI/30000)));
        Line2D lin = new Line2D.Float(secondHand_x, secondHand_y, later_x, later_y);
        g2.draw(lin);

        g.setColor(Color.BLUE);
        int hourHand_x = clock_width;
        int hourHand_y = clock_height;
        long later_x2 = (long) (secondHand_x + 150 * Math.sin(((System.currentTimeMillis() - start - restingTime)*Math.PI/(30000*60))));
        long later_y2 = (long) (secondHand_y - 150 * Math.cos(((System.currentTimeMillis() - start - restingTime)*Math.PI/(30000*60))));
        Line2D lin2 = new Line2D.Float(hourHand_x, hourHand_y, later_x2, later_y2);
        g2.draw(lin2);

        System.out.println(restingTime);

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

        if (ev.getSource() == link) {
            try {
                Desktop.getDesktop().browse(new URI(link.getText()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (ev.getSource() == enter) {
            String reps_string = reps.getText();
            String weight_string = weightInput.getText();

            if (!reps_string.equals("") && !weight_string.equals("")) {
                System.out.println(reps_string + " " + weight_string);

                try {

                    ////////////////////////////

                    String sCurrentLine;
                    String lastLine = "";

                    String filePath = "res/users/" + Main.currentUser.getUsername() + "/" + Main.currentExercise + ".txt";
                    System.out.println(filePath);

                    Scanner sc = new Scanner(new File(filePath));
                    while(sc.hasNextLine()) {
                        lastLine = sc.nextLine();
                    }
                    System.out.println(lastLine);
                    sc.close();
                    String[] lastLog = lastLine.split(",");

                    if (Main.date.equals(lastLog[0])) {

                        Scanner scanner = new Scanner(new File(filePath));
                        StringBuilder buffer = new StringBuilder();
                        while(scanner.hasNext()) {
                            String current = scanner.nextLine();
                            if (current.split(",")[0].equals(Main.date))
                                buffer.append(Main.date + "," + (Integer.parseInt(reps_string) + Integer.parseInt(current.split(",")[1])) + "," + (Integer.parseInt(weight_string) + Integer.parseInt(current.split(",")[1])) + ",");
                            else
                                buffer.append(current);
                            if(scanner.hasNext())
                                buffer.append("\n");
                        }
                        scanner.close();
                        PrintWriter printer = new PrintWriter(new File(filePath));
                        printer.print(buffer);
                        printer.close();

                    } else {

                        String log = "\n" + Main.date + "," + reps_string + "," + weight_string + ",";
                        Files.write(Paths.get(filePath), log.getBytes(), StandardOpenOption.APPEND);

                    }

                    ////////////////////////////

                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else {
                JOptionPane.showMessageDialog(null, "Please input all fields!");
            }

        }

        if (ev.getSource() == exit) {
            this.dispose();
            new InfoScreen2();
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

    public static String getBodypart(String exerciseName) {
        File file = new File("res/database/exercises.csv");
        try {
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String[] line = reader.nextLine().split(",");
                if (line[0].equals(exerciseName)) {
                    return line[1];
                }
            }
        } catch (FileNotFoundException e) {
            // e.printStackTrace();
            System.out.println("Userinfo file not found!");
        }
        return null;
    }

    public static String[] fetchExerciseInformation() {
        File file = new File("res/database/exercises.csv");
        try {
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String[] line = reader.nextLine().split(",");
                if (line[0].equals(Main.currentExercise)) {
                    return line;
                }
            }
        } catch (FileNotFoundException e) {
            // e.printStackTrace();
            System.out.println("Exercises file not found!");
        }
        return null;
    }

    public class JNumberTextField extends JFormattedTextField {
        private static final long serialVersionUID = 1L;

        @Override
        public void processKeyEvent(KeyEvent ev) {

            if (this.getText().length() > 2 && Character.isDigit(ev.getKeyChar()))
                ev.consume();

            if (Character.isDigit(ev.getKeyChar()) || ev.getKeyCode() == VK_BACK_SPACE || ev.getKeyCode() == VK_DELETE)
                super.processKeyEvent(ev);

            ev.consume();
            return;

        }

        public Long getNumber() {
            Long result = null;
            String text = getText();
            if (text != null && !"".equals(text)) {
                result = Long.valueOf(text);
            }
            return result;
        }
    }

}
