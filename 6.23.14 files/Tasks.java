import java.awt.Desktop;
import java.awt.Toolkit;
import java.awt.datatransfer.*;
import java.util.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

/****************************************************************
Tasks are the methods called for by Display
through the GUI.

@author Susanna Bradbury, James Houghton, Pranav Ramanan
@version 06/05/2014
****************************************************************/
public class Tasks
{
	public static boolean done = false;
	public static JFrame sliderFrame;
	public static int threshold;
	public static BufferedImage bwImage;
	public static JSlider slider;
	/****************************************************************
	Location of where the most recent saved file was saved.
	****************************************************************/
	public static String saveLocation=null;
	
	/* Pranav's methods */
	/****************************************************************
	Inverts the loaded image's colors.
	****************************************************************/
	public static void task1()
	{		
		System.out.println(Display.getText());
		BufferedImage image=Display.getImage();
		
		if(Display.getText()==null)
		{
			String message = Steg.decrypt(image);
			Display.setEncodedText(message);
		}
		image = Effects.Inverse(image,Display.getHeight(),Display.getWidth());
		Display.unloadImage();
		Display.loadBI(image, 1);
		image = Steg.encrypt(image);
		Display.loadBI(image, 2);
	}
	/****************************************************************
	Fades the colors of the image.
	****************************************************************/
	public static void task2()
	{
		BufferedImage image=Display.getImage();
		
		if(Display.getText()==null)
		{
			String message = Steg.decrypt(image);
			Display.setEncodedText(message);
		}
		String color = JOptionPane.showInputDialog("R | G | B | ALL");
		if ((color != null) && (color.length() > 0))
		{
			try
			{
				String tintString = JOptionPane.showInputDialog("Decrease color value by: (0-225)");
				if ((tintString!=null) && (tintString.length()>0))
				{
					int tint = Integer.parseInt(tintString);
					image = Effects.Fade(image,Display.getHeight(),Display.getWidth(),tint,color);
					Display.unloadImage();
					Display.loadBI(image, 1);
					image = Steg.encrypt(image);
					Display.loadBI(image, 2);
				}
			}
			catch(Exception e){}
		}
	}
	/****************************************************************
	Tints the image's colors.
	****************************************************************/
	public static void task3()
	{
		BufferedImage image=Display.getImage();
		
		if(Display.getText()==null)
		{
			String message = Steg.decrypt(image);
			Display.setEncodedText(message);
		}
		
		String color = JOptionPane.showInputDialog("R | G | B | ALL");
		if ((color != null) && (color.length() > 0))
		{
			try
			{
				String tintString = JOptionPane.showInputDialog("Increase color value by: (0-225)");
				if ((tintString!=null) && (tintString.length()>0))
				{
					int tint = Integer.parseInt(tintString);
					image = Effects.Tint(image,Display.getHeight(),Display.getWidth(),tint,color);
					Display.unloadImage();
					Display.loadBI(image, 1);
					image = Steg.encrypt(image);
					Display.loadBI(image, 2);
				}
			}
			catch(Exception e){}
		}
	}
	/****************************************************************
	Makes the image a mix of only black and white color.
	****************************************************************/
	public static void task4()
	{
		bwImage=Display.getImage();
		
		if(Display.getText()==null)
		{
			String message = Steg.decrypt(bwImage);
			Display.setEncodedText(message);
		}
		int average = getAverage(bwImage);
		threshold = average;
		sliderFrame = new JFrame("Set threshold");
		JButton sliderButton = new JButton("Apply");
		JButton setSlider = new JButton("Reset to default");
		JButton precise = new JButton("Set value");
		sliderButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				sliderFrame.setVisible(false);
				sliderFrame.dispose();
			}
		});
		setSlider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				slider.setValue(getAverage(bwImage));
			}
		});
		precise.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				try {
				slider.setValue(Integer.parseInt(JOptionPane.showInputDialog("Set slider to precise value:"))); }
				catch (Exception ex)
				{
					JOptionPane.showMessageDialog(null,"Action could not be completed!","Error!",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		slider = new JSlider(JSlider.HORIZONTAL,0,255,average);
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e)
			{
				refreshBWImage(bwImage,slider.getValue());
			}
		});
		refreshBWImage(bwImage,average);
		slider.setMinorTickSpacing(10);
		slider.setMajorTickSpacing(100);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		JPanel intermediatePanel = new JPanel();
		intermediatePanel.add(setSlider);
		intermediatePanel.add(precise);
		intermediatePanel.add(sliderButton);
		JPanel sliderPanel = new JPanel();
		sliderPanel.setBorder(new EmptyBorder(5,5,5,5));
		sliderPanel.add(slider);
		sliderFrame.add(sliderPanel,BorderLayout.NORTH);
		sliderFrame.add(intermediatePanel,BorderLayout.CENTER);
		sliderFrame.setSize(400,130);
		sliderFrame.setResizable(false);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Point middle = new Point(screenSize.width/2,screenSize.height/2);
		Point middleCorrected = new Point(middle.x-(sliderFrame.getWidth()/2),middle.y-(sliderFrame.getHeight()/2));
		sliderFrame.setLocation(middleCorrected);
		sliderFrame.setVisible(true);
	}
	/****************************************************************
	Displays an updated Black/White filtered image.
	****************************************************************/
	public static void refreshBWImage(BufferedImage image, int threshold)
	{
		image = Effects.BlackWhite(image,Display.getHeight(),Display.getWidth(),threshold);
		Display.unloadImage();
		Display.loadBI(image, 1);
		image = Steg.encrypt(image);
		Display.loadBI(image, 2);
	}
	/****************************************************************
	Returns untouched image before Black/White filter is applied.
	****************************************************************/
	public static BufferedImage getImage()
	{
		return bwImage;
	}
	/****************************************************************
	Removes a specified color in the loaded	image.
	****************************************************************/
	public static void task5()
	{
		BufferedImage image=Display.getImage();
		
		if(Display.getText()==null)
		{
			String message = Steg.decrypt(image);
			Display.setEncodedText(message);
		}
		
		String color = JOptionPane.showInputDialog("R|G|B");
		if ((color != null) && (color.length() > 0))
		{
		image = Effects.Remove(image,Display.getHeight(),Display.getWidth(),color);
		Display.unloadImage();
		Display.loadBI(image, 1);
		image = Steg.encrypt(image);
		Display.loadBI(image, 2);
		}
	}
	/****************************************************************
	Grayscales the image.
	****************************************************************/
	public static void task6()
	{
		BufferedImage image=Display.getImage();
		
		if(Display.getText()==null)
		{
			String message = Steg.decrypt(image);
			Display.setEncodedText(message);
		}
		
		image = Effects.Grayscale(image,Display.getHeight(),Display.getWidth());
		Display.unloadImage();
		Display.loadBI(image, 1);
		image = Steg.encrypt(image);
		Display.loadBI(image, 2);
	}
	/****************************************************************
	'Colorizes' the image. Dramatically changes the colors of the image.
	****************************************************************/
	public static void task7()
	{
		BufferedImage image=Display.getImage();
		
		if(Display.getText()==null)
		{
			String message = Steg.decrypt(image);
			Display.setEncodedText(message);
		}
		image = Effects.Colorize(image,Display.getHeight(),Display.getWidth());
		Display.unloadImage();
		Display.loadBI(image, 1);
		image = Steg.encrypt(image);
		Display.loadBI(image, 2);
	}
	
	public static int getAverage(BufferedImage image)
	{
		int h=image.getHeight();
		int w=image.getWidth();
		int avg=0;
		for(int y=0; y<h; y++)
		{
			for(int x=0; x<w; x++)
			{
				int rgb=image.getRGB(x, y);
				int r = (rgb)&0xFF;
				int g = (rgb>>8)&0xFF;
				int b = (rgb>>16)&0xFF;
				avg+=(r+g+b)/3;
			}
		}
		avg=avg/(h*w);
		return avg;
	}
	
	/* Susanna's methods */
	/****************************************************************
	Encodes the loaded image.
	****************************************************************/
    public static void encode()
	{
		Display.setEncodedText(Display.getText(1));
		BufferedImage eImage = Steg.encrypt(Display.getImage());
		Display.unloadImage();
		Display.loadBI(eImage, 2);
		
	}
	/****************************************************************
	Decodes the loaded image.
	****************************************************************/
	public static void decode()
	{
		String message = Steg.decrypt(Display.getImage());
		System.out.println(message);
		if(message.equals(""))
		{
			Display.blankError();
		}
		else
			Display.setText(message);
		
	}
	
	/* James' methods */
	/****************************************************************
	Opens a new image.
	****************************************************************/
	public static void open()
	{
		Display.loadImage("");
	}
	/****************************************************************
	Saves the loaded image.
	****************************************************************/
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
	/****************************************************************
	Saves the loaded image as a specific file in a specific place.
	****************************************************************/
	public static void saveAs()
	{
		String filename = Display.fnSave();
		if(filename!=null)
		{
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
   
	/****************************************************************
	Displays the user manual to assist the user.
	****************************************************************/
	public static void help()
	{
		Resources.showHelp();
	}
   
	/****************************************************************
	Displays a dialog containing information about CRYPTICON
	and its developers.
	****************************************************************/
	public static void info()
	{
		Resources.displayInfo();
	}
	/****************************************************************
	Sets the last save location to a global variable, making it
	accessible when needed.
	****************************************************************/
	public static void setLoadedImage(String filename)
	{
		saveLocation=filename;
	}
	/****************************************************************
	Copies text in Display's output text field to the system's clipboard.
	****************************************************************/
	public static void copy()
	{
		String output = Display.getOutput();
		StringSelection stringSelection = new StringSelection(output);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection,null);
	}
}