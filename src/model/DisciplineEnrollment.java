package model;

import java.util.UUID;

import model.enums.DisciplineEnrollmentStatus;

public class DisciplineEnrollment extends Base {
    private Discipline discipline;
    private double firstAvg;
    private double secondAvg;
    private double finalAvg;
    private DisciplineEnrollmentStatus status;

    public DisciplineEnrollment(UUID id, Discipline discipline, double firstAvg, double secondAvg,
    double finalAvg, DisciplineEnrollmentStatus status) {
        super(id);
        this.discipline = discipline;
        this.firstAvg = firstAvg;
        this.secondAvg = secondAvg;
        this.finalAvg = finalAvg;
        this.status = status;
    }
}
