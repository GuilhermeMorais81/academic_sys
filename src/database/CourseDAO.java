package database;

import java.sql.PreparedStatement;

import model.Course;

public class CourseDAO {
    public static void save(Course course) throws Exception {
        String sql = "INSERT INTO t_course VALUES (?, ?)";
        
        try(var connection = ConnectionFactory.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setObject(1, course.getId());
            ps.setString(2, course.getName());
            ps.executeUpdate();
        }
        DisciplineDAO.saveCourseDisciplines(course);
    }


}
