package controller;

import java.util.ArrayList;
import java.util.UUID;

import javax.swing.JOptionPane;

import database.CourseDAO;
import model.Course;
import model.Discipline;
import view.ErrorHandler;
import view.UImessage;
import view.UniversalInfoInput;

public class CourseServices {
    public static void addCourses() {
        String name = UniversalInfoInput.receiveName(UImessage.askInput("course's name"));
        int numDisciplines = UniversalInfoInput.receiveNumber(UImessage.askInput("number of disciplines of the course"));
        var disciplines = new ArrayList<Discipline>();
        for(int i = 0; i < numDisciplines; i++) disciplines.add(DisciplineServices.addDiscipline());
        try {
            CourseDAO.save(new Course(UUID.randomUUID(), name, disciplines));
            JOptionPane.showMessageDialog(null, name + "course was successfully added.");
        }
        catch(Exception e) {
            ErrorHandler.showError(e);
        }
    }
}
