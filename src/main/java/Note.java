import javafx.beans.property.SimpleStringProperty;

public class Note {

    public  SimpleStringProperty title = new SimpleStringProperty();
    public SimpleStringProperty description = new SimpleStringProperty();
    public  SimpleStringProperty dateCreated = new SimpleStringProperty();
    public  SimpleStringProperty idNO = new SimpleStringProperty();

    public String getTitle() {
        return title.get();
    }

    public void setTitle(String titleStr) {
        title.set(titleStr);
    }

    public String getDescription() {
        return description.get();
    }

    public void setDescription(String descriptionStr) {
        description.set(descriptionStr);
    }

    public String getDateCreated() {
        return dateCreated.get();
    }

    public void setDateCreated(String datecreatedStr) {
        dateCreated.set(datecreatedStr);
    }

    public String getIdNO() {
        return idNO.get();
    }

    public void setIdNO(String idnoStr) {
        idNO.set(idnoStr);
    }
}
