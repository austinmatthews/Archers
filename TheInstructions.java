import javax.swing.*;  

import java.util.*;  
import java.io.*;  

public class TheInstructions
{  

	public TheInstructions(){
		
		readTheFile();
	}

	public static void readTheFile(){

		try  
		{  

			//read the file and scan the file
			FileReader readTextFile=new FileReader("ScorchedInstructions.txt");   
			Scanner fileReaderScan=new Scanner(readTextFile);  
			
			
			//append the text as one giant sring
			String longestStringEver="";  
			
			//loop through the file until no line is left
			while(fileReaderScan.hasNextLine() == true)  
			
			{  
				//read in each line, and then enter the next line
				String toAppend = fileReaderScan.nextLine()+"\n";  

				longestStringEver = longestStringEver + toAppend;  
				
			}  
  
			
			JTextArea theTextArea = new JTextArea(longestStringEver); 
			
			//make and add to frame
			JFrame theJFrame= new JFrame("Archers Instructions");    
			theJFrame.add(theTextArea); 
			theJFrame.setSize(800,600);  
			theJFrame.setResizable(false);
			theJFrame.setVisible(true);
			
		}

		catch(Exception e)  {  
			//show a dialogue box saying unable to find ArcherInstructions.txt
			JOptionPane.showMessageDialog(new JFrame(), "ScorchedInstructions.txt could not be found and/or read");
		}  
	}
}