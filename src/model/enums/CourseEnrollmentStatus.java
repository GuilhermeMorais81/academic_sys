package model.enums;

public enum CourseEnrollmentStatus {
    IN_COURSE(0),
    APPROVED(1),
    NOT_APPROVED(2),
    WITHDRAWN(3);

    private final int number;

    CourseEnrollmentStatus(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}
