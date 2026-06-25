package view.output;

import javax.swing.JOptionPane;

public class ErrorHandler {
    public static void showError(Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, 
            "Sorry, try again later...", 
            "Unexpected Error", 
            JOptionPane.OK_OPTION
        );
    }
}
