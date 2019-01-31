import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class TeamlistController {

    public TextField nameTextField;
    public TextField lastNameTextField;
    public TextField nicknameTextField;
    public TextField teamTextField;
    public TextField positionTextField;
    public TextField steamIDTextField;

    @FXML private ResourceBundle resources;
    @FXML private URL location;

    public Button dashboardButton;
    public TableView playersTableView;
    private ObservableList<ObservableList> data;

    @FXML void initialize() {
        try {
            buildData();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    //TODO: Change buttons to TOGGLE buttons
    @FXML void changeScreen(ActionEvent actionEvent) throws IOException {
        Object source = actionEvent.getSource();
        Button clickedBtn = (Button) source;

        SceneManipulationHelper sceneManipulationHelper = new SceneManipulationHelper(dashboardButton.getScene());
        sceneManipulationHelper.activate(clickedBtn.getId());
        }

        // TODO: graphic changes to table view so it looks good
    //CONNECTION DATABASE
    public void buildData() throws SQLException {
        data = FXCollections.observableArrayList();

        Connection conn;
        SQLite sqLite = new SQLite();
        conn = sqLite.connect();

        //TODO: Prepare SQL statement based on buttons pressed
        //SQL FOR SELECTING ALL OF CUSTOMER
        String SQL = "SELECT * FROM players";
        ResultSet rs = conn.createStatement().executeQuery(SQL);
        /**
         * ********************************
         * TABLE COLUMN ADDED DYNAMICALLY *
         *********************************
         */
        for (int i = 1; i < rs.getMetaData().getColumnCount(); i++) {
            final int j = i;
            TableColumn col = new TableColumn(rs.getMetaData().getColumnLabel(i + 1));
            col.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>) param -> new SimpleStringProperty(param.getValue().get(j).toString()));

            col.setCellFactory(TextFieldTableCell.forTableColumn());
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
        playersTableView.setEditable(true);
    }

    public void confirmTableUpdate(ActionEvent actionEvent) throws SQLException {
        String statement = "INSERT INTO players(FirstName,LastName,Nickname,Team,Position,SteamID64) VALUES (\""+nameTextField.getText()+"\",\""+lastNameTextField.getText()+"\",\""+nicknameTextField.getText()+"\",\""+teamTextField.getText()+"\",\""+positionTextField.getText()+"\",\""+steamIDTextField.getText()+"\")";
        playersTableView.getColumns().clear();

        SQLite sqLite = new SQLite();
        sqLite.insertIntoTable(statement);

        buildData();
    }
}

