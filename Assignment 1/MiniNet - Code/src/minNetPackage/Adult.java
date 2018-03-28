package minNetPackage;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sumit
 *
 */
public class Adult extends Person 
{
	private List<Adult>listOfPeople = new ArrayList<Adult>();
	private boolean hasPartner = false;
	private boolean hasChild = false;
	
	public Adult(String id, String name, int age)
	{
		super(id, name, age);
	}
	
	public Adult()
	{
		super();
	}
	
	//private List<Connection> c1 = new ArrayList<Connection>();
	
	public List<Adult> getListOfPeople()
	{
		return this.listOfPeople;
	}
	
	public void setPartner(boolean partner)
	{
		this.hasPartner = partner;
	}

	public boolean getHasPartner()
	{
		return this.hasPartner;
	}
	
	public void setHasChild(boolean child)
	{
		this.hasChild = child;
	}
	
	public boolean getHasChild()
	{
		return this.hasChild;
	}
}
