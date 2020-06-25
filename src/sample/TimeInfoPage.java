package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class TimeInfoPage {
    static VBox layout;
    static VBox btnLayout;
    static TextField timeName;
    static TextField timeName2;
    static TextField timeName3;
    static TextField timeName4;
    static TeeTime teetime;
    static Stage window;
    static final ObservableList<Member> members = FXCollections.observableArrayList();
    static ListView<String> memberView = new ListView();
    static int amtPlayers = 1;
    static int memNum = 0;
    //displays GUI
    public static TeeTime createTime(String time, String date) throws SQLException {
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Create Tee Time");
        window.setMinWidth(300);

        amtPlayers = 1;
        timeName = new TextField();
        timeName.setPromptText("Enter Player1");
        timeName2 = new TextField();
        timeName2.setPromptText("Enter Player2");
        timeName3 = new TextField();
        timeName3.setPromptText("Enter Player3");
        timeName4 = new TextField();
        timeName4.setPromptText("Enter Player4");
        Text nameTextPrompt = new Text("Enter Name:");
        Text playersTextPrompt = new Text("Select Amount of Players:");


        Button createTimeBtn = new Button("Create Time");
        Button cancelBtn = new Button("Cancel");
        Button onePlayer = new Button("1");
        Button twoPlayer = new Button("2");
        Button threePlayer = new Button("3");
        Button fourPlayer = new Button("4");

        createTimeBtn.setOnAction(e -> validateInput(time, date));
        cancelBtn.setOnAction(e -> window.close());
        onePlayer.setOnAction(e -> onePlayerBtnClicked());
        twoPlayer.setOnAction(e -> twoPlayerBtnClicked());
        threePlayer.setOnAction(e -> threePlayerBtnClicked());
        fourPlayer.setOnAction(e -> fourPlayerBtnClicked());
        populateMembers();
        memberView.setOnMouseClicked(e -> listviewClicked(time, date));
        teetime = new TeeTime(LocalTime.parse(time));

        layout = new VBox(10);
        btnLayout = new VBox(10);
        VBox page = new VBox(10);
        HBox lastBtns = new HBox(10);
        lastBtns.getChildren().addAll(cancelBtn, createTimeBtn);
        lastBtns.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(playersTextPrompt, onePlayer, twoPlayer, threePlayer, fourPlayer, nameTextPrompt, btnLayout);
        layout.setAlignment(Pos.CENTER);
        btnLayout.setAlignment(Pos.CENTER);
        page.setAlignment(Pos.CENTER);
        page.getChildren().addAll(layout, memberView, lastBtns);
        Scene scene = new Scene(page, 800, 800);
        scene.getStylesheets().add("sample/TeeTimeStyleSheet.css");
        window.setScene(scene);
        window.showAndWait();

        return teetime;
    }
    public static void onePlayerBtnClicked(){
        amtPlayers = 1;
        btnLayout.getChildren().clear();
        btnLayout.getChildren().add(timeName);
    }
    public static void twoPlayerBtnClicked(){
        amtPlayers = 2;
        btnLayout.getChildren().clear();
        btnLayout.getChildren().addAll(timeName, timeName2);
    }
    public static void threePlayerBtnClicked(){
        amtPlayers = 3;
        btnLayout.getChildren().clear();
        btnLayout.getChildren().addAll(timeName, timeName2, timeName3);
    }
    public static void fourPlayerBtnClicked(){
        amtPlayers = 4;
        btnLayout.getChildren().clear();
        btnLayout.getChildren().addAll(timeName, timeName2, timeName3, timeName4);
    }
    public static void validateInput(String time, String date){
        boolean valid = true;
        if(amtPlayers>0){
            if(timeName.getText().isEmpty()){
                valid = false;
            }
        }
        if(amtPlayers>1){
            if(timeName2.getText().isEmpty()){
                valid = false;
            }
        }
        if(amtPlayers>2){
            if(timeName3.getText().isEmpty()){
                valid = false;
            }
        }
        if(amtPlayers>3){
            if(timeName4.getText().isEmpty()){
                valid = false;
            }
        }
        if(valid){
            createTimeBtnClicked(time, date);
        }else{
            ErrorPopup.load("Please fill in all fields");
        }
    }
    //creates tee time
    public static void createTimeBtnClicked(String time, String date){
        String name = timeName.getText();
        Member mem = new Member(timeName.getText());
        int memberNumber = mem.getMemNum();
        teetime = new TeeTime(LocalTime.parse(time), name, amtPlayers, LocalDate.parse(date), false, memberNumber);
        teetime.setstatusP2(false);
        teetime.setstatusP3(false);
        teetime.setstatusP4(false);
        if(amtPlayers > 1){
            teetime.setPlayer2(timeName2.getText());
            Member member = new Member(timeName2.getText());
            teetime.setMemNumP2(member.getMemNum());
            teetime.pullReservationTypeP2();
        }
        if(amtPlayers > 2){
            teetime.setPlayer3(timeName3.getText());
            Member member = new Member(timeName3.getText());
            teetime.setMemNumP3(member.getMemNum());
            teetime.pullReservationTypeP3();
        }
        if(amtPlayers > 3){
            teetime.setPlayer4(timeName4.getText());
            Member member = new Member(timeName4.getText());
            teetime.setMemNumP4(member.getMemNum());
            teetime.pullReservationTypeP4();
        }
        window.close();
    }
    //populates observable list of members
    public static void populateMembers() throws SQLException {
        if(members.size()==0) {
            SqliteConnection sqlite = new SqliteConnection();
            List<String> memberList;
            memberList = sqlite.pullMember();
            for (int counter = 0; counter < memberList.size(); counter++) {
                members.add(new Member(memberList.get(counter)));
            }
            populateMembersView();
        }
    }
    //populates listview with observable list
    public static void populateMembersView(){
        for(int counter = 0; counter<members.size(); counter++){
            Member member = members.get(counter);
            memberView.getItems().add(member.getName());
        }
    }
    //populates the first open textfield with the member clicked from the listview
    public static void listviewClicked(String time, String date){
        String name = memberView.getSelectionModel().getSelectedItem();
        int Index = memberView.getSelectionModel().getSelectedIndex();
        Member member = members.get(Index);
        memNum = member.getMemNum();
        TextField field = findEmptyField();
        field.setText(member.getName());
        //teetime = new TeeTime(LocalTime.parse(time), name, amtPlayers, LocalDate.parse(date), false, memNum);
    }
    //finds the empty field for the above method to populate
    public static TextField findEmptyField(){
        if(timeName.getText().trim().isEmpty()){
            return timeName;
        }else if(timeName2.getText().trim().isEmpty()){
            return timeName2;
        }else if(timeName3.getText().trim().isEmpty()){
            return timeName3;
        }else if(timeName4.getText().trim().isEmpty()){
            return timeName4;
        }
        return timeName4;
    }
    public static void closeWindow(){
        window.close();
    }
}
