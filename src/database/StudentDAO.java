package database;

import java.sql.PreparedStatement;

import model.Student;

public class StudentDAO {
    public static void save(Student student) throws Exception {
        String sql = "INSERT INTO t_student VALUES (?, ?, ?, ?)";
        
        try(var connection = ConnectionFactory.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setObject(1, student.getId());
            ps.setString(2, student.getName());
            ps.setObject(3, student.getBirthDate());
            ps.setString(4, student.getSSN());
            ps.executeUpdate();
        }
    }
}
