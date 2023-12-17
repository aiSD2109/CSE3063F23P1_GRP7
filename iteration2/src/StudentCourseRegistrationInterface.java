import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class StudentCourseRegistrationInterface {
    private ArrayList<Course> allCourses;
    private ArrayList<Lecture> allLectures;
    private ArrayList<Lab> allLabs;

    private ArrayList<Course> availableCourses;
    private ArrayList<Lecture> availableLectures;
    private ArrayList<Lab> availableLabs;

    private ArrayList<Course> selectedCourses;
    private ArrayList<Lecture> selectedLectures;
    private ArrayList<Lab> selectedLabs;

    private Session session;
    private StudentInterface studentInt;
    private Scanner scanner;

    public StudentCourseRegistrationInterface(Session session, StudentInterface studentInt) {
        this.session = session;
        this.studentInt = studentInt;

        this.availableCourses = new ArrayList<Course>();
        this.availableLectures = new ArrayList<Lecture>();
        this.availableLabs = new ArrayList<Lab>();

        this.selectedCourses = new ArrayList<Course>();
        this.selectedLectures = new ArrayList<Lecture>();
        this.selectedLabs = new ArrayList<Lab>();

        this.allCourses = new ArrayList<Course>();
        this.allLectures = new ArrayList<Lecture>();
        this.allLabs = new ArrayList<Lab>();
        getAllCourses(); // get all courses from courses.json
        setAllLecturesAndLabs(); // get all lectures and labs from CoursesOffered.json
        this.scanner = new Scanner(System.in);
    }

    public void stuRegMenu() {
        while (true) {
            // showing student information
            showStudentInf();
            System.out.println(Colors.BOLD+Colors.RED +
                    "\nStudent Course Registration System\n"
                    + Colors.RESET+Colors.RESET);
            System.out.println(Colors.YELLOW + "1" + Colors.RESET + ".   Selected Courses");
            System.out.println(Colors.YELLOW + "2" + Colors.RESET + ".   Available Courses");
            System.out.println(Colors.YELLOW + "0" + Colors.RESET + ".   Go back to Student Menu");
            System.out.print("\n" + Colors.BLUE + "--> " + Colors.RESET + "What do you want to do?   ");

            System.out.print(Colors.BLUE);
            var choice = scanner.next();
            System.out.print(Colors.RESET);

            switch (choice) {
                case "1":
                    selectedCoursesMenu();
                    break;
                case "2":
                    availableCoursesMenu();
                    break;
                case "0":
                    studentInt.stuMenu(); // this must be only return
                default:
                    System.out.println(Colors.YELLOW + "Invalid input! Please try again." + Colors.RESET);

            }
        }
    }

    private void showStudentInf() { // advisor information is wrong
        // printing user id and name
        System.out.println("\n" +
                "--------------------------------------------------------------------------------");
        System.out.printf("| %40s%-45s |%n",
                Colors.YELLOW + "Student ID - Name and Surname: " + Colors.RESET,
                session.getUser().getID() +
                        " - " + session.getUser().getName() + " " + session.getUser().getSurname());
        // printing user advisor
        System.out.printf("| %40s%-45s |%n", Colors.YELLOW + "Advisor: " + Colors.RESET,
                ((Student) (session.getUser())).getAdvisor().getName() + " " +
                        ((Student) (session.getUser())).getAdvisor().getSurname());

        System.out.printf("| %40s%-45s |%n", Colors.YELLOW + "Semester: " + Colors.RESET,
                ((Student) (session.getUser())).getCurrentSemester());
        System.out.println(
                "--------------------------------------------------------------------------------");

    }

    private void selectedCoursesMenu() {
        while (true) {
            showStudentInf();
            System.out.println(Colors.BOLD +Colors.RED+
                    "\nSelected Course Menu\n" + Colors.RESET+Colors.RESET);
            System.out.println(Colors.YELLOW + "1" + Colors.RESET + ".   Show selected courses");
            System.out.println(Colors.YELLOW + "2" + Colors.RESET + ".   Delete selected courses");
            System.out.println(Colors.YELLOW + "3" + Colors.RESET + ".   Send registration request");
            System.out
                    .println(Colors.YELLOW + "0" + Colors.RESET + ".   Go back to Student Course Registration System");
            System.out.print("\n" + Colors.BLUE + "--> " + Colors.RESET + "What do you want to do?   ");

            System.out.print(Colors.BLUE);
            var choice = scanner.next();

            System.out.print(Colors.RESET);
            if (selectedCourses.size() == 0) {
                System.out.println(Colors.YELLOW + "\nAt present, no courses have been selected!" + Colors.RESET);
                System.out.println("You will be " + Colors.GREEN + "redirected" + Colors.RESET
                        + " to Student Course Registration System");
                System.out.println("You can add courses from Available Courses Menu\n");
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
                    System.out.println(Colors.YELLOW + "Invalid input! Please try again." + Colors.RESET);
            }
        }
    }

    private void showSelectedCourses() { // lecturer must be added
        int courseNumber = 1;
        System.out.println(Colors.BOLD +Colors.RED+
                "\nSelected Course Menu"
                + Colors.RESET+Colors.RESET);
        System.out.println(
                "\n-----------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-8s | %-13s | %-50s| %-50s | %-8s| %-15s |%n", "Number", "CourseID", "CourseName",
                "Lecturer", "Credit", "CourseType");
        System.out.println(
                "-----------------------------------------------------------------------------------------------------------------------------------------------------------------");

        for (var lecture : selectedLectures) {

            System.out.printf("| %-8s | %-13s | %-50s| %-50s | %-8s| %-15s |%n", courseNumber++, lecture.getLectureID(),
                    lecture.getCourseName(), lecture.getLecturer(),
                    lecture.getCredit(),
                    lecture.getType().equals("E") ? "Elective" : "Mandatory");

        }
        System.out.println(
                "-----------------------------------------------------------------------------------------------------------------------------------------------------------------\n");

       
        System.out
                .println(Colors.YELLOW + "0" + Colors.RESET
                        + ".  Go back to the Selected Course Menu");
        System.out.print(Colors.BLUE + "\n--> " + Colors.RESET + "What do you want to do?   ");

        System.out.print(Colors.BLUE);
        char choice1 = scanner.next().charAt(0);
        System.out.print(Colors.RESET);

        if (choice1 == '0') {
            return;
        }
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
                    showAvailableLectures();
                    if (selectedCourses.size() == 5) { // if student selected 5 courses already then return
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

                    if (input.equals("0")) { // if input is 0 then return to available courses menu
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

                        if (input.equals("0")) { // if input is 0 then return to available courses menu
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

    private void calculateAvailableLectures() {
        availableLectures.clear();
        try {
            for (var lecture : allLectures) {
                for (var availableCourse : availableCourses) {
                    if (lecture.getCourseID().equals(availableCourse.getCourseID())) {
                        availableLectures.add(lecture);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void calculateAvailableLabs(Lecture selectedLecture) {
        availableLabs.clear();
        try {
            for (var lab : allLabs) {
                for (int i = 1; i < 20; i++) { // 20 is the maximum number of labs for a lecture
                    // (e.g. CSE101.1.1, CSE101.1.2, ... CSE101.1.19, CSE101.1.20 etc)
                    if (selectedLecture.getLectureID().concat("." + i).equals(lab.getLabID())) {
                        availableLabs.add(lab);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getAllCourses() {
        try {
            // this file contains all courses with their properties
            // such as course name, course id, credit, course type, semester, mandatory and
            // optional prerequisites
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

                var mandatoryPrerequisites = (JSONArray) courseJson.get("MandatoryPrerequisites");
                var optionalPrerequisites = (JSONArray) courseJson.get("OptionalPrerequisites");

                var mandatoryPrerequisiteList = new ArrayList<Course>();
                var optionalPrerequisiteList = new ArrayList<Course>();

                for (var mandatoryPrerequisite : mandatoryPrerequisites) {
                    var mandatoryPrerequisiteCourseID = ((String) mandatoryPrerequisite).trim();
                    var mandatoryPrerequisiteCourse = findCourseByID(allCourses, mandatoryPrerequisiteCourseID);

                    if (mandatoryPrerequisiteCourse != null) {
                        mandatoryPrerequisiteList.add(mandatoryPrerequisiteCourse);
                    }
                }

                for (var optionalPrerequisite : optionalPrerequisites) {
                    var optionalPrerequisiteCourseID = ((String) optionalPrerequisite).trim();
                    var optionalPrerequisiteCourse = findCourseByID(allCourses, optionalPrerequisiteCourseID);

                    if (optionalPrerequisiteCourse != null) {
                        optionalPrerequisiteList.add(optionalPrerequisiteCourse);
                    }
                }

                var course = new Course(courseName, courseID, credit, courseType, semester);
                course.setMandatoryPrerequisite(mandatoryPrerequisiteList);
                course.setOptionalPrerequisite(optionalPrerequisiteList);
                allCourses.add(course);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setAllLecturesAndLabs() {
        try {
            // this file contains additional information about courses that are not in the
            // courses.json file
            // such as course session, lecturer, theoric, practice, course students
            // and we create detailed lecture and lab objects with this information
            var coursesOfferedJson = "jsons/CoursesOffered.json";
            var parser = new JSONParser();
            var obj = parser.parse(new FileReader(coursesOfferedJson));
            var coursesOfferedArray = (JSONArray) obj;

            for (var courseObj : coursesOfferedArray) {
                var courseJson = (JSONObject) courseObj;
                var courseID = (String) courseJson.get("CourseID");
                var courseName = (String) courseJson.get("CourseName");
                var quota = ((Long) courseJson.get("Quota")).intValue();
                var courseDayTimeLocation = (String) courseJson.get("CourseDayTimeLocation");
                var lecturer = (String) courseJson.get("Lecturer");
                var theoric = ((Long) courseJson.get("Theoric")).intValue();
                var practice = ((Long) courseJson.get("Practice")).intValue();
                var courseStudents = ((Long) courseJson.get("CourseStudents")).intValue();

                var dotCount = courseID.length() - courseID.replace(".", "").length();
                if (dotCount == 1) {
                    var courseSession = createCourseSession(courseDayTimeLocation);
                    var lecture = new Lecture(courseName, courseID, quota, courseSession);
                    var correspondingCourse = findCourseByID(allCourses, lecture.getCourseID());

                    if (correspondingCourse != null) { // if course is found
                        // initialize lecture object with corresponding course object properties
                        correspondingCourse.setCourseSession(courseSession);
                        lecture.setType(correspondingCourse.getType());
                        lecture.setCredit(correspondingCourse.getCredit());
                        lecture.setSemester(correspondingCourse.getSemester());
                        lecture.setLecturer(lecturer);
                        lecture.setTheoric(theoric);
                        lecture.setPractice(practice);
                        lecture.setCourseStudents(courseStudents);
                        lecture.setMandatoryPrerequisite(correspondingCourse.getMandatoryPrerequisite());
                        lecture.setOptionalPrerequisite(correspondingCourse.getOptionalPrerequisite());
                        allLectures.add(lecture);
                    }
                } else if (dotCount == 2) {
                    var courseSession = createCourseSession(courseDayTimeLocation);
                    var lab = new Lab(courseName, courseID, quota, courseSession);
                    var correspondingCourse = findCourseByID(allCourses, lab.getCourseID());

                    if (correspondingCourse != null) {
                        // initialize lab object with corresponding course object properties
                        correspondingCourse.setCourseSession(courseSession);
                        lab.setType(correspondingCourse.getType());
                        lab.setCredit(correspondingCourse.getCredit());
                        lab.setSemester(correspondingCourse.getSemester());
                        lab.setLecturer(lecturer);
                        lab.setTheoric(theoric);
                        lab.setPractice(practice);
                        lab.setCourseStudents(courseStudents);
                        lab.setMandatoryPrerequisite(correspondingCourse.getMandatoryPrerequisite());
                        lab.setOptionalPrerequisite(correspondingCourse.getOptionalPrerequisite());
                        allLabs.add(lab);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void calculateAvailableCourses() {
        // get current semester of current student
        var targetSemester = ((Student) (session.getUser())).getCurrentSemester();
        // get last gano from transcript
        var gano = ((Student) (session.getUser())).getTranscript().getGano().get(targetSemester - 2);

        availableCourses.clear();
        for (var course : allCourses) {
            if (gano >= 3 && course.getSemester() >= targetSemester) { // if gano is greater than 3 then student can
                                                                       // take courses from next semesters
                availableCourses.add(course);
            } else if (course.getSemester() == targetSemester) {
                availableCourses.add(course);
            }
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
                var currentCourseArray = (JSONArray) currentSemester.get("Courses"); // get courses of current semester

                for (var course : currentCourseArray) { // iterate over courses of current semester for checking
                                                        // grades of courses and add them to available courses according
                                                        // to whether they are passed or failed
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
                    var availableCoursesCopy = new ArrayList<Course>(); // create copy of available courses for
                                                                        // preventing concurrent modification
                    for (var availableCourse : availableCourses) {
                        availableCoursesCopy.add(availableCourse);
                    }

                    if (courseObj != null) {
                        if ((Float) courseGrade <= 1) { // if failed
                            // check if that course is in availableCourses already
                            var isExists = false;
                            for (var availableCourse : availableCoursesCopy) {
                                if (availableCourse.getCourseID().equals(courseID)) {
                                    isExists = true;
                                    break;
                                }
                            }
                            if (!isExists) { // if course is not in availableCourses then add it
                                availableCourses.add(courseObj);
                            }

                            for (var availableCourse : availableCoursesCopy) {
                                var mandatoryPrerequisitesOfAvailableCourse = availableCourse
                                        .getMandatoryPrerequisite(); // get mandatory prerequisites of current available
                                                                     // courses

                                for (var mandatoryPrerequisite : mandatoryPrerequisitesOfAvailableCourse) {
                                    // iterate over mandatory prerequisites of current available courses and remove
                                    // them if they are failed in current semester (e.g. if CSE101 is failed then
                                    // remove CSE102 from available courses)
                                    if (mandatoryPrerequisite.getCourseID().equals(courseID)) {
                                        availableCourses.remove(availableCourse);
                                    }
                                }
                            }
                        } else if ((Float) courseGrade >= 1) { // if passed
                            for (var availableCourse : availableCoursesCopy) {
                                if (availableCourse.getCourseID().equals(courseID)) {
                                    continue;
                                }
                            }
                            for (var availableCourse : availableCoursesCopy) {
                                var optionalPrerequisitesOfAvailableCourse = availableCourse.getOptionalPrerequisite();
                                // iterate over optional prerequisites of current available courses and add them
                                // if they are passed in current semester (e.g. if CSE101 is passed then add
                                // CSE102 to available courses)
                                for (var optionalPrerequisite : optionalPrerequisitesOfAvailableCourse) {
                                    if (optionalPrerequisite.getCourseID().equals(courseID)) {
                                        availableCourses.add(courseObj);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (availableCourses.size() == 0) {
                System.err.println("\n!!! Currently, there are no courses available !!!\n");
                return;
            }

            if (selectedCourses.size() > 0) {
                // create copy of available courses for preventing concurrent modification
                var availableCoursesCopy = new ArrayList<Course>();
                for (var availableCourse : availableCourses) {
                    availableCoursesCopy.add(availableCourse);
                }
                // remove selected courses from available courses list
                for (var availableCourse : availableCoursesCopy) {
                    for (var selectedCourse : selectedCourses) {
                        if (selectedCourse.getCourseID().equals(availableCourse.getCourseID())) {
                            availableCourses.remove(availableCourse);
                        }
                    }
                }
            }

            calculateAvailableLectures();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showAvailableLectures() {// lecturer must be added
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

        if (courseNumber == 1) {
            System.err.println("\n!!! Currently, there are no lectures available !!!\n");
            return;
        }
        System.out.println();
    }

    private boolean showAvailableLabs(int selectedIndex) {// lecturer must be added
        int courseNumber = 1;
        System.out.printf("%n%-8s%-13s%-70s%-8s%-15s%n", "Number", "CourseID", "CourseName", "Credit", "CourseType");
        System.out.printf(
                "--------------------------------------------------------------------------------------------------------------%n");
        var selectedLecture = availableLectures.get(selectedIndex - 1);

        calculateAvailableLabs(selectedLecture);

        for (var availableLab : availableLabs) {
            System.out.printf("%-8s%-13s%-70s%-8s%-15s%n", courseNumber++, availableLab.getLabID(),
                    availableLab.getCourseName(),
                    availableLab.getCredit(),
                    availableLab.getType().equals("E") ? "Elective" : "Mandatory");
        }
        if (courseNumber == 1) {
            System.err.println("\n!!! Currently, there are no labs available for this lecture !!!\n");
            return false;
        }
        return true;
    }

    private void saveAvailableCourses(int selectedLectureIndex, int selectedLabIndex) {
        selectedCourses.add((Course) (availableLectures.get(selectedLectureIndex - 1))); // cast lecture to course
        selectedLectures.add(availableLectures.get(selectedLectureIndex - 1)); // add lecture to selected lectures
        selectedLabs.add(availableLabs.get(selectedLabIndex - 1)); // add lab to selected labs
        System.out.println("\nSelected Lecture: " + availableLectures.get(selectedLectureIndex - 1) + "\nSelected Lab: "
                + availableLabs.get(selectedLabIndex - 1)); // print selected lecture and lab together
    }

    private void saveAvailableCourses(int selectedLectureIndex) { // if there is no lab
        selectedCourses.add((Course) (availableLectures.get(selectedLectureIndex - 1))); // cast lecture to course
        selectedLectures.add(availableLectures.get(selectedLectureIndex - 1)); // add lecture
        System.out.println("\nSelected Lecture: " + availableLectures.get(selectedLectureIndex - 1)); // print selected
                                                                                                      // lecture
    }

}