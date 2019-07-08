import java.util.*;
import javax.swing.JOptionPane;

public class Queen extends Ant 
{
   int food, randAnt, randBala, queenAlive = 0;
   
   //Generates random ants 
   Random antGenerator = new Random();
   
   //Queen constructor
   public Queen()
   {
      lifeSpan = 73000;
      antID = 0;
   }
   
   //Hatches new random ant 
   public int hatch()
   {
      randAnt = antGenerator.nextInt(4) + 1;  
      return randAnt;
   }
   
   //Hatches bala ant, 3% chance of spawning 
   public int balaHatch()
   {
      randBala = antGenerator.nextInt(100);
      return randBala;
   }
   
   //Creates eat method 
   public void eat(ColonyNode[][] colonyGrid)
   {
      //Get food amount from queen node 
      food = colonyGrid[13][13].getFood();
      
      //If food level already 0, simulation over.
      if (food == 0)
      {
         //Shows dialog box if queen dies of starvation
         JOptionPane.showMessageDialog (null, "The Queen has died of starvation. The Simulation is over.", "Simulation Ended", JOptionPane.INFORMATION_MESSAGE);
         System.exit(0);
      }
      
      //Consumes 1 unit of food 
      food--;
      
      //Sets food amount on node 
      colonyGrid[13][13].setFood(food);
      colonyGrid[13][13].setFoodAmount(food);
   }
   
   //Sets queen alive status 
   public void setQueenAlive(int queenAlive)
   { 
      this.queenAlive = queenAlive;
   }
   
   //Gets queen alive status 
   public int getQueenAlive()
   {
      return queenAlive;
   }
}