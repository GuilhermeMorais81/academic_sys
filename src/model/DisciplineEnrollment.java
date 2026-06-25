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

    public void calculateFinalAvg(Double first, Double second) {
        firstAvg = first;
        secondAvg = second;
        finalAvg = (firstAvg + secondAvg) / 2;
        setNewStatus();
    }

    public boolean isAverageAlreadySet() {
        return status != DisciplineEnrollmentStatus.IN_COURSE;
    }

    private void setNewStatus() {
        if(firstAvg == null || finalAvg == null) throw new IllegalStateException("averages were not set");
        if(finalAvg >= 6) status = DisciplineEnrollmentStatus.APPROVED;
        else status = DisciplineEnrollmentStatus.NOT_APPROVED;
    }
}
