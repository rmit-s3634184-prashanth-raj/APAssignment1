package controller;

import gui.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class DeleteProfile 
{
	Main mainObj = new Main();

	@FXML
	private Button goBack;

	@FXML
	private void goBack()
	{
		mainObj.toAndFromNavigation("MainPage");
	}
}
