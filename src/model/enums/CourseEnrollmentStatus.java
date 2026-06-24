package model.enums;

public enum CourseEnrollmentStatus {
    IN_COURSE((short) 0),
    APPROVED((short) 1),
    NOT_APPROVED((short) 2),
    WITHDRAWN((short) 3);

    private final short number;

    CourseEnrollmentStatus(short number) {
        this.number = number;
    }

    public static CourseEnrollmentStatus parseNumberToStatus(short number) {
        for(CourseEnrollmentStatus ces : values()) {
            if(ces.number == number) return ces;
        }
        throw new IllegalArgumentException("Invalid status number");
    } 

    public short getNumber() {
        return number;
    }
}
