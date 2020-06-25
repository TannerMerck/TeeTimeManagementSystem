package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.List;

public class UsersDisplay {
    static TableView<User> table = new TableView<User>();
    final static ObservableList<User> data = FXCollections.observableArrayList();

    public static void load(Stage primaryStage, String permissions) throws SQLException {
        populateOL();
        displayTable();
        setupGUI(primaryStage, permissions);
    }
    //displays the GUI of users table
    public static void setupGUI(Stage primaryStage, String permissions){
        Button addUser = new Button("Create New User");
        addUser.setOnAction(e -> {
            createUserButtonClicked();
        });

        MenuBar menuBar = new MenuBar();
        if(permissions.equals("admin")){
            menuBar = AdminMenuBar.load(primaryStage);
        }else if(permissions.equals("shop")){
            menuBar = ShopMenuBar.load(primaryStage);
        }


        VBox layout = new VBox(10);
        VBox elements = new VBox(10);
        elements.getChildren().addAll(table);
        if(permissions.equals("admin")){
            elements.getChildren().add(addUser);
        }
        layout.getChildren().addAll(menuBar, elements);
        elements.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout, 800, 800);
        primaryStage.setMaximized(true);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Tee Time Management System");
        scene.getStylesheets().add("sample/TeeTimeStyleSheet.css");
    }
    //creates the table of users
    public static void displayTable(){
        table.setEditable(true);
        TableColumn<User, String> usernamecol = new TableColumn("Username");
        usernamecol.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
        TableColumn<User, String> permissionscol = new TableColumn("Permissions");
        permissionscol.setCellValueFactory(new PropertyValueFactory<User, String>("permissions"));
        usernamecol.setMinWidth(200);
        permissionscol.setMinWidth(200);
        table.setItems(data);
        table.getColumns().addAll(usernamecol, permissionscol);
        table.setMaxWidth(400);
        table.setMinHeight(600);
    }
    //populates the observable list with user objects used by the table
    public static void populateOL() throws SQLException {
        List<String> timelist;
        SqliteConnection sqlite = new SqliteConnection();
        timelist = sqlite.pullUsers();
        for (int counter = 0; counter < timelist.size(); counter++) {
            data.add(new User(timelist.get(counter)));
        }
    }
    //calls the UserDisplay window to get information for creating User Object
    public static void createUserButtonClicked(){
        String uname = UserInfoPage.load();
        if(uname.equals("")){

        }else{
            data.add(new User(uname));
        }
    }
}
