import java.util.Stack;
import java.util.*;

public class ColonyNode extends ColonyNodeView 
{
   int food, pheromone, scouts, balas, soldiers, foragers, ants, antID, 
       scoutID, foragerID, soldierID, balaID, queenAlive = 1;
   
   //Declares array list to store history of x coordinates
   List<Scout> scoutNodeList = new ArrayList<Scout>();
   List<Forager> foragerNodeList = new ArrayList<Forager>();
   List<Soldier> soldierNodeList = new ArrayList<Soldier>();
   List<Bala> balaNodeList = new ArrayList<Bala>();
   
   //Decalres integers to store sizes of list 
   int balaSize, scoutSize, foragerSize, soldierSize;
   
   //Declares ant types 
   Scout scout, scoutAnt;
   Soldier soldier, soldierAnt;
   Forager forager, foragerAnt;
   Bala bala, balaAnt;
   Queen queen;
   
   //Gets queen
   public Queen getQueenRef()
   {
      return queen;
   }
   
   //Sets queen
   public void setQueenRef(Queen queen)
   {
      this.queen = queen;
   }
   
   //Sets node food amount  
   public void setFood(int food)
   {
      this.food = food;
   }
   
   //Gets node food amount 
   public int getFood()
   {
      return food;
   }
   
   //Sets node pheromone amount 
   public void setPheromone(int pheromone)
   {
      this.pheromone = pheromone;
   }
  
   //Gets node pheromone amount 
   public int getPheromone()
   {
      return pheromone;
   }
  
   //Sets node for bala count 
   public void setBalas(int balas)
   {
      this.balas = balas;
   }
  
   //Gets node bala count 
   public int getBalas()
   {
      return balas;
   }
  
   //Sets node scout count 
   public void setScouts(int scouts)
   {
      this.scouts = scouts;
   }
  
   //Gets node scout count 
   public int getScouts()
   {
      return scouts;
   }
  
   //Sets node soldier count 
   public void setSoldiers(int soldiers)
   {
      this.soldiers = soldiers;
   }
  
   //Gets node soldier count 
   public int getSoldiers()
   {
      return soldiers;
   }
  
   //Sets node forager count 
   public void setForagers(int foragers)
   {
      this.foragers = foragers;
   }
  
   //Get node forager count 
   public int getForagers()
   {
      return foragers;
   }
  
   //Adds bala ant to bala list 
   public void addBala(Bala ant)
   {
      balaNodeList.add(ant);
   }
   
   //Gets bala from bala list 
   public Bala getBala()
   {
      balaAnt = balaNodeList.get(balaNodeList.size()-1);
      return balaAnt;
   }
   
   
   //Removes bala ant from bala list
   public void removeBala(int id)
   {
      //Stores size of scout list 
      balaSize = balaNodeList.size();
      
      if (balaSize > 0)
      {
         try
         {
            //Searches through arraylist to remove scout ant 
            for (int x = 0; x < balaSize; ++x)
            {
               //Stores scout in list 
               bala = balaNodeList.get(x);
               
               //Stores ant ID
               balaID = bala.getAntID();
               
               //Compares scout ID with input ID 
               if (balaID == id)
               {
                  balaNodeList.remove(x);
               }
            }
         } catch (IndexOutOfBoundsException e)
         {
            return;
         }
      }
   }
   
   //Checks if bala list contains ants 
   public boolean checkBala()
   {
      if (balaNodeList.size() > 0)
      {
         return true;
      } else 
      {
         return false;
      }
   }
   
   //Gets bala list size 
   public int getBalaSize()
   {
      balaSize = balaNodeList.size();
      return balaSize;
   }
   
   //Adds soldier to soldier list 
   public void addSoldier(Soldier ant)
   {
      soldierNodeList.add(ant);
   }
   
   //Gets soldier from soldier list 
   public Soldier getSoldier()
   {
      soldierAnt = soldierNodeList.get(soldierNodeList.size()-1);
      return soldierAnt;
   }
   
   //Removes soldier ant from stoldier list 
   public void removeSoldier(int id)
   {
      //Stores size of scout list 
      soldierSize = soldierNodeList.size();
      
      if (soldierSize > 0)
      {
         try
         {
            //Searches through arraylist to remove scout ant 
            for (int x = 0; x < soldierSize; ++x)
            {
               //Stores scout in list 
               soldier = soldierNodeList.get(x);
               
               //Stores ant ID
               soldierID = soldier.getAntID();
               
               //Compares scout ID with input ID 
               if (soldierID == id)
               {
                  soldierNodeList.remove(x);
               }
            }
         } catch (IndexOutOfBoundsException e)
         {
            return;
         }
      }
   }
   
   //Checks if soldier list contains ants 
   public boolean checkSoldier()
   {
      if (soldierNodeList.size() > 0)
      {
         return true;
      } else
      {
         return false;
      }
   }
   
   //Gets soldier list size 
   public int getSoldierSize()
   {
      soldierSize = soldierNodeList.size();
      return soldierSize;
   }
   
   //Adds forager ant to forager list 
   public void addForager(Forager ant)
   {
      foragerNodeList.add(ant);
   }
   
   //Gets forager from forager list 
   public Forager getForager()
   {
      foragerAnt = foragerNodeList.get(foragerNodeList.size()-1);
      return foragerAnt;
   }
   
   //Removes forager ant from forager list 
   public void removeForager(int id)
   {
      //Stores size of scout list 
      foragerSize = scoutNodeList.size();
      
      if (foragerSize > 0)
      {
         try
         {
            //Searches through arraylist to remove scout ant 
            for (int x = 0; x < foragerSize; ++x)
            {
               //Stores scout in list 
               forager = foragerNodeList.get(x);
               
               //Stores ant ID
               foragerID = forager.getAntID();
               
               //Compares scout ID with input ID 
               if (foragerID == id)
               {
                  foragerNodeList.remove(x);
               }
            }
         } catch (IndexOutOfBoundsException e)
         {
            return;
         }
      }
   }
   
   //Checks if forager list contains ants 
   public boolean checkForager()
   {
      if (foragerNodeList.size() > 0)
      {
         return true;
      } else 
      {
         return false;
      }
   }
   
   //Gets forager list size 
   public int getForagerSize()
   {
      foragerSize = foragerNodeList.size();
      return foragerSize;
   }
   
   //Adds scout ant to scout list 
   public void addScout(Scout ant)
   {
      scoutNodeList.add(ant);
   }
   
   //Gets scout from scout list 
   public Scout getScout()
   {
      scoutAnt = scoutNodeList.get(scoutNodeList.size()-1);
      return scoutAnt;
   }
   
   //Removes scout ant from scout list  
   public void removeScout(int id)
   {
      //Stores size of scout list 
      scoutSize = scoutNodeList.size();
      
      if (scoutSize > 0)
      {
         try 
         {
            //Searches through arraylist to remove scout ant 
            for (int x = 0; x < scoutSize; ++x)
            {
               //Stores scout in list 
               scout = scoutNodeList.get(x);
               
               //Stores ant ID
               scoutID = scout.getAntID();
               
               //Compares scout ID with input ID 
               if (scoutID == id)
               {
                  scoutNodeList.remove(x);
               }
            }
         } catch (IndexOutOfBoundsException e)
         {
            return;
         }
      }
   }
   
   //Checks if scout list contains ants 
   public boolean checkScout()
   {
      if (scoutNodeList.size() > 0)
      {
         return true;
      } else
      {
         return false;
      }
   }
   
   //Gets scout list size 
   public int getScoutList()
   {
      scoutSize = scoutNodeList.size();
      return scoutSize;
   }
}