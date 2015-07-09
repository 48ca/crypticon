/****************************************************************
Display contains methods for initializing and opening a GUI to 
run the steganography program.

@author James Houghton
@version 0.0
****************************************************************/
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;
import javax.swing.border.*;
import java.awt.image.BufferedImage;

public class Display
{
	private JFrame pictureFrame;
	private JScrollPane scrollPane;
	
	/** Main menu bar */
	private JMenuBar menuBar;
	/** Effects drop down menu */
	private JMenu effects;
	
	public Display(BufferedImage picture)
	{
		/****************************************************************
		Sets up the frames to hold the images and menus and loads them.
		****************************************************************/
		initFrame();
		initMenu();
		JTextField inputTextField = initInputTextField();
		initButtons();
	}
	public void initMenu()
	{
		/****************************************************************
		Takes the defined JMenu entries in the beginning of the class
		and creates the action listeners and binds them together.
		****************************************************************/	
	}
	public void initFrame()
    {
		/****************************************************************
		Performs basic tasks to initialize the actual GUI.
		****************************************************************/
		pictureFrame = new JFrame();
		pictureFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		pictureFrame.setTitle(null);
	}
	public void initButtons()
	{
		/****************************************************************
		Creates buttons to apply inputText to the image.
		****************************************************************/
		JButton encode = new JButton("Encode");
		JButton decode = new JButton("Decode");
	}
	public JTextField initInputTextField()
	{
		/****************************************************************
		Creates, formats, and returns the input text panel.
		****************************************************************/
		JTextField inputTextPanel = new JTextField();
		return inputTextPanel;
	}
	
	public String getText()
	{    
		/****************************************************************
		Gets input from jTextField, converts to string.
		****************************************************************/
		String message = new String("");
		return message;
    }
}