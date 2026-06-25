package controller;

import java.time.LocalDate;
import java.util.UUID;

import javax.swing.JOptionPane;
import database.CourseEnrollmentDAO;
import database.StudentDAO;
import model.CourseEnrollment;
import model.Student;
import model.enums.CourseEnrollmentStatus;
import view.CourseInfoInput;
import view.ErrorHandler;
import view.StudentInfoInput;
import view.UImessage;
import view.UniversalInfoInput;

public class StudentServices {
    public static void addStudent() {
        String name = UniversalInfoInput.receiveName(UImessage.askInput("student's name"));
        LocalDate birthDate = StudentInfoInput.receiveBirthDate();
        try {
            String SSN = StudentInfoInput.receiveUniqueSSN();
            StudentDAO.save(new Student(UUID.randomUUID(), name, birthDate, SSN));
            JOptionPane.showMessageDialog(null, name + " was successfully added.");
        }
        catch(Exception e) {
            ErrorHandler.showError(e);
        }
    }

    public static void enrollStudent() {
        try {
            var student = StudentInfoInput.receiveStudentBySSN();
            var course = CourseInfoInput.receiveCourseByName();
            if(!CourseEnrollmentDAO.studentIsAlreadyEnrolled(student, course)) {
                var courseEnrollment = new CourseEnrollment(
                    UUID.randomUUID(), 
                    course.getId(), 
                    student,
                    LocalDate.now(), 
                    CourseEnrollmentStatus.IN_COURSE
                );
                CourseEnrollmentDAO.save(courseEnrollment);
                JOptionPane.showMessageDialog(null, UImessage.enrollmentSucess(student.getName(), course.getName()));
            }
            else JOptionPane.showMessageDialog(null, "The student is already enrolled in this course.");
        }
        catch(Exception e) {
            ErrorHandler.showError(e);
        }
    }
}
