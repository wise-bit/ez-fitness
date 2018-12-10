/**
 * Author: Satrajit
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class getRes extends JFrame implements ActionListener {

    JButton go;

    JFileChooser chooser;
    String choosertitle;

    public getRes() {

        this.addWindowListener(
                new WindowAdapter() {
                    public void windowClosing(WindowEvent e) {
                        System.exit(0);
                    }
                }
        );
        this.setSize(this.getPreferredSize());
        // this.setVisible(true);

        chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle(choosertitle);
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        chooser.setAcceptAllFileFilterUsed(false);
        //
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {

            PrintWriter printer = null;
            try {
                printer = new PrintWriter(new File("source.txt"));
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
            printer.print(String.valueOf(chooser.getCurrentDirectory()));
            printer.close();
        }
        else {
            System.out.println("No Selection ");
        }

    }

    public void actionPerformed(ActionEvent e) {

    }

    public Dimension getPreferredSize(){
        return new Dimension(200, 200);
    }

}