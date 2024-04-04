package FxControllers;

import Hike.BaseHike;
import Monitoring.Check;
import Monitoring.NumberException;
import Other.GlobalVariables;
import com.example.starhike.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
/**
 * This class handles adding a new hike to the system and cancelling the action.
 */
public class AddHikeController implements Initializable, CancelController {
    @FXML
    private Label hikeNameLabel;
    @FXML
    private TextField hikeNameTextField;
    @FXML
    private TextField hikeMinTextField;

    @FXML
    private ChoiceBox<String> difficultyChoiceBox;
    private String[] diff = {"Novice", "Easy", "Medium", "Hard", "Expert"};

    @FXML
    private CheckBox walkCheckBox, climbCheckBox, ladderCheckBox, hikeCheckBox, ccCheckBox;
    @FXML
    private CheckBox sunnyCheckBox, cloudyCheckBox, snowyCheckBox, rainyCheckBox, windyCheckBox;

    @FXML
    private RadioButton AVLtrue, AVLfalse;

    @FXML
    private Button addHikeBttn, cancelBttn;
    @FXML
    private Label nameErr;
    private Stage stage;
    private Scene scene;
    private Parent root;

    /**
     * Adds a new hike to the system.
     *
     * @param event the event that triggers the action
     * @throws IOException if an I/O error occurs
     * @throws NumberException if an error occurs with parsing a number, custom exception
     */
    public void addHike(ActionEvent event) throws IOException, NumberException {
        ArrayList<CheckBox> types = new ArrayList<>();
        types.add(climbCheckBox);
        types.add(walkCheckBox);
        types.add(hikeCheckBox);
        types.add(ladderCheckBox);
        types.add(ccCheckBox);

        ArrayList<CheckBox> weathers = new ArrayList<>();
        weathers.add(snowyCheckBox);
        weathers.add(sunnyCheckBox);
        weathers.add(cloudyCheckBox);
        weathers.add(rainyCheckBox);
        weathers.add(windyCheckBox);

        String hikeName = hikeNameTextField.getText();

        ArrayList<Integer> typesI = new ArrayList<>();
        ArrayList<Integer> weathersI = new ArrayList<>();

       for (BaseHike hike : GlobalVariables.allHikes){
           if(hike.getNAME().equals(hikeName)){
               nameErr.setText("This hike already exists");
               return;
           }
       }
        for (CheckBox box : types){
            if(box.isSelected()){
                typesI.add(types.indexOf(box));
            }
        }
        for (CheckBox box : weathers){
            if(box.isSelected()){
                weathersI.add(weathers.indexOf(box));
            }
        }
        String checkMin = hikeMinTextField.getText();
        int min;
        if(validate(checkMin)){
            min = Integer.parseInt(checkMin);
        }else{
            throw new NumberException();
        }



        boolean avl = AVLtrue.isSelected();


        GlobalVariables.allHikes.add(BaseHike.newHike().NAME(hikeName).MIN(min).Type(typesI).WeatherOK(weathersI).AVL(avl).Difficulty(getLvl()).build());
        new Check(GlobalVariables.observer, "hikes");

        CancelController.super.cancel(event);


    }
    /**
     * Cancels the action of adding a new hike. Default method of a CancelController Interface
     *
     * @param event the event that triggers the action
     * @throws IOException if an I/O error occurs
     */

    public void cancel(ActionEvent event) throws IOException {
        CancelController.super.cancel(event);
    }
    /**
     * Initializes the controller by adding the difficulty levels to the choice box.
     *
     * @param url the location used to resolve relative paths for the root object
     * @param resourceBundle the resource bundle used to localize the root object
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        difficultyChoiceBox.getItems().addAll(diff);
    }

    /**
     * Validates whether the input text is a number.
     *
     * @param text the input text to validate
     * @return true if the input text is a number, false otherwise
     */
    private boolean validate(String text){
        return text.matches("[0-9]*");
    }

    /**
     * Returns the index of the selected difficulty level.
     *
     * @return the index of the selected difficulty level
     */
    public int getLvl(){
        String lvlS = difficultyChoiceBox.getValue();
        return GlobalVariables.getIndex(lvlS);
    }
}
