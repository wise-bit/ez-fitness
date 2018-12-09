import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class User {

    private String username = "";
    private String password = "";
    private String height = "";
    private String weight = "";
    private String age = "";
    private String level = "";

    public User() {

    }

    public User(String username, String password, String height, String weight, String age, String level) {
        this.username = username;
        this.password = password;
        this.height = height;
        this.weight = weight;
        this.age = age;
        this.level = level;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", height='" + height + '\'' +
                ", weight='" + weight + '\'' +
                ", age='" + age + '\'' +
                ", level='" + level + '\'' +
                '}';
    }

    // Helper methods

    public void promote() throws IOException {
        Scanner input = new Scanner(new File("res/database/exercises.csv"));

        int age = 0;

        while (input.hasNextLine()) {

            String[] temp = input.nextLine().split(",");

            String filePath = "res/users/" + getUsername() + "/" + temp[0] + ".txt";
            age += Statistics.countLines(filePath);

        }

        if (age > 50 && level.equals(1)) {

            try {

                String filePath = "res/database/userInfo.csv";
                Scanner scanner = new Scanner(new File(filePath));
                StringBuilder buffer = new StringBuilder();

                while(scanner.hasNext()) {
                    String current = scanner.nextLine();
                    if (current.split(",")[0].equals(username)) {
                        buffer.append(username + "," + password + "," + height + "," + weight + "," + age + "2" + ",");
                    }
                    else
                        buffer.append(current);
                    if(scanner.hasNext())
                        buffer.append("\n");
                }
                scanner.close();
                PrintWriter printer = new PrintWriter(new File(filePath));
                printer.print(buffer);
                printer.close();

            } catch (IOException error) {
                error.printStackTrace();
            }

        } else if (age > 150 && (level.equals("2") || level.equals("1"))) {
            try {

                String filePath = "res/database/userInfo.csv";
                Scanner scanner = new Scanner(new File(filePath));
                StringBuilder buffer = new StringBuilder();

                while(scanner.hasNext()) {
                    String current = scanner.nextLine();
                    if (current.split(",")[0].equals(username)) {
                        buffer.append(username + "," + password + "," + height + "," + weight + "," + age + "3" + ",");
                    }
                    else
                        buffer.append(current);
                    if(scanner.hasNext())
                        buffer.append("\n");
                }
                scanner.close();
                PrintWriter printer = new PrintWriter(new File(filePath));
                printer.print(buffer);
                printer.close();

            } catch (IOException error) {
                error.printStackTrace();
            }
        }
    }
}
