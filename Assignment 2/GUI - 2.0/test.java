package GUI;
import javafx.application.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;

public class test extends Application {

    Stage window;
    Scene scene1, scene2;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        
        VBox vb = new VBox();
        vb.setPadding(new Insets(10, 50, 50, 50));
        vb.setSpacing(10);
        vb.setAlignment(Pos.BASELINE_CENTER);

        //Button 1
        Label label1 = new Label("Welcome to the first scene!");
        Button button1 = new Button("Go to scene 2");
        button1.setOnAction(e -> window.setScene(scene2));

        //Layout 1 - children laid out in vertical column
        VBox layout1 = new VBox(20);
        layout1.getChildren().addAll(label1, button1);
        scene1 = new Scene(layout1, 400, 300);


        {
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


        button1.setOnAction(e -> window.setScene(scene1));
        vb.getChildren().add(button1);

        //Layout 2
        

        //Display scene 1 at first
        window.setScene(scene1);
        window.setTitle("Title Here");
        window.show();
    }
    }
}