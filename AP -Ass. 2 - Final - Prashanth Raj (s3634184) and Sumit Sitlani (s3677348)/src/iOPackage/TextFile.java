package iOPackage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import minNetModelPackage.Adult;
import minNetModelPackage.Person;
import utility.CreatePeople;

/*
 * This class is used to handle data from external source: Text File
 * 1. people.txt
 * 2. realtions.txt
 */
public class TextFile 
{
	public static String peopleFileNotFound = null;
	public static String relationFileNotFound = null;
	
	public void readPeopleTextFile() 
	{
		BufferedReader bReader = null; //4
		FileReader fReader = null;

		String currentLine = null;
		String tempString = null;
		String[] tempSplitArray = null;
		try 
		{
			fReader = new FileReader("people.txt");
			bReader = new BufferedReader(fReader);

			int counter = 1;
			String id = null;
			while( (currentLine = bReader.readLine()) != null )
			{
				id = "id" + counter;
				tempString = currentLine;
				tempSplitArray = tempString.split(",");
				int age = Integer.parseInt(tempSplitArray[4].trim());
				CreatePeople.createPeople(id, tempSplitArray[0].trim(), tempSplitArray[1].trim(), tempSplitArray[2].trim(), tempSplitArray[3].trim(), age, tempSplitArray[5].trim());
				tempSplitArray = null;
				counter++;
			}
			/*
			System.out.println("ctPpl.getListOfAdult().size() " + ctPpl.getListOfAdult().size());
			System.out.println("ctPpl.getListOfDependent().size() " + ctPpl.getListOfDependent().size());
			*/
		}
		catch(Exception e)
		{
			peopleFileNotFound = e.getMessage();
			System.out.println("[TextFile]readTextFile: " + e.getMessage());
		}
	}

	public void readRelationsTextFile()
	{
		BufferedReader bReader = null; //4
		FileReader fReader = null;

		String currentLine = null;
		String tempString = null;
		String[] tempSplitArray = null;
		try 
		{
			fReader = new FileReader("relations.txt");
			bReader = new BufferedReader(fReader);

			while( (currentLine = bReader.readLine()) != null )
			{
				tempString = currentLine;
				tempSplitArray = tempString.split(",");
				
				CreatePeople.createConnectionInputList2(tempSplitArray[0].trim(), tempSplitArray[1].trim(), tempSplitArray[2].trim());
				tempSplitArray = null;
			}
			System.out.println("CreatePeople.getConnectionInputList()2.size() " + CreatePeople.getConnectionInputList2().size());
		}
		catch(Exception e)
		{
			relationFileNotFound = e.getMessage();
			System.out.println("[TextFile]readTextFile: " + e.getMessage());
		}
	}
}
