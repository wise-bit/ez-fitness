
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.awt.Color;
import java.awt.Font;
import java.io.*;
import java.util.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame implements ActionListener {

    private JLabel appTitle = new JLabel("EZFitness Pro", SwingConstants.CENTER);
    private Font font = new Font("Sylfaen", Font.BOLD, 48); // Freestyle Script, Matura MT Script Capitals, French Script MT

    private JTextField username = new JTextField();
    private JPasswordField pw = new JPasswordField();
    private JButton login = new JButton("Login");
    private JButton newusers = new JButton("New User");
    public ArrayList<String> namelist = new ArrayList<String>();
    public ArrayList<String> pwlist = new ArrayList<String>();
    public ArrayList<String> heightlist = new ArrayList<String>();
    public ArrayList<String> weightlist = new ArrayList<String>();
    public ArrayList<String> agelist = new ArrayList<String>();
    public ArrayList<String> intensitylist = new ArrayList<String>();
    public JLabel userq=new JLabel("Username:");
    public JLabel passq=new JLabel("Password:");
    public int playerno;
    public JButton enter = new JButton("Enter");
    private String a;
    private JPanel information;

    public Login() throws IOException {

        setLayout(new BorderLayout());
        this.getContentPane().setBackground(new Color(255, 243, 160));
        setBounds(0, 0, 1000, 800);
        this.setTitle("EZFitness Pro");
        // setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("EZFitness/res/background.jpg")))));
        this.setBackground(Color.WHITE);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        appTitle.setForeground(Color.WHITE);
        appTitle.setFont(font);
        appTitle.setPreferredSize(new Dimension((int) (Main.dim.getWidth()*1.0/4), (int) (Main.dim.getWidth()*1.0/20)));
        add(appTitle, BorderLayout.PAGE_START);
        appTitle.setBorder( new MatteBorder(0, 0, 5, 0, Color.black));
        appTitle.setOpaque(true);
        appTitle.setBackground(new Color(225, 185, 0));
        appTitle.setVisible(true);

        ImageIcon link = new ImageIcon("res/mainScreenImage.JPG");

        JLabel image = new JLabel(link);
        // image.setSize((int) (Main.dim.getWidth()*3.0/8), image.getSize().height);
        image.setMaximumSize(new Dimension((int) (Main.dim.getWidth()*3.0/8), image.getSize().height));
        this.getContentPane().add(image, BorderLayout.LINE_START);

        information = new JPanel();
        information.setLayout(new BoxLayout(information, BoxLayout.PAGE_AXIS));
        information.setBackground(new Color(255, 243, 160));

        JPanel placeHolder = new JPanel();
        placeHolder.setBackground(new Color(255, 243, 160));
        placeHolder.setPreferredSize(new Dimension(placeHolder.getWidth(),this.getHeight()/3));
        placeHolder.setMaximumSize(new Dimension(placeHolder.getWidth(),this.getHeight()/3));
        information.add(placeHolder);

        Border border = BorderFactory.createLineBorder(information.getBackground(), 20);

        userq.setBounds(950, 250, 200, 60);
        userq.setAlignmentX(Component.CENTER_ALIGNMENT);
        information.add(userq);

        username.setPreferredSize(new Dimension(220, 50));
        username.setMinimumSize(new Dimension(220, 50));
        username.setMaximumSize(new Dimension(220, 50));
        username.setFont(font);
        username.setAlignmentX(Component.CENTER_ALIGNMENT);
        username.setMaximumSize( username.getPreferredSize() );
        information.add(username);

        passq.setBounds(950, 360, 200, 60);
        passq.setAlignmentX(Component.CENTER_ALIGNMENT);
        information.add(passq);

        pw.setPreferredSize(new Dimension(220, 50));
        pw.setMinimumSize(new Dimension(220, 50));
        pw.setMaximumSize(new Dimension(220, 50));
        pw.setAlignmentX(Component.CENTER_ALIGNMENT);
        pw.setMaximumSize( pw.getPreferredSize() );
        pw.setFont(font);
        information.add(pw);

//        login.setBounds(950, 500, 100, 50);

        JPanel buttons = new JPanel();
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.LINE_AXIS));

        information.add(Box.createRigidArea(new Dimension(0,20)));

        login.setPreferredSize(new Dimension(100, 50));
        login.setMinimumSize(new Dimension(100, 50));
        login.setMaximumSize(new Dimension(100, 50));
        login.setAlignmentX(Component.CENTER_ALIGNMENT);
        login.setBackground(Color.WHITE);
        login.setBorderPainted(false);
        buttons.add(login);
        login.addActionListener(this);

        buttons.add(Box.createRigidArea(new Dimension(20,10)));

        newusers.setPreferredSize(new Dimension(100, 50));
        newusers.setMinimumSize(new Dimension(100, 50));
        newusers.setMaximumSize(new Dimension(100, 50));
        newusers.setAlignmentX(Component.CENTER_ALIGNMENT);
        newusers.setBackground(Color.WHITE);
        newusers.setBorderPainted(false);
        buttons.add(newusers);
        newusers.addActionListener(this);

        information.add(buttons);
        information.add(Box.createRigidArea(new Dimension(0,20)));

        enter.setAlignmentX(Component.CENTER_ALIGNMENT);
        enter.setPreferredSize(new Dimension(220, 50));
        enter.setMinimumSize(new Dimension(220, 50));
        enter.setMaximumSize(new Dimension(220, 50));
        enter.setBackground(Color.WHITE);
        enter.setBorderPainted(false);
        enter.addActionListener(this);

        add(information, BorderLayout.CENTER);


        setVisible(true);
        // setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
        setSize(1440, 900);
        this.setLocation(Main.dim.width / 2 - this.getSize().width / 2, Main.dim.height / 2 - this.getSize().height / 2);
        repaint();

        Scanner data = null;
        try {
            data = new Scanner(new File("res/database/userInfo.csv"));
            data.useDelimiter(",");
            while (data.hasNext()) {
                a=data.next().replace("\n","");
                a=a.replace("\r","");
                namelist.add(a);
                pwlist.add(data.next());
                heightlist.add(data.next());
                weightlist.add(data.next());
                agelist.add(data.next());
                intensitylist.add(data.next());


            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(namelist);
        System.out.println(pwlist);
        System.out.println(heightlist);
        System.out.println(weightlist);
        System.out.println(agelist);
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        if (arg0.getSource() == login) {
            for (int i=0;i<namelist.size();i++) {
                if (username.getText().equals(namelist.get(i))) {
                    if (String.valueOf(pw.getPassword()).equals(pwlist.get(i))) {
                        pw.setBackground(Color.GREEN);
                        username.setBackground(Color.GREEN);
                        playerno=i;

                        Main.currentUser.setUsername(username.getText());
                        fetchUserInformation();

                        try { Main.currentUser.promote(); } catch (IOException e) { e.printStackTrace(); }

                        information.add(enter);
                        validate();
                        repaint();

                        break;
                    } else {
                        pw.setBackground(Color.RED);
                        username.setBackground(Color.RED);
                    }
                } else {
                    username.setBackground(Color.RED);
                    pw.setBackground(Color.RED);
                }
            }
        }
        if (arg0.getSource() == enter) {
            Main.info = new InfoScreen2();
            Main.info.setVisible(true);
            this.dispose();
            // dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        }
        if (arg0.getSource() == newusers){
            NewUser page=new NewUser(namelist);
            page.setVisible(true);
            // this.dispose();
        }

    }

    public static void fetchUserInformation() {
        File file = new File("res/database/userInfo.csv");
        try {
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String[] line = reader.nextLine().split(",");
                if (line[0].equals(Main.currentUser.getUsername())) {
                    Main.currentUser.setPassword(line[1]);
                    Main.currentUser.setHeight(line[2]);
                    Main.currentUser.setWeight(line[3]);
                    Main.currentUser.setAge(line[4]);
                    Main.currentUser.setLevel(line[5]);
                }
            }
        } catch (FileNotFoundException e) {
            // e.printStackTrace();
            System.out.println("Userinfo file not found!");
        }
    }

}