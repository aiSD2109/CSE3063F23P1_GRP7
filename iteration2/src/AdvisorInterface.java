
import java.util.ArrayList;
import java.util.Scanner;

public class AdvisorInterface implements Schedule {

    Colors Colors = new Colors();

    private Scanner scanner;
    private Session session;
    private NotificationsInterface notificationsInt;
    private LoginInterface loginInt;
    private AdvisorCourseRegistrationInterface advCourseRegInt;

    public AdvisorInterface(Session session, LoginInterface loginInt) {
        this.session = session;
        this.loginInt = loginInt;
        advCourseRegInt = new AdvisorCourseRegistrationInterface(session, this);
    }

    // the terminal inteface for advisor
    public void advMenu() {

        while (true) {
            scanner = new Scanner(System.in);
            System.out.println(
                    Colors.getBOLD() + Colors.getRED() + "\n> Advisor Menu\n" + Colors.getRESET() + Colors.getRESET());
            System.out.println(Colors.getYELLOW() + "1" + Colors.getRESET() + ".   View Notifications");
            System.out.println(Colors.getYELLOW() + "2" + Colors.getRESET() + ".   View Weekly Schedule");
            System.out.println(Colors.getYELLOW() + "3" + Colors.getRESET() + ".   View Given Courses");
            System.out.println(Colors.getYELLOW() + "4" + Colors.getRESET() + ".   Course Registration System");
            System.out.println(Colors.getYELLOW() + "*" + Colors.getRESET() + ".   Logout");
            System.out.println(Colors.getYELLOW() + "x" + Colors.getRESET() + ".   Exit");

            System.out.print("\n" + Colors.getBLUE() + "--> " + Colors.getRESET() + "What do you want to do?   ");

            System.out.print(Colors.getBLUE());
            char caseToken = scanner.next().charAt(0);
            System.out.print(Colors.getRESET());

            switch (caseToken) {
                case '1': // view notifications
                    notificationsInt = new NotificationsInterface(session);
                    notificationsInt.notificationsMenu();
                    break;
                case '2': // view weekly schedule
                    showWeeklySchedule(calculateWeeklySchedule());
                    break;
                case '3': // view given courses
                    showGivenCourses(calculateWeeklySchedule());
                    break;
                case '4': // go to course registration system
                    advCourseRegInt.advRegMenu();
                    break;
                case '*': // logout
                    loginInt.logout();
                    break;
                case 'x': // exit
                    loginInt.exit();
                    break;
                default: // invalid input
                    System.out.println(Colors.getYELLOW() + "Invalid input! Please try again." + Colors.getRESET());
                    continue;
            }
        }
    }

    // it calculates the weekly schedule of advisor and stores it
    @Override
    public ArrayList<Course> calculateWeeklySchedule() {
        ArrayList<Course> weeklySchedule = ((Lecturer) session.getUser()).getGivenCourses();
        return weeklySchedule;
    }

    // it prints the weekly schedule of advisor
    @Override
    public void showWeeklySchedule(ArrayList<Course> courses) {

        scanner = new Scanner(System.in);

        // creating arraylists for each day of the week
        ArrayList<String> mondayCoursesID = new ArrayList<String>();
        ArrayList<String> tuesdayCoursesID = new ArrayList<String>();
        ArrayList<String> wednesdayCoursesID = new ArrayList<String>();
        ArrayList<String> thursdayCoursesID = new ArrayList<String>();
        ArrayList<String> fridayCoursesID = new ArrayList<String>();
        ArrayList<String> saturdayCoursesID = new ArrayList<String>();
        ArrayList<String> sundayCoursesID = new ArrayList<String>();

        ArrayList<String> mondayCoursesStartTime = new ArrayList<String>();
        ArrayList<String> tuesdayCoursesStartTime = new ArrayList<String>();
        ArrayList<String> wednesdayCoursesStartTime = new ArrayList<String>();
        ArrayList<String> thursdayCoursesStartTime = new ArrayList<String>();
        ArrayList<String> fridayCoursesStartTime = new ArrayList<String>();
        ArrayList<String> saturdayCoursesStartTime = new ArrayList<String>();
        ArrayList<String> sundayCoursesStartTime = new ArrayList<String>();

        ArrayList<String> mondayCoursesPlace = new ArrayList<String>();
        ArrayList<String> tuesdayCoursesPlace = new ArrayList<String>();
        ArrayList<String> wednesdayCoursesPlace = new ArrayList<String>();
        ArrayList<String> thursdayCoursesPlace = new ArrayList<String>();
        ArrayList<String> fridayCoursesPlace = new ArrayList<String>();
        ArrayList<String> saturdayCoursesPlace = new ArrayList<String>();
        ArrayList<String> sundayCoursesPlace = new ArrayList<String>();

        System.out.println(Colors.getRED() + Colors.getBOLD()
                + "\n>> Weekly Schedule\n"
                + Colors.getRESET() + Colors.getRESET());
        System.out.println(
                "------------------------------------------------------------------------------------------------------------------------------------------");

        System.out.printf("|  %-15s|  %-14s|  %-14s|  %-14s|  %-14s|  %-14s|  %-14s|  %-14s|%n", "", "Monday",
                "Tuesday", "Wednesday",
                "Thursday", "Friday", "Saturday", "Sunday");

        Course course;
        CourseSession courseSession;

        for (int i = 0; i < courses.size(); i++) {
            course = courses.get(i);
            courseSession = course.getCourseSession();

            for (int j = 0; j < courseSession.getCourseDay().size(); j++) {
                if (courseSession.getCourseDay().get(j).equals("Pazartesi")) {
                    if (course instanceof Lecture) {
                        mondayCoursesID.add(((Lecture) course).getLectureID());
                    } else {
                        mondayCoursesID.add(((Lab) course).getLabID());
                    }
                    mondayCoursesPlace.add(courseSession.getCoursePlace().get(j));
                    mondayCoursesStartTime.add(courseSession.getCourseStartTime().get(j));

                } else if (courseSession.getCourseDay().get(j).equals("Salı")) {
                    if (course instanceof Lecture) {
                        tuesdayCoursesID.add(((Lecture) course).getLectureID());
                    } else {
                        tuesdayCoursesID.add(((Lab) course).getLabID());
                    }

                    tuesdayCoursesPlace.add(courseSession.getCoursePlace().get(j));
                    tuesdayCoursesStartTime.add(courseSession.getCourseStartTime().get(j));

                } else if (courseSession.getCourseDay().get(j).equals("Çarşamba")) {
                    if (course instanceof Lecture) {
                        wednesdayCoursesID.add(((Lecture) course).getLectureID());
                    } else {
                        wednesdayCoursesID.add(((Lab) course).getLabID());
                    }
                    wednesdayCoursesPlace.add(courseSession.getCoursePlace().get(j));
                    wednesdayCoursesStartTime.add(courseSession.getCourseStartTime().get(j));

                } else if (courseSession.getCourseDay().get(j).equals("Perşembe")) {
                    if (course instanceof Lecture) {
                        thursdayCoursesID.add(((Lecture) course).getLectureID());
                    } else {
                        thursdayCoursesID.add(((Lab) course).getLabID());
                    }
                    thursdayCoursesPlace.add(courseSession.getCoursePlace().get(j));
                    thursdayCoursesStartTime.add(courseSession.getCourseStartTime().get(j));

                } else if (courseSession.getCourseDay().get(j).equals("Cuma")) {
                    if (course instanceof Lecture) {
                        fridayCoursesID.add(((Lecture) course).getLectureID());
                    } else {
                        fridayCoursesID.add(((Lab) course).getLabID());
                    }

                    fridayCoursesPlace.add(courseSession.getCoursePlace().get(j));
                    fridayCoursesStartTime.add(courseSession.getCourseStartTime().get(j));

                } else if (courseSession.getCourseDay().get(j).equals("Cumartesi")) {
                    if (course instanceof Lecture) {
                        saturdayCoursesID.add(((Lecture) course).getLectureID());
                    } else {
                        saturdayCoursesID.add(((Lab) course).getLabID());
                    }
                    saturdayCoursesPlace.add(courseSession.getCoursePlace().get(j));
                    saturdayCoursesStartTime.add(courseSession.getCourseStartTime().get(j));

                } else if (courseSession.getCourseDay().get(j).equals("Pazar")) {
                    if (course instanceof Lecture) {
                        sundayCoursesID.add(((Lecture) course).getLectureID());
                    } else {
                        sundayCoursesID.add(((Lab) course).getLabID());
                    }
                    sundayCoursesPlace.add(courseSession.getCoursePlace().get(j));
                    sundayCoursesStartTime.add(courseSession.getCourseStartTime().get(j));

                }
            }

        }
        String mondayCourses;
        String tuesdayCourses;
        String wednesdayCourses;
        String thursdayCourses;
        String fridayCourses;
        String saturdayCourses;
        String sundayCourses;

        String mondayCoursePlace;
        String tuesdayCoursePlace;
        String wednesdayCoursePlace;
        String thursdayCoursePlace;
        String fridayCoursePlace;
        String saturdayCoursePlace;
        String sundayCoursePlace;

        WeeklySchedule weeklySchedule = new WeeklySchedule();

        CourseSessionTimes courseSessionTimes = new CourseSessionTimes();

        for (int k = 0; k < courseSessionTimes.SESSION_START.size(); k++) {

            mondayCourses = weeklySchedule.printMondayCourses(mondayCoursesID, mondayCoursesStartTime,
                    courseSessionTimes.SESSION_START, k);
            tuesdayCourses = weeklySchedule.printTuesdayCourses(tuesdayCoursesID, tuesdayCoursesStartTime,
                    courseSessionTimes.SESSION_START,
                    k);
            wednesdayCourses = weeklySchedule.printWednesdayCourses(wednesdayCoursesID, wednesdayCoursesStartTime,
                    courseSessionTimes.SESSION_START, k);
            thursdayCourses = weeklySchedule.printThursdayCourses(thursdayCoursesID, thursdayCoursesStartTime,
                    courseSessionTimes.SESSION_START, k);
            fridayCourses = weeklySchedule.printFridayCourses(fridayCoursesID, fridayCoursesStartTime,
                    courseSessionTimes.SESSION_START, k);
            saturdayCourses = weeklySchedule.printSaturdayCourses(saturdayCoursesID, saturdayCoursesStartTime,
                    courseSessionTimes.SESSION_START, k);
            sundayCourses = weeklySchedule.printSundayCourses(sundayCoursesID, sundayCoursesStartTime,
                    courseSessionTimes.SESSION_START, k);

            mondayCoursePlace = weeklySchedule.printMondayCoursePlace(mondayCoursesPlace, mondayCoursesStartTime,
                    courseSessionTimes.SESSION_START, k);
            tuesdayCoursePlace = weeklySchedule.printTuesdayCoursePlace(tuesdayCoursesPlace, tuesdayCoursesStartTime,
                    courseSessionTimes.SESSION_START, k);
            wednesdayCoursePlace = weeklySchedule.printWednesdayCoursePlace(wednesdayCoursesPlace,
                    wednesdayCoursesStartTime,
                    courseSessionTimes.SESSION_START, k);
            thursdayCoursePlace = weeklySchedule.printThursdayCoursePlace(thursdayCoursesPlace,
                    thursdayCoursesStartTime,
                    courseSessionTimes.SESSION_START, k);
            fridayCoursePlace = weeklySchedule.printFridayCoursePlace(fridayCoursesPlace, fridayCoursesStartTime,
                    courseSessionTimes.SESSION_START, k);
            saturdayCoursePlace = weeklySchedule.printSaturdayCoursePlace(saturdayCoursesPlace,
                    saturdayCoursesStartTime,
                    courseSessionTimes.SESSION_START,
                    k);
            sundayCoursePlace = weeklySchedule.printSundayCoursePlace(sundayCoursesPlace, sundayCoursesStartTime,
                    courseSessionTimes.SESSION_START, k);

            if (mondayCourses != "" || tuesdayCourses != "" || wednesdayCourses != "" || thursdayCourses != ""
                    || fridayCourses != "" || saturdayCourses != "" || sundayCourses != "") {
                System.out.println(
                        "------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.printf("|  %-15s|  %-14s|  %-14s|  %-14s|  %-14s|  %-14s|  %-14s|  %-14s|%n",
                        "", mondayCourses,
                        tuesdayCourses, wednesdayCourses, thursdayCourses, fridayCourses, saturdayCourses,
                        sundayCourses);
                System.out.printf("|  %-15s|  %-14s|  %-14s|  %-14s|  %-14s|  %-14s|  %-14s|  %-14s|%n",
                        courseSessionTimes.SESSION_START.get(k) + " - " + courseSessionTimes.SESSION_END.get(k),
                        mondayCoursePlace, tuesdayCoursePlace, wednesdayCoursePlace, thursdayCoursePlace,
                        fridayCoursePlace, saturdayCoursePlace, sundayCoursePlace);
            }

        }
        System.out.println(
                "------------------------------------------------------------------------------------------------------------------------------------------\n");
        while (true) {
            System.out.println(Colors.getYELLOW() + "0" + Colors.getRESET() + ".  Back to Advisor Menu");
            System.out.print("\n" + Colors.getBLUE() + "--> " + Colors.getRESET() + "What do you want to do?   ");
            System.out.print(Colors.getBLUE());
            String caseTokenLine = scanner.nextLine();
            if (caseTokenLine.length() > 1) {
                System.out.println(
                        Colors.getYELLOW() + "\nInvalid input format! Please give a number!" + Colors.getRESET());
                continue;
            }
            char caseToken = caseTokenLine.charAt(0);
            System.out.print(Colors.getRESET());
            switch (caseToken) {
                case '0':
                    return;
                default:
                    System.out.println(Colors.getYELLOW() + "\nInvalid input! Please try again." + Colors.getRESET());
                    break;
            }
        }

    }

    // it prints the given courses of advisor
    public void showGivenCourses(ArrayList<Course> courses) {
        scanner = new Scanner(System.in);
        System.out.println(
                Colors.getRED() + Colors.getBOLD() + "\n>> Given Courses\n" + Colors.getRESET() + Colors.getRESET());

        Course course;
        ArrayList<String> courseID = new ArrayList<String>();

        System.out.println("---------------------------------------------------------");
        System.out.printf("|  %s%-13s%s|  %s%-35s%s  |%n", Colors.getYELLOW(), "Course ID", Colors.getRESET(),
                Colors.getYELLOW(),
                "Course Name", Colors.getRESET());

        for (int i = 0; i < courses.size(); i++) {
            course = courses.get(i);
            if (course instanceof Lecture) {
                courseID.add(((Lecture) course).getLectureID());
            } else {
                courseID.add(((Lab) course).getLabID());
            }

            System.out.println("---------------------------------------------------------");
            System.out.printf("|  %-13s|  %-35s  |%n", courseID.get(i), course.getCourseName());

        }
        System.out.println("---------------------------------------------------------\n");

        while (true) {
            System.out.println(Colors.getYELLOW() + "0" + Colors.getRESET() + ".  Back to Advisor Menu");
            System.out.print("\n" + Colors.getBLUE() + "--> " + Colors.getRESET() + "What do you want to do?   ");
            System.out.print(Colors.getBLUE());
            String caseTokenLine = scanner.nextLine();
            if (caseTokenLine.length() > 1) {
                System.out.println(
                        Colors.getYELLOW() + "\nInvalid input format! Please give a number!\n" + Colors.getRESET());
                continue;
            }
            char caseToken = caseTokenLine.charAt(0);
            System.out.print(Colors.getRESET());
            switch (caseToken) {
                case '0':
                    return;
                default:
                    System.out.println(Colors.getYELLOW() + "Invalid input! Please try again." + Colors.getRESET());
                    break;
            }
        }

    }

}