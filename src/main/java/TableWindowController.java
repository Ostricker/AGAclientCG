import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;

public class TableWindowController {

    @FXML
    private Button tableButton;
    public Button dashboardButton;
    public Button teamlistButton;

    @FXML private ResourceBundle resources;
    @FXML private URL location;

    @FXML void initialize() {

    }

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
