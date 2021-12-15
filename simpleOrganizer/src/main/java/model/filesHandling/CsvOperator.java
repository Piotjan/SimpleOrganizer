package model.filesHandling;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to operate with csv files
 *
 * @author Piotr Janowski
 * @version 1.4
 */
public class CsvOperator implements FileOperator {
	/**
	 * file name
	 */
	private String fileName;
	
	/**
	 * creator
	 *
	 * @param fileName fileName
	 */
	public CsvOperator(String fileName) {
		this.fileName = fileName;
	}
	
	@Override
	public List<List<String>> getFromFile() throws IOException {
		List<List<String>> toReturn = new ArrayList<List<String>>();
		String line = "";
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		while ((line = br.readLine()) != null)   //returns a Boolean value
		{
			List<String> reader = List.of(line.split(";"));    // use comma as separator
			toReturn.add(reader);
		}
		return toReturn;
	}
	
	@Override
	public void saveToFile(List<String> toSave) throws IOException {
		FileWriter writer = new FileWriter(fileName);
		for (String line : toSave) {
			writer.append(line + "\n");
		}
		writer.flush();
		writer.close();
		
	}
}
