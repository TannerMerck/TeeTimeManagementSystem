package sample;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CheckInTeeTimePage {
    public static CheckBox boxPlayer1;
    public static CheckBox boxPlayer2;
    public static CheckBox boxPlayer3;
    public static CheckBox boxPlayer4;
    public static Stage window;
    //Displays window to select which player to check in
    public static void load(TeeTime teetime){
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Check In Player");

        Label label = new Label("Select Player(s) to Check In");
        boxPlayer1 = new CheckBox();
        boxPlayer2 = new CheckBox();
        boxPlayer3 = new CheckBox();
        boxPlayer4 = new CheckBox();
        boxPlayer1.getStyleClass().add("visibleBox");
        boxPlayer2.getStyleClass().add("visibleBox");
        boxPlayer3.getStyleClass().add("visibleBox");
        boxPlayer4.getStyleClass().add("visibleBox");
        Text player1 = new Text(teetime.getName());
        Text player2 = new Text(teetime.getPlayer2());
        Text player3 = new Text(teetime.getPlayer3());
        Text player4 = new Text(teetime.getPlayer4());

        Button button = new Button("Check In");

        button.setOnAction(e->{
            checkPlayersIn(teetime);
        });

        VBox layout = new VBox(10);
        if(teetime.getAmtPlayers() == 1){
            layout.getChildren().addAll(label, player1, boxPlayer1, button);
            checkPlayer1(teetime);
        }
        if(teetime.getAmtPlayers() == 2){
            layout.getChildren().addAll(label, player1, boxPlayer1, player2, boxPlayer2, button);
            checkPlayer1(teetime);
            checkPlayer2(teetime);
        }
        if(teetime.getAmtPlayers() == 3){
            layout.getChildren().addAll(label, player1, boxPlayer1, player2, boxPlayer2, player3, boxPlayer3, button);
            checkPlayer1(teetime);
            checkPlayer2(teetime);
            checkPlayer3(teetime);
        }
        if(teetime.getAmtPlayers() == 4){
            layout.getChildren().addAll(label, player1, boxPlayer1, player2, boxPlayer2, player3, boxPlayer3, player4, boxPlayer4, button);
            checkPlayer1(teetime);
            checkPlayer2(teetime);
            checkPlayer3(teetime);
            checkPlayer4(teetime);
        }
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout, 500, 500);
        scene.getStylesheets().add("sample/TeeTimeStyleSheet.css");
        window.setScene(scene);
        window.showAndWait();
    }
    //Checks in the player that has its checkbox checked
    public static void checkPlayersIn(TeeTime teetime){
        if(boxPlayer1.isSelected()){
            teetime.checkInTeeTime();
        }
        if(boxPlayer2.isSelected()){
            teetime.setCheckedInP2();
        }
        if(boxPlayer3.isSelected()){
            teetime.setCheckedInP3();
        }
        if(boxPlayer4.isSelected()){
            teetime.setCheckedInP4();
        }
        window.close();
    }
    //Next four methods check the checkbox of the player if they have already been checked in
    public static void checkPlayer1(TeeTime teetime){
        if(teetime.isCheckedIn()){
            boxPlayer1.setSelected(true);
        }
    }
    public static void checkPlayer2(TeeTime teetime){
        if(teetime.isCheckedInP2()){
            boxPlayer2.setSelected(true);
        }
    }
    public static void checkPlayer3(TeeTime teetime){
        if(teetime.isCheckedInP3()){
            boxPlayer3.setSelected(true);
        }
    }
    public static void checkPlayer4(TeeTime teetime){
        if(teetime.isCheckedInP4()){
            boxPlayer4.setSelected(true);
        }
    }
}
