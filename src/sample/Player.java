package sample;

import javafx.scene.control.CheckBox;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

public class Player {
    public String name;
    public int memNum;
    public String memType;
    public boolean status;
    public CheckBox checkIn;

    //Will be used in future to seperate TeeTime class further
    public Player(LocalTime time, LocalDate date, int amtPlayers, String name, int memNum, String memType, boolean status) throws SQLException {
        this.name = name;
        this.memNum = memNum;
        this.memType = memType;
        this.status = status;
        checkIn = new CheckBox();
        setCheckIn(status);
    }

    public void setCheckIn(boolean status){
        checkIn.setSelected(status);
    }
}
