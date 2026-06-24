package model;

import java.util.UUID;
import lombok.Getter;

@Getter
public class Discipline extends Base {
    private String name;

    public Discipline(UUID id) {
        super(id);
    }

    public Discipline(UUID id, String name) {
        super(id);
        this.name = name;
    }

    @Override
    public String toString() {
        return name + " | " + id;
    }
}
