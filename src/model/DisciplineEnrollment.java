package model;

import java.util.UUID;

import lombok.Getter;
import lombok.ToString;
import model.enums.DisciplineEnrollmentStatus;

@Getter
@ToString
public class DisciplineEnrollment extends Base {
    private Discipline discipline;
    private Double firstAvg;
    private Double secondAvg;
    private Double finalAvg;
    private DisciplineEnrollmentStatus status;

    public DisciplineEnrollment(UUID id, Discipline discipline, Double firstAvg, Double secondAvg,
    Double finalAvg, DisciplineEnrollmentStatus status) {
        super(id);
        this.discipline = discipline;
        this.firstAvg = firstAvg;
        this.secondAvg = secondAvg;
        this.finalAvg = finalAvg;
        this.status = status;
    }

    
}
