import java.sql.*;

public class SQLite {


    //TODO: Create table check in case table is not already created
    public void createTable(){
    }

    //TODO: Open DB here. Code here - init in main
    public Connection connect(){
        String url = "jdbc:sqlite:src/main/database/main_database.db";

        Connection con = null;
        try {
            con = DriverManager.getConnection(url);
            System.out.println("Connection estabilished");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return con;
    }

    public void insertIntoTable(String insertString) throws SQLException {
        Statement statement = connect().createStatement();
        statement.execute(insertString);
    }

    //TODO: SELECT statement with parameters
    public void selectFromTable() throws SQLException {
    }

    //TODO: UPDATE statement with parameters
    public void updateIntoTable(){
    }

    //TODO: DELETE statement with parameters
    public void deleteFromTable(){
    }
}

