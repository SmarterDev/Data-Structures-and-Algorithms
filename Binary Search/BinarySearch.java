//Ryan Leonard
//Module3 Homework
//9/16/16

public class BinarySearch 
{
   //Method to search through array for insertion point
   public static int binarySearch(Comparable[] objArray, Comparable searchObj)
   {
      int low = 0;
      int high = objArray.length - 1;
      int mid = 0; 
      
      if (objArray.length > 0)
      {
        //Compares search object to initial low value
        if (objArray[low].compareTo(searchObj) >= 0)
        {
          return low;
        }
        
        //Compares search object to intial high value
        if (objArray[high].compareTo(searchObj) < 0)
        {
          return high;
        }
        
        //Searches through array for insertion point
        while (low <= high)
        {
          mid = (low + high) / 2;
          
          if (objArray[mid].compareTo(searchObj) < 0)
          {
            low = mid + 1;
          }
          else if (objArray[mid].compareTo(searchObj) > 0)
          {
            high = mid - 1;
          }
          else 
          {
            return mid;
          }
        } 
      } else 
      {
         return 0;
      }
      
      return mid; 
   }
}