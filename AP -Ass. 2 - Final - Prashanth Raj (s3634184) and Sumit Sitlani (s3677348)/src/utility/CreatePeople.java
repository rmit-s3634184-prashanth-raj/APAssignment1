package utility;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

import minNetModelPackage.Adult;
import minNetModelPackage.Connection;
import minNetModelPackage.Dependent;
import minNetModelPackage.Person;
import minNetModelPackage.Profile;

/**
 * 
 * @author Prashanth
 *
 */
public class CreatePeople 
{
	static List<Connection> connectionInputList = new ArrayList<Connection>();
	static List<Connection> connectionInputList2 = new ArrayList<Connection>();
	static List<Adult> listOfAdult = new ArrayList<Adult>();
	static List<Dependent> listOfDependent = new ArrayList<Dependent>();
	static List<Profile> listOfProfile = new ArrayList<Profile>();
	static Adult a1 = null;
	static Dependent d1 = null;
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

	static int personCounter = 1;

	public void createPeople()
	{
		try
		{
			for( int i = 0; i < numberOfAdults; i ++)
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

	public static void createPeople(String iD, String name, String image, String status, String gender, int age, String states )
	{
		try
		{
			if( age >=16 && age <= 99 )
			{
				a1 = new Adult(iD, name, image, status, gender, age, states);
				a1.setType("adult");
				listOfAdult.add(a1);
			}
			else if( age >=1 && age <= 15 )
			{
				d1 = new Dependent(iD, name, image, status, gender, age, states);
				d1.setType("dependent");
				listOfDependent.add(d1);
			}
			else
			{
				System.out.println("Age: " + age + " is invalid");
			}
		}
		catch(Exception e)
		{
			System.out.println("[CreatePeople]createPeople: " + e.getMessage());
		}
	}
	
	public static void createConnectionInputList(String name1, String name2, String connectionType)
	{
		Connection conObj = null;
		String type1 = null;
		String type2 = null;
		Adult adultObj1 = null;
		Adult adultObj2 = null;
		Dependent depenObj1 = null;
		Dependent depenObj2 = null;
		try
		{
			/*
			 * 1. get person type by name for both the names
			 * 2. create connection object accordingly using connection type
			 * 
			 */
			type1 = getTypeByPersonName(name1);
			type2 = getTypeByPersonName(name2);
			if( type1.equalsIgnoreCase("adult") && type2.equalsIgnoreCase("adult") )
			{
				adultObj1 = getAdultObjectByName(name1);
				adultObj2 = getAdultObjectByName(name2);
				conObj = new Connection(adultObj1, adultObj2, connectionType);
			}
			else if( type1.equalsIgnoreCase("dependent") && type2.equalsIgnoreCase("dependent") )
			{
				depenObj1 = getDependentObjectByName(name1);
				depenObj2 = getDependentObjectByName(name2);
				conObj = new Connection(depenObj1, depenObj2, connectionType);
			}
			else if( type1.equalsIgnoreCase("adult") && type2.equalsIgnoreCase("dependent") )
			{
				adultObj1 = getAdultObjectByName(name1);
				depenObj1 = getDependentObjectByName(name2);
				conObj = new Connection(adultObj1, depenObj1, connectionType);
			}
			else if(  type1.equalsIgnoreCase("dependent") && type2.equalsIgnoreCase("adult") )
			{
				depenObj1 = getDependentObjectByName(name1);
				adultObj1 = getAdultObjectByName(name2);
				conObj = new Connection(adultObj1, depenObj1, connectionType);
			}
				
			connectionInputList.add(conObj);
		}
		catch(Exception e)
		{
			System.out.println("[CreatePeople]createConnectionInputList: " + e.getMessage());
		}
	}
	
	public static void createConnectionInputList2(String name1, String name2, String connectionType)
	{
		Connection conObj = null;
		String personOneId = "";
		String personTwoId = "";
		try
		{
			personOneId = CreatePeople.getPersonIdByPersonName(name1);
			personTwoId = CreatePeople.getPersonIdByPersonName(name2);
			conObj = new Connection(personOneId, personTwoId, connectionType);
			connectionInputList2.add(conObj);
		}
		catch(Exception e)
		{
			System.out.println("[CreatePeople]createConnectionInputList: " + e.getMessage());
		}
	}
	
	public static List<Connection> getConnectionInputList()
	{
		
		return connectionInputList;
	}
	
	public static List<Connection> getConnectionInputList2()
	{
		
		return connectionInputList2;
	}
	
	public static String getTypeByPersonName(String name)
	{
		String type = null;
		try
		{
			for(int i = 0; i < listOfAdult.size(); i ++ )
			{
				if( listOfAdult.get(i).getName().equalsIgnoreCase(name) )
				{
					type = listOfAdult.get(i).getType();
					break;
				}
			}
			for( int i = 0; i < listOfDependent.size(); i ++ )
			{
				if( listOfDependent.get(i).getName().equalsIgnoreCase(name) )
				{
					type = listOfDependent.get(i).getType();
					break;
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("[CreatePeople]getTypeByPersonName: " + e.getMessage());
		}
		return type;
	}
	
	public static List<Adult> getListOfAdult()
	{
		return listOfAdult;
	}

	public static List<Dependent> getListOfDependent()
	{
		return listOfDependent;
	}

	public int getTotalNumberOfPeople()
	{
		int totalNumberOfPeople = this.listOfAdult.size() + this.listOfDependent.size();
		return totalNumberOfPeople;
	}

	public static void displayAdultDetails()
	{
		try
		{
			personCounter = 1;
			for( int i = 0; i < listOfAdult.size(); i ++ )
			{
				if( ! listOfAdult.get(i).getHasProfile() )
				{
					System.out.println( personCounter + ". Person ID: " + listOfAdult.get(i).getID());
					System.out.println("Person Name: " + listOfAdult.get(i).getName());
					if( listOfAdult.get(i).getType() != null )
						System.out.println("Type: " + listOfAdult.get(i).getType());
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
	public static void displayDependentDetails()
	{
		try
		{
			for( int i = 0 ; i < listOfDependent.size(); i ++ )
			{
				if( ! listOfDependent.get(i).getHasProfile() )
				{
					System.out.println( personCounter + ". Person ID: " + listOfDependent.get(i).getID());
					System.out.println("Person Name: " + listOfDependent.get(i).getName());
					if( listOfDependent.get(i).getType() != null )
						System.out.println("Type: " + listOfDependent.get(i).getType());
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
		}
		catch(Exception e)
		{
			System.out.println("[CreatePeople] validSearchByProfileName " + e.getMessage());
		}
		return validStatus;
	}

	public static Adult getAdultObjectById(String iD)
	{
		//System.out.println("id: " + iD);
		Adult a1 = null;
		try
		{
			for( int i = 0; i < listOfAdult.size(); i ++)
			{
				if( listOfAdult.get(i).getID().equalsIgnoreCase(iD) )
				{
					a1 = new Adult();
					a1 = listOfAdult.get(i);
					break;
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("[CreatePeople]getAdultObjectById: " + e.getMessage());
		}
		return a1;
	}

	public static Dependent getDependentObjectById(String iD)
	{
		//System.out.println("id: " + iD);
		Dependent d1 = null;
		try
		{
			for( int i = 0; i < listOfDependent.size(); i ++)
			{
				if( listOfDependent.get(i).getID().equalsIgnoreCase(iD) )
				{
					d1 = new Dependent();
					d1 = listOfDependent.get(i);
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("[CreatePeople]getDependentObjectById: " + e.getMessage());
		}
		return d1;
	}

	public static String getPersonTypeById(String id)
	{
		String type = "";
		try
		{
			for( int i = 0; i < listOfAdult.size(); i ++)
			{
				if( listOfAdult.get(i).getID().equalsIgnoreCase(id) )
				{
					type = listOfAdult.get(i).getType();
				}
			}
			for( int i = 0; i  < listOfDependent.size(); i ++ )
			{
				if( listOfDependent.get(i).getID().equalsIgnoreCase(id) )
				{
					type = listOfDependent.get(i).getType();
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("[CreatePeople]getAdultObjectById: " + e.getMessage());
		}
		return type;
	}
	
	public static String getPersonIdByPersonName(String name)
	{
		String personId = null;
		try
		{
			for( int i = 0; i < listOfAdult.size(); i ++)
			{
				if( listOfAdult.get(i).getName().equalsIgnoreCase(name) )
				{
					personId = listOfAdult.get(i).getID();
				}
			}
			for( int i = 0; i  < listOfDependent.size(); i ++ )
			{
				if( listOfDependent.get(i).getName().equalsIgnoreCase(name) )
				{
					personId = listOfDependent.get(i).getID();
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("[CreatePeople]getPersonIdByProflieName: " + e.getMessage());
		}
		return personId;
	}
	
	//change: display the person detail and then create a new function for getting the profile a person is associated with by name!
	public void getPersonDetailsByName(String name)
	{
		boolean personFound = false;
		try
		{
			for( int i = 0; i < this.listOfAdult.size(); i ++)
			{
				if( this.listOfAdult.get(i).getName().equalsIgnoreCase(name) )
				{
					personFound = true;
					System.out.println("Person by name:	" + name + " was found");
					System.out.println("Person ID: " + this.listOfAdult.get(i).getID());
					System.out.println("Person Name: " + this.listOfAdult.get(i).getName());
					if( this.listOfAdult.get(i).getType() != null )
						System.out.println("Type: " + this.listOfAdult.get(i).getType());
					if( this.listOfAdult.get(i).getHasProfile() )
					{
						System.out.println("Profile name: " + this.listOfAdult.get(i).getProfile().getProfileName());
						System.out.println("Age: " + this.listOfAdult.get(i).getProfile().getAge());
						System.out.println("Image Path: " + this.listOfAdult.get(i).getProfile().getProfileImagePath());
						System.out.println("Status: " + this.listOfAdult.get(i).getProfile().getProfileStatus() );
					}
					else
					{
						System.out.println("This person has no profile in the network");
					}
					break;
				}
			}
			for( int i = 0; i < this.listOfDependent.size(); i ++)
			{
				if( this.listOfDependent.get(i).getName().equalsIgnoreCase(name) )
				{
					personFound = true;
					System.out.println("Person by name: " + name + " was found");
					System.out.println("ID: " + this.listOfDependent.get(i).getID());
					System.out.println("Name: " + this.listOfDependent.get(i).getName());
					if( this.listOfDependent.get(i).getType() != null )
						System.out.println("Type: " + this.listOfDependent.get(i).getType());
					if( this.listOfDependent.get(i).getHasProfile() )
					{
						System.out.println("Profile name: " + this.listOfDependent.get(i).getProfile().getProfileName());
						System.out.println("Age: " + this.listOfDependent.get(i).getProfile().getAge());
						System.out.println("Image Path: " + this.listOfDependent.get(i).getProfile().getProfileImagePath());
						System.out.println("Status: " + this.listOfDependent.get(i).getProfile().getProfileStatus());
					}
					else
					{
						System.out.println("This person has no profile in the network");
					}
					break;
				}
			}
			if( ! personFound )
			{
				System.out.println("Person by name: " + name + " was not found");
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

	public static Profile getProfileObjectByProflieName(String name)
	{
		Profile p1 = null;
		try
		{
			for( int i = 0; i < CreatePeople.listOfProfile.size(); i ++)
			{
				if( CreatePeople.listOfProfile.get(i).getProfileName().equalsIgnoreCase(name) )
				{
					p1 = CreatePeople.listOfProfile.get(i);
					break;
				}
				else
				{
					if( i == ( CreatePeople.listOfProfile.size() - 1 ) )
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
	
	public static String getPersonIdByProflieName(String name)
	{
		String personId = null;
		try
		{
			for( int i = 0; i < CreatePeople.listOfProfile.size(); i ++)
			{
				if( CreatePeople.listOfProfile.get(i).getProfileName().equalsIgnoreCase(name) )
				{
					personId = CreatePeople.listOfProfile.get(i).getPersonId();
					break;
				}
				else
				{
					if( i == ( CreatePeople.listOfProfile.size() - 1 ) )
					{
						System.out.println("Not found");
					}
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("[CreatePeople]getPersonIdByProflieName: " + e.getMessage());
		}
		return personId;
	}
		
	public static Adult getAdultObjectByName(String name)
	{
		Adult a1 = null;
		try
		{
			for( int i = 0; i < listOfAdult.size(); i ++)
			{
				if( listOfAdult.get(i).getName().equalsIgnoreCase(name) )
				{
					a1 = new Adult();
					a1 = listOfAdult.get(i);
					break;
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("[CreatePeople]getAdultObjectByName: " + e.getMessage());
		}
		return a1;
	}
	
	public static Dependent getDependentObjectByName(String name)
	{
		Dependent d1 = null;
		try
		{
			for( int i = 0; i < listOfDependent.size(); i ++)
			{
				if( listOfDependent.get(i).getName().equalsIgnoreCase(name) )
				{
					d1 = new Dependent();
					d1 = listOfDependent.get(i);
					break;
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("[CreatePeople]getDependentObjectByName: " + e.getMessage());
		}
		return d1;
	}

	public static void addProfileToList(Profile profile1)
	{
		try
		{
			listOfProfile.add(profile1);
		}
		catch(Exception e)
		{
			System.out.println("[CreatePeople]addProfileToList " + e.getMessage());
		}
	}

	public static List<Profile> getListOfProfile()
	{
		return listOfProfile;
	}

	public static void deletePersonById(String id)
	{
		boolean isRemoved = false;
		//get person type by id/name
		//based on the type select either adult or dependent list
		try
		{
			for( int i = 0; i < listOfAdult.size(); i ++)
			{
				if( listOfAdult.get(i).getID().equalsIgnoreCase(id) )
				{
					listOfAdult.remove(i);
					isRemoved = true;
				}
			}
			if( isRemoved )
			{
				System.out.println("Person with ID: " + id + " is removed");
			}
			else
			{
				for( int i = 0; i < listOfDependent.size(); i ++)
				{
					if( listOfDependent.get(i).getID().equalsIgnoreCase(id) )
					{
						listOfDependent.remove(i);
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

	public static void deleteProfileByName(String name)
	{
		try
		{
			for( int i = 0; i < listOfProfile.size(); i ++)
			{
				if( listOfProfile.get(i).getProfileName().equalsIgnoreCase(name) )
				{
					listOfProfile.remove(i);
					System.out.println("Profile by name: " + name + " is removed");
				}
				else
				{
					if( i == ( listOfProfile.size() - 1 ) )
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

	public static void displayProfiles()
	{
		try
		{
			if( listOfProfile.size() > 0 )
			{
				System.out.println("Profile details: ");
				for( int i = 0; i < listOfProfile.size(); i ++ )
				{
					System.out.println("Profile Name: " + listOfProfile.get(i).getProfileName());
					System.out.println("Profile Age: " + listOfProfile.get(i).getAge());
					System.out.println("Profile Image path: " + listOfProfile.get(i).getProfileImagePath());
					System.out.println("Profile Status: " + listOfProfile.get(i).getProfileStatus());
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
	
	public static void displayAllProfileDetails()
	{
		try
		{
			for( int i = 0; i < CreatePeople.listOfAdult.size(); i ++  )
			{
				if( CreatePeople.listOfAdult.get(i).getHasProfile() )
				{
					System.out.println("Profile name: " + CreatePeople.listOfAdult.get(i).getProfile().getProfileName());
					System.out.println("Age: " + CreatePeople.listOfAdult.get(i).getProfile().getAge());
					System.out.println("Profile image path: " + CreatePeople.listOfAdult.get(i).getProfile().getProfileImagePath());
					System.out.println("Profile status: " + CreatePeople.listOfAdult.get(i).getProfile().getProfileStatus());
					if( CreatePeople.listOfAdult.get(i).getProfile().getConnections().size() > 0 )
					{
						System.out.println("Number of connection: " + CreatePeople.listOfAdult.get(i).getProfile().getConnections().size());
						for(int j = 0; j < CreatePeople.listOfAdult.get(i).getProfile().getConnections().size() ; j ++)
						{
							if( CreatePeople.listOfAdult.get(i).getProfile().getConnections().get(j).getConnectionType().equalsIgnoreCase("Friends") 
								|| CreatePeople.listOfAdult.get(i).getProfile().getConnections().get(j).getConnectionType().equalsIgnoreCase("Partners")) 
							{
								if( CreatePeople.listOfAdult.get(i).getProfile().getConnections().get(j).getAdult().getHasProfile() )
									System.out.println( (j + 1) + ") Connection name: " + CreatePeople.listOfAdult.get(i).getProfile().getConnections().get(j).getAdult().getProfile().getProfileName());
							}
							else if( CreatePeople.listOfAdult.get(i).getProfile().getConnections().get(j).getConnectionType().equalsIgnoreCase("Family") )
							{
								if( CreatePeople.listOfAdult.get(i).getProfile().getConnections().get(j).getDependent().getHasProfile() )
									System.out.println( (j + 1) + ") Connection name: " + CreatePeople.listOfAdult.get(i).getProfile().getConnections().get(j).getDependent().getProfile().getProfileName());
							}
						}
						System.out.println();
					}
					else
					{
						System.out.println("Number of connection: " + CreatePeople.listOfAdult.get(i).getProfile().getConnections().size());
					}
				}
			}
			for( int i = 0; i < CreatePeople.listOfDependent.size(); i ++  )
			{
				if( CreatePeople.listOfDependent.get(i).getHasProfile() )
				{
					System.out.println("Profile name: " + CreatePeople.listOfDependent.get(i).getProfile().getProfileName());
					System.out.println("Age: " + CreatePeople.listOfDependent.get(i).getProfile().getAge());
					System.out.println("Profile image path: " + CreatePeople.listOfDependent.get(i).getProfile().getProfileImagePath());
					System.out.println("Profile status: " + CreatePeople.listOfDependent.get(i).getProfile().getProfileStatus());
					if( CreatePeople.listOfDependent.get(i).getProfile().getConnections().size() > 0 )
					{
						System.out.println("Number of connection: " + CreatePeople.listOfDependent.get(i).getProfile().getConnections().size());
						for(int j = 0; j < CreatePeople.listOfDependent.get(i).getProfile().getConnections().size() ; j ++)
						{
							if( CreatePeople.listOfDependent.get(i).getProfile().getConnections().get(j).getConnectionType().equalsIgnoreCase("Friends") ) 
							{
								if( CreatePeople.listOfDependent.get(i).getProfile().getConnections().get(j).getDependent().getHasProfile() )
									System.out.println( (j + 1) + ") Connection name: " + CreatePeople.listOfDependent.get(i).getProfile().getConnections().get(j).getDependent().getProfile().getProfileName());
							}
							else if( CreatePeople.listOfDependent.get(i).getProfile().getConnections().get(j).getConnectionType().equalsIgnoreCase("Family") )
							{
								if( CreatePeople.listOfDependent.get(i).getProfile().getConnections().get(j).getAdult().getHasProfile() )
									System.out.println( (j + 1) + ") Connection name: " + CreatePeople.listOfDependent.get(i).getProfile().getConnections().get(j).getAdult().getProfile().getProfileName());
							}
						}
						System.out.println();
					}
					else
					{
						System.out.println("Number of connection: " + CreatePeople.listOfDependent.get(i).getProfile().getConnections().size());
					}
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("[Menu]displayAllProfileDetails: " + e.getMessage());
		}
	}
}
