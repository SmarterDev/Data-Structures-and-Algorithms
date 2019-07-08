import java.util.*;

public class Bala extends Ant
{
   int number;
   
   //Constructor for bala ant 
   public Bala()
   {
      this.setX(0);
      this.setY(0);
   }
   
   //Creates fight method 
   public boolean fight()
   {
      //Creates random number for 50% attack success rate 
      Random randNum = new Random();
      number = randNum.nextInt(2);
      
      //If attack successful, return true 
      if (number == 0)
      {
         return true;
      }
      
      //If attack unsuccessful, return false 
      return false;
   }
}