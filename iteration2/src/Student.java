import java.util.ArrayList;
public class Student extends Person {
    
    private Transcript transcript;
    private Advisor advisor;
    private int currentSemester;
    private ArrayList<Course> currentTakenCourses = new ArrayList<Course>();
    public Student(String name, String surname, String email, String phoneNumber, String ID, String password, String faculty, String department,int currentSemester, Advisor advisor) {
        super(name, surname, email, phoneNumber, ID, password, faculty, department);
        this.advisor = advisor;
        this.transcript = new Transcript(this);
        this.currentSemester = currentSemester;
    }

    public Transcript getTranscript() {
        return transcript;
    }
    public void setTranscript(Transcript transcript) {
        this.transcript = transcript;
    }
    public Advisor getAdvisor() {
        return advisor;
    }
    public void setAdvisor(Advisor advisor) {
        this.advisor = advisor;
    }
    public int getCurrentSemester() {
        return currentSemester;
    }
    public void setCurrentSemester(int currentSemester) {
        this.currentSemester = currentSemester;
    }
    public ArrayList<Course> getCurrentTakenCourses() {
        return currentTakenCourses;
    }
    public void setCurrentTakenCourses(Course course) {
        this.currentTakenCourses.add(course);
    }
}