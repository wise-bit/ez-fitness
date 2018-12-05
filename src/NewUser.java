import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.Color;
import java.awt.Font;
import java.awt.*;
import java.awt.font.*;
import java.io.*;
import javax.swing.*;
import java.util.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.io.IOException;

import javax.swing.*;

import static java.lang.Character.isDigit;
import static java.lang.Character.isLetter;

public class NewUser extends JFrame implements ActionListener {
    JLabel Question[] = new JLabel[6];
    JLabel error = new JLabel("already have a same name");
    TextField name = new TextField();
    TextField age = new TextField();
    TextField height = new TextField();
    TextField weight = new TextField();
    JPasswordField pw = new JPasswordField();
    JButton level[] = new JButton[3];
    JButton returns = new JButton("Return");
    JButton start = new JButton("Start");
    boolean levelpass = false;
    boolean pass = true;
    String username = "";
    ArrayList<String> same=new ArrayList<String>();
    int intensity = 1;


    public NewUser(ArrayList z) {
        same=z;
        setSize(350, 450);
        setTitle("Registration Page");
        setResizable(false);
        setLayout(null);
        name.setBounds(50, 75, 100, 20);
        name.setVisible(true);
        name.addActionListener(this);
        add(name);
        pw.setBounds(175, 75, 100, 20);
        pw.setVisible(true);
        pw.addActionListener(this);
        add(pw);
        age.setBounds(50, 125, 100, 20);
        age.setVisible(true);
        age.addActionListener(this);
        add(age);
        height.setBounds(50, 175, 100, 20);
        height.setVisible(true);
        height.addActionListener(this);
        add(height);
        weight.setBounds(50, 225, 100, 20);
        weight.setVisible(true);
        weight.addActionListener(this);
        add(weight);
        error.setBounds(50,25,200,20);
        error.setVisible(false);
        add(error);


        for (int i = 0; i < 5; i++) {
            Question[i] = new JLabel();
            Question[i].setBounds(50, 50 + 50 * i, 100, 20);
            add(Question[i]);
        }
        Question[5] = new JLabel();
        Question[5].setBounds(170, 50, 100, 20);
        add(Question[5]);
        Question[0].setText("Username:");
        Question[1].setText("Age:");
        Question[2].setText("Height(cm):");
        Question[3].setText("Weight(kg):");
        Question[4].setText("Level:");
        Question[5].setText("Password:");

        for (int i = 0; i < 3; i++) {
            level[i] = new JButton();
            level[i].setBounds(50 + 85 * i, 275, 90, 30);
            add(level[i]);
            level[i].addActionListener(this);
            level[i].setBackground(Color.WHITE);
        }
        level[0].setText("Beginner");
        level[1].setText("Medium");
        level[2].setText("Experiesed");

        level[0].setFont(level[0].getFont().deriveFont(9f));
        level[1].setFont(level[1].getFont().deriveFont(9f));
        level[2].setFont(level[2].getFont().deriveFont(9f));

        start.setBounds(200, 350, 80, 50);
        start.addActionListener(this);
        start.setBackground(Color.RED);
        start.setForeground(Color.WHITE);
        add(start);

        returns.setBounds(50, 350, 80, 50);
        returns.addActionListener(this);
        returns.setBackground(Color.WHITE);
        returns.setForeground(Color.BLACK);
        add(returns);
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        if (arg0.getSource() == level[0]) {
            level[0].setBackground(Color.RED);
            level[0].setForeground(Color.WHITE);
            level[1].setBackground(Color.WHITE);
            level[1].setForeground(Color.BLACK);
            level[2].setBackground(Color.WHITE);
            level[2].setForeground(Color.BLACK);
            intensity = 1;
            levelpass = true;
        }
        if (arg0.getSource() == level[1]) {
            level[1].setBackground(Color.RED);
            level[1].setForeground(Color.WHITE);
            level[0].setBackground(Color.WHITE);
            level[0].setForeground(Color.BLACK);
            level[2].setBackground(Color.WHITE);
            level[2].setForeground(Color.BLACK);
            intensity = 50;
            levelpass = true;
        }
        if (arg0.getSource() == level[2]) {
            level[2].setBackground(Color.RED);
            level[2].setForeground(Color.WHITE);
            level[1].setBackground(Color.WHITE);
            level[1].setForeground(Color.BLACK);
            level[0].setBackground(Color.WHITE);
            level[0].setForeground(Color.BLACK);
            intensity = 100;
            levelpass = true;
        }

        if (arg0.getSource() == start) {
            pass = true;
            if (name.getText().length() == 0) {
                name.setBackground(Color.RED);
                pass = false;
            } else {
                name.setBackground(Color.WHITE);
            }
            if (checkint(age.getText()) && age.getText().length() > 0) {
                age.setBackground(Color.WHITE);
            } else {
                age.setBackground(Color.RED);
                pass = false;
            }
            if (checkint(height.getText()) && height.getText().length() > 0) {
                height.setBackground(Color.WHITE);
            } else {
                height.setBackground(Color.RED);
                pass = false;
            }
            if (checkint(weight.getText()) && weight.getText().length() > 0) {
                weight.setBackground(Color.WHITE);
            } else {
                weight.setBackground(Color.RED);
                pass = false;
            }
            if(pw.getPassword().length == 0){
                pass=false;
                pw.setBackground(Color.RED);
            }
            else{
                pw.setBackground(Color.WHITE);
            }
            for(int i=0;i<same.size();i++){
                if(same.get(i).equals(name.getText())){
                    error.setVisible(true);
                    pass=false;
                    break;
                }
                else
                    error.setVisible(false);
            }
            if(pass==true&&levelpass==true) {
                try {
                    FileWriter ab = new FileWriter("res/database/userInfo.csv", true);
                    ab.append("\r");
                    username = name.getText();
                    ab.append(username);
                    ab.append(",");
                    ab.append(String.valueOf(pw.getPassword()));
                    ab.append(",");
                    ab.append(height.getText());
                    ab.append(",");
                    ab.append(weight.getText());
                    ab.append(",");
                    ab.append(age.getText());
                    ab.append(",");
                    ab.append(Integer.toString(intensity));
                    ab.append(",");

                    /// Creating the user in the database

                    createFiles();

                    //////

                    ab.flush();
                    ab.close();
                } catch (Exception e) {
                    System.out.print(e.getMessage());
                }
                Main.x.dispose();
                this.dispose();
                try {
                    Login a = new Login();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        if (arg0.getSource() == returns) {

            try {
                Login a = new Login();
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.dispose();

        }

    }
    public Boolean checkint (String b){
        for (int i = 0; i < b.length(); i++) {
            if (isLetter(b.charAt(i)))
                return false;
        }
        return true;
    }

    public void createFiles() throws FileNotFoundException, UnsupportedEncodingException {
        String path =  "res/users/" + username;
        new File(path).mkdirs();

        File exerciseList = new File("res/database/exercises.csv");
        Scanner reader = new Scanner(exerciseList);
        while (reader.hasNextLine()) {

            String[] current = reader.nextLine().split(",");
            PrintWriter writer = new PrintWriter(path + "/" + current[0] + ".txt", "UTF-8");

            // time, reps, weight
            writer.println(Main.date + "," + 0 + "," + 0 + ",");
            writer.close();

        }

    }


}