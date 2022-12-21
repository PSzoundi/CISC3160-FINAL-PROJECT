import java.util.*;

public class Program {

   static private ArrayList<Assignment> assignments = new ArrayList<>() ;
   
   public void createAssignment(String str){
      Assignment newAssign = new Assignment(str);
      boolean status = false;
      for(int i=0; i< assignments.size(); i++){
         if(assignments.get(i).getIdentifier().equals(newAssign.getIdentifier())){
            status = true;
            assignments.add(i, newAssign);
            break;
         }
            
      }
      if(!status){
         assignments.add(newAssign);
      }
   }
   //given an identifier, this function return the corresponding expression if it exist in the list
   public static int getExpression(String identifier){
      int x=0;
      if(assignments.size()> 0){
         for(int i=0; i< assignments.size(); i++){
            if(assignments.get(i).getIdentifier().equals(identifier)){
               x = assignments.get(i).getExpValue();
               
               break;
            }
         }
       
         return x;
      }
      else{
         throw new RuntimeException("error");
      }
   }
   public static void displayProgram(){
      for(int i=0; i< assignments.size(); i++){
         assignments.get(i).displayAssignment();
      }
   }
  
}