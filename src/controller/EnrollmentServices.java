package controller;

import java.util.List;

import javax.swing.JOptionPane;


import database.CourseEnrollmentDAO;
import model.DisciplineEnrollment;
import view.output.UImessage;
import view.output.EnrollmentOutputHandler;
import view.output.ErrorHandler;
import view.input.CourseInfoInput;
import view.input.DisciplineEnrollInfoInput;
import view.input.StudentInfoInput;

public class EnrollmentServices {
    public static void setEnrollmentAverages() {
        try {
            var student = StudentInfoInput.receiveStudentBySSN();
            var course = CourseInfoInput.receiveCourseByName();
            var courseEnrollment = CourseEnrollmentDAO.query(student, course);
            if(courseEnrollment != null) {
                if(!courseEnrollment.isAverageAlreadySet()) {
                    setDisciplineEnrollAverage(courseEnrollment.getDisciplineEnrollments());
                    courseEnrollment.setNewStatus();
                    CourseEnrollmentDAO.update(courseEnrollment);
                    JOptionPane.showMessageDialog(null, "Enrollment was successfully updated");
                }
                else JOptionPane.showMessageDialog(null, "Enrollment averages are already calculated.");
            }
            else JOptionPane.showMessageDialog(null, "Enrollment not found");
        }
        catch(Exception e) {
            ErrorHandler.showError(e);
        }

    }

    private static void setDisciplineEnrollAverage(List<DisciplineEnrollment> disciplineEnrollments) {
        for(DisciplineEnrollment de : disciplineEnrollments) {
            var firstAvg = 
            DisciplineEnrollInfoInput.receiveAvg(UImessage.askDisciplineEnrollAvg(de.getDiscipline().getName(), "First"));
            var secondAvg = 
            DisciplineEnrollInfoInput.receiveAvg(UImessage.askDisciplineEnrollAvg(de.getDiscipline().getName(), "Second"));
            de.calculateFinalAvg(firstAvg, secondAvg);
        }
    }

    public static void generateEnrollmentReport() {
        try {
            var student = StudentInfoInput.receiveStudentBySSN();
            var course = CourseInfoInput.receiveCourseByName();
            var courseEnrollment = CourseEnrollmentDAO.query(student, course);
            if(courseEnrollment != null) 
                JOptionPane.showMessageDialog(null, EnrollmentOutputHandler.generateCourseEnrollReport(courseEnrollment, course));
            else 
                JOptionPane.showMessageDialog(null, "Enrollment not found");
        }
        catch(Exception e) {
            ErrorHandler.showError(e);
        }
    }
}
