package controller;

import java.time.LocalDate;
import java.util.UUID;

import javax.swing.JOptionPane;

import database.StudentDAO;
import model.Student;
import view.ErrorHandler;
import view.StudentInfoInput;
import view.UImessage;
import view.UniversalInfoInput;

public class StudentServices {
    public static void addStudent() {
        String name = UniversalInfoInput.receiveName(UImessage.askInput("student's name"));
        LocalDate birthDate = StudentInfoInput.receiveBirthDate();
        String SSN = StudentInfoInput.receiveSSN();
        try {
            StudentDAO.save(new Student(UUID.randomUUID(), name, birthDate, SSN));
            JOptionPane.showMessageDialog(null, name + " was successfully added.");
        }
        catch(Exception e) {
            ErrorHandler.showError(e);
        }
    }
}
