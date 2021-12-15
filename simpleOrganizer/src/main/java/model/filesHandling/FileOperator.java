package model.filesHandling;

import java.io.IOException;
import java.util.List;

/**
 * Interface to manage files
 *
 * @author Piotr Janowski
 * @version 1.0
 */
public interface FileOperator {
	/**
	 * Reads data from the file
	 *
	 * @return data from file
	 */
	public List<List<String>> getFromFile() throws IOException;
	
	/**
	 * Writes data to file
	 *
	 * @param toSave data to save
	 */
	public void saveToFile(List<String> toSave) throws IOException;
}
