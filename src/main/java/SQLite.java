import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
    public void selectFromTable() throws SQLException {



    }

    //TODO: UPDATE statement with parameters
    public void updateIntoTable(){

    }

    //TODO: DELETE statement with parameters
    public void deleteFromTable(){

    }

    public ObservableList<Player> temporaryGetPlayers(){
        ObservableList<Player> player = FXCollections.observableArrayList();
        player.add(new Player("Jan", "Olšanský", "Ostricker","Gunrunners","Active Player","7321567654231"));

        return player;
    }
}
