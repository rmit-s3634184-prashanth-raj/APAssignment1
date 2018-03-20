//Authors: Sumit Sitlani & Prashanth Raj

import java.util.Scanner;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int ch;
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("=========Enter a choice =======");
			System.out.println("Press 1 to list everyone ");
			System.out.println("Press 2  to select a friend ");
			System.out.println("Press 3 to check if they are direct friends ");
			System.out.println("Press 4 to list all the parents");
			System.out.println("Press 5 to list all dependents ");
			System.out.println("Press 6 to list all dependents who are friends ");
			System.out.println("Press 7 to exit");
			ch = sc.nextInt();
			switch(ch)
			{
				case 1: 
				break;
				case 2: 
				break;
				case 3: 
				break;
				case 4: 
				break;
				case 5: 
				break;
				case 6: 
				break;
				case 7:
			    break;
				default: System.out.println("Invaild Choice");
		
			}
			}while(ch!=7);
		System.out.println("Exited From System");
		}
	
}