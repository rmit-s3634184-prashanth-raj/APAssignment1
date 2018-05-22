package miniNetGUI;

import java.io.FileNotFoundException;

import iOPackage.TextFile;
import javafx.application.Application;
import javafx.stage.Stage;
import minNetModelPackage.MiniNetModel;
import utility.CreatePeople;
import utility.Driver;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class MiniNet extends Application 
{
	private Scene scene;
	private Stage primaryStage;
	private static BorderPane samplePane,sample2Pane,sample3Pane;

	@Override
	public void start(Stage primaryStage) 
	{
		try 
		{
			this.primaryStage = primaryStage;
			this.primaryStage.setTitle("MiniNet");

			System.out.println("Welcome to miniNet");

			TextFile textFileObj = new TextFile();
			Driver driverObj = new Driver();

			textFileObj.readPeopleTextFile();
			driverObj.addPersonIntoNetwork(1);
			textFileObj.readRelationsTextFile();
			//create connection
			driverObj.createConnectionsFromTxt2();
			driverObj.addPersonIntoNetwork(2);

			showMainView();
		} 
		catch(Exception e) 
		{
			System.out.println("[start]Exception: " + e.getMessage());
		}
	}

	private void showMainView()  
	{
		try
		{
			FXMLLoader loader = new FXMLLoader();
			samplePane = loader.load(getClass().getResource("MainPage.fxml"));
			this.scene = new Scene(samplePane,1000,800);
			this.scene.setRoot(samplePane);
			primaryStage.setScene(this.scene);
			primaryStage.show();
		}
		catch(Exception e)
		{
			System.out.println("[showMainView]Exception: " + e.getMessage());
		}
	}

	public Scene getScene()
	{
		return this.scene;
	}


	public void toAndFromNavigation(String navigateToOrFrom)
	{
		try
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MiniNet.class.getResource(navigateToOrFrom + ".fxml"));
			sample2Pane = loader.load();
			samplePane.setCenter(sample2Pane);
		}
		catch(Exception ex)
		{ 
			System.out.println("[toAndFromNavigation]Exception: " + ex.getMessage());
		}
	}

	public static void main(String[] args) 
	{
		launch(args);
	}
}
