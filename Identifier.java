/*
   This class take a string and validate as an identifier
*/
import java.util.regex.*; 
public class Identifier{
   private String s;

   public Identifier(String str){
      s= str;
   }
   boolean isValidIdentifier(){
      String pattern = "^([a-zA-Z]|_)(([a-zA-Z]|_)|[0-9])*";
      //check for validation
      return Pattern.matches(pattern, s);
   
   }
   
   String getIdentifier(){
      if(isValidIdentifier()){
         return s;
      }
      else{
         throw new RuntimeException("Identifier not valid");
      }
     
   }
}