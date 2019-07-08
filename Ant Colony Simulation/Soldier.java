import java.util.*;

public class Soldier extends Ant 
{
   int xLower, xUpper, yLower, yUpper, addX, addY, xCoor, yCoor, loopNumber,
       size, randomNum, number, direction, counter, lastItem = 0, balaAmount = 0;
   
   //Declares array list to store x coordinate movement history 
   List<Integer> directionList = new ArrayList<Integer>();
   
   //Creates random number generator object
   Random randNum = new Random();
   
   public Soldier()
   {
     this.setX(13);
     this.setY(13); 
   }
   
   public int scoutMode(ColonyNode[][] colonyGrid, Soldier ant, int xInput, int yInput)
   {
       //Sets counter to 0
       counter = 0;
       
       //Resets direction
       direction = 0;
       
       //Clears direction arraylist 
       directionList.clear();
       
       //Sets loop number to 9
       loopNumber = 9;
       
       //Gets 8 nodes surrounding ant 
       xLower = xInput - 1;
       xUpper = xInput + 2;
       yLower = yInput - 1;
       yUpper = yInput + 2;
       
     iterations:
       //Searches through nearby nodes for bala ant count  
       for (int a = xLower; a < xUpper; ++a)
       {
         for (int b = yLower; b < yUpper; ++b)
         {            
           //Increments counter for direction 
           counter++;
           
           //Checks to see if ant is at border, prevents illegal movement 
           try 
           {
              colonyGrid[a][b].isVisible();
           } catch (Exception e)
           {
              continue;
           }
           
           //If node not visible / ant at queen node / ant at current node: skip node
           if (colonyGrid[a][b].isVisible() == false || (a == 13 && b == 13) || counter == 5)
           {
              continue;
           }
           
           //Adds current direction to list 
           directionList.add(counter);
           
           //Gets number of bala ants in nearby node   
           balaAmount = colonyGrid[a][b].getBalas();
            
           //If nearby node contains bala ant, go to that node 
           if (balaAmount > 0)
           { 
              //Stores current counter value as direction 
              direction = counter;
              break iterations;
           }
              
           //Stores size of direction arraylist
           size = directionList.size(); 

           //If arraylist has direction stored, get direction 
           if (size > 0)
           {
              //Creates random number 
              randomNum = randNum.nextInt(size);
              
              //Stores direction chosen from arraylist of visible nodes
              direction = directionList.get(randomNum);
           }        
         }//Ends nested for loop
       }//Ends for loop 
       
       //Return direction chosen 
       return direction;
   }
   
   
   //Creates fight method 
   public boolean attackMode()
   {
      //Creates random number for 50% attack success rate 
      number = randNum.nextInt(2);
      
      //If attack successful, return true 
      if (number == 0)
      {
         return true;
      } else 
      {
      //If attack unsuccessful, return false 
      return false;  
      }
   }
}