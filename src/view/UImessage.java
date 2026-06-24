package view;


public class UImessage {
    public static String askInput(String complement) {
        return "Enter the " + complement;
    }

    public static String errorMessage(String complement) {
        return complement + ". Try again";
    }

    public static String menuMessage(String[] options) {
        var sb = new StringBuilder();
        int i = 1;
        for(String option: options) {
            sb.append(i + " - " + option + "\n");
            i++;
        }
        sb.append("\nSelect one of the options above:\n");
        return sb.toString();
    }

    public static String enrollmentSucess(String student, String course) {
        return student + " Was sucessfully enrolled in " + course;
    }
}
