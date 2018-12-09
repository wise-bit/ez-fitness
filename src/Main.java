import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main{

    public static ArrayList<Exercise> allExercises;

    public static Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    public static User currentUser;
    public static String date;
    public static ArrayList<String> nameList = new ArrayList<String>();
    public static String currentExercise = "Incline Dumbbell Skullcrushers";

    public static Login x;
    public static InfoScreen2 info;

    public static void main(String[] args) throws IOException {

        init();

        x = new Login();
        // new addMissedDate();
        // new Activity(currentExercise);
        // new InfoScreen2();

        // new Statistics();
        // new NewUser(nameList).setVisible(true);
        // new InfoScreen2();

//        PieChart demo = new PieChart("Comparison", "Which operating system are you using?");
//        demo.pack();
//        demo.setVisible(true);

        // Incline Dumbbell Skullcrushers

    }

    public static void init() throws IOException {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        date = simpleDateFormat.format(new Date());
        updateNameList();
        importExercises();
        importPlayer();
    }

    public static void updateNameList() throws IOException {
        Scanner data = null;
        data = new Scanner(new File("res/database/userInfo.csv"));
        while (data.hasNextLine()) {
            String[] current = data.nextLine().split(",");
            nameList.add(current[0]);
        }
    }

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

    public static void importPlayer() throws IOException {

        currentUser = new User();

    }

}
