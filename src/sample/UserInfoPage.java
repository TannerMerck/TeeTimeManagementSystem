package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserInfoPage {
    static TextField usernameField;
    static PasswordField passwordField;
    static String uname = "";
    static String permVal = "shop";
    static ComboBox permissionField;
    static PasswordField confirmPassword;
    static Stage window;

    //displays window for information of user object to create
    public static String load(){
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Add New User");
        window.setMinWidth(300);

        ObservableList<String> options = FXCollections.observableArrayList(
                "shop",
                "admin"
        );
        Label unameLabel = new Label("Enter Username:");
        usernameField = new TextField();
        usernameField.setPromptText("Enter Username");
        Label passLabel = new Label("Enter Password:");
        Label confirmLabel = new Label("Confirm Password:");
        passwordField = new PasswordField();
        passwordField.setPromptText("Enter Password");
        confirmPassword = new PasswordField();
        confirmPassword.setPromptText("Confirm Password");
        permissionField = new ComboBox(options);
        Label permLabel = new Label("Select Permissions:");
        permLabel.getStyleClass().add("label-login");

        Button createUserBtn = new Button("Create User");
        Button cancelBtn = new Button("Cancel");

        createUserBtn.setOnAction(e-> {
            createUser();
        });
        cancelBtn.setOnAction(e -> {
            window.close();
        });

        VBox layout = new VBox(10);
        HBox btnLayout = new HBox(10);
        btnLayout.getChildren().addAll(cancelBtn, createUserBtn);
        btnLayout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(unameLabel, usernameField, passLabel, passwordField, confirmLabel, confirmPassword, permLabel, /*adminPerm, shopPerm,*/ permissionField, btnLayout);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 500, 500);
        scene.getStylesheets().add("sample/TeeTimeStyleSheet.css");
        window.setScene(scene);
        window.showAndWait();
        return uname;
    }
    //creates the user object from the fields provided
    public static void createUser(){
        String username = usernameField.getText();
        String password = passwordField.getText();
        String confirm = confirmPassword.getText();
        if(password.equals(confirm)){
            if(permissionField.getSelectionModel().isEmpty() || usernameField.getText().isEmpty() || passwordField.getText().isEmpty() || confirmPassword.getText().isEmpty()){
                ErrorPopup.load("Please fill out all fields");
            }else {
                String hashed = generateHash(password);
                permVal = (permissionField.getSelectionModel().getSelectedItem()).toString();
                uname = username;
                User user = new User(username, hashed, permVal);
                window.close();
            }
        }else{
            ErrorPopup.load("Passwords not the same");
        }

    }
    //hashes the password
    public static String generateHash(String input) {
        StringBuilder hash = new StringBuilder();

        try {
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            byte[] hashedBytes = sha.digest(input.getBytes());
            char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                    'a', 'b', 'c', 'd', 'e', 'f' };
            for (int idx = 0; idx < hashedBytes.length; idx++) {
                byte b = hashedBytes[idx];
                hash.append(digits[(b & 0xf0) >> 4]);
                hash.append(digits[b & 0x0f]);
            }
        } catch (NoSuchAlgorithmException e) {

        }

        return hash.toString();
    }
}
