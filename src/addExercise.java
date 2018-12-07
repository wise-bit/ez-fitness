import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
// import java.util.concurrent.Flow;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import javax.swing.*;

import static java.lang.Character.isDigit;
import static java.lang.Character.isLetter;


public class addExercise extends JFrame implements ActionListener {
    TextField exersises = new TextField();
    TextField bodypart = new TextField();
    TextField set1 = new TextField();
    TextField set2 = new TextField();
    TextField set3 = new TextField();
    JButton add1 = new JButton("Add");
    JLabel[] word = new JLabel[5];
    JLabel tittle= new JLabel("Add your own exercise!");
    JLabel option= new JLabel("Optional choice:");
    JLabel des= new JLabel("Describion about the exercise:");
    JLabel GIF1= new JLabel("GIF sucessfully chosen");
    JLabel GIF2= new JLabel("GIF faily chosen");
    TextArea des1 = new TextArea();
    JButton inport=new JButton("Import GIFs");
    File sourcefile=null;
    Font font= new Font("abc", Font.BOLD, 18);
    File a=null;
    boolean pass= false;
    String[] bodypartname = { "Chest", "Back", "Shoulders", "Biceps", "Triceps","Legs" };
    JComboBox bodyList = new JComboBox(bodypartname);


    public addExercise() throws IOException {
        setSize(375, 500);
        setTitle("Addexersises");
        setResizable(false);
        setLayout(null);
        setVisible(true);
        tittle.setFont(font);
        tittle.setBounds(80, 10, 300, 50);
        add(tittle);

        exersises.setBounds(150, 75, 100, 20);
        exersises.setVisible(true);
        add(exersises);

        bodyList.setBounds(150, 100, 100, 20);
        bodyList.setVisible(true);
        bodyList.addActionListener(this);
        add(bodyList);

        set1.setBounds(150, 125, 100, 20);
        set1.setVisible(true);
        add(set1);

        set2.setBounds(150, 150, 100, 20);
        set2.setVisible(true);
        add(set2);

        set3.setBounds(150, 175, 100, 20);
        set3.setVisible(true);
        add(set3);

        add1.setBounds(230, 400, 100, 35);
        add1.setVisible(true);
        add1.addActionListener(this);
        add1.setBackground(Color.RED);
        add1.setForeground(Color.WHITE);
        add(add1);

        for(int i=0;i<5;i++){
            word[i] = new JLabel();
            word[i].setBounds(50, 75 +25*i, 100, 20);
            word[i].setVisible(true);
            add(word[i]);
        }
        word[0].setText("Exersisce name:*");
        word[1].setText("Bodypart:*");
        word[2].setText("Set1:*");
        word[3].setText("Set2:*");
        word[4].setText("Set3:*");

        inport.setBounds(220, 260, 120, 20);
        inport.setVisible(true);
        inport.addActionListener(this);
        add(inport);

        GIF1.setBounds(220, 280, 120, 20);
        GIF1.setVisible(false);
        add(GIF1);

        GIF2.setBounds(220, 280, 120, 20);
        GIF2.setVisible(false);
        add(GIF2);

        option.setBounds(20, 200, 120, 20);
        option.setVisible(true);
        add(option);

        des.setBounds(20, 220, 200, 20);
        des.setVisible(true);
        add(des);

        des1.setBounds(20, 250, 180, 200);
        des1.setVisible(true);
        add(des1);






    }

    @Override
    public void actionPerformed(ActionEvent e)  {
        if (e.getSource() == add1) {
            pass=true;
            if (exersises.getText().length() == 0) {
                exersises.setBackground(Color.RED);
                pass = false;
            } else {
                exersises.setBackground(Color.WHITE);
            }

            if (checkint(set1.getText()) && set1.getText().length() > 0) {
                set1.setBackground(Color.WHITE);
            } else {
                set1.setBackground(Color.RED);
                pass = false;
            }
            if (checkint(set2.getText()) && set2.getText().length() > 0) {
                set2.setBackground(Color.WHITE);
            } else {
                set2.setBackground(Color.RED);
                pass = false;
            }
            if (checkint(set3.getText()) && set3.getText().length() > 0) {
                set3.setBackground(Color.WHITE);
            } else {
                set3.setBackground(Color.RED);
                pass = false;
            }

            if (pass==true) {
                try {
//                    a=new File("res/GIFs/"+sourcefile.getName());
//                    try {
//                        copyFile(sourcefile, a);
//                    }catch (Exception b) {
//                        System.out.print(b.getMessage());
//                    }
                    FileWriter ab = new FileWriter("res/database/exercises.csv", true);
                    ab.append("\n");
                    ab.append(exersises.getText());
                    ab.append(",");
                    ab.append(bodyList.getSelectedItem().toString());
                    ab.append(",");
                    ab.append(set1.getText());
                    ab.append(",");
                    ab.append(set2.getText());
                    ab.append(",");
                    ab.append(set3.getText());
                    ab.flush();
                    ab.close();
                    System.out.println("Information added!");
                    a=new File("res/GIFs/"+sourcefile.getName());
                    try {
                        copyFile(sourcefile, a);
                    }catch (Exception b) {
                        System.out.println(b.getMessage());
                    }
                } catch (Exception b) {
                    System.out.println(b.getMessage());
                }
                //get the Selected Item
                System.out.println(bodyList.getSelectedItem());
                //get text from text area
                System.out.println(des1.getText());
                this.dispose();
            }


        }
        if(e.getSource()== inport){
            FileNameExtensionFilter filter= new FileNameExtensionFilter("gif","gif");
            JFileChooser gifchoose = new JFileChooser();
            gifchoose.setFileFilter(filter);
            gifchoose.showDialog(null,"Select the GIF");
            gifchoose.setVisible(true);
            sourcefile = gifchoose.getSelectedFile();
            if (sourcefile!=null){
                GIF1.setVisible(true);
                GIF2.setVisible(false);
            }
            else{
                GIF1.setVisible(false);
                GIF2.setVisible(true);
            }



        }
    }
    private static void copyFile(File source, File dest) throws IOException {
        Files.copy(source.toPath(), dest.toPath());
    }
    public Boolean checkint (String b){
        for (int i = 0; i < b.length(); i++) {
            if (isLetter(b.charAt(i)))
                return false;
        }
        return true;
    }
}