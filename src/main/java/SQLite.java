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
}

