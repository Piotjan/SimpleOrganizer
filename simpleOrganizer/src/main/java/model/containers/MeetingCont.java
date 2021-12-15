package model.containers;

import model.exceptions.InvalidObjectToDelete;
import model.exceptions.InvalidParameter;
import model.exceptions.MissingValuesOfParameter;
import model.filesHandling.CsvOperator;
import model.organizerClasses.Meeting;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Container of Meetings
 *
 * @author Piotr Janowski
 * @version 1.18
 */
public class MeetingCont implements Container {
	/**
	 * Vector of Meetings
	 */
	private List<Meeting> meetings;
	
	/**
	 * constructor
	 */
	public MeetingCont() {
		this.meetings = new ArrayList<Meeting>();
	}
	
	@Override
	public void addNew(String newParams) throws ParseException, MissingValuesOfParameter, NumberFormatException, InvalidParameter {
		List<String> params = new ArrayList<String>();
		params = List.of(newParams.split(";"));
		for (Meeting meeting : meetings) {
			if (meeting.getName().compareTo(params.get(0)) == 0)
				throw new InvalidParameter("Names have to be different");
		}
		if (params.get(2).compareTo("-") == 0) {
			List<String> changedParams = new ArrayList<String>();
			for (int i = 0; i < params.size(); ++i) {
				if (i == 2)
					changedParams.add("0");
				else
					changedParams.add(params.get(i));
			}
			meetings.add(new Meeting(changedParams));
		} else
			meetings.add(new Meeting(params));
	}
	
	/**
	 * Sorts Meetings by the date
	 */
	private void sortArray() {
		meetings.sort(new Comparator<Meeting>() {
			@Override
			public int compare(Meeting o1, Meeting o2) {
				return o1.getDate().compareTo(o2.getDate());
			}
		});
	}
	
	@Override
	public boolean delete(String which) throws InvalidObjectToDelete {
		List<Meeting> toDelete = new ArrayList<Meeting>();
		meetings
				.stream()
				.filter(name -> name.getName().equals(which))
				.forEach(name -> {
					toDelete.add(name);
				});
		if (toDelete.isEmpty())
			throw new InvalidObjectToDelete("No matching meetings with this name");
		meetings.removeAll(toDelete);
		return true;
	}
	
	@Override
	public List<String> allToPrint() {
		this.sortArray();
		List<String> toShow = new ArrayList<String>();
		SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
		Date currentDate = new Date(System.currentTimeMillis());
		for (int i = 0; i < meetings.size(); ++i) {
			boolean ifAdded = false;
			do {
				if (currentDate.before(meetings.get(i).getDate())) {
					toShow.add(formatter.format(currentDate));
				} else {
					toShow.add(meetings.get(i).getAllData());
					currentDate = meetings.get(i).getDate();
					ifAdded = true;
				}
				Calendar c = Calendar.getInstance();
				c.setTime(currentDate);
				c.add(Calendar.DATE, 1);
				currentDate = c.getTime();
			} while (!ifAdded);
		}
		return toShow;
	}
	
	@Override
	public void loadFromFile(String fileName) throws ParseException, IOException, MissingValuesOfParameter {
		CsvOperator dataReader = new CsvOperator(fileName);
		List<List<String>> dataFromFile = dataReader.getFromFile();
		Date currentDate = new Date(System.currentTimeMillis());
		for (List<String> meeting : dataFromFile) {
			SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
			Date date = formatter.parse(meeting.get(1));
			if (!date.before(currentDate)) {
				meetings.add(new Meeting(meeting));
			}
		}
	}
	
	@Override
	public void saveToFile(String fileName) {
		CsvOperator fileWriter = new CsvOperator(fileName);
		List<String> toSave = new ArrayList<String>();
		for (Meeting meeting : meetings) {
			toSave.add(meeting.getAllData());
		}
		try {
			fileWriter.saveToFile(toSave);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<Meeting> getAll() {
		return this.meetings;
	}
}
