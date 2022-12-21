import java.util.regex.*; 
public class Evaluator {
   private String s;
   private int value;
   private int currIndex;
   private int n;
   private char inputToken;
   
   public Evaluator(String s){
      this.s = s;
      currIndex = 0;
      n = s.length();
      nextToken();
   }

   void nextToken(){
      char c;
      do {
         if (currIndex == n){
            inputToken = '$';
            return;
         }
         c = s.charAt(currIndex++);
      } while (Character.isWhitespace(c));
      inputToken = c;
   }
  
   void match(char token){
      if (inputToken == token){
         nextToken();
      } else {
         throw new RuntimeException("syntax error");
      }
   }
   int getExpression(){
      return value;
   }
   int eval(){
   
      int x = exp();
      if (inputToken == '$'){
         value = x;
         return x;
      } else {
         throw new RuntimeException("syntax error");
      }
   }
  
   int exp(){
      int x = term();
      while (inputToken == '+' || inputToken == '-'){
         char op = inputToken;
         nextToken();
         int y = term();
         x = apply(op, x, y);
      }
      return x;
   }
  
   int term(){
      int x = factor();
      while (inputToken == '*'){
         char op = inputToken;
         nextToken();
         int y = factor();
         x = apply(op, x, y);
      }
      return x;
   }
  
   int factor(){
      int x;
      if(inputToken == '('){
         nextToken();
         x = exp();
         match(')');
         return x;
      }
      // if there a negative sign return the opposite value.
      else if(inputToken == '-'){
         nextToken();
         x= factor();
         if(x == 0){
            throw new RuntimeException("error");
         }
         return -x;
      }
      else if(inputToken == '+'){
         nextToken();
         x= factor();
         return x;
      }
      // if current char is digit it might be a literal
      else if(Character.isDigit(inputToken)){
         x= literal();
         return x;
      }
      // if current char is a letter or '_' it might be an identifier
      else if(Character.isLetter(inputToken) || inputToken == '_'){
         x =getValue();
         
         return x;
      }
      
      else{
         throw new RuntimeException("error");
       
      }
   }
   //This function accumlated digits and return the corresponding integer
   int literal(){
      String str= "";
      String patern = "0|^[1-9]\\d*";
      int x;
      do{
         str += inputToken;
         nextToken();
      }
      while(Character.isDigit(inputToken));
       //check for validation
      boolean validated =Pattern.matches(patern, str);
      if(validated){
         x = Integer.parseInt(str);
      }
      else{
         throw new RuntimeException("error");
      }
      return x;
   }
   //get the value assigned to the identifier
   int getValue(){
      String str= "";
      char  c =inputToken;
      int x;
      do{
        
         str += c;
         nextToken();
         c =inputToken;
           
      }while(c !='+' && c !='-' && c !=')' && c !='$');
      
       //check for validation
      Identifier id = new Identifier(str);
      String var = id.getIdentifier();
      
      x = Program.getExpression(var);
      
      return x;
   }
   
   int apply(char op, int x, int y){
      int z = 0;
      switch (op){
         case '+': z = x + y; 
            break;
         case '-': z = x - y; 
            break;
         case '*': z = x * y; 
            break;
      }
      return z;
   }
   
}