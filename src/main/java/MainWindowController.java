
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class MainWindowController {

    public Button dashboardButton;


    public void buttonPress(ActionEvent actionEvent) {
    }

    public void action(ActionEvent actionEvent) {
    }




    //TODO: Change buttons to TOGGLE buttons
    @FXML
    void changeScreen(ActionEvent actionEvent) throws IOException {

        Object source = actionEvent.getSource();
        Button clickedBtn = (Button) source;

        SceneManipulationHelper sceneManipulationHelper = new SceneManipulationHelper(dashboardButton.getScene());
        sceneManipulationHelper.activate(clickedBtn.getId());
    }
}
