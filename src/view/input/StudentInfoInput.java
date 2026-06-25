package view.input;

import java.time.LocalDate;

import javax.swing.JOptionPane;

import controller.validators.StudentValidators;
import database.StudentDAO;
import model.Student;
import view.output.UImessage;

public class StudentInfoInput {
    public static String receiveSSN() {
        while(true) {
            String option = JOptionPane.showInputDialog(UImessage.askInput("Student's SSN"));
            if(StudentValidators.isValidSSN(option)) return option;
            else JOptionPane.showMessageDialog(null, UImessage.errorMessage("Incorrect SSN typed"));
        }
    }

    public static String receiveUniqueSSN() throws Exception {
        while(true) {
            String input = receiveSSN();
            if(StudentDAO.query(input) == null) return input;
            else JOptionPane.showMessageDialog(null, UImessage.errorMessage("SSN already registered"));
        }
    }

    public static LocalDate receiveBirthDate() {
        while(true) {
            String option = JOptionPane.showInputDialog(UImessage.askInput("Student's Birth Date"));
            if(StudentValidators.isValidBirthDate(option)) return LocalDate.parse(option);
            else JOptionPane.showMessageDialog(null, UImessage.errorMessage("This birth date can not be used"));
        }
    }

    public static Student receiveStudentBySSN() throws Exception {
        while(true) {
            String SSN = StudentInfoInput.receiveSSN();
            var student = StudentDAO.query(SSN);
            if(student != null) return student;
            else JOptionPane.showMessageDialog(null, UImessage.errorMessage("Student not found"));
        }
    }
}
