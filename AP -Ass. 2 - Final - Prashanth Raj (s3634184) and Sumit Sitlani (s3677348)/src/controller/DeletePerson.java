package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import miniNetGUI.MiniNet;
import utility.CreatePeople;
import utility.CustomExecption;
import utility.Driver;

public class DeletePerson 
{
	MiniNet mainObj = new MiniNet();

	@FXML
	private Button goBack;
	
	@FXML
	private ChoiceBox<String> dpPersonOne;
	
	@FXML
	ObservableList<String> personList = FXCollections.observableArrayList();
	String personOneName = "", personOneId = "";
	
	@FXML
	private TextArea dpTextArea;

	@SuppressWarnings("unchecked")
	@FXML
	private void initialize() 
	{
		try
		{
			for(int i = 0; i < CreatePeople.getListOfAdult().size(); i ++) 
			{
				personList.add(CreatePeople.getListOfAdult().get(i).getName());
			}
			for(int i = 0; i < CreatePeople.getListOfDependent().size(); i ++)
			{
				personList.add(CreatePeople.getListOfDependent().get(i).getName());
			}
			dpPersonOne.setItems(personList);
			
			dpPersonOne.getSelectionModel().selectedItemProperty().addListener( new ChangeListener<String>()
			{
				@Override
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) 
				{
					personOneName = newValue;
					dpTextArea.setText("");
					dpTextArea.appendText("\n1. Person Name : " + personOneName +" is selected");
				}
			});
			
		}
		catch(Exception ex)
		{
			dpTextArea.appendText("\n[DeletePerson]initialize: " + ex.getMessage());
		}
	}
	
	@FXML
	private void deletePerson()
	{
		try
		{
			dpTextArea.setText("");
			Driver.deletePerson(personOneName);
			dpTextArea.appendText("\n" + personOneName + " is deleted");
		}
		catch(CustomExecption ex1)
		{
			dpTextArea.appendText("\n[DeletePerson]deletePerson: " + ex1.getMessage());
		}
		catch(Exception ex)
		{
			dpTextArea.appendText("\n[DeletePerson]deletePerson: " + ex.getMessage());
		}
	}
	
	@FXML
	private void goBack()
	{
		mainObj.toAndFromNavigation("MainPage");
	}
}
