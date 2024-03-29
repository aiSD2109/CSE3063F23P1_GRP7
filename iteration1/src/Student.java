public class Student extends Person {
    private int semester;
    private Transcript transcript;
    private Advisor advisor;
    public Student(String name, String surname, String email, String phoneNumber, String ID, String password, String faculty, String department, int semester,Advisor advisor) {
        super(name, surname, email, phoneNumber, ID, password, faculty, department);
        this.semester = semester;
        this.advisor = advisor;
        this.transcript = new Transcript(this);
    }
    public int getSemester() {
        return semester;
    }
    public void setSemester(int semester) {
        this.semester = semester;
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
}