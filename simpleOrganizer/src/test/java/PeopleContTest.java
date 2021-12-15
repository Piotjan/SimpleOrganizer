//
//import model.containers.PeopleCont;
//import model.exceptions.InvalidObjectToDelete;
//import model.exceptions.InvalidParameter;
//import model.exceptions.InvalidPhoneNumber;
//import model.exceptions.MissingValuesOfParameter;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.ValueSource;
//
//import java.io.IOException;
//
//import static org.junit.jupiter.api.Assertions.fail;
//
//
///**
// * Tests of class PeopleCont
// *
// * @author Piotr Janowski
// * @version 1.0
// */
//public class PeopleContTest {
//
//	/**
//	 * Container of people to test
//	 */
//	private PeopleCont people;
//
//	/**
//	 * Creating new container before every test
//	 */
//	@BeforeEach
//	private void setUp() {
//		people = new PeopleCont();
//	}
//
//	/**
//	 * Test of method allToPrint
//	 */
//	@Test
//	public void allToPrintWhenEmpty() {
//		people.allToPrint();
//	}
//
//	/**
//	 * Test of method addNew with correct arguments. Shouldn't throw exceptions.
//	 *
//	 * @param params parameters for parametrized test
//	 */
//	@ParameterizedTest
//	@ValueSource(strings = {
//			"Name;111222333;email;address;",
//			"Name;-;email;address;",
//			"Name;111222333;-;address;",
//			"Name;111222333;email;-;",
//			"Name;111 222 333;email;address;",
//			"Name;+48111222333;email;address;",
//			"Name;+48 111 222 333;email;address;",
//			"Name;-;-;-;"
//	})
//	public void addNewNoExc(String params) {
//		try {
//			people.addNew(params);
//		} catch (InvalidPhoneNumber e) {
//			fail("An InvalidPhoneNumber exception shouldn't be thrown");
//		} catch (MissingValuesOfParameter e) {
//			fail("A MissingValuesOfParameters exception shouldn't be thrown");
//		} catch (InvalidParameter e) {
//			fail("An InvalidParameter exception shouldn't be thrown");
//		}
//	}
//
//	/**
//	 * Test of method addNew with incorrect arguments. Should throw exceptions.
//	 *
//	 * @param params parameters for parametrized test
//	 */
//	@ParameterizedTest
//	@ValueSource(strings = {
//			"-;111222333;email;address;",
//			"Name;5555111222333;email;address;",
//			"Name;111233;email;address;",
//			"Name;-48111222333;email;address;",
//			"Name;Foo;email;address;"
//	})
//	public void addNewExc(String params){
//		try {
//			people.addNew(params);
//			fail("An exception should be thrown");
//		} catch (InvalidPhoneNumber e) {
//		} catch (MissingValuesOfParameter e) {
//		} catch (InvalidParameter e) {
//		}
//	}
//
//	/**
//	 * Test of method delete with correct arguments. Shouldn't throw exceptions.
//	 *
//	 * @param param parameters for parametrized test
//	 */
//	@ParameterizedTest
//	@ValueSource(strings = {
//			"Name"
//	})
//	public void deleteNoEcx(String param){
//		String toCreate = param+";-;-;-;";
//		try {
//			people.addNew(toCreate);
//		} catch (InvalidPhoneNumber e) {
//			fail("An exception shouldn't be thrown");
//		} catch (MissingValuesOfParameter e) {
//			fail("An exception shouldn't be thrown");
//		} catch (InvalidParameter e) {
//			fail("An exception shouldn't be thrown");
//		}
//		try {
//			people.delete(param);
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
//		try {
//			String toCreate = param+";-;-;-;";
//			people.addNew("Name;-;-;-;");
//		} catch (InvalidPhoneNumber e) {
//			fail("An exception shouldn't be thrown");
//		} catch (MissingValuesOfParameter e) {
//			fail("An exception shouldn't be thrown");
//		} catch (InvalidParameter e) {
//			fail("An exception shouldn't be thrown");
//		}
//		try {
//			people.delete(param+"Foo");
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
//			"people.csv"
//	})
//	public void loadFromFileNoExc(String param){
//		try {
//			people.loadFromFile(param);
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
//			people.loadFromFile(param);
//			fail("An IOException should be thrown");
//		} catch (IOException e) {
//		} catch (MissingValuesOfParameter e) {
//			fail("An IOException should be thrown");
//		}
//	}
//}
