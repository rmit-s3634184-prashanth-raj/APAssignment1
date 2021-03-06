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

	public class FamMemDetails extends Application {

	    Stage window;

	    public static void main(String[] args) {
	        launch(args);
	    }

	    @Override
	    public void start(Stage primaryStage) throws Exception {
	        window = primaryStage;
	        window.setTitle("Family Members Details");

	        GridPane grid = new GridPane();
	        grid.setPadding(new Insets(20, 20, 20, 20));
	        grid.setVgap(20);
	        grid.setHgap(40);

	        Label lbl = new Label("Name1");
	        GridPane.setConstraints(lbl, 0, 0);

	        Label lbl1 = new Label("Name2");
	        GridPane.setConstraints(lbl1, 1, 0);

	       

	        Button btn = new Button("Go back to menu");
	        GridPane.setConstraints(btn, 1, 2);

	        //Add everything to grid
	        grid.getChildren().addAll(lbl, lbl1, btn);

	        Scene scene = new Scene(grid, 400, 200);
	        window.setScene(scene);
	        window.show();
	    }


	}

