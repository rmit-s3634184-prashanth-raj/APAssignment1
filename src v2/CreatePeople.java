package utility;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import minNetPackage.Adult;
import minNetPackage.Dependent;
import minNetPackage.Person;
import minNetPackage.Profile;

public class CreatePeople 
{
	List<Adult> listOfAdult = new ArrayList<Adult>();
	List<Dependent> listOfDependent = new ArrayList<Dependent>();
	List<Profile> listOfProfile = new ArrayList<Profile>();
	Adult a1 = null;
	Dependent d1 = null;
	int id = 0;
	String name = "";
	int age = 0;

	int numberOfAdults = 10;
	int numberOfDependents = 10;

	Random ranObj = new Random();
	int minAgeAdult = 17;
	int maxAgeAdult = 82;

	int minAgeDependent = 1;
	int maxAgeDependent = 15;

	int personCounter = 1;

	public void createPeople()
	{
		try
		{
			for( int i = 0; i < numberOfAdults; i ++ )
			{
				age = genRandomAge(minAgeAdult, maxAgeAdult);
				a1 = new Adult("a" + ( i + 1 ), "Adult" + ( i + 1 ), age);
				//a1.setType(age);
				this.listOfAdult.add(this.a1);
			}
			for(int i = 0; i < numberOfDependents; i ++)
			{
				age = genRandomAge(minAgeDependent, maxAgeDependent);
				d1 = new Dependent("d" + ( i + 1 ), "Dependent" + ( i + 1 ), age);
				//d1.setType(age);
				this.listOfDependent.add(this.d1);
			}
		}
		catch(Exception e)
		{
			System.out.println("[CreatePeople]createPeople: " + e.getMessage());
		}
	}

	public List<Adult> getListOfAdult()
	{
		return this.listOfAdult;
	}

	public List<Dependent> getListOfDependent()
	{
		return this.listOfDependent;
	}

	public int getTotalNumberOfPeople()
	{
		int totalNumberOfPeople = this.listOfAdult.size() + this.listOfDependent.size();
		return totalNumberOfPeople;
	}

	public void displayAdultDetails()
	{
		try
		{
			personCounter = 1;
			for( int i = 0; i < this.listOfAdult.size(); i ++ )
			{
				if( ! this.listOfAdult.get(i).getHasProfile() )
				{
					System.out.println( personCounter + ". ID: " + this.listOfAdult.get(i).getID());
					System.out.println("Name: " + this.listOfAdult.get(i).getName());
					if( this.listOfAdult.get(i).getType() != null )
						System.out.println("Type: " + this.listOfAdult.get(i).getType());
					System.out.println();
					personCounter++;
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("[CreatePeople]displayAdultDetails: " + e.getMessage());
		}
	}
	public void displayDependentDetails()
	{
		try
		{
			for( int i = 0 ; i < this.listOfDependent.size(); i ++ )
			{
				if( ! this.listOfDependent.get(i).getHasProfile() )
				{
					System.out.println( personCounter + ". ID: " + this.listOfDependent.get(i).getID());
					System.out.println("Name: " + this.listOfDependent.get(i).getName());
					if( this.listOfDependent.get(i).getType() != null )
						System.out.println("Type: " + this.listOfDependent.get(i).getType());
					System.out.println();
					personCounter++;
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("[CreatePeople]displayAdultDetails: " + e.getMessage());
		}
	}

	public boolean validSearchByProfileName(String name)
	{
		boolean validStatus = false;
		try
		{
			if( listOfProfile.size() > 0 )
			{
				for( int i = 0; i < listOfProfile.size(); i++ )
				{
					if( listOfProfile.get(i).getProfileName().equalsIgnoreCase(name) )
						validStatus = true;
				}
			}
			else
			{
				System.out.println("There are no profiles in the network");
			}
			/*
			for( int i = 0; i < this.listOfAdult.size(); i ++ )
			{
				if( this.listOfAdult.get(i).getProfile().getProfileName() != null )
				{
					if( this.listOfAdult.get(i).getProfile().getProfileName().equalsIgnoreCase(name) )
					{
						validStatus = true;
					}
				}
			}
			for(int i = 0; i < this.listOfDependent.size(); i++ )
			{
				if( this.listOfDependent.get(i).getProfile().getProfileName() != null ) 
				{
					if( this.listOfDependent.get(i).getProfile().getProfileName().equalsIgnoreCase(name) )
					{
						validStatus = true;
					}
				}
			}
			*/
		}
		catch(Exception e)
		{
			System.out.println("[CreatePeople] validSearchByProfileName " + e.getMessage());
		}
		return validStatus;
	}