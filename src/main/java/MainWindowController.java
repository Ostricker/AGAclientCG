
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;

import java.io.IOException;

public class MainWindowController {



    public void buttonPress(ActionEvent actionEvent) {
    }

    public void action(ActionEvent actionEvent) {
    }





    public Button dashboardButton;

    //TODO: Change buttons to TOGGLE buttons
    @FXML
    void changeScreen(ActionEvent actionEvent) throws IOException {

        Object source = actionEvent.getSource();
        Button clickedBtn = (Button) source;

        SceneManipulationHelper sceneManipulationHelper = new SceneManipulationHelper(dashboardButton.getScene());
        sceneManipulationHelper.activate(clickedBtn.getId());
    }
}
