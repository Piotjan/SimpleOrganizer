//import model.containers.MeetingCont;
//import model.exceptions.InvalidObjectToDelete;
//import model.exceptions.InvalidParameter;
//import model.exceptions.MissingValuesOfParameter;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.ValueSource;
//
//import java.io.IOException;
//import java.text.ParseException;
//
//
//import static org.junit.jupiter.api.Assertions.fail;
//
///**
// * Tests of class MeetingCont
// *
// * @author Piotr Janowski
// * @version 1.0
// */
//public class MeetingContTest {
//
//	/**
//	 * Container of meetings to test
//	 */
//	private MeetingCont meetings;
//
//	/**
//	 * Creating new container before every test
//	 */
//	@BeforeEach
//	private void setUpEach() {
//		meetings = new MeetingCont();
//	}
//
//	/**
//	 * Test of method allToPrint
//	 */
//	@Test
//	public void allToPrintWhenEmpty() {
//		meetings.allToPrint();
//	}
//
//	/**
//	 * Test of method addNew with correct arguments. Shouldn't throw exceptions.
//	 *
//	 * @param params parameters for parametrized test
//	 */
//	@ParameterizedTest
//	@ValueSource(strings = {"Name;20.11.2021;3;Place;", "Name;20.11.2021;-;Place;", "Name;20.11.2021;3;-;"})
//	public void addNewNoExc(String params){
//		try {
//			meetings.addNew(params);
//		} catch (ParseException e) {
//			fail("An exception shouldn't be thrown");
//		} catch (MissingValuesOfParameter e) {
//			fail("An exception shouldn't be thrown");
//		} catch (InvalidParameter e) {
//			e.printStackTrace();
//		}
//	}
//
//	/**
//	 * Test of method addNew with incorrect arguments. Should throw exceptions.
//	 *
//	 * @param params parameters for parametrized test
//	 */
//	@ParameterizedTest
//	@ValueSource(strings = {"-;20.11.2021;3;Place;", "Name;Foo;3;Place;", "Name;20.11.2021;Foo;Place;"})
//	public void addNewExc(String params){
//		try {
//			meetings.addNew(params);
//			fail("An exception should be thrown");
//		} catch (ParseException e) {
//		} catch (NumberFormatException e){
//		} catch (MissingValuesOfParameter e) {
//		} catch (InvalidParameter e) {
//		}
//	}
//
//	/**
//	 * Test of method delete with correct arguments. Shouldn't throw exceptions.
//	 *
//	 *@param param parameters for parametrized test
//	 */
//	@ParameterizedTest
//	@ValueSource(strings = {
//		"Name"
//			})
//	public void deleteNoExc(String param){
//		String toCreate = param+";20.11.2021;-;-;";
//		try {
//			meetings.addNew(toCreate);
//		} catch (ParseException e) {
//			fail("An exception shouldn't be thrown");
//		} catch (MissingValuesOfParameter e) {
//			fail("An exception shouldn't be thrown");
//		} catch (InvalidParameter e) {
//			fail("An exception shouldn't be thrown");
//		}
//		try {
//			meetings.delete(param);
//		} catch (InvalidObjectToDelete e) {
//			fail("An exception shouldn't be thrown");
//		}
//	}
//
//	/**
//	 * Test of method delete with incorrect arguments. Should throw exceptions.
//	 *
//	 * @param param parameters for parametrized test
//	 */
//	@ParameterizedTest
//	@ValueSource(strings = {
//			"Name"
//	})
//	public void deleteExc(String param){
//		String toCreate = param+";20.11.2021;-;-;";
//		try {
//			meetings.addNew(toCreate);
//		} catch (ParseException e) {
//			fail("An exception shouldn't be thrown");
//		} catch (MissingValuesOfParameter e) {
//			fail("An exception shouldn't be thrown");
//		} catch (InvalidParameter e) {
//			fail("An exception shouldn't be thrown");
//		}
//		try {
//			meetings.delete(param+"Foo");
//			fail("An exception should be thrown");
//		} catch (InvalidObjectToDelete e) {
//		}
//	}
//
//	/**
//	 * Test of method loadFromFile with correct arguments. Shouldn't throw exceptions.
//	 *
//	 * @param param parameters for parametrized test
//	 */
//	@ParameterizedTest
//	@ValueSource(strings = {
//			"meetings.csv"
//	})
//	public void loadFromFileNoExc(String param){
//		try {
//			meetings.loadFromFile(param);
//		} catch (ParseException e) {
//			fail("An exception shouldn't be thrown");
//		} catch (IOException e) {
//			fail("An exception shouldn't be thrown");
//		} catch (MissingValuesOfParameter e) {
//			fail("An exception shouldn't be thrown");
//		}
//	}
//
//	/**
//	 * Test of method loadFromFile with incorrect arguments. Should throw exceptions.
//	 *
//	 * @param param parameters for parametrized test
//	 */
//	@ParameterizedTest
//	@ValueSource(strings = {
//			"wrongFile.csv"
//	})
//	void loadFromFileExc(String param){
//		try {
//			meetings.loadFromFile(param);
//			fail("An IOException should be thrown");
//		} catch (ParseException e) {
//			fail("An IOException should be thrown");
//		} catch (IOException e) {
//		} catch (MissingValuesOfParameter e) {
//			fail("An IOException should be thrown");
//		}
//	}
//}
