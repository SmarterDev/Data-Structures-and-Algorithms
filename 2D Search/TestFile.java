//Ryan Leonard
//Test File 

public class TestFile
{
   public static void main(String[] args)
   {
    
    //Declares variables
    int comparisons;
    
    //Declares 2d array
    String[][] array = {{"john", "ryan", "ryan", "sammy", "sammy", "ant", "a", "b", "c", "d", "e"},
                         {"john", "ryan" , "kat", "a", "b", "c", "d", "e", "ryan"},
                         {"john", "ryan" , "kat", "mallary", "a", "b", "c","ryan", "d", "e"},
                         {"john", "ryan", "ryan" , "kat", "mallary", "sammy", "a", "b", "c", "d", "e"},
                         {"john", "ryan" , "kat", "mallary", "sammy", "ant", "ryan", "a", "b", "c", "d", "e"}}; 
    
    
    //Declares 2d array
    /*String[][] array = {{"john", "ryan", "sammy", "ryan"},
                         {"john", "ryan" , "sammy", "a"},
                         {"john", "ryan" , "sammy", "mallary"},
                         {"john", "ryan", "sammy", "ryan" },
                         {"john", "ryan" , "sammy", "mallary"}};
    */
    //Creates object to call findCommonElements method
    CommonElements object = new CommonElements();
    
    //Calls for comparisons and size of answer array 
    comparisons = object.getComparisons();
    
    //Declares array to hold return values 
    Comparable[] answers;
    
    //Calls findCommonElements method 
    answers = object.findCommonElements(array);
    
    //Displays answers on screen
    for (int x = 0; x < answers.length; ++x)
    {
      System.out.print(answers[x] + ", ");
    }
   }
}