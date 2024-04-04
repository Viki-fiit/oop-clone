package FxControllers;

import Monitoring.Check;
import Monitoring.NumberException;
import Other.GlobalVariables;
import User.BaseUser;
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

public class EditUserController implements CancelController, Initializable {

    @FXML
    private Button cancelBttn;
    @FXML
    private Button editBttn;
    @FXML
    private CheckBox ClimbCheckBox;
    @FXML
    private CheckBox WalkCheckBox;
    @FXML
    private CheckBox HikeCheckBox;
    @FXML
    private CheckBox LadderCheckBox;
    @FXML
    private CheckBox CableCarCheckBox;
    @FXML
    private ChoiceBox<String> diffChoiceBox;
    private String[] diff = {"Novice", "Easy", "Medium", "Hard", "Expert"};
    @FXML
    private PasswordField psswdField;

    @FXML
    private TextField userNameTextField;

    @FXML
    private Label usernameErrorMsg;
    @FXML
    private Stage stage;
    private Parent root;
    private Scene scene;

    private BaseUser userEdit;
    ArrayList<CheckBox> types = new ArrayList<>();
    /**
     *Initializes the controller class.
     *Adds the difficulty levels to the difficultyChoiceBox.
     *@param url The location used to resolve relative paths for the root object, or null if the location is not known.
     *@param resourceBundle The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        diffChoiceBox.getItems().addAll(diff);
    }
    /**
     * Sets the BaseUser object that is being edited in the UI and calls the setAll() method to populate the UI fields with the user's current values.
     *
     * @param user the BaseUser object being edited
     */
    public void setUser(BaseUser user){
        userEdit = user;
        setAll();
    }

    /**
     *This method updates the GUI components to display the information of a hike being edited.
     */
    public void setAll(){

        types.add(ClimbCheckBox);
        types.add(WalkCheckBox);
        types.add(HikeCheckBox);
        types.add(LadderCheckBox);
        types.add(CableCarCheckBox);

        userNameTextField.setText(userEdit.getUsername());
        psswdField.setText(userEdit.getPassword());
        diffChoiceBox.setValue(GlobalVariables.getHikeLvl(userEdit.getLvl()));
        for (int type : userEdit.getPreffType()) {
            types.get(type).setSelected(true);
        }
    }
    /**
     * Handles the edit user button action event and saves the changes made by the user.
     * @param event the action event.
     * @throws IOException if an I/O error occurs.
     */

    public void edit(ActionEvent event) throws IOException {
        ArrayList<Integer> newTypes = new ArrayList<>();
        String newusername;
        if(!userNameTextField.getText().isEmpty()){
            String checkname = userNameTextField.getText();
            for (BaseUser user: GlobalVariables.allUsers){
                if(Objects.equals(userEdit, user)){
                    continue;

                }
                if(user.getUsername().equals(checkname)){
                    usernameErrorMsg.setText("Another user has this name.");
                    return;
                }
            }
            newusername = checkname;
        }else {
            newusername = userEdit.getUsername();
        }
        String newpsswd = psswdField.getText();
        int newLvl = getLvl();
        for (CheckBox box: types){
            if(box.isSelected()){
                newTypes.add(types.indexOf(box));
            }
        }

        userEdit.setLvl(newLvl);
        userEdit.setPreffType(newTypes);
        userEdit.setPassword(newpsswd);
        userEdit.setUsername(newusername);
        new Check(GlobalVariables.observer, "users");
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("mainMenu.fxml"));
        root = fxmlLoader.load();

        MainMenuController mainMenuController = fxmlLoader.getController();

        mainMenuController.displayName(userEdit.getUsername());
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }
    /**
     *Returns the index of the difficulty level selected in the difficultyChoiceBox.
     *@return The index of the difficulty level selected.
     */
    public int getLvl(){
        String lvlS = diffChoiceBox.getValue();
        return GlobalVariables.getIndex(lvlS);
    }
    /**
     * Handles the cancel button action event and changes the scene to mainMenu.fxml
     * @param event the action event.
     * @throws IOException if an I/O error occurs.
     */
    @Override
    public void cancel(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("mainMenu.fxml"));
        root = fxmlLoader.load();

        MainMenuController mainMenuController = fxmlLoader.getController();

        mainMenuController.displayName(userEdit.getUsername());
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
