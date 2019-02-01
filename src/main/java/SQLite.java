import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;

import java.sql.*;

public class SQLite {


    //TODO: Create table check in case table is not already created
    public void createTable(){
    }

    public Connection connect(){
        String url = "jdbc:sqlite:src/main/database/main_database.db";
        Connection con = null;
        try {
            con = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return con;
    }

    public void insertIntoTable(String insertString) throws SQLException {
        Statement statement = connect().createStatement();
        statement.execute(insertString);
    }

    public void getFromTable() throws SQLException {

        PreparedStatement preparedStatement = connect().prepareStatement("SELECT name FROM sqlite_master WHERE type='table'");

    }

    //TODO: UPDATE statement with parameters
    public void updateIntoTable(){
    }

    //TODO: DELETE statement with parameters
    public void deleteFromTable(){
    }

    //CONNECTION DATABASE
    public void buildData(TableView tableView, ObservableList<ObservableList> data, String table) throws SQLException {
        data = FXCollections.observableArrayList();
        tableView.getColumns().clear();

        Connection conn;
        conn = connect();

        //SQL FOR SELECTING ALL OF CUSTOMER
        String SQL = "SELECT * FROM "+table+"";
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
            col.prefWidthProperty().bind(tableView.widthProperty().divide(rs.getMetaData().getColumnCount()*0.95));
            tableView.getColumns().addAll(col);
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
            System.out.println("Row added" + row);
            data.add(row);
        }
        tableView.setItems(data);
        tableView.setEditable(true);
    }
}

