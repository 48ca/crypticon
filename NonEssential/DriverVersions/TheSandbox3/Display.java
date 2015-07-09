/****************************************************************
Display contains methods for initializing and opening a GUI to 
run the steganography program.

Version designated by finished?.year.month.day.hour.minute

@author James Houghton
@version 0.14.5.31.14.11
****************************************************************/
/*


ERROR PROCESSING INCOMPLETE
GUI INCOMPLETE
	
TO BE ADDED:
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
import javax.swing.filechooser.FileFilter;
import javax.imageio.ImageIO;
import java.net.URL;


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
        if (evt.getActionCommand()=="Help")
			Tasks.help();
        if (evt.getActionCommand()=="Info")
			Tasks.info();
		if (evt.getActionCommand()=="Copy to clipboard")
			Tasks.copy();
		if (evt.getActionCommand()=="Clear")
			Display.clear();
	}
}

public class Display
{
	public static JFrame pictureFrame;
	public static JFrame openFrame;
	public static JPanel sidePanel;
	
	/** Main menu bar */
	public static JMenuBar menuBar;
	/** Effects drop down menu */
	public static JMenu effects;
	public static JMenuItem effect1;
	public static JMenuItem effect2;
	public static JMenuItem effect3;
	public static JMenuItem effect4;
	public static JMenuItem effect5;
	public static JMenuItem effect6;
	public static JMenuItem effect7;
	
	public static JPanel picturePanel;
	public static JLabel imageLabel;
	public static JLabel prevImageLabel;
	
	public static JMenu fileMenu;
	public static JMenuItem open;
	public static JMenuItem save;
	public static JMenuItem saveAs;
	public static JMenuItem quit;
   
    public static JMenu resources;
    public static JMenuItem help;
    public static JMenuItem info;
	
	public static JLayeredPane layeredPane;
	public static JTextArea inputTextField;
	public static JTextArea outputTextField;
	
	public static JButton encode;
	public static JButton decode;
	public static JButton copy;
	public static JButton clear;
	
	public static int height;
	public static int width;
	
	public static BufferedImage loadedImage;
	public static BufferedImage imageToBeSent;
	
	public static JScrollPane scrollPane;
	public static JScrollPane scrollPane2;
	
	public static FileFilter fileFilter;
	
	public static String prevOpen;
	public static String prevOpenFile;
	public static String prevOpenChecked;
	public static String prevOpenFileChecked;
	
	
	public static void initMenu()
	{
		/****************************************************************
		Takes the defined JMenu entries in the beginning of the class
		and creates the action listeners and binds them together.
		****************************************************************/
		menuBar = new JMenuBar();
		fileMenu = new JMenu("File");
		effects = new JMenu("Effects");
        resources = new JMenu("Resources");
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
        help = new JMenuItem("Help");
        info = new JMenuItem("Info");
		
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
        help.addActionListener(new MenuActionListener());
        info.addActionListener(new MenuActionListener());
		
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
        resources.add(info);
        resources.add(help);
		
		menuBar.add(fileMenu);
		menuBar.add(effects);
        menuBar.add(resources);
		
		pictureFrame.setJMenuBar(menuBar);
	}
	public static void initFrame()
    {
		/****************************************************************
		Performs basic tasks to initialize the actual GUI.
		****************************************************************/
		pictureFrame = new JFrame();
		pictureFrame.setResizable(false);
		pictureFrame.getContentPane().setLayout(new BorderLayout());
		pictureFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		pictureFrame.setTitle("CRYPTICON");
		openFrame = new JFrame("Open...");
		Dimension dimension = new Dimension(40, 400);
		pictureFrame.setPreferredSize(dimension);
	}
	public static void initButtonsAndMenu()
	{
		/****************************************************************
		Creates buttons to apply inputText to the image.
		****************************************************************/
		encode = new JButton("Encode");
		decode = new JButton("Decode");
		clear = new JButton("Clear");
		copy = new JButton("Copy to clipboard");
		encode.addActionListener(new MenuActionListener());
		decode.addActionListener(new MenuActionListener());
		copy.addActionListener(new MenuActionListener());
		clear.addActionListener(new MenuActionListener());
		
		setDisabled();
		open.setEnabled(true);
		quit.setEnabled(true);
		
		fileFilter = new FileFilter()
		{
			public boolean accept(File file)
			{
				if (file.isDirectory())
				{
					return true;
				}
				String name = file.getName();
				return name.endsWith(".png")||name.endsWith(".jpg");
			}
			public String getDescription()
			{
				return "*.png, *.jpg";
			}
		};
	}
	public static void setDisabled()
	{
		effect1.setEnabled(false);
		effect2.setEnabled(false);
		effect3.setEnabled(false);
		effect4.setEnabled(false);
		effect5.setEnabled(false);
		effect6.setEnabled(false);
		effect7.setEnabled(false);
		save.setEnabled(false);
		saveAs.setEnabled(false);
		encode.setEnabled(false);
		decode.setEnabled(false);
		copy.setEnabled(false);
		clear.setEnabled(false);
	}
	public static void setEnabled()
	{
		effect1.setEnabled(true);
		effect2.setEnabled(true);
		effect3.setEnabled(true);
		effect4.setEnabled(true);
		effect5.setEnabled(true);
		effect6.setEnabled(true);
		effect7.setEnabled(true);
		save.setEnabled(true);
		saveAs.setEnabled(true);
		encode.setEnabled(true);
		decode.setEnabled(true);
		//copy.setEnabled(true);
		//clear.setEnabled(true);
	}
	public static void initSidePanelandTextField()
	{
		/****************************************************************
		Creates, formats, and returns the input text panel.
		****************************************************************/
		sidePanel = new JPanel();
		sidePanel.setLayout(new FlowLayout());
		sidePanel.setBackground(new Color(160,160,160));
		sidePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		inputTextField = new JTextArea(10,11);
		inputTextField.setBounds(5,5,100,100);
		inputTextField.setPreferredSize(new Dimension(150,200));
		inputTextField.setLineWrap(true);
		inputTextField.setWrapStyleWord(true);
		//inputTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		//inputTextField.setMargin(new Insets(10,10,10,10));
		inputTextField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK,2),BorderFactory.createEmptyBorder(5,5,5,5)));
		/*scrollPane = new JScrollPane(inputTextField);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		sidePanel.add(scrollPane);*/
		sidePanel.add(inputTextField);
		outputTextField = new JTextArea(10,11);
		outputTextField.setBounds(5,5,100,100);
		outputTextField.setPreferredSize(new Dimension(150,200));
		outputTextField.setLineWrap(true);
		outputTextField.setWrapStyleWord(true);
		outputTextField.setEditable(false);
		//outputTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		//outputTextField.setMargin(new Insets(10,10,10,10));
		outputTextField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK,2),BorderFactory.createEmptyBorder(5,5,5,5)));
		outputTextField.setBackground(new Color(216,216,216));
		//outputTextField.setText("           OUTPUT TEXT");
	}
	public static void applyOutputTextField()
	{
		sidePanel.add(outputTextField);
		/*
		scrollPane2 = new JScrollPane(outputTextField);
		scrollPane2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		sidePanel.add(scrollPane2);
		*/
	}
	public static void applyButtons()
	{
		sidePanel.add(encode);
		sidePanel.add(decode);
	}
	public static void applySidePanel()
	{
		pictureFrame.add(sidePanel);
	}
	public static void initImagePanel()
	{
		 picturePanel = new JPanel(new BorderLayout());
	}
	public static BufferedImage toBufferedInstance(Image image,int width,int height)
	{
		BufferedImage bi = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = bi.createGraphics();
		g2d.drawImage(image, 0, 0, null);
		g2d.dispose();
		return bi;
	}
	public static JLabel initImageLabel(String filename,int mode)
	{
		JLabel imageLabel;
		BufferedImage image=null;
		if(mode==0)
		{
			boolean success=false;
			while(success==false && filename!=null){ 
				try
				{
					image = ImageIO.read(new File(filename));
					prevOpenChecked=prevOpen;
					prevOpenFileChecked=prevOpenFile;
					success=true;
				}
				catch(IOException e)
				{
				boolean again=tryAgain();
				if (again==true)
				{
					filename=fnPrompt();
					if(filename!=null)
					{
						if(filename.length()>4)
						{
							if(!filename.substring(filename.length()-4).equals(".png")&&!filename.substring(filename.length()-4).equals(".jpg"))
							{
								filename+=".png";
								try
								{
									ImageIO.read(new File(filename));
								}
								catch(IOException e1)
								{
									filename = filename.substring(0,filename.length()-4);
									filename+=".jpg";
									try
									{
										ImageIO.read(new File(filename));
									}
									catch(IOException e2)
									{
										filename = filename.substring(0,filename.length()-4);
									}
								}
								/*
								if(filename.substring(filename.length()-4).equals(".jpg"))
									prevOpenFile=prevOpenFile.substring(prevOpenFile.length()-4);
									prevOpenFile+=".png";
								*/
							}
						}
					}
					else
						return prevImageLabel;
				}
				else
					return prevImageLabel;
				}
			}
		}
		else{
			try
			{
				InputStream stream = Display.class.getResourceAsStream("resources/images/blank.png");
				image = ImageIO.read(stream);
			}
			catch(IOException e){}
		}
		imageLabel = initImageLabel(image);
		setImageToBeSent(image);
		prevImageLabel = imageLabel;
		return imageLabel;
	}
	public static boolean tryAgain()
	{
		JOptionPane op = new JOptionPane();
		op.setMessageType(JOptionPane.ERROR_MESSAGE);
		int i=op.showConfirmDialog(null,"Try again?","File not found!",JOptionPane.YES_NO_OPTION);
		if(i==JOptionPane.YES_OPTION)
			return true;
		if(i==JOptionPane.NO_OPTION)
			return false;
		else
			return false;
	}
	public static JLabel initImageLabel(String filename)
	{
		JLabel label = initImageLabel(filename,0);
		return label;
	}
	public static JLabel initImageLabel(BufferedImage image)
	{
		JLabel imageLabel;
		height = image.getHeight();
		width = image.getWidth();
		loadedImage = image;
		double scaler = 600.0/width;
		double dsWidth = width*scaler;
		double dsHeight = height*scaler;
		int sWidth = (int) dsWidth;
		int sHeight = (int) dsHeight;
		Image sImage=image.getScaledInstance(sWidth,sHeight,Image.SCALE_SMOOTH);
		BufferedImage bsImage=toBufferedInstance(sImage,sWidth,sHeight);
		imageLabel = new JLabel(new ImageIcon(bsImage));
		return imageLabel;
	}
	public static String getText()
	{    
		/****************************************************************
		Gets input from jTextField, converts to string.
		****************************************************************/
		return inputTextField.getText();
    }
	public static String getOutput()
	{
		return outputTextField.getText();
	}
	public static void setText(String string)
	{
		outputTextField.setBackground(Color.WHITE);
		outputTextField.setText(string);
		clear.setEnabled(true);
		copy.setEnabled(true);
	}
	public static void loadBI(BufferedImage image)
	{
		imageLabel = initImageLabel(image);
		Border paddingBorder = BorderFactory.createEmptyBorder(10,10,10,10);
		Border border = BorderFactory.createLineBorder(Color.BLACK,5);
		imageLabel.setBorder(BorderFactory.createCompoundBorder(border,paddingBorder));
		picturePanel.add(BorderLayout.EAST,imageLabel);
		pictureFrame.getContentPane().add(BorderLayout.EAST,picturePanel);
		pictureFrame.validate();
		pictureFrame.repaint();
	}
	public static void loadImage(String filename)
	{
		loadImage(filename,0);
	}
	public static void loadImage(String filename,int mode)
	{
		if(mode==0)
        {
		    filename=fnPrompt();
            if(filename!=null)
            {
				if(filename.length()>4)
				{
					if(!filename.substring(filename.length()-4).equals(".png")&&!filename.substring(filename.length()-4).equals(".jpg"))
					{
						filename+=".png";
						try
						{
							ImageIO.read(new File(filename));
						}
						catch(IOException e1)
						{
							filename = filename.substring(0,filename.length()-4);
							filename+=".jpg";
							try
							{
								ImageIO.read(new File(filename));
							}
							catch(IOException e2)
							{
								filename = filename.substring(0,filename.length()-4);
							}
						}
						/*
						if(filename.substring(filename.length()-4).equals(".jpg"))
							prevOpenFile=prevOpenFile.substring(prevOpenFile.length()-4);
							prevOpenFile+=".png";
						*/
					}
				}
				else
					filename+=".png";
				unloadImage();
				initImageLabelS2(filename);
				Tasks.setLoadedImage(filename);
				setEnabled();
		    }
        }
		else
        {
			filename = "resources/images/blank.png";
			initImageLabelS2(filename,1);
        }
	}
	public static void initImageLabelS2(String filename,int mode)
   {
		imageLabel=initImageLabel(filename,mode);
		Border paddingBorder = BorderFactory.createEmptyBorder(10,10,10,10);
		Border border = BorderFactory.createLineBorder(Color.BLACK,5);
		imageLabel.setBorder(BorderFactory.createCompoundBorder(border,paddingBorder));
		picturePanel.add(BorderLayout.EAST,imageLabel);
		pictureFrame.getContentPane().add(BorderLayout.EAST,picturePanel);
		pictureFrame.validate();
		pictureFrame.repaint();
   }
   public static void initImageLabelS2(String filename)
   {
		imageLabel=initImageLabel(filename);
		Border paddingBorder = BorderFactory.createEmptyBorder(10,10,10,10);
		Border border = BorderFactory.createLineBorder(Color.BLACK,5);
		imageLabel.setBorder(BorderFactory.createCompoundBorder(border,paddingBorder));
		picturePanel.add(BorderLayout.EAST,imageLabel);
		pictureFrame.getContentPane().add(BorderLayout.EAST,picturePanel);
		pictureFrame.validate();
		pictureFrame.repaint();
   }
   public static String fnPrompt()
   {
    JFileChooser fileChooser = new JFileChooser(prevOpen);
    fileChooser.setFileFilter(fileFilter);
		int returnval = fileChooser.showOpenDialog(openFrame);
		if(returnval == JFileChooser.APPROVE_OPTION)
		{
			String prevOpenFull = fileChooser.getSelectedFile().getAbsolutePath();
			prevOpen = prevOpenFull;
			int length=prevOpen.length();
			int i=length;
			while(i>0 && !prevOpen.substring(i-1,i).equals("\\"))
			{
				i--;
			}
			prevOpen = prevOpen.substring(0,i);
			prevOpenFile = prevOpenFull.substring(i,length);
			if(prevOpenFile.substring(prevOpenFile.length()-4).equals(".jpg"))
			{
				prevOpenFile=prevOpenFile.substring(0,prevOpenFile.length()-4);
				prevOpenFile+=".png";
			}
			if(!prevOpenFile.substring(prevOpenFile.length()-4).equals(".png"))
			{
				prevOpenFile+=".png";
			}
			return fileChooser.getSelectedFile().getAbsolutePath();
		}
      else
        return null;
      
   }
   public static String fnSave()
   {
      JFileChooser fileChooser = new JFileChooser(prevOpenChecked);
      fileChooser.setSelectedFile(new File(prevOpenFileChecked));
      int returnval = fileChooser.showSaveDialog(new JFrame("Save as..."));
      if(returnval == JFileChooser.APPROVE_OPTION)
        return fileChooser.getSelectedFile().getAbsolutePath();
      else
		return null;
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
		picturePanel.remove(imageLabel);
		imageLabel = null;
	}
	public static BufferedImage getImage()
	{
		return getImage(0);
	} 
	public static BufferedImage getImage(int mode)
	{
		if(mode==1)
			return loadedImage;
		else
			return imageToBeSent;
	} 
	public static void applyCopy()
	{
		sidePanel.add(copy);
	}
	public static void apply()
	{
		applyButtons();
		applyOutputTextField();
		applyCopy();
		applyClear();
		applySidePanel();
	}
	public static void centerFrame()
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Point middle = new Point(screenSize.width/2,screenSize.height/2);
		Point middleCorrected = new Point(middle.x-(pictureFrame.getWidth()/2),middle.y-(pictureFrame.getHeight()/2));
		pictureFrame.setLocation(middleCorrected);
	}
	public static void setImageToBeSent(BufferedImage image)
	{
		imageToBeSent=image;
	}
	public static void applyClear()
	{
		sidePanel.add(clear);
	}
	public static void clear()
	{
		outputTextField.setText("");
		outputTextField.setBackground(new Color(216,216,216));
		copy.setEnabled(false);
		clear.setEnabled(false);
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
		apply();
		pictureFrame.pack();
		initImagePanel();
		loadImage("",1);
		Dimension dim = new Dimension(800,600);
		pictureFrame.setPreferredSize(dim);
		pictureFrame.setSize(dim);
		centerFrame();
		pictureFrame.setVisible(true);
		/*
		try
		{
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		}
		catch(Exception e){}
		*/
		
	}
	public static void main(String args[])
   {
	/* Fail-safe in case you run this class with the intention of running the program */
      init();
   }
	
}