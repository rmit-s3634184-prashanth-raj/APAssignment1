package GUI;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class MainMenu extends Application {
  public static void main(String[] args) {
    Application.launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    primaryStage.setTitle("MININET");

    VBox vb = new VBox();
    vb.setPadding(new Insets(10, 50, 50, 50));
    vb.setSpacing(10);
    vb.setAlignment(Pos.BASELINE_CENTER);
    

    Label lbl = new Label("Welcome To Mininet Social Network");
    lbl.setFont(Font.font("Amble CN", FontWeight.BOLD, 24));
    vb.getChildren().add(lbl);

    Button btn1 = new Button();
    btn1.setText("Join The Network");
    vb.getChildren().add(btn1);

    Button btn2 = new Button();
    btn2.setText("View A Profile");
    vb.getChildren().add(btn2);

    Button btn3 = new Button();
    btn3.setText("Delete A Profile");
    vb.getChildren().add(btn3);

    Button btn4 = new Button();
    btn4.setText("Find Out Connection Between Two Profiles");
    vb.getChildren().add(btn4);
    
    Button btn5= new Button();
    btn5.setText("View A Profile's Family Members");
    vb.getChildren().add(btn5);
    
    Button btn6= new Button();
    btn6.setText("EXIT");
    vb.getChildren().add(btn6);


    // Adding VBox to the scene
    Scene scene = new Scene(vb);
    primaryStage.setScene(scene);
    primaryStage.show();
    
  }
}