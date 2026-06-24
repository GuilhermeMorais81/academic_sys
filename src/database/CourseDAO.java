package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;

import model.Course;

public class CourseDAO {

    public static Course mapResultSetToCourse(ResultSet rs) throws Exception {
        var course =  new Course
        (
            rs.getObject("id_course", java.util.UUID.class), 
            rs.getString("nm_course"), 
            null
        );
        course.setDisciplines(DisciplineDAO.query(course));
        return course;
    }

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

    public static Course query(String name) throws Exception {
        String sql = "SELECT * FROM t_course WHERE nm_course = ?";
        
        try(var connection = ConnectionFactory.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, name);
            try(ResultSet rs = ps.executeQuery()) {
                if(rs.next()) return mapResultSetToCourse(rs);
            }
        }
        return null;
    }

    public static Course query(UUID id) throws Exception {
        String sql = "SELECT * FROM t_course WHERE id_course = ?";
        
        try(var connection = ConnectionFactory.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setObject(1, id);
            try(ResultSet rs = ps.executeQuery()) {
                if(rs.next()) return mapResultSetToCourse(rs);
            }
        }
        return null;
    }
}
