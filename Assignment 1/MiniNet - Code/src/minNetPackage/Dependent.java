package minNetPackage;

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
