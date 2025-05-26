
import java.util.HashMap;

public class Main {

	public static void main(String[] args) {
		
		//This is going to be a Map of Strings to Integers
		//The Keys of our map are going to be strings and the Integers are going to be values
		//We are going to add employee names to their IDs
		// This map is going to hold that information for us and allow us to do that lookup
		HashMap<String , Integer> empIds = new HashMap<>();
		
		// add a value to the map
		empIds.put("John", 12345);
		empIds.put("Carl", 54321);
		empIds.put("Jerry", 8675309);
		
		System.out.println(empIds);
		//These are in a different order than I added them in , why ?
		//Maps doesnt gaurantee the order
		//The Map just guarantees that we will that key mapped to the particular value 
		
		
		//Getting a particuular value you use the associated key then you get the associated value
		System.out.println(empIds.get("Carl"));
		
		
		//Check to see if a certain Key exixts in our map
		//Lets check to see if Jerry had an employee Id we  can do this
		System.out.println(empIds.containsKey("George"));
		//It returns a false because it doesnt exist in our set of Keys //You can use containsValue();
		
		System.out.println(empIds.containsValue(8675309));
		
		// This part is going to search for the Key John and Update it since the Key already exists if it was different then it was going to
		//Create a new key and value
		empIds.put("John", 98765);
		System.out.println(empIds);
		
		//replace()
		//putIfAbsent()
		//.remove() 
		
		
		
	}

}
