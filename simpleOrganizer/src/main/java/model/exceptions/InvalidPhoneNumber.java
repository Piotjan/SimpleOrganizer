package model.exceptions;

/**
 * Exception in case of Invalid number of chars in phone number
 *
 * @author Piotr Janowski
 * @version 1.0
 */
public class InvalidPhoneNumber extends Exception{
	/**
	 * constructor
	 *
	 * @param str text of exception
	 */
	public InvalidPhoneNumber(String str){
		super(str);
	}
}
