package Monitoring;

import javafx.scene.control.Alert;
/**
 *The NumberException class is a custom exception that is thrown when the user enters an invalid input for the duration of a hike.
 *It displays an error message in a JavaFX alert.
 */
public class NumberException extends Throwable {
    /**
     * Constructor for NumberException class that displays an error message in a JavaFX alert.
     */
    public NumberException(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("NumberException");
        alert.setContentText("Duration of hike has to be a number.");
        alert.show();
    }

}
