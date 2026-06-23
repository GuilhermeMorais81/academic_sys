package database;

import java.sql.PreparedStatement;

import model.Course;
import model.Discipline;

public class DisciplineDAO {
    public static void saveCourseDisciplines(Course course) throws Exception {
        for(Discipline discipline : course.getDisciplines())
            save(course, discipline);
    }

    public static void save(Course course, Discipline discipline) throws Exception {
        String sql = "INSERT INTO t_discipline VALUES (?, ?, ?)";
        
        try(var connection = ConnectionFactory.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setObject(1, course.getId());
            ps.setObject(2, discipline.getId());
            ps.setString(3, discipline.getName());
            ps.executeUpdate();
        }
    }
}
