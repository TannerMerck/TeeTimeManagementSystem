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

public class MembershipPage {
    static TableView<Member> table = new TableView<Member>();
    final static ObservableList<Member> data = FXCollections.observableArrayList();
    public static void load(Stage primaryStage, String permissions) throws SQLException {
        populateOL();
        displayTable();
        setupGUI(primaryStage, permissions);
    }
    //displays Members in table
    public static void setupGUI(Stage primaryStage, String permissions){
        Button addMember = new Button("Create New Member");
        addMember.setOnAction(e -> {
            try{
                addMemberButtonClicked();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
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
            elements.getChildren().add(addMember);
        }
        layout.getChildren().addAll(menuBar, elements);
        Scene membership = new Scene(layout, 800, 800);
        elements.setAlignment(Pos.CENTER);
        primaryStage.setMaximized(true);
        primaryStage.setScene(membership);
        primaryStage.setTitle("Tee Time Management System");
        membership.getStylesheets().add("sample/TeeTimeStyleSheet.css");
    }
    //creates TableView to display members
    public static void displayTable(){
        table.setEditable(true);
        TableColumn<Member, String> memNumcol = new TableColumn("Member Number");
        memNumcol.setCellValueFactory(new PropertyValueFactory<Member, String>("memNum"));
        TableColumn<Member, String> namecol = new TableColumn("Member Name");
        namecol.setCellValueFactory(new PropertyValueFactory<Member, String>("name"));
        TableColumn<Member, String> phoneNumcol = new TableColumn("Phone Number");
        phoneNumcol.setCellValueFactory(new PropertyValueFactory<Member, String>("phoneNum"));
        TableColumn<Member, String> addresscol = new TableColumn("Address");
        addresscol.setCellValueFactory(new PropertyValueFactory<Member, String>("address"));
        TableColumn<Member, String> memTypecol = new TableColumn("Member Type");
        memTypecol.setCellValueFactory(new PropertyValueFactory<Member, String>("memType"));
        memNumcol.setMinWidth(100);
        namecol.setMinWidth(200);
        phoneNumcol.setMinWidth(200);
        addresscol.setMinWidth(200);
        memTypecol.setMinWidth(200);
        table.setItems(data);
        table.getColumns().addAll(memNumcol, namecol, phoneNumcol, addresscol, memTypecol);
        table.setMaxWidth(900);
        table.setMinHeight(600);
    }
    //populates Observable List with the members from the database
    // by creating member objects by members present in database
    public static void populateOL() throws SQLException {
        List<Integer> timelist;
        SqliteConnection sqlite = new SqliteConnection();
        timelist = sqlite.pullMemberByNumber();
        for (int counter = 0; counter < timelist.size(); counter++) {
            data.add(new Member(timelist.get(counter)));
        }
    }
    //adds new member to table after it is created from MemberPage class
    public static void addMemberButtonClicked() throws SQLException {
        int memberNumber = MemberPage.addNewMember();
        if(memberNumber == 0){

        }else{
            data.add(new Member(memberNumber));
        }
    }
}
