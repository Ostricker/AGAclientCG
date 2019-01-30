import javafx.beans.property.SimpleStringProperty;

public class Player {

    private SimpleStringProperty firstName;
    private SimpleStringProperty lastName;
    private SimpleStringProperty nickname;
    private SimpleStringProperty team;
    private SimpleStringProperty position;
    private SimpleStringProperty steamId64;

    public Player(String firstName, String lastName, String nickname, String team, String position, String steamId64) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.nickname =  new SimpleStringProperty(nickname);
        this.team =  new SimpleStringProperty(team);
        this.position =  new SimpleStringProperty(position);
        this.steamId64 =  new SimpleStringProperty(steamId64);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public SimpleStringProperty firstNameProperty() {
        return firstName;
    }

    public String getLastName() {
        return lastName.get();
    }

    public SimpleStringProperty lastNameProperty() {
        return lastName;
    }

    public String getNickname() {
        return nickname.get();
    }

    public SimpleStringProperty nicknameProperty() {
        return nickname;
    }

    public String getTeam() {
        return team.get();
    }

    public SimpleStringProperty teamProperty() {
        return team;
    }

    public String getPosition() {
        return position.get();
    }

    public SimpleStringProperty positionProperty() {
        return position;
    }

    public String getSteamId64() {
        return steamId64.get();
    }

    public SimpleStringProperty steamId64Property() {
        return steamId64;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public void setNickname(String nickname) {
        this.nickname.set(nickname);
    }

    public void setTeam(String team) {
        this.team.set(team);
    }

    public void setPosition(String position) {
        this.position.set(position);
    }

    public void setSteamId64(String steamId64) {
        this.steamId64.set(steamId64);
    }
}
