package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Course;
import model.Discipline;

public class DisciplineDAO {

    public static Discipline mapResultSetToDiscipline(ResultSet rs) throws Exception {
        var discipline = new Discipline(rs.getObject("id_discipline", java.util.UUID.class));
        return discipline;
    }

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

    public static List<Discipline> query(Course course) throws Exception {
        var disciplines = new ArrayList<Discipline>();
        String sql = "SELECT id_discipline FROM t_discipline WHERE id_course = ?";
        try(var connection = ConnectionFactory.getConnection();
        var ps = connection.prepareStatement(sql)) {
            ps.setObject(1, course.getId());
            try(ResultSet rs = ps.executeQuery()) {
                while(rs.next()) {
                    disciplines.add(mapResultSetToDiscipline(rs));
                }
                return disciplines;
            }
        }
    }
}
