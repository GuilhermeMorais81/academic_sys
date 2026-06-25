package database;

import java.sql.ResultSet;
import java.util.UUID;
import model.Course;
import model.CourseEnrollment;
import model.Discipline;
import model.DisciplineEnrollment;
import model.Student;
import model.enums.CourseEnrollmentStatus;
import model.enums.DisciplineEnrollmentStatus;

public class CourseEnrollmentDAO {

    private static CourseEnrollment mapResultSetToCourseEnrollment(ResultSet rs) throws Exception {
        
        var courseEnrollment = new CourseEnrollment(
            rs.getObject("id_course_enroll", java.util.UUID.class),
            rs.getObject("id_course", java.util.UUID.class), 
            StudentDAO.query(rs.getObject("id_student", java.util.UUID.class)), 
            rs.getObject("dt_creation", java.time.LocalDate.class), 
            CourseEnrollmentStatus.parseNumberToStatus(rs.getShort("status"))
        );
        courseEnrollment.setDisciplineEnrollments(DisciplineEnrollmentDAO.query(courseEnrollment.getId()));
        return courseEnrollment;
    }

    public static void save(CourseEnrollment courseEnrollment) throws Exception {
        String sql = "INSERT INTO t_course_enroll VALUES (?, ?, ?, ?, ?)";

        try(var connection = ConnectionFactory.getConnection();
        var ps = connection.prepareStatement(sql)) {
            ps.setObject(1, courseEnrollment.getCourseId());
            ps.setObject(2, courseEnrollment.getStudent().getId());
            ps.setObject(3, courseEnrollment.getId());
            ps.setObject(4, courseEnrollment.getCreationDate());
            ps.setShort(5, courseEnrollment.getStatus().getNumber());
            ps.executeUpdate();
            saveDisciplineEnrollments(courseEnrollment);
        }
    }

    private static void saveDisciplineEnrollments(CourseEnrollment courseEnrollment) throws Exception {
        var course = CourseDAO.query(courseEnrollment.getCourseId());
        for(Discipline discipline : course.getDisciplines()) {
            var de = new DisciplineEnrollment(
                UUID.randomUUID(), 
                discipline, 
                null, 
                null, 
                null, 
                DisciplineEnrollmentStatus.IN_COURSE
            );
            DisciplineEnrollmentDAO.save(courseEnrollment.getId(), de);
        }
    }

    public static boolean studentIsAlreadyEnrolled(Student student, Course course) throws Exception {
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

    public static CourseEnrollment query(Student student, Course course) throws Exception {
        String sql = "SELECT * FROM t_course_enroll WHERE id_student = ? AND id_course = ?";

        try(var connection = ConnectionFactory.getConnection();
        var ps = connection.prepareStatement(sql)) {
            ps.setObject(1, student.getId());
            ps.setObject(2, course.getId());
            try(ResultSet rs = ps.executeQuery()) {
                if(rs.next()) return mapResultSetToCourseEnrollment(rs);
            }
        }
        return null;
    }

    public static void update(CourseEnrollment courseEnrollment) throws Exception {
        String sql = "UPDATE t_course_enroll SET status = ? WHERE id_course_enroll = ?";
        try(var connection = ConnectionFactory.getConnection();
        var ps = connection.prepareStatement(sql)) {
            ps.setShort(1, courseEnrollment.getStatus().getNumber());
            ps.setObject(2, courseEnrollment.getId());
            ps.executeUpdate();
            DisciplineEnrollmentDAO.update(courseEnrollment.getDisciplineEnrollments());
        }
    }
}

