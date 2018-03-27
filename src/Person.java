//@author Sumit Sitlani

package minNetPackage;

import java.util.ArrayList;
import java.util.List;

public abstract class Person 
{
	private String iD;
	private String name;
	private String type;
	private List<Person> listOfPeople = new ArrayList<Person>();
	private boolean hasProfile = false;
	//private Profile p1 = new Profile();
	private Profile p1 = null;

	public Person()
	{

	}

	public Person(String iD, String name, int age)
	{
		this.iD = iD;
		this.name = name;
	}

	public Person(Profile p1)
	{
		this.p1 = p1;
	}

	public String getID()
	{
		return this.iD;
	}

	public String getName()
	{
		return this.name;
	}

	public String getType()
	{
		return this.type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public void setProfile(Profile p1)
	{
		this.p1 = p1;
		this.hasProfile = true;
	}

	public Profile getProfile()
	{
		return this.p1;
	}

	public boolean getHasProfile()
	{
		return this.hasProfile;
	}
	public Profile getProfileByPersonName(String name)
	{
		Profile profile1 = null;
		if( this.name.equalsIgnoreCase(name)) 
		{
			profile1 = this.p1;
		}
		return profile1;
	}

	

	//public abstract void setType(int age);

	public void displayPersonDetails()
	{
		System.out.println("ID: " + this.iD);
		System.out.println("Name: " + this.name);
		if( this.p1 != null )
			System.out.println("Age: " + this.p1.getAge());
		if( this.type != null )
			System.out.println("Type: " + this.type);
		System.out.println();
	}
}
