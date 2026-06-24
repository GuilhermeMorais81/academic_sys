package model.enums;

public enum DisciplineEnrollmentStatus {
    IN_COURSE((short) 0),
    APPROVED((short) 1),
    NOT_APPROVED((short) 2);

    private final short number;

    DisciplineEnrollmentStatus(short number) {
        this.number = number;
    }

    public short getNumber() {
        return number;
    }
}
