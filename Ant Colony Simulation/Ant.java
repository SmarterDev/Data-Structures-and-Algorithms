import java.util.*;

public class Ant
{
   Integer direction, antID, lifeSpan, numDays, x, y;  
   
   //Creates ant constructor 
   public Ant()
   {
      antID = 0;
      lifeSpan = 3650;
      numDays = 0;
   }
   
   //Sets x-position in grid
   public void setX(int x)
   {
      this.x = x;
   }
   
   //Gets x-position on grid
   public int getX()
   {
      return x;
   }
   
   //Sets y-position on grid 
   public void setY(int y)
   {
      this.y = y;
   }
   
   //Gets y-position on grid
   public int getY()
   {
      return y;
   }
   
   //Method to get x coordinates of new node based on direction
   public int getNewX(int direction, int x)
   {
     if (direction == 1 || direction == 2 || direction == 3)
     {
        return x-1;
     }
     
     else if (direction == 7 || direction == 8 || direction == 9)
     {
        return x+1;
     }
     
     else if (direction == 4 || direction == 6)
     {
        return x;
     }
     
     return x;
   }
   
   //Method to get y coordinates of new node based on direction
   public int getNewY(int direction, int y)
   {
     if (direction == 1 || direction == 4 || direction == 7)
     {
        return y-1;
     }
     
     else if (direction == 3 || direction == 6 || direction == 9)
     {
        return y+1;
     }
     
     else if (direction == 2 || direction == 8)
     {
        return y;
     }
     
     return y;
   }
   
   //Reduce life by 1
   public void reduceLife()
   {
      lifeSpan--;
   }
   
   //Gets queen current lifespan
   public int getLife()
   {
      return lifeSpan;
   }
   
   //Sets and ID
   public void setAntID(int antID)
   {
      this.antID = antID;
   }
  
   //Gets ant ID
   public int getAntID()
   {
      return antID;
   }
   
   //Creates method for movement
   public void move(Ant ant, int x, int y, int moveDirection)
   {  
      //Move northwest
      if (moveDirection == 1)
      {
         ant.setX(x-1);
         ant.setY(y-1);
      }
      
      //Move west
      else if (moveDirection == 2)
      {
         ant.setX(x-1);
      }
      
      //Move southwest
      else if (moveDirection == 3)
      {
         ant.setX(x-1);
         ant.setY(y+1);
      }
      
      //Move north
      else if (moveDirection == 4)
      {
         ant.setY(y-1);
      }
      
      //Move south
      else if (moveDirection == 6)
      {
         ant.setY(y+1);
      }
      
      //Move northeast
      else if (moveDirection == 7)
      {
         ant.setX(x+1);
         ant.setY(y-1);
      }
      
      //Move east
      else if (moveDirection == 8)
      {
         ant.setX(x+1);     
      }
      
      //Move southeast
      else if (moveDirection == 9)
      {
         ant.setX(x+1);
         ant.setY(y+1);
      } 
  }
  
  //Creates random direction for movement 
  public int getRandomDirection(Ant ant)
  {
     //Creates random number generator object
     Random randDir = new Random();
     
     //Gets x and y coordinates of node 
     int xCoor = ant.getX();
     int yCoor = ant.getY();
     
     //If ant not at any border, movement is free
     //Stores random direction
     direction = randDir.nextInt(10);
     
     //If ant is at northern border, stop moving north
     //Direction cannot be 1,4,7
     if (yCoor == 0)
     {
        while (direction == 1 || direction == 4 || direction == 7)
        {
           direction = randDir.nextInt(10);
        }       
     }
     
     //If ant is at southern border, stop moving south
     //Direction cannot be 3,6,9
     else if (yCoor == 26)
     {
        while (direction == 3 || direction == 6 || direction == 9)
        {
           direction = randDir.nextInt(10);
        }
     }
     
     //If ant is at western border, stop moving west
     //Direction cannot be 1,2,3
     else if (xCoor == 0)
     {
         while (direction == 1 || direction == 2 || direction == 3)
        {
           direction = randDir.nextInt(10);
        }
        
     }
     
     //If ant is at eastern border, stop moving east
     //Direction cannot be 
     else if (xCoor == 26)
     {    
        while (direction == 7 || direction == 8 || direction == 9)
        {
           direction = randDir.nextInt(10);
        }
     }
     
     //If ant is in northwestern corner, stop moving north/west
     if (xCoor == 0 && yCoor == 0)
     {
        while (direction == 1 || direction == 2 || direction == 3 || direction == 4 || direction == 7)
        { 
           direction = randDir.nextInt(10);
        }
     }
     
     //If ant is in southwestern corner, stop moving south/west
     if (xCoor == 0 && yCoor == 26)
     {
        while (direction == 1 || direction == 2 || direction == 3 || direction == 6 || direction == 9)
        { 
           direction = randDir.nextInt(10);
        }
     }
     
     //If ant is in southeastern corner, stop moving south/east
     if (xCoor == 26 && yCoor == 26)
     {
        while (direction == 3 || direction == 6 || direction == 7 || direction == 8 || direction == 9)
        { 
           direction = randDir.nextInt(10);
        }
     }
     
     //If ant is in northeastern corner, stop moving north/east
     if (xCoor == 26 && yCoor == 0)
     {
        while (direction == 1 || direction == 4 || direction == 7 || direction == 8 || direction == 9)
        { 
           direction = randDir.nextInt(10);
        }
     }
     
     return direction;
  }
}