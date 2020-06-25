package sample;

import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class LoginPage {
    static TextField usernameBox;
    static PasswordField passwordBox;
    //Displays login page
    public static void load(Stage primaryStage){
        usernameBox = new TextField();
        usernameBox.setPromptText("Username");
        passwordBox = new PasswordField();
        passwordBox.setPromptText("Password");

        Label usernameLabel = new Label("Enter Username:");
        Label passwordLabel = new Label("Enter Password:");
        usernameLabel.getStyleClass().add("label-login");
        passwordLabel.getStyleClass().add("label-login");

        Button loginBtn = new Button("Login");
        loginBtn.setOnAction(e -> {
            String permission = null;
            try {
                permission = attemptLogin(usernameBox.getText(), passwordBox.getText());
            } catch (UnsupportedEncodingException ex) {
                ex.printStackTrace();
            } catch (NoSuchAlgorithmException ex) {
                ex.printStackTrace();
            }
            if(permission.equals("admin")) {
                try {
                    TeeSheetPage.load(primaryStage, permission);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }else if(permission.equals("shop")){
                //Call teeSheet Page
                try {
                    TeeSheetPage.load(primaryStage, permission);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }else{
                System.out.println("Incorrect Login");
                usernameBox.clear();
                passwordBox.clear();
            }
        });
        VBox items = new VBox(10);
        items.getChildren().addAll(usernameLabel, usernameBox, passwordLabel, passwordBox, loginBtn);
        BorderPane loginLayout = new BorderPane();
        items.setAlignment(Pos.CENTER);
        loginLayout.setCenter(items);
        Scene loginScene = new Scene(loginLayout, 1500, 1500);
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        primaryStage.setWidth(bounds.getWidth());
        primaryStage.setHeight(bounds.getHeight());
        primaryStage.setMaximized(true);
        primaryStage.setScene(loginScene);
        primaryStage.setTitle("Tee Time Management System");
        primaryStage.show();
        loginScene.getStylesheets().add("sample/TeeTimeStyleSheet.css");
    }
    //This method calls the login method in the User class and attempts a log in
    public static String attemptLogin(String username, String password) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        User user = new User(username);
        String hashed = generateHash(password);
        if(user.login(username, hashed)){
            return user.getPermissions();
        }else{
            return "error";
        }
    }
    //This method hashes the password inputted by the user so that no plain text password is saved
    public static String generateHash(String input) {
        StringBuilder hash = new StringBuilder();
        try {
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            byte[] hashedBytes = sha.digest(input.getBytes());
            char[] digits = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
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
