package sample;

import javafx.scene.control.CheckBox;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

public class TeeTime {
    public LocalTime time;
    public LocalDate date;
    public String name;
    public String player2;
    public String player3;
    public String player4;
    public int amtPlayers;
    public boolean checkedIn;
    public boolean checkedInP2;
    public boolean checkedInP3;
    public boolean checkedInP4;
    public int memNum;
    public int memNumP2;
    public int memNumP3;
    public int memNumP4;
    public String memType;
    public String memTypeP2;
    public String memTypeP3;
    public String memTypeP4;
    public CheckBox statusP1;
    public CheckBox statusP2;
    public CheckBox statusP3;
    public CheckBox statusP4;


    public TeeTime(LocalTime time){
        this.time = time;
        this.date = null;
        this.name = "";
        this.amtPlayers = 0;
        this.checkedIn = false;
        this.statusP1 = new CheckBox();
    }

    public TeeTime(LocalTime time, LocalDate date) throws SQLException {
        this.time = time;
        this.date = date;
        pullTeeTimeName(time, date);
        pullTeeTimePlayers(time, date);
        pullTeeTimeCheckedIn(time, date);
        pullTeeTimeCheckedInP2(time, date);
        pullTeeTimeCheckedInP3(time, date);
        pullTeeTimeCheckedInP4(time, date);
        pullTeeTimeMemberNumber(time, date);
        pullTeeTimeMemberNumberP2(time, date);
        pullTeeTimeMemberNumberP3(time, date);
        pullTeeTimeMemberNumberP4(time, date);
        pullReservationType();
        pullReservationTypeP2();
        pullReservationTypeP3();
        pullReservationTypeP4();
        pullPlayer2(time, date);
        pullPlayer3(time, date);
        pullPlayer4(time, date);
        this.statusP1 = new CheckBox();
        setstatusP1(checkedIn);
        this.statusP2 = new CheckBox();
        setstatusP2(checkedInP2);
        this.statusP3 = new CheckBox();
        setstatusP3(checkedInP3);
        this.statusP4 = new CheckBox();
        setstatusP4(checkedInP4);
    }


    public TeeTime(LocalTime time, String name, int amtPlayers, LocalDate date, boolean checkedIn, int memNum){
        this.time = time;
        this.amtPlayers = amtPlayers;
        this.name = name;
        this.date = date;
        this.checkedIn = checkedIn;
        this.checkedInP2 = false;
        this.checkedInP3 = false;
        this.checkedInP4 = false;
        this.memNum = memNum;
        storeTeeTime();
        pullReservationType();
        this.statusP1 = new CheckBox();
        setstatusP1(checkedIn);
        this.statusP2 = new CheckBox();
        setstatusP2(checkedInP2);
        this.statusP3 = new CheckBox();
        setstatusP3(checkedInP3);
        this.statusP4 = new CheckBox();
        setstatusP4(checkedInP4);
        setMemNumP2(-1);
        setMemNumP3(-1);
        setMemNumP4(-1);
    }
    public TeeTime(LocalTime time, LocalDate date, int amtPlayers){
        this.time = time;
        this.date =date;
        this.amtPlayers = amtPlayers;
    }

    public void setstatusP1(boolean checkedIn){
        statusP1.setSelected(checkedIn);
    }
    public void setstatusP2(boolean checkedInP2){
        statusP2.setSelected(checkedInP2);
    }
    public void setstatusP3(boolean checkedInP3){
        statusP3.setSelected(checkedInP3);
    }
    public void setstatusP4(boolean checkedInP4){
        statusP4.setSelected(checkedInP4);
    }

    public CheckBox getStatusP1() {
        return statusP1;
    }
    public CheckBox getStatusP2() {
        return statusP2;
    }
    public CheckBox getStatusP3() {
        return statusP3;
    }
    public CheckBox getStatusP4() {
        return statusP4;
    }

    public void storeTeeTime(){
        SqliteConnection.createTeeTime(String.valueOf(time), name, amtPlayers, String.valueOf(date), convertbooltoint(checkedIn), memNum);
    }

    public void pullTeeTimeName(LocalTime Ttime, LocalDate Tdate) throws SQLException {
        SqliteConnection sqlite = new SqliteConnection();
        name = sqlite.pullCertainTimeName(String.valueOf(Ttime), String.valueOf(Tdate));
    }

    public void pullTeeTimePlayers(LocalTime Ttime, LocalDate Tdate) throws SQLException {
        SqliteConnection sqlite = new SqliteConnection();
        amtPlayers = sqlite.pullCertainTimePlayers(String.valueOf(Ttime), String.valueOf(Tdate));
    }

    public void pullTeeTimeDate(LocalTime Ttime) throws SQLException {
        SqliteConnection sqlite = new SqliteConnection();
        date = LocalDate.parse(sqlite.pullCertainTimeDate(String.valueOf(Ttime)));
    }

    public void pullTeeTimeCheckedIn(LocalTime Ttime, LocalDate Tdate) throws SQLException{
        SqliteConnection sqlite = new SqliteConnection();
        int num = sqlite.pullCertainTimeCheckedIn(String.valueOf(Ttime), String.valueOf(Tdate));
        checkedIn = convertinttobool(num);
    }
    public void pullTeeTimeCheckedInP2(LocalTime Ttime, LocalDate Tdate) throws SQLException{
        SqliteConnection sqlite = new SqliteConnection();
        int num = sqlite.pullCheckedInP2(String.valueOf(Ttime), String.valueOf(Tdate));
        checkedInP2 = convertinttobool(num);
    }
    public void pullTeeTimeCheckedInP3(LocalTime Ttime, LocalDate Tdate) throws SQLException{
        SqliteConnection sqlite = new SqliteConnection();
        int num = sqlite.pullCheckedInP3(String.valueOf(Ttime), String.valueOf(Tdate));
        checkedInP3 = convertinttobool(num);
    }
    public void pullTeeTimeCheckedInP4(LocalTime Ttime, LocalDate Tdate) throws SQLException{
        SqliteConnection sqlite = new SqliteConnection();
        int num = sqlite.pullCheckedInP4(String.valueOf(Ttime), String.valueOf(Tdate));
        checkedInP4 = convertinttobool(num);
    }

    public void pullTeeTimeMemberNumber(LocalTime Ttime, LocalDate Tdate) throws SQLException{
        SqliteConnection sqlite = new SqliteConnection();
        memNum = sqlite.pullCertainTimeMemberNumber(String.valueOf(Ttime), String.valueOf(Tdate));
    }

    public void pullTeeTimeMemberNumberP2(LocalTime Ttime, LocalDate Tdate) throws SQLException{
        SqliteConnection sqlite = new SqliteConnection();
        memNumP2 = sqlite.pullCertainTimeMemberNumberP2(String.valueOf(Ttime), String.valueOf(Tdate));
    }

    public void pullTeeTimeMemberNumberP3(LocalTime Ttime, LocalDate Tdate) throws SQLException{
        SqliteConnection sqlite = new SqliteConnection();
        memNumP3 = sqlite.pullCertainTimeMemberNumberP3(String.valueOf(Ttime), String.valueOf(Tdate));
    }

    public void pullTeeTimeMemberNumberP4(LocalTime Ttime, LocalDate Tdate) throws SQLException{
        SqliteConnection sqlite = new SqliteConnection();
        memNumP4 = sqlite.pullCertainTimeMemberNumberP4(String.valueOf(Ttime), String.valueOf(Tdate));
    }

    public void pullReservationType(){
        if (memNum == 0) {
            memType =  "Guest";
        }else{
            Member member = new Member(memNum);
            memType = member.getMemType();
        }
    }

    public void pullReservationTypeP2(){
        if(memNumP2>-1) {
            if (memNumP2 == 0) {
                memTypeP2 = "Guest";
            } else {
                Member member = new Member(memNumP2);
                memTypeP2 = member.getMemType();
            }
        }
    }

    public void pullReservationTypeP3(){
        if(memNumP3>-1) {
            if (memNumP3 == 0) {
                memTypeP3 = "Guest";
            } else {
                Member member = new Member(memNumP3);
                memTypeP3 = member.getMemType();
            }
        }
    }

    public void pullReservationTypeP4(){
        if(memNumP4>(-1)) {
            if (memNumP4 == 0) {
                memTypeP4 = "Guest";
            } else {
                Member member = new Member(memNumP4);
                memTypeP4 = member.getMemType();
            }
        }else{
            memTypeP4 = "";
        }
    }

    public void pullPlayer2(LocalTime Ttime, LocalDate Tdate){
        player2 = SqliteConnection.pullPlayer2(String.valueOf(Ttime), String.valueOf(Tdate));
    }

    public void pullPlayer3(LocalTime Ttime, LocalDate Tdate){
        player3 = SqliteConnection.pullPlayer3(String.valueOf(Ttime), String.valueOf(Tdate));
    }

    public void pullPlayer4(LocalTime Ttime, LocalDate Tdate){
        player4 = SqliteConnection.pullPlayer4(String.valueOf(Ttime), String.valueOf(Tdate));
    }

    public void deleteTime() throws SQLException {
        SqliteConnection.deleteTime(String.valueOf(getTime()), String.valueOf(getDate()));
    }

    public boolean convertinttobool(int num){
        if(num == 1){
            return true;
        }else{
            return false;
        }
    }

    public int convertbooltoint(boolean flag){
        if(flag){
            return 1;
        }else{
            return 0;
        }
    }

    public boolean checkInTeeTime(){
        SqliteConnection.checkInTime(String.valueOf(time), String.valueOf(date));
        checkedIn = true;
        return true;
    }

    public void setCheckedInP2(){
        SqliteConnection.checkInP2(String.valueOf(time), String.valueOf(date));
        checkedInP2 = true;
    }
    public void setCheckedInP3(){
        SqliteConnection.checkInP3(String.valueOf(time), String.valueOf(date));
        checkedInP3 = true;
    }
    public void setCheckedInP4(){
        SqliteConnection.checkInP4(String.valueOf(time), String.valueOf(date));
        checkedInP4 = true;
    }

    public LocalTime getTime() {
        return time;
    }

    public String getName() {
        return name;
    }

    public int getAmtPlayers() {
        return amtPlayers;
    }

    public boolean isCheckedIn() {
        return checkedIn;
    }

    public boolean isCheckedInP2() {
        return checkedInP2;
    }

    public boolean isCheckedInP3() {
        return checkedInP3;
    }

    public boolean isCheckedInP4() {
        return checkedInP4;
    }

    public LocalDate getDate() { return date; }

    public int getMemNum(){ return memNum; }

    public int getMemNumP2(){ return memNumP2; }
    public int getMemNumP3(){ return memNumP3; }


    public String getMemType(){ return memType; }
    public String getMemTypeP2(){ return memTypeP2; }
    public String getMemTypeP3(){ return memTypeP3; }
    public String getMemTypeP4(){ return memTypeP4; }

    public String getPlayer2() {
        return player2;
    }

    public void setPlayer2(String player2) {
        this.player2 = player2;
        SqliteConnection.addPlayer2(player2, String.valueOf(time), String.valueOf(date));
    }

    public String getPlayer3() {
        return player3;
    }

    public void setPlayer3(String player3) {
        this.player3 = player3;
        SqliteConnection.addPlayer3(player3, String.valueOf(time), String.valueOf(date));
    }

    public String getPlayer4() {
        return player4;
    }

    public void setPlayer4(String player4) {
        this.player4 = player4;
        SqliteConnection.addPlayer4(player4, String.valueOf(time), String.valueOf(date));
    }

    public void setMemNumP2(int memNumP2){
        this.memNumP2 = memNumP2;
        SqliteConnection.addMemNum2(memNumP2, String.valueOf(time), String.valueOf(date));
    }
    public void setMemNumP3(int memNumP3){
        this.memNumP3 = memNumP3;
        SqliteConnection.addMemNum3(memNumP3, String.valueOf(time), String.valueOf(date));
    }
    public void setMemNumP4(int memNumP4){
        this.memNumP4 = memNumP4;
        SqliteConnection.addMemNum4(memNumP4, String.valueOf(time), String.valueOf(date));
    }
}
