package controller;

import gui.Main;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import minNetPackage.Profile;
import utility.CreatePeople;
import utility.CustomExecption;
import utility.Driver;

public class CreateConnection 
{
	Main mainObj = new Main();

	@FXML
	private TextArea ccTextArea;	
	
	@FXML
	private Button goBack, ccSubmit;
	
	@FXML
	private ChoiceBox<String> ccProfileOne, ccProfileTwo, ccConnType;
	String profileNameOne = "", profileNameTwo = "", connType = "", personOneId = "", personTwoId = "";
	
	@FXML
	ObservableList<String> profileOneList = FXCollections.observableArrayList();
	@FXML
	ObservableList<String> profileTwoList = FXCollections.observableArrayList();
	@FXML
	ObservableList<String> connectionTypeList = FXCollections.observableArrayList("Friends", "Couple", "Parent", "Siblings" , "Colleagues", "Classmates");
	/*
	 * 1. friends, 2. couple[partner], 3. family 4. siblings 5. colleagues, 6.classmates  
	 */

	@SuppressWarnings("unchecked")
	@FXML
	private void initialize() 
	{
		try
		{
			ccConnType.setItems(connectionTypeList);
			ccProfileTwo.setDisable(true);
			for(int i = 0; i < CreatePeople.getListOfProfile().size(); i ++)
			{
				profileOneList.add(CreatePeople.getListOfProfile().get(i).getProfileName() );
			}
			ccProfileOne.setItems(profileOneList);
			
			ccProfileOne.getSelectionModel().selectedItemProperty().addListener( new ChangeListener<String>()
			{
				@Override
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) 
				{
					profileNameOne = newValue;
					ccTextArea.setText("");
					ccTextArea.appendText("\n1. Profile Name : " + profileNameOne +" is selected");
					ccTextArea.appendText("\n2. Profile Name : " + profileNameTwo +" is selected");
					ccTextArea.appendText("\nConnection Type : " + connType +" is selected");
					
					profileTwoList = FXCollections.observableArrayList();
					ccProfileTwo.setDisable(false);
					for(int i = 0; i < CreatePeople.getListOfProfile().size(); i ++)
					{
						if( ! CreatePeople.getListOfProfile().get(i).getProfileName().equalsIgnoreCase(profileNameOne) )
							profileTwoList.add(CreatePeople.getListOfProfile().get(i).getProfileName() );
					}
					ccProfileTwo.setItems(profileTwoList);
				}
			});
			
			ccProfileTwo.getSelectionModel().selectedItemProperty().addListener( new ChangeListener<String>()
			{
				@Override
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) 
				{
					profileNameTwo = newValue;
					ccTextArea.setText("");
					ccTextArea.appendText("\n1. Profile Name : " + profileNameOne +" is selected");
					ccTextArea.appendText("\n2. Profile Name : " + profileNameTwo +" is selected");
					ccTextArea.appendText("\nConnection Type : " + connType +" is selected");
				}
			});
			
			ccConnType.getSelectionModel().selectedItemProperty().addListener( new ChangeListener<String>()
			{
				@Override
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) 
				{
					connType = newValue;
					ccTextArea.setText("");
					ccTextArea.appendText("\n1. Profile Name : " + profileNameOne +" is selected");
					ccTextArea.appendText("\n2. Profile Name : " + profileNameTwo +" is selected");
					ccTextArea.appendText("\nConnection Type : " + connType +" is selected");
				}
			});
		}
		catch(Exception ex)
		{
			ccTextArea.appendText("[CreateConnection]intialize: " + ex.getMessage());
		}
	}
	@FXML
	private void goBack()
	{
		mainObj.toAndFromNavigation("MainPage");
	}
	
	@FXML
	private void createConnections()
	{
		Profile profile1 = null;
		Profile profile2 = null;
		String profile2PersonType = "";
		try
		{
			personOneId = CreatePeople.getPersonIdByProflieName(profileNameOne);
			personTwoId = CreatePeople.getPersonIdByProflieName(profileNameTwo);
			ccTextArea.appendText("\n" + profileNameOne + "'s person id: " + personOneId);
			ccTextArea.appendText("\n" + profileNameTwo + "'s person id: " + personTwoId);
			Driver.createConnections(personOneId, personTwoId, connType);
			profile1 = CreatePeople.getProfileObjectByProflieName(profileNameOne);
			profile2 = CreatePeople.getProfileObjectByProflieName(profileNameTwo);
			
			if( profile1.getConnections().size() > 0 )
			{
				int i = profile1.getConnections().size() - 1;
				ccTextArea.appendText("\n");
				ccTextArea.appendText("\nConnection was successfully created between profiles: " + profileNameOne + " and " + profileNameTwo);
				ccTextArea.appendText("\nConnection details for Profile: " + profileNameOne);
				ccTextArea.appendText("\nConnection type: " + profile1.getConnections().get(i).getConnectionType());
				
				profile2PersonType = CreatePeople.getPersonTypeById(profile2.getPersonId());
				
				if( profile2PersonType.equalsIgnoreCase("adult") )
				{
					ccTextArea.appendText("\nConnection profile name: " + profile1.getConnections().get(i).getAdult().getProfile().getProfileName());
					ccTextArea.appendText("\nConnection person type: " + profile1.getConnections().get(i).getAdult().getType());
					ccTextArea.appendText("\nConnection age: " + profile1.getConnections().get(i).getAdult().getProfile().getAge());
					ccTextArea.appendText("\nConnection person id: " + profile1.getConnections().get(i).getAdult().getID());
				}
				else if( profile2PersonType.equalsIgnoreCase("dependent") )
				{
					ccTextArea.appendText("\nConnection profile name: " + profile1.getConnections().get(i).getDependent().getProfile().getProfileName());
					ccTextArea.appendText("\nConnection person type: " + profile1.getConnections().get(i).getDependent().getType());
					ccTextArea.appendText("\nConnection age: " + profile1.getConnections().get(i).getDependent().getProfile().getAge());
					ccTextArea.appendText("\nConnection person id: " + profile1.getConnections().get(i).getDependent().getID());
				}
				ccTextArea.appendText("\nTotal number of connection(s): " + profile1.getConnections().size());
				ccTextArea.appendText("\n");
			}
		}
		catch(CustomExecption ex1)
		{
			ccTextArea.appendText("\nCustomException: " + ex1.getMessage());
		}
		catch(Exception ex)
		{
			ccTextArea.appendText("\n[CreateConnections]createConnections: " + ex.getMessage());
		}
		
		//get either adult or dependent person id from each of the profile names of 1 and 2
	}
}
