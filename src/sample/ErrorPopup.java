package sample;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ErrorPopup {
    //Accepts String that is displayed in the error popup
    public static void load(String output){
        setupGUI(output);
    }
    public static void setupGUI(String output){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Error");
        window.setMinWidth(300);
        Label errorMSG = new Label();
        errorMSG.setText(output);

        Button okBtn = new Button("OK");
        okBtn.setOnAction(e -> {
            window.close();
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(errorMSG, okBtn);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout, 200, 200);
        scene.getStylesheets().add("sample/TeeTimeStyleSheet.css");
        window.setScene(scene);
        window.showAndWait();

    }

}
