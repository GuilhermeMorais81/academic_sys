package controller;

import view.UniversalInfoInput;

public class UserServices {
    private static final int ADD_STUDENT = 1;
    private static final int ADD_COURSE = 2;
    private static final int EXIT = 3;
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
