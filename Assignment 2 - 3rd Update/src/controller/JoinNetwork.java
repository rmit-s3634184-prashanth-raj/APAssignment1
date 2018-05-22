package controller;

import java.text.DecimalFormat;
import java.text.ParsePosition;

import gui.Main;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import utility.CreatePeople;
import utility.Driver;

public class JoinNetwork 
{
	Main mainObj = new Main();

	@FXML
	private Button goBack, jnSubmit;

	@FXML
	private TextField jnName, jnImagePath, jnStatus, jnAge;

	@FXML
	private TextArea joinNWTextArea;

	@FXML
	private ChoiceBox<String> jnGender, jnState, jnParentName;
	String gender = "", state = "", profileName = "", image = "", status = "", age = "", personId = "" , parentName = "", parentId = "";
	int intGAge = 0;
	@FXML
	ObservableList<String> genderList = FXCollections.observableArrayList("Male", "Female");

	@FXML
	ObservableList<String> stateList = FXCollections.observableArrayList("ACT", "NSW", "NT", "QLD", "SA", "TAS", "VIC", "WA");

	@FXML
	ObservableList<String> parentList = FXCollections.observableArrayList();


	@SuppressWarnings("unchecked")
	@FXML
	private void initialize() 
	{
		try
		{
			jnGender.setItems(genderList);
			jnState.setItems(stateList);
			jnParentName.setDisable(true);

			jnName.textProperty().addListener(new ChangeListener<String>() 
			{
				@Override
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) 
				{
					profileName = newValue;
					joinNWTextArea.setText("");
					joinNWTextArea.appendText("\nProfile Name: " + profileName +" is entered");
					joinNWTextArea.appendText("\nGender: " + gender +" is selected");
					joinNWTextArea.appendText("\nAge: " + age + " is entered");
					joinNWTextArea.appendText("\nState: " + state +" is selected");
					joinNWTextArea.appendText("\nStatus: " + status +" is entered");
					joinNWTextArea.appendText("\nImage Path: " + image +" is entered");
				}
			});

			jnGender.getSelectionModel().selectedItemProperty().addListener( new ChangeListener<String>()
			{
				@Override
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) 
				{
					gender = newValue;
					joinNWTextArea.setText("");
					joinNWTextArea.appendText("\nProfile Name: " + profileName +" is entered");
					joinNWTextArea.appendText("\nGender: " + gender +" is selected");
					joinNWTextArea.appendText("\nAge: " + age + " is entered");
					joinNWTextArea.appendText("\nState: " + state +" is selected");
					joinNWTextArea.appendText("\nStatus: " + status +" is entered");
					joinNWTextArea.appendText("\nImage Path: " + image +" is entered");
				}
			});

			DecimalFormat format = new DecimalFormat( "#.0" );

			jnAge.setTextFormatter( new TextFormatter<>(c ->
			{
				if ( c.getControlNewText().isEmpty() )
				{
					return c;
				}

				ParsePosition parsePosition = new ParsePosition( 0 );
				Object object = format.parse( c.getControlNewText(), parsePosition );

				if ( object == null || parsePosition.getIndex() < c.getControlNewText().length() 
						|| c.getControlNewText().length() > 2 
						||  ( c.getControlNewText().equalsIgnoreCase("00") ) 
						||  ( c.getControlNewText().equalsIgnoreCase("0") ))
				{
					return null;
				}
				else
				{
					age = c.getControlNewText();
					joinNWTextArea.setText("");
					joinNWTextArea.appendText("\nProfile Name: " + profileName +" is entered");
					joinNWTextArea.appendText("\nGender: " + gender +" is selected");
					joinNWTextArea.appendText("\nAge: " + age + " is entered");
					joinNWTextArea.appendText("\nState: " + state +" is selected");
					joinNWTextArea.appendText("\nStatus: " + status +" is entered");
					joinNWTextArea.appendText("\nImage Path: " + image +" is entered");
					return c ;
				}
			}));
			
			jnState.getSelectionModel().selectedItemProperty().addListener( new ChangeListener<String>()
			{
				@Override
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) 
				{
					state = newValue;
					joinNWTextArea.setText("");
					joinNWTextArea.appendText("\nProfile Name: " + profileName +" is entered");
					joinNWTextArea.appendText("\nGender: " + gender +" is selected");
					joinNWTextArea.appendText("\nAge: " + age + " is entered");
					joinNWTextArea.appendText("\nState: " + state +" is selected");
					joinNWTextArea.appendText("\nStatus: " + status +" is entered");
					joinNWTextArea.appendText("\nImage Path: " + image +" is entered");
				}
			});

			jnStatus.textProperty().addListener(new ChangeListener<String>() 
			{
				@Override
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) 
				{
					status = newValue;
					joinNWTextArea.setText("");
					joinNWTextArea.appendText("\nProfile Name: " + profileName +" is entered");
					joinNWTextArea.appendText("\nGender: " + gender +" is selected");
					joinNWTextArea.appendText("\nAge: " + age + " is entered");
					joinNWTextArea.appendText("\nState: " + state +" is selected");
					joinNWTextArea.appendText("\nStatus: " + status +" is entered");
					joinNWTextArea.appendText("\nImage Path: " + image +" is entered");
				}
			});

			jnImagePath.textProperty().addListener(new ChangeListener<String>() 
			{
				@Override
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) 
				{
					image = newValue;
					joinNWTextArea.setText("");
					joinNWTextArea.appendText("\nProfile Name: " + profileName +" is entered");
					joinNWTextArea.appendText("\nGender: " + gender +" is selected");
					joinNWTextArea.appendText("\nAge: " + age + " is entered");
					joinNWTextArea.appendText("\nState: " + state +" is selected");
					joinNWTextArea.appendText("\nStatus: " + status +" is entered");
					joinNWTextArea.appendText("\nImage Path: " + image +" is entered");
				}
			});
		}
		catch(Exception ex)
		{
			System.out.println("[JoinNetwork]initialize: " + ex.getMessage());
		}
	}

	@FXML
	private void goBack()
	{
		mainObj.toAndFromNavigation("MainPage");
	}

	@FXML
	private void joinNetwork()
	{
		try
		{
			System.out.println("Before: " + CreatePeople.getListOfAdult().size());
			if( age != "" )
			{
				intGAge = Integer.parseInt(age);
				//adult person and profile record
				if( intGAge >= 16 && intGAge <= 99 )
				{
					generatePersonId(intGAge);
					CreatePeople.createPeople(personId.trim(), profileName.trim(), image.trim(), status.trim(), gender.trim(), intGAge, state.trim());
					joinNWTextArea.appendText("\n");
					joinNWTextArea.appendText("\n Person record is successfully created");
					System.out.println("After: " + CreatePeople.getListOfAdult().size());
					Driver.addAdultIntoNetwork(1,personId);
					joinNWTextArea.appendText("\n Profile is added into the network");
					joinNWTextArea.appendText("\n\n");
					joinNWTextArea.appendText("Profile details from network: ");
					for( int i = CreatePeople.getListOfAdult().size() - 1; i < CreatePeople.getListOfAdult().size(); i ++  )
					{
						if( CreatePeople.getListOfAdult().get(i).getHasProfile() )
						{
							joinNWTextArea.appendText("\nProfile name: " + CreatePeople.getListOfAdult().get(i).getProfile().getProfileName());
							joinNWTextArea.appendText("\nAge: " + CreatePeople.getListOfAdult().get(i).getProfile().getAge());
							joinNWTextArea.appendText("\nProfile image path: " + CreatePeople.getListOfAdult().get(i).getProfile().getProfileImagePath());
							joinNWTextArea.appendText("\nProfile status: " + CreatePeople.getListOfAdult().get(i).getProfile().getProfileStatus());
							if( CreatePeople.getListOfAdult().get(i).getProfile().getConnections().size() > 0 )
							{
								joinNWTextArea.appendText("\nNumber of connection: " + CreatePeople.getListOfAdult().get(i).getProfile().getConnections().size());
								for(int j = 0; j < CreatePeople.getListOfAdult().get(i).getProfile().getConnections().size() ; j ++)
								{
									if( CreatePeople.getListOfAdult().get(i).getProfile().getConnections().get(j).getConnectionType().equalsIgnoreCase("Friends") 
										|| CreatePeople.getListOfAdult().get(i).getProfile().getConnections().get(j).getConnectionType().equalsIgnoreCase("Partners")
										|| CreatePeople.getListOfAdult().get(i).getProfile().getConnections().get(j).getConnectionType().equalsIgnoreCase("colleagues")) 
									{
										if( CreatePeople.getListOfAdult().get(i).getProfile().getConnections().get(j).getAdult().getHasProfile() )
											joinNWTextArea.appendText("\n" + (j + 1) + ") Connection name: " + CreatePeople.getListOfAdult().get(i).getProfile().getConnections().get(j).getAdult().getProfile().getProfileName());
											joinNWTextArea.appendText("\n--Connection Type: " + CreatePeople.getListOfAdult().get(i).getProfile().getConnections().get(j).getConnectionType());
									}
									else if( CreatePeople.getListOfAdult().get(i).getProfile().getConnections().get(j).getConnectionType().equalsIgnoreCase("Family") )
									{
										if( CreatePeople.getListOfAdult().get(i).getProfile().getConnections().get(j).getDependent().getHasProfile() )
											joinNWTextArea.appendText("\n" + (j + 1) + ") Connection name: " + CreatePeople.getListOfAdult().get(i).getProfile().getConnections().get(j).getDependent().getProfile().getProfileName());
											joinNWTextArea.appendText("\n--Connection Type: " + CreatePeople.getListOfAdult().get(i).getProfile().getConnections().get(j).getConnectionType());
									}
								}
								joinNWTextArea.appendText("\n");
							}
							else
							{
								joinNWTextArea.appendText("\nNumber of connection: " + CreatePeople.getListOfAdult().get(i).getProfile().getConnections().size());
							}
						}
					}
				}
				else
				{
					System.out.println("Driver.partnerList.size()>> " + Driver.partnerList.size());
					if( Driver.partnerList.size() > 0 )
					{
						for(int i = 0; i < Driver.partnerList.size(); i ++ )
						{
							parentList.add(Driver.partnerList.get(i).getName());
						}
					}
					jnParentName.setDisable(false);
					jnParentName.setItems(parentList);
					
					jnParentName.getSelectionModel().selectedItemProperty().addListener( new ChangeListener<String>()
					{
						@Override
						public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) 
						{
							parentName = newValue;
							joinNWTextArea.setText("");
							joinNWTextArea.appendText("\nProfile Name: " + profileName +" is entered");
							joinNWTextArea.appendText("\nGender: " + gender +" is selected");
							joinNWTextArea.appendText("\nAge: " + age + " is entered");
							joinNWTextArea.appendText("\nState: " + state +" is selected");
							joinNWTextArea.appendText("\nStatus: " + status +" is entered");
							joinNWTextArea.appendText("\nImage Path: " + image +" is entered");
							joinNWTextArea.appendText("\nParent Name: " + parentName +" is selected");
						}
					});
					
					if( parentName != "" )
					{
						generatePersonId(intGAge);
						CreatePeople.createPeople(personId.trim(), profileName.trim(), image.trim(), status.trim(), gender.trim(), intGAge, state.trim());
						joinNWTextArea.appendText("\n");
						joinNWTextArea.appendText("\n Person record is successfully created");
						System.out.println("After: " + CreatePeople.getListOfAdult().size());
						parentId = Driver.getParentIdByName(parentName);
						Driver.addDependentIntoNetwork(2,personId,parentId);
						joinNWTextArea.appendText("\n Profile is added into the network");
						joinNWTextArea.appendText("\n\n");
						joinNWTextArea.appendText("Profile details from network: ");
						for( int i = CreatePeople.getListOfDependent().size() - 1; i < CreatePeople.getListOfDependent().size(); i ++  )
						{
							if( CreatePeople.getListOfDependent().get(i).getHasProfile() )
							{
								joinNWTextArea.appendText("\nProfile name: " + CreatePeople.getListOfDependent().get(i).getProfile().getProfileName());
								joinNWTextArea.appendText("\nAge: " + CreatePeople.getListOfDependent().get(i).getProfile().getAge());
								joinNWTextArea.appendText("\nProfile image path: " + CreatePeople.getListOfDependent().get(i).getProfile().getProfileImagePath());
								joinNWTextArea.appendText("\nProfile status: " + CreatePeople.getListOfDependent().get(i).getProfile().getProfileStatus());
								if( CreatePeople.getListOfDependent().get(i).getProfile().getConnections().size() > 0 )
								{
									joinNWTextArea.appendText("\nNumber of connection: " + CreatePeople.getListOfDependent().get(i).getProfile().getConnections().size());
									for(int j = 0; j < CreatePeople.getListOfDependent().get(i).getProfile().getConnections().size() ; j ++)
									{
										if( CreatePeople.getListOfDependent().get(i).getProfile().getConnections().get(j).getConnectionType().equalsIgnoreCase("Friends") ) 
										{
											if( CreatePeople.getListOfDependent().get(i).getProfile().getConnections().get(j).getDependent().getHasProfile() )
												joinNWTextArea.appendText("\n" + (j + 1) + ") Connection name: " + CreatePeople.getListOfDependent().get(i).getProfile().getConnections().get(j).getDependent().getProfile().getProfileName());
												joinNWTextArea.appendText("\n--Connection type: " + CreatePeople.getListOfDependent().get(i).getProfile().getConnections().get(j).getConnectionType());
										}
										else if( CreatePeople.getListOfDependent().get(i).getProfile().getConnections().get(j).getConnectionType().equalsIgnoreCase("Family") )
										{
											if( CreatePeople.getListOfDependent().get(i).getProfile().getConnections().get(j).getAdult().getHasProfile() )
												joinNWTextArea.appendText("\n" + (j + 1) + ") Connection name: " + CreatePeople.getListOfDependent().get(i).getProfile().getConnections().get(j).getAdult().getProfile().getProfileName());
												joinNWTextArea.appendText("\n--Connection type: " + CreatePeople.getListOfDependent().get(i).getProfile().getConnections().get(j).getConnectionType());
										}
									}
									joinNWTextArea.appendText("\n");
								}
								else
								{
									joinNWTextArea.appendText("\nNumber of connection: " + CreatePeople.getListOfDependent().get(i).getProfile().getConnections().size());
								}
							}
						}
					}
				}
			}

		}
		catch(Exception ex)
		{
			System.out.println("[joinNetworkFn]Exception: " + ex.getMessage());
		}
	}
	
	private void generatePersonId( int age )
	{
		String adultId = "";
		String dependentId = "";
		int intAdultIDNum = 0;
		int intDependentIDNum = 0;
		String[] localSplit = null;
		try
		{
			adultId = CreatePeople.getListOfAdult().get(CreatePeople.getListOfAdult().size()-1).getID();
			dependentId = CreatePeople.getListOfDependent().get(CreatePeople.getListOfDependent().size()-1).getID();
			
			localSplit = null;
			localSplit = adultId.split("id");
			intAdultIDNum = Integer.parseInt(localSplit[1]);
			
			localSplit = null;
			localSplit = dependentId.split("id");
			intDependentIDNum = Integer.parseInt(localSplit[1]);
			
			if( intDependentIDNum > intAdultIDNum )
			{
				System.out.println("[ID]Before: id" + intDependentIDNum);
				personId = "id" + ( intDependentIDNum + 1 );  
			}
			else
			{
				System.out.println("[ID]Before: id" + intAdultIDNum);
				System.out.println("[ID]After: " + personId);
				personId = "id" + ( intAdultIDNum + 1 );
			}
			
			System.out.println("[ID]After: " + personId);
		}
		catch(Exception ex)
		{
			System.out.println("[generatePersonId]Exception: " + ex.getMessage());
		}
	}
}
