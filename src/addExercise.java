import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import static java.lang.Character.isLetter;


public class addExercise extends JFrame implements ActionListener {
    TextField exersises = new TextField();
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
    JLabel diff1= new JLabel("Difficulty:");
    JLabel error= new JLabel("Excisises already excited");
    TextArea des1 = new TextArea();
    JButton inport=new JButton("Import GIFs");
    File sourcefile=null;
    Font font= new Font("abc", Font.BOLD, 18);
    File a=null;
    boolean Gifin = false;
    boolean pass= false;
    String[] bodypartname = { "chest", "back", "shoulders", "biceps", "triceps","legs" };
    String[] diff = { "Easy", "Medium", "Hard"};
    JComboBox bodyList = new JComboBox(bodypartname);
    JComboBox difflist = new JComboBox(diff);
    String indes ="1";
    ArrayList<String> same1=new ArrayList<String>();
    ArrayList<String> same2=new ArrayList<String>();
    ArrayList<String> same3=new ArrayList<String>();
    ArrayList<String> same4=new ArrayList<String>();
    ArrayList<String> same5=new ArrayList<String>();
    ArrayList<String> same6=new ArrayList<String>();
    String check1,check2;


    public addExercise(ArrayList legsList,
                       ArrayList chestList,
                       ArrayList backList,
                       ArrayList shouldersList,
                       ArrayList bicepsList,
                       ArrayList tricepsList) throws IOException {
        same1=legsList;
        same2=chestList;
        same3=backList;
        same4=shouldersList;
        same5=bicepsList;
        same6=tricepsList;


        setSize(375, 500);
        setTitle("Add Exersises");
        setResizable(false);
        setLayout(null);
        setVisible(true);
        tittle.setFont(font);
        tittle.setBounds(80, 10, 300, 50);
        add(tittle);

        exersises.setBounds(150, 75, 100, 20);
        exersises.setVisible(true);
        add(exersises);

        error.setBounds(150, 50, 100, 20);
        error.setVisible(false);
        add(error);

        bodyList.setBounds(150, 100, 100, 20);
        bodyList.setVisible(true);
        bodyList.addActionListener(this);
        add(bodyList);

        diff1.setBounds(265, 150, 100, 20);
        diff1.setVisible(true);
        add(diff1);

        difflist.setBounds(265, 170, 100, 20);
        difflist.setVisible(true);
        add(difflist);

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
            word[i].setBounds(50, 72+25*i, 110, 20);
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
            error.setVisible(false);
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
            for(int i=0;i<same1.size();i++){
                check1=exersises.getText();
                check2=same1.get(i);
                check1=check1.replace(" ","");
                check2=check2.replace(" ","");
                check1=check1.toLowerCase();
                check2=check2.toLowerCase();
                if (check1.equals(check2)){
                    pass=false;
                    error.setVisible(true);
                    System.out.println(check1);
                    System.out.println(check2);
                }
            }
            for(int i=0;i<same2.size();i++){
                check1=exersises.getText();
                check2=same2.get(i);
                check1=check1.replace(" ","");
                check2=check2.replace(" ","");
                check1=check1.toLowerCase();
                check2=check2.toLowerCase();
                if (check1.equals(check2)){
                    pass=false;
                    error.setVisible(true);
                    System.out.println(check1);
                    System.out.println(check2);
                }
            }
            for(int i=0;i<same3.size();i++){
                check1=exersises.getText();
                check2=same3.get(i);
                check1=check1.replace(" ","");
                check2=check2.replace(" ","");
                check1=check1.toLowerCase();
                check2=check2.toLowerCase();
                if (check1.equals(check2)){
                    pass=false;
                    error.setVisible(true);
                    System.out.println(check1);
                    System.out.println(check2);
                }
            }
            for(int i=0;i<same4.size();i++){
                check1=exersises.getText();
                check2=same4.get(i);
                check1=check1.replace(" ","");
                check2=check2.replace(" ","");
                check1=check1.toLowerCase();
                check2=check2.toLowerCase();
                if (check1.equals(check2)){
                    pass=false;
                    error.setVisible(true);
                    System.out.println(check1);
                    System.out.println(check2);
                }
            }
            for(int i=0;i<same5.size();i++){
                check1=exersises.getText();
                check2=same5.get(i);
                check1=check1.replace(" ","");
                check2=check2.replace(" ","");
                check1=check1.toLowerCase();
                check2=check2.toLowerCase();
                if (check1.equals(check2)){
                    pass=false;
                    error.setVisible(true);
                    System.out.println(check1);
                    System.out.println(check2);
                }
            }
            for(int i=0;i<same6.size();i++){
                check1=exersises.getText();
                check2=same6.get(i);
                check1=check1.replace(" ","");
                check2=check2.replace(" ","");
                check1=check1.toLowerCase();
                check2=check2.toLowerCase();
                if (check1.equals(check2)){
                    pass=false;
                    error.setVisible(true);
                }
            }

            if (pass==true) {
                if(difflist.getSelectedItem().toString()=="Easy"){
                    indes= "1";
                }
                if(difflist.getSelectedItem().toString()=="Medium"){
                    indes= "2";
                }
                if(difflist.getSelectedItem().toString()=="Hard"){
                    indes= "3";
                }
                try {
                    if(Gifin == true){
                        a=new File("res/GIFs/"+sourcefile.getName());
                        try {
                            copyFile(sourcefile, a);
                        }catch (Exception b) {
                            System.out.print(b.getMessage());
                        }
                    }
                    FileWriter ab = new FileWriter("res/database/exercises.csv", true);
                    ab.append("\r");
                    ab.append(exersises.getText());
                    ab.append(",");
                    ab.append(bodyList.getSelectedItem().toString());
                    ab.append(",");
                    ab.append(set1.getText());
                    ab.append(",");
                    ab.append(set2.getText());
                    ab.append(",");
                    ab.append(set3.getText());
                    ab.append(",");
                    ab.append(indes);
                    ab.flush();
                    ab.close();
                    a=new File("res/GIFs/"+sourcefile.getName());
                    try {
                        copyFile(sourcefile, a);
                    }catch (Exception b) {
                        System.out.print(b.getMessage());
                    }
                } catch (Exception b) {
                    System.out.print(b.getMessage());
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
                Gifin = true;
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
