import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * DirectoryLister class.
 * This class allows the user to recursively display the contents of a
 * selected directory in the file system.
 */
public class DirectoryLister
{
	
	// -----------------------------------------------------------------------
	// Attributes
	// -----------------------------------------------------------------------
	
	/** GUI used to display results */
	private GUI gui; 
	
	/** base path of directory to be traversed */
	private String basePath;

	
	// -----------------------------------------------------------------------
	// Constructors
	// -----------------------------------------------------------------------
	
	/**
	 *	Create a new DirectoryLister that uses the specified GUI.
	 */
	public DirectoryLister(GUI gui)
	{
		this.gui = gui;
	}
	
	
	// -----------------------------------------------------------------------
	// Methods
	// -----------------------------------------------------------------------
	
	/**
	 *	Allow user to select a directory for traversal.
	 */
	public void selectDirectory()
	{
		// clear results of any previous traversal
		gui.resetGUI();
		
		// allow user to select a directory from the file system
		basePath = gui.getAbsoluteDirectoryPath();
		
		// update the address label on the GUI
		gui.setAddressLabelText(basePath);
		
		// traverse the selected directory, and display the contents
		showDirectoryContents(basePath);
	}
	
	
	/**
	 *	Show the directory listing.
	 *	An error message is displayed if basePath does not represent a valid directory.
	 * 
	 *	@param	basePath		the absolute path of a directory in the file system.
	 */
	public void showDirectoryContents(String basePath)
	{ 
     
      try {   
         
         //Throws exception if basepath is null
         if (basePath == null)
            throw new NullPointerException();
         
         //Creates file object    
         File dir = new File(basePath);
         
         //Calls enumerate method on chosen directory
         enumerateDirectory(dir);
         
      } catch (NullPointerException e){
         JOptionPane.showMessageDialog(null, "Error Encountered", "Error",
                                       JOptionPane.ERROR_MESSAGE);                            
      } 
	}
	
	
	/**
	 *	Recursive method to enumerate the contents of a directory.
	 *
	 *	@param	f	directory to enumerate
	 */
	private void enumerateDirectory(File f)
	{
		/*use recursion to display contents of directory*/
      
      //Creates file array to store file/folder information
      File[] files = f.listFiles();
      
         for (File file : files) {
      		if (file.isDirectory()) {
               
               //Stores file path 
      		   String filePath = file.getPath();
               
               //Stores size 
               String fileLength = "";
               
               //Stores type, file or folder
               String type = "Folder";
               
               //Stores date last modified for file 
               long modified = file.lastModified();
               
               //Converts long integer into formatted date
               String dateText = formattedDateString(modified);
               
               //Updates table with information
               gui.updateListing(filePath, fileLength, type, dateText);
               
               //Recursively enumerates directory using file input 
               enumerateDirectory(file);
               
      		} else {
      			
               //Stores file path
               String filePath = file.getPath();
   
               //Stores file length
               long length = file.length();
    
					//Converts bytes to kilobytes
					String convLength = getSizeString(length);
   
               //Stores type, of file or folder
               String type = "File";
               
               //Stores date last modified for file 
               long modified = file.lastModified();
               
               //Converts long integer into formatted date
               String dateText = formattedDateString(modified);
               
               //Updates table with information
               gui.updateListing(filePath, convLength, type, dateText);
      		}
         }       
    }
	
	/**
	 *	Convert a size from bytes to kilobytes, rounded down, and return an appropriate descriptive string.
	 *	Example: 123456 bytes returns 120 KB
	 *
	 *	@param size		size, in bytes
	 *	@return			size, in kilobytes (rounded down) + "KB"
	 */
	private String getSizeString(long size)
	{
		long kbSize = size / 1024;
		
		return "" + kbSize + " KB";
	}
	
	
	/**
	 *	Return a numeric time value as a formatted date string.
	 *
	 *	@param		time	numeric time value in milliseconds
	 *	@return		formatted string using the format "MM/dd/yyyy hh:mm:ss aaa"
	 *				Example: 01/15/2010 12:37:52 PM
	 */
	private String formattedDateString(long time)
	{
		// create Date object from numeric time
		Date d = new Date(time);
		
		// create formatter
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss aaa");

		// return formatted date string
		return sdf.format(d);
	}
}