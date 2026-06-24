package database;

import java.util.UUID;
import model.DisciplineEnrollment;

public class DisciplineEnrollmentDAO {
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


}
