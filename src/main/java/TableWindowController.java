import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Callback;

public class TableWindowController {

    public Button dashboardButton;
    
    public TextField teamTF;
    public TextField matchesPlayedTF;
    public TextField winsTF;
    public TextField lossesTF;
    public TextField overtimeWinTF;
    public TextField overtimeLossTF;
    public TextField scoreTF;
    public TextField differenceTF;
    public TextField pointsTF;
    public TableView teamsTableView;
    private ObservableList<ObservableList> data;
    private List itemList;


    @FXML private ResourceBundle resources;
    @FXML private URL location;

    @FXML void initialize() throws SQLException {
        SQLite sqLite = new SQLite();
        sqLite.buildData(teamsTableView,data,"teams");



        teamsTableView.setRowFactory( tv -> {
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

        String sql = "INSERT INTO teams(Team, Matches_Played, Wins, Losses, Overtime_Wins, Overtime_Losses, Score, Difference, Points) VALUES (?,?,?,?,?,?,?,?,?)";
        updateStatement = sqLite.connect().prepareStatement(sql);

        updateStatement.setString(1, teamTF.getText());
        updateStatement.setString(2, matchesPlayedTF.getText());
        updateStatement.setString(3, winsTF.getText());
        updateStatement.setString(4, lossesTF.getText());
        updateStatement.setString(5, overtimeWinTF.getText());
        updateStatement.setString(6, overtimeLossTF.getText());
        updateStatement.setString(7, scoreTF.getText());
        updateStatement.setString(8, differenceTF.getText());
        updateStatement.setString(9, pointsTF.getText());
        updateStatement.executeUpdate();
        System.out.println("Row inserted");

        sqLite.buildData(teamsTableView,data,"teams");
    }

    public void deleteRow(ActionEvent actionEvent) throws SQLException {
        List list = (List) teamsTableView.getSelectionModel().getSelectedItem();

        SQLite sqLite = new SQLite();
        PreparedStatement deleteStatement = sqLite.connect().prepareStatement("DELETE FROM teams WHERE ID=?");
        deleteStatement.setString(1, list.get(0).toString());
        deleteStatement.executeUpdate();

        sqLite.buildData(teamsTableView,data,"teams");
    }

    public void editRow() {
        itemList = (List) teamsTableView.getSelectionModel().getSelectedItem();
        teamTF.setText(itemList.get(1).toString());
        matchesPlayedTF.setText(itemList.get(2).toString());
        winsTF.setText(itemList.get(3).toString());
        lossesTF.setText(itemList.get(4).toString());
        overtimeWinTF.setText(itemList.get(5).toString());
        overtimeLossTF.setText(itemList.get(6).toString());
        scoreTF.setText(itemList.get(7).toString());
        differenceTF.setText(itemList.get(8).toString());
        pointsTF.setText(itemList.get(9).toString());
    }

    public void updateRow(ActionEvent actionEvent) {
        itemList.add(1, teamTF.getText());
        itemList.add(2, matchesPlayedTF.getText());
        itemList.add(3, winsTF.getText());
        itemList.add(4, lossesTF.getText());
        itemList.add(5, overtimeWinTF.getText());
        itemList.add(6, overtimeLossTF.getText());
        itemList.add(7, scoreTF.getText());
        itemList.add(8, differenceTF.getText());
        itemList.add(9, pointsTF.getText());
        System.out.println(itemList);

        SQLite sqLite = new SQLite();
        PreparedStatement updateStatement = null;
        try {
            String sql = "UPDATE teams SET Team=?,Matches_Played=?,Wins=?,Losses=?,Overtime_Wins=?,Overtime_Losses=?,Score=?,Difference=?,Points=? WHERE ID=?";

            updateStatement = sqLite.connect().prepareStatement(sql);
            for (int i=1; i<=9 ;i++){
                updateStatement.setString(i, itemList.get(i).toString());
            }
            updateStatement.setString(10, itemList.get(0).toString());
            updateStatement.executeUpdate();
            System.out.println("Row updated");

            sqLite.buildData(teamsTableView,data,"teams");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

}
