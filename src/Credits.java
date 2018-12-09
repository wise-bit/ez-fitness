import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
import java.io.IOException;
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

public class Credits extends JFrame implements ActionListener {

    public static Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

    private JPanel panel = new JPanel();
    private JPanel panel2 = new JPanel();
    private JPanel buttons = new JPanel();

    private JLabel body = new JLabel();

    private JLabel creditsTitle = new JLabel("Thanks to all Project Contributers");

    private String[] creds = new String[18];

    private JList<String> allText = new JList<String>();


    private JButton exit = new JButton("EXIT");

    public static int width = (int) dim.getWidth();

    public static int height = (int) dim.getHeight();

    public static int width1 = (int) dim.getWidth();

    private int x = 0;

    private int y = 100;

    Border border = BorderFactory.createLineBorder(Color.BLACK, 5);
    Border borderchoose= BorderFactory.createLineBorder(Color.RED, 5);

    public Credits() {

        setBackground(new Color(57, 173, 189));
        setSize(width1, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        panel.setBounds(0, 0, width - (width - height), height - (height / 18));
        panel.setBackground(Color.WHITE);
        panel.setLayout(null);
        panel.setBorder(border);
        add(panel);

        panel2.setBounds(width - (width - height), 0, width - height,height - (height / 18));
        panel2.setBackground(Color.WHITE);
        panel2.setLayout(new BorderLayout());
        panel2.setBorder(border);
        add(panel2);

        body.setIcon(new ImageIcon(new ImageIcon("res/GIFs/LMAO1.gif").getImage().getScaledInstance(height/3, height/2, 0)));
        // body.setLayout(new BorderLayout());
        // body.setVisible(true);
        body.setBounds((width - (width - height) + (height / 5) - (height / 6)) / 4, (height - (height / 18)) / 4, height/3, height/2);
        //panel.add(body, BorderLayout.CENTER);
        panel.add(body);
        // panel.revalidate();
        // panel.repaint();

        //input creds

        creds[0] = " ";
        creds[1] = "Project Manager: Satrajit C.";
        creds[2] = " ";
        creds[3] = "Coder: Daniel Z.";
        creds[4] = " ";
        creds[5] = "Coder: Tom S.";
        creds[6] = "--------------------";
        creds[7] = "Client Communications Head: Jason C.";
        creds[8] = " ";
        creds[9] = "Client: Hesigan";
        creds[10] = " ";
        creds[11] = "Client: Chris W.";
        creds[12] = " ";
        creds[13] = "Client: Nirrosan";


        allText = new JList<String>(creds);

        allText.setFont(new Font("Arial", Font.BOLD, 35));
        allText.setVisible(true);

        creditsTitle.setFont(new Font("Arial", Font.BOLD, 35));
        creditsTitle.setVisible(true);

        panel2.add(creditsTitle, BorderLayout.NORTH);
        panel2.add(allText, BorderLayout.CENTER);

        buttons.setBackground(Color.WHITE);
        buttons.setLayout(new BorderLayout());

        exit.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 26));
        exit.setPreferredSize(new Dimension(220, 50));
        exit.setMinimumSize(new Dimension(220, 50));
        exit.setMaximumSize(new Dimension(220, 50));
        exit.setBackground(Color.RED);
        exit.setForeground(Color.WHITE);
        exit.setBorderPainted(false);
        exit.addActionListener(this);
        buttons.add(exit, BorderLayout.NORTH);

        panel2.add(buttons, BorderLayout.PAGE_END);

        setVisible(true);


        System.out.println("screen built");

        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
        repaint();

    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == exit) {

            // this.setVisible(false);
            this.dispose();

        }


    }


}




