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
import java.util.Objects;
import java.util.ResourceBundle;

public class EditHikeController implements Initializable, CancelController {

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
    private Button editHikeBttn, cancelBttn;
    @FXML
    private Label nameErr;
    private Stage stage;
    private Scene scene;
    private Parent root;

    ArrayList<CheckBox> weathers = new ArrayList<>();
    ArrayList<CheckBox> types = new ArrayList<>();
    private BaseHike editing;
    /**
     * Handles the cancel button action event and closes the current window.
     * @param event the action event.
     * @throws IOException if an I/O error occurs.
     */
    public void cancel(ActionEvent event) throws IOException {
        CancelController.super.cancel(event);
    }
    /**
     * Handles the edit hike button action event and saves the changes made by the user.
     * @param event the action event.
     * @throws NumberException if hikeMinTextField does not contain a valid integer.
     * @throws IOException if an I/O error occurs.
     */
    public void editHike(ActionEvent event) throws NumberException, IOException {
        ArrayList<Integer> newTypes = new ArrayList<>();
        ArrayList<Integer> newWeathers = new ArrayList<>();
        String newuserName;
        int min;
        if (!hikeNameTextField.getText().isEmpty()) {
            String newname = hikeNameTextField.getText();
            for (BaseHike hike : GlobalVariables.allHikes){
                if (Objects.equals(hike, editing)){
                    continue;
                }
                if(hike.getNAME().equals(newname)){
                    nameErr.setText("This hike already exists");
                    return;
                }
            }
             newuserName = newname;

        }else {
             newuserName = editing.getNAME();
        }
        if(!hikeMinTextField.getText().isEmpty()){
            if(validate(hikeMinTextField.getText())){
                 min = Integer.parseInt(hikeMinTextField.getText());
            }else{
                throw new NumberException();
            }
        }else {
             min = editing.getMin();
        }
        for (CheckBox box: types){
            if(box.isSelected()){
                newTypes.add(types.indexOf(box));
            }
        }
        for(CheckBox box: weathers){
            if(box.isSelected()){
                newWeathers.add(weathers.indexOf(box));
            }
        }
        int newLvl = getLvl();
        System.out.println("Diff" + newLvl);
        boolean newAvl = AVLtrue.isSelected();

        editing.setAVL(newAvl);

        editing.setNAME(newuserName);

        editing.setMIN(min);
        editing.setWeatherOK(newWeathers);
        editing.setType(newTypes);
        editing.setDifficulty(newLvl);

        new Check(GlobalVariables.observer, "hikes");
        CancelController.super.cancel(event);



    }
    /**
     * Sets the BaseHike object that is being edited in the UI and calls the setAll() method to populate the UI fields with the hike's current values.
     *
     * @param hike the BaseHike object being edited
     */
    public void setEditing(BaseHike hike){
        editing = hike;
        setAll();
    }
    /**
     *This method updates the GUI components to display the information of a hike being edited.
     */

    public void setAll() {

        types.add(climbCheckBox);
        types.add(walkCheckBox);
        types.add(hikeCheckBox);
        types.add(ladderCheckBox);
        types.add(ccCheckBox);


        weathers.add(snowyCheckBox);
        weathers.add(sunnyCheckBox);
        weathers.add(cloudyCheckBox);
        weathers.add(rainyCheckBox);
        weathers.add(windyCheckBox);
        hikeNameLabel.setText("Editing " + editing.getNAME());
        hikeNameTextField.setText(editing.getNAME());
        hikeMinTextField.setText(Integer.toString(editing.getMin()));
        difficultyChoiceBox.setValue(GlobalVariables.getHikeLvl(editing.getDifficulty()));
        if (editing.getAVL()) {
            AVLtrue.setSelected(true);
        } else {
            AVLfalse.setSelected(true);
        }

        for (int type : editing.getType()) {
            types.get(type).setSelected(true);
        }
        for (int weather : editing.getWeatherOK()) {
            weathers.get(weather).setSelected(true);
        }
    }
    /**
     *Initializes the controller class.
     *Adds the difficulty levels to the difficultyChoiceBox.
     *@param url The location used to resolve relative paths for the root object, or null if the location is not known.
     *@param resourceBundle The resources used to localize the root object, or null if the root object was not localized.
     */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        difficultyChoiceBox.getItems().addAll(diff);


    }
    /**
     *Validates if the given string contains only digits.
     *@param text The string to be validated.
     *@return true if the string contains only digits, false otherwise.
     */

    private boolean validate(String text){
        return text.matches("[0-9]*");
    }

    /**
     *Returns the index of the difficulty level selected in the difficultyChoiceBox.
     *@return The index of the difficulty level selected.
     */
    public int getLvl(){
        String lvlS = difficultyChoiceBox.getValue();
        return GlobalVariables.getIndex(lvlS);
    }
}
