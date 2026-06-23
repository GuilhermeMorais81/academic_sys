package view;

import java.time.LocalDate;

import javax.swing.JOptionPane;

import controller.validators.StudentValidators;

public class StudentInfoInput {
    public static String receiveSSN() {
        while(true) {
            String option = JOptionPane.showInputDialog(UImessage.askInput("Student's SSN"));
            if(StudentValidators.isValidSSN(option)) return option;
            else JOptionPane.showMessageDialog(null, UImessage.errorMessage("Incorrect SSN typed"));
        }
    }

    public static LocalDate receiveBirthDate() {
        while(true) {
            String option = JOptionPane.showInputDialog(UImessage.askInput("Student's Birth Date"));
            if(StudentValidators.isValidBirthDate(option)) return LocalDate.parse(option);
            else JOptionPane.showMessageDialog(null, UImessage.errorMessage("This birth date can not be used"));
        }
    }
}
