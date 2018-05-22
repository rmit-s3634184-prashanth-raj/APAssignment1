package minNetModelPackage;

/**
 * @author Sumit
 *
 */
public class Dependent extends Person
{
	private boolean hasParent = false;
	public Dependent(String id, String name, int age)
	{
		super(id, name, age);
	}
	
	public Dependent(String iD, String name, String image, String status, String gender, int age, String states)
	{
		super(iD, name, image, status, gender, age, states);
	}
	
	public Dependent()
	{
		
	}
	
	public void setHasParent(boolean parent)
	{
		this.hasParent = parent;
	}
	
	public boolean getHasParent()
	{
		return this.hasParent;
	}
	//private List<Connection> c1; 
}
