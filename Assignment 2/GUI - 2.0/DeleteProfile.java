package GUI;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class DeleteProfile extends Application {
  
	Stage window;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle(" Delete A Profile ");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        Label lbl = new Label(" Enter A Profile Name To Be Deleted ");
        GridPane.setConstraints(lbl, 0, 0);

        TextField txt = new TextField();
        txt.setPromptText("Enter Here");
        GridPane.setConstraints(txt, 1, 0);

        Button btn = new Button("Submit");
        GridPane.setConstraints(btn, 1, 3);

        Button btn1 = new Button ("Go Back");
        GridPane.setConstraints(btn1, 1, 4);

        btn.setOnAction(e -> {
            boolean result = DelConfirmBox.display("Are You Sure You Want To Delete The Profile", null);
            System.out.println(result);
        });
        
        //Add everything to grid
        grid.getChildren().addAll(lbl, txt, btn, btn1);

        Scene scene = new Scene(grid, 400, 200);
        window.setScene(scene);
        window.show();
    }
}
