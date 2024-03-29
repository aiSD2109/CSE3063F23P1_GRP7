import java.util.ArrayList;

public class Course {

    // initialize variables

    private String courseName, courseID, type;
    private int credit, theoric, practice, quota, semester;
    private double grade;
    private ArrayList<Course> optionalPrerequisite;
    private ArrayList<Course> mandatoryPrerequisite;
    private int courseStudents;
    private String lecturer;
    private CourseSession courseSession;

    // constructor

    public Course(String courseName, String courseID, int credit, String type, int semester) {
        this.courseName = courseName;
        this.courseID = courseID;
        this.credit = credit;
        this.type = type;
        this.semester = semester;
    }

    public Course(String courseName, String courseID, int credit, String type, int semester, double grade) {
        this.courseName = courseName;
        this.courseID = courseID;
        this.credit = credit;
        this.type = type;
        this.semester = semester;
        this.grade = grade;
    }

    public Course(String courseName, String courseID, int credit, String type, int semester, double grade,
            ArrayList<Course> optionalPrerequisite, ArrayList<Course> mandatoryPrerequisite) {
        this.courseName = courseName;
        this.courseID = courseID;
        this.credit = credit;
        this.type = type;
        this.semester = semester;
        this.grade = grade;
        this.optionalPrerequisite = optionalPrerequisite;
        this.mandatoryPrerequisite = mandatoryPrerequisite;
    }

    public Course(String courseName, String courseID, CourseSession courseSession) {
        this.courseName = courseName;
        this.courseID = courseID;
        this.courseSession = courseSession;
    }

    public Course(String courseName, String courseID, CourseSession courseSession, String lecturer) {
        this.courseName = courseName;
        this.courseID = courseID;
        this.courseSession = courseSession;
        this.lecturer = lecturer;
    }

    public Course(String courseName, String courseID, int quota, CourseSession courseSession) {
        this.courseName = courseName;
        this.courseID = courseID;
        this.quota = quota;
        this.courseSession = courseSession;
    }

    // getters and setters
    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<Course> getOptionalPrerequisite() {
        return optionalPrerequisite;
    }

    public void setOptionalPrerequisite(ArrayList<Course> optionalPrerequisite) {
        this.optionalPrerequisite = optionalPrerequisite;
    }

    public ArrayList<Course> getMandatoryPrerequisite() {
        return mandatoryPrerequisite;
    }

    public void setMandatoryPrerequisite(ArrayList<Course> mandatoryPrerequisite) {
        this.mandatoryPrerequisite = mandatoryPrerequisite;
    }

    public int getTheoric() {
        return theoric;
    }

    public void setTheoric(int theoric) {
        this.theoric = theoric;
    }

    public int getPractice() {
        return practice;
    }

    public void setPractice(int practice) {
        this.practice = practice;
    }

    public int getCourseStudents() {
        return courseStudents;
    }

    public void setCourseStudents(int courseStudents) {
        this.courseStudents = courseStudents;
    }

    public String getLecturer() {
        return lecturer;
    }

    public void setLecturer(String lecturer) {
        this.lecturer = lecturer;
    }

    public int getQuota() {
        return quota;
    }

    public void setQuota(int quota) {
        this.quota = quota;
    }

    public CourseSession getCourseSession() {
        return courseSession;
    }

    public void setCourseSession(CourseSession courseSession) {
        this.courseSession = courseSession;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }
}
