import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
public class StudentInterface {

    // attributes
    private Student student;
    private LoginInterface loginInterface;
    private StudentCourseRegistrationInterface studentCourseRegistrationInterface;
    Scanner input;
    // constructor
    public StudentInterface(Student person, LoginInterface loginInterface) {
        this.student = person;
        this.loginInterface = loginInterface;
       
    }

    public void stuMenu() {

        // prompting
        System.out.println("-------------------- Student Menu --------------------\n");
        System.out.println("What do you want to do?\n");
        System.out.println("Enter '1' to view transcript");
        System.out.println("Enter '2' to view courses");
        System.out.println("Enter '3' to go to Course Registration System");
        System.out.println("Enter '*' to logout");
        System.out.println("Enter 'x' to exit");

        input = new Scanner(System.in);
        char choice  = input.next().charAt(0);
        

        switch (choice) {
        
        // viewing transcript
        case '1':
            student.getTranscript().viewTranscript();
            boolean a = true;
            while (a) {
                System.out.println("Enter '0' to go back to the main menu.");
                char backChoice = input.next().charAt(0);
                if (backChoice == '0') {
                    stuMenu();
                } else {
                    System.out.println("Invalid input. Please try again.");
                }
            }

        // show courses
        case '2':
        try {
            showCourses();
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        } catch (ParseException e) {
        }
            break;

        // going to course registration system
        case '3':
        studentCourseRegistrationInterface = new StudentCourseRegistrationInterface(student,this);
        studentCourseRegistrationInterface.stuRegMenu();
            break;
        
        // logging out
        case '*':
        loginInterface.logout();
            break;

        // exiting
        case 'x':
        System.out.println("\n-----------------------Thank you for using Marmara Course Registration System-----------------------");
        loginInterface.exit();
            break;
        
        // invalid input
        default:
        System.out.println("Invalid input.Please try again.");
        stuMenu();
            break;
        }
    }
    private void showCourses() throws FileNotFoundException, IOException, ParseException {
        Object coursesJSONobj;
            JSONArray courseJSONarr;
            coursesJSONobj = new JSONParser().parse(new FileReader("./jsons/courses.json"));
    
            courseJSONarr = (JSONArray) coursesJSONobj;
    
            int previousSemester = -1;
            for (Object courseObj : courseJSONarr) {
                JSONObject course = (JSONObject) courseObj;
    
                String CourseID = (String) course.get("CourseID");
                String CourseName = (String) course.get("CourseName");
                String CourseType = (String) course.get("CourseType");
                long Credit = (long) course.get("Credit");
                long Semester = (long) course.get("Semester");
                JSONArray OptionalPrerequisites = (JSONArray) course.get("OptionalPrerequisites");
                JSONArray MandatoryPrerequisites = (JSONArray) course.get("MandatoryPrerequisites");
    
                if (CourseType.equals("M")) {
                    CourseType = "Mandatory";
                } else if (CourseType.equals("E")) {
                    CourseType = "Elective";
                }
    
                String optionalPrerequisitesString = OptionalPrerequisites.toString().replace("\"", "").replace("[", "")
                        .replace("]", "").replace(", ", "-");
                String mandatoryPrerequisitesString = MandatoryPrerequisites.toString().replace("\"", "").replace("[", "")
                        .replace("]", "").replace(", ", "-");
    
                if (previousSemester != Semester) {
                    System.out.printf(
                            "-----------------------------------------------------------------------------------------------------------------------------------------------------------------%n");
                    System.out.printf("Semester %s", Semester);
                    System.out.printf("\t%-10s%-45s%-15s%-8s%-35s%-35s%n%n",
                            "CourseID", "CourseName", "CourseType", "Credit",
                            "OptionalPrerequisites", "MandatoryPrerequisites");
                    previousSemester = (int) Semester;
                }
    
                System.out.printf("\t\t%-10s%-45s%-15s%-8s%-35s%-35s%n",
                        CourseID, CourseName, CourseType, Credit,
                        optionalPrerequisitesString, mandatoryPrerequisitesString);
        
    
            }
            boolean a = true;
            while (a) {
                System.out.println("Enter '0' to go back to the main menu.");
                input = new Scanner(System.in);
                char backChoice = input.next().charAt(0);
                if (backChoice == '0') {
                    stuMenu();
                } else {
                    System.out.println("Invalid input. Please try again.");
                }
            }
    }       
}

