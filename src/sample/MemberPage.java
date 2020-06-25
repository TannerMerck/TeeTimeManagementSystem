package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.SQLException;

public class MemberPage {
    static Stage window;
    static TextField memNameField;
    static TextField phoneNumField;
    static TextField addressField;
    static ComboBox memTypeField;
    static int memberNum = 0;
    //Displays the add new member page
    public static int addNewMember() throws SQLException {
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Add New Member");
        window.setMinWidth(300);

        ObservableList<String> options = FXCollections.observableArrayList(
                "Social",
                "Single",
                "Couple",
                "Family",
                "Corporate"
        );

        memNameField = new TextField();
        memNameField.setPromptText("Name");
        phoneNumField = new TextField();
        phoneNumField.setPromptText("Phone Number");
        addressField = new TextField();
        addressField.setPromptText("Address");
        memTypeField = new ComboBox(options);
        memTypeField.setPromptText("Type");

        Text namePrompt = new Text("Enter Member Name");
        Text phoneNumPrompt = new Text("Enter Phone Number");
        Text addressPrompt = new Text("Enter Address");
        Text typePrompt = new Text("Enter Membership Type");

        Button addMemberBtn = new Button("Add Member");
        Button cancelBtn = new Button("Cancel");
        addMemberBtn.setOnAction(e -> addMemberToDB());
        cancelBtn.setOnAction(e -> window.close());

        VBox layout = new VBox(10);
        VBox layout1 = new VBox(10);
        HBox btnLayout = new HBox(10);
        btnLayout.getChildren().addAll(cancelBtn, addMemberBtn);
        btnLayout.setAlignment(Pos.CENTER);
        layout1.getChildren().addAll(namePrompt, memNameField, phoneNumPrompt, phoneNumField, addressPrompt, addressField, typePrompt, memTypeField);
        layout.getChildren().addAll(layout1, btnLayout);
        layout.setAlignment(Pos.CENTER);
        layout1.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout, 500, 500);
        window.setTitle("Add Member");
        scene.getStylesheets().add("sample/TeeTimeStyleSheet.css");
        window.setScene(scene);
        window.showAndWait();
        return memberNum;
    }
    //adds member to database
    public static void addMemberToDB(){
        if(memNameField.getText().isEmpty() || phoneNumField.getText().isEmpty() || addressField.getText().isEmpty() || memTypeField.getSelectionModel().isEmpty()){
            ErrorPopup.load("Please fill in all fields");
        }else{
            String memName = memNameField.getText();
            String phoneNum = phoneNumField.getText();
            String address = addressField.getText();
            String memType = (memTypeField.getSelectionModel().getSelectedItem()).toString();
            Member member = new Member(memName, phoneNum, address, memType);
            memberNum = member.getMemNum();
            window.close();
        }

    }
}
