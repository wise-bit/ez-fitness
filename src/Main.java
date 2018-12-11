/**
 *
 * The main class of the project, which is to be run in order to run the full program.
 * @author Satrajit
 * @version 2.0
 *
 *
 * @param This program is intended to be an aid to helping people during their workout.There are several
 *             important features in this program, some requested by the clients, while some made sense
 *             for the program to have in order for it to be complete.
 *
 *             This application focuses primarily on
 *
 *             The main features include:
 *
 *             (i)  A feature for users to have separate account protected by passwords to ensure that
 *             their data is secure from the eyes of other users. This includes sensitive information
 *             like their weight, height, and how much they have worked out.
 *
 *             (ii) A feature for users to select a body part, and displaying a list of exercises of
 *             that body part to choose one from, which leads to the Activity page with that chosen
 *             exercise.
 *
 *             (iii) A feature in the form of an Activity page which shows information regarding the
 *             chosen activity, like the description, a video showing how to do it, and the recommended number
 *             of reps and sets for that exercise.
 *
 *             (iv) A feature to show which how much each user has used this, and how much they have worked
 *             out over past, showing the data in a table and chart format, but focused mainly on the charts.
 *
 *             (v) A feature to modify old entries.
 *
 *             (vi) A feature to add entries for past dates.
 *
 *             (vii) A feature to add more exercises, their videos and their descriptions.
 *
 *             (viii) A feature to view the source of all the data for the graphs so the user can get specific information.
 *
 *
 *             The way the application is designed, it can be used to keep track of anything the user desires, like:
 *
 *             (i) Cardio, by creating a new Exercise with an appropriate name
 *
 *             (ii) Other forms of workout which can be manipulated with the creation of new users and new exercises.
 *
 *             The app is made as visual as possible to make it approachable to even the technologically challenged.
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

            // Goes through each of the lines to add the exercises as separate objects
            while (input.hasNextLine()) {
                String[] temp = input.nextLine().split(",");
                // Ignores if the entry states Overall, which was kept initially as an added feature
                // to be manipulated in the future if required
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
