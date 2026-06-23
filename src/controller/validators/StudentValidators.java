package controller.validators;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class StudentValidators {
    public static boolean isValidSSN(String input) {
        if(input.length() != 9) return false;
        return UniversalValidators.isJustNumbers(input);
    } 

    public static boolean isValidBirthDate(String input) {
        if(!UniversalValidators.tryParseDate(input)) return false;
        LocalDate birthDate = LocalDate.parse(input);
        long age = ChronoUnit.YEARS.between(birthDate, LocalDate.now());
        return age <= 120;
    }
}
