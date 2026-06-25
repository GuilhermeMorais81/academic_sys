package controller.validators;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class UniversalValidators {
    public static boolean isValidString(String input, int maxLength) {
        if(input.length() > maxLength) return false;
        return !input.isBlank();
    }

    public static boolean tryParseDate(String dateString) {
        try {
            LocalDate.parse(dateString);
            return true;
        }
        catch(DateTimeParseException e) {
            return false;
        }
    }

    public static boolean isValidNumber(String input, int min, int max) {
        try {
            int parsed = Integer.parseInt(input);
            return parsed >= min && parsed <= max;
        }
        catch(NumberFormatException e) {
            return false;
        }
    }

    public static boolean isValidNumber(String input, Double min, Double max) {
        try {
            Double parsed = Double.parseDouble(input);
            return parsed >= min && parsed <= max;
        }
        catch(NumberFormatException e) {
            return false;
        }
    }

    public static boolean isJustNumbers(String str) {
        if(str.isBlank()) return false;
        for(int i  = 0; i < str.length(); i++) {
            if(!Character.isDigit(str.charAt(i))) return false;
        }
        return true;
    }
}
