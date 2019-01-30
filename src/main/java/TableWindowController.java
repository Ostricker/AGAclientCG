import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;

public class TableWindowController {




    @FXML private ResourceBundle resources;
    @FXML private URL location;

    @FXML void initialize() {

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
