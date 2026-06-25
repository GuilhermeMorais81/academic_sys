package controller;

import java.util.ArrayList;
import java.util.UUID;

import javax.swing.JOptionPane;

import database.CourseDAO;
import model.Course;
import model.Discipline;
import view.CourseInfoInput;
import view.ErrorHandler;
import view.UImessage;
import view.UniversalInfoInput;

public class CourseServices {
    public static void addCourses() {
        var disciplines = new ArrayList<Discipline>();
        try {
            String name = CourseInfoInput.receiveUniqueCourseName();
            int numDisciplines = UniversalInfoInput.receiveNumber(UImessage.askInput("number of disciplines of the course"));
            for(int i = 0; i < numDisciplines; i++) disciplines.add(DisciplineServices.addDiscipline());
            CourseDAO.save(new Course(UUID.randomUUID(), name, disciplines));
            JOptionPane.showMessageDialog(null, name + " course was successfully added.");
        }
        catch(Exception e) {
            ErrorHandler.showError(e);
        }
    }
}
