package model;

import java.util.List;
import java.util.UUID;
import lombok.Getter;

@Getter
public class Course extends Base {
    private String name;
    private List<Discipline> disciplines;

    public Course(UUID id, String name, List<Discipline> disciplines) {
        super(id);
        this.name = name;
        this.disciplines = disciplines;
    }
}
