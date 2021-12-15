package model.organizerClasses;


/**
 * Interface of main entity of the program
 *
 * @author Piotr Janowski
 * @version 1.3
 */
public interface MyEntity {
	/**
	 * Returns the name of object
	 *
	 * @return name parameter
	 */
	String getName();
	
	/**
	 * Returns all parameters in one String
	 *
	 * @return String with alla parameters
	 */
	String getAllData();
}
