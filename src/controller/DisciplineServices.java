package controller;

import java.util.UUID;

import model.Discipline;
import view.input.UniversalInfoInput;
import view.output.UImessage;

public class DisciplineServices {
    public static Discipline addDiscipline() {
        String name = UniversalInfoInput.receiveName(UImessage.askInput("discipline's name"));
        return new Discipline(UUID.randomUUID(), name);
    }
}
