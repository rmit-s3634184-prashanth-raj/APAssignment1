package minNetPackage;

import java.io.FileNotFoundException;

import iOPackage.TextFile;
import utility.CreatePeople;
import utility.Driver;
/**
 * 
 * @author Sumit
 * This the main class.
 * Two methods are called.
 * createPeople: To create two type of people, adult and dependent, who could potentially be added into the network.
 * displayMenu: To display all the possible operation that can be performed.
 *
 */
public class MiniNet {

	public static void main() throws FileNotFoundException 
	{
		Driver utilObj = new Driver();
		CreatePeople ctPpl = new CreatePeople();
		TextFile textFileObj = new TextFile();
		
		System.out.println("Welcome to miniNet");
		textFileObj.readPeopleTextFile();
		textFileObj.readRelationsTextFile();
		//ctPpl.createPeople(); //create set of people in order to add them into network.
		utilObj.displayMenu(ctPpl); // display list of menu options
	}
}
