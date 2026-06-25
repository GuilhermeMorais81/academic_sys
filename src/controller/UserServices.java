package controller;

import view.input.UniversalInfoInput;

public class UserServices {
    private static final int ADD_STUDENT = 1;
    private static final int ADD_COURSE = 2;
    private static final int ENROLL_STUDENT = 3;
    private static final int SET_ENROLLMENT_AVG = 4;
    private static final int GEN_ENROLL_REPORT = 5;
    private static final int EXIT = 6;
    private static boolean continueApp = true;

    public static void directToServices() {
        while(continueApp) {
            switch (UniversalInfoInput.chooseMainMenuOption()) {
                case ADD_STUDENT:
                    StudentServices.addStudent();
                    break;
                case ADD_COURSE:
                    CourseServices.addCourses();
                    break;
                case ENROLL_STUDENT:
                    StudentServices.enrollStudent();
                    break;
                case SET_ENROLLMENT_AVG:
                    EnrollmentServices.setEnrollmentAverages();
                    break;
                case GEN_ENROLL_REPORT:
                    EnrollmentServices.generateEnrollmentReport();
                    break;
                case EXIT:
                    stopApp();
                    break;
            }
        }
    }

    public static void stopApp() {
        continueApp = false;
    }
}
