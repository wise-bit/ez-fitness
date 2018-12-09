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

    public void promote() {

    }
}
