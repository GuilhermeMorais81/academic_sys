package database;

import java.sql.ResultSet;

import model.Course;
import model.CourseEnrollment;
import model.DisciplineEnrollment;
import model.Student;

public class CourseEnrollmentDAO {

    public static void save(CourseEnrollment courseEnrollment) throws Exception {
        String sql = "INSERT INTO t_course_enroll VALUES (?, ?, ?, ?, ?)";

        try(var connection = ConnectionFactory.getConnection();
        var ps = connection.prepareStatement(sql)) {
            ps.setObject(1, courseEnrollment.getCourse().getId());
            ps.setObject(2, courseEnrollment.getStudent().getId());
            ps.setObject(3, courseEnrollment.getId());
            ps.setObject(4, courseEnrollment.getCreationDate());
            ps.setShort(5, courseEnrollment.getStatus().getNumber());
            ps.executeUpdate();
            saveDisciplineEnrollments(courseEnrollment);
        }
    }

    private static void saveDisciplineEnrollments(CourseEnrollment courseEnrollment) throws Exception {
        for(DisciplineEnrollment de : courseEnrollment.getDisciplineEnrollments()) {
            DisciplineEnrollmentDAO.save(courseEnrollment.getId(), de);
        }
    }

    public static boolean query(Student student, Course course) throws Exception {
        String sql = "SELECT 1 FROM t_course_enroll WHERE id_student = ? AND id_course = ?";

        try(var connection = ConnectionFactory.getConnection();
        var ps = connection.prepareStatement(sql)) {
            ps.setObject(1, student.getId());
            ps.setObject(2, course.getId());
            try(ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }
}

