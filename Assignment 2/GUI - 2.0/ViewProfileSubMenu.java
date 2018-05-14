package GUI;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class ViewProfileSubMenu extends Application {

    Stage window;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("View a Profile");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20, 20, 20, 20));
        grid.setVgap(20);
        grid.setHgap(40);

        Label lbl = new Label("Name");
        GridPane.setConstraints(lbl, 0, 0);

        Label lbl1 = new Label("DOB");
        GridPane.setConstraints(lbl1, 1, 0);

        Label lbl2 = new Label("Adult/Dependent");
        GridPane.setConstraints(lbl2, 0, 1);

        Label lbl3 = new Label("Profile Image");
        GridPane.setConstraints(lbl3, 1, 1);

        Button btn = new Button("Go back to menu");
        GridPane.setConstraints(btn, 1, 2);

        //Add everything to grid
        grid.getChildren().addAll(lbl, lbl1, lbl2, lbl3, btn);

        Scene scene = new Scene(grid, 400, 200);
        window.setScene(scene);
        window.show();
    }


}