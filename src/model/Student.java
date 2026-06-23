package model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.UUID;
import lombok.Getter;

@Getter
public class Student extends Base {
    private String name;
    private LocalDate birthDate;
    private String SSN;

    public Student(UUID id, String name, LocalDate birthDate, String sSN) {
        super(id);
        this.name = name;
        this.birthDate = birthDate;
        SSN = sSN;
    }

    @Override
    public String toString() {
        return name + " | " + id + " | " + getAge() + " | " + SSN;
    }

    public long getAge() {
        return ChronoUnit.YEARS.between(birthDate, LocalDate.now());
    }
}
