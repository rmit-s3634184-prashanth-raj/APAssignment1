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
	
	public Adult getAdultObjectById(String iD)
	{
		//System.out.println("id: " + iD);
		Adult a1 = null;
		try
		{
			for( int i = 0; i < this.listOfAdult.size(); i ++)
			{
				if( this.listOfAdult.get(i).getID().equalsIgnoreCase(iD) )
				{
					a1 = new Adult();
					a1 = this.listOfAdult.get(i);
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("[CreatePeople]getAdultObjectById: " + e.getMessage());
		}
		return a1;
	}

	public Dependent getDependentObjectById(String iD)
	{
		//System.out.println("id: " + iD);
		Dependent d1 = null;
		try
		{
			for( int i = 0; i < this.listOfDependent.size(); i ++)
			{
				if( this.listOfDependent.get(i).getID().equalsIgnoreCase(iD) )
				{
					d1 = new Dependent();
					d1 = this.listOfDependent.get(i);
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("[CreatePeople]getDependentObjectById: " + e.getMessage());
		}
		return d1;
	}

	public String getPersonTypeById(String id)
	{
		String type = "";
		try
		{
			for( int i = 0; i < this.listOfAdult.size(); i ++)
			{
				if( this.listOfAdult.get(i).getID().equalsIgnoreCase(id) )
				{
					type = this.listOfAdult.get(i).getType();
				}
			}
			for( int i = 0; i  < this.listOfDependent.size(); i ++ )
			{
				if( this.listOfDependent.get(i).getID().equalsIgnoreCase(id) )
				{
					type = this.listOfDependent.get(i).getType();
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("[CreatePeople]getAdultObjectById: " + e.getMessage());
		}
		return type;
	}
	
	//change: display the person detail and then create a new function for getting the profile a person is associated with by name!
	public void getPersonDetailsByName(String name)
	{
		try
		{
			for( int i = 0; i < this.listOfAdult.size(); i ++)
			{
				if( this.listOfAdult.get(i).getName().equalsIgnoreCase(name) )
				{
					System.out.println("Person by name: " + name + " was found");
					System.out.println("ID: " + this.listOfAdult.get(i).getID());
					System.out.println("Name: " + this.listOfAdult.get(i).getName());
					if( this.listOfAdult.get(i).getType() != null )
						System.out.println("Type: " + this.listOfAdult.get(i).getType());
					break;
				}
				else
				{
					if( i == ( this.listOfAdult.size() - 1 ) )
					{
						System.out.println("Not found");
					}
				}
			}
			for( int i = 0; i < this.listOfDependent.size(); i ++)
			{
				if( this.listOfDependent.get(i).getName().equalsIgnoreCase(name) )
				{
					System.out.println("Person by name: " + name + " was found");
					System.out.println("ID: " + this.listOfDependent.get(i).getID());
					System.out.println("Name: " + this.listOfDependent.get(i).getName());
					if( this.listOfDependent.get(i).getType() != null )
						System.out.println("Type: " + this.listOfDependent.get(i).getType());
					break;
				}
				else
				{
					if( i == ( this.listOfDependent.size() - 1 ) )
					{
						System.out.println("Not found");
					}
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("[Adult]getPersonDetailsByName: " + e.getMessage());
		}
	}

	//change: display the person detail and then create a new function for getting the profile a person is associated with by name!
	public Profile getPersonProfileByPersonName(String name)
	{
		Profile p1 = null;
		try
		{
			for( int i = 0; i < this.listOfAdult.size(); i ++)
			{
				if( this.listOfAdult.get(i).getName().equalsIgnoreCase(name) )
				{
					p1 = this.listOfAdult.get(i).getProfile();
					break;
				}
				else
				{
					if( i == ( this.listOfAdult.size() - 1 ) )
					{
						System.out.println("Not found");
					}
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("[Adult]getPersonDetailsByName: " + e.getMessage());
		}
		return p1;
	}

	public Profile getProfileObjectByProflieName(String name)
	{
		Profile p1 = null;
		try
		{
			for( int i = 0; i < this.listOfProfile.size(); i ++)
			{
				if( this.listOfProfile.get(i).getProfileName().equalsIgnoreCase(name) )
				{
					p1 = this.listOfProfile.get(i);
					break;
				}
				else
				{
					if( i == ( this.listOfProfile.size() - 1 ) )
					{
						System.out.println("Not found");
					}
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("[CreatePeople]getProfileObjectByName: " + e.getMessage());
		}
		return p1;
	}

	public Adult getAdultObjectByName(String name)
	{
		Adult a1 = null;
		try
		{
			for( int i = 0; i < this.listOfAdult.size(); i ++)
			{
				if( this.listOfAdult.get(i).getName().equalsIgnoreCase(name) )
				{
					a1 = new Adult();
					a1 = this.listOfAdult.get(i);
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("[CreatePeople]getAdultObjectByName: " + e.getMessage());
		}
		return a1;
	}

	public void addProfileToList(Profile profile1)
	{
		try
		{
			this.listOfProfile.add(profile1);
		}
		catch(Exception e)
		{
			System.out.println("[CreatePeople]addProfileToList " + e.getMessage());
		}
	}

	public List<Profile> getListOfProfile()
	{
		return this.listOfProfile;
	}

	public void deletePersonById(String id)
	{
		boolean isRemoved = false;
		//get person type by id/name
		//based on the type select either adult or dependent list
		try
		{
			for( int i = 0; i < this.listOfAdult.size(); i ++)
			{
				if( this.listOfAdult.get(i).getID().equalsIgnoreCase(id) )
				{
					this.listOfAdult.remove(i);
					isRemoved = true;
				}
			}
			if( isRemoved )
			{
				System.out.println("Person with ID: " + id + " is removed");
			}
			else
			{
				for( int i = 0; i < this.listOfDependent.size(); i ++)
				{
					if( this.listOfDependent.get(i).getID().equalsIgnoreCase(id) )
					{
						this.listOfDependent.remove(i);
						isRemoved = true;
					}
				}
				if( isRemoved )
				{
					System.out.println("Person with ID: " + id + " is removed");
				}
				else
				{
					System.out.println("Person with ID: " + id + " not found");
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("[CreatePeople]deletePersonById " + e.getMessage());
		}
	}

	public void deleteProfileByName(String name)
	{
		try
		{
			for( int i = 0; i < this.listOfProfile.size(); i ++)
			{
				if( this.listOfProfile.get(i).getProfileName().equalsIgnoreCase(name) )
				{
					this.listOfProfile.remove(i);
					System.out.println("Profile by name: " + name + " is removed");
				}
				else
				{
					if( i == ( this.listOfProfile.size() - 1 ) )
					{
						System.out.println("Profile by name: " + name + " not found");
					}
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("[CreatePeople]deleteProfileByName: " + e.getMessage());
		}
	}

	public void displayProfiles()
	{
		try
		{
			if( this.listOfProfile.size() > 0 )
			{
				System.out.println("Profile details: ");
				for( int i = 0; i < this.listOfProfile.size(); i ++ )
				{
					System.out.println("Profile Name: " + this.listOfProfile.get(i).getProfileName());
					System.out.println("Profile Age: " + this.listOfProfile.get(i).getAge());
					System.out.println("Profile Image path: " + this.listOfProfile.get(i).getProfileImagePath());
					System.out.println("Profile Status: " + this.listOfProfile.get(i).getProfileStatus());
					System.out.println();
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("[CreatePeople]displayProfiles " + e.getMessage());
		}
	}
	private int genRandomAge(int min, int max)
	{
		int randomInt = 0;
		try
		{
			randomInt = ranObj.nextInt(max) + min;
		}
		catch(Exception e)
		{
			System.out.println("[CreatePeople]genRandomAge: " + e.getMessage());
		}
		return randomInt;
	}
}
