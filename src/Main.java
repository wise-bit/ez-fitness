/**
 *
 * The main class of the project
 * @author Satrajit
 * @version 2.0
 * 
 * 
 */

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main{

    // Creates all variables which require global access from other classes

    // Creates all exercise objects
    public static ArrayList<Exercise> allExercises;

    // Makes the application scalable
    public static Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

    // Creates all of the necessary global objects
    public static User currentUser;
    public static String date;
    public static ArrayList<String> nameList = new ArrayList<String>();
    public static String currentExercise = "Incline Dumbbell Skullcrushers";

    // These two screens are made from Main, and can be closed by calling name.dispose();
    public static Login x;
    public static InfoScreen2 info;

    // For using res as an external folder, not in use right now
    public static String pathToRes = "";

    public static void main(String[] args) throws IOException {

        // For use with pathToRes when required

        // Scanner filePathFile = new Scanner(new File("source.txt"));
        // String path = filePathFile.nextLine();
        // File file = new File(path);
        // if (!file.exists() || !path.contains("res")) {
            // new getRes();
        // }

        // Initializes all of the global variables
        init();

        // Creates a disposable login screen
        x = new Login();

    }

    // Initializes all of the global variables
    public static void init() throws IOException {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        date = simpleDateFormat.format(new Date());
        updateNameList();
        importExercises();
        importPlayer();
    }

    // Refreshes the nanme list of user information for use when new users are made
    public static void updateNameList() throws IOException {
        Scanner data = null;
        data = new Scanner(new File("res/database/userInfo.csv"));
        while (data.hasNextLine()) {
            String[] current = data.nextLine().split(",");
            nameList.add(current[0]);
        }
    }

    // Imports all of the exercises from the files in the form of Exercise objects
    public static void importExercises() throws IOException {
        allExercises = new ArrayList<Exercise>();

        try {

            Scanner input = new Scanner(new File("res/database/exercises.csv"));

            while (input.hasNextLine()) {
                String[] temp = input.nextLine().split(",");
                if (!temp[0].equals("Overall"))
                    allExercises.add(new Exercise(temp[0],temp[1],temp[2],temp[3],temp[4],temp[5]));
            }
            input.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    // Creates a blank user, which can later be assigned from the Login page
    public static void importPlayer() throws IOException {

        currentUser = new User();

    }

}
