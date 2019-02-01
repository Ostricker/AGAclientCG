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
import java.util.ArrayList;
import java.util.List;
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
    private List itemList;

    @FXML void initialize() throws SQLException {
        SQLite sqLite = new SQLite();
        sqLite.buildData(playersTableView,data,"players");

        playersTableView.setRowFactory( tv -> {
            TableRow<List> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty())){
                    editRow();
                }
            });
            return row;
        });
    }

    //TODO: Change buttons to TOGGLE buttons
    @FXML void changeScreen(ActionEvent actionEvent) throws IOException {
        Object source = actionEvent.getSource();
        Button clickedBtn = (Button) source;

        SceneManipulationHelper sceneManipulationHelper = new SceneManipulationHelper(dashboardButton.getScene());
        sceneManipulationHelper.activate(clickedBtn.getId());
        }


    public void addNewRowToTable(ActionEvent actionEvent) throws SQLException {
        SQLite sqLite = new SQLite();
        PreparedStatement updateStatement = null;

            String sql = "INSERT INTO players(FirstName, LastName, Nickname, Team, Position, SteamID64) VALUES (?,?,?,?,?,?)";
            updateStatement = sqLite.connect().prepareStatement(sql);

            updateStatement.setString(1, nameTextField.getText());
            updateStatement.setString(2, lastNameTextField.getText());
            updateStatement.setString(3, nicknameTextField.getText());
            updateStatement.setString(4, teamTextField.getText());
            updateStatement.setString(5, positionTextField.getText());
            updateStatement.setString(6, steamIDTextField.getText());
            updateStatement.executeUpdate();
            System.out.println("Row inserted");

        sqLite.buildData(playersTableView,data,"players");
    }

    public void deleteRow(ActionEvent actionEvent) throws SQLException {
        List list = (List) playersTableView.getSelectionModel().getSelectedItem();

        SQLite sqLite = new SQLite();
        PreparedStatement deleteStatement = sqLite.connect().prepareStatement("DELETE FROM players WHERE ID=?");
        deleteStatement.setString(1, list.get(0).toString());
        deleteStatement.executeUpdate();

        sqLite.buildData(playersTableView,data,"players");
    }

    public void editRow() {
        itemList = (List) playersTableView.getSelectionModel().getSelectedItem();
        nameTextField.setText(itemList.get(1).toString());
        lastNameTextField.setText(itemList.get(2).toString());
        nicknameTextField.setText(itemList.get(3).toString());
        teamTextField.setText(itemList.get(4).toString());
        positionTextField.setText(itemList.get(5).toString());
        steamIDTextField.setText(itemList.get(6).toString());
    }

    public void updateRow(ActionEvent actionEvent) {
        itemList.add(1, nameTextField.getText());
        itemList.add(2, lastNameTextField.getText());
        itemList.add(3, nicknameTextField.getText());
        itemList.add(4, teamTextField.getText());
        itemList.add(5, positionTextField.getText());
        itemList.add(6, steamIDTextField.getText());
        System.out.println(itemList);

        SQLite sqLite = new SQLite();
        PreparedStatement updateStatement = null;
        try {
            String sql = "UPDATE players SET FirstName=?,LastName=?,Nickname=?,Team=?,Position=?,SteamID64=? WHERE ID=?";

            updateStatement = sqLite.connect().prepareStatement(sql);
            for (int i=1; i<=6 ;i++){
                updateStatement.setString(i, itemList.get(i).toString());
            }
            updateStatement.setString(7, itemList.get(0).toString());
            updateStatement.executeUpdate();
            System.out.println("Row updated");

            sqLite.buildData(playersTableView,data,"players");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

}

