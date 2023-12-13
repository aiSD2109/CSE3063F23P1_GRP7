import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class StudentCourseRegistrationInterface {
    private ArrayList<Course> availableCourses;
    private ArrayList<Lecture> availableLectures;
    private ArrayList<Lab> availableLabs;
    private ArrayList<Course> selectedCourses;
    private ArrayList<Lecture> selectedLectures;
    private ArrayList<Lab> selectedLabs;
    // private ArrayList<String> coursesCodesOffered;
    private ArrayList<Lecture> coursesOffered;

    private Session session;

    // private MessagesInterface messagesInt;
    private StudentInterface studentInt;
    private Scanner scanner;

    public StudentCourseRegistrationInterface(Session session, StudentInterface studentInt) {
        this.session = session;
        this.studentInt = studentInt;
        this.availableCourses = new ArrayList<Course>();
        this.selectedCourses = new ArrayList<Course>();
        this.availableLectures = new ArrayList<Lecture>();
        this.selectedLectures = new ArrayList<Lecture>();
        this.availableLabs = new ArrayList<Lab>();
        this.selectedLabs = new ArrayList<Lab>();
        // this.coursesCodesOffered = new ArrayList<String>();
        this.coursesOffered = new ArrayList<Lecture>();
        getCoursesOffered();
        this.scanner = new Scanner(System.in);
    }

    public void stuRegMenu() {
        while (true) {
            showStudentInf();
            System.out.println(
                    "\n---------------------------------Student Course Registration System---------------------------------\n");
            System.out.println("Choose an option by entering the corresponding number:\n");
            System.out.println("1. Selected courses");
            System.out.println("2. Available courses");
            System.out.println("0. Go back to Student Menu\n");

            var choice = scanner.next();
            switch (choice) {
                case "1":
                    selectedCoursesMenu();
                    break;
                case "2":
                    availableCoursesMenu();
                    break;
                case "0":
                    studentInt.stuMenu();
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    private void showStudentInf() {
        System.out.printf("%n%-12s%-3s%-22s%-10s%-2s%-40s%n", "Student ID", "-", "Name and Surname:",
                session.getUser().getID(),
                "-", session.getUser().getName() + " " + session.getUser().getSurname());
        System.out.printf("%-10s%-30s%n", "Advisor: ",
                session.getUser().getName() + " " + session.getUser().getSurname());
        System.out.printf("%-10s%-5s%n", "Semester: ", ((Student) (session.getUser())).getCurrentSemester());

    }

    private void selectedCoursesMenu() {
        while (true) {
            showStudentInf();
            System.out.println(
                    "\n---------------------------------Selected Course Menu---------------------------------\n");
            System.out.println("Choose an option by entering the corresponding number:\n");
            System.out.println("1. Show selected courses");
            System.out.println("2. Delete selected courses");
            System.out.println("3. Send registration request");
            System.out.println("0. Go back to Student Course Registration System\n");
            var choice = scanner.next();
            if (selectedCourses.size() == 0) {
                System.out.println("\n!!! Currently, there are no courses selected !!!\n");
                break;
            }
            switch (choice) {
                case "1":
                    showSelectedCourses();
                    break;
                case "2":
                    deleteSelectedCourseMenu();
                    break;
                case "3":
                    sendRegRequest();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    private void showSelectedCourses() {
        int courseNumber = 1;
        System.out.printf("%n%-8s%-13s%-70s%-8s%-15s%n", "Number", "CourseID", "CourseName", "Credit", "CourseType");
        System.out.printf(
                "--------------------------------------------------------------------------------------------------------------%n");

        for (var course : selectedCourses) {

            System.out.printf("%-8s%-13s%-70s%-8s%-15s%n", courseNumber++, course.getCourseID(), course.getCourseName(),
                    course.getCredit(),
                    course.getType().equals("E") ? "Elective" : "Mandatory");

        }
        System.out.println();
    }

    private void deleteSelectedCourseMenu() {
        showSelectedCourses();
        while (true) {
            showStudentInf();
            System.out.println(
                    "\n---------------------------------Delete Selected Course Menu---------------------------------\n");
            System.out.println("Choose an option by entering the corresponding number:\n");
            System.out.println("1. Delete all selected courses");
            System.out.println("2. Delete a selected course");
            System.out.println("0. Go back to Selected Course Menu\n");
            var choice = scanner.next();
            switch (choice) {
                case "1":
                    selectedCourses.clear();
                    return;
                case "2":
                    System.out.print(
                            "Enter the number of the courses you want to delete (for example -> 1-2-3) or cancel with entering 0: ");
                    var selectedIndexes = scanner.next().split("-");
                    if (selectedIndexes[0].equals("0")) {
                        break;
                    }
                    System.out.print("Do you want to delete selected courses? (y/n): ");
                    var saveChoice = scanner.next();
                    if (saveChoice.toLowerCase().equals("y")) {
                        deleteSelectedCourses(selectedIndexes);
                    }
                    return;
                case "0":
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    private void deleteSelectedCourses(String[] selectedIndexes) {
        // copy selected courses to a new list
        var selectedCoursesCopy = new ArrayList<Course>();
        for (var course : selectedCourses) {
            selectedCoursesCopy.add(course);
        }
        // remove selected courses from selected courses
        for (var selectedIndex : selectedIndexes) {
            var index = Integer.parseInt(selectedIndex);
            selectedCourses.remove(selectedCoursesCopy.get(index - 1));
        }
    }

    private void sendRegRequest() {
        try {
            // Read the existing JSON content
            JSONParser parser = new JSONParser();
            JSONArray existingRegistrationArray;

            try (FileReader reader = new FileReader("jsons/RegistrationRequests.json")) {
                Object obj = parser.parse(reader);
                existingRegistrationArray = (JSONArray) obj;
            } catch (Exception e) {
                existingRegistrationArray = new JSONArray();
            }

            // Convert the selected courses to JSON
            JSONArray selectedCoursesJsonArray = new JSONArray();
            JSONArray selectedLecturesJsonArray = new JSONArray();
            JSONArray selectedLabsJsonArray = new JSONArray();
            JSONArray approvedCoursesJsonArray = new JSONArray();
            JSONArray approvedLecturesJsonArray = new JSONArray();
            JSONArray approvedLabsJsonArray = new JSONArray();

            for (Course course : selectedCourses) {
                selectedCoursesJsonArray.add(course.getCourseID());
            }

            for (Lecture lecture : selectedLectures) {
                selectedLecturesJsonArray.add(lecture.getLectureID());
            }

            for (Lab lab : selectedLabs) {
                selectedLabsJsonArray.add(lab.getLabID());
            }

            // Create the JSON object
            JSONObject registrationJson = new JSONObject();
            registrationJson.put("StudentID", session.getUser().getID());
            registrationJson.put("SelectedCourses", selectedCoursesJsonArray);
            registrationJson.put("SelectedLectures", selectedLecturesJsonArray);
            registrationJson.put("SelectedLabs", selectedLabsJsonArray);
            registrationJson.put("ApprovedCourses", approvedCoursesJsonArray);
            registrationJson.put("ApprovedLectures", approvedLecturesJsonArray);
            registrationJson.put("ApprovedLabs", approvedLabsJsonArray);

            // Append the new data to the existing JSON array
            existingRegistrationArray.add(registrationJson);

            // Write the updated JSON array back to the file
            try (FileWriter fileWriter = new FileWriter("jsons/RegistrationRequests.json")) {
                fileWriter.write(existingRegistrationArray.toJSONString());
                System.out.println("!!! Registration request appended to RegistrationRequests.json !!!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void availableCoursesMenu() {
        while (true) {
            showStudentInf();
            System.out.println(
                    "\n---------------------------------Available Course Menu---------------------------------\n");
            System.out.println("Choose an option by entering the corresponding number:\n");

            System.out.println("1. Show available courses");
            System.out.println("0. Go back to Student Course Registration System\n");
            var choice = scanner.next();
            switch (choice) {
                case "1":
                    calculateAvailableCourses();
                    calculateAvailableLectures();
                    calculateAvailableLabs();
                    showAvailableLectures();
                    if (selectedCourses.size() == 5) {
                        System.out.println("5 courses already selected, press 0 to go back");
                        var input = "";
                        do {
                            input = scanner.next();
                            if (input.equals("0")) {
                                System.out.println("Invalid input");
                                continue;
                            }
                            return;
                        } while (true);
                    }
                    System.out.print("Select courses you want to add (for example -> 1) or cancel with entering 0: ");
                    var input = "";
                    do {
                        input = scanner.next();
                        if (input.length() == 0 || Integer.parseInt(input) > availableLectures.size()) {
                            System.out.println("Invalid input");
                            continue;
                        }
                        break;
                    } while (true);

                    if (input.equals("0")) {
                        return;
                    }

                    var isLabsAvailable = showAvailableLabs(Integer.parseInt(input));
                    if (isLabsAvailable) {
                        System.out.print("Select labs you want to add (for example -> 1) or cancel with entering 0: ");
                        do {
                            input = scanner.next();
                            if (input.length() == 0 || Integer.parseInt(input) > availableLabs.size()) {
                                System.out.println("Invalid input");
                                continue;
                            }
                            break;
                        } while (true);
                        if (input.equals("0")) {
                            break;
                        }
                        saveAvailableCourses(Integer.parseInt(input), Integer.parseInt(input));
                        System.out.println("!!! Selected lecture and lab added !!!");
                    } else {
                        saveAvailableCourses(Integer.parseInt(input));
                        System.out.println("!!! Selected lecture added only !!!");
                    }

                    break;
                case "0":
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    private Course findCourseByID(ArrayList<Course> courses, String courseID) {
        for (var course : courses) {
            if (course.getCourseID().equals(courseID)) {
                return course;
            }
        }
        return null; // returns when course not found
    }

    private CourseSession createCourseSession(String courseDayTimeLocation) {
        // parse the string and create course session object
        String[] parts = courseDayTimeLocation.split(" ");
        ArrayList<String> courseDay = new ArrayList<>();
        ArrayList<String> courseStartTime = new ArrayList<>();
        ArrayList<String> courseEndTime = new ArrayList<>();
        ArrayList<String> coursePlace = new ArrayList<>();
        // each object of the list is a different course session
        for (int i = 0; i < parts.length; i += 5) {
            courseDay.add(parts[i]);
            courseStartTime.add(parts[i + 1]);
            courseEndTime.add(parts[i + 3]);
            coursePlace.add(parts[i + 4]);
        }

        return new CourseSession(courseDay, courseStartTime, courseEndTime, coursePlace);
    }

    private void getCoursesOffered() {
        try {
            var coursesOfferedJson = "jsons/CoursesOffered.json";
            var parser = new JSONParser();
            var obj = parser.parse(new FileReader(coursesOfferedJson));
            var coursesOfferedArray = (JSONArray) obj;

            for (var courseObj : coursesOfferedArray) {
                var courseJson = (JSONObject) courseObj;
                var lectureID = (String) courseJson.get("CourseID");
                var courseName = (String) courseJson.get("CourseName");
                // var lecturer = ((Long) courseJson.get("Lecturer")).intValue();
                // var theoratical = ((Long) courseJson.get("Theoratical")).intValue();
                // var practical = ((Long) courseJson.get("Practical")).intValue();
                // var credit = ((Long) courseJson.get("Credit")).intValue();
                var quota = ((Long) courseJson.get("Quota")).intValue();
                var courseDayTimeLocation = (String) courseJson.get("CourseDayTimeLocation");
                // var courseStudents = ((Long) courseJson.get("CourseStudents")).intValue();
                var lecture = new Lecture(courseName, lectureID, quota, createCourseSession(courseDayTimeLocation));
                coursesOffered.add(lecture);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void calculateAvailableLectures() {
        var availableCoursesCopy = new ArrayList<Course>();
        for (var availableCourse : availableCourses) {
            availableCoursesCopy.add(availableCourse);
        }
        availableLectures.clear();
        try {
            for (var courseOffered : coursesOffered) {
                var courseIDOffered = courseOffered.getLectureID();
                for (var availableCourse : availableCoursesCopy) {
                    for (int i = 1; i < 20; i++) {
                        if ((availableCourse.getCourseID().concat("." + i)).equals(courseIDOffered)) {
                            var lecture = new Lecture(availableCourse.getCourseName(),
                                    courseIDOffered,
                                    availableCourse.getCredit(), availableCourse.getType(),
                                    availableCourse.getSemester());

                            lecture.setLectureID(courseIDOffered);
                            availableLectures.add(lecture);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void calculateAvailableLabs() {
        var availableLecturesCopy = new ArrayList<Lecture>();
        for (var availableCourse : availableLectures) {
            availableLecturesCopy.add(availableCourse);
        }
        availableLabs.clear();
        try {
            for (var courseOffered : coursesOffered) {
                var courseIDOffered = courseOffered.getLectureID();
                for (var availableLecture : availableLecturesCopy) {
                    for (int i = 1; i < 20; i++) {
                        if ((availableLecture.getLectureID().concat("." + i))
                                .equals(courseIDOffered)) {
                            var lab = new Lab(availableLecture.getCourseName(),
                                    courseIDOffered,
                                    availableLecture.getCredit(), availableLecture.getType(),
                                    availableLecture.getSemester());
                            lab.setLabID(courseIDOffered);
                            availableLabs.add(lab);
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void calculateAvailableCourses() {
        var allCourses = new ArrayList<Course>();
        var targetSemester = ((Student) (session.getUser())).getCurrentSemester();

        // Read courses from JSON file
        try {
            var coursesJson = "jsons/courses.json";
            var parser = new JSONParser();
            var obj = parser.parse(new FileReader(coursesJson));
            var coursesArray = (JSONArray) obj;

            // creating all courses and add them to availableCourses
            for (var courseObj : coursesArray) {
                var courseJson = (JSONObject) courseObj;
                var courseName = (String) courseJson.get("CourseName");
                var semester = ((Long) courseJson.get("Semester")).intValue();
                var courseID = ((String) courseJson.get("CourseID")).trim();
                var credit = ((Long) courseJson.get("Credit")).intValue();
                var courseType = (String) courseJson.get("Type");

                var course = new Course(courseName, courseID, credit, courseType, semester);
                allCourses.add(course);
            }

            // add optional and mandatory prerequisite course to each course by finding
            // their courseIDs
            for (var courseObj : coursesArray) {
                var courseJson = (JSONObject) courseObj;
                var courseID = ((String) courseJson.get("CourseID")).trim();
                var course = allCourses.stream().filter(c -> c.getCourseID().equals(courseID)).findFirst().get();

                var mandatoryPrerequisites = (JSONArray) courseJson.get("MandatoryPrerequisites");
                var optionalPrerequisites = (JSONArray) courseJson.get("OptionalPrerequisites");

                var mandatoryPrerequisiteList = new ArrayList<Course>();
                var optionalPrerequisiteList = new ArrayList<Course>();

                for (var mandatoryPrerequisite : mandatoryPrerequisites) {
                    var mandatoryPrerequisiteCourseID = ((String) mandatoryPrerequisite).trim();
                    var mandatoryPrerequisiteCourse = findCourseByID(allCourses, mandatoryPrerequisiteCourseID);

                    if (mandatoryPrerequisiteCourse != null) {
                        mandatoryPrerequisiteList.add(mandatoryPrerequisiteCourse);
                    } else {
                        System.err.println(
                                "Mandatory prerequisite course with ID " + mandatoryPrerequisiteCourseID
                                        + " not found.");
                    }
                }

                for (var optionalPrerequisite : optionalPrerequisites) {
                    var optionalPrerequisiteCourseID = ((String) optionalPrerequisite).trim();
                    var optionalPrerequisiteCourse = findCourseByID(allCourses, optionalPrerequisiteCourseID);

                    if (optionalPrerequisiteCourse != null) {
                        optionalPrerequisiteList.add(optionalPrerequisiteCourse);
                    } else {
                        System.err.println(
                                "Optional prerequisite course with ID " + optionalPrerequisiteCourseID + " not found.");
                    }
                }

                course.setMandatoryPrerequisite(mandatoryPrerequisiteList);
                course.setOptionalPrerequisite(optionalPrerequisiteList);
            }

            // clear availableCourses
            availableCourses.clear();

            // filter by target semester and add to availableCourses
            for (var course : allCourses) {
                if (course.getSemester() == targetSemester)
                    availableCourses.add(course);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // student information reading
        try {
            var stuId = session.getUser().getID();
            var studentJson = "jsons/student/" + stuId + ".json";
            var studentObj = new JSONParser().parse(new FileReader(studentJson));
            var studentJSONObject = (JSONObject) studentObj;
            var transcript = (JSONObject) studentJSONObject.get("Transcript");
            var allSemesterArray = (JSONArray) transcript.get("Semester");

            for (var semester : allSemesterArray) {
                var currentSemester = (JSONObject) semester;
                var currentCourseArray = (JSONArray) currentSemester.get("Courses");

                for (var course : currentCourseArray) {
                    var currentCourse = (JSONObject) course;
                    var courseID = ((String) currentCourse.get("CourseID")).trim();
                    var courseGrade = currentCourse.get("Grade");
                    // convert to float or double
                    if (courseGrade instanceof Long) {
                        courseGrade = ((Long) courseGrade).floatValue();
                    } else if (courseGrade instanceof Double) {
                        courseGrade = ((Double) courseGrade).floatValue();
                    }
                    var courseObj = findCourseByID(allCourses, courseID);
                    // if failed add to failedCourses
                    var availableCoursesCopy = new ArrayList<Course>();
                    for (var availableCourse : availableCourses) {
                        availableCoursesCopy.add(availableCourse);
                    }

                    if (courseObj != null) {
                        if ((Float) courseGrade <= 1) {
                            // check if that course is in availableCourses already
                            var isExists = false;
                            for (var availableCourse : availableCoursesCopy) {
                                if (availableCourse.getCourseID().equals(courseID)) {
                                    isExists = true;
                                    break;
                                }
                            }
                            if (!isExists) {
                                availableCourses.add(courseObj);
                            }

                            for (var availableCourse : availableCoursesCopy) {
                                var mandatoryPrerequisitesOfAvailableCourse = availableCourse
                                        .getMandatoryPrerequisite();

                                for (var mandatoryPrerequisite : mandatoryPrerequisitesOfAvailableCourse) {
                                    if (mandatoryPrerequisite.getCourseID().equals(courseID)) {
                                        availableCourses.remove(availableCourse);
                                    }
                                }
                            }
                        } else if ((Float) courseGrade >= 1) {
                            for (var availableCourse : availableCoursesCopy) {
                                if (availableCourse.getCourseID().equals(courseID)) {
                                    continue;
                                }
                            }
                            for (var availableCourse : availableCoursesCopy) {
                                var optionalPrerequisitesOfAvailableCourse = availableCourse.getOptionalPrerequisite();

                                for (var optionalPrerequisite : optionalPrerequisitesOfAvailableCourse) {
                                    if (optionalPrerequisite.getCourseID().equals(courseID)) {

                                        availableCourses.add(courseObj);
                                    }
                                }
                            }
                        }
                    } else {
                        System.err.println("Course with ID " + courseID + " not found.");
                    }
                }
            }
            // remove from availableCourses if that course already in selectedCourses
            var availableCoursesCopy = new ArrayList<Course>();
            for (var availableCourse : availableCourses) {
                availableCoursesCopy.add(availableCourse);
            }
            for (var availableCourse : availableCourses) {
                for (var selectedCourse : selectedCourses) {
                    if (selectedCourse.getCourseID().equals(availableCourse.getCourseID())) {
                        availableCoursesCopy.remove(availableCourse);
                    }
                }
            }
            availableCourses = availableCoursesCopy;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showAvailableLectures() {
        int courseNumber = 1;
        System.out.printf("%n%-8s%-13s%-70s%-8s%-15s%n", "Number", "CourseID", "CourseName", "Credit", "CourseType");
        System.out.printf(
                "--------------------------------------------------------------------------------------------------------------%n");

        for (var lecture : availableLectures) {

            System.out.printf("%-8s%-13s%-70s%-8s%-15s%n", courseNumber++, lecture.getLectureID(),
                    lecture.getCourseName(),
                    lecture.getCredit(),
                    lecture.getType().equals("E") ? "Elective" : "Mandatory");

        }
        System.out.println();
    }

    private boolean showAvailableLabs(int selectedIndex) {
        int courseNumber = 1;
        System.out.printf("%n%-8s%-13s%-70s%-8s%-15s%n", "Number", "CourseID", "CourseName", "Credit", "CourseType");
        System.out.printf(
                "--------------------------------------------------------------------------------------------------------------%n");
        var selectedLecture = availableLectures.get(selectedIndex - 1);

        for (var availableLab : availableLabs) {
            int lastIndex = availableLab.getLabID().lastIndexOf(".");
            var correspondingLectureId = availableLab.getLabID().substring(0, lastIndex);
            if (correspondingLectureId.equals(selectedLecture.getLectureID())) {
                System.out.printf("%-8s%-13s%-70s%-8s%-15s%n", courseNumber++, availableLab.getLabID(),
                        availableLab.getCourseName(), availableLab.getCredit(),
                        availableLab.getType().equals("E") ? "Elective" : "Mandatory");
            }
        }
        if (courseNumber == 1) {
            System.err.println("\n!!! Currently, there are no labs available for this lecture !!!\n");
            return false;
        }
        return true;
    }

    private void saveAvailableCourses(int selectedLectureIndex, int selectedLabIndex) {
        selectedCourses.add((Course) (availableLectures.get(selectedLectureIndex - 1)));
        selectedLectures.add(availableLectures.get(selectedLectureIndex - 1));
        selectedLabs.add(availableLabs.get(selectedLabIndex - 1));
        System.out.println("\nSelected Lecture: " + availableLectures.get(selectedLectureIndex - 1) + "\nSelected Lab: "
                + availableLabs.get(selectedLabIndex - 1));
    }

    private void saveAvailableCourses(int selectedLectureIndex) {
        selectedCourses.add((Course) (availableLectures.get(selectedLectureIndex - 1)));
        selectedLectures.add(availableLectures.get(selectedLectureIndex - 1));
        System.out.println("\nSelected Lecture: " + availableLectures.get(selectedLectureIndex - 1));
    }

}