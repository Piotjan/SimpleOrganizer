package model.containers;

import model.exceptions.InvalidObjectToDelete;
import model.exceptions.InvalidParameter;
import model.exceptions.InvalidPhoneNumber;
import model.exceptions.MissingValuesOfParameter;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 * Interface of containers of main objects
 *
 * @author Piotr Janowski
 * @version 1.5
 */
public interface Container {
	/**
	 * Adding new object to the list
	 *
	 * @param newParams parameters for new object
	 */
	public void addNew(String newParams) throws ParseException, InvalidPhoneNumber, MissingValuesOfParameter, NumberFormatException, InvalidParameter;
	
	/**
	 * Deletes an object from list
	 *
	 * @param which name of object to delete
	 */
	public boolean delete(String which) throws InvalidObjectToDelete;
	
	/**
	 * Prepares data for show all objects from the list and runs interface's method
	 * @return
	 */
	public List<String> allToPrint();
	
	/**
	 * Reads data from file
	 *
	 * @param fileName file name
	 */
	public void loadFromFile(String fileName) throws ParseException, IOException, MissingValuesOfParameter;
	
	/**
	 * Saves data to file
	 *
	 * @param fileName file name
	 */
	public void saveToFile(String fileName);
}
