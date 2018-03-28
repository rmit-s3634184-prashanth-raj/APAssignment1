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
