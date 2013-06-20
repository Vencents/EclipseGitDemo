package database;

public class StringParser {

	/*
	 * Skip equation sign and return the real value of fields.
	 * And the data format should be like "key=value"
	 */
	public String retriveData(String item, char delimeter){
		System.out.println("Decode.java retriveData(): " + item);
		int index = item.indexOf(delimeter);
		return item.substring(index+1);
	}
}
