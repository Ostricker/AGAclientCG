
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;

import java.io.IOException;

public class MainWindowController {

    public Button tableButton;
    public Button teamlistButton;
    public Button dashboardButton;

    public void buttonPress(ActionEvent actionEvent) {
    }

    public void action(ActionEvent actionEvent) {
    }



    //TODO: Create better way to change scenes. This is a lot of duplicate code.
    //TODO: Change buttons to TOGGLE buttons
    public void changeScreen(ActionEvent actionEvent) throws IOException {
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
