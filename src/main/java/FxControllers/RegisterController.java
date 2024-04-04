package FxControllers;

import Datasets.UserDataset;
import Other.GlobalVariables;

import User.BaseUser;
import com.example.starhike.HelloApplication;
import javafx.animation.PauseTransition;
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
import java.util.Hashtable;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.util.Duration;
/**
 *The RegisterController class controls the registration form where users can create a new account.
 */
public class RegisterController implements Initializable, Message {
    @FXML
    public Label weatherLabel;
    @FXML
    private Button registerBttn;
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
    private Label psswdErrorMsg;
    @FXML
    private PasswordField psswdField1;
    @FXML
    private PasswordField psswdField2;
    @FXML
    private TextField userNameTextField;

    @FXML
    private Label usernameErrorMsg;
    @FXML
    private Stage stage;
    private Parent root;
    private Scene scene;

    /**
     *Registers a new user account with the given user name, password, and hiking preferences.
     *Displays error messages for invalid inputs and updates the user interface accordingly.
     *@param event the event that triggered the method call
     *@throws IOException if an I/O error occurs
     */
    public void register(ActionEvent event) throws IOException {
        ArrayList<CheckBox> types = new ArrayList<>();
        types.add(ClimbCheckBox);
        types.add(WalkCheckBox);
        types.add(HikeCheckBox);
        types.add(LadderCheckBox);
        types.add(CableCarCheckBox);

        String username = userNameTextField.getText();
        UserDataset temp = UserDataset.getInstance();
        Hashtable<String, String> users = temp.getUserPsswdData();

        for(String key: users.keySet()) {
            System.out.println("Key: "+key);
            if(Objects.equals(username, key)){

                usernameErrorMsg.setText("This name already exists.");
                return;
            }

        }

        String psswd1 = psswdField1.getText();
        String psswd2 = psswdField2.getText();
        if(!Objects.equals(psswd1, psswd2)){
            psswdErrorMsg.setText("Passwords do not match !");
            return;
        }else {
            psswdErrorMsg.setText("");
        }

        ArrayList<Integer> prefTypes = new ArrayList<>();
        for(CheckBox box : types){
            if(box.isSelected()){
                prefTypes.add(types.indexOf(box));
            }
        }

        users.put(username, psswd1);
        temp.writeData();

        BaseUser tempUser = new BaseUser(username, psswd1);
        tempUser.setPreffType(prefTypes);
        tempUser.setLvl(getLvl());

        System.out.println(tempUser.getPreffType());
        GlobalVariables.allUsers.add(tempUser);
        register();


        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("mainMenu.fxml"));
        root = fxmlLoader.load();

        MainMenuController mainMenuController = fxmlLoader.getController();
        mainMenuController.displayName(username);


        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    } /**
     *Initializes the user interface by populating the difficulty choice box and updating the weather label.
     *@param url the location used to resolve relative paths for the root object
     *@param resourceBundle the resources used to localize the root object
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        diffChoiceBox.getItems().addAll(diff);
        PauseTransition wait = new PauseTransition(Duration.seconds(1));
        wait.setOnFinished((e) -> {
            weatherLabel.setText(String.valueOf(GlobalVariables.weatherLabel));
            wait.playFromStart();
        });
        wait.play();
    }
    /**
     *Returns the hiking difficulty level selected by the user as an integer.
     *@return the index of the selected difficulty level in the GlobalVariables.diff array
     */
    public int getLvl(){
        String lvlS = diffChoiceBox.getValue();
        return GlobalVariables.getIndex(lvlS);
    }



}
