package minNetPackage;

/**
 * 
 * @author Prashanth, Sumit
 *
 */
public class Connection 
{
	private String connectionType;
	private Adult adultObj;
	private Dependent dependentObj;
	private int numberOfPartners = 0;
	
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
	
	public String getConnectionType()
	{
		return this.connectionType;
	}
}
