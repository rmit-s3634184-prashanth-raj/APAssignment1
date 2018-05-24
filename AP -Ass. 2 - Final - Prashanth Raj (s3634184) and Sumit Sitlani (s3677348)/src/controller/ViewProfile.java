package controller;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import minNetModelPackage.Adult;
import minNetModelPackage.Dependent;
import minNetModelPackage.Profile;
import miniNetGUI.MiniNet;
import utility.CreatePeople;

public class ViewProfile 
{
	MiniNet mainObj = new MiniNet();

	@FXML
	private Label profileNameFiller, genderFiller, ageFiller, stateFiller, statusFiller, imageFiller, connFiller;
	
	@FXML
	private Button goBack;
	
	@FXML
	private TextArea vpTextArea;	
	
	@FXML
	ObservableList<String> profileOneList = FXCollections.observableArrayList();
	
	@FXML
	private ChoiceBox<String> vpProfileOne;
	String profileNameOne = "", personOneId = "";
	
	@SuppressWarnings("unchecked")
	@FXML
	private void initialize() 
	{
		try
		{
			for(int i = 0; i < CreatePeople.getListOfProfile().size(); i ++)
			{
				profileOneList.add(CreatePeople.getListOfProfile().get(i).getProfileName() );
			}
			vpProfileOne.setItems(profileOneList);
			
			vpProfileOne.getSelectionModel().selectedItemProperty().addListener( new ChangeListener<String>()
			{
				@Override
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) 
				{
					profileNameOne = newValue;
					vpTextArea.setText("");
					vpTextArea.appendText("\n1. Profile Name : " + profileNameOne +" is selected");
				}
			});
		}
		catch(Exception ex)
		{
			vpTextArea.appendText("\n[ViewProfile]intialize: " + ex.getMessage());
		}
	}

	@FXML
	private void viewProfile()
	{
		Profile profile1 = null; 
		String profile1PersonType = "";
		Adult adultObj = null;
		Dependent depenObj = null;
		try
		{
			//profileNameFiller, genderFiller, ageFiller, stateFiller, statusFiller, imageFiller, connFiller;
			personOneId = CreatePeople.getPersonIdByProflieName(profileNameOne);
			profile1 = CreatePeople.getProfileObjectByProflieName(profileNameOne);
			profile1PersonType = CreatePeople.getPersonTypeById(profile1.getPersonId());

			if( profile1PersonType.equalsIgnoreCase("adult") )
			{
				adultObj = CreatePeople.getAdultObjectById(personOneId);
				//profileNameFiller, genderFiller, ageFiller, stateFiller, statusFiller, imageFiller, connFiller;
				profileNameFiller.setText(adultObj.getProfile().getProfileName());
				genderFiller.setText(adultObj.getGender());
				ageFiller.setText(Integer.toString(adultObj.getAge()));
				stateFiller.setText(adultObj.getStates());
				statusFiller.setText(adultObj.getProfile().getProfileStatus());
				imageFiller.setText(adultObj.getProfile().getProfileImagePath());
				connFiller.setText(Integer.toString(adultObj.getProfile().getConnections().size()));
			}
			else if(profile1PersonType.equalsIgnoreCase("dependent") )
			{
				depenObj = CreatePeople.getDependentObjectById(personOneId);
				//profileNameFiller, genderFiller, ageFiller, stateFiller, statusFiller, imageFiller, connFiller;
				profileNameFiller.setText(depenObj.getProfile().getProfileName());
				genderFiller.setText(depenObj.getGender());
				ageFiller.setText(Integer.toString(depenObj.getAge()));
				stateFiller.setText(depenObj.getStates());
				statusFiller.setText(depenObj.getProfile().getProfileStatus());
				imageFiller.setText(depenObj.getProfile().getProfileImagePath());
				connFiller.setText(Integer.toString(depenObj.getProfile().getConnections().size()));
			}
			
			vpTextArea.setText("");
			vpTextArea.appendText("\n1. Profile Name : " + profileNameOne +" is selected");
			vpTextArea.appendText("\n" + profileNameOne + "'s person id: " + personOneId);
			vpTextArea.appendText("\n Profile person type: " + profile1PersonType );			
		}
		catch(Exception ex)
		{
			vpTextArea.appendText("\n[ViewProfile]viewProfile: " + ex.getMessage());
		}
	}
	
	@FXML
	private void goBack()
	{
		mainObj.toAndFromNavigation("MainPage");
	}
}
