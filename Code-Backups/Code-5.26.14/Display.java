/****************************************************************
Display contains methods for initializing and opening a GUI to 
run the steganography program.

Version designated by finished?.year.month.day.hour.minute

@author James Houghton
@version 0.14.5.26.14.28
****************************************************************/
/*

BASIC MENUBAR AND OPENING HAS BEEN WRITTEN
IMAGE DISPLAY HAS BEEN WRITTEN

ERROR PROCESSING INCOMPLETE
GUI INCOMPLETE
	
TO BE ADDED:
	TWO JTEXTFIELDS FOR LENGTH AND KEY
	E/D BUTTON METHODS CHECK THAT
		JTEXTFIELDS IN QUESTION ARE FILLED
			//ENCRYPT: MESSAGE, KEY (or should key be randomly generated?)
			//DECRYPT: LENGTH, KEY
		ELSE, BRINGS UP DIALOGUE BOX

5-26-14

*/
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;
import javax.swing.border.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;


class MenuActionListener implements ActionListener
{
	public void actionPerformed(ActionEvent evt)
	{
		if (evt.getActionCommand()=="effect1")
			Tasks.task1();
		if (evt.getActionCommand()=="effect2")
			Tasks.task2();
		if (evt.getActionCommand()=="effect3")
			Tasks.task3();
		if (evt.getActionCommand()=="effect4")
			Tasks.task4();
		if (evt.getActionCommand()=="effect5")
			Tasks.task5();
		if (evt.getActionCommand()=="effect6")
			Tasks.task6();
		if (evt.getActionCommand()=="effect7")
			Tasks.task7();
		if (evt.getActionCommand()=="Encode")
			Tasks.encode();
		if(evt.getActionCommand()=="Decode")
			Tasks.decode();
		if (evt.getActionCommand()=="Open...")
			Tasks.open();
		if (evt.getActionCommand()=="Save")
			Tasks.save();
		if (evt.getActionCommand()=="Save as...")
			Tasks.saveAs();
		if (evt.getActionCommand()=="Exit")
			System.exit(0);
	}
}

public class Display
{
	public static JFrame pictureFrame;
	public static JPanel sidePanel;
	
	/** Main menu bar */
	public static JMenuBar menuBar;
	/** Effects drop down menu */
	public static JMenu effects;
	public static JMenu fileMenu;
	public static JMenuItem effect1;
	public static JMenuItem effect2;
	public static JMenuItem effect3;
	public static JMenuItem effect4;
	public static JMenuItem effect5;
	public static JMenuItem effect6;
	public static JMenuItem effect7;
	
	public static JPanel picturePanel;
	public static JLabel imageLabel;
	
	public static JMenuItem open;
	public static JMenuItem save;
	public static JMenuItem saveAs;
	public static JMenuItem quit;
	
	public static JLayeredPane layeredPane;
	//public static JPanel inputTextPanel;
	public static JTextField inputTextField;
	
	public static JButton encode;
	public static JButton decode;
	
	public static int height;
	public static int width;
	
	public static BufferedImage loadedImage;
	
	public static JScrollPane scrollPane;
	
	public static void initMenu()
	{
		/****************************************************************
		Takes the defined JMenu entries in the beginning of the class
		and creates the action listeners and binds them together.
		****************************************************************/
		menuBar = new JMenuBar();
		fileMenu = new JMenu("File");
		effects = new JMenu("Effects");
		effect1 = new JMenuItem("effect1");
		effect2 = new JMenuItem("effect2");
		effect3 = new JMenuItem("effect3");
		effect4 = new JMenuItem("effect4");
		effect5 = new JMenuItem("effect5");
		effect6 = new JMenuItem("effect6");
		effect7 = new JMenuItem("effect7");
		open = new JMenuItem("Open...");
		save = new JMenuItem("Save");
		saveAs = new JMenuItem("Save as...");
		quit = new JMenuItem("Exit");
		
		effect1.addActionListener(new MenuActionListener());
		effect2.addActionListener(new MenuActionListener());
		effect3.addActionListener(new MenuActionListener());
		effect4.addActionListener(new MenuActionListener());
		effect5.addActionListener(new MenuActionListener());
		effect6.addActionListener(new MenuActionListener());
		effect7.addActionListener(new MenuActionListener());
		open.addActionListener(new MenuActionListener());
		save.addActionListener(new MenuActionListener());
		saveAs.addActionListener(new MenuActionListener());
		quit.addActionListener(new MenuActionListener());
		
		effects.add(effect1);
		effects.add(effect2);
		effects.add(effect3);
		effects.add(effect4);
		effects.add(effect5);
		effects.add(effect6);
		effects.add(effect7);
		fileMenu.add(open);
		fileMenu.add(save);
		fileMenu.add(saveAs);
		fileMenu.add(quit);
		
		menuBar.add(fileMenu);
		menuBar.add(effects);
		
		pictureFrame.setJMenuBar(menuBar);
	}
	public static void initFrame()
    {
		/****************************************************************
		Performs basic tasks to initialize the actual GUI.
		****************************************************************/
		pictureFrame = new JFrame();
		pictureFrame.setResizable(true);
		pictureFrame.getContentPane().setLayout(new BorderLayout());
		pictureFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		pictureFrame.setTitle("CRYPTICON");
		Dimension dimension = new Dimension(40, 400);
		//getPreferredDimension
		pictureFrame.setPreferredSize(dimension);
	}
	public static void initButtonsAndMenu()
	{
		/****************************************************************
		Creates buttons to apply inputText to the image.
		****************************************************************/
		encode = new JButton("Encode");
		decode = new JButton("Decode");
		encode.addActionListener(new MenuActionListener());
		decode.addActionListener(new MenuActionListener());
		sidePanel.add(encode);
		sidePanel.add(decode);
		
		/*
		Buttons
		*/
		effect1.setEnabled(true);
		effect2.setEnabled(true);
		effect3.setEnabled(true);
		effect4.setEnabled(true);
		effect5.setEnabled(true);
		effect6.setEnabled(true);
		effect7.setEnabled(true);
		open.setEnabled(true);
		save.setEnabled(true);
		saveAs.setEnabled(true);
		quit.setEnabled(true);
	}
	public static void initSidePanelandTextField()
	{
		/****************************************************************
		Creates, formats, and returns the input text panel.
		****************************************************************/
		sidePanel = new JPanel();
		sidePanel.setLayout(new FlowLayout());
		sidePanel.setBackground(new Color(160,160,160));
		inputTextField = new JTextField();
		inputTextField.setBounds(5,5,100,100);
		inputTextField.setPreferredSize(new Dimension(120,40));
		sidePanel.add(inputTextField);
		pictureFrame.add(sidePanel);
	}
	public static void initImagePanel()
	{
		 picturePanel = new JPanel(new BorderLayout());
	}
	public static JLabel initImageLabel(String filename)
	{
		JLabel imageLabel;
		BufferedImage image=null;
		boolean success=false;
		while(success==false){ 
			try
			{
				image = ImageIO.read(new File(filename));
				success=true;
			}
			catch(IOException e){filename = JOptionPane.showInputDialog("Try again.");}
		}
		height = image.getHeight();
		width = image.getWidth();
		loadedImage = image;
		imageLabel = new JLabel(new ImageIcon(image));
		return imageLabel;
	}
	public static String getText()
	{    
		/****************************************************************
		Gets input from jTextField, converts to string.
		****************************************************************/
		String message = inputTextField.getText();
		return message;
    }
	public static void loadBI(BufferedImage image)
	{
		JLabel imageLabel = new JLabel(new ImageIcon(image));
		picturePanel.add(BorderLayout.EAST,imageLabel);
		pictureFrame.getContentPane().add(BorderLayout.EAST,picturePanel);
		pictureFrame.repaint();
	}
	public static void loadImage(String filename)
	{
		if(filename =="")
			filename = JOptionPane.showInputDialog("Filename? ");
		imageLabel=initImageLabel(filename);
		picturePanel.add(BorderLayout.EAST,imageLabel);
		//createScrollPane();
		pictureFrame.getContentPane().add(BorderLayout.EAST,picturePanel);
		pictureFrame.validate();
		pictureFrame.repaint();
	}
	public static int getHeight()
	{
		return height;
	}
	public static int getWidth()
	{
		return width;
	}
	public static void unloadImage()
	{
		loadedImage = null;
		picturePanel.remove(imageLabel);
	}
	public static BufferedImage getImage()
	{
		return loadedImage;
	}
	public static void createScrollPane()
	{
		scrollPane = new JScrollPane();
		scrollPane.setViewportView(picturePanel);
		pictureFrame.getContentPane().add(scrollPane, BorderLayout.CENTER);
		
	}
	public static void init()
	{
		/****************************************************************
		Sets up the frames to hold the images and menus and loads them.
		****************************************************************/
		initFrame();
		initMenu();
		initSidePanelandTextField();
		initButtonsAndMenu();
		pictureFrame.pack();
		//createScrollPane();
		initImagePanel();
		loadImage("");
		Dimension dim = new Dimension(800,600);
		pictureFrame.setPreferredSize(dim);
		pictureFrame.setSize(dim);
		pictureFrame.setVisible(true);
		
	}
	
	
}