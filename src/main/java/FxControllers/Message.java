package FxControllers;

import Datasets.UserDataset;
import Monitoring.Check;
import Other.GlobalVariables;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
/**
 *The Message interface provides methods for displaying various types of alerts and messages to the user.
 */
public interface Message {
    /**
     *Displays an information alert that thanks the user for using StarHike and wishes them to stay safe.
     */
    default void goodBye(){
        Alert window = new Alert(Alert.AlertType.INFORMATION);
        window.setTitle("Thank you <3");
        window.setContentText("Thank you for using StarHike.\nStay safe.");
        window.show();
    }
    /**
     *Displays an information alert that thanks the user for registering with StarHike.
     */
    default void register(){
        Alert window = new Alert(Alert.AlertType.INFORMATION);
        window.setTitle("Thank you <3");
        window.setContentText("Thank you for registering with StarHike!");
        window.show();
    }
    /**
     *Displays a warning alert that advises the user to always check the weather before going on a hike.
     */

    default void warning(){
        Alert window = new Alert(Alert.AlertType.WARNING);
        window.setTitle("Be careful!");
        window.setContentText("Please always check the weather before going on a hike!");
        window.show();
    }
    /**
     *Creates a confirmation alert that asks the user if they want to continue logging out.
     *@return an Alert object that can be displayed to the user.
     */
    default Alert loggingOut(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Close");
        alert.setHeaderText("You're about to close the app </3 ");
        alert.setContentText("Do you want to continue?");
        return alert;
    }
}
