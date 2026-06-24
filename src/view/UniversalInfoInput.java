package view;

import javax.swing.JOptionPane;

import controller.validators.UniversalValidators;

public class UniversalInfoInput {

    public static int chooseMainMenuOption() {
        String[] options = {"Add students", "Add Courses", "Enroll Students", "Exit"};

        while(true) {
            String option = JOptionPane.showInputDialog(UImessage.menuMessage(options));
            if(UniversalValidators.isValidNumber(option, 1, options.length)) 
                return Integer.parseInt(option);
            else
                JOptionPane.showMessageDialog(null, UImessage.errorMessage("This option does not exist"));
        }
    }

    public static int receiveNumber(String message) {
        while(true) {
            String option = JOptionPane.showInputDialog(message);
            if(UniversalValidators.isValidNumber(option, 3, 30))
                return Integer.parseInt(option);
            else
                JOptionPane.showMessageDialog(null, UImessage.errorMessage("This number is too large or small"));
        }
    }

    public static String receiveName(String message) {
        while(true) {
            String option = JOptionPane.showInputDialog(message);
            if(UniversalValidators.isValidString(option, 160)) return option;
            else JOptionPane.showMessageDialog(null, UImessage.errorMessage("This name can not be used"));
        }
    }
}
