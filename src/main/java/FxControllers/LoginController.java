package FxControllers;

import Monitoring.Check;
import Serialize.SerializeUsers;
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
import java.util.Hashtable;
import java.util.Objects;
import java.util.ResourceBundle;

import Datasets.UserDataset;
import Thread.WeatherThread;
import Other.GlobalVariables;
import javafx.util.Duration;

public class LoginController implements Initializable, Message {

    public Label weatherLabel;
    @FXML
    private Label loginErrorMsg;
    @FXML
    private Button loginButton;
    @FXML
    private TextField loginUserName;
    @FXML
    private PasswordField loginPsswd;
    @FXML
    private Stage stage;
    private Parent root;
    private Scene scene;
        String userName;
    String psswd;

    /**
     * Login method is used to authenticate the user and log them into the application.
     * @param event The event of clicking the login button
     * @throws IOException when loading the appropriate view of the user type fails.
     */
    public void login(ActionEvent event) throws IOException {
        UserDataset temp = UserDataset.getInstance();
        Hashtable<String, String> users = temp.getUserPsswdData();
        userName = loginUserName.getText();
        psswd = loginPsswd.getText();
        boolean check = false;
        boolean checkAdmin = false;
       // System.out.println(users);
        
        for(String key : users.keySet()){
            //System.out.println("Key: "+key);
            if (Objects.equals(userName, key)){
                check = true;
                if(Objects.equals(psswd, users.get(userName))){
                    break;

                }else{
                    loginErrorMsg.setText("The password is incorrect");
                    return;
                }
                
            }
        }
        if (userName.equals("admin") && psswd.equals("admin")){
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("adminMain.fxml"));
            root = fxmlLoader.load();

            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            return;
        }

        if(!check){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Who are you?");
            alert.setHeaderText("Uknown username.");
            alert.setContentText("Would you like to register ?");

            if(alert.showAndWait().get() == ButtonType.OK) {
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("register.fxml"));
                root = fxmlLoader.load();

                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                return;
            }
            return;
        }
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("mainMenu.fxml"));
        root = fxmlLoader.load();
        warning();
        MainMenuController mainMenuController = fxmlLoader.getController();

        mainMenuController.displayName(userName);


        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    /**
     * The initialize method initializes the weather widget in the login view
     * @param url the URL of the FXML file that was used to initialize this controller
     * @param resourceBundle The resources used to localize this application
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
