import java.util.*;
import javax.swing.JOptionPane;

public class Simulation implements SimulationEventListener
{
   //Declares int variables 
   int ants, turns, days, direction, foragerDirection, newForagerDirection, newSoldierCount,
       balaCount, life, returnPath, balaX, balaY, newBalaX, newBalaY, antTotal, soldierDirection,
       scoutX, scoutY, newSoldierX, newSoldierY, hatchType, soldierCount, pheromone, hatchBala, soldierAmount, 
       balaAmount, scoutAmount, newScoutX, newScoutY, soldierX, soldierY, queenFood, foragerCount,
       scoutCount,newBalaCount, scoutListSize, foragerListSize, soldierListSize, balaListSize, scoutID,
       foragerAmount, foragerID, soldierID, balaID, queenCounter = 0, Id = 1, foodAmount = 0,
       increment = 0, newPheromone = 1;
   
   String daysConverted, turnsConverted, updateTime;
   
   //Declares boolean variables
   boolean outcomeBala, outcomeSoldier, error, stepping;
   
   //Sets up colony in 27x27 grid
   ColonyView view = new ColonyView(27,27);
    
   //Declares 2d array of ColonyNode objects
   ColonyNode[][] colonyGrid = new ColonyNode[27][27];
   
   Thread thread;
   
   //Creates variables of different ant types 
   Scout scout, scoutRemoval, scoutListAnt;
   Soldier soldier, soldierRemoval, soldierListAnt;
   Forager forager, foragerRemoval, foragerListAnt;
   Bala bala, balaRemoval, balaListAnt;
   Queen queen;
   
   //Creates arraylists to store different types of ants 
   List<Bala> balaList = new ArrayList<Bala>();
   List<Scout> scoutList = new ArrayList<Scout>();
   List<Soldier> soldierList = new ArrayList<Soldier>();
   List<Forager> foragerList = new ArrayList<Forager>();
   List<Queen> queenList = new ArrayList<Queen>();
   
   //Creates AntSimGUI object
   AntSimGUI gui = new AntSimGUI();
   
   //Simulation constructor
   public Simulation()
   {
      initColony(gui);
      gui.addSimulationEventListener(this);
   }

   //Runs simulation events based on input 
   public void simulationEventOccurred(SimulationEvent event) 
   {
      //If normal setup button pressed, execute normal setup method 
      if (event.getEventType() == SimulationEvent.NORMAL_SETUP_EVENT) 
      {    
         //Creates normal setup
         normalSetup(colonyGrid);
      }
   
      //If run button pressed, runs simulation continuously 
      if (event.getEventType() == SimulationEvent.RUN_EVENT) 
      {
             thread = new Thread() 
             {
               public void run() 
               {
                 runSim(colonyGrid, view, gui);
               }
             };
         thread.start();
      }
   
      //If step button pressed, runs simulation step by step 
      if (event.getEventType() == SimulationEvent.STEP_EVENT) 
      {
             thread = new Thread() 
             {
               public void run() 
               {
                 stepping = true;
                 runSim(colonyGrid, view, gui);
               }
             };
         thread.start();
      }
   }
   
   //Method to initialize colony 
   public void initColony(AntSimGUI gui)
   {
      //Declares attributes 
      String iD, xLocation, yLocation;
      int foodAmount, randNum;
      ColonyNodeView colonyNode;
       
       //Sets up grid of colonyNodeViews
       for (int x = 0; x < 27; ++x)
       {
         for (int y = 0; y < 27; ++y)
         {   
         
            //Sets x and y variables to strings
            xLocation = Integer.toString(x);
            yLocation = Integer.toString(y);
            
            //Stores x and y value strings into one
            iD = xLocation + ", " + yLocation;
            
            //Creates ColonyNodeView instance
            ColonyNode node = new ColonyNode();
            
            //Adds node to colony view 
            view.addColonyNodeView(node, x, y);
            
            //Sets ID of node
            node.setID(iD);
            
            //Sets number of soldiers ants
            node.setSoldierCount(0);
       
            //Sets number of forager ants
            node.setForagerCount(0);
       
            //Sets number of scout ants
            node.setScoutCount(0);
       
            //Sets number of bala ants
            node.setBalaCount(0);
       
            //Sets level of food
            Random foodGenerator = new Random();
            randNum = foodGenerator.nextInt(4);
            
            //Creates random amount of food from 500 - 1000
            if (randNum == 1)
            {
               //25% chance of node containing food 
               foodAmount = foodGenerator.nextInt(501) + 500;
   
               //Sets food amounts for node 
               node.setFoodAmount(foodAmount);
               node.setFood(foodAmount);
            } else
            {
               //75% chance of node containing no food 
               node.setFoodAmount(0);
               node.setFood(0);
            }
       
            //Sets pheromone count 
            node.setPheromoneLevel(0);
            node.setPheromone(0);
            
            //Stores node into 2D array
            colonyGrid[x][y] = node;
            
             //If queen node, set up queen 
             if (x == 13 && y ==13)
             {  
                //Generates queen ant 
                Queen queen = new Queen();
                
                //Sets queen at node 
                colonyGrid[x][y].setQueenRef(queen);
                
                //Sets queen ant ID to 0
                queen.setAntID(0);
                
                //Adds queen to queen list
                queenList.add(queen);
                
                //Queen node ID
                String queenID = "13, 13";
                
                //Sets queen node ID 
                colonyGrid[x][y].setID(queenID);
                
                //Sets node as queen node
                colonyGrid[x][y].setQueen(true);
                
                //Show icon of queen ant 
                colonyGrid[x][y].showQueenIcon();
                
                //Show icon of scout ant 
                colonyGrid[x][y].showScoutIcon();
                
                //Show icon of forager ant 
                colonyGrid[x][y].showForagerIcon();
                
                //Show icon of soldier ant 
                colonyGrid[x][y].showSoldierIcon();
                
                //Sets number of soldiers ants
                colonyGrid[x][y].setSoldierCount(10);
                colonyGrid[x][y].setSoldiers(10);
                
                //Sets number of forager ants
                colonyGrid[x][y].setForagerCount(50);
                colonyGrid[x][y].setForagers(50);
                
                //Sets number of scout ants
                colonyGrid[x][y].setScoutCount(4);
                colonyGrid[x][y].setScouts(4);
                
                //Sets number of bala ants
                colonyGrid[x][y].setBalaCount(0);
                colonyGrid[x][y].setBalas(0);
                
                //Sets level of food
                colonyGrid[x][y].setFoodAmount(1000);
                colonyGrid[x][x].setFood(1000);
                
                //Sets pheromone count 
                colonyGrid[x][y].setPheromoneLevel(0);
                colonyGrid[x][x].setPheromone(0);
             }
         }
       }   
      
      //Adds scout ants to queen node 
      for (int x = 0; x < 4; ++x)
      {
         //Creates scout ant
         Scout scout = new Scout();
               
         //Sets ant ID
         scout.setAntID(Id);
         
         //Adds scout ant to list 
         scoutList.add(scout);
         
         //Increments ID
         ++Id;
      }
      
      //Adds forager ants to queen node 
      for (int x = 0; x < 50; ++x)
      {
         //Creates forager ant
         Forager forager = new Forager();
               
         //Sets ant ID
         forager.setAntID(Id);
         
         //Adds forager ant to list 
         foragerList.add(forager);
         
         //Increments ID
         ++Id;
      }
      
      //Adds soldier ants to queen node 
      for (int x = 0; x < 10; ++x)
      {
         //Creates forager ant
         Soldier soldier = new Soldier();
               
         //Sets ant ID
         soldier.setAntID(Id);
         
         //Adds forager ant to list 
         soldierList.add(soldier);
         
         //Increments ID
         ++Id;
      }
      
      //Uses colonyView to set GUI
      gui.initGUI(view);
   } 
   
   //Main method for running simulation 
   public void runSim(ColonyNode[][] colonyGrid, ColonyView view, AntSimGUI gui)
   {
      //Creates loop to continuously execute simulation 
      do 
      {    
         /*****************************************
      	*	Queen ant instructions start here 
      	*/
         
         //Reduces queen lifespan by 1 each turn 
         queen = colonyGrid[13][13].getQueenRef();
         
         //Reduces queen life 
         queen.reduceLife();
         
         //If queens life span is reduced to 0, simulation over
         if (queen.getLife() == 0)
         {
            JOptionPane.showMessageDialog (null, "The Queen has died of old age. The Simulation is over.", "Simulation Ended", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
         }
         
         //Queen eats 1 food each day 
         queen.eat(colonyGrid);
         
         //Creates random bala generator 
         hatchBala = queen.balaHatch();
         
         //Sets queen 
         colonyGrid[13][13].setQueenRef(queen);
         
         //Increments turn counter each pass through 
         turns++;
         
         //Increments simulation while counter 
         increment++;
         
         //Converts days integer to string 
         daysConverted = Integer.toString(days);
         
         //Converts turn integer to string 
         turnsConverted = Integer.toString(turns);
         
         //Concatenates strings 
         updateTime = "Day: " + daysConverted + " Turn: " + turnsConverted;
         
         //Sets time of simulation 
         gui.setTime(updateTime);
         
         //Counts new day every ten turns 
         if (turns % 10 == 0)
         {
            //Increments new day every 10 turns 
            days++;
            
            //Creates random ant generator
            hatchType = queen.hatch();
            
            //Decreases pheromone level by half each day
            //Sets ant icons on each node  
            for (int x = 0; x < 27; ++x)
            {
               for (int y = 0; y < 27; ++y)
               {  
                  //If pheromone level equal to 0, skip node
                  if (colonyGrid[x][y].getPheromone() == 0)
                  {
                     continue;
                  } else 
                  {  
                     //Gets amount of pheromone from each node
                     pheromone = colonyGrid[x][y].getPheromone();
                     
                     //Cuts pheromone amount in half 
                     newPheromone = pheromone / 2;
                     
                     //Sets new pheromone amount for node
                     colonyGrid[x][y].setPheromone(newPheromone);
                     
                     //Sets new pheromone level for node 
                     colonyGrid[x][y].setPheromoneLevel(newPheromone);
                  }
               }
            }
            
            //25% chance of scout each spawn 
            if (hatchType == 1)
            {
               //Creates scout ant 
               Scout scout = new Scout();
               
               //Sets ant ID
               scout.setAntID(Id);
               
               //Adds scout ant to scout arraylist
               scoutList.add(scout);
               
               //Increments ant ID
               Id++;
            }
         
            //50% chance of forager each spawn 
            else if (hatchType == 2 || hatchType == 3)
            {
               //Creates forager ant
               Forager forager = new Forager();
               
               //Sets ant ID
               forager.setAntID(Id);
               
               //Adds forager ant to forager list
               foragerList.add(forager);
               
               //Increments ant ID
               Id++;
            }
         
            //25% chance of soldier each spawn 
            else if (hatchType == 4)
            {
               //Creates soldier ant
               Soldier soldier = new Soldier();
               
               //Sets ant ID
               soldier.setAntID(Id);
               
               //Adds soldier ant to soldier list
               soldierList.add(soldier);
               
               //Increments ant ID
               Id++;
            }
         }
                  
         //3% chance of bala spawn each turn 
         if (hatchBala == 1 || hatchBala == 2 || hatchBala == 3)
         {
            //Creates bala ant 
            Bala bala = new Bala();
            
            //Sets ant ID
            bala.setAntID(Id);
            
            //Adds bala ant to bala list 
            balaList.add(bala);
            
            //Increments ant ID
            Id++;
         }
         
         /********************************************
      	*	Scout ant instructions start here 
      	*/
         
         //Checks to see scout list contains ants
         scoutListSize = scoutList.size();
         
         //If scout list contains ants, execute scout instructions
         if (scoutListSize > 0)
         {
            //Creates loop to interate through scout list
            for (int x = 0; x < scoutListSize; ++x)
            {
               //Stores current item in scout list as ant
               scout = scoutList.get(x);
               
               //Updates scout lifespans 
               scout.reduceLife();
               
               //If scout life 0, remove ant from simulation 
               if (scout.getLife() == 0)
               {
                  scoutList.remove(x);
                  --x;
                  continue;
               }
                
               //Gets direction to move 
               direction = scout.getRandomDirection(scout);
               
               //Gets current x and y coordinates
               scoutX = scout.getX();
               scoutY = scout.getY();
               
               //Moves scout ant
               scout.move(scout, scoutX, scoutY, direction);
               
               //Gets new x and y coordinates
               newScoutX = scout.getX();
               newScoutY = scout.getY();
               
               //Gets scout ID  
               scoutID = scout.getAntID();
               
               //Removes scout ant from previous node list 
               colonyGrid[scoutX][scoutY].removeScout(scoutID);
               
               //Adds scout to new colony node scout list 
               colonyGrid[newScoutX][newScoutY].addScout(scout);
               
               //Hides scout icon on previous node
               colonyGrid[scoutX][scoutY].hideScoutIcon();
               
               //Gets scout count on previous node
               scoutCount = colonyGrid[scoutX][scoutY].getScouts();
               
               //Reduces scout count on previous node by 1
               if (scoutCount > 0)
               {
                  scoutCount--;
               }
               
               //Updates scout count on previous node 
               colonyGrid[scoutX][scoutY].setScouts(scoutCount);
               colonyGrid[scoutX][scoutY].setScoutCount(scoutCount);
               
               //Shows scout icon on new node
               colonyGrid[newScoutX][newScoutY].showScoutIcon();
               
               //Gets scout count on new node
               scoutCount = colonyGrid[newScoutX][newScoutY].getScouts();
               
               //Increases scout count on new node by 1
               scoutCount++;
               colonyGrid[newScoutX][newScoutY].setScouts(scoutCount);
               colonyGrid[newScoutX][newScoutY].setScoutCount(scoutCount);
               
               //Shows node 
               colonyGrid[newScoutX][newScoutY].showNode();
            }
         }
         
         /********************************************
      	*	Forager ant instructions start here 
      	*/
         
         //Checks to see forager list contains ants
         foragerListSize = foragerList.size();
         
         //If forager list contains ants, execute forager instructions
         if (foragerListSize > 0)
         {
            //Creates loop to interate through scout list
            for (int y = 0; y < foragerListSize; ++y)
            {
               //Stores current item in scout list as ant
               forager = foragerList.get(y);
               
               //Updates forager lifespan
               forager.reduceLife();
               
               //If forager life 0, remove ant from simulation 
               if (forager.getLife() == 0)
               {
                  foragerList.remove(y);
                  --y;
                  continue;
               }
               
               //Gets x and y coordinates
               int foragerX = forager.getX();
               int foragerY = forager.getY();
               
               //Checks to see if ant is carrying food
               if (forager.getCarryingFood() == true)
               {
                  //Enters return to nest mode
                  foragerDirection = forager.returnNest();
                  
                  //If stack is empty, ant is at queen node
                  if (foragerDirection == 0)
                  {
                     //Gets amount of food in queen node
                     queenFood = colonyGrid[foragerX][foragerY].getFood();
                     
                     //Increments amount of food by 1 
                     queenFood++;
                     
                     //Sets new amount of food in node 
                     colonyGrid[foragerX][foragerY].setFood(queenFood);
                     colonyGrid[foragerX][foragerY].setFoodAmount(queenFood);
                     
                     //Drops pheromone in current node before moving to next
                     if (foragerX == 13 && foragerY == 13)
                     {
                        //Skip queen node when dropping pheromone
                     } else if (colonyGrid[foragerX][foragerY].getFood() < 1000)
                     {
                        forager.dropPheromone(colonyGrid, foragerX, foragerY);
                     }
                     
                     //Changes carrying food state back to false
                     forager.setCarryingFood(0);
                  }
               } else
               { 
                  //If ant is not carrying food, search for node with highest pheromone level 
                  //Gets adjacent nodes pheromone levels
                  foragerDirection = forager.getAdjacentPheromone(colonyGrid, forager, foragerX, foragerY);
               
                  //Converts direction traveled into opposite direction for return path
                  returnPath = forager.convertDirection(foragerDirection);
                  
                  //Adds return path direction to list
                  forager.addMovementHistory(returnPath);
                  
                  //Adds x and y coordinates to history 
                  forager.setXcoor(foragerX);
                  forager.setYcoor(foragerY);
                  
                  //Increments movement counter
                  //Ensures recent nodes are not visited (last 3)
                  forager.addMovement();
               }  
               
               //Moves forager ant 
               forager.move(forager, foragerX, foragerY, foragerDirection);    
                        
               //Gets new x and y coordinates
               int newForagerX = forager.getX();
               int newForagerY = forager.getY();
               
               //Gets forager ID  
               foragerID = forager.getAntID();
               
               try 
               {
                  //Removes forager from previous node list 
                  colonyGrid[foragerX][foragerY].removeForager(foragerID);
                  
                  //Adds forager to new colony node list 
                  colonyGrid[newForagerX][newForagerY].addForager(forager);
               } catch (IndexOutOfBoundsException e)
               {
                  
               }
               
               //If forager not carrying food, get food amount from node         
               if (forager.getCarryingFood() == false && !(newForagerX == 13 && newForagerY == 13))
               {
                  //Stores amount of food returned from node
                  foodAmount = colonyGrid[newForagerX][newForagerY].getFood();
                  
                  if (foodAmount > 0)
                  {
                     //Takes 1 unit of food away
                     foodAmount--;
                     
                     //Sets new amount of food in node
                     colonyGrid[newForagerX][newForagerY].setFood(foodAmount);
                     colonyGrid[newForagerX][newForagerY].setFoodAmount(foodAmount);
                     
                     //Sets carrying food attribute to true
                     forager.setCarryingFood(1);
                  }
               }
               
               //Hides forager icon 
               colonyGrid[foragerX][foragerY].hideForagerIcon();
               
               //Gets forager count on previous node
               foragerCount = colonyGrid[foragerX][foragerY].getForagers();
               
               //Reduces forager count in previous node by 1
               if (foragerCount > 0)
               {
                  foragerCount--;
               }
               
               //Updates forager count on previous node 
               colonyGrid[foragerX][foragerY].setForagers(foragerCount);
               colonyGrid[foragerX][foragerY].setForagerCount(foragerCount);
               
               //Shows forager icon
               colonyGrid[newForagerX][newForagerY].showForagerIcon();
               
               //Gets forager count on new node
               foragerCount = colonyGrid[newForagerX][newForagerY].getForagers();
               
               //Increases forager count by 1
               foragerCount++;
               colonyGrid[newForagerX][newForagerY].setForagers(foragerCount);
               colonyGrid[newForagerX][newForagerY].setForagerCount(foragerCount);
            }
         }
         
         /********************************************
      	*	Soldier ant instructions start here  
      	*/
         
         //Checks to see scout list contains ants
         soldierListSize = soldierList.size();
         
         //If forager list contains ants, execute forager instructions
         if (soldierListSize > 0)
         {
            //Creates loop to interate through scout list
            for (int z = 0; z < soldierListSize; ++z)
            {
               //Stores current item in scout list as ant
               soldier = soldierList.get(z);
         
               //Updates soldier lifespan
               soldier.reduceLife();
               
               //If soldier life 0, remove ant from simulation 
               if (soldier.getLife() == 0)
               {
                  soldierList.remove(z);
                  --z;
                  continue;
               }
               
               //Gets new x and y coordinates
               soldierX = soldier.getX();
               soldierY = soldier.getY();
               
               //Gets number of bala ants on current node    
               balaAmount = colonyGrid[soldierX][soldierY].getBalas();
               
               //If bala ants present, attack
               if (balaAmount > 0)
               {
                  //Resets outcome of fight 
                  outcomeSoldier = false;
                  
                  //Calls for attack
                  outcomeSoldier = soldier.attackMode();
                  
                  //If soldier wins attack, proceeds to remove bala from node 
                  if (outcomeSoldier == true)
                  {
                     //Gets bala for removal 
                     balaRemoval = colonyGrid[soldierX][soldierY].getBala();
                     
                     //Gets bala ID
                     balaID = balaRemoval.getAntID();
                     
                     //Removes bala from node list 
                     colonyGrid[soldierX][soldierY].removeBala(balaID);
                     
                     //Removes bala from simulation list 
                     for (int xxx = 0; xxx < balaList.size(); ++xxx)
                     {
                        //Gets bala ant from bala list 
                        balaListAnt = balaList.get(xxx);
                        
                        //Gets bala ant ID with bala ant removal 
                        if (balaListAnt.getAntID() == balaID)
                        {
                           //Remove bala ant 
                           balaList.remove(xxx);
                        }
                     }
                     
                     //Reduces bala count by 1 
                     --balaAmount;
                     
                     //Updates bala counts on node 
                     colonyGrid[soldierX][soldierY].setBalas(balaAmount);
                     colonyGrid[soldierX][soldierY].setBalaCount(balaAmount);
                  }
               }         
               
               //Gets direction to move 
               soldierDirection = soldier.scoutMode(colonyGrid, soldier, soldierX, soldierY);
               
               //Moves soldier ant
               soldier.move(soldier, soldierX, soldierY, soldierDirection);
               
               //Gets new x and y coordinates
               newSoldierX = soldier.getX();
               newSoldierY = soldier.getY();
               
               //Gets soldier ID  
               soldierID = soldier.getAntID();
               
               //Removes soldier from previous node list 
               colonyGrid[soldierX][soldierY].removeSoldier(soldierID);
               
               //Adds soldier to new colony node list 
               colonyGrid[newSoldierX][newSoldierY].addSoldier(soldier);
               
               //Gets soldier ant count from previous node 
               soldierCount = colonyGrid[soldierX][soldierY].getSoldiers();
               
               //Reduces soldier count in previous node by 1
               if (soldierCount > 0)
               {
                  soldierCount--;
               }
               
               //Updates soldier count from previous node
               colonyGrid[soldierX][soldierY].setSoldiers(soldierCount);
               colonyGrid[soldierX][soldierY].setSoldierCount(soldierCount);
               
               //Gets soldier count from next node 
               newSoldierCount = colonyGrid[newSoldierX][newSoldierY].getSoldiers();
               
               //Increases soldier count at new node 
               newSoldierCount++;
               
               //Updates soldier count at new node
               colonyGrid[newSoldierX][newSoldierY].setSoldiers(newSoldierCount);
               colonyGrid[newSoldierX][newSoldierY].setSoldierCount(newSoldierCount);
               
               //Hides soldier icon on previous node
               colonyGrid[soldierX][soldierY].hideSoldierIcon();
               
               //Shows soldier icon on new node
               colonyGrid[newSoldierX][newSoldierY].showSoldierIcon();
            }
         }   
         
         /*****************************************
      	*	Bala ant instructions start here  
      	*/         
         
         //Checks to see scout list contains ants
         balaListSize = balaList.size();
         
         //If forager list contains ants, execute forager instructions
         if (balaListSize > 0)
         {
            //Creates loop to interate through scout list
            for (int xx = 0; xx < balaListSize; ++xx)
            {
               //Stores current item in scout list as ant
               bala = balaList.get(xx);
         
               //Updates bala lifespan
               bala.reduceLife();
               
               //If bala life 0, remove ant from simulation 
               if (bala.getLife() == 0)
               {
                  balaList.remove(xx);
                  --xx;
                  continue;
               }
               
               //Gets direction to move 
               direction = bala.getRandomDirection(bala);
               
               //Gets new x and y coordinates
               int balaX = bala.getX();
               int balaY = bala.getY();
               
               //Moves bala ant
               bala.move(bala, balaX, balaY, direction);
               
               //Gets new x and y coordinates
               int newBalaX = bala.getX();
               int newBalaY = bala.getY();
               
               //Gets bala ID  
               balaID = bala.getAntID();
               
               //Removes bala from previous node list 
               colonyGrid[balaX][balaY].removeBala(balaID);
               
               //Adds bala to new colony node list 
               colonyGrid[newBalaX][newBalaY].addBala(bala);
               
               //Gets bala ant count from previous node 
               balaCount = colonyGrid[balaX][balaY].getBalas();
               
               //Reduces bala count in previous node by 1
               if (balaCount > 0)
               {
                  balaCount--;
               }
               
               //Updates bala count from previous node moved 
               colonyGrid[balaX][balaY].setBalas(balaCount);
               colonyGrid[balaX][balaY].setBalaCount(balaCount);
               
               //If in current node with ants, fight 
               if (colonyGrid[balaX][balaY].getScouts() > 0 || colonyGrid[balaX][balaY].getForagers() > 0 || colonyGrid[balaX][balaY].getSoldiers() > 0)
               {
                  //Resets outcome of fight 
                  outcomeBala = false;
                  
                  //Bala fights random ant 
                  outcomeBala = bala.fight();
                  
                  if (outcomeBala == true)
                  {
                     //If bala ant in queen node, fight queen
                     if (newBalaX == 13 && newBalaY == 13)
                     {
                        queen.setQueenAlive(1);
                     }
                     
                     //If bala ant in node with scouts, fight scouts 
                     else if (colonyGrid[balaX][balaY].getScouts() > 0)
                     {
                        //Gets scout for removal 
                        scoutRemoval = colonyGrid[balaX][balaY].getScout();
                        
                        //Gets number of scout ants on current node    
                        scoutAmount = colonyGrid[balaX][balaY].getScouts();
                        
                        //Gets scout ID
                        scoutID = scoutRemoval.getAntID();
                        
                        //Removes scout from node list 
                        colonyGrid[balaX][balaY].removeScout(scoutID);
                        
                        //Removes scout from simulation list 
                        for (int xxx = 0; xxx < scoutList.size(); ++xxx)
                        {
                           //Gets scout ant from scout list 
                           scoutListAnt = scoutList.get(xxx);
                           
                           //Gets scout ant ID with scout ant removal 
                           if (scoutListAnt.getAntID() == scoutID)
                           {
                              //Remove scout ant 
                              scoutList.remove(xxx);
                           }
                        }
                        
                        //Reduces scout count by 1 
                        --scoutAmount;
                        
                        //Updates scout counts on node 
                        colonyGrid[balaX][balaY].setScouts(scoutAmount);
                        colonyGrid[balaX][balaY].setScoutCount(scoutAmount);
                     }
                     
                     //If bala ant in node with forager, fight forager
                     else if (colonyGrid[balaX][balaY].getForagers() > 0)
                     {
                        //Gets forager for removal 
                        foragerRemoval = colonyGrid[balaX][balaY].getForager();
                        
                        //Gets number of forager ants on current node    
                        foragerAmount = colonyGrid[balaX][balaY].getForagers();
                        
                        //Gets forager ID
                        foragerID = foragerRemoval.getAntID();
                        
                        //Removes forager from node list 
                        colonyGrid[balaX][balaY].removeForager(foragerID);
                        
                        //Removes forager from simulation list 
                        for (int xxx = 0; xxx < foragerList.size(); ++xxx)
                        {
                           //Gets forager ant from forager list 
                           foragerListAnt = foragerList.get(xxx);
                           
                           //Gets forager ant ID with forager ant removal 
                           if (foragerListAnt.getAntID() == foragerID)
                           {
                              //Remove forager ant 
                              foragerList.remove(xxx);
                           }
                        }
                        
                        //Reduces forager count by 1 
                        --foragerAmount;
                        
                        //Updates forager counts on node 
                        colonyGrid[balaX][balaY].setForagers(foragerAmount);
                        colonyGrid[balaX][balaY].setForagerCount(foragerAmount);
                     }
                     
                     //If bala ant in node with soldier ants, fight soldier 
                     else if (colonyGrid[balaX][balaY].getSoldiers() > 0)
                     {
                        //Gets soldier for removal 
                        soldierRemoval = colonyGrid[balaX][balaY].getSoldier();
                        
                        //Gets number of soldier ants on current node    
                        soldierAmount = colonyGrid[balaX][balaY].getSoldiers();
                        
                        //Gets soldier ID
                        soldierID = soldierRemoval.getAntID();
                        
                        //Removes soldier from node list 
                        colonyGrid[balaX][balaY].removeSoldier(soldierID);
                        
                        //Removes soldier from simulation list 
                        for (int xxx = 0; xxx < soldierList.size(); ++xxx)
                        {
                           //Gets soldier ant from soldier list 
                           soldierListAnt = soldierList.get(xxx);
                           
                           //Gets soldier ant ID with soldier ant removal 
                           if (soldierListAnt.getAntID() == soldierID)
                           {
                              //Remove soldier ant 
                              soldierList.remove(xxx);
                           }
                        }
                        
                        //Reduces soldier count by 1 
                        --soldierAmount;
                        
                        //Updates soldier counts on node 
                        colonyGrid[balaX][balaY].setSoldiers(soldierAmount);
                        colonyGrid[balaX][balaY].setSoldierCount(soldierAmount);
                     }
                  }
               }
               
               //Gets bala count from new node 
               newBalaCount = colonyGrid[newBalaX][newBalaY].getBalas();
               
               //Increases bala count at new node 
               newBalaCount++;
               
               //Updates bala count at new node
               colonyGrid[newBalaX][newBalaY].setBalas(newBalaCount);
               colonyGrid[newBalaX][newBalaY].setBalaCount(newBalaCount);
               
               //Hides bala icon on previous node
               colonyGrid[balaX][balaY].hideBalaIcon();
               
               //Shows bala icon on new node
               colonyGrid[newBalaX][newBalaY].showBalaIcon(); 
            }
         }  

         //Sets icons on each node 
         for (int xx = 0; xx < 27; ++xx)
         {
            for (int yy = 0; yy < 27; ++yy)
            {
               
               //Sets scout icon 
               if (colonyGrid[xx][yy].getScouts() > 0)
               {
                  colonyGrid[xx][yy].showScoutIcon();
               } else
               {
                  colonyGrid[xx][yy].hideScoutIcon();
               }
               
               //Sets forager icons 
               if (colonyGrid[xx][yy].getForagers() > 0)
               {
                  colonyGrid[xx][yy].showForagerIcon();
               } else
               {
                  colonyGrid[xx][yy].hideForagerIcon();
               }
               
               //Sets soldier icons 
               if (colonyGrid[xx][yy].getSoldiers() > 0)
               {
                  colonyGrid[xx][yy].showSoldierIcon();
               } else
               {
                  colonyGrid[xx][yy].hideSoldierIcon();
               }
               
               //Sets bala icons 
               if (colonyGrid[xx][yy].getBalas() > 0)
               {
                  colonyGrid[xx][yy].showBalaIcon();
               } else 
               {
                  colonyGrid[xx][yy].hideBalaIcon();
               }
            }
         }   
         
         /*****************************************
      	*	Ends simulation if queen is dead at end of turn   
      	*/
         
         if (queen.getQueenAlive() == 1)
         {
            JOptionPane.showMessageDialog (null, "The Queen has died from an attack. The Simulation is over.", "Simulation Ended", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
         }
         
         /*****************************************
      	*	Updates GUI view  
      	*/
         
         //Updates view of GUI
         view.validate();
         view.repaint();
         
         //Delays execution of simulation 
         try
         {
           //Delays 1 second if set to 1000
           Thread.sleep(100);     
         }catch(InterruptedException ex){
           //do stuff
         }
         
         //Checks to see if in step mode 
         if (stepping == true)
         {
            
            stepping = false;
            break;
         }
         
      } while (increment != 10000);
   }
   
   //Creates normal setup for colony grid  
   public void normalSetup(ColonyNode[][] colonyGrid)  
   {
      for (int x = 12; x < 15; ++x)
      {
         for (int y = 12; y < 15; ++y)
         {
            colonyGrid[x][y].showNode();
         }
      } 
   }
}