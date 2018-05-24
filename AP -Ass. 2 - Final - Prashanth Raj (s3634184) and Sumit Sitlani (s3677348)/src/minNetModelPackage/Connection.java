package minNetModelPackage;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Prashanth, Sumit
 *
 */
public class Connection 
{
	private String connectionType;
	private Adult adultObj;
	private Adult adultObj1;
	private Adult adultObj2;
	private Dependent dependentObj;
	private Dependent dependentObj1;
	private Dependent dependentObj2;
	private int numberOfPartners = 0;
	
	private String personIdOne;
	private String personIdTwo;
	
	public Connection()
	{
		
	}
	public Connection( String type, Adult a1 )
	{
		this.connectionType = type;
		this.adultObj = a1;
	}
	
	public Connection( String type, Dependent d1)
	{
		this.connectionType = type;
		this.dependentObj = d1;
	}
	
	public Connection(String personId1, String personid2, String connType )
	{
		this.personIdOne = personId1;
		this.personIdTwo = personid2;
		this.connectionType = connType;
	}
	
	public Connection(Adult adult1, Adult adult2, String connectionType)
	{
		this.adultObj1 = adult1;
		this.adultObj2 = adult2;
		this.connectionType = connectionType;
	}
	
	public Connection(Adult adult1, Dependent dependent1, String connectionType)
	{
		this.adultObj1 = adult1;
		this.dependentObj1 = dependent1;
		this.connectionType = connectionType;
	}
	
	public Connection(Dependent dependent1, Dependent dependent2, String connectionType)
	{
		this.dependentObj1 = dependent1;
		this.dependentObj2 = dependent2;
		this.connectionType = connectionType;
	}
	
	public Adult getAdult()
	{
		return this.adultObj;
	}
	
	public Dependent getDependent()
	{
		return this.dependentObj;
	}
	
	public void setNumberOfPartners()
	{
		System.out.println("numberOfPartners: " + numberOfPartners);
		System.out.println("this.connectionType.equalsIgnoreCase(Partners): " + this.connectionType.equalsIgnoreCase("Partners"));
		if( this.connectionType.equalsIgnoreCase("Partners") )
		{
			numberOfPartners++;
		}
		System.out.println("no of partners: " + numberOfPartners);
	}
	
	public int getNumberOfPartners()
	{
		return this.numberOfPartners;
	}
	
	public Adult getAdult1()
	{
		return this.adultObj1;
	}
	
	public Adult getAdult2()
	{
		return this.adultObj2;
	}
	
	public Dependent getDependent1()
	{
		return this.dependentObj1;
	}
	
	public Dependent getDependent2()
	{
		return this.dependentObj2;
	}
	
	public String getConnectionType()
	{
		return this.connectionType;
	}

	public String getpersonIdOne()
	{
		return this.personIdOne;
	}
	
	public String getpersonIdTwo()
	{
		return this.personIdTwo;
	}
}
