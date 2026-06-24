package view;

import javax.swing.JOptionPane;

import database.CourseDAO;
import model.Course;

public class CourseInfoInput {
    public static Course receiveCourseByName() throws Exception {
        while(true) {
            String name = UniversalInfoInput.receiveName(UImessage.askInput("course's name"));
            var course = CourseDAO.query(new Course(name.trim().toLowerCase()));
            if(course != null) return course;
            else JOptionPane.showMessageDialog(null, UImessage.errorMessage("Course not found"));
        }
    }

    public static String receiveUniqueCourseName() throws Exception {
        while(true) {
            String name = UniversalInfoInput.receiveName(UImessage.askInput("course's name"));
            name = name.trim().toLowerCase();
            if(CourseDAO.query(new Course(name)) == null) return name;
            else JOptionPane.showMessageDialog(null, UImessage.errorMessage("Course name already registered"));
        }
    }
}
