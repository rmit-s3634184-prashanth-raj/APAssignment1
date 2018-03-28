package utility;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


import minNetPackage.Adult;
import minNetPackage.Connection;
import minNetPackage.Dependent;
import minNetPackage.Profile;

/**
 * 
 * @author Prashanth, Sumit
 * Once the program is triggered, all the actions [CRUD] are performed by this class.
 *
 */
public class Driver 
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

	/*
	 * display a list of menu and prompt user to select a valid option.
	 * Parameter: CreatePeople class object in order to access the data and data members of class "CreatePeople"
	 */
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
		System.out.println("4. Display all the profiles in the network");
		System.out.println("5. Update the profile information of the selected person");
		System.out.println("6. Delete the selected person");
		System.out.println("7. Create connections");
		System.out.println("8. Find out whether a person is a direct friend of another person");
		System.out.println("9. Find out the name(s) of a person’s child(ren) or the names of the parents");
		System.out.println("10. Exit!");
		System.out.println("Please select an option from the displayed menu");
		System.out.println(">>");
		choice = getMenuChoiceInt("main");
		
		selectAnOption(choice);
	}

	/*
	 * Choice from the end user is passed to this method to access MiniNet and perform the matching functionality
	 * Parameter: choice => read in displayMenu method, option selected by end user.
	 */
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
				
				case 4: System.out.println("Menu option : " + choice + ". Display all the profiles in the network - selected!");
						ctPpl.displayAllProfileDetails();
						displayMenu(this.ctPpl);
						break;
						
				case 5: System.out.println("Menu option : " + choice + ". Update the profile information of the selected person - selected!");
						updateProfileInformation();
						displayMenu(this.ctPpl);
						break;
	
				case 6: System.out.println("Menu option : " + choice + ". Delete the selected person - selected!");
						deletePerson();
						displayMenu(this.ctPpl);
						break; 
	
				case 7: System.out.println("Menu option : " + choice + ". Create connections - selected!");
						createConnections();
						displayMenu(this.ctPpl);
						break;
	
				case 8: System.out.println("Menu option : " + choice + ". Find out whether a person is a direct friend of another person - selected!");
						connectionLookUpFriends();
						displayMenu(this.ctPpl);
						break;
	
				case 9: System.out.println("Menu option : " + choice + ". Find out the name(s) of a person’s child(ren) or the names of the parents - selected!");
						connectionLookUpFamily();
						displayMenu(this.ctPpl);
						break;
	
				case 10: System.out.println("Menu option : " + choice + ". Exit - selected!");
						System.exit(0);
			}
		}
		catch(Exception e)
		{
			System.out.println("[Driver]selectAnOption: " + e.getMessage());
		}
	}
	
	/*
	 * Functionality 1 - To add a person into the network.
	 * No parameters.
	 */
	private void addPersonIntoNetwork()
	{
		int addChoice = 0;
		try
		{
			scannerObj = new Scanner(System.in);
			//Only person type adults can be added into network.
			if( partnerList.size() <= 0  ) 
			{
				System.out.println("Please select 1 to add an adult or 2 to add a dependent to the network");
				System.out.println("*Please note Option - 2. Add dependent is not available until: There are at least 2 adults with connection type: Partners");
				System.out.println("1. Add adult");
				System.out.println("0. Go back to previous menu");
				addChoice = getMenuChoiceInt("sub2"); //a generic method to handle integer type menu and sub menu choices.
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
			//only if there are at least one partners then add a dependent. Adult can also be added.
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
			System.out.println("[Driver]addPersonIntoNetwork");
		}
	}
	
	/*
	 * Helper method for Adding a person into network functionality
	 * Paramter: choice. To determine person type[Adult / Dependent].
	 */
	private void addAdultIntoNetwork(int addChoice)
	{
		try
		{
			System.out.println("Sub menu option: " + addChoice + ". Add adult - Selected");
			scannerObj = new Scanner(System.in);

			System.out.println("List of people available to be added to the network:");
			createPeopleAndDisplayDetails(); // Display list of people that was created as a prerequisite data.

			searchQuery = selectAPersonById();

			if( ( addProfile(searchQuery, addChoice) ) != null )
			{
				displayListofProfiles(); //after profile is created, display all the profile details
			}
		}
		catch(Exception e)
		{
			System.out.println("[Driver]addAdultIntoNetwork: " + e.getMessage());
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
				displayListofProfiles(); //after profile is created, display all the profile details
			}
		}
		catch(Exception e)
		{
			System.out.println("[Driver]addDependentIntoNetwork: " + e.getMessage());
		}
	}

	/*
	 * Helper method to add a person into network
	 * Parameter:
	 * 1. personId: Id of the person to be added into the network.
	 * 2. addChoice: to determine person type[adult/dependent].
	 * return: profile object 
	 */
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
				profile1 = createAdultProfile(personId, profileName, profileAge, imagePath, status, adult1, profile1); //create adult profile
			}
			else if( addChoice == 2 )
			{
				profile1 = createDependentProfile(personId, profileName, profileAge, imagePath, status, dependent1, profile1); //create dependent profile
			}
			else
			{
				System.out.println("Person not found");
			}
		}
		catch(Exception e)
		{
			System.out.println("[Driver]addProfile: " + e.getMessage());
		}
		return profile1;
	}

	/*
	 * Helper to add an adult into the network:
	 * Personid: preson id of the selected person.
	 * profileName, profileAge, ImagePath, Status, Adult object and profile objects are passed to create an adult profile.
	 * return: profile object
	 */
	private Profile createAdultProfile(String personId, String profileName, int profileAge, String imagePath, String status, Adult adult1, Profile profile1)
	{
		try
		{
			adult1 = ctPpl.getAdultObjectById(personId);
			if( adult1 != null )
			{
				//profile details are captured
				System.out.println("Person with id " + personId + " is selected");
				System.out.println("Please enter Profile Name >>");
				profileName = scannerObj.nextLine();
				System.out.println("Please enter your age [>=16 and <=99] >>");
				profileAge = getMenuChoiceInt("ageAdult");
				System.out.println("Please enter Profile image path >>");
				imagePath = scannerObj.nextLine();
				System.out.println("Please enter Profile status >>");
				status = scannerObj.nextLine();

				//adult profile is created
				profile1 = new Profile(profileName, profileAge, imagePath, status);
				ctPpl.addProfileToList(profile1);
				adult1.setProfile(profile1);
				adult1.setType("adult");
				profileListWithPersonIds.add(personId);
				
				System.out.println();
				System.out.println("Profile was successfully created for person with id: " + personId);
				System.out.println("Name: 	" + profile1.getProfileName());
				System.out.println("Age: 	" + profile1.getAge());
				System.out.println("Type:   " + adult1.getType());
				System.out.println("Image: 	" + profile1.getProfileImagePath());
				System.out.println("Status: " + profile1.getProfileStatus());
			}
		}
		catch(Exception e)
		{
			System.out.println("[Driver]createAdultProfile: " + e.getMessage());
		}
		return profile1;
	}

	/*
	 * Helper to add an dependent into the network:
	 * Personid: preson id of the selected person.
	 * profileName, profileAge, ImagePath, Status, Adult object and profile objects are passed to create an adult profile.
	 * return: profile object
	 */
	private Profile createDependentProfile(String personId, String profileName, int profileAge, String imagePath, String status, Dependent dependent1, Profile profile1)
	{
		boolean isCreated = false;
		String parentId = "";
		try
		{
			dependent1 = ctPpl.getDependentObjectById(personId);
			if( dependent1 != null )
			{
				//profile details are captured
				System.out.println("Person with id " + personId + " is selected");
				System.out.println("Please enter Profile Name >>");
				profileName = scannerObj.nextLine();
				System.out.println("Please enter your age [>1 and <16]>>");
				profileAge = getMenuChoiceInt("ageDepen");
				System.out.println("Please enter Profile image path >>");
				imagePath = scannerObj.nextLine();
				System.out.println("Please enter Profile status >>");
				status = scannerObj.nextLine();
				
				System.out.println("An isolated dependent cannot exist in the system.");
				System.out.println("Please enter one of the parent ID to be added in to the network");
				do
				{
					parentId = scannerObj.nextLine();
				}while( ! validPartnerId(parentId));
				
				//dependent profile is created
				profile1 = new Profile(profileName, profileAge, imagePath, status);
				dependent1.setProfile(profile1);
				dependent1.setType("dependent");
				/*
				 * an isolated dependent cannot exist in the system, hence while adding a dependent into the network, 
				 * if partners exist in the system they are added as family connection. 
				 */
				
				if( createConnectionForIsolatedDependent(parentId, personId) )
				{
					ctPpl.addProfileToList(profile1);
					profileListWithPersonIds.add(personId);
					System.out.println();
					System.out.println("Profile was successfully created for person with id: " + personId);
					System.out.println("Name: 	" + profile1.getProfileName());
					System.out.println("Age: 	" + profile1.getAge());
					System.out.println("Type:   " + dependent1.getType());
					System.out.println("Image: 	" + profile1.getProfileImagePath());
					System.out.println("Status: " + profile1.getProfileStatus());
				}
				else
				{
					System.out.println("Isolated dependent profile cannot be created ");
					profile1 = null;
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("[Driver]createDependentProfile: " + e.getMessage());
		}
		return profile1;
	}
	/*
	 * Display list of profiles that exit in the mini by profile name.
	 */
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
			System.out.println("[Driver]displayListofProfiles: " + e.getMessage());
		}
	}

	/*
	 * fetch and display the people that are created as a prerequisite data at the start of execution.
	 */
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
			System.out.println("[Driver]createPeopleAndDisplayDetails");
		}
	}
	/*
	 * Prompt user to enter person id
	 * return : person ID
	 */
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
			System.out.println("[Driver]selectAPersonById: " + e.getMessage());
		}
		return iD;
	}

	/*
	 * select person by thier name
	 */
	private void selectAPersonByName()
	{
		try
		{
			System.out.println("Search for a person by name: ");
			searchQuery = scannerObj.nextLine();
			System.out.println("Searching '.'.'.' person by name: " + searchQuery);
			System.out.println();
			ctPpl.getPersonDetailsByName(searchQuery);
		}
		catch(Exception e)
		{
			System.out.println("[Driver]selectAPersonByName: " + e.getMessage());
		}
	}
	
	/*
	 * Menu Option 3: displayProfileDetails
	 * Display profile details of person who is searched by profile name
	 */
	private void displayProfileDetails()
	{
		String name = "";
		Profile profile1 = null;
		try
		{
			if( displayProfileNames() )
			{
				scannerObj = new Scanner(System.in);
				System.out.println("Please enter Profile name to get profile details: ");
				name = scannerObj.nextLine();
				if( ctPpl.validSearchByProfileName(name) )
				{
					profile1 = ctPpl.getProfileObjectByProflieName(name);
					System.out.println("Profile details by name: " + name  );
					System.out.println("Profile name: " + profile1.getProfileName());
					System.out.println("Age: " + profile1.getAge());
					System.out.println("Profile image path: " + profile1.getProfileImagePath());
					System.out.println("Profile status: " + profile1.getProfileStatus());
					if( profile1.getConnections().size() > 0 ) 
					{
						System.out.println("Total number of connections: " + profile1.getConnections().size());
					}
					else
					{
						System.out.println("Total number of connections: " + profile1.getConnections().size());
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
			System.out.println("[Driver] displayProfileDetails: " + e.getMessage());
		}
	}
	
	/*
	 * Menu option 4; Update profile information
	 * Profile information:
	 * Name, image path and status can be updated.
	 */
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
					String oldProfileImagePath = profile1.getProfileImagePath();
					int oldAge = profile1.getAge();
					String oldStatus = profile1.getProfileStatus();
					updateInfoChoice = getMenuChoiceInt("sub4");
					if( updateInfoChoice == 1 ) //upadate profile name
					{
						System.out.println("Enter new Profile name");
						String newProfileName = "";
						newProfileName = scannerObj.nextLine();
						profile1.setProfileName(newProfileName);
						System.out.println("Profile name is changed from '" + oldProfileName + "' to '" +profile1.getProfileName() + "'");
					}
					else if( updateInfoChoice == 2 ) // update image path
					{
						System.out.println("Enter new image path");
						String newImagePathName = "";
						newImagePathName= scannerObj.nextLine();
						profile1.setImagePath(newImagePathName);
						System.out.println("Profile image path is changed from '" + oldProfileImagePath + "' to '" +profile1.getProfileImagePath() + "'");
					}
					else if( updateInfoChoice == 3 ) //update status
					{
						System.out.println("Enter new status");
						String newStatus= scannerObj.nextLine();
						profile1.setStatus(newStatus);
						System.out.println("Profile image path is changed from '" + oldStatus + "' to '" +profile1.getProfileStatus() + "'");
					}
					else if( updateInfoChoice == 0 ) 
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
					System.out.println("Profile not found");
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("[Driver] updateProfileInformation: " + e.getMessage());
		}
	}

	/*
	 * Perform delete person
	 * Removes a person, associated profile and from other people connections. 
	 */
	public void deletePerson()
	{
		String name = "";
		String profileName = "";
		Adult adult1 = null;
		Dependent depen1 = null;
		Profile profile1 = null;
		String selectedId = "";
		try
		{
			scannerObj = new Scanner(System.in);
			System.out.println("Please enter person id to delete");
			name = scannerObj.nextLine();
			if( (adult1 = ctPpl.getAdultObjectById(name) ) != null ) //get the person to be deleted by person ID
			{
				adult1.displayPersonDetails();
				profile1 = adult1.getProfile();
				selectedId = adult1.getID();
				ctPpl.deletePersonById(selectedId); //delete the person[adult]

				System.out.println("List of people after deleting person with id: " + selectedId);
				ctPpl.displayAdultDetails();
				ctPpl.displayDependentDetails();

				if( profile1 != null )
				{
					//profileListWithPersonIds
					if( profileListWithPersonIds.size() > 0  )
					{
						for( int i = 0; i < profileListWithPersonIds.size(); i ++)
						{
							if( profileListWithPersonIds.get(i).equalsIgnoreCase(selectedId) )
							{
								profileListWithPersonIds.remove(i);
							}
						}
					}
					profileName = profile1.getProfileName();
					
					profile1 = null;
					adult1.deActivateProfile(profile1); //deactivate the profile associated with the selected person
					
					System.out.println("Profile name associated with this person: " + profileName);
					ctPpl.deleteProfileByName(profileName);
					System.out.println("List of profiles after deleting profile by name " + profileName + " associated with person with id: " + selectedId);
					ctPpl.displayProfiles();
				}
			}
			else if( (depen1 = ctPpl.getDependentObjectById(name) ) != null )
			{
				depen1.displayPersonDetails();
				profile1 = depen1.getProfile();
				selectedId = depen1.getID();
				ctPpl.deletePersonById(selectedId);
				
				System.out.println("List of people after deleting person with id: " + selectedId);
				ctPpl.displayAdultDetails();
				ctPpl.displayDependentDetails();

				if( profile1 != null )
				{
					//profileListWithPersonIds
					if( profileListWithPersonIds.size() > 0  )
					{
						for( int i = 0; i < profileListWithPersonIds.size(); i ++)
						{
							if( profileListWithPersonIds.get(i).equalsIgnoreCase(selectedId) )
							{
								profileListWithPersonIds.remove(i);
							}
						}
					}
					profileName = profile1.getProfileName();
					
					profile1 = null;
					depen1.deActivateProfile(profile1); //deactivate the profile associated with the selected person
					
					System.out.println("Profile name associated with this person: " + profileName);
					ctPpl.deleteProfileByName(profileName);
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
			System.out.println("[Driver] deletePerson: " + e.getMessage());
		}
	}
	/*
	 * Option 7: Create connections:
	 * 1. Connection between adults
	 * 2. Connection between an adult and a dependent
	 * 3. Connection between dependents
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
				//if there are at least 2 profiles only then create connections
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
						createConnectionBetweenAdults(firstPersonId, secondPersonId); //createConnectionBetweenAdults
					}
					else if( type1.equalsIgnoreCase("adult") && type2.equalsIgnoreCase("dependent") )
					{
						createConnectionBetweenAdultAndDependent(firstPersonId, secondPersonId); //createConnectionBetweenAdultAndDependent
					}
					else if( type1.equalsIgnoreCase("dependent") && type2.equalsIgnoreCase("adult") )
					{
						createConnectionBetweenDependentAndAdult(firstPersonId, secondPersonId); //createConnectionBetweenDependentAndAdult
					}
					else if( type1.equalsIgnoreCase("dependent") && type2.equalsIgnoreCase("dependent") )
					{
						createConnectionBetweenDependents(firstPersonId, secondPersonId); //createConnectionBetweenDependents
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
			System.out.println("[Driver]createConnections " +e.getMessage());
		}
	}
	
	/*
	 * Display all the profiles that are in the MiniNet 
	 */
	private boolean displayPeopleHaveProfileDetails()
	{
		boolean hasProfile = false;
		try
		{
			//list of Adults who have profile can only be connected!
			System.out.println();
			System.out.println("List of people who have profiles: ");
			for( int i = 0; i < adultList.size(); i ++)
			{
				//adult type profiles
				if( adultList.get(i).getHasProfile() )
				{
					hasProfile = true;
					System.out.println("Person ID: " + adultList.get(i).getID());
					System.out.println("Person name: " + adultList.get(i).getName());
					System.out.println("Profile name: " + adultList.get(i).getProfile().getProfileName());
					System.out.println("Age: " + adultList.get(i).getProfile().getAge());
					System.out.println("Person type: " + adultList.get(i).getType());
					System.out.println("Has partner: " + adultList.get(i).getHasPartner());
					System.out.println("Has child: " + adultList.get(i).getHasChild());
					System.out.println();
					//profileListWithPersonIds.add(adultList.get(i).getID());
				}
			}
			for( int i = 0; i < dependentList.size(); i++)
			{
				//dependent type profiles
				if( dependentList.get(i).getHasProfile() )
				{
					hasProfile = true;
					System.out.println("Person ID: " + dependentList.get(i).getID());
					System.out.println("Person name: " + dependentList.get(i).getName());
					System.out.println("Profile name: " + dependentList.get(i).getProfile().getProfileName());
					System.out.println("Age: " + dependentList.get(i).getProfile().getAge());
					System.out.println("Person type: " + dependentList.get(i).getType());
					System.out.println("Has parent: " + dependentList.get(i).getHasParent());
					System.out.println();
					//profileListWithPersonIds.add(dependentList.get(i).getID());
				}
			}
			if( ! hasProfile )
			{
				System.out.println("There are no profiles in the network");
			}
		}
		catch(Exception e)
		{
			System.out.println("[Driver]displayPeopleHaveProfileDetails: " + e.getMessage());
		}
		return hasProfile;
	}
	
	/*
	 * display all the profiles in the MiniNet by names
	 */
	private boolean displayProfileNames()
	{
		boolean hasProfile = false;
		try
		{
			//list of Adults who have profile can only be connected!
			System.out.println();
			System.out.println("List of people who have profiles: ");
			for( int i = 0; i < adultList.size(); i ++)
			{
				if( adultList.get(i).getHasProfile() )
				{
					hasProfile = true;
					System.out.println("Person ID: " + adultList.get(i).getID());
					System.out.println("Profile name: " + adultList.get(i).getProfile().getProfileName());
					System.out.println();
					//profileListWithPersonIds.add(adultList.get(i).getID());
				}
			}
			for( int i = 0; i < dependentList.size(); i++)
			{
				if( dependentList.get(i).getHasProfile() )
				{
					hasProfile = true;
					System.out.println("Person ID: " + dependentList.get(i).getID());
					System.out.println("Profile name: " + dependentList.get(i).getProfile().getProfileName());
					System.out.println();
					//profileListWithPersonIds.add(dependentList.get(i).getID());
				}
			}
			if( ! hasProfile )
			{
				System.out.println("There are no profiles in the network");
			}
		}
		catch(Exception e)
		{
			System.out.println("[Driver]displayPeopleHaveProfileDetails: " + e.getMessage());
		}
		return hasProfile;
	}
	
	/*
	 * For most of the operation person id is used to perform them
	 * return: true if a person exist or false.
	 */
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
			System.out.println("[Driver]validPersonId: " + e.getMessage());
		}
		return isValid;
	}
	
	/*
	 * While connecting a dependent to a partners a valid partner id must be entered which is
	 * validated by this method.
	 * return true if valid partner id else false.
	 */
	private boolean validPartnerId(String personId)
	{
		boolean isValid = false;
		try
		{
			for( int i = 0; i < partnerList.size(); i++)
			{
				System.out.println("partner list>> " + partnerList.get(i).getID());
				if( partnerList.get(i).getHasProfile() )
				{
					if(  partnerList.get(i).getID().equalsIgnoreCase(personId))
					{
						isValid = true;
					}
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
			System.out.println("[Driver]validPersonId: " + e.getMessage());
		}
		return isValid;
	}

	/*
	 * createConnectionBetweenAdults
	 * parameter:
	 * person ids of first and second persons
	 */
	private void createConnectionBetweenAdults(String firstPersonId, String secondPersonId)
	{
		Adult adult1 = null, adult2 = null;
		Connection connection1 = null, connection2 = null;
		List<Connection> connectionList1 = null, connectionList2 = null;
		int connectionChoice = 0 , connectionIndex = -1;
		boolean isConnectionCreated = false;
		try
		{
			System.out.println();
			System.out.println(">>Creating connection between two adults");
			adult1 = ctPpl.getAdultObjectById(firstPersonId);
			adult2 = ctPpl.getAdultObjectById(secondPersonId);
			//if
			connectionList1 = adult1.getProfile().getConnections();
			connectionList2 = adult2.getProfile().getConnections();
			System.out.println();
			if( connectionList1.size() == 0 && connectionList2.size() == 0 )
			{
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
					isConnectionCreated = true;
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
					partnerList.add(adult2);
					isConnectionCreated = true;
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
						isConnectionCreated = true;
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
						partnerList.add(adult2);
						isConnectionCreated = true;
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
						isConnectionCreated = true;
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
						isConnectionCreated = true;
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
						partnerList.add(adult2);
						isConnectionCreated = true;
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
						isConnectionCreated = true;
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
								isConnectionCreated = true;
								break;
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
								isConnectionCreated = true;
								break;
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
					//if they are already friends then partner is the only connection type possible
					//else either partner or friends
					for( int i = 0; i < connectionList1.size(); i ++ )
					{
						if( adult1.getProfile().getConnections().get(i).getAdult().getID().equalsIgnoreCase(adult2.getID()) )
						{
							System.out.println("Possible connection: ");
							System.out.println("1. Partner"); //partnerList
							System.out.println("0. Go back to previous menu");
							connectionChoice = getMenuChoiceInt("sub2");
							System.out.println("connectionChoice>> " + connectionChoice);
							if( connectionChoice == 1 )
							{
								//partners
								connection1 = new Connection("Partners", adult1);
								connection2 = new Connection("Partners", adult2);
								/*
								 * update the connection from Friends to Partners
								 */
								adult1.getProfile().getConnections().set(i, connection2);
								adult2.getProfile().getConnections().set(i, connection1);
								//adult1.getProfile().setConnection(connection2);
								//adult2.getProfile().setConnection(connection1);
								adult1.setPartner(true);
								adult2.setPartner(true);
								partnerList.add(adult1);
								partnerList.add(adult2);
								isConnectionCreated = true;
								break;
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
									isConnectionCreated = true;
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
									partnerList.add(adult2);
									isConnectionCreated = true;
									break;
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
			if( isConnectionCreated )
			{
				if( adult1.getProfile().getConnections().size() > 0 )
				{
					int i = adult1.getProfile().getConnections().size() - 1;
					//for( int i = 0; i < adult1.getProfile().getConnections().size(); i ++)
					{
						System.out.println();
						System.out.println("Connection was successfully created between persons with id: " + firstPersonId + " and " + secondPersonId);
						System.out.println("Connection details for person with id: " + firstPersonId);
						System.out.println("Connection person id: " + adult1.getProfile().getConnections().get(i).getAdult().getID());
						System.out.println("Connection profile name: " + adult1.getProfile().getConnections().get(i).getAdult().getProfile().getProfileName());
						System.out.println("Connection age: " + adult1.getProfile().getConnections().get(i).getAdult().getProfile().getAge());
						System.out.println("Connection person type: " +adult1.getProfile().getConnections().get(i).getAdult().getType());
						System.out.println("Connection type: " + adult1.getProfile().getConnections().get(i).getConnectionType());
						System.out.println("Total number of connection(s): " + adult1.getProfile().getConnections().size());
					}
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("[Driver]createConnectionBetweenAdults: " + e.getMessage());
		}
	}

	/*
	 * createConnectionBetweenAdultAndDependent
	 * parameter:
	 * person ids of first and second persons
	 */
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
					for(int i = 0; i < connectionList1.size(); i ++)
					{
						if( connectionList1.get(i).getConnectionType().equalsIgnoreCase("Partners") )
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
						System.out.println();
						System.out.println("Connection type Family is successfully created for dependent: " + depen1.getProfile().getProfileName());
						System.out.println("Parents names: ");
						System.out.println("Parent 1: " + adult1.getProfile().getProfileName());
						System.out.println("Parent 2: " + adult2.getProfile().getProfileName());
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
			System.out.println("[Driver]createConnectionBetweenAdultAndDependent: " + e.getMessage());
		}
	}
	
	/*
	 * createConnectionForIsolatedDependent
	 * parameter:
	 * person ids of first and second persons
	 */
	private boolean createConnectionForIsolatedDependent(String firstPersonId, String secondPersonId)
	{
		boolean isCreated = false;
		Adult adult1 = null, adult2 = null;
		Dependent depen1 = null;
		Connection connection1 = null, connection2 = null, connection3 = null;
		List<Connection> connectionList1 = null;
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
					for(int i = 0; i < connectionList1.size(); i ++)
					{
						if( connectionList1.get(i).getConnectionType().equalsIgnoreCase("Partners") )
						{
							adult2 = adult1.getProfile().getConnections().get(i).getAdult(); 
						}
					}
					
					System.out.println("adult1: " + adult1.getID());
					System.out.println("adult2: " + adult2.getID());
					System.out.println("depen: " + depen1.getID());
					
					System.out.println("Possible connection type: ");
					System.out.println("1. Family");
					System.out.println("0. Go back to previous menu");
					System.out.println("Please choose the type of connection [ 1 / 0 (Go back to previous menu)] ");
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
					isCreated = true;
					System.out.println();
					System.out.println("Connection type Family is successfully created for dependent: " + depen1.getProfile().getProfileName());
					System.out.println("Parents names: ");
					System.out.println("Parent 1: " + adult1.getProfile().getProfileName());
					System.out.println("Parent 2: " + adult2.getProfile().getProfileName());
				}
			}
			if( ! isCreated )
			{
				System.out.println("Selected adult has no partner, hence connnection can not be created.");
			}
		}
		catch(Exception e)
		{
			System.out.println("[Driver]createConnectionForIsolatedDependent: " + e.getMessage());
		}
		return isCreated;
	}
	
	/*
	 * createConnectionBetweenDependentAndAdult
	 * parameter:
	 * person ids of first and second persons
	 */
	private void createConnectionBetweenDependentAndAdult(String firstPersonId, String secondPersonId)
	{
		Adult adult1 = null, adult2 = null;
		Dependent depen1 = null;
		Connection connection1 = null, connection2 = null, connection3 = null;
		List<Connection> connectionList1 = null;
		int connectionChoice = 0;
		try
		{
			System.out.println("Creating connection between an Dependent and adult");
			depen1 = ctPpl.getDependentObjectById(firstPersonId);
			adult1 = ctPpl.getAdultObjectById(secondPersonId);

			if( adult1.getHasPartner() )
			{
				if( ! ( depen1.getHasParent() ) )
				{
					connectionList1 = adult1.getProfile().getConnections();
					for(int i = 0; i < connectionList1.size(); i ++)
					{
						if( connectionList1.get(i).getConnectionType().equalsIgnoreCase("Partners") )
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
						System.out.println();
						System.out.println("Connection type Family is successfully created for dependent: " + depen1.getProfile().getProfileName());
						System.out.println("Parents names: ");
						System.out.println("Parent 1: " + adult1.getProfile().getProfileName());
						System.out.println("Parent 2: " + adult2.getProfile().getProfileName());
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
			System.out.println("[Driver]createConnectionBetweenDependentAndAdult: " + e.getMessage());
		}
	}

	/*
	 * createConnectionBetweenDependents
	 * parameter:
	 * person ids of first and second persons
	 */
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
					if( connectionList1.get(i).getConnectionType().equalsIgnoreCase("Family") )
					{
						adult1 = connectionList1.get(i).getAdult();
						break;
					}
				}
				for( int i = 0; i < connectionList2.size(); i++ )
				{	if( connectionList1.get(i).getConnectionType().equalsIgnoreCase("Family") )
					{
						adult2 = connectionList2.get(i).getAdult();
						break;
					}
				}
				
				System.out.println("Parent 1 id: " + adult1.getID());
				System.out.println("Parent 2 id: " + adult2.getID());

				if( adult1.getID().equalsIgnoreCase(adult2.getID() ) )
				{
					System.out.println("The selected dependents are siblings.");
					/*connection1 = new Connection("Sibling", depen1);
					connection2 = new Connection("Sibling", depen2);
					depen1.getProfile().setConnection(connection2);
					depen2.getProfile().setConnection(connection1);*/
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
						if( ageDiff <= 3 )
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
								System.out.println();
								System.out.println("Connection type Friends between two dependents " + depen1.getProfile().getProfileName() + " and " + depen2.getProfile().getProfileName() + " is successfully created");
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
						System.out.println("Cannot create connection. Dependents are 2 years or younger.");
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
			System.out.println("[Driver]createConnectionBetweenDependents: " + e.getMessage());
		}
	}

	/*
	 * Menu option: 8
	 * To find out whether to perons who have profiles are direct "Friends" or not.
	 */
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
				//determine if two adults are direct friends
				if( type1.equalsIgnoreCase("adult") && type2.equalsIgnoreCase("adult") )
				{
					adult1 = ctPpl.getAdultObjectById(firstPersonId);
					adult2 = ctPpl.getAdultObjectById(secondPersonId);
					connectionList1 = adult1.getProfile().getConnections();
					connectionList2 = adult2.getProfile().getConnections();
	
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
								else if( connectionList1.get(i).getConnectionType().equalsIgnoreCase("Partners") )
								{
									isDirectFriends = true;
								}
							}
						}
					}
				}
				//determine if an adult and a dependent are direct friends
				else if( type1.equalsIgnoreCase("adult") && type2.equalsIgnoreCase("dependent") )
				{
					adult1 = ctPpl.getAdultObjectById(firstPersonId);
					depen1 = ctPpl.getDependentObjectById(secondPersonId);
					isDirectFriends = false;
				}
				//determine if an adult and a dependent are direct friends
				else if( type1.equalsIgnoreCase("dependent") && type2.equalsIgnoreCase("adult") )
				{
					depen1 = ctPpl.getDependentObjectById(firstPersonId);
					adult2 = ctPpl.getAdultObjectById(secondPersonId);
					isDirectFriends = false;
				}
				//determine if two dependents are direct friends
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
								if( connectionList1.get(i).getConnectionType().equalsIgnoreCase("Friends") )
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
			System.out.println("[Driver]connectionLookUpFriends: ");
		}
	}
	/*
	 * Menu option9: To fetech parents and children names
	 */
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
					if( adult1.getHasChild() ) //has child - children details
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
					if( depen1.getHasParent() ) //has parents - parents details
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
			System.out.println("[Driver]connectionLookUpFamily: " + e.getMessage());
		}
	}
	
	/*
	 * Generic method to handle menu options throughout the application
	 * 
	 */
	public int getMenuChoiceInt(String filter)
	{
		int choice = 0;
		try
		{
			scannerObj = new Scanner(System.in);
			if( filter.equalsIgnoreCase("main") )
			{
				choice = validateIntMenuOption(1, 10, "main");
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
			if( filter.equalsIgnoreCase("sub4") )
			{
				choice = validateIntMenuOption(0, 3, "sub4");
			}
		}
		catch(Exception e)
		{
			System.out.println("[Driver]validMenuChoiceInt: " + e.getMessage() );
		}
		return choice;
	}
	
	/*
	 * validate menu option of type integer. Only possible integer value is returned
	 */
	public int validateIntMenuOption(int min, int max, String filter)
	{
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
			System.out.println("[Driver]validateIntMenuOption " + e.getMessage());
		}
		return choice;
	}
}
