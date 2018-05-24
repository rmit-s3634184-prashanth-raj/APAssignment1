package minNetModelPackage;

import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @author Prashanth
 *
 */
public class Profile 
{
	private String profileName;
	private int age;
	private String profileImagePath;
	private String profileStatus;
	private int numberOfFriends; //no of connections [Connection object]
	private String personId;
	private List<Connection> listOfConnections = new ArrayList<Connection>();

	public Profile()
	{

	}
	public Profile(String name, int age, String image, String status, String personId)
	{
		this.profileName = name;
		this.age = age;
		this.profileImagePath = image;
		this.profileStatus = status;
		this.personId = personId;
	}

	public void setProfileName(String name)
	{
		this.profileName = name;
	}
	
	public void setImagePath(String path)
	{
		this.profileImagePath = path;
	}
	
	public void setAge(int age)
	{
		this.age = age;
	}
	
	public void setStatus(String status)
	{
		this.profileStatus = status;
	}

	public String getProfileName()
	{
		return this.profileName;
	}
	
	public int getAge()
	{
		return this.age;
	}

	public String getProfileImagePath()
	{
		return this.profileImagePath;
	}

	public String getProfileStatus()
	{
		return this.profileStatus;
	}

	public int numberOfFriends()
	{
		return this.numberOfFriends;
	}
	
	public String getPersonId()
	{
		return this.personId;
	}
	
	public void setConnection(Connection c1)
	{
		try
		{
			this.listOfConnections.add(c1);
		}
		catch(Exception e)
		{
			System.out.println("[Profile]setConnectionn: " + e.getMessage());
		}
	}
	
	public List<Connection> getConnections()
	{
		return this.listOfConnections;
	}
	
	public void setNumberOfFriends()
	{
		this.numberOfFriends = this.listOfConnections.size();
	}
	
	public int getNumberofFriends()
	{
		return this.numberOfFriends;
	}
	
	public void displayProfileDetails()
	{
		try
		{
			System.out.println("Profile name: " + this.profileName);
			System.out.println("Profile image path: " + this.profileImagePath);
			System.out.println("Profile status: " + this.profileStatus);
			System.out.println("Profile number of friends: " + this.numberOfFriends);
			System.out.println();
		}
		catch(Exception e)
		{
			System.out.println("[Profile]displayProfileDetails: " + e.getMessage() );
		}
	}	
}
