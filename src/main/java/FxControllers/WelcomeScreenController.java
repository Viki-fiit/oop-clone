package FxControllers;

import Other.GlobalVariables;
import com.example.starhike.HelloApplication;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
/**
 *Controller for the welcome screen of the application.
 */

public class WelcomeScreenController implements Initializable {
    @FXML
    public Label weatherLabel;
    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;
    /**
     *Switches to the login screen when the login button is clicked.
     *@param event An action event that triggers the method.
     *@throws IOException If the FXML file for the login screen is not found.
     */
    @FXML
    public void switchToLogin(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
        root = fxmlLoader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    /**
     *Switches to the register screen when the register button is clicked.
     *@param event An action event that triggers the method.
     *@throws IOException If the FXML file for the register screen is not found.
     */
    public void switchToRegister(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("register.fxml"));
        root = fxmlLoader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     *Initializes the controller by setting the weather label and creating a pause transition that updates the label.
     *@param url The location used to resolve relative paths for the root object, or null if the location is not known.
     *@param resourceBundle The resources used to localize the root object, or null if the root object was not localized.
     */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PauseTransition wait = new PauseTransition(Duration.seconds(1));
        wait.setOnFinished((e) -> {
            weatherLabel.setText(String.valueOf(GlobalVariables.weatherLabel));
            wait.playFromStart();
        });
        wait.play();
    }


}