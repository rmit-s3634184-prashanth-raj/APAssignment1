package GUI;


	import javafx.application.Application;
	import javafx.geometry.Insets;
	import javafx.scene.Scene;
	import javafx.scene.control.Button;
	import javafx.scene.control.Label;
	import javafx.scene.control.TextField;
	import javafx.scene.layout.GridPane;
	import javafx.stage.Stage;

	public class ConnectionType extends Application {

	    Stage window;

	    public static void main(String[] args) {
	        launch(args);
	    }

	    @Override
	    public void start(Stage primaryStage) throws Exception {
	        window = primaryStage;
	        window.setTitle("Connection Type");

	        //GridPane with 10px padding around edge
	        GridPane grid = new GridPane();
	        grid.setPadding(new Insets(10, 10, 10, 10));
	        grid.setVgap(8);
	        grid.setHgap(10);

	        //Name Label - constrains use (child, column, row)
	        Label lbl = new Label(" Connection Type is: ");
	        GridPane.setConstraints(lbl, 0, 0);
	        
	        //result of connection type
	        Label lb2 = new Label(" Result ");
	        GridPane.setConstraints(lb2, 1, 0 );


	        
	        Button btn1 = new Button ("Go Back");
	        GridPane.setConstraints(btn1, 1, 3);

	        //Add everything to grid
	        grid.getChildren().addAll(lbl, lb2, btn1);

	        Scene scene = new Scene(grid, 300, 200);
	        window.setScene(scene);
	        window.show();
	    }
	}

