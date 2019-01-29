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

    private ObservableList<Note> dataNotes;

    @FXML void initialize() throws SQLException {
        SQLite sqLite = new SQLite();
        sqLite.openDB("src/main/database/main_database.db");
        System.out.println("Connected to database.");
        sqLite.selectFromTable("SELECT ID, FirstName, LastName  FROM players");
    }









    @FXML private Button tableButton;
    public Button dashboardButton;
    public Button teamlistButton;

    //TODO: Create better way to change scenes. This is a lot of duplicate code.
    //TODO: Change buttons to TOGGLE buttons
    @FXML void changeScreen(ActionEvent actionEvent) throws IOException {
        Object source = actionEvent.getSource();
        Button clickedBtn = (Button) source;
        System.out.println(clickedBtn.getId());

        SceneManipulationHelper sceneManipulationHelper = new SceneManipulationHelper(tableButton.getScene());
        sceneManipulationHelper.addScreen("dashboard", FXMLLoader.load(getClass().getResource("MainWindow.fxml")));
        sceneManipulationHelper.addScreen("teamlist", FXMLLoader.load(getClass().getResource("Teamlist.fxml")));
        sceneManipulationHelper.addScreen("table", FXMLLoader.load(getClass().getResource("TableWindow.fxml")));

        switch (clickedBtn.getId()){
            case "dashboardButton":
                sceneManipulationHelper.activate("dashboard");
                break;
            case "teamlistButton":
                sceneManipulationHelper.activate("teamlist");
                break;
            case "tableButton":
                sceneManipulationHelper.activate("table");
                break;
        }
    }

}
