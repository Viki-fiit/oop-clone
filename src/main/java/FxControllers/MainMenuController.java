package FxControllers;


import Hike.BaseHike;
import Other.GlobalVariables;
import Thread.WeatherThread;
import User.BaseUser;
import com.example.starhike.HelloApplication;
import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable, Message {
    @FXML
    private TableColumn<BaseHike,String> nameCol;
    @FXML
    private Button detailBttn;
    @FXML
    private TableView<BaseHike> hikeList;
    @FXML
    private Button editInfoBttn;
    @FXML
    Label nameLabel;

    @FXML
    public Label weatherLabel;

    @FXML
    private Button logoutBttn;
    @FXML
    private AnchorPane mainMenu;

    public Stage stage;
    public Scene scene;
    public Parent root;
    private String checkusername;
    ObservableList<BaseHike> oblistk = FXCollections.observableArrayList();

    /**
     * Sets the name of the logged in user to be displayed on the main menu page.
     *
     * @param username the username of the logged in user.
     */

    public void displayName(String username){
        checkusername = username;
        nameLabel.setText("Hello " + username +"!");
    }
    /**
     * Logs out the user and returns them to the welcome screen page.
     *
     * @param event the ActionEvent that triggers the logout method.
     * @throws IOException if the FXML file for the welcome screen page cannot be found.
     */
    public void logout(ActionEvent event) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Log out");
        alert.setHeaderText("You're about to log out :(");
        alert.setContentText("Do you want to continue?");

        if(alert.showAndWait().get() == ButtonType.OK) {
            goodBye();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("welcomeScreen.fxml"));
            root = fxmlLoader.load();

            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

    }
    /**
     * Initializes the main menu page with the current weather conditions and starts the weather update thread.
     *
     * @param url the URL of the FXML file for the main menu page.
     * @param resourceBundle the resource bundle for the main menu page.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        oblistk.addAll(GlobalVariables.allHikes);

        hikeList.setItems(oblistk);
        PauseTransition wait = new PauseTransition(Duration.seconds(1));
        wait.setOnFinished((e) -> {
            weatherLabel.setText(String.valueOf(GlobalVariables.weatherLabel));
            wait.playFromStart();
        });
        wait.play();
    }

    /**
     * Handles detail Button action and calls upon a new scene to be displayed.
     * @param event
     * @throws IOException
     */
    public void details(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hikeDetails.fxml"));
        root = fxmlLoader.load();

        HikeDetailsController hikeDetailsController = fxmlLoader.getController();
        BaseUser checkuser = null;
        for(BaseUser user: GlobalVariables.allUsers){
            if(user.getUsername().equals(checkusername)){
                checkuser = user;
            }
        }
        hikeDetailsController.setUser(checkuser);
        hikeDetailsController.setHikedetail(hikeList.getSelectionModel().getSelectedItem());

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    /**
     * Handles edit Button action and calls upon a new scene to be displayed.
     * @param event
     * @throws IOException
     */
    public void editInfo(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("editUser.fxml"));
        root = fxmlLoader.load();

        EditUserController editUserController = fxmlLoader.getController();
        BaseUser checkuser = null;
        for(BaseUser user: GlobalVariables.allUsers){
            if(user.getUsername().equals(checkusername)){
                checkuser = user;
            }
        }
        editUserController.setUser(checkuser);


        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }

}
