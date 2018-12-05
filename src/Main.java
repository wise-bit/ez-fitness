import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main{

    public static Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    public static String currentUser = "";
    public static String[] currentInformation;
    public static String date;
    public static ArrayList<String> nameList = new ArrayList<String>();
    public static String currentExercise = "Incline Dumbbell Skullcrushers";

    public static Login x;

    public static void main(String[] args) throws IOException {

        init();

        x = new Login();
        // new Activity(currentExercise);

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
    }

    public static void updateNameList() throws IOException {
        Scanner data = null;
        data = new Scanner(new File("res/database/userInfo.csv"));
        while (data.hasNextLine()) {
            String[] current = data.nextLine().split(",");
            nameList.add(current[0]);
        }
    }

}
