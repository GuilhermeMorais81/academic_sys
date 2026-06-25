package controller;

import java.util.List;

import javax.swing.JOptionPane;

import database.CourseEnrollmentDAO;
import model.DisciplineEnrollment;
import view.CourseInfoInput;
import view.DisciplineEnrollInfoInput;
import view.ErrorHandler;
import view.StudentInfoInput;
import view.UImessage;

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
}
