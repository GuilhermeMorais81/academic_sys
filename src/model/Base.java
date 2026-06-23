package model;
import java.util.UUID;
import lombok.Getter;

@Getter
public abstract class Base {
    protected UUID id;
    

    public Base(UUID id) {
        this.id = id;
    }
}
