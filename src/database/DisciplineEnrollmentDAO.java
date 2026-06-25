package database;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import model.DisciplineEnrollment;
import model.enums.DisciplineEnrollmentStatus;

public class DisciplineEnrollmentDAO {

    private static DisciplineEnrollment mapResultSetToDisciplineEnroll(ResultSet rs) throws Exception {
        return new DisciplineEnrollment
        (
            rs.getObject("id_dis_enroll", java.util.UUID.class), 
            DisciplineDAO.query(rs.getObject("id_discipline", java.util.UUID.class)), 
            TypeConverter.parseBigDecToDouble(rs.getBigDecimal("first_avg")), 
            TypeConverter.parseBigDecToDouble(rs.getBigDecimal("second_avg")), 
            TypeConverter.parseBigDecToDouble(rs.getBigDecimal("final_avg")), 
            DisciplineEnrollmentStatus.parseNumberToStatus(rs.getShort("status"))
        );
    }

    public static void save(UUID courseEnrollmentId, DisciplineEnrollment disciplineEnrollment) throws Exception {
        String sql = "INSERT INTO t_dis_enroll VALUES (?, ?, ?, ?, ?, ?, ?)";

        try(var connection = ConnectionFactory.getConnection();
        var ps = connection.prepareStatement(sql)) {
            ps.setObject(1, courseEnrollmentId);
            ps.setObject(2, disciplineEnrollment.getId());
            ps.setObject(3, disciplineEnrollment.getFirstAvg(), java.sql.Types.DECIMAL);
            ps.setObject(4, disciplineEnrollment.getSecondAvg(), java.sql.Types.DECIMAL);
            ps.setObject(5, disciplineEnrollment.getFinalAvg(), java.sql.Types.DECIMAL);
            ps.setShort(6, disciplineEnrollment.getStatus().getNumber());
            ps.setObject(7, disciplineEnrollment.getDiscipline().getId());
            ps.executeUpdate();
        }
    } 

    public static List<DisciplineEnrollment> query(UUID courseEnrollmentId) throws Exception {
        String sql = "SELECT * FROM t_dis_enroll WHERE id_course_enroll = ?";
        var disciplineEnrollments = new ArrayList<DisciplineEnrollment>();
        try(var connection = ConnectionFactory.getConnection();
        var ps = connection.prepareStatement(sql)) {
            ps.setObject(1, courseEnrollmentId);
            try(ResultSet rs = ps.executeQuery()) {
                while(rs.next()) 
                    disciplineEnrollments.add(mapResultSetToDisciplineEnroll(rs));
                return disciplineEnrollments;
            }
        }
    }

    public static void update(List<DisciplineEnrollment> disciplineEnrollments) throws Exception {
        for(DisciplineEnrollment de : disciplineEnrollments) update(de);
    }

    public static void update(DisciplineEnrollment disciplineEnrollment) throws Exception {
        String sql = 
        "UPDATE t_dis_enroll SET first_avg = ?, second_avg = ?, final_avg = ?, status = ? WHERE id_dis_enroll = ?";
        try(var connection = ConnectionFactory.getConnection();
        var ps = connection.prepareStatement(sql)) {
            ps.setBigDecimal(1, BigDecimal.valueOf(disciplineEnrollment.getFirstAvg()));
            ps.setBigDecimal(2, BigDecimal.valueOf(disciplineEnrollment.getSecondAvg()));
            ps.setBigDecimal(3, BigDecimal.valueOf(disciplineEnrollment.getFinalAvg()));
            ps.setShort(4, disciplineEnrollment.getStatus().getNumber());
            ps.setObject(5, disciplineEnrollment.getId());
            ps.executeUpdate();
        }
    }
}
