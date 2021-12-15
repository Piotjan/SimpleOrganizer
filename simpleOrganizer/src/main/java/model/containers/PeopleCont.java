package model.containers;

import model.exceptions.InvalidObjectToDelete;
import model.exceptions.InvalidParameter;
import model.exceptions.InvalidPhoneNumber;
import model.exceptions.MissingValuesOfParameter;
import model.filesHandling.CsvOperator;
import model.organizerClasses.Person;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Container of People
 *
 * @author Piotr Janowski
 * @version 1.8
 */
public class PeopleCont implements Container {
	/**
	 * Vector of People
	 */
	private List<Person> people;
	
	/**
	 * constructor
	 *
	 */
	public PeopleCont() {
		this.people = new ArrayList<Person>();
	}
	
	@Override
	public void addNew(String newParams) throws InvalidPhoneNumber, MissingValuesOfParameter, InvalidParameter {
		List<String> params = List.of(newParams.split(";"));
		for (Person person : people) {
			if (person.getName().compareTo(params.get(0)) == 0)
				throw new InvalidParameter("Names have to be different");
		}
		
		String[] splitter = params.get(1).split("\\s");
		String number = "";
		for (int i = 0; i < splitter.length; ++i)
			number = number + splitter[i];
		number.replaceAll(" ", "");
		if (params.get(0).compareTo("-") == 0)
			throw new MissingValuesOfParameter("Name of person is necessary");
		if (number.charAt(0) == '-' && number.length() == 1)
			people.add(new Person(params));
		else {
			boolean dialCode = false;
			if (number.charAt(0) == '+') {
				if (number.length() != 12)
					throw new InvalidPhoneNumber("Wrong length of number");
				dialCode = true;
			} else if (number.length() != 9)
				throw new InvalidPhoneNumber("Wrong length of number");
			int firstPos = 0;
			if (dialCode)
				firstPos = 1;
			for (int i = firstPos; i < number.length(); ++i) {
				if (number.charAt(i) < 48 || number.charAt(i) > 57)
					throw new InvalidPhoneNumber("Only numbers can be in number");
			}
		}
		people.add(new Person(params));
	}
	
	@Override
	public boolean delete(String which) throws InvalidObjectToDelete {
		List<Person> toDelete = new ArrayList<Person>();
		people
				.stream()
				.filter(name -> name.getName().equals(which))
				.forEach(name -> {
					toDelete.add(name);
				});
		if (toDelete.isEmpty())
			throw new InvalidObjectToDelete("No matching meetings with this name");
		people.removeAll(toDelete);
		return true;
	}
	
	@Override
	public List<String> allToPrint() {
		List<String> toPrint = new ArrayList<String>();
		int i = 0;
		for (Person person : people) {
			toPrint.add(person.getAllData());
			++i;
		}
		return toPrint;
	}
	
	@Override
	public void loadFromFile(String fileName) throws IOException, MissingValuesOfParameter {
		CsvOperator dataReader = new CsvOperator(fileName);
		List<List<String>> dataFromFile = dataReader.getFromFile();
		for (List<String> person : dataFromFile) {
			people.add(new Person(person));
		}
	}
	
	@Override
	public void saveToFile(String fileName) {
		CsvOperator fileWriter = new CsvOperator(fileName);
		List<String> toSave = new ArrayList<String>();
		for (Person person : people) {
			toSave.add(person.getAllData());
		}
		try {
			fileWriter.saveToFile(toSave);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<Person> getAll(){
		return people;
	}
}
