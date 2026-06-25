package view.input;

import javax.swing.JOptionPane;

import controller.validators.UniversalValidators;
import view.output.UImessage;

public class DisciplineEnrollInfoInput {
    public static Double receiveAvg(String message) {
        while(true) {
            String input = JOptionPane.showInputDialog(message);
            if(UniversalValidators.isValidNumber(input, 0.0, 10.0)) return Double.parseDouble(input);
            else JOptionPane.showMessageDialog(null, UImessage.errorMessage("Invalid average number"));
        }
    }
}
