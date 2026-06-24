package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;

import model.Student;

public class StudentDAO {

    public static Student mapResultSetToStudent(ResultSet rs) throws Exception {
        var student = new Student
        (
            rs.getObject("id_student", java.util.UUID.class), 
            rs.getString("nm_student"), 
            rs.getObject("dt_birth", java.time.LocalDate.class), 
            rs.getString("ssn")
        );
        return student;
    }

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

    public static Student query(String SSN) throws Exception {
        String sql = "SELECT * FROM t_student WHERE SSN = ?";

        try(var connection = ConnectionFactory.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, SSN);
            try(ResultSet rs = ps.executeQuery()) {
                if(rs.next()) return mapResultSetToStudent(rs);
            }
        }
        return null;
    }

    public static Student query(UUID id) throws Exception {
        String sql = "SELECT * FROM t_student WHERE id_student = ?";

        try(var connection = ConnectionFactory.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setObject(1, id);
            try(ResultSet rs = ps.executeQuery()) {
                if(rs.next()) return mapResultSetToStudent(rs);
            }
        }
        return null;
    }
}
