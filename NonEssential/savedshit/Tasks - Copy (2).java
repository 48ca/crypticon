/**********************************************
Tasks.java are the methods called for by Display
through the GUI.

@author JHSBPR
@version 0.14.5.26.16.39
**********************************************/
import java.awt.Desktop;
import java.awt.Toolkit;
import java.awt.datatransfer.*;
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
		//System.out.println("TASK 1");
		
		/*
		This would be how you would retrieve and send an image.
		*/
		
		// !!! IMPORTANT: Try to do all the execution in Effects.java
		// This can be done by using Effects.METHOD
		// Method --> defined in Effects class.
		
		System.out.println(Display.getText());
		BufferedImage image=Display.getImage();
		if(Display.getText()==null)
		{
			String message = Steg.decrypt(image);
			Display.setEncodedText(message);
		}
		//Edit BufferedImage
		image = Effects.Inverse(image,Display.getHeight(),Display.getWidth());
		Display.unloadImage();
		Display.loadBI(image, 1);
		image = Steg.encrypt(image);
		Display.loadBI(image, 2);
	}
	public static void task2()
	{
		//System.out.println("TASK 2");
		BufferedImage image=Display.getImage();
		if(Display.getText()==null)
		{
			String message = Steg.decrypt(image);
			Display.setEncodedText(message);
		}
		image = Effects.Fade(image,Display.getHeight(),Display.getWidth());
		Display.unloadImage();
		Display.loadBI(image, 1);
		image = Steg.encrypt(image);
		Display.loadBI(image, 2);
	}
	public static void task3()
	{
		//System.out.println("TASK 3");
		BufferedImage image=Display.getImage();
		if(Display.getText()==null)
		{
			String message = Steg.decrypt(image);
			Display.setEncodedText(message);
		}
		int tint;
		tint = 10;
		String color = JOptionPane.showInputDialog("R|G|B");
		image = Effects.Tint(image,Display.getHeight(),Display.getWidth(),tint,color);
		Display.unloadImage();
		Display.loadBI(image, 1);
		image = Steg.encrypt(image);
		Display.loadBI(image, 2);
	}
	public static void task4()
	{
		//System.out.println("TASK 4");
		BufferedImage image=Display.getImage();
		if(Display.getText()==null)
		{
			String message = Steg.decrypt(image);
			Display.setEncodedText(message);
		}
		image = Effects.BlackWhite2(image,Display.getHeight(),Display.getWidth());
		Display.unloadImage();
		Display.loadBI(image, 1);
		image = Steg.encrypt(image);
		Display.loadBI(image, 2);
	}
	public static void task5()
	{
		//System.out.println("TASK 5");
		BufferedImage image=Display.getImage();
		if(Display.getText()==null)
		{
			String message = Steg.decrypt(image);
			Display.setEncodedText(message);
		}
		String color = JOptionPane.showInputDialog("R|G|B");
		image = Effects.Remove(image,Display.getHeight(),Display.getWidth(),color);
		Display.unloadImage();
		Display.loadBI(image, 1);
		image = Steg.encrypt(image);
		Display.loadBI(image, 2);
	}
	public static void task6()
	{
		//System.out.println("TASK 6");
		BufferedImage image=Display.getImage();
		if(Display.getText()==null)
		{
			String message = Steg.decrypt(image);
			Display.setEncodedText(message);
		}
		image = Effects.Inverse(image,Display.getHeight(),Display.getWidth(),"D");
		Display.unloadImage();
		Display.loadBI(image, 1);
		image = Steg.encrypt(image);
		Display.loadBI(image, 2);
	}
	public static void task7()
	{
		//System.out.println("TASK 7");
		BufferedImage image=Display.getImage();
		if(Display.getText()==null)
		{
			String message = Steg.decrypt(image);
			Display.setEncodedText(message);
		}
		image = Effects.Inverse(image,Display.getHeight(),Display.getWidth(),"D");
		Display.unloadImage();
		Display.loadBI(image, 1);
		image = Steg.encrypt(image);
		Display.loadBI(image, 2);
	}
	
	
	/* Susanna's methods */
    public static void encode()
	{
		Display.setEncodedText(Display.getText(1));
		BufferedImage eImage = Steg.encrypt(Display.getImage());
		Display.unloadImage();
		Display.loadBI(eImage, 2);
		
	}
	public static void decode()
	{
		String message = Steg.decrypt(Display.getImage());
		Display.setText(message);
		
	}
	
	/* James' methods */
	public static void open()
	{
		Display.clear();
		Display.loadImage("");
	}
	public static void save()
	{
		if(saveLocation!=null)
		{
		try {
			BufferedImage image=Display.getImage(1);
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
		String filename = Display.fnSave();
		if(filename!=null)
		{
		//boolean success is a failsafe, it has not been seen useful yet.
			boolean success=false;
			while (success==false){
			try {
				BufferedImage image=Display.getImage(1);
				if(filename.length()>3)
			 {
				   String extension=filename.substring(filename.length()-4).toLowerCase();
				if(!extension.equals(".png"))
				{
				   filename+=".png";
				}
			 }
				else filename+=".png";
				saveLocation=filename;
				File outputFile = new File(filename);
				ImageIO.write(image,filename.substring(filename.length()-3),outputFile);
				success=true;
			}
			catch (IOException e){filename = JOptionPane.showInputDialog("Try again.");}}
		}
	}
   
	public static void help()
	{
		System.out.println("HELP");
	}
   
	public static void info()
	{
		System.out.println("INFO");
	}
	public static void setLoadedImage(String filename)
	{
		saveLocation=filename;
	}
	public static void copy()
	{
		String output = Display.getOutput();
		StringSelection stringSelection = new StringSelection(output);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection,null);
	}
}