/****************************************************************
StegDriver is the central class file that calls all methods in
this steganography program.
 
@authors Susanna Bradbury, James Houghton, Pranav Ramanan
@version 0.0
****************************************************************/

import java.util.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class  StegDriver{
 	//start of application
    public static void main(String[] args) throws Exception{
		//Object[] options = { "ENCODE", "DECODE" };
        //int code=JOptionPane.showOptionDialog(null, "Encode or decode?", "Crypticon",
        //JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
        //null, options, options[0]);
        //Desktop.getDesktop().open(new File("c:\\"));
	  
   
      if (code==0){
        //encode
		String text= getText(); //gets text from jtextfield
        String newImageName= getImageName();
        BufferedImage newImage=Encrypt( img, text);
        SaveImage(newImage, newImageName);
      }
      
      else{
        //decode
		//updates jtextfield in gui
      }
    }
   	
    public static String getImageName() //prompts user for file name{
        
		/****************************************************************
		Brings up JOptionPane asking user to input desired file name
		for encrypted image.
		****************************************************************/
		return "";	
		
    }
	
    public static String getText() //text to encrypt{
        
		/****************************************************************
		Gets input from jTextField in GUI, converts to string.
		****************************************************************/
		return "";
		
    }		
    public static void LoadImage() throws IOException{
        
		/****************************************************************
		Loads image by opening an Explorer window and the user then
		selects the desired image.
		****************************************************************/		
    } 
   	
    public static void SaveImage(BufferedImage image) throws IOException{
		
		/****************************************************************
		Saves encrypted image. An Explorer window is opened to allow the
		user to choose a location to save the image.
		****************************************************************/
		
    }
}