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
