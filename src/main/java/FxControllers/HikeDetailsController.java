package FxControllers;

import Hike.BaseHike;
import Other.GlobalVariables;
import User.BaseUser;
import com.example.starhike.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class HikeDetailsController implements CancelController{
    @FXML
    private Label warningLabel;
    @FXML
    private Label hikeNameLabel;
    @FXML
    private Label durationLabel;
    @FXML
    private Label typeLabel;
    @FXML
    private Label difficultyLabel;
    @FXML
    private Label AVLLabel;
    @FXML
    private Label weatherLabel;
    @FXML
    private Button backBttn;

    public Stage stage;
    public Scene scene;
    public Parent root;

    private BaseHike hikedetail;
    private BaseUser userdetail;

    /**
     * Sets the BaseUser object that is being edited in the UI.
     *
     * @param user the BaseUser object being edited
     */
    public void setUser(BaseUser user) {
        userdetail = user;
    }

    /**
     * Sets the BaseHike object that is being edited in the UI and calls the setAll() method to populate the UI fields with the hike's current values.
     *
     * @param hike the BaseUser object being edited
     */
    public void setHikedetail(BaseHike hike) {
        hikedetail = hike;
        setDetails();
    }
    /**
     *This method updates the GUI components to display the information of a hike being edited.
     */
    public void setDetails() {
        hikeNameLabel.setText(hikedetail.getNAME());
        durationLabel.setText(hikedetail.getMin() + " min");
        typeLabel.setText("Types: " + GlobalVariables.getHikeTypes(hikedetail.getType()).get(0));
        if (hikedetail.getType().size() > 1) {
            for (int i = 1; i < GlobalVariables.getHikeTypes(hikedetail.getType()).size() - 1; i++) {
                appendText(typeLabel, GlobalVariables.getHikeTypes(hikedetail.getType()).get(i));
            }
        }
        difficultyLabel.setText(GlobalVariables.getHikeLvl(hikedetail.getDifficulty()));
        AVLLabel.setText(String.valueOf(hikedetail.getAVL()));
        weatherLabel.setText("Weathers: " + GlobalVariables.getWeather(hikedetail.getWeatherOK().get(0)));
        if (hikedetail.getWeatherOK().size() > 2) {
            for (int i = 1; i < hikedetail.getWeatherOK().size() - 1; i++) {
                appendText(weatherLabel, GlobalVariables.getWeather(hikedetail.getWeatherOK().get(i)));
            }
        }
        if (hikedetail.getDifficulty() > userdetail.getLvl()) {
            warningLabel.setText("THIS HIKE IS NOT RECOMMENDED!");
        }
        if (hikedetail.getDifficulty() == userdetail.getLvl()) {
            warningLabel.setTextFill(Color.color(0, 0, 0));
            warningLabel.setText("This hike is just right for you.");
        }
        if (hikedetail.getDifficulty() < userdetail.getLvl()) {
            warningLabel.setTextFill(Color.color((double) 39 / 255, (double) 156 / 255, (double) 64 / 255));
            warningLabel.setText("This hike is a piece of cake !");
        }


    }
    /**
     * Handles the toMain button action event and changes the scene to mainMenu.fxml
     * @param event the action event.
     * @throws IOException if an I/O error occurs.
     */
    public void toMain(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("mainMenu.fxml"));
        root = fxmlLoader.load();

        MainMenuController mainMenuController = fxmlLoader.getController();

        mainMenuController.displayName(userdetail.getUsername());


        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    /**
     * Appends given text to the label.
     * @param label the label to append to.
     * @param append String to be appended.
     */
    public void appendText(Label label, String append) {
        label.setText(label.getText() + " ," + append);
    }
}


