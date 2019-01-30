import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.HashMap;

public class SceneManipulationHelper {

    private HashMap<String, Pane> screenMap = new HashMap<>();
    private Scene main;

    public SceneManipulationHelper(Scene main){
        this.main = main;
    }

    protected void activate(String name) throws IOException {
        switch (name){
            case "dashboardButton":
                screenMap.put("dashboardButton", FXMLLoader.load(getClass().getResource("MainWindow.fxml")));
                break;
            case "teamlistButton":
                screenMap.put("teamlistButton", FXMLLoader.load(getClass().getResource("Teamlist.fxml")));
                break;
            case "tableButton":
                screenMap.put("tableButton", FXMLLoader.load(getClass().getResource("TableWindow.fxml")));
                break;
        }
        main.setRoot(screenMap.get(name));
    }
}
