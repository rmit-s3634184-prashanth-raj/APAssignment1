//@author Sumit Sitlani

package minNetPackage;

import utility.CreatePeople;
import utility.Menu;

public class Main {

	public static void main(String[] args) 
	{
		Menu utilObj = new Menu();
		CreatePeople ctPpl = new CreatePeople();
		
		System.out.println("Welcome to miniNet");
		ctPpl.createPeople();
		utilObj.displayMenu(ctPpl);
	}
}
