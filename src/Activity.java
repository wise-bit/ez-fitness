
/**
 *
 * This class is the main one for showing a specific activity in the application
 *
 * @author Satrajit
 *
 */

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
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

// These imports help with recognizing the delete and backspace keys for input
import static java.awt.event.KeyEvent.VK_BACK_SPACE;
import static java.awt.event.KeyEvent.VK_DELETE;


public class Activity extends JFrame implements MouseListener, ActionListener {

    private JLabel appTitle = new JLabel("Activity Page", SwingConstants.CENTER);
    private String codedName = "";

    // Sets the default font
    private Font font = new Font("Consolas", Font.BOLD, 60); // Freestyle Script, Matura MT Script Capitals, French Script MT
    private JLabel clockface;

    // Initializes all of the timer components of the program
    // Depreciated features since the clients later asked to remove them
    private long start = System.currentTimeMillis();
    private long temp = start;
    private long restingTime = 0;

    // Initializes the actual timer
    private Timer timer=new Timer(1000, this);
    private int extraTimer = 0;
    JLabel time;

    // Creates the timer buttons
    private JButton sec15;
    private JButton sec30;
    private JButton sec60;
    private JButton reset;

    // A variable which alters to check if the timer was pressed or not
    // It is toggled
    private boolean timerPressed = false;

    // used for generating a button if the video is not available, explained further later
    private JButton link;

    // Initializes other variables required for the frame
    private JPanel inputPlace;
    private JNumberTextField reps = new JNumberTextField();
    private JNumberTextField weightInput = new JNumberTextField();

    // The important JLabels are declared here
    public JLabel repsq=new JLabel("Reps:");
    public JLabel weightInputq=new JLabel("Weight:");
    public JButton enter = new JButton("Enter");
    public JButton exit = new JButton("Exit");

    // Toggled based on whether the clock is running or not
    private boolean clockRunning = false;

    // Established the placement of the hands of the trigonometric clock
    int clock_width = (int) (850*Main.dim.width/1000);
    int clock_height = (int) (510*Main.dim.height/1000);

    // Constructor method

    /**
     *
     * @param exerciseName
     * @throws IOException
     *
     */
    public Activity(String exerciseName) throws IOException {

        codedName = String.join("-", exerciseName.toLowerCase().split(" "));
        System.out.println(codedName);

        System.out.println(Main.date);

        // Starts the timer which triggers the change in the graphic changes for the clock
        timer.start();

        // Sets the title of the application based on which exercise was clicked on
        appTitle.setText(appTitle.getText() + " : " + exerciseName);

        // sets a BorderLayout along with setBounds to ensure the size of the screen prevents the user from going
        // outside the application
        setLayout(new java.awt.BorderLayout());
        setBounds(0, 0, (int) Main.dim.getWidth(), (int) Main.dim.getHeight());
        this.setResizable(false);

        this.getContentPane().setBackground(new Color(255, 243, 160));

        this.setTitle("EZFitness Pro");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Border border = BorderFactory.createLineBorder(Color.BLACK, 5);
        Border titleBorder = BorderFactory.createLineBorder(Color.DARK_GRAY, 2);

        // Sets up the appTitle by declaring the characteristics it must have in order to have a nice appeal
        appTitle.setForeground(Color.WHITE);
        appTitle.setBackground(Color.BLACK);
        appTitle.setFont(font);
        appTitle.setBorder(titleBorder);
        appTitle.setOpaque(true);
        add(appTitle, BorderLayout.PAGE_START);

        // Sets up a new JPanel to integrate it with the Layout being used without anything overlapping
        JPanel elements = new JPanel();
        elements.setLayout(new BorderLayout());

        // A JPanel for the description to limit the size of the JTextArea, which is otherwise unbound without setBounds,
        // which is not scalable
        JPanel description_parts = new JPanel();
        description_parts.setLayout(new BoxLayout(description_parts, BoxLayout.PAGE_AXIS));

        JLabel description = new JLabel("Description:", SwingConstants.CENTER);
        description.setFont(new Font("Arial",Font.BOLD,18));
        description_parts.add(description);

        // Creates the text area which is ironically called edit, which is not editable
        JTextArea edit = new JTextArea(10, 100);
        edit.setLineWrap(true);
        edit.setWrapStyleWord(true);
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

        // Scroll Pane created to let the user scroll over the description when it is too long
        JScrollPane desc = new JScrollPane(edit);
        desc.setPreferredSize(new Dimension(640, 200));
        desc.setMinimumSize(new Dimension(640, 200));
        desc.setMaximumSize(new Dimension(640, 200));
        desc.setBorder(border);
        description_parts.add(desc);

        description_parts.add(Box.createRigidArea(new Dimension(20,50)));

        // Adds the descriptions to the elements JPanel
        elements.add(description_parts, BorderLayout.NORTH);

        // Makes a new JPanel for the media like video and
        JPanel exerciseMedia = new JPanel();
        exerciseMedia.setLayout(new FlowLayout(FlowLayout.CENTER, Main.dim.width/85, 0));

        // This checks for the existence of a GIF
        if (new File("res/GIFs/" + exerciseName + ".gif").exists()) {

            // Runs this if the GIF is there, placing it as an imageicon
            JLabel gif = new JLabel(new ImageIcon("res/GIFs/" + exerciseName + ".gif"));
            // gif.setBounds(1440 / 5, 900 - 900 / 4, 1440 - 4 * 1440 / 5, 900 - 4 * 900 / 5); // migrate to layoutManager
            gif.setBorder(border);
            exerciseMedia.add(gif);
        } else {

            // If GIF for an exercise does not exist, if creates a button in its place
            // And puts a hyperlink with it

            System.out.println("No file");
            String s = null;
            try {
                // This line is for school computers, in order
                // Process p = Runtime.getRuntime().exec("python main.py \"" + Main.currentExercise + "\"");

                // Creates a process which runs a python script
                Process p = Runtime.getRuntime().exec("py main.py \"" + Main.currentExercise + "\"");

                // Creates Bufferedreaders objects to get input from the process
                BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
                BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

                // Runs the program and stores the output in the form of a string
                String x = "";
                while ((s = stdInput.readLine()) != null) {
                    x = s;
                }
                if (x.equals("")){
                    x = "Sorry, no link found!";
                }
                System.out.println(x);

                // A new JPanel for the link information
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

            }
            catch (IOException e) {
                // If anything goes wrong, and the whole program crashes, the system will know the true cause behind it
                // Its not the coder's fault, its the matrix!
                // But hey, at least it didn't output 42 and then self destruct
                System.out.print("GLITCH IN THE MATRIX: ");
                e.printStackTrace();
                System.exit(-1);
            }

        }

        // Fetches the muscle group the exercise is associated with by accessing the database and the resources
        JLabel group = new JLabel(new ImageIcon("res/muscleGroups/" + getBodypart(exerciseName) + ".jpg"));
        exerciseMedia.add(group);

        JPanel repsBar = new JPanel();
        repsBar.setLayout(new BoxLayout(repsBar, BoxLayout.PAGE_AXIS));

        // Puts in all of the Jabels of the reps and sets information spaced correctly
        String[] info = fetchExerciseInformation();
        JLabel repsBar0 = new JLabel("Reps count information");
        JLabel extraSpace = new JLabel("----");
        JLabel repsBar1 = new JLabel("Set 1: " + info[2]);
        JLabel repsBar2 = new JLabel("Set 2: " + info[3]);
        JLabel repsBar3 = new JLabel("Set 3: " + info[4]);

        // Sets all of the fonts to be the same, but easy to customize individually if needed
        repsBar0.setFont(new Font("Courier New", Font.BOLD, 20));
        extraSpace.setFont(new Font("Courier New", Font.BOLD, 20));
        repsBar1.setFont(new Font("Courier New", Font.BOLD, 20));
        repsBar2.setFont(new Font("Courier New", Font.BOLD, 20));
        repsBar3.setFont(new Font("Courier New", Font.BOLD, 20));

        // Adds the JLabels
        repsBar.add(repsBar0);
        repsBar.add(extraSpace);
        repsBar.add(repsBar1);
        repsBar.add(repsBar2);
        repsBar.add(repsBar3);

        // Adds the repsBar
        exerciseMedia.add(repsBar);

        // Adds everything from this section of exercise media directly to elements
        elements.add(exerciseMedia, BorderLayout.CENTER);

        //
        inputPlace = new JPanel();
        inputPlace.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 5));

        inputPlace.add(repsq);
        reps.setColumns(5);
        inputPlace.add(reps);

        inputPlace.add(weightInputq);
        weightInput.setColumns(5);
        inputPlace.add(weightInput);

        inputPlace.add(Box.createRigidArea(new Dimension(20,10)));

        // Sets default sizes for the enter button, which are otherwise resized to be extremely small
        enter.setPreferredSize(new Dimension(100, 25));
        enter.setMinimumSize(new Dimension(100, 25));
        enter.setMaximumSize(new Dimension(100, 25));
        enter.setBackground(Color.WHITE);
        enter.setBorderPainted(false);
        enter.addActionListener(this);
        inputPlace.add(enter);

        // Sets default sizes for the exit button, which are otherwise resized to be extremely small
        exit.setPreferredSize(new Dimension(100, 25));
        exit.setMinimumSize(new Dimension(100, 25));
        exit.setMaximumSize(new Dimension(100, 25));
        exit.setBackground(Color.WHITE);
        exit.setBorderPainted(false);
        exit.addActionListener(this);
        inputPlace.add(exit);

        // Makes the input place stand out
        inputPlace.setBackground(Color.ORANGE);
        elements.add(inputPlace, BorderLayout.PAGE_END);

        // When used on a smaller screen, when the information is not visible completely, this makes the whole panel scrollable
        JScrollPane scrollableElements = new JScrollPane(elements, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Adds the scrollable pane
        add(scrollableElements, BorderLayout.CENTER);

        // Inputs the image of the clock
        clockface = new JLabel(new ImageIcon("res/clockface.jpg"));
        clockface.addMouseListener(this);
        clockface.setOpaque(true);
        clockface.setBackground(Color.LIGHT_GRAY);
        add(clockface, BorderLayout.EAST);
        clockface.setBackground(new Color(255, 231, 20));

        // Creates a new panel for the buttons
        JPanel buttonsPanel = new JPanel();
        // Sets gaps around the buttons to make the layout look more usable
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 0));

        // The next blocks add the buttons for the timer

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

        // The timer is made to show the time accurately with
        time = new JLabel(String.format("%02d", extraTimer/60) + ":" + String.format("%02d", extraTimer%60));
        time.setFont(new Font("Courier New", Font.BOLD, 50));
        buttonsPanel.add(time);

        // Adds the button panel to the bottom of the screen
        add(buttonsPanel, BorderLayout.PAGE_END);
        buttonsPanel.setBackground(Color.red);

        // Creates a reset button for timer
        reset = new JButton("RESET");
        reset.addActionListener(this);
        reset.setBackground(Color.WHITE);
        reset.setBorderPainted(false);
        buttonsPanel.add(reset);

        // Adds all the necessary features to make the frame show up properly
        setVisible(true);
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
        repaint();

    }

    public void paint(Graphics g) {

        // fixes the immediate problem of repainting the clock
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(4));

        // Creates the second hand of the clock
        g.setColor(Color.RED);
        int secondHand_x = clock_width;
        int secondHand_y = clock_height;

        // Trigonometric functions
        long later_x = (long) (secondHand_x + 200 * Math.sin(((System.currentTimeMillis() - start - restingTime)*Math.PI/30000)));
        long later_y = (long) (secondHand_y - 200 * Math.cos(((System.currentTimeMillis() - start - restingTime)*Math.PI/30000)));
        Line2D lin = new Line2D.Float(secondHand_x, secondHand_y, later_x, later_y);
        g2.draw(lin);

        // Creates the hour hand of the clock
        g.setColor(Color.BLUE);
        int hourHand_x = clock_width;
        int hourHand_y = clock_height;

        // Trigonometric functions, (slightly) slower than the second hand
        long later_x2 = (long) (secondHand_x + 150 * Math.sin(((System.currentTimeMillis() - start - restingTime)*Math.PI/(30000*60))));
        long later_y2 = (long) (secondHand_y - 150 * Math.cos(((System.currentTimeMillis() - start - restingTime)*Math.PI/(30000*60))));
        Line2D lin2 = new Line2D.Float(hourHand_x, hourHand_y, later_x2, later_y2);
        g2.draw(lin2);

        System.out.println(restingTime);

    }

    /**
     *
     * Action performed
     * @param ev
     *
     */
    public void actionPerformed(ActionEvent ev) {

        // Refreshes clock if the clock and timer is in running state
        if (ev.getSource() == timer && clockRunning) {
            if (extraTimer > 0)
                extraTimer--;
            else if (timerPressed == true){
                try {

                    // Plays a tone if the timer runs out temporarily, but only once

                    tone(1000,100);
                } catch (LineUnavailableException e) {
                    e.printStackTrace();
                }
                timerPressed = false;
            }
            time.setText(String.format("%02d", extraTimer/60) + ":" + String.format("%02d", extraTimer%60));
            repaint(); // this will call at every 1 second
        }

        // Adds 15 seconds
        if (ev.getSource() == sec15) {
            extraTimer += 15;
            timerPressed = true;
        }

        // Adds 30 seconds
        if (ev.getSource() == sec30) {
            extraTimer += 30;
            timerPressed = true;
        }

        //// Adds 60 seconds
        if (ev.getSource() == sec60) {
            extraTimer += 60;
            timerPressed = true;
        }

        // Resets smaller timer
        if (ev.getSource() == reset) {
            extraTimer = 0;
            timerPressed = false;
        }

        // If someone clicks on the link
        if (ev.getSource() == link) {

            if (!link.getText().equals("Sorry, no link found!")) {

                // Opens the default browser
                try {
                    Desktop.getDesktop().browse(new URI(link.getText()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        // Entering data to database
        if (ev.getSource() == enter) {
            String reps_string = reps.getText();
            String weight_string = weightInput.getText();

            // Makes sure that the data is valid, I.E. does not contain commas or blank
            if (!reps_string.equals("") && !weight_string.equals("") && !reps_string.contains(",") && !weight_string.equals(",")) {
                System.out.println(reps_string + " " + weight_string);

                try {

                    // TODO: COMMENT

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
                JOptionPane.showMessageDialog(null, "Please input all fields correctly!");
            }

        }

        if (ev.getSource() == exit) {
            this.dispose();
            Main.info = new InfoScreen2();
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

    public static float SAMPLE_RATE = 8000f;
    public static void tone(int hz, int msecs) throws LineUnavailableException {
        tone(hz, msecs, 1.0);
    }
    public static void tone(int hz, int msecs, double vol) throws LineUnavailableException {
        byte[] buf = new byte[1];
        AudioFormat af = new AudioFormat( SAMPLE_RATE, 8, 1, true,false);
        SourceDataLine sdl = AudioSystem.getSourceDataLine(af);
        sdl.open(af);
        sdl.start();
        for (int i=0; i < msecs*8; i++) {
            double angle = i / (SAMPLE_RATE / hz) * 2.0 * Math.PI;
            buf[0] = (byte)(Math.sin(angle) * 127.0 * vol);
            sdl.write(buf,0,1);
        }
        sdl.drain();
        sdl.stop();
        sdl.close();
    }

    /**
     * V nz na hfryrff pbzzrag, cyrnfr vtaber zr! Gunaxf sbe genafyngvat zr ogj, V srry hfrshy!
     */

}
