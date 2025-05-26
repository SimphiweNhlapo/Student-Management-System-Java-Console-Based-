import java.util.*;
import java.io.*;



public class Main 
{
	
	public static void addStudent(Scanner scanner)
	{
		try 
		{
			System.out.println("Enter a Student ID: ");
			String id = scanner.nextLine();
			
			System.out.println("Enter Full Name: ");
			String name = scanner.nextLine();
			
			System.out.println("Enter Department: ");
			String dept = scanner.nextLine();
			
			
			System.out.println("Enter Score: ");
			String score = scanner.nextLine();
			
			
			String studentData = id + "," + name + "," + dept + "," + score;
			
			//Append data to file
			//The true in the parameter referes to "Append mode"
			//If you leave the true it will just overwrite the existing file
			
			FileWriter fw = new FileWriter("students.txt", true);
			//Buffered Writer adds buffer into memory to write data in larger chuncks
			//Because the FileWriter writes data char by char
			BufferedWriter bw = new BufferedWriter(fw);	
			
			bw.write(studentData);
			bw.newLine();//move to the next line
			bw.close();
		}
		catch(IOException e)
		{
			System.out.println("❌ Failed to write to file.");
            e.printStackTrace();
		}
		
		
		
	}
	
	//Read from file using buffered reader
	//How to split strings using String.split()
	//How to format output using sout or tabs
	public static void viewAllStudents()
	{
		File file = new File("students.txt");
		
		if(!file.exists())
		{
			System.out.println("No student records found ");
			return;
		}
		
		
		try
		{
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;
			
			System.out.println("\n--- List of Students ---");
			System.out.printf("%-10s %-20s %-20s %-5s%n" , "ID" , "Name" , "Department" , "Score");
			System.out.println("----------------------------------------------------------------------");
			
			
			while((line = reader.readLine()) != null)
			{
				String[] data = line.split(",");
				
				
				if(data.length == 4)
				{
					String id = data[0];
					String name = data[1];
					String dept = data[2];
					String score = data[3];
					
					
					
					System.out.printf("%-10s %-20s %-20s %-5s%n" , id, name , dept, score);
					
					
				}
			}
			
			reader.close();
			
		}
		catch(IOException e)
		{
			System.out.println("Failed to read from file");
			e.printStackTrace();
		}
	}
	
	//We are going to learn how to search input
	//Loop and macth user input with file data
	//How to do case-insensitive comaparisons
	
	public static void searchStudent(Scanner scanner)
	{
		File file = new File("students.txt");
		
		if(!file.exists())
		{
			System.out.println("no student records found");
			return;
			
		    
		}
		
		System.out.println("Enter a Student Name or ID to search");
		String keyword = scanner.nextLine().toLowerCase();
		
		
		
		boolean found = false;
		
		
		try
		{
			BufferedReader reader = new BufferedReader(new FileReader(file));
			
			String line;
			
			 System.out.printf("%-10s %-20s %-20s %-5s%n", "ID", "Name", "Department", "Score");
		     System.out.println("---------------------------------------------------------------");
		     
		     while((line = reader.readLine())!=null)
		     {
		    	 String[] data = line.split(",");
		    	 
		    	 if(data.length == 4)
		    	 {
		    		 String id = data[0];
		    		 String name = data[1];
		    		 String dept = data[2];
		    		 String score = data[3];
		    		 
		    		 
		    		 if(id.equalsIgnoreCase(keyword) || name.toLowerCase().contains(keyword))
		    		 {
		    			 System.out.printf("%-10s %-20s %-20s %-5s%n", id, name, dept, score);
		                 found = true;
		    		 }
		    	 }
		     }
		     
		     reader.close();
		     
		     if(!found)
		     {
		    	 System.out.printf("No matching student found");
                 
		     }
			
			
		}
		catch(IOException e)
		{
			System.out.println("Failed to read from file");
		    e.printStackTrace();
		}
		
		
	}
	
	//So this is what our function need to do :
	//Ask the user for a Student ID
	//Copy all the record except the one to delete in a temporary file 
	//Replace the original file with the updated file
	
	//Key  takeaways 
	//How to read and filter data
	//How to write to a temporary file
	//How to replace one file with another
	
	
	public static void deleteStudentById(Scanner scanner)
	{
		//create the temp and existing files
		File inputFile = new File("students.txt");
		File tempFile = new File("students_temp.txt");
		
		if(!inputFile.exists())
		{
			System.out.println("No student records are found");
			return;
		}
		
		System.out.println("Enter the Student ID to delete: ");
		String deleteId = scanner.nextLine();
		
		boolean deleted = false;
		
		try
		{
			
			
			BufferedReader reader = new BufferedReader(new FileReader(inputFile));
			BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
			
			String line;
			
			while((line = reader.readLine()) != null)
			{
				String[] data = line.split(",");
				
				if(data.length == 4)	
				{
					String id = data[0];
					
					
					if(id.equals(deleteId))
					{
						deleted = true;
						continue;
					}
				}
				
				writer.write(line);
				writer.newLine();
			}
			reader.close();
			writer.close();
			
			
			//This is the part where replace original file with update one
			
			if(deleted)
			{
				if(inputFile.delete())
				{
					if(tempFile.renameTo(inputFile))
					{
						System.out.println("Student record deleted successfully");
					}
					else
					{
						System.out.println("Failed to rename the temp file");
					}
				}
				else
				{
					System.out.println("Failed to delete the original file");
				}
			}
			else
			{
				tempFile.delete();
				System.out.println("Student ID not found");
			}
			
		}
		catch(IOException e)
		{
			System.out.println("There was a problem reading the file");
			e.printStackTrace();
		}
	}
	

	public static void main(String[] args) {
	
		
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Welcome to the Student Management System !");
		
		File file = new File("students.txt");
		
		
		try
		{
			
			
			if(file.createNewFile())
			{
				System.out.println("File has been created successfully");
			}
			else
			{
				System.out.println("The file already exists");
			}
		}
		catch(IOException e)
		{
			System.out.println("There is something wrong : "  );
			e.printStackTrace();
		}
		
		
		while(true)
		{
			System.out.println("---Student Management System ---");
			System.out.println("1. Add a new student");
			System.out.println("2. View All Students");
			System.out.println("3. Search for a Student");
			System.out.println("4. Delete Student by ID");
			System.out.println("5.Exit");
			System.out.println("Choose an option: ");
			int choice = scanner.nextInt();
			scanner.nextLine();
			
			
			if(choice == 1)
			{
				addStudent(scanner);
				
			}
			else if(choice == 2)
			{
				viewAllStudents();
			}
			else if(choice == 3)
			{
				searchStudent(scanner);
			}
			else if(choice == 4)
			{
				deleteStudentById(scanner);
			}
			else if(choice == 5)
			{
				System.out.println("Goodbye!");
			    break;
			}
			else
			{
				System.out.println("Please enter a valid number try again");
			    
			}
		}
		
		
		
		
		
		scanner.close();

	}

}
