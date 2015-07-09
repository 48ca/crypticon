/**********************************************
Tasks.java are the methods called for by Display
through the GUI.

@author JHSBPR
@version 0.14.5.26.15.47
**********************************************/
import java.awt.Desktop;
import java.util.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import javax.swing.JOptionPane;
public class Tasks
{
	public static String saveLocation=null;
	/*
	TO GET THE IMAGE, CALL Display.getImage()
	*/
	
	/* Pranav's methods */
	public static void task1()
	{
		System.out.println("TASK 1");
		
		/*
		This would be how you would retrieve and send an image.
		*/
		BufferedImage image=Display.getImage();
		Display.unloadImage();
		//Edit BufferedImage
		Display.loadBI(image);
	}
	public static void task2()
	{
		System.out.println("TASK 2");
	}
	public static void task3()
	{
		System.out.println("TASK 3");
	}
	public static void task4()
	{
		System.out.println("TASK 4");
	}
	public static void task5()
	{
		System.out.println("TASK 5");
	}
	public static void task6()
	{
		System.out.println("TASK 6");
	}
	public static void task7()
	{
		System.out.println("TASK 7");
	}
	
	
	/* Susanna's methods */
	public static void encode()
	{
		System.out.println("ENCODE: "+Display.getText());
	}
	public static void decode()
	{
		System.out.println("DECODE: "+Display.getText());
	}
	
	
	/* James' methods */
	public static void open()
	{
		/*
		try{File file = new File("c:\\");
		Desktop desktop = Desktop.getDesktop();
		desktop.open(file);}
		catch(IOException e){}
		*/
		
		Display.unloadImage();
		Display.loadImage("");
	}
	public static void save()
	{
	/* METHOD TO SAVE IMAGE WITH A PREV. SPECIFIED LOCATION */
		System.out.println("SAVE");
		if(saveLocation!=null)
		{
		try {
			BufferedImage image=Display.getImage();
			File outputFile = new File(saveLocation);
			ImageIO.write(image,"png",outputFile);
		}
		catch (IOException e){}
		}
		else
			saveAs();
	}
	public static void saveAs()
	{
	/* METHOD TO SAVE IMAGE TO DESIRED LOCATION */
		System.out.println("SAVEAS");
		String filename = JOptionPane.showInputDialog("Save as... (PNG)");
		//boolean success is a failsafe, it has not been seen useful yet.
		boolean success=false;
		while (success==false){
		try {
			BufferedImage image=Display.getImage();
			if(filename.substring(filename.length()-3)!=".png")
				filename+=".png";
			saveLocation=filename;
			File outputFile = new File(filename);
			ImageIO.write(image,filename.substring(filename.length()-3),outputFile);
			success=true;
		}
		catch (IOException e){filename = JOptionPane.showInputDialog("Try again.");}}
	}
}