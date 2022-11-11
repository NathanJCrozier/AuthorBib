package authorbib;


//-----------------------------------------------------
//COMP 249
//Assignment: COMP 249 Assignment 3
//Written by: Nathan Crozier (40048644)
//-----------------------------------------------------








/**
 * @author Nathan Crozier (40048644)
 * @version 4.10.0
 * @see AuthorBibCreator
 *
 */
public class FileExistsException extends Exception {
	
	String message;
	
	
	public FileExistsException() { //Default message
		message = "Exception: There is already an existing file for that author. File will be renamed as BU, and older BU files will be deleted!";
	}
	
	public FileExistsException(String message) { //Custom message
		this.message = message;
	}

}
