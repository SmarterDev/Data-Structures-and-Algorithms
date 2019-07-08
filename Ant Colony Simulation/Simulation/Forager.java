import java.util.*;

public class Forager extends Ant 
{       
  int pheromoneAmount, pheromoneInput, xLower, xUpper, yLower, yUpper, 
      addX, addY, xCoor, yCoor, size, drop, counter,
      randomNum, direction, loopNumber, returnDirection, visibleCounter,
      randomNumber, beforeLast, last, pheromoneListSize, movementCounter = 0,
      lastItem = 0;
  
  Object goBack, backup;
  
  boolean carryingFood = false;
  
  //Declares array list to store history of x coordinates
  List<Integer> directionList = new ArrayList<Integer>();
  List<Integer> pheromoneList = new ArrayList<Integer>();
  
  //Declares array list to store x/y coordinate movement history 
  List<Integer> xCoorList = new ArrayList<Integer>();
  List<Integer> yCoorList = new ArrayList<Integer>();
  
  //Declares stack to store movement history 
  Stack movementHistory = new Stack();
  
  //Creates random number generator object
  Random randNum = new Random();
  
  //Constructor for forager ant 
  public Forager()
  {
      this.setX(13);
      this.setY(13);
  }
  
  //Gets adjacent nodes pheromone levels 
  public int getAdjacentPheromone(ColonyNode[][] colonyGrid, Forager ant, int xInput, int yInput)
  {
     //Sets counter to 0
     counter = 0;
       
     //Resets direction
     direction = 0;
       
     //Clears direction and pheromone arraylist 
     directionList.clear();
     pheromoneList.clear();  
       
     //Resets pheromone amount
     pheromoneAmount = 0;  
       
     //Resets visible node counter  
     visibleCounter = 0; 
     
     //Sets loop number to 9
     loopNumber = 9;
       
     //Gets 8 nodes surrounding ant 
     xLower = xInput - 1;
     xUpper = xInput + 2;
     yLower = yInput - 1;
     yUpper = yInput + 2;
       
     //Checks xInput/yInput to see if currently at border
     if (xInput == 0)
     {
       xLower = 0;
       loopNumber = 6;
     }
     if (xInput == 26)
     {
       xUpper = 27;
       loopNumber = 6;
     }
     if (yInput == 0)
     {
       yLower = 0;
       loopNumber = 6;
     }
     if (yInput == 26)
     {
       yUpper = 27;
       loopNumber = 6;
     }
     
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
           
           //Checks to see if node is visible
           if (colonyGrid[a][b].isVisible() == false)
           {
              //Increments direction of node that is unavailable 
              visibleCounter++;
              
              //If all nodes around current node are not visible, go back to previous node 
              if (visibleCounter == 7)
              {
                 //Gets most recent node visited for return direction
                 backup = movementHistory.pop();
                 
                 //Stores object from stack history as integer
                 direction = (int) backup;
                 
                 //Returns direction
                 return direction;
              }
              continue;
           }
           
           //If node not visible / ant at queen node / ant at current node: skip node
           if ((a == 13 && b == 13) || counter == 5)
           {
              continue;
           } 
           
           //Adds direction to list since node is visible 
           directionList.add(counter); 
           
           //Gets amount of pheromone in nearby nodes   
           pheromoneAmount = colonyGrid[a][b].getPheromone();
           
           //If nearby node contains pheromone ant, go to that node 
           if (pheromoneAmount > 0)
           { 
              //Adds direcetion to pheromone list if amount is greater than 0
              pheromoneList.add(counter); 
              
              //Stores direction of larger pheromone amount 
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
       }//Ends foor loop
       
       //Sorts pheromone list 
       Collections.sort(pheromoneList);
       
       //Stores size of pheromone list 
       pheromoneListSize = pheromoneList.size();
       
       //Gets last two items in list
       if (pheromoneListSize >= 2)
       {
          //Stores last two items in list 
          beforeLast = pheromoneList.get(pheromoneListSize - 2);
          last = pheromoneList.get(pheromoneListSize - 1);
          
          //If pheromone amount is equal in last two indexes of array, choose randomly
          if (beforeLast == last)
          {
              //Creates random number generator object
              Random random = new Random();

              //Gets random number 0 through 1
              randomNumber = random.nextInt(2);
              
              if (randomNumber == 0)
              {
                 direction = beforeLast;
              } else 
              {
                 direction = last;
              }
          } else
          {
             direction = last;
          }
          
       } 
        
       //Checks to make sure ant is not visiting previous nodes  
       for (int x = 0, y = 0; x < xCoorList.size(); ++x, ++y)
       {  
          //Gets coordinates of upcoming movement 
          xCoor = getNewX(direction, xInput);
          yCoor = getNewY(direction, yInput);
          
          //Checks to make sure new node has not been visited 
          while (xCoor == xCoorList.get(x) && yCoor == yCoorList.get(y))
          {
             //Gets random direction
             direction = getRandomDirection(ant);
             return direction;
          }
       }
       
       //Clears recent nodes visited after 4 consecutive nodes
       if (movementCounter == 4)
       {
         //Clears x and y coordinate history list 
         xCoorList.clear();
         yCoorList.clear();
         
         //Resets movement counter 
         movementCounter = 0;
       }  
          
     return direction;
  }
  
  //Enters return to nest mode
  public int returnNest()
  {
     //Checks to see if movement history stack is empty
     if (movementHistory.empty() == true)
     {
        return 0;
     } else
     {
        //Gets most recent node visited for return direction
        goBack = movementHistory.pop();
        
        //Stores object from stack history as integer
        returnDirection = (int) goBack;
        
        //Returns return direction
        return returnDirection;
     }
  }
  
  //Sets carry food attribute to true
  public void setCarryingFood(int state)
  {
     //If not carrying food, set false
     if (state == 0)
     {
        carryingFood = false;
        
       //If carryinf food, set true  
     } else
     {
        carryingFood = true;
     }
  }
  
  //Gets carryingFood state
  public boolean getCarryingFood()
  {
     return carryingFood;
  }
  
  //Creates movement counter
  public void addMovement()
  {
     movementCounter++;
  }
  
  //Adds x coordinate to list 
  public void setXcoor(int x)
  {
     xCoorList.add(x);
  }
  
  //Adds y coordinate to list
  public void setYcoor(int y)
  {
     yCoorList.add(y);
  }
  
  //Converts direction into opposite direction
  public int convertDirection(int direction)
  {
     if (direction == 1)
     {
        return 9;
     }
     
     else if (direction == 2)
     {
        return 8;
     }
     
     else if (direction == 3)
     {
        return 7;
     }
     
     else if (direction == 4)
     {
        return 6;
     }
     
     else if (direction == 6)
     {
        return 4;
     }
     
     else if (direction == 7)
     {
        return 3;
     }
     
     else if (direction == 8)
     {
        return 2;
     }
     
     else if (direction == 9)
     {
        return 1;
     }
     
     return 0;
  }
  
  //Adds items to return path 
  public void addMovementHistory(int item)
  {
     movementHistory.push(item);
  }
  
  //Deposit pheromone amount in each square visited
  public void dropPheromone(ColonyNode[][] colonyGrid, int x, int y)
  {
    //Gets pheromon level in current node
    drop = (colonyGrid[x][y].getPheromone() + 10);
   
    //Adds 10 to current pheromone level 
    colonyGrid[x][y].setPheromone(drop);
   
    //Sets pheromone level to newly added amount 
    colonyGrid[x][y].setPheromoneLevel(drop);
  }
}