package controllers;

import model.containers.MeetingCont;
import model.containers.PeopleCont;
import model.exceptions.InvalidObjectToDelete;
import model.exceptions.InvalidParameter;
import model.exceptions.InvalidPhoneNumber;
import model.exceptions.MissingValuesOfParameter;
import model.organizerClasses.Meeting;
import model.organizerClasses.Person;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 * Controller which manage containers.
 *
 * @author Piotr Janowski
 * @version 1.1
 */
public class MainController {
	
	private String meetingsNameFile;
	private String peopleNameFile;
	private MeetingCont meetings;
	private PeopleCont people;
	
	/**
	 * Controller of MainController
	 *
	 * @param meetingsFile Name of file with saved meetings
	 * @param peopleFile Name of file with saved contacts
	 * @throws ParseException
	 * @throws IOException
	 * @throws MissingValuesOfParameter
	 */
	MainController(String meetingsFile, String peopleFile) throws ParseException, IOException, MissingValuesOfParameter {
		meetingsNameFile=meetingsFile;
		peopleNameFile = peopleFile;
		meetings = new MeetingCont();
		meetings.loadFromFile(meetingsNameFile);
		people = new PeopleCont();
		people.loadFromFile(peopleNameFile);
	}
	
	/**
	 * Method which returns saved meetings.
	 *
	 * @return List of meetings
	 */
	public List<Meeting> calendar() {
		return meetings.getAll();
	}
	
	/**
	 * Method which returns saved contacts.
	 *
	 * @return List of people
	 */
	public List<Person> contacts(){
		return people.getAll();
	}
	
	/**
	 * Method which manage deleting chosen meeting.
	 *
	 * @param name name of chosen meeting
	 * @throws InvalidObjectToDelete
	 */
	public void deleteMeeting(String name) throws InvalidObjectToDelete {
		meetings.delete(name);
		meetings.saveToFile(meetingsNameFile);
	}
	
	/**
	 * Method which manage deleting chosen contact.
	 *
	 * @param name name of chosen contact
	 * @throws InvalidObjectToDelete
	 */
	public void  deletePerson(String name) throws InvalidObjectToDelete {
		people.delete(name);
		people.saveToFile(peopleNameFile);
	}
	
	
	/**
	 * Method which manage adding new meeting.
	 *
	 * @param params parameters of new meeting
	 * @throws MissingValuesOfParameter
	 * @throws ParseException
	 * @throws InvalidParameter
	 */
	public void addMeeting(String params) throws MissingValuesOfParameter, ParseException, InvalidParameter {
		meetings.addNew(params);
		meetings.saveToFile(meetingsNameFile);
	}
	
	
	/**
	 * Method which manage adding new contact.
	 *
	 * @param params parameters of new contact
	 * @throws MissingValuesOfParameter
	 * @throws InvalidPhoneNumber
	 * @throws InvalidParameter
	 */
	public void addPerson(String params) throws MissingValuesOfParameter, InvalidPhoneNumber, InvalidParameter {
		people.addNew(params);
		people.saveToFile(peopleNameFile);
	}
	
}
