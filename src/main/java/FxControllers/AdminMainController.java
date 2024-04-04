package FxControllers;

import Hike.BaseHike;
import Monitoring.Check;
import Other.GlobalVariables;
import User.BaseUser;
import com.example.starhike.HelloApplication;
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


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.stage.Stage;
/**
 * The controller class for the main admin screen.
 */
public class AdminMainController implements Initializable {
    @FXML
    private Button editHikeBttn;
    @FXML
    private Button addHikeBttn;
    @FXML
    private Button logoutBttn;
    @FXML
   private TableView<BaseHike> hikesTableView ;

   @FXML
   private TableColumn<BaseHike,String> colName;

    @FXML
    private Button delHikeBttn;
    private Stage stage;
    private Scene scene;
    private Parent root;

    ObservableList<BaseHike> oblistk = FXCollections.observableArrayList();

    /**
     * Initializes the controller.
     *
     * @param url the URL
     * @param resourceBundle the resource bundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        oblistk.addAll(GlobalVariables.allHikes);

        hikesTableView.setItems(oblistk);
    }

    /**
     * Handles the "Add Hike" button action. Calls upon a new scene.
     *
     * @param event the action event
     * @throws IOException if an I/O error occurs
     */
    public void addHike(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("addHike.fxml"));
        root = fxmlLoader.load();

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    /**
     * Handles the "Add Hike" button action. Calls upon a new scene.
     *
     * @param event the action event
     * @throws IOException if an I/O error occurs
     */
    public void editHike(ActionEvent event) throws IOException {

           // int index = hikesTableView.getSelectionModel().selectedIndexProperty().get();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("editHike.fxml"));
        root = fxmlLoader.load();
        EditHikeController editHikeController = fxmlLoader.getController();
        editHikeController.setEditing(hikesTableView.getSelectionModel().getSelectedItem());

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    /**
     * Handles the "Add Hike" button action. Refreshes current scene.
     *
     * @param event the action event
     * @throws IOException if an I/O error occurs
     */
    public void deleteHike(ActionEvent event) throws IOException {
        BaseHike toDelete = hikesTableView.getSelectionModel().getSelectedItem();
        GlobalVariables.allHikes.remove(toDelete);
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("adminMain.fxml"));
        root = fxmlLoader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();



    }
    /**
     * Logs out the current user by showing a confirmation dialog box and, if the user clicks the OK button,
     * creates a new check object with the observer and "hikes" as arguments, which initializes serialization. Then it loads the welcomeScreen.fxml
     * file and displays it in a new stage.
     *
     * @param event the ActionEvent triggered by clicking the logout button
     * @throws IOException if an I/O error occurs when loading the welcomeScreen.fxml file
     */
    public void logout(ActionEvent event) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Log out");
        alert.setHeaderText("You're about to log out :(");
        alert.setContentText("Do you want to continue?");

        if(alert.showAndWait().get() == ButtonType.OK) {
            new Check(GlobalVariables.observer, "hikes");
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("welcomeScreen.fxml"));
            root = fxmlLoader.load();

            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

    }
}
