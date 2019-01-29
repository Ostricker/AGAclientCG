import java.sql.*;

public class SQLite {

    private Connection connection = null;
    private String urlClass;

    //TODO: Create table check in case table is not already created
    public void createTable(){
    }

    //TODO: Open DB here. Code here - init in main
    public Connection openDB(String databaseUrl){
        String url = "jdbc:sqlite:"+databaseUrl;


        Connection con = null;
        try {
            con = DriverManager.getConnection(url);
            urlClass = databaseUrl;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        connection = con;
        return con;
    }

    //TODO: Insert statement with parameters
    public void insertIntoTable(){

    }

    //TODO: SELECT statement with parameters
    public void selectFromTable(String statement) throws SQLException {


        Connection con = this.openDB(urlClass);
        Statement stmt = con.createStatement();
        ResultSet rs   = stmt.executeQuery(statement);

        while (rs.next()){
            System.out.println(rs.getInt("ID")+"\t"+
                    rs.getString("FirstName") +"\t"+
                    rs.getString("LastName") +"\t");

        }
    }

    //TODO: UPDATE statement with parameters
    public void updateIntoTable(){

    }

    //TODO: DELETE statement with parameters
    public void deleteFromTable(){

    }
}
