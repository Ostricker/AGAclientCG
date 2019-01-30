import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class TeamlistController {

    @FXML private ResourceBundle resources;
    @FXML private URL location;

    public Button dashboardButton;
    public TableView playersTableView;
    private ObservableList<ObservableList> data;

    @FXML void initialize() throws SQLException {
        buildData();
    }

    //TODO: Populate columns from database
     public void populateColumns(){

     }



    //TODO: Change buttons to TOGGLE buttons
    @FXML void changeScreen(ActionEvent actionEvent) throws IOException {
        Object source = actionEvent.getSource();
        Button clickedBtn = (Button) source;

        SceneManipulationHelper sceneManipulationHelper = new SceneManipulationHelper(dashboardButton.getScene());
        sceneManipulationHelper.activate(clickedBtn.getId());
        }

    //CONNECTION DATABASE
    public void buildData() throws SQLException {
        data = FXCollections.observableArrayList();

        Connection conn;
        SQLite sqLite = new SQLite();
        conn = sqLite.connect();

        //SQL FOR SELECTING ALL OF CUSTOMER
        String SQL = "SELECT * FROM players";
        ResultSet rs = conn.createStatement().executeQuery(SQL);
        /**
         * ********************************
         * TABLE COLUMN ADDED DYNAMICALLY *
         *********************************
         */
        for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
            final int j = i;
            TableColumn col = new TableColumn(rs.getMetaData().getColumnLabel(i + 1));
            col.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>) param -> new SimpleStringProperty(param.getValue().get(j).toString()));

            playersTableView.getColumns().addAll(col);
            System.out.println("Column [" + i + "] ");
        }
        /**
         * ******************************
         * Data added to ObservableList *
         *******************************
         */
        while (rs.next()){
            ObservableList<String> row = FXCollections.observableArrayList();
            for(int i = 1; i <= rs.getMetaData().getColumnCount(); i++){
                row.add(rs.getString(i));
            }
            System.out.println("Row [1] added" + row);
            data.add(row);
        }
        playersTableView.setItems(data);
    }

}

