package model.enums;

public enum DisciplineEnrollmentStatus {
    IN_COURSE(0),
    APPROVED(1),
    NOT_APPROVED(2);

    private final int number;

    DisciplineEnrollmentStatus(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}
