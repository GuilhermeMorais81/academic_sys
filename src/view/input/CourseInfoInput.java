package view.input;

import javax.swing.JOptionPane;

import database.CourseDAO;
import model.Course;
import view.output.UImessage;

public class CourseInfoInput {
    public static Course receiveCourseByName() throws Exception {
        while(true) {
            String name = UniversalInfoInput.receiveName(UImessage.askInput("course's name"));
            var course = CourseDAO.query(name.trim().toLowerCase());
            if(course != null) return course;
            else JOptionPane.showMessageDialog(null, UImessage.errorMessage("Course not found"));
        }
    }

    public static String receiveUniqueCourseName() throws Exception {
        while(true) {
            String name = UniversalInfoInput.receiveName(UImessage.askInput("course's name"));
            name = name.trim().toLowerCase();
            if(CourseDAO.query(name) == null) return name;
            else JOptionPane.showMessageDialog(null, UImessage.errorMessage("Course name already registered"));
        }
    }
}
