//@author Prashanth Raj & Sumit Sitlani

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