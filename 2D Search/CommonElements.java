//Ryan Leonard
//Module 4 Homework
//9/21/16

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommonElements
{
  //Declares variables 
  int arraySize, returnComparisons, returnSize, answerSize, amount, totalComparisons, comparisons, length,
  comparisonSum = 0, increment = -1, queryRow = 0, columns = 0, count = 0, counter = 0, lastArraysize = 0;
  
  boolean stored = false;
  
  //Declares comparable
  Comparable result, answerHere;
  
  //Declares comparable array 
  Comparable[] answers;
  
  //Declares arrays 
  int[] answer = new int[2];
  
  //Declares arraylist to store comparisons each pass
  List<Integer> comparisonList = new ArrayList<Integer>();
      
  //Declares arraylist to store matches
  List<Comparable> answerList = new ArrayList<Comparable>();
  
  public CommonElements()
  {
     totalComparisons = 0;
     answerSize = 0;
  }
  
  public Comparable[] findCommonElements(Comparable[][] collections)
  { 
    
    if (collections.length == 1)
    {
      System.out.println("Need more than 1 array to find duplicate values");
      System.exit(0);
    }else 
    {
       
       //Stores total number of rows in 2d array
       int rows = collections.length;
         
       //Declares array to store length of each row 
       Integer[] columnArray = new Integer[rows];
       
       //Checks to see if array is empty 
       if (collections.length == 0)
       {
          System.out.print("The array set is empty. No matches found.");
          System.exit(0);
       }
        
       //Stores length of each row into array
       for (int x = 0; x < rows; x++)
       {
         //Stores number of columns for each row into array
         columnArray[x] = collections[x].length;
       }   
       
       //Sorts each row of array
       for (int x = 0; x < rows; ++x)
       {
         //Utilizes insertion sort to sort array 
         insertionSort(collections[x]);
         
         //Stores collections values 
         for (int y = 0; y < collections[x].length; ++y)
         {
            answerHere = collections[x][y];
         }
       }
       
       //=================================================
       //Finds smallest row to store as query array 
       
       //Stores first value of column array
       int smallRow = columnArray[0];
   
       //Iterates through each row 
       for (int x = 0; x < rows; ++x) 
       {
         //Checks to see if row length is less than current smallest 
         if (columnArray[x].compareTo(smallRow) < 0) 
         { 
           //If smallest, stores number of elements in row 
           smallRow = columnArray[x];
           
           //Stores row for query 
           queryRow = x;
           
           //Sets boolean to indicate row was stored
           stored = true;
           
         } else if (x == rows - 1 && stored == false)
         {
            queryRow = 0;
         }
       }
       
       //Now begin process of finding matches 
       for (int x = 0; x < smallRow; ++x)
       {
          counter = 0;
          
          for (int y = 0; y < rows; ++y)
          {
             //If on query row, skip 
             if (y == queryRow)
             {
                continue;
             }
             
             //Stores answer returned from binary search method           
             answer = binarySearch(collections[y], collections[queryRow][x]);
             
             //Stores comparisons performed so far
             comparisons = answer[1];
             
             //Adds amount to list 
             comparisonList.add(comparisons);
             
             //Item found 
             if (answer[0] == 1)
             {
                //Increments counter 
                ++counter;
             }
             
             //Item not found 
             else if (answer[0] == 0)
             {
                continue;
             }
             
             //Adds item to arraylist 
             if (counter == (rows-1) && y == (rows - 1))
             {
                //Stores match 
                answerList.add(collections[queryRow][x]);
             }
          }
       }
       
       //Adds up number of comparisons made total
       for (int x = 0; x < comparisonList.size(); ++x)
       {
          amount = comparisonList.get(x);
          comparisonSum += amount;
       }
       
       System.out.println("Total Comparisons: " + comparisonSum);
       this.setComparisons(comparisonSum);
       
       //Stores size of answer list 
       arraySize = answerList.size();
       
       //Declares new array to store answers from arraylist
       answers = new Comparable[arraySize];
       
       //Stores answers from arraylist to array
       for (int x = 0; x < arraySize; ++x)
       {
          result = answerList.get(x);
          answers[x] = result;
       }
     }
     return answers;
   } 
   
   //Stores number of comparisons
   public void setComparisons(int comparisons)
   {
      this.totalComparisons = comparisons;
   }
   
   //Gets total number of comparisons performed
   public int getComparisons()
   {
     return comparisons;
   }
    
   //Method to sort each row of array using insertion sort algorithm         
   public Comparable[] insertionSort(Comparable[] objArray)
   {
      for (int i = 1; i < objArray.length; i++) 
      {
         Comparable firstUnsorted = objArray[i];
         int index = i - 1; 
            
         while (index >= 0 && firstUnsorted.compareTo(objArray[index]) < 0)
         { 
            objArray[index + 1] = objArray[index]; 
            index--; 
         } 
          
         objArray[index + 1] = firstUnsorted; 
      }
      
      return objArray;
   }
   
   //Method to search through array for insertion point
   public int[] binarySearch(Comparable[] objArray, Comparable searchObj)
   {
      int low = 0, high = objArray.length - 1, mid = 0, comparisons = 0;
      
      int[] array = new int[2];
      
      //If array has items
      if (objArray.length > 0)
      { 
        //Searches through array for insertion point
        while (low <= high)
        {
          mid = (low + high) / 2;
          comparisons = comparisons + 2;
          
          if (objArray[mid].compareTo(searchObj) < 0)
          {
            low = mid + 1;
            --comparisons;
          }
          else if (objArray[mid].compareTo(searchObj) > 0)
          {
            high = mid - 1;
          }
          
          //Item found 
          else if (objArray[mid].compareTo(searchObj) == 0) 
          {
            ++comparisons;
            array[0] = 1;
            array[1] = comparisons;
            return array;
          }
        }
        
        //Item not found  
      } else 
      {
         array[0] = 0;
         array[1] = 0;
         return array;
      } 
      
      return array;
   }
}