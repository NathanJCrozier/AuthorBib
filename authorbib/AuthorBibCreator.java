package authorbib;

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;



//-----------------------------------------------------
//COMP 249
//Assignment: COMP 249 Assignment 3
//Written by: Nathan Crozier (40048644)
//-----------------------------------------------------



/**
 * @author Nathan Crozier
 * @version 4.10.0
 * @see Article
 * @see FileExistsException
 *
 */
public class AuthorBibCreator {
	
	public static final String PROJECTPATH = "C:\\Users\\Nathan\\eclipse-workspace\\COMP 249 Assignment 3\\"; //Change this constant to match your directory. Make sure to have a bibFile folder wherever this leads that contains 10 Latex files numbered 1-10.
	
	

	/** Take in an array of strings and returns an ArrayList of strings with whitespace and the closing semicolon removed.
	 * @param rawList An array of strings containing trimmed and split strings from a bibTex file. The list you are meant to pass into here is the one that is returned from the method called preProcess().
	 * @return returns null if you passed in an empty list, and if all goes according to plan, will return an ArrayList of strings that has gotten rid of empty spaces, extraneous white space, and the final semicolon.
	 */

	public static ArrayList<String> refineThis(String[] rawList) { //Take in an array of strings and returns an ArrayList of strings with whitespace and the closing semicolon removed.

		if (rawList == null) {
			return null;
		}

		ArrayList<String> firstRefinement = new ArrayList<String>();
		ArrayList<String> secondRefinement = new ArrayList<String>();

		for (String s : rawList) {
			if (!(s.equals(""))) {
				firstRefinement.add(s.trim());
			}
		}

		for (String s : firstRefinement) {
			secondRefinement.add(s.substring(0, s.length() - 1));
		}

		return secondRefinement;

	}

	/**
	 * @param file Takes in a file object which should point to a bibTex file. It will check first if the file might be one of the json files created by this program and avoid manipulating those.
	 * @return Once it takes in a file, it will break that file down into smaller strings and return an array of strings containing the split pieces. This is directly meant to be passed into the refineThis() method.
	 * Take in a file and as long as it's not one of the created files, split it up and return an array of cleaned strings. This is meant to be sent into the refineThis() method.
	 */
	public static String[] preProcess(File file) { //Take in a file and as long as it's not one of the created files, split it up and return an array of cleaned strings. This is meant to be sent into the refineThis() method.

		Scanner fileReader = null;
		String temp = "";

		try {
			fileReader = new Scanner(new FileInputStream(file));

			if ((file.getName().contains("ACM")) || (file.getName().contains("IEEE"))
					|| (file.getName().contains("NJ"))) {

				fileReader.close();
				return null;
			}

			while (fileReader.hasNext()) {

				fileReader.useDelimiter("@");

				temp += fileReader.next();

			}

		}

		catch (FileNotFoundException e) {
			System.out.println("File not found when attempting to preprocess");
		}

		fileReader.close();
		return temp.split("ARTICLE\\{");

	}

	/** Take in an author name, and an ArrayList of Article objects that has already been filled with the searched author name, and create the required files.
	 * @param givenAuthorName This is the surname of an author that will be used to name the json files.
	 * @param curatedMasterList This is a pre-searched ArrayList of Article objects that will be used to create the json output of this program.
	 * 
	 */
	public static void createFiles(String givenAuthorName, ArrayList<Article> curatedMasterList) { //Take in an author name, and an ArrayList of Article objects that has already been filled with the searched author name, and create the required files.

		System.out.println("A total of " + curatedMasterList.size() + " articles with the name " + givenAuthorName + " were found.");
		System.out.println("Creating 3 files..");

		File iEEE = new File(PROJECTPATH + "bibFiles\\" + givenAuthorName
				+ "-IEEE.json"); // Create new IEEE file for the author.
		if (iEEE.exists())
			try {
				throw new FileExistsException(
						"IEE file for this author was already found! Creating a back up of the old one before overwriting!");
			} catch (FileExistsException e) {

				System.out.println(e.message);

				File backUpIEEE = new File(PROJECTPATH + "bibFiles\\"
						+ givenAuthorName + "-IEEE-BU.json");
				 
				if (backUpIEEE.exists()) { //If the backup already exists, delete it to make space for the new one.
					backUpIEEE.delete();
				}
																

				iEEE.renameTo(backUpIEEE);

				try {
					iEEE.createNewFile(); // Create the file now that the backup is saved.
				} catch (IOException d) {
					// TODO Auto-generated catch block
					System.out.print("IO Exception!");
				}

			}

		else {
			try {
				iEEE.createNewFile(); // If the file doesn't already exist, just make one.
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.print("IO Exception!");
			}
		}

		File acm = new File(PROJECTPATH + "bibFiles\\" + givenAuthorName
				+ "-ACM.json"); // Create new ACM file for the author.
		
		
		if (acm.exists())
			try {
				throw new FileExistsException(
						"ACM file for this author was already found! Creating a back up of the old one before overwriting!");
			} catch (FileExistsException e) {

				System.out.println(e.message);

				File backUpACM = new File(PROJECTPATH + "bibFiles\\"
						+ givenAuthorName + "-ACM-BU.json"); // If the file already exists, rename it to backup and make
																// the new one.
				
				if (backUpACM.exists()) { //If the backup already exists, delete it to make space for the new one.
					backUpACM.delete();
				}
				acm.renameTo(backUpACM);

				try {
					acm.createNewFile(); // Create the file now that the backup is saved.
				} catch (IOException d) {
					// TODO Auto-generated catch block
					System.out.print("IO Exception!");
				}

			}

		else {
			try {
				acm.createNewFile(); // If the file doesn't already exist, just make one.
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.print("IO Exception!");
			}
		}

		File nj = new File(PROJECTPATH + "bibFiles\\" + givenAuthorName
				+ "-NJ.json"); // Create new NJ file for the author.
		if (nj.exists())
			try {
				throw new FileExistsException(
						"NJ file for this author was already found! Creating a back up of the old one before overwriting!");
			} catch (FileExistsException e) {

				System.out.println(e.message);

				File backUpNJ = new File(PROJECTPATH + "bibFiles\\"
						+ givenAuthorName + "-NJ-BU.json"); // If the file already exists, rename it to backup and make
															// the new one.
				
				if (backUpNJ.exists()) { //If the backup already exists, delete it to make space for the new one.
					backUpNJ.delete();
				}
				
				nj.renameTo(backUpNJ);

				try {
					nj.createNewFile(); // Create the file now that the backup is saved.
				} catch (IOException d) {
					// TODO Auto-generated catch block
					System.out.print("IO Exception!");
				}

			}
		else {
			try {
				nj.createNewFile(); // If the file doesn't already exist, just make one.
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.print("IO Exception!");
			}
		}
		AuthorBibCreator.processBibFiles(curatedMasterList, iEEE, acm, nj); //Pass in the created files, and the curated master list to the processBibFiles() method to write the required strings to their respective files.
		System.out.println("Files successfully created!"); //Let the user know the files have successfully been created.

	}

	/** Pass in the master list of Articles, and the author name and this method will search all of the articles for the requested author and return a new ArrayList containing just the articles with the requested author.
	 * @param masterList This is an ArrayList containing all of the found articles from all of the bibTex files.
	 * @param givenAuthorName This is the desired surname of an author. This name will be searched in the master list to return the result.
	 * @return The method will return a new ArrayList of articles which will contain only those articles that have been found to contain the surname of the desired author.
	 * 
	 */
	public static ArrayList<Article> authorSearch(ArrayList<Article> masterList, String givenAuthorName) { //Pass in the master list of Articles, and the author name and this method will search all of the articles for the requested author and return a new ArrayList containing just the articles with the requested author.

		ArrayList<Article> curatedMasterList = new ArrayList<Article>();

		for (Article a : masterList) {
			boolean containsAuthor = false;

			for (String s : a.getAuthors()) {

				if (s.endsWith(givenAuthorName) || s.endsWith(givenAuthorName.toLowerCase())) { //If the article has the requested author, add it to the curated list.

					containsAuthor = true;

				}

			}

			if (containsAuthor == true) {
				curatedMasterList.add(a);
			}
		}

		return curatedMasterList;

	}

	/** Pass in the curated master list, as well as the 3 files, and this method will use a printWriter to output the article information from the ArrayList to the proper files with the correct formatting.
	 * @param curatedMasterList This is the pre-searched ArrayList that you can get when you run the authorSearch() method.
	 * @param iEEE the file object which points to the already created IEEE file.
	 * @param acm the file object which points to the already created ACM file.
	 * @param nj the file object which points to the already created NJ file.
	 * 
	 * 
	 */
	public static void processBibFiles(ArrayList<Article> curatedMasterList, File iEEE, File acm, File nj) { //Pass in the curated master list, as well as the 3 files, and this method will use a printWriter to output the article information from the ArrayList to the proper files with the correct formatting.
		
		PrintWriter fileWriter = null;
		
		try {
			fileWriter = new PrintWriter (new FileOutputStream (iEEE));
			
			for (Article a: curatedMasterList) {
				fileWriter.println(a.listAuthorsIEEE() + " \"" + a.title + "\", " + a.journal + ", vol." + a.volume + ", no." + a.number + ", p." + a.pages + ", " + a.month + " " + a.year + "."); //IEEE formatting for each of the articles.
				fileWriter.println("");
			}
			
			fileWriter.flush(); //Make sure the contents of the printWriter make it to the file.
		}
		
		catch (FileNotFoundException e) {
			System.out.println("The created IEEE file could not be found! Terminating the program.");
			iEEE.delete(); //If the IEEE file couldn't be found, make sure to delete any that were made.
		}
		
		 fileWriter = null;
		 int counter = 1; //This counter will be used to number the articles for ACM formatting.
		
		try {
			fileWriter = new PrintWriter (new FileOutputStream (acm));
			
			for (Article a: curatedMasterList) {
				fileWriter.println("[" + counter + "]" + "\t" + a.listAuthorsACM() + " " + a.year + ". " + a.title + ". "
						+ a.journal + ". " + a.volume + ", " + a.number + "(" + a.year + "), " + a.pages
						+ ". DOI: https://doi.org " + a.doi + "."); //ACM formatting for each of the articles.
				fileWriter.println("");
				counter++;
			}
			
			fileWriter.flush(); //Make sure the contents of the printWriter make it to the file.
			
		}
		
		catch (FileNotFoundException e) {
			System.out.println("The created ACM file could not be found! Terminating the program.");
			iEEE.delete();
			acm.delete(); //If the ACM file couldn't be found, make sure to delete any that were made, as well as any previous files.
		}
		
		fileWriter = null;
		
		try {
			fileWriter = new PrintWriter (new FileOutputStream (nj));
			
			for (Article a: curatedMasterList) {
				fileWriter.println(a.listAuthorsNJ() + " " + a.title + ". " + a.journal + ". " + a.volume + ", " + a.pages + "(" + a.year + ")."); //NJ formatting for each of the articles.
				fileWriter.println("");
				
			}
			
			
		}
		
		catch (FileNotFoundException e) {
			System.out.println("The created NJ file could not be found! Terminating the program.");
			iEEE.delete();
			acm.delete();
			nj.delete(); //If the NJ file couldn't be found, make sure to delete any that were made, as well as any previous files.
		}
		
		
		fileWriter.close(); //Make sure the contents of the printWriter make it to the file and that it's closed to avoid memory leaks. Keep in mind this also calls flush() anyway, so it's doing the same thing as the others.
		
		
	}
	
	
	
		
		public static void main(String args[]) {

		File mainDirectory = new File(PROJECTPATH + "bibFiles"); //Create a directory leading to the bibFile folder.


		String[] fileNameArray = mainDirectory.list(); //Create a list of Strings of the file names in the bibFile folder.

		ArrayList<Article> masterList = new ArrayList<Article>();

		for (int i = 1; i <= 10; i++) { // Checking the directory to ensure that all 10 Latex files are there, otherwise
										// it throws an exception and terminates.
			File temp = new File(
					PROJECTPATH + "bibFiles\\Latex" + i + ".bib");
			if (!(temp.exists())) {
				try {
					throw new FileNotFoundException();
				} catch (FileNotFoundException e) {
					System.out.println("Could not open file Latex" + i
							+ ".bib for reading. Please check if file exists! Program will terminate after closing any opened files.");
					System.exit(0);
				}
			}
		}

		for (String s : fileNameArray) { //For every file name in the array of file names.

			File workOnThisFile = new File(
					PROJECTPATH + "bibFiles\\" + s); //create a file object attached to the corresponding name
																									

			String[] temporaryList = AuthorBibCreator.preProcess(workOnThisFile); // For every file created, run the
																					// preprocessing method.
			ArrayList<String> refinedList = new ArrayList<String>();
			if (temporaryList != null) {
				refinedList = AuthorBibCreator.refineThis(temporaryList); // For every list returned from
																			// pre-processing, run the refining method.
			}

			ArrayList<Article> miniArticleList = Article.createArticleList(refinedList); // For every refined list
																							// returned from refining,
																							// create an arraylist of
																							// article objects.

			for (Article a : miniArticleList) { // For every arraylist of article objects created, add the article
												// objects to the master list. We can now work with the master list from
												// here on out.

				if (a != null)//Don't add the article to the master list if it's empty.
					masterList.add(a); 

			}


		}

		System.out.println("Welcome to BibCreator!\n\n"); //Welcome message.

		System.out.println("Please enter the surname of the author you are targeting: ");
		Scanner keyIn = new Scanner(System.in);

		boolean authorNameLowercase = false;
		String givenAuthorName = null;

		do {

			givenAuthorName = keyIn.next();

			if (givenAuthorName.equals(givenAuthorName.toLowerCase())) { //An attempt to stop people from searching for author names without using proper syntax. This helps the program run more smoothly and with less hiccups.
				System.out.println(
						"It appears you have entered an invalid name or an author name without capitalizing the first letter. Last names should have the first letter capitalized. Please try again: ");
				authorNameLowercase = true;
			} else
				authorNameLowercase = false;

		} while (authorNameLowercase == true);

		ArrayList<Article> curatedMasterList = AuthorBibCreator.authorSearch(masterList, givenAuthorName); //Run an author search on the master list after receiving the author name. Take that returned list as the curated list.

		if (curatedMasterList.isEmpty()) { //If the returned list has no matches, then obviously there weren't any authors with that last name in the master list.
			System.out.println("No records were found for author(s) with the name: " + givenAuthorName
					+ ". No files will be created and the program will close. Have a nice day!");
			System.exit(0);
		}

		AuthorBibCreator.createFiles(givenAuthorName, curatedMasterList); //Create the files.
		
		

		System.out.println("Goodbye! Hope you have enjoyed creating the needed files using AuthorBibCreator!");
		keyIn.close(); //Close the scanner, and alert the user that the program is closing.

	}

}
