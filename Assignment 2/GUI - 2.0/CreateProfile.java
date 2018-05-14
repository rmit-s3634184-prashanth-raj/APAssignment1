package GUI;

import java.awt.Checkbox;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class CreateProfile extends Application {

    Stage window;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("Create Profile");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        Label lbl = new Label("Name");
        GridPane.setConstraints(lbl, 0, 0);

        TextField txt = new TextField();
        txt.setPromptText("Enter Here");
        GridPane.setConstraints(txt, 1, 0);

        Label lbl1 = new Label("D.O.B");
        GridPane.setConstraints(lbl1, 0, 1);

        TextField txt1 = new TextField();
        txt1.setPromptText("Enter Here");
        GridPane.setConstraints(txt1, 1, 1);

        Label lbl2 = new Label("Adult/Dependent");
        GridPane.setConstraints(lbl2, 0, 5);

        CheckBox box1 = new CheckBox("Adult");
        CheckBox box2 = new CheckBox("Dependent");
        GridPane.setConstraints(box1,1,5);
        GridPane.setConstraints(box2, 1, 7);
        
        Label lbl3 = new Label("Upload Profile Image");
        GridPane.setConstraints(lbl3, 0, 2);
        
        Button btn2 = new Button ("Upload from drive");
        GridPane.setConstraints(btn2, 1, 2);

        Button btn = new Button("Submit");
        GridPane.setConstraints(btn, 1, 11);

        Button btn1 = new Button ("Go Back");
        GridPane.setConstraints(btn1, 1, 12);
        
        

        //Add everything to grid
        grid.getChildren().addAll(lbl, txt, lbl1, txt1, btn, btn1,box1,box2,lbl2, lbl3, btn2);

        Scene scene1 = new Scene(grid, 500, 300);
        window.setScene(scene1);
        window.show();
    }


}