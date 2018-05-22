package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import minNetModelPackage.Profile;
import miniNetGUI.MiniNet;
import utility.CreatePeople;
import utility.Driver;

public class FindConnections 
{
	MiniNet mainObj = new MiniNet();

	@FXML
	private Button goBack;

	@FXML
	private ChoiceBox<String> fcProfileOne, fcProfileTwo;
	String profileNameOne = "", profileNameTwo = "", personOneId = "", personTwoId = "";

	@FXML
	private TextArea fcTextArea;

	@FXML
	ObservableList<String> profileOneList = FXCollections.observableArrayList();
	@FXML
	ObservableList<String> profileTwoList = FXCollections.observableArrayList();

	@SuppressWarnings("unchecked")
	@FXML
	private void initialize() 
	{
		try
		{
			fcProfileTwo.setDisable(true);
			for(int i = 0; i < CreatePeople.getListOfProfile().size(); i ++)
			{
				profileOneList.add(CreatePeople.getListOfProfile().get(i).getProfileName() );
			}
			fcProfileOne.setItems(profileOneList);

			fcProfileOne.getSelectionModel().selectedItemProperty().addListener( new ChangeListener<String>()
			{
				@Override
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) 
				{
					profileNameOne = newValue;
					fcTextArea.setText("");
					fcTextArea.appendText("\n1. Profile Name : " + profileNameOne +" is selected");
					fcTextArea.appendText("\n2. Profile Name : " + profileNameTwo +" is selected");

					profileTwoList = FXCollections.observableArrayList();
					fcProfileTwo.setDisable(false);
					for(int i = 0; i < CreatePeople.getListOfProfile().size(); i ++)
					{
						if( ! CreatePeople.getListOfProfile().get(i).getProfileName().equalsIgnoreCase(profileNameOne) )
							profileTwoList.add(CreatePeople.getListOfProfile().get(i).getProfileName() );
					}
					fcProfileTwo.setItems(profileTwoList);
				}
			});

			fcProfileTwo.getSelectionModel().selectedItemProperty().addListener( new ChangeListener<String>()
			{
				@Override
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) 
				{
					profileNameTwo = newValue;
					fcTextArea.setText("");
					fcTextArea.appendText("\n1. Profile Name : " + profileNameOne +" is selected");
					fcTextArea.appendText("\n2. Profile Name : " + profileNameTwo +" is selected");
				}
			});
		}
		catch(Exception ex)
		{
			fcTextArea.appendText("\n[FindConnections]initialize: " + ex.getMessage());
		}
	}

	@FXML
	private void findConnections()
	{
		Profile profile1 = null;
		Profile profile2 = null;
		String profile2PersonType = "", connectionType = "Not Connected";
		try
		{
			personOneId = CreatePeople.getPersonIdByProflieName(profileNameOne);
			personTwoId = CreatePeople.getPersonIdByProflieName(profileNameTwo);
			fcTextArea.setText("");
			fcTextArea.appendText("\n" + profileNameOne + "'s person id: " + personOneId);
			fcTextArea.appendText("\n" + profileNameTwo + "'s person id: " + personTwoId);
			profile1 = CreatePeople.getProfileObjectByProflieName(profileNameOne);
			profile2 = CreatePeople.getProfileObjectByProflieName(profileNameTwo);
			profile2PersonType = CreatePeople.getPersonTypeById(profile2.getPersonId());
			fcTextArea.appendText("\nprofile2PersonType: " + profile2PersonType );
			
			if( profile1.getConnections().size() > 0 )
			{
				for( int i = 0; i < profile1.getConnections().size(); i ++)
				{
					if( profile2PersonType.equalsIgnoreCase("adult") )
					{
						if( profile1.getConnections().get(i).getAdult() != null )
						{
							if( profile1.getConnections().get(i).getAdult().getProfile().getProfileName().equalsIgnoreCase(profileNameTwo) )
							{
								connectionType = profile1.getConnections().get(i).getConnectionType();
								break;
							}
						}
					}
					else if( profile2PersonType.equalsIgnoreCase("dependent") )
					{
						if( profile1.getConnections().get(i).getDependent() != null )
						{
							if( profile1.getConnections().get(i).getDependent().getProfile().getProfileName().equalsIgnoreCase(profileNameTwo) )
							{
								connectionType = profile1.getConnections().get(i).getConnectionType();
								break;
							}
						}
					}
				}
			}
			fcTextArea.setText("");
			fcTextArea.appendText("\n1. Profile Name : " + profileNameOne +" is selected");
			fcTextArea.appendText("\n2. Profile Name : " + profileNameTwo +" is selected");
			fcTextArea.appendText("\n");
			fcTextArea.appendText("\nOutcome: ");
			fcTextArea.appendText("\n" + connectionType);
		}
		catch(Exception ex)
		{
			fcTextArea.appendText("\n[FindConnections]findConnections: " + ex.getMessage());
		}
	}
	
	@FXML
	private void goBack()
	{
		mainObj.toAndFromNavigation("MainPage");
	}
}
