package sample;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import java.sql.SQLException;

public class AdminMenuBar {
    //Displays Admin menu bar
    public static MenuBar load(Stage primaryStage){
        Menu fileMenu = new Menu("File");
        Menu viewMenu = new Menu("View");

        MenuItem newMember = new MenuItem("Add New Member...");
        MenuItem newUser = new MenuItem("Add New User...");
        MenuItem logout = new MenuItem("Logout");
        fileMenu.getItems().addAll(newMember, newUser, logout);

        MenuItem viewTeeSheet = new MenuItem("Tee Sheet");
        MenuItem viewMembers = new MenuItem("Members");
        MenuItem viewUsers = new MenuItem("Users");
        viewMenu.getItems().addAll(viewTeeSheet, viewMembers, viewUsers);

        newMember.setOnAction(e -> {
            try {
                addMember();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        viewTeeSheet.setOnAction(e -> {
            TeeSheetPage.open(primaryStage, "admin");
        });
        viewMembers.setOnAction(e -> {
            try {
                MembershipPage.load(primaryStage, "admin");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        viewUsers.setOnAction(e -> {
            try {
                UsersDisplay.load(primaryStage, "admin");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        newUser.setOnAction(e -> UserInfoPage.load());
        logout.setOnAction(e -> {
            primaryStage.close();
            //LoginPage.load(primaryStage);
        });


        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu, viewMenu);

        return menuBar;
    }

    public static void addMember() throws SQLException {
        MemberPage.addNewMember();
    }

}
