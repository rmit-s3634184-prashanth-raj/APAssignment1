package utility;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


import minNetPackage.Adult;
import minNetPackage.Connection;
import minNetPackage.Dependent;
import minNetPackage.Profile;

public class Menu 
{
	private Scanner scannerObj = null;
	String searchQuery = "";
	int age = 0;
	int totalNumberOfPeople = 0;
	List<Adult> adultList = new ArrayList<Adult>();
	List<Adult> partnerList = new ArrayList<Adult>();
	List<Dependent> dependentList = new ArrayList<Dependent>();
	List<Profile> profileList = new ArrayList<Profile>();
	List<String> profileListWithPersonIds = new ArrayList<String>();
	List<Connection> totalconnectionList = new ArrayList<Connection>();
	CreatePeople ctPpl = null;

	public void displayMenu(CreatePeople ppl1)
	{
		ctPpl = ppl1;
		scannerObj = new Scanner(System.in);
		int choice = 0;
		System.out.println();
		System.out.println("Menu>>");
		System.out.println("1. Add a person into the network");
		System.out.println("2. Select a person by name");
		System.out.println("3. Display the profile of the selected person");
		System.out.println("4. Update the profile information of the selected person");
		System.out.println("5. Delete the selected person");
		System.out.println("6. Create connections");
		System.out.println("7. Find out whether a person is a direct friend of another person");
		System.out.println("8. Find out the name(s) of a person’s child(ren) or the names of the parents");
		System.out.println("9. Exit!");
		System.out.println("Please select an option from the displayed menu");
		System.out.println(">>");
		choice = getMenuChoiceInt("main");
		
		selectAnOption(choice);
	}

	private void selectAnOption(int choice)
	{
		try
		{
			switch(choice)
			{
				case 1: System.out.println("Menu option : " + choice + ". Add a person into the network - selected!");
						addPersonIntoNetwork();
						displayMenu(this.ctPpl);
						break;
	
				case 2: System.out.println("Menu option : " + choice + ". Select a person by name - selected!");
						selectAPersonByName();
						displayMenu(this.ctPpl);
						break;
	
				case 3: System.out.println("Menu option : " + choice + ". Display the profile of the selected person - selected!");
						displayProfileDetails();
						displayMenu(this.ctPpl);
						break;
	
				case 4: System.out.println("Menu option : " + choice + ". Update the profile information of the selected person - selected!");
						updateProfileInformation();
						displayMenu(this.ctPpl);
						break;
	
				case 5: System.out.println("Menu option : " + choice + ". Delete the selected person - selected!");
						deletePerson();
						displayMenu(this.ctPpl);
						break; 
	
				case 6: System.out.println("Menu option : " + choice + ". Create connections - selected!");
						createConnections();
						displayMenu(this.ctPpl);
						break;
	
				case 7: System.out.println("Menu option : " + choice + ". Find out whether a person is a direct friend of another person - selected!");
						connectionLookUpFriends();
						displayMenu(this.ctPpl);
						break;
	
				case 8: System.out.println("Menu option : " + choice + ". Find out the name(s) of a person’s child(ren) or the names of the parents - selected!");
						connectionLookUpFamily();
						displayMenu(this.ctPpl);
						break;
	
				case 9: System.out.println("Menu option : " + choice + ". Exit - selected!");
						System.exit(0);
			}
		}
		catch(Exception e)
		{
			System.out.println("[Menu]selectAnOption: " + e.getMessage());
		}
	}

	private void addPersonIntoNetwork()
	{
		int addChoice = 0;
		try
		{
			scannerObj = new Scanner(System.in);
			if( partnerList.size() <= 0  ) 
			{
				System.out.println("Please select 1 to add an adult or 2 to add a dependent to the network");
				System.out.println("*Please note Option - 2. Add dependent is not available until: There are at least 2 adults with connection type: Partners");
				System.out.println("1. Add adult");
				System.out.println("0. Go back to previous menu");
				addChoice = getMenuChoiceInt("sub2");
				if( addChoice == 1 )
				{
					addAdultIntoNetwork(addChoice);
				}
				else if( addChoice == 0 )
				{
					System.out.println("Go back to prevoius menu - Selected");
					scannerObj = null;
					displayMenu(this.ctPpl);
				}
			}
			else
			{
				System.out.println("Please select 1 to add an adult or 2 to add a dependent to the network");
				System.out.println("1. Add adult");
				System.out.println("2. Add dependent");
				System.out.println("0. Go back to previous menu");
				addChoice = scannerObj.nextInt();
				scannerObj.nextLine();
				if( addChoice == 1 )
				{
					addAdultIntoNetwork(addChoice);
				}
				else if( addChoice == 2 )
				{
					addDependentIntoNetwork(addChoice);
				}
				else if( addChoice == 0 )
				{
					System.out.println("Go back to prevoius menu - Selected");
					scannerObj = null;
					displayMenu(this.ctPpl);
				}
				else
				{
					System.out.println("Invalid option");
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("[Menu]addPersonIntoNetwork");
		}
	}

	private void addAdultIntoNetwork(int addChoice)
	{
		try
		{
			System.out.println("Sub menu option: " + addChoice + ". Add adult - Selected");
			scannerObj = new Scanner(System.in);

			System.out.println("List of people available to be added to the network:");
			createPeopleAndDisplayDetails();

			searchQuery = selectAPersonById();

			if( ( addProfile(searchQuery, addChoice) ) != null )
			{
				displayListofProfiles();
			}
		}
		catch(Exception e)
		{
			System.out.println("[Menu]addAdultIntoNetwork: " + e.getMessage());
		}
	}

	private void addDependentIntoNetwork(int addChoice)
	{
		try
		{
			System.out.println("Sub menu option: " + addChoice + ". Add Dependent - Selected");
			scannerObj = new Scanner(System.in);

			System.out.println("List of people available to be added to the network:");
			createPeopleAndDisplayDetails();

			searchQuery = selectAPersonById();

			if( ( addProfile(searchQuery, addChoice) ) != null )
			{
				displayListofProfiles();
			}
		}
		catch(Exception e)
		{
			System.out.println("[Menu]addDependentIntoNetwork: " + e.getMessage());
		}
	}

	private Profile addProfile(String personId, int addChoice)
	{
		String profileName = "";
		int profileAge = 0;
		String imagePath = "";
		String status = "";
		Profile profile1 = null;
		Adult adult1 = null;
		Dependent dependent1 = null;
		try
		{
			scannerObj = new Scanner(System.in);
			if( addChoice == 1 )
			{
				profile1 = createAdultProfile(personId, profileName, profileAge, imagePath, status, adult1, profile1);
			}
			else if( addChoice == 2 )
			{
				profile1 = createDependentProfile(personId, profileName, profileAge, imagePath, status, dependent1, profile1);
			}
			else
			{
				System.out.println("Person not found");
			}
		}
		catch(Exception e)
		{
			System.out.println("[Menu]addProfile: " + e.getMessage());
		}
		return profile1;
	}

	private Profile createAdultProfile(String personId, String profileName, int profileAge, String imagePath, String status, Adult adult1, Profile profile1)
	{
		try
		{
			adult1 = ctPpl.getAdultObjectById(personId);
			if( adult1 != null )
			{
				System.out.println("Person with id " + personId + " is selected");
				System.out.println("Please enter Profile Name >>");
				profileName = scannerObj.nextLine();
				System.out.println("Please enter your age [>=16 and <=99] >>");
				profileAge = getMenuChoiceInt("ageAdult");
				System.out.println("Please enter Profile image path >>");
				imagePath = scannerObj.nextLine();
				System.out.println("Please enter Profile status >>");
				status = scannerObj.nextLine();
				System.out.println("The following details was entered");
				System.out.println("1. Profile name: " + profileName);
				System.out.println("2. Age: " + profileAge);
				System.out.println("3. Profile image path: " + imagePath);
				System.out.println("4. Status: " + status);

				profile1 = new Profile(profileName, profileAge, imagePath, status);
				ctPpl.addProfileToList(profile1);
				adult1.setProfile(profile1);
				adult1.setType("adult");
			}
		}
		catch(Exception e)
		{
			System.out.println("[Menu]createAdultProfile: " + e.getMessage());
		}
		return profile1;
	}

	private Profile createDependentProfile(String personId, String profileName, int profileAge, String imagePath, String status, Dependent dependent1, Profile profile1)
	{
		try
		{
			dependent1 = ctPpl.getDependentObjectById(personId);
			if( dependent1 != null )
			{
				System.out.println("Person with id " + personId + " is selected");
				System.out.println("Please enter Profile Name >>");
				profileName = scannerObj.nextLine();
				System.out.println("Please enter your age [>1 and <16]>>");
				profileAge = getMenuChoiceInt("ageDepen");
				System.out.println("Please enter Profile image path >>");
				imagePath = scannerObj.nextLine();
				System.out.println("Please enter Profile status >>");
				status = scannerObj.nextLine();
				System.out.println("The following details was entered");
				System.out.println("1. Profile name: " + profileName);
				System.out.println("2. Age: " + profileAge);
				System.out.println("3. Profile image path: " + imagePath);
				System.out.println("4. Status: " + status);

				profile1 = new Profile(profileName, profileAge, imagePath, status);
				ctPpl.addProfileToList(profile1);
				dependent1.setProfile(profile1);
				dependent1.setType("dependent");
			}
		}
		catch(Exception e)
		{
			System.out.println("[Menu]createDependentProfile: " + e.getMessage());
		}
		return profile1;
	}

	private void displayListofProfiles()
	{
		try
		{
			profileList = ctPpl.getListOfProfile();
			System.out.println();
			System.out.println("List of profiles in MiniNet");
			System.out.println("Total number of profiles: " + profileList .size() );
			System.out.println("Profile list by names: ");
			for(int i = 0; i < profileList.size(); i++)
			{
				System.out.println( ( i + 1 ) + ") " + profileList.get(i).getProfileName() );
			}
		}
		catch(Exception e)
		{
			System.out.println("[Menu]displayListofProfiles: " + e.getMessage());
		}
	}

	private void createPeopleAndDisplayDetails()
	{
		try
		{
			adultList = ctPpl.getListOfAdult();
			dependentList = ctPpl.getListOfDependent();
			totalNumberOfPeople = ctPpl.getTotalNumberOfPeople();
			ctPpl.displayAdultDetails();
			if( partnerList.size() > 0 )
			{
				ctPpl.displayDependentDetails();
			}
		}
		catch(Exception e)
		{
			System.out.println("[Menu]createPeopleAndDisplayDetails");
		}
	}

	private String selectAPersonById()
	{
		String iD = "";
		try
		{
			System.out.println("Please select a person by id or name to add into the network from the displayed list");
			scannerObj = new Scanner(System.in);
			iD = scannerObj.nextLine();
		}
		catch(Exception e)
		{
			System.out.println("[Menu]selectAPersonById: " + e.getMessage());
		}
		return iD;
	}

	private void selectAPersonByName()
	{
		try
		{
			System.out.println("Search for a person by name: ");
			searchQuery = scannerObj.nextLine();
			System.out.println("Searching '.'.'.' person by name: " + searchQuery);
			ctPpl.getPersonDetailsByName(searchQuery);
		}
		catch(Exception e)
		{
			System.out.println("[Menu]selectAPersonByName: " + e.getMessage());
		}
	}
private void displayProfileDetails()
	{
		String name = "";
		Profile profile1 = null;
		try
		{
			if( displayPeopleHaveProfileDetails() )
			{
				scannerObj = new Scanner(System.in);
				System.out.println("Please enter Profile name to get profile details: ");
				name = scannerObj.nextLine();
				if( ctPpl.validSearchByProfileName(name) )
				{
					profile1 = ctPpl.getProfileObjectByProflieName(name);
					System.out.println("Profile details by name: " + name  );
					System.out.println("Profile name: " + profile1.getProfileName());
					System.out.println("Profile image path: " + profile1.getProfileImagePath());
					System.out.println("Profile status: " + profile1.getProfileStatus());
					System.out.println("Profile number of friends: " + profile1.getNumberofFriends());
				}
				else
				{
					System.out.println("Profile not found");
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("[Menu] displayProfileDetails: " + e.getMessage());
		}
	}

	public void updateProfileInformation()
	{
		String name = "";
		Profile profile1 = null;
		int updateInfoChoice = 0;
		try
		{
			if( displayPeopleHaveProfileDetails() )
			{
				scannerObj = new Scanner(System.in);
				System.out.println("Please enter Profile name to get profile details: ");
				name = scannerObj.nextLine();
				if( ctPpl.validSearchByProfileName(name) )
				{
					System.out.println("Proflie by name " + name + " was found");
					System.out.println("1. Profile Name");
					System.out.println("2. Profile Image Path");
					System.out.println("3. Profile Status");
					System.out.println("0. Go back to previous menu");
					System.out.println("Please select an information [ 1 / 2 / 3 / 0 (Go back to previous menu)] from above to update profile information: ");
	
					profile1 = ctPpl.getProfileObjectByProflieName(name);
					String oldProfileName = profile1.getProfileName();
					updateInfoChoice = scannerObj.nextInt();
					scannerObj.nextLine();
					if( updateInfoChoice == 1 )
					{
						System.out.println("Enter new Profile name");
						String newProfileName = "";
						newProfileName = scannerObj.nextLine();
						profile1.setProfileName(newProfileName);
						System.out.println("Profile name is changed from '" + oldProfileName + "' to '" +profile1.getProfileName() );
					}
					else if( updateInfoChoice == 2 )
					{
						System.out.println("Enter new Image path");
						String newProfileName = "";
						newProfileName = scannerObj.nextLine();
						profile1.setProfileName(newProfileName);
						System.out.println("Profile name is changed from '" + oldProfileName + "' to '" +profile1.getProfileName() );
					}
					else if( updateInfoChoice == 3 )
					{
	
					}
					else if( updateInfoChoice == 0 )
					{
	
					}
					else
					{
						System.out.println("Invalid choice");
					}
				}
				else
				{
					System.out.println("Profile not found");
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("[Menu] updateProfileInformation: " + e.getMessage());
		}
	}

	public void deletePerson()
	{
		String name = "";
		String profileName = "";
		Adult adult1 = null;
		Profile profile1 = null;
		String selectedId = "";
		try
		{
			scannerObj = new Scanner(System.in);
			System.out.println("Please enter person to delete");
			name = scannerObj.nextLine();
			if( (adult1 = ctPpl.getAdultObjectByName(name) ) != null )
			{
				adult1.displayPersonDetails();
				profile1 = adult1.getProfile();
				selectedId = adult1.getID();
				ctPpl.deletePersonById(selectedId);

				System.out.println("List of people after deleting person with id: " + selectedId);
				ctPpl.displayAdultDetails();
				ctPpl.displayDependentDetails();

				if( profile1 != null )
				{
					profileName = profile1.getProfileName();
					System.out.println("Profile name associated with this person: " + profileName);
					ctPpl.deleteProfileByName(profile1.getProfileName());
					System.out.println("List of profiles after deleting profile by name " + profileName + " associated with person with id: " + selectedId);
					ctPpl.displayProfiles();
				}
			}
			else
			{
				System.out.println("Person not found");
			}
		}
		catch(Exception e)
		{
			System.out.println("[Menu] deletePerson: " + e.getMessage());
		}
	}
	/*
	 * if they are already connected - display the same
	 */
	public void createConnections()
	{
		String type1 = "";
		String type2 = "";
		try
		{
			scannerObj = new Scanner(System.in);
			String firstPersonId = "";
			String secondPersonId = "";
			if( displayPeopleHaveProfileDetails() )
			{
				if( profileList.size() >= 2 )
				{	
					System.out.println("In order to create connections please select 2 persons by id from the above list");
					System.out.println("Select first person by Id: ");
					do
					{
						firstPersonId = scannerObj.nextLine();
					}while( ! validPersonId(firstPersonId));
					System.out.println("Select second person by Id: ");
					do
					{
						secondPersonId = scannerObj.nextLine(); 
					}while( ! validPersonId(secondPersonId));
					type1 = ctPpl.getPersonTypeById(firstPersonId);
					type2 = ctPpl.getPersonTypeById(secondPersonId);
					if( type1.equalsIgnoreCase("adult") && type2.equalsIgnoreCase("adult") )
					{
						createConnectionBetweenAdults(firstPersonId, secondPersonId);
					}
					else if( type1.equalsIgnoreCase("adult") && type2.equalsIgnoreCase("dependent") )
					{
						createConnectionBetweenAdultAndDependent(firstPersonId, secondPersonId);
					}
					else if( type1.equalsIgnoreCase("dependent") && type2.equalsIgnoreCase("adult") )
					{
						createConnectionBetweenDependentAndAdult(firstPersonId, secondPersonId);
					}
					else if( type1.equalsIgnoreCase("dependent") && type2.equalsIgnoreCase("dependent") )
					{
						createConnectionBetweenDependents(firstPersonId, secondPersonId);
					}
					else
					{
						System.out.println("Connection cannot be created");
					}
				}
				else
				{
					System.out.println("There has to be at least 2 profiles to create connection");
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("[Menu]createConnections " +e.getMessage());
		}
	}

	private boolean displayPeopleHaveProfileDetails()
	{
		boolean hasProfile = false;
		try
		{
			//list of Adults who have profile can only be connected!
			System.out.println("List of people who have profiles: ");
			for( int i = 0; i < adultList.size(); i ++)
			{
				if( adultList.get(i).getHasProfile() )
				{
					hasProfile = true;
					System.out.println("Person type: " + adultList.get(i).getType());
					System.out.println("Person ID: " + adultList.get(i).getID());
					System.out.println("Person name: " + adultList.get(i).getName());
					System.out.println("Profile name: " + adultList.get(i).getProfile().getProfileName());
					System.out.println("Has partner: " + adultList.get(i).getHasPartner());
					System.out.println();
					profileListWithPersonIds.add(adultList.get(i).getID());
				}
			}
			for( int i = 0; i < dependentList.size(); i++)
			{
				if( dependentList.get(i).getHasProfile() )
				{
					hasProfile = true;
					System.out.println("Person type: " + dependentList.get(i).getType());
					System.out.println("Person ID: " + dependentList.get(i).getID());
					System.out.println("Person name: " + dependentList.get(i).getName());
					System.out.println("Profile name: " + dependentList.get(i).getProfile().getProfileName());
					System.out.println();
					profileListWithPersonIds.add(dependentList.get(i).getID());
				}
			}
			if( ! hasProfile )
			{
				System.out.println("There are no profiles in the network");
			}
		}
		catch(Exception e)
		{
			System.out.println("[Menu]displayPeopleHaveProfileDetails: " + e.getMessage());
		}
		return hasProfile;
	}

	private boolean validPersonId(String personId)
	{
		boolean isValid = false;
		try
		{
			for( int i = 0; i < profileListWithPersonIds.size(); i++)
			{
				if(  profileListWithPersonIds.get(i).equalsIgnoreCase(personId))
				{
					isValid = true;
				}
			}
			
			if( isValid )
			{
				System.out.println("Person Id: " + personId + " is a valid selection");
			}
			else
			{
				System.out.println("Person Id: " + personId + " is not a valid selection. Please select a valid ID");
			}
		}
		catch(Exception e)
		{
			System.out.println("[Menu]validPersonId: " + e.getMessage());
		}
		return isValid;
	}
private void createConnectionBetweenAdults(String firstPersonId, String secondPersonId)
	{
		Adult adult1 = null, adult2 = null;
		Connection connection1 = null, connection2 = null;
		List<Connection> connectionList1 = null, connectionList2 = null;
		int connectionChoice = 0;
		try
		{
			System.out.println("Creating connection between two adults");
			adult1 = ctPpl.getAdultObjectById(firstPersonId);
			adult2 = ctPpl.getAdultObjectById(secondPersonId);
			//if
			connectionList1 = adult1.getProfile().getConnections();
			connectionList2 = adult2.getProfile().getConnections();
			System.out.println();
			System.out.println("con btw ad: connectionList1.size()>> " + connectionList1.size());
			System.out.println("con btw ad: connectionList2.size()>> " + connectionList2.size());
			if( connectionList1.size() == 0 && connectionList2.size() == 0 )
			{
				System.out.println("Case 1");
				System.out.println("Possible connection type: ");
				System.out.println("1. Friends");
				System.out.println("2. Partners");
				System.out.println("0. Go back to previous menu");
				System.out.println("Please choose the type of connection [ 1 / 2 / 0(Go back to previous menu)] ");
				connectionChoice = getMenuChoiceInt("sub3");
				if( connectionChoice == 1 )
				{
					//friends
					connection1 = new Connection("Friends", adult1);
					connection2 = new Connection("Friends", adult2);
					adult1.getProfile().setConnection(connection2);
					adult2.getProfile().setConnection(connection1);
				}
				else if( connectionChoice == 2 )
				{
					//partners
					connection1 = new Connection("Partners", adult1);
					connection2 = new Connection("Partners", adult2);
					adult1.getProfile().setConnection(connection2);
					adult2.getProfile().setConnection(connection1);
					adult1.setPartner(true);
					adult2.setPartner(true);
					partnerList.add(adult1);
					//connection1.setNumberOfPartners();
				}
				else if( connectionChoice == 0 )
				{
					System.out.println("Go back to prevoius menu - Selected");
					scannerObj = null;
					displayMenu(this.ctPpl);
				}
				else
				{
					System.out.println("Invalid choice");
				}
			}
			//
			else if( connectionList1.size() > 0 && connectionList2.size() == 0 )
			{
				if( ! adult1.getHasPartner() )
				{
					System.out.println("Possible connection type: ");
					System.out.println("1. Friends");
					System.out.println("2. Partners"); //partnerList.add
					System.out.println("0. Go back to previous menu");
					System.out.println("Please choose the type of connection [ 1 / 2 / 0(Go back to previous menu)] ");
					connectionChoice = getMenuChoiceInt("sub3");
					if( connectionChoice == 1 )
					{
						//friends
						connection1 = new Connection("Friends", adult1);
						connection2 = new Connection("Friends", adult2);
						adult1.getProfile().setConnection(connection2);
						adult2.getProfile().setConnection(connection1);
					}
					else if( connectionChoice == 2 )
					{
						//partners
						connection1 = new Connection("Partners", adult1);
						connection2 = new Connection("Partners", adult2);
						adult1.getProfile().setConnection(connection2);
						adult2.getProfile().setConnection(connection1);
						adult1.setPartner(true);
						adult2.setPartner(true);
						partnerList.add(adult1);
						//connection1.setNumberOfPartners();
					}
					else if( connectionChoice == 0 )
					{
						System.out.println("Go back to prevoius menu - Selected");
						scannerObj = null;
						displayMenu(this.ctPpl);
					}
					else
					{
						System.out.println("Invalid choice");
					}
				}
				else
				{
					System.out.println("Possible connection type: ");
					System.out.println("1. Friends");
					System.out.println("0. Go back to previous menu");
					System.out.println("Please choose the type of connection [ 1 / 0(Go back to previous menu)]");
					connectionChoice = getMenuChoiceInt("sub2");
					if( connectionChoice == 1 )
					{
						//friends
						connection1 = new Connection("Friends", adult1);
						connection2 = new Connection("Friends", adult2);
						adult1.getProfile().setConnection(connection2);
						adult2.getProfile().setConnection(connection1);
					}
					else if( connectionChoice == 0 )
					{
						System.out.println("Go back to prevoius menu - Selected");
						scannerObj = null;
						displayMenu(this.ctPpl);
					}
					else
					{
						System.out.println("Invalid choice");
					}
				}
			}
			//
			else if( connectionList1.size() == 0 && connectionList2.size() > 0 )
			{
				if( ! adult2.getHasPartner() )
				{
					System.out.println("Possible connection type: ");
					System.out.println("1. Friends");
					System.out.println("2. Partners"); //partnerList
					System.out.println("Please choose the type of connection [ 1 / 2] ");
					connectionChoice = getMenuChoiceInt("sub2");
					if( connectionChoice == 1 )
					{
						//friends
						connection1 = new Connection("Friends", adult1);
						connection2 = new Connection("Friends", adult2);
						adult1.getProfile().setConnection(connection2);
						adult2.getProfile().setConnection(connection1);
					}
					else if( connectionChoice == 2 )
					{
						//partners
						connection1 = new Connection("Partners", adult1);
						connection2 = new Connection("Partners", adult2);
						adult1.getProfile().setConnection(connection2);
						adult2.getProfile().setConnection(connection1);
						adult1.setPartner(true);
						adult2.setPartner(true);
						partnerList.add(adult1);
						//connection1.setNumberOfPartners();
					}
					else if( connectionChoice == 0 )
					{
						System.out.println("Go back to prevoius menu - Selected");
						scannerObj = null;
						displayMenu(this.ctPpl);
					}
					else
					{
						System.out.println("Invalid choice");
					}
				}
				else
				{
					System.out.println("Possible connection type: ");
					System.out.println("1. Friends");
					System.out.println("0. Go back to previous menu");
					System.out.println("Please choose the type of connection [ 1 / 0(Go back to previous menu)]");
					connectionChoice = getMenuChoiceInt("sub2");
					if( connectionChoice == 1 )
					{
						//friends
						connection1 = new Connection("Friends", adult1);
						connection2 = new Connection("Friends", adult2);
						adult1.getProfile().setConnection(connection2);
						adult2.getProfile().setConnection(connection1);
					}
					else if( connectionChoice == 0 )
					{
						System.out.println("Go back to prevoius menu - Selected");
						scannerObj = null;
						displayMenu(this.ctPpl);
					}
					else
					{
						System.out.println("Invalid choice");
					}
				}
			}
			//
			else if( connectionList1.size() > 0 && connectionList2.size() > 0 )
			{
				if( adult1.getHasPartner() )
				{
					for( int i = 0; i < connectionList1.size(); i ++)
					{
						if( adult1.getProfile().getConnections().get(i).getAdult().getID().equalsIgnoreCase(adult2.getID()) )
						{
							System.out.println("adult1.getProfile().getConnections().get(i).getAdult().getID()>> " + adult1.getProfile().getConnections().get(i).getAdult().getID());
							System.out.println("Persons with ids " + firstPersonId + " and " + secondPersonId + " are already connected");
							System.out.println("Connection type: Partners, therefore they are implicitly friends");
						}
						else
						{
							System.out.println("Possible connection");
							System.out.println("1. Friends");
							System.out.println("0. Go back to previous menu");
							System.out.println("Please choose the type of connection [ 1 / 0(Go back to previous menu)]");
							connectionChoice = getMenuChoiceInt("sub2");
							if( connectionChoice == 1 )
							{
								//friends
								connection1 = new Connection("Friends", adult1);
								connection2 = new Connection("Friends", adult2);
								adult1.getProfile().setConnection(connection2);
								adult2.getProfile().setConnection(connection1);
							}
							else if( connectionChoice == 0 )
							{
								System.out.println("Go back to prevoius menu - Selected");
								scannerObj = null;
								displayMenu(this.ctPpl);
							}
							else
							{
								System.out.println("Invalid choice");
							}
						}
					}
				}
				else if( adult2.getHasPartner() )
				{
					for( int i = 0; i < connectionList2.size(); i ++)
					{
						if( adult2.getProfile().getConnections().get(i).getAdult().getID().equalsIgnoreCase(adult1.getID()) )
						{
							System.out.println("adult2.getProfile().getConnections().get(i).getAdult().getID()>> " + adult2.getProfile().getConnections().get(i).getAdult().getID());
							System.out.println("Persons with ids " + firstPersonId + " and " + secondPersonId + " are already connected");
							System.out.println("Connection type: Partners, therefore they are implicitly friends");
						}
						else
						{
							System.out.println("Possible connection");
							System.out.println("1. Friends");
							System.out.println("0. Go back to previous menu");
							System.out.println("Please choose the type of connection [ 1 / 0(Go back to previous menu)]");
							connectionChoice = getMenuChoiceInt("sub2");
							if( connectionChoice == 1 )
							{
								//friends
								connection1 = new Connection("Friends", adult1);
								connection2 = new Connection("Friends", adult2);
								adult1.getProfile().setConnection(connection2);
								adult2.getProfile().setConnection(connection1);
							}
							else if( connectionChoice == 0 )
							{
								System.out.println("Go back to prevoius menu - Selected");
								scannerObj = null;
								displayMenu(this.ctPpl);
							}
							else
							{
								System.out.println("Invalid choice");
							}
						}
					}
				}
				else
				{
					System.out.println("connectionList1.size()>> " + connectionList1.size());
					//if they are already friends then partner is the only connection type possible
					//else either partner or friends
					for( int i = 0; i < connectionList1.size(); i ++ )
					{
						System.out.println("adult1.getProfile().getConnections().get(i).getAdult().getID()>> " + adult1.getProfile().getConnections().get(i).getAdult().getID());
						System.out.println("adult2.getID()>> " + adult2.getID());
						if( adult1.getProfile().getConnections().get(i).getAdult().getID().equalsIgnoreCase(adult2.getID()) )
						{
							System.out.println(">>??i: " + i);
							System.out.println("Possible connection: ");
							System.out.println("1. Partner"); //partnerList
							System.out.println("0. Go back to previous menu");
							connectionChoice = getMenuChoiceInt("sub2");
							System.out.println("connectionChoice>> " + connectionChoice);
							if( connectionChoice == 1 )
							{
								System.out.println("reached?");
								//partners
								connection1 = new Connection("Partners", adult1);
								connection2 = new Connection("Partners", adult2);
								adult1.getProfile().setConnection(connection2);
								adult2.getProfile().setConnection(connection1);
								adult1.setPartner(true);
								adult2.setPartner(true);
								partnerList.add(adult1);
								break;
								//connection1.setNumberOfPartners();
							}
							else if( connectionChoice == 0 )
							{
								System.out.println("Go back to prevoius menu - Selected");
								scannerObj = null;
								displayMenu(this.ctPpl);
								break;
							}
						}
						else
						{
							if( i == ( connectionList1.size() - 1 ) )
							{
								System.out.println("Possible connections: ");
								System.out.println("1. Friends");
								System.out.println("2. Partners");//partnerList
								System.out.println("0. Go back to previous menu");
								System.out.println("Please choose the type of connection [ 1 / 2 / 0(Go back to previous menu)] ");
								connectionChoice = getMenuChoiceInt("sub3");
								if( connectionChoice == 1 )
								{
									//friends
									connection1 = new Connection("Friends", adult1);
									connection2 = new Connection("Friends", adult2);
									adult1.getProfile().setConnection(connection2);
									adult2.getProfile().setConnection(connection1);
									break;
								}
								else if( connectionChoice == 2 )
								{
									//partners
									connection1 = new Connection("Partners", adult1);
									connection2 = new Connection("Partners", adult2);
									adult1.getProfile().setConnection(connection2);
									adult2.getProfile().setConnection(connection1);
									adult1.setPartner(true);
									adult2.setPartner(true);
									partnerList.add(adult1);
									break;
									//connection1.setNumberOfPartners();
								}
								else if( connectionChoice == 0 )
								{
									System.out.println("Go back to prevoius menu - Selected");
									scannerObj = null;
									displayMenu(this.ctPpl);
								}
								else
								{
									System.out.println("Invalid choice");
								}
							}
						}
					}
				}
			}
			System.out.println("list of conneection for adult1: ");
			totalconnectionList = adult1.getProfile().getConnections();
			System.out.println("totalconnectionList: " + totalconnectionList.size());
			System.out.println("Connection type: " + adult1.getProfile().getConnections().get(0).getConnectionType());
			System.out.println("Connection adult ID: " + adult1.getProfile().getConnections().get(0).getAdult().getID());
			System.out.println("Connection adult1 partner: " + adult1.getProfile().getConnections().get(0).getAdult().getHasPartner());
			System.out.println("Connection adult2 partner: " + adult2.getProfile().getConnections().get(0).getAdult().getHasPartner());
		}
		catch(Exception e)
		{
			System.out.println("[Menu]createConnectionBetweenAdults: " + e.getMessage());
		}
	}

	private void createConnectionBetweenAdultAndDependent(String firstPersonId, String secondPersonId)
	{
		Adult adult1 = null, adult2 = null;
		Dependent depen1 = null;
		Connection connection1 = null, connection2 = null, connection3 = null;
		List<Connection> connectionList1 = null;
		int connectionChoice = 0;
		try
		{
			System.out.println("Creating connection between an adult and dependent");
			adult1 = ctPpl.getAdultObjectById(firstPersonId);
			depen1 = ctPpl.getDependentObjectById(secondPersonId);

			if( adult1.getHasPartner() )
			{
				if( ! ( depen1.getHasParent() ) )
				{
					connectionList1 = adult1.getProfile().getConnections();
					System.out.println("connectionList1>> " + connectionList1.size());
					for(int i = 0; i < connectionList1.size(); i ++)
					{
						//System.out.println(i + "adult1.getProfile().getConnections().get(i).getAdult().getID()>> " + adult1.getProfile().getConnections().get(i).getAdult().getID());
						System.out.println(i + "connectionList1.get(i).getConnectionType()>> " + connectionList1.get(i).getConnectionType());
						if( connectionList1.get(i).getConnectionType().equalsIgnoreCase("Partners") )
						{
							System.out.println(i + "adult1.getProfile().getConnections().get(i).getAdult().getID()2>> " + adult1.getProfile().getConnections().get(i).getAdult().getID());
							System.out.println(i + "connectionList1.get(i).getConnectionType()2>> " + connectionList1.get(i).getConnectionType());
							System.out.println("adult1.getProfile().getConnections().get(i).getAdult()>> " + adult1.getProfile().getConnections().get(i).getAdult());
							adult2 = adult1.getProfile().getConnections().get(i).getAdult(); 
						}
					}

					System.out.println("Possible connection type: ");
					System.out.println("1. Family");
					System.out.println("0. Go back to previous menu");
					System.out.println("Please choose the type of connection [ 1 / 0 (Go back to previous menu)] ");
					connectionChoice = getMenuChoiceInt("sub2");
					System.out.println(">>connectionChoice: " + connectionChoice);
					System.out.println(">> adult1: " + adult1.getID());
					System.out.println(">> adult2: " + adult2.getID());
					if( connectionChoice == 1 )
					{
						System.out.println("create family?");
						connection1 = new Connection("Family", adult1);
						connection2 = new Connection("Family", adult2);
						connection3 = new Connection("Family", depen1);
						adult1.getProfile().setConnection(connection3);
						adult2.getProfile().setConnection(connection3);
						depen1.getProfile().setConnection(connection1);
						depen1.getProfile().setConnection(connection2);
						adult1.setHasChild(true);
						adult2.setHasChild(true);
						depen1.setHasParent(true);
					}
					else if( connectionChoice == 0 )
					{
						System.out.println("Go back to prevoius menu - Selected");
						scannerObj = null;
						displayMenu(this.ctPpl);
					}
					else
					{
						System.out.println("Invalid choice");
					}
				}
				else
				{
					System.out.println("Dependent has a parent");
				}
			}
			else
			{
				System.out.println("Selected adult has no partner, hence connnection can not be created.");
			}
		}
		catch(Exception e)
		{
			System.out.println("[Menu]createConnectionBetweenAdultAndDependent: " + e.getMessage());
		}
	}

	private void createConnectionBetweenDependentAndAdult(String firstPersonId, String secondPersonId)
	{
		Adult adult1 = null, adult2 = null;
		Dependent depen1 = null;
		Connection connection1 = null, connection2 = null, connection3 = null;
		List<Connection> connectionList1 = null;
		int connectionChoice = 0;
		try
		{
			System.out.println("Creating connection between an adult and dependent");
			depen1 = ctPpl.getDependentObjectById(firstPersonId);
			adult1 = ctPpl.getAdultObjectById(secondPersonId);

			if( adult1.getHasPartner() )
			{
				if( ! ( depen1.getHasParent() ) )
				{
					connectionList1 = adult1.getProfile().getConnections();
					for(int i = 0; i < connectionList1.size(); i ++)
					{
						if( adult1.getProfile().getConnections().get(i).getAdult().getID().equalsIgnoreCase(adult1.getID() ) )
						{
							adult2 = adult1.getProfile().getConnections().get(i).getAdult(); 
						}
					}

					System.out.println("Possible connection type: ");
					System.out.println("1. Family");
					System.out.println("0. Go back to previous menu");
					System.out.println("Please choose the type of connection [ 1 / 0 (Go back to previous menu)] ");
					connectionChoice = getMenuChoiceInt("sub2");
					if( connectionChoice == 1 )
					{
						connection1 = new Connection("Family", adult1);
						connection2 = new Connection("Family", adult2);
						connection3 = new Connection("Family", depen1);
						adult1.getProfile().setConnection(connection3);
						adult2.getProfile().setConnection(connection3);
						depen1.getProfile().setConnection(connection1);
						depen1.getProfile().setConnection(connection2);
						adult1.setHasChild(true);
						adult2.setHasChild(true);
						depen1.setHasParent(true);
					}
					else if( connectionChoice == 0 )
					{
						System.out.println("Go back to prevoius menu - Selected");
						scannerObj = null;
						displayMenu(this.ctPpl);
					}
					else
					{
						System.out.println("Invalid choice");
					}
				}
				else
				{
					System.out.println("Dependent has a parent");
				}
			}
			else
			{
				System.out.println("Selected adult has no partner, hence connnection can not be created.");
			}
		}
		catch(Exception e)
		{
			System.out.println("[Menu]createConnectionBetweenDependentAndAdult: " + e.getMessage());
		}
	}

	private void createConnectionBetweenDependents(String firstPersonId, String secondPersonId)
	{
		Adult adult1 = null, adult2 = null;
		Dependent depen1 = null, depen2 = null;
		Connection connection1 = null, connection2 = null;
		List<Connection> connectionList1 = null, connectionList2 = null;
		int connectionChoice = 0;
		int ageDiff = 0;
		try
		{
			depen1 = ctPpl.getDependentObjectById(firstPersonId);
			depen2 = ctPpl.getDependentObjectById(secondPersonId);

			if( depen1.getHasParent() && depen2.getHasParent() )
			{
				connectionList1 = depen1.getProfile().getConnections();
				connectionList2 = depen2.getProfile().getConnections();
				for( int i = 0; i < connectionList1.size(); i ++ )
				{
					adult1 = connectionList1.get(i).getAdult();
				}
				for( int i = 0; i < connectionList2.size(); i++ )
				{
					adult2 = connectionList2.get(i).getAdult();
				}

				if( adult1.getID().equalsIgnoreCase(adult2.getID() ) )
				{
					System.out.println("The selected dependents are siblings, implicitly they both are friends");
					connection1 = new Connection("Sibling", depen1);
					connection2 = new Connection("Sibling", depen2);
					depen1.getProfile().setConnection(connection2);
					depen2.getProfile().setConnection(connection1);
				}
				else
				{
					System.out.println("1. Dependent " + depen1.getProfile().getProfileName() + " age: " + depen1.getProfile().getAge());
					System.out.println("2. Dependent " + depen2.getProfile().getProfileName() + " age: " + depen2.getProfile().getAge());
					if( depen1.getProfile().getAge() > 2 && depen2.getProfile().getAge() > 2 )
					{
						System.out.println("1. Dependent " + depen1.getProfile().getProfileName() + " age: " + depen1.getProfile().getAge());
						System.out.println("2. Dependent " + depen2.getProfile().getProfileName() + " age: " + depen2.getProfile().getAge());
						if( depen1.getProfile().getAge() > depen2.getProfile().getAge() )
						{
							ageDiff = depen1.getProfile().getAge() - depen2.getProfile().getAge();
						}
						else
						{
							ageDiff = depen2.getProfile().getAge() - depen1.getProfile().getAge();
						}
						if( ageDiff < 3 )
						{
							System.out.println("Possible connection");
							System.out.println("1. Friend");
							System.out.println("0. Go back to previous menu");
							System.out.println("Please choose the type of connection [ 1 / 0 (Go back to previous menu)] ");
							connectionChoice = getMenuChoiceInt("sub2");
							if( connectionChoice == 1 )
							{
								connection1 = new Connection("Friend", depen1);
								connection2 = new Connection("Friend", depen2);
								depen1.getProfile().setConnection(connection2);
								depen2.getProfile().setConnection(connection1);
							}
							else if( connectionChoice == 0 )
							{
								System.out.println("Go back to previous menu");
							}
							else
							{
								System.out.println("Invalid choice");
							}
						}
						else
						{
							System.out.println("Cannot create connection. Age differences between the dependents is more than 3 years");
						}
					}
					else
					{
						System.out.println("Cannot create connection. Dependents are 2 years of age.");
					}
				}
			}
			else
			{
				System.out.println("Cannot create connection.");
			}
		}
		catch(Exception e)
		{
			System.out.println("[Menu]createConnectionBetweenDependents: " + e.getMessage());
		}
	}

	public void connectionLookUpFriends()
	{
		String firstPersonId = "";
		String secondPersonId = "";
		String type1 = "", type2 = ""; 
		Adult adult1 = null, adult2 = null;
		Dependent depen1 = null, depen2 = null;
		List<Connection> connectionList1 = null, connectionList2= null;
		boolean isDirectFriends = false;
		try
		{
			/*
			 	1. Person with only profile
			 */
			if ( displayPeopleHaveProfileDetails() )
			{
				System.out.println("Please select 2 persons by ID to find the relationship between them from the above list of people");
				scannerObj = new Scanner(System.in);
				displayPeopleHaveProfileDetails();
				System.out.println("In order to create connections please select 2 persons by id from the above list");
				System.out.println("Select first person by Id: ");
				do
				{
					firstPersonId = scannerObj.nextLine();
				}while( ! validPersonId(firstPersonId));
				System.out.println("Select second person by Id: ");
				do
				{
					secondPersonId = scannerObj.nextLine(); 
				}while( ! validPersonId(secondPersonId));
				type1 = ctPpl.getPersonTypeById(firstPersonId);
				type2 = ctPpl.getPersonTypeById(secondPersonId);
				if( type1.equalsIgnoreCase("adult") && type2.equalsIgnoreCase("adult") )
				{
					adult1 = ctPpl.getAdultObjectById(firstPersonId);
					adult2 = ctPpl.getAdultObjectById(secondPersonId);
					connectionList1 = adult1.getProfile().getConnections();
					connectionList2 = adult2.getProfile().getConnections();
	
					System.out.println("friends look up connectionList1.size()>> " + connectionList1.size());
					System.out.println("friends look up connectionList2.size()>> " + connectionList2.size());
					if( connectionList1.size() > 0 && connectionList2.size() > 0 )
					{
						for( int i = 0; i < connectionList1.size(); i ++ )
						{
							if( connectionList1.get(i).getAdult().getID().equalsIgnoreCase(adult2.getID()) )
							{
								if( connectionList1.get(i).getConnectionType().equalsIgnoreCase("Friends") )
								{
									isDirectFriends = true;
								}
							}
						}
					}
				}
				else if( type1.equalsIgnoreCase("adult") && type2.equalsIgnoreCase("dependent") )
				{
					adult1 = ctPpl.getAdultObjectById(firstPersonId);
					depen1 = ctPpl.getDependentObjectById(secondPersonId);
				}
				else if( type1.equalsIgnoreCase("dependent") && type2.equalsIgnoreCase("adult") )
				{
					depen1 = ctPpl.getDependentObjectById(firstPersonId);
					adult2 = ctPpl.getAdultObjectById(secondPersonId);
				}
				else if( type1.equalsIgnoreCase("dependent") && type2.equalsIgnoreCase("dependent") )
				{
					depen1 = ctPpl.getDependentObjectById(firstPersonId);
					depen2 = ctPpl.getDependentObjectById(secondPersonId);
					connectionList1 = depen1.getProfile().getConnections();
					connectionList2 = depen2.getProfile().getConnections();
					if( connectionList1.size() > 0 && connectionList2.size() > 0 )
					{
						for( int i = 0; i < connectionList1.size(); i ++ )
						{
							if( connectionList1.get(i).getDependent().getID().equalsIgnoreCase(depen2.getID()) )
							{
								if( connectionList1.get(i).getAdult().getType().equalsIgnoreCase("Friends") )
								{
									System.out.println("Persons ids: 1. " + firstPersonId + " and 2. " + secondPersonId );
									isDirectFriends = true;
								}
							}
						}
					}
				}
				else
				{
					System.out.println("Connection not found");
				}
				if( isDirectFriends )
				{
					System.out.println("Persons ids: 1. " + firstPersonId + " and 2. " + secondPersonId );
					System.out.println("Direct friends: Yes!" );
				}
				else
				{
					System.out.println("Persons ids: 1. " + firstPersonId + " and 2. " + secondPersonId );
					System.out.println("Direct friends: No!" );
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("[Menu]connectionLookUpFriends: ");
		}
	}

	public void connectionLookUpFamily()
	{
		int counter = 1;
		String firstPersonId = "";
		String type1 = ""; 
		Adult adult1 = null;
		Dependent depen1 = null;
		List<Connection> connectionList1 = null;
		try
		{
			if( displayPeopleHaveProfileDetails() )
			{
				System.out.println("Select a person by Id to find out the name(s) of a person’s child(ren) or the names of the parents");
				System.out.println("Select first person by Id: ");
				do
				{
					firstPersonId = scannerObj.nextLine();
				}while( ! validPersonId(firstPersonId));
				type1 = ctPpl.getPersonTypeById(firstPersonId);
				if( type1.equalsIgnoreCase("adult") )
				{
					adult1 = ctPpl.getAdultObjectById(firstPersonId);
					if( adult1.getHasChild() )
					{
						connectionList1 = adult1.getProfile().getConnections();
						for( int i = 0; i < connectionList1.size(); i ++ )
						{
							if( connectionList1.get(i).getConnectionType().equalsIgnoreCase("Family") )
							{
								System.out.println("Child(ren) name(s)");
								System.out.println(counter + ") Person ID: " + connectionList1.get(i).getDependent().getID());
								System.out.println("Person name: " + connectionList1.get(i).getDependent().getName());
								System.out.println("Person profile name: " + connectionList1.get(i).getDependent().getProfile().getProfileName());
								counter++;
							}
						}
					}
				}
				else if( type1.equalsIgnoreCase("dependent") )
				{
					depen1 = ctPpl.getDependentObjectById(firstPersonId);
					if( depen1.getHasParent() )
					{
						connectionList1 = depen1.getProfile().getConnections();
						for( int i = 0; i < connectionList1.size(); i ++)
						{
							System.out.println("Names of parents");
							System.out.println("Person Id: " + connectionList1.get(i).getAdult().getID());
							System.out.println("Person Name: " + connectionList1.get(i).getAdult().getName());
							System.out.println("Person profile name: " + connectionList1.get(i).getAdult().getProfile().getProfileName());
						}
						//display parent names
					}
				}
				else
				{
					System.out.println("Selected person has no family connection");
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("[Menu]connectionLookUpFamily: " + e.getMessage());
		}
	}
	
	public int getMenuChoiceInt(String filter)
	{
		int choice = 0;
		try
		{
			scannerObj = new Scanner(System.in);
			if( filter.equalsIgnoreCase("main") )
			{
				choice = validateIntMenuOption(1, 9, "main");
			}
			if( filter.equalsIgnoreCase("sub3") )
			{
				choice = validateIntMenuOption(0, 2, "sub3");
			}
			if( filter.equalsIgnoreCase("sub2") )
			{
				choice = validateIntMenuOption(0, 1, "sub2");
			}
			if( filter.equalsIgnoreCase("ageAdult") )
			{
				choice = validateIntMenuOption(16, 99, "ageAdult");
			}
			if( filter.equalsIgnoreCase("ageDepen") )
			{
				choice = validateIntMenuOption(1, 15, "ageDepen");
			}
		}
		catch(Exception e)
		{
			System.out.println("[Menu]validMenuChoiceInt: " + e.getMessage() );
		}
		return choice;
	}
	
	public int validateIntMenuOption(int min, int max, String filter)
	{
		System.out.println(">>filter " + filter);
		boolean isValid = false;
		int choice = 0;
		try
		{
			do
			{
				if( scannerObj.hasNextInt() )
				{
					choice = scannerObj.nextInt();
					scannerObj.nextLine();
					if( choice >=min && choice <=max )
					{
						isValid = true;
					}
					else
					{
						if( filter.equalsIgnoreCase("ageAdult")  )
						{
							System.out.println("Enter age between 16 and 99 [inclusive]");
						}
						else if( filter.equalsIgnoreCase("ageDepen") )
						{
							System.out.println("Enter age between 2 and 15 [inclusive]");
						}
						else
						{
							System.out.println("Enter a valid option");
						}
					}
				}
				else
				{
					scannerObj.nextLine();
					if( filter.equalsIgnoreCase("ageAdult") || filter.equalsIgnoreCase("ageDepen") )
					{
						System.out.println("Enter age between 16 and 99 [inclusive]");
					}
					else if( filter.equalsIgnoreCase("ageDepen") )
					{
						System.out.println("Enter age between 2 and 15 [inclusive]");
					}
					else
					{
						System.out.println("Enter a valid option");
					}
				}
			}while( ! isValid );
		}
		catch(Exception e)
		{
			System.out.println("[Menu]validateIntMenuOption " + e.getMessage());
		}
		System.out.println(">>isValid:  " + isValid);
		return choice;
	}
}
/*
5 adults in the system 3 has profile and 3 don't
adultList = ctPpl.getListOfAdult();
for( int i = 0; i < this.adultList.size(); i++ )
{
	if( i != 3 && i != 4 )
	{
		p1 = new Profile( profileName + i, i, profileImage + i, profileStatus + i );
		this.adultList.get(i).setProfile(p1);
		System.out.println(this.adultList.get(i).getHasProfile());
	}
}
 */
/*
 * debug code to be deleted:
 creation of connection
adult1 = ctPpl.getAdultObjectById("a1");
adult2 = ctPpl.getAdultObjectById("a2");
connectionList1 = adult1.getProfile().getConnections();
connectionList2 = adult2.getProfile().getConnections();
connection1 = new Connection("Partners", adult1);
connection2 = new Connection("Partners", adult2);
adult1.getProfile().setConnection(connection2);
adult2.getProfile().setConnection(connection1);
adult1.setPartner(true);
adult2.setPartner(true);
partnerList.add(adult1);
connection1.setNumberOfPartners();

connection1 = new Connection("Friends", adult1);
connection2 = new Connection("Friends", adult2);
adult1.getProfile().setConnection(connection2);
adult2.getProfile().setConnection(connection1);
 */

/*
between adults: 
	friend - only if they aren't partners
	partners - if they are friends, update the connection type from friends partners 
 */
