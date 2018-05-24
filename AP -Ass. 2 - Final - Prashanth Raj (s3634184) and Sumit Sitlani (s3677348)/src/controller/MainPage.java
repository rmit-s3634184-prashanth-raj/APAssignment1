package controller;

import java.io.IOException;

import iOPackage.TextFile;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import miniNetGUI.MiniNet;
import utility.CreatePeople;
import utility.Driver;

public class MainPage 
{
	TextFile textFileObj = null;
	Driver driverObj = null;
	private MiniNet mainObj = new MiniNet();
	@FXML
	private Button exit, addPersonIntoNetwork, createConnection, viewProfile, deleteProfile, viewFamMemDetails, findConnections;
	@FXML
	private TextArea mainPageTextarea;

	@SuppressWarnings("unchecked")
	@FXML
	private void initialize() 
	{
		try
		{
			//addpeopleToNetWork();
			//textFileObj.readRelationsTextFile();
			//ctPpl.createPeople(); //create set of people in order to add them into network.
			//mainPageTextarea.setDisable(true);
			mainPageTextarea.setText("");
			if( TextFile.peopleFileNotFound != null )
			{
				mainPageTextarea.appendText("\nException: " + TextFile.peopleFileNotFound);
			}
			if( TextFile.relationFileNotFound != null )
			{
				mainPageTextarea.appendText("\nException: " + TextFile.relationFileNotFound);
			}
			mainPageTextarea.appendText("\nCurrent status of the network:");
			mainPageTextarea.appendText("\n");
			mainPageTextarea.appendText("\nNo of Adults in the system: ");
			mainPageTextarea.appendText("" + CreatePeople.getListOfAdult().size());
			mainPageTextarea.appendText("\nNo of dependents in the system: ");
			mainPageTextarea.appendText("" + CreatePeople.getListOfDependent().size());
			CreatePeople.getListOfProfile();
			mainPageTextarea.appendText("\n");
			mainPageTextarea.appendText("\nTotal number of profiles: " + CreatePeople.getListOfProfile().size() );
			mainPageTextarea.appendText("\nProfile list by names: ");
			for(int i = 0; i < CreatePeople.getListOfProfile().size(); i++)
			{
				mainPageTextarea.appendText( "\n" + ( i + 1 ) + ") " + CreatePeople.getListOfProfile().get(i).getProfileName() );
			}
			mainPageTextarea.appendText("\n");
			mainPageTextarea.appendText("\nConnection list between profiles by profile names: ");
			mainPageTextarea.appendText("\n");
			for( int i = 0; i < CreatePeople.getListOfAdult().size(); i ++  )
			{
				if( CreatePeople.getListOfAdult().get(i).getHasProfile() )
				{
					mainPageTextarea.appendText("\nProfile name: " + CreatePeople.getListOfAdult().get(i).getProfile().getProfileName());
					mainPageTextarea.appendText("\nAge: " + CreatePeople.getListOfAdult().get(i).getProfile().getAge());
					mainPageTextarea.appendText("\nProfile image path: " + CreatePeople.getListOfAdult().get(i).getProfile().getProfileImagePath());
					mainPageTextarea.appendText("\nProfile status: " + CreatePeople.getListOfAdult().get(i).getProfile().getProfileStatus());
					if( CreatePeople.getListOfAdult().get(i).getProfile().getConnections().size() > 0 )
					{
						mainPageTextarea.appendText("\nNumber of connection: " + CreatePeople.getListOfAdult().get(i).getProfile().getConnections().size());
						for(int j = 0; j < CreatePeople.getListOfAdult().get(i).getProfile().getConnections().size() ; j ++)
						{
							if( CreatePeople.getListOfAdult().get(i).getProfile().getConnections().get(j).getConnectionType().equalsIgnoreCase("Friends") 
								|| CreatePeople.getListOfAdult().get(i).getProfile().getConnections().get(j).getConnectionType().equalsIgnoreCase("Partners")
								|| CreatePeople.getListOfAdult().get(i).getProfile().getConnections().get(j).getConnectionType().equalsIgnoreCase("colleagues")
								|| CreatePeople.getListOfAdult().get(i).getProfile().getConnections().get(j).getConnectionType().equalsIgnoreCase("Classmates")) 
							{
								if( CreatePeople.getListOfAdult().get(i).getProfile().getConnections().get(j).getAdult().getHasProfile() )
									mainPageTextarea.appendText("\n" + (j + 1) + ") Connection name: " + CreatePeople.getListOfAdult().get(i).getProfile().getConnections().get(j).getAdult().getProfile().getProfileName());
									mainPageTextarea.appendText("\n--Connection Type: " + CreatePeople.getListOfAdult().get(i).getProfile().getConnections().get(j).getConnectionType());
							}
							else if( CreatePeople.getListOfAdult().get(i).getProfile().getConnections().get(j).getConnectionType().equalsIgnoreCase("Family") )
							{
								if( CreatePeople.getListOfAdult().get(i).getProfile().getConnections().get(j).getDependent().getHasProfile() )
									mainPageTextarea.appendText("\n" + (j + 1) + ") Connection name: " + CreatePeople.getListOfAdult().get(i).getProfile().getConnections().get(j).getDependent().getProfile().getProfileName());
									mainPageTextarea.appendText("\n--Connection Type: " + CreatePeople.getListOfAdult().get(i).getProfile().getConnections().get(j).getConnectionType());
							}
						}
						mainPageTextarea.appendText("\n");
					}
					else
					{
						mainPageTextarea.appendText("\nNumber of connection: " + CreatePeople.getListOfAdult().get(i).getProfile().getConnections().size());
					}
				}
			}
			for( int i = 0; i < CreatePeople.getListOfDependent().size(); i ++  )
			{
				if( CreatePeople.getListOfDependent().get(i).getHasProfile() )
				{
					mainPageTextarea.appendText("\nProfile name: " + CreatePeople.getListOfDependent().get(i).getProfile().getProfileName());
					mainPageTextarea.appendText("\nAge: " + CreatePeople.getListOfDependent().get(i).getProfile().getAge());
					mainPageTextarea.appendText("\nProfile image path: " + CreatePeople.getListOfDependent().get(i).getProfile().getProfileImagePath());
					mainPageTextarea.appendText("\nProfile status: " + CreatePeople.getListOfDependent().get(i).getProfile().getProfileStatus());
					if( CreatePeople.getListOfDependent().get(i).getProfile().getConnections().size() > 0 )
					{
						mainPageTextarea.appendText("\nNumber of connection: " + CreatePeople.getListOfDependent().get(i).getProfile().getConnections().size());
						for(int j = 0; j < CreatePeople.getListOfDependent().get(i).getProfile().getConnections().size() ; j ++)
						{
							if( CreatePeople.getListOfDependent().get(i).getProfile().getConnections().get(j).getConnectionType().equalsIgnoreCase("Friends") ) 
							{
								if( CreatePeople.getListOfDependent().get(i).getProfile().getConnections().get(j).getDependent().getHasProfile() )
									mainPageTextarea.appendText("\n" + (j + 1) + ") Connection name: " + CreatePeople.getListOfDependent().get(i).getProfile().getConnections().get(j).getDependent().getProfile().getProfileName());
									mainPageTextarea.appendText("\n--Connection type: " + CreatePeople.getListOfDependent().get(i).getProfile().getConnections().get(j).getConnectionType());
							}
							else if( CreatePeople.getListOfDependent().get(i).getProfile().getConnections().get(j).getConnectionType().equalsIgnoreCase("Family") )
							{
								if( CreatePeople.getListOfDependent().get(i).getProfile().getConnections().get(j).getAdult().getHasProfile() )
									mainPageTextarea.appendText("\n" + (j + 1) + ") Connection name: " + CreatePeople.getListOfDependent().get(i).getProfile().getConnections().get(j).getAdult().getProfile().getProfileName());
									mainPageTextarea.appendText("\n--Connection type: " + CreatePeople.getListOfDependent().get(i).getProfile().getConnections().get(j).getConnectionType());
							}
						}
						mainPageTextarea.appendText("\n");
					}
					else
					{
						mainPageTextarea.appendText("\nNumber of connection: " + CreatePeople.getListOfDependent().get(i).getProfile().getConnections().size());
					}
				}
			}
		}
		catch(Exception ex)
		{
			mainPageTextarea.appendText("[initialize]Exception: " + ex.getMessage());
		}
	}
	
	@FXML
	private void navigateToJoinNetwork() throws IOException
	{
		mainObj.toAndFromNavigation("JoinNetwork");
	}
	
	@FXML
	private void navigateToCreateConnection() throws IOException
	{
		mainObj.toAndFromNavigation("CreateConnection");
	}
	
	@FXML
	private void navigateToViewProfile() throws IOException
	{
		mainObj.toAndFromNavigation("ViewProfile");
	}
	
	@FXML
	private void navigateToDeleteProfile() throws IOException
	{
		mainObj.toAndFromNavigation("DeletePerson");
	}

	@FXML
	private void navigateToViewFamMemDetails() throws IOException
	{
		mainObj.toAndFromNavigation("ViewFamMemDetails");
	}
	
	@FXML
	private void navigateToFindConnections() throws IOException
	{
		mainObj.toAndFromNavigation("FindConnections");
	}
	public void exitMiniNet(ActionEvent event)
	{
		mainPageTextarea.appendText("exitMiniNet");
		try
		{
			if( event.getSource().toString().contains("exit") )
			{
				System.exit(0);
			}
			else
			{
				throw new Exception();
			}
		}
		catch(Exception ex)
		{
			mainPageTextarea.appendText("[exitMiniNet]Exception: " + ex.getMessage());
		}
	}
}
