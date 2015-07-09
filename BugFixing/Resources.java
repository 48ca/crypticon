import java.io.*;
import javax.swing.JOptionPane;
import java.awt.Desktop;

/****************************************************************
Resources contains methods that display help or extra information
about CRYPTICON and its developers.

@author James Houghton
@version 06/05/2014
****************************************************************/
public class Resources
{
	/****************************************************************
	Reads information about the authors from a file. Displays the loaded
	text with a JOptionPane message dialog box.
	****************************************************************/
	public static void displayInfo()
	{
		InputStream stream = Display.class.getResourceAsStream("resources/authors.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(stream));
		String line = null;
		String message = "";
		try{
		while ((line=br.readLine())!=null)
		{
			message+=line;
			message+="\n";
		}
		}
		catch(IOException e){message="The file containing the information about the authors is missing!";};
		JOptionPane.showMessageDialog(null,message,"About the authors...",JOptionPane.INFORMATION_MESSAGE);
	}
	/****************************************************************
	Loads the CRYPTICON User Manual and attemps to open the document.
	If no third-party loader is present, notify the user.
	****************************************************************/
	public static void showHelp()
	{
		if(JOptionPane.showConfirmDialog(null,"Open CRYPTICON User Manual now?","Help",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
		{
			if(Desktop.isDesktopSupported())
			{
				try 
				{
					
					/*
					InputStream is = Display.class.getResourceAsStream("resources/manual.pdf");
					byte[]data = new byte[is.available()];
					is.read(data);
					File pdf = File.createTempFile("tmpmanual",".pdf");
					FileOutputStream fos = new FileOutputStream(pdf);
					fos.write(data);
					fos.flush();
					is.close();
					fos.close();
					pdf.deleteOnExit();
					*/
					File pdf = new File("resources/CRYPTICON_UserManual.pdf");
					try{
					Desktop.getDesktop().open(pdf);}
					catch(IllegalArgumentException e){JOptionPane.showMessageDialog(null,"Manual not found!","Error!",JOptionPane.ERROR_MESSAGE);}
				} catch (IOException ex) {JOptionPane.showMessageDialog(null,"Cannot open manual!","Error!",JOptionPane.ERROR_MESSAGE);}
			}
		}
	}
}