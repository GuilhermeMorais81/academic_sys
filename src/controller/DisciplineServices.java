package controller;

import java.util.UUID;

import model.Discipline;
import view.UImessage;
import view.UniversalInfoInput;

public class DisciplineServices {
    public static Discipline addDiscipline() {
        String name = UniversalInfoInput.receiveName(UImessage.askInput("discipline's name"));
        return new Discipline(UUID.randomUUID(), name);
    }
}
