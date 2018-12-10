/**
 * Author: Satrajit
 */

public class Exercise {

    private String name = "";
    private String bodyPart = "";
    private String reps1 = "";
    private String reps2 = "";
    private String reps3 = "";
    private String intensity = "";

    public Exercise(String name, String bodyPart, String reps1, String reps2, String reps3, String intensity) {
        this.name = name;
        this.bodyPart = bodyPart;
        this.reps1 = reps1;
        this.reps2 = reps2;
        this.reps3 = reps3;
        this.intensity = intensity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBodyPart() {
        return bodyPart;
    }

    public void setBodyPart(String bodyPart) {
        this.bodyPart = bodyPart;
    }

    public String getReps1() {
        return reps1;
    }

    public void setReps1(String reps1) {
        this.reps1 = reps1;
    }

    public String getReps2() {
        return reps2;
    }

    public void setReps2(String reps2) {
        this.reps2 = reps2;
    }

    public String getReps3() {
        return reps3;
    }

    public void setReps3(String reps3) {
        this.reps3 = reps3;
    }

    public String getIntensity() {
        return intensity;
    }

    public void setIntensity(String intensity) {
        this.intensity = intensity;
    }
}
