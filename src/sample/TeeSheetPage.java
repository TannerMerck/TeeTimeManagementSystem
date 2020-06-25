package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

public class TeeSheetPage {
    public static void load(Stage primaryStage, String permissions) throws SQLException {
        addMenuBar(primaryStage, permissions);
        initialize();
        checkDBConnection();
        createTableView();
        populateOpenTimes();
        populateBookedTimes();
        //styleCheckInRow();
        setupGUI(primaryStage);
    }
    //Called from menu bar to prevent table glitch where table is repeated on itself
    public static void open(Stage primaryStage, String permissions){
        addMenuBar(primaryStage, permissions);
        setupGUI(primaryStage);
    }
    //Adds the menu bar to the GUI depending on permissions
    public static void addMenuBar(Stage primaryStage, String permissions){
        if(permissions.equals("admin")){
            menuBar = AdminMenuBar.load(primaryStage);
        }else if(permissions.equals("shop")){
            menuBar = ShopMenuBar.load(primaryStage);
        }
    }

    public static void initialize(){
        addTime = new Button("Create Time");
        changeDate = new Button("Display Date");
        deleteTime = new Button("Delete Time");
        checkInTime = new Button("Check In Time");
        displayToday = new Button("Today's Date");
        duplicateTime = new Text();
        timeBox = new HBox();
        addName = new Text();
        showDateDisplayed = new Text(String.valueOf(displayedDate));
        SelectDate = new TextField(String.valueOf(LocalDate.now()));
        SelectDate.setPromptText("Display Date");
    }

    public static void checkDBConnection(){
        if(dbcontroller.isDBConnected()){
            System.out.println("Connected to DB successfully");
        }else{
            System.out.println("Error Connecting");
        }
    }
    //creates table view
    //commented columns are included because they may be used for future additions
    public static void createTableView() throws SQLException {
        table.setEditable(true);
        TableColumn<TeeTime, LocalTime> teetimecol = new TableColumn("Time");
        teetimecol.setCellValueFactory(new PropertyValueFactory<TeeTime, LocalTime>("time"));
//        TableColumn<TeeTime, LocalDate> teedatecol = new TableColumn("Date");
//        teedatecol.setCellValueFactory(new PropertyValueFactory<TeeTime, LocalDate>("date"));
        TableColumn<TeeTime, String> playerscol = new TableColumn("Players");
        playerscol.setCellValueFactory(new PropertyValueFactory<TeeTime, String>("amtPlayers"));


        TableColumn<TeeTime, CheckBox> checkBoxcol = new TableColumn("Box");

        TableColumn<TeeTime, String> playerTestcol = new TableColumn("Player1");
        TableColumn<TeeTime, String> playername = new TableColumn("Name");
        playername.setCellValueFactory(new PropertyValueFactory<TeeTime, String>("name"));
        TableColumn<TeeTime, CheckBox> playerstatus = new TableColumn("Status");
        playerstatus.setCellValueFactory(new PropertyValueFactory<TeeTime, CheckBox>("statusP1"));
        TableColumn<TeeTime, String> playertype = new TableColumn("Type");
        playertype.setCellValueFactory(new PropertyValueFactory<TeeTime, String>("memType"));

        TableColumn<TeeTime, String> player2Testcol = new TableColumn("Player2");
        TableColumn<TeeTime, String> player2name = new TableColumn("Name");
        player2name.setCellValueFactory(new PropertyValueFactory<TeeTime, String>("player2"));
        TableColumn<TeeTime, CheckBox> player2status = new TableColumn("Status");
        player2status.setCellValueFactory(new PropertyValueFactory<TeeTime, CheckBox>("statusP2"));
        TableColumn<TeeTime, String> player2type = new TableColumn("Type");
        player2type.setCellValueFactory(new PropertyValueFactory<TeeTime, String>("memTypeP2"));

        TableColumn<TeeTime, String> player3Testcol = new TableColumn("Player3");
        TableColumn<TeeTime, String> player3name = new TableColumn("Name");
        player3name.setCellValueFactory(new PropertyValueFactory<TeeTime, String>("player3"));
        TableColumn<TeeTime, CheckBox> player3status = new TableColumn("Status");
        player3status.setCellValueFactory(new PropertyValueFactory<TeeTime, CheckBox>("statusP3"));
        TableColumn<TeeTime, String> player3type = new TableColumn("Type");
        player3type.setCellValueFactory(new PropertyValueFactory<TeeTime, String>("memTypeP3"));

        TableColumn<TeeTime, String> player4Testcol = new TableColumn("Player4");
        player4Testcol.getStyleClass().add("playerCol");
        TableColumn<TeeTime, String> player4name = new TableColumn("Name");
        player4name.setCellValueFactory(new PropertyValueFactory<TeeTime, String>("player4"));
        TableColumn<TeeTime, CheckBox> player4status = new TableColumn("Status");
        player4status.setCellValueFactory(new PropertyValueFactory<TeeTime, CheckBox>("statusP4"));
        TableColumn<TeeTime, String> player4type = new TableColumn("Type");
        player4type.setCellValueFactory(new PropertyValueFactory<TeeTime, String>("memTypeP4"));

        //teedatecol.setMinWidth(200);
        teetimecol.setMinWidth(100);
        playerscol.setMinWidth(50);
        checkBoxcol.setMinWidth(100);
        playername.setMinWidth(200);
        playertype.setMinWidth(100);
        playerstatus.setMinWidth(50);
        player2name.setMinWidth(200);
        player2type.setMinWidth(100);
        player2status.setMinWidth(50);
        player3name.setMinWidth(200);
        player3type.setMinWidth(100);
        player3status.setMinWidth(50);
        player4name.setMinWidth(200);
        player4type.setMinWidth(100);
        player4status.setMinWidth(50);
        table.setItems(data);
        table.getColumns().addAll(teetimecol, /*teedatecol,*/  playerscol , playerTestcol, player2Testcol, player3Testcol, player4Testcol);
        playerTestcol.getColumns().addAll(playername, playertype, playerstatus);
        player2Testcol.getColumns().addAll(player2name, player2type, player2status);
        player3Testcol.getColumns().addAll(player3name, player3type, player3status);
        player4Testcol.getColumns().addAll(player4name, player4type, player4status);
        table.setMaxWidth(1600);
        table.setPrefWidth(1600);
        table.setPrefHeight(800);
    }
    //Populates observable list with blank times in the tee sheet for every time
    public static void populateOpenTimes(){
        int hour;
        LocalTime Ttime;
        for(hour=8; hour<13; hour++) {
            for (int min = 0; min < 60; min += 10) {
                Ttime = LocalTime.of(hour, min);
                data.add(new TeeTime(Ttime));
            }
        }
        for(hour=1; hour<6; hour++) {
            for (int min = 0; min < 60; min += 10) {
                Ttime = LocalTime.of(hour, min);
                data.add(new TeeTime(Ttime));
            }
        }
    }
    //Populates observable list with times that are booked
    public static void populateBookedTimes() throws SQLException {
        for(int i=0; i<data.size(); i++){
            TeeTime teetime = data.get(i);
            if(SqliteConnection.checkTime(String.valueOf(teetime.getTime()), String.valueOf(displayedDate))){
                TeeTime ttime = new TeeTime(teetime.getTime(), displayedDate);
                data.remove(i);
                data.add(i,ttime);
            }
        }
    }
    //Displays tee sheet GUI
    public static void setupGUI(Stage primaryStage){
        addTime.setOnAction(e -> {
            try {
                addTimeButtonClicked();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        changeDate.setOnAction(e -> {
            try {
                changeDateButtonClicked();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        deleteTime.setOnAction(e -> {
            try {
                deleteTimeButtonClicked();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        checkInTime.setOnAction(e -> {
            try{
                checkInTimeButtonClicked();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        displayToday.setOnAction(e -> {
            try {
                displayTodayButtonCLicked();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        VBox layout = new VBox(10);
        VBox page = new VBox(10);
        VBox buttonLayout = new VBox(20);
        HBox dateBox = new HBox(10);
        dateBox.getStyleClass().add("date-Display");
        BorderPane pane = new BorderPane();
        dateBox.getChildren().addAll(SelectDate, changeDate, displayToday);
        page.getChildren().addAll(menuBar, dateBox);
        buttonLayout.getChildren().addAll(addTime, deleteTime, checkInTime);
        layout.setPadding(new Insets(20, 20, 20, 20));
        buttonLayout.setPadding(new Insets(20, 20, 20, 20));
        layout.getChildren().addAll(table, duplicateTime);
        pane.setTop(page);
        pane.setLeft(buttonLayout);
        pane.setCenter(layout);

        Scene scene = new Scene(pane, 1500, 1500);
        primaryStage.setMaximized(true);
        primaryStage.setScene(scene);
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        primaryStage.setWidth(bounds.getWidth());
        primaryStage.setHeight(bounds.getHeight());
        primaryStage.setTitle("Tee Time Management System");
        scene.getStylesheets().add("sample/TeeTimeStyleSheet.css");
        primaryStage.show();

    }

    //Changes the tee sheet display to the date specified by the user
    public static void changeDateButtonClicked() throws SQLException {
        data.clear();
        displayedDate = LocalDate.parse(SelectDate.getText());
        populateOpenTimes();
        populateBookedTimes();
        showDateDisplayed.setText(String.valueOf(displayedDate));
    }
    //Checks if a time is selected before attempting to prompt user for information on tee time
    public static void addTimeButtonClicked() throws SQLException {
        if(table.getSelectionModel().getSelectedItem()==null){
            ErrorPopup.load("Please Select a Time");
        }else{
            addTeeTime();
        }
    }
    //Displays the TimeInfoPage class to prompt user for information about tee time to create TeeTime object
    public static void addTeeTime() throws SQLException {
        TeeTime teetime = table.getSelectionModel().getSelectedItem();
        String time = String.valueOf(teetime.getTime());
        String date = String.valueOf(displayedDate);
        int index = table.getSelectionModel().getSelectedIndex();
        SqliteConnection sqlite = new SqliteConnection();
        if(!sqlite.checkTime(time, date)){
            TeeTime teetime2 = TimeInfoPage.createTime(time, date);
            data.remove(index);
            data.add(index, teetime2);
        }else{
            duplicateTime.setText(time + " already exists");
        }
    }

    public static void deleteTimeButtonClicked() throws SQLException {
        if(table.getSelectionModel().getSelectedItem()==null){
            ErrorPopup.load("Please Select a Time");
        }else{
            deleteTeeTime();
        }
    }
    public static void deleteTeeTime() throws SQLException {
        TeeTime teetime = table.getSelectionModel().getSelectedItem();
        LocalTime time = teetime.getTime();
        int index = table.getSelectionModel().getSelectedIndex();
        teetime.deleteTime();
        data.remove(index);
        data.add(index, new TeeTime(time));
    }
    public static void checkInTimeButtonClicked() throws SQLException{
        if(table.getSelectionModel().getSelectedItem()==null){
            ErrorPopup.load("Please Select a Time");
        }else{
            checkInTeeTime();
        }
    }

    public static void checkInTeeTime() throws SQLException {
        TeeTime teetime = table.getSelectionModel().getSelectedItem();
        int index = table.getSelectionModel().getSelectedIndex();
        //teetime.checkInTeeTime();
        CheckInTeeTimePage.load(teetime);
        TeeTime Ttime = new TeeTime(teetime.getTime(), teetime.getDate());
        data.remove(index);
        data.add(index, Ttime);
        //styleCheckInRow();
    }
    //Displays the current days tee sheet
    public static void displayTodayButtonCLicked() throws SQLException {
        SelectDate.setText(String.valueOf(LocalDate.now()));
        changeDateButtonClicked();
    }

    static TableView<TeeTime> table = new TableView<TeeTime>();
    static MenuBar menuBar = new MenuBar();
    static Button addTime;
    static Button changeDate;
    static Button deleteTime;
    static Button checkInTime;
    static Button displayToday;
    static Text duplicateTime;
    static Text showDateDisplayed;
    static TextField SelectDate;
    static HBox timeBox;
    static Text addName;
    static LocalDate displayedDate = LocalDate.now();
    final static ObservableList<TeeTime> data = FXCollections.observableArrayList();
    public static DBController dbcontroller = new DBController();
}
