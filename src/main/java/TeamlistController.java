import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class TeamlistController {

    @FXML private ResourceBundle resources;
    @FXML private URL location;

    public TableView playersTableView;
    public TableColumn steamIDColumn;
    public TableColumn lastNameColumn;
    public TableColumn nicknameColumn;
    public TableColumn firstNameColumn;
    public TableColumn teamColumn;
    public TableColumn positionColumn;

    SQLite sqLite = new SQLite();

    @FXML void initialize() throws SQLException {
        sqLite.openDB("src/main/database/main_database.db");


        firstNameColumn.setCellValueFactory(new PropertyValueFactory<Player, String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<Player, String>("lastName"));
        nicknameColumn.setCellValueFactory(new PropertyValueFactory<Player, String>("nickname"));
        teamColumn.setCellValueFactory(new PropertyValueFactory<Player, String>("team"));
        positionColumn.setCellValueFactory(new PropertyValueFactory<Player, String>("position"));
        steamIDColumn.setCellValueFactory(new PropertyValueFactory<Player, String>("steamId64"));

        playersTableView.setItems(sqLite.temporaryGetPlayers());
    }

    //TODO: Populate columns from database
     public void populateColumns(){

     }



    public Button dashboardButton;

    //TODO: Change buttons to TOGGLE buttons
    @FXML void changeScreen(ActionEvent actionEvent) throws IOException {
        Object source = actionEvent.getSource();
        Button clickedBtn = (Button) source;

        SceneManipulationHelper sceneManipulationHelper = new SceneManipulationHelper(dashboardButton.getScene());
        sceneManipulationHelper.activate(clickedBtn.getId());
        }
    }


