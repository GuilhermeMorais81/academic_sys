package model;

import java.util.List;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
public class Course extends Base {
    private String name;

    private @Setter List<Discipline> disciplines;

    public Course(UUID id, String name, List<Discipline> disciplines) {
        super(id);
        this.name = name;
        this.disciplines = disciplines;
    }

    public Course(String name) {
        super(null);
        this.name = name;
    }

    @Override
    public String toString() {
        return name + " | " + id;
    }
}
