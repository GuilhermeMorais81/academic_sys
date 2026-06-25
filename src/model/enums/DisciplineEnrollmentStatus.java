package model.enums;

public enum DisciplineEnrollmentStatus {
    IN_COURSE((short) 0),
    APPROVED((short) 1),
    NOT_APPROVED((short) 2);

    private final short number;

    DisciplineEnrollmentStatus(short number) {
        this.number = number;
    }

    public static DisciplineEnrollmentStatus parseNumberToStatus(short number) {
        for(DisciplineEnrollmentStatus des : values()) {
            if(des.number == number) return des;
        }
        throw new IllegalArgumentException("Invalid status number");
    }

    public short getNumber() {
        return number;
    }
}
