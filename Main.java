import java.io.*;  // Import the File class
import java.util.Scanner; // Import the Scanner class to read text files
public class Main {

   public static void main(String[] args)throws IOException {
      File file = new File(args[0]); // open a file 
      Scanner reader = new Scanner(file);
      Program program = new Program(); //
       
      // check to see if there is something in the file
      while (reader.hasNextLine()) {
         String data = reader.nextLine(); //get the data from the file 
         program.createAssignment(data);      
      }
      Program.displayProgram();
      reader.close();
      
   }
}