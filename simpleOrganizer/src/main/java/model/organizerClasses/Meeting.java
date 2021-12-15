package model.organizerClasses;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.exceptions.MissingValuesOfParameter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Class which represents meeting in calendar
 *
 * @author Piotr Janowski
 * @version 1.11
 */
public class Meeting implements MyEntity {
	/**
	 * name of object
	 */
	private String name;
	/**
	 * date of the meeting
	 */
	private Date date;
	/**
	 * duration of meeting in hours
	 */
	private Float duration;
	/**
	 * place of meeting
	 */
	private String place;
	
	/**
	 * constructor
	 *
	 * @param params String table with parameters to create a new object
	 * @throws ParseException
	 */
	public Meeting(List<String> params) throws ParseException, NumberFormatException, MissingValuesOfParameter {
		this.name = params.get(0);
		if(name.isBlank()||name.isEmpty())
			throw new MissingValuesOfParameter("Name cannot be empty!");
		SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
		this.date = formatter.parse(params.get(1));
		this.duration = Float.parseFloat(params.get(2));
		this.place = params.get(3);
	}
	
	@Override
	public String getAllData() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
		String dateFormatted = formatter.format(date);
		return new String(name + ";" + dateFormatted + ";" + duration + ";" + place + ";");
	}
	
	
	public final StringProperty nameProperty(){
		return new SimpleStringProperty(name);
	}
	
	public final StringProperty dateProperty(){
		SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
		String dateFormatted = formatter.format(date);
		return new SimpleStringProperty(dateFormatted.toString());
	}
	
	public final StringProperty durationProperty(){
		return new SimpleStringProperty(duration.toString());
	}
	
	public final StringProperty placeProperty(){
		return new SimpleStringProperty(place);
	}
	
	
	/**
	 * Returns date
	 *
	 * @return date
	 */
	public Date getDate() {
		return date;
	}
	
	@Override
	public String getName() {
		return name;
	}
}
