/*
Author: 2020 OOPD lecture slides
Date created: 19/10/2020
Purpose: Read in an array of Strings (one position in the array for each line in the CSV file)
*/
import java.io.*;
/*
	this is adapted code from the lecture 7 slides, I do not claim to have created this completely by myself
														    */

public class IO
{
    // imports the file name from main
    //reads the file twice (once for size/ number of lines and second time to fill array with lines )
    // returns array of strings to main
    public static String[] readFile(String pFileName)
    {
	FileInputStream fileStream = null;
	InputStreamReader rdr;
	BufferedReader bufRdr;
	int lineNum = 1;
	String line;
	try
	{
	    fileStream = new FileInputStream(pFileName);
	    rdr = new InputStreamReader(fileStream);
      	    bufRdr = new BufferedReader(rdr);
	    lineNum = 0;	    
	    line = bufRdr.readLine();
	    
	    while(line != null)
	    {
	        lineNum++;
	        line = bufRdr.readLine();

	    }
	
        fileStream.close();
	}
	catch(IOException e)
	{
	    if(fileStream != null)
	    {
	        try
	        {
		    fileStream.close();
	        }
	        catch(IOException ex2)
		{ }
	    }
	    System.out.println("Error in fileProcessing: " + e.getMessage());
	}
  	// uses line num to create array of correct size
	String[] lineArray = new String[lineNum];
	// reads the file second time to fill array with lines 
	try
	{
	    fileStream = new FileInputStream(pFileName);
	    rdr = new InputStreamReader(fileStream);
      	    bufRdr = new BufferedReader(rdr);
	    lineNum = 0;	    
	    line = bufRdr.readLine();
	    
	    while(line != null)
	    {
	        lineNum++;
		lineArray[lineNum -1] = line;
	        line = bufRdr.readLine();

	    }
	
        fileStream.close();
	}
	catch(IOException e)
	{
	    if(fileStream != null)
	    {
	        try
	        {
		    fileStream.close();
	        }
	        catch(IOException ex2)
		{ }
	    }
	    System.out.println("Error in fileProcessing: " + e.getMessage());
	}
	return lineArray ;
    }

	public static void writeRow(String s1, String fileName)
    {
	FileOutputStream fileStrm = null;
	PrintWriter pw;
	try
	{
	    fileStrm = new FileOutputStream(fileName ,true);
	    pw = new PrintWriter(fileStrm);
	    pw.println(s1);
	    pw.flush();
	    pw.close();
	}
	catch(IOException e)
	{
	    if (fileStrm != null)
	    {
		try
		{
		    fileStrm.close();
	        }
	        catch(IOException ex2)
	        { }
	    }
	    System.out.println("Error in writing to file: " + e.getMessage());
        }
     }



}
