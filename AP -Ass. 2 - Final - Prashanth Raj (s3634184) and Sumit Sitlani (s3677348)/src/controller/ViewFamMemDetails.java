package controller;

import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import minNetModelPackage.Adult;
import minNetModelPackage.Connection;
import minNetModelPackage.Dependent;
import minNetModelPackage.Profile;
import miniNetGUI.MiniNet;
import utility.CreatePeople;

public class ViewFamMemDetails 
{
	MiniNet mainObj = new MiniNet();

	@FXML
	private Button goBack;
	
	@FXML
	private ChoiceBox<String> vfProfileOne;
	String profileNameOne = "", personOneId= "";
	
	@FXML
	ObservableList<String> profileOneList = FXCollections.observableArrayList();
	
	@FXML
	private TextArea vfTextArea;
	
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
			vfProfileOne.setItems(profileOneList);
			
			vfProfileOne.getSelectionModel().selectedItemProperty().addListener( new ChangeListener<String>()
			{
				@Override
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) 
				{
					profileNameOne = newValue;
					vfTextArea.setText("");
					vfTextArea.appendText("\n1. Profile Name : " + profileNameOne +" is selected");
				}
			});
		}
		catch(Exception ex)
		{
			vfTextArea.appendText("\n[ViewProfile]intialize: " + ex.getMessage());
		}
	}

	@FXML
	private void viewDetails()
	{
		Profile profile1 = null; 
		String profile1PersonType = "";
		int counter = 1;
		Adult adult1 = null;
		Dependent depen1 = null;
		List<Connection> connectionList1 = null;
		boolean connFound = false;
		try
		{
			personOneId = CreatePeople.getPersonIdByProflieName(profileNameOne);
			profile1 = CreatePeople.getProfileObjectByProflieName(profileNameOne);
			profile1PersonType = CreatePeople.getPersonTypeById(profile1.getPersonId());
			if( profile1PersonType.equalsIgnoreCase("adult") )
			{
				adult1 = CreatePeople.getAdultObjectById(personOneId);
				if( adult1.getHasChild() ) //has child - children details
				{
					vfTextArea.setText("");
					vfTextArea.appendText("\n1. Profile Name : " + profileNameOne +" is selected");
					vfTextArea.appendText("\nChild(ren) name(s)");
					connectionList1 = adult1.getProfile().getConnections();
					for( int i = 0; i < connectionList1.size(); i ++ )
					{
						if( connectionList1.get(i).getConnectionType().equalsIgnoreCase("Family") )
						{
							vfTextArea.appendText("\n" + counter + ") ID: " + connectionList1.get(i).getDependent().getID());
							vfTextArea.appendText("\nName: " + connectionList1.get(i).getDependent().getProfile().getProfileName());
							counter++;
						}
					}
					connFound = true;
				}
			}
			else if( profile1PersonType.equalsIgnoreCase("dependent") )
			{
				depen1 = CreatePeople.getDependentObjectById(personOneId);
				if( depen1.getHasParent() ) //has parents - parents details
				{
					vfTextArea.setText("");
					vfTextArea.appendText("\n1. Profile Name : " + profileNameOne +" is selected");
					vfTextArea.appendText("\nNames of parents");
					connectionList1 = depen1.getProfile().getConnections();
					for( int i = 0; i < connectionList1.size(); i ++)
					{
						if( connectionList1.get(i).getConnectionType().equalsIgnoreCase("Family") )
						{
							vfTextArea.appendText("\nId: " + connectionList1.get(i).getAdult().getID());
							vfTextArea.appendText("\nName: " + connectionList1.get(i).getAdult().getProfile().getProfileName());
						}
					}
					connFound = true;
				}
			}
			else
			{
				vfTextArea.setText("");
				vfTextArea.appendText("\n1. Profile Name : " + profileNameOne +" is selected");
				vfTextArea.appendText("Selected person has no family connection");
			}
			
			if( ! connFound )
			{
				vfTextArea.setText("");
				vfTextArea.appendText("\n1. Profile Name : " + profileNameOne +" is selected");
				vfTextArea.appendText("\nSelected person has no family connection");
			}
		}
		catch(Exception ex)
		{
			vfTextArea.appendText("\n[ViewFamMemDetails]viewDetails: " + ex.getMessage());
		}
	}
	
	@FXML
	private void goBack()
	{
		mainObj.toAndFromNavigation("MainPage");
	}
}
