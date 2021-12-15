package model.organizerClasses;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.exceptions.MissingValuesOfParameter;

import java.util.List;

/**
 * Class which represents person in contacts
 *
 * @author Piotr Janowski
 */
public class Person implements MyEntity {
	/**
	 * name of object
	 */
	private String name;
	/**
	 * phone number of the person
	 */
	private String phoneNumber;
	/**
	 * email of the person
	 */
	private String email;
	/**
	 * address of the person
	 */
	private String address;
	
	/**
	 * constructor
	 *
	 * @param params String table with parameters to create a new object
	 */
	public Person(List<String> params) throws MissingValuesOfParameter {
		this.name = params.get(0);
		if(name.isBlank()||name.isEmpty())
			throw new MissingValuesOfParameter("Name cannot be empty!");
		this.phoneNumber = params.get(1);
		this.email = params.get(2);
		this.address = params.get(3);
	}
	
	public final StringProperty nameProperty(){
		return new SimpleStringProperty(name);
	}
	
	public final StringProperty phoneNumberProperty(){
		return new SimpleStringProperty(phoneNumber);
	}
	
	public final StringProperty emailProperty(){
		return new SimpleStringProperty(email);
	}
	
	public final StringProperty addressProperty(){
		return new SimpleStringProperty(address);
	}
	
	@Override
	public String getAllData() {
		return new String(name + ";" + phoneNumber + ";" + email + ";" + address + ";");
	}
	
	@Override
	public String getName() {
		return name;
	}
}
