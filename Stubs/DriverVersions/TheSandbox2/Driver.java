    import javax.swing.JFrame;
    //
    import javax.swing.JOptionPane;
     
    //Dependencies found for "Main Business"
    import java.util.*;
    import java.io.*;
    import java.awt.Desktop;
    /*Dependencies found in PixLab Driver
    import java.awt.*;
    import java.awt.event.*;
    import javax.swing.*;
    import java.awt.image.*;
    import javax.swing.border.*;*/
    public class Driver
    {
        public static void main(String[] args) //throws Exception
        {
		/**********************************************************
		Open main program, no image loaded. Prompt the user to select an image.
		Afterwards, allow the use of basic editing tools.
			HOWEVER, upon first attempted edit, notify the user that any changes
			to the image will destroy possible encrypted data.
				If 'proceed' is selected, warning is ignored.
				If 'cancel' is selected, close the dialog box; do nothing.
		On user request, hide data inside image.
			When 'steg' method is invoked:
			Prompt user for encryption or decryption.
				If encryption is chosen:
					Prompt user with a text box to enter text to be encoded 
					OR have them select a file to be read with an ActionListener.
					Encrypt message
				If decryption is chosen:
					Decrypt image
					Display text with a text field
						Prompt user if it wants to save the file OR copy to clipboard.
						If save file is chosen:
							Prompt user to create a file and proceed to write the data to it.
							Do NOT remove the JOptionPane message upon selection.
					When requested, close JOptionPane.
		System.exit(0);
		**********************************************************/
		}
		public static void steg(Picture picture, int mode, String data)
		{
			/**********************************************************
			if mode = 1 (encrypt)
			{
				Invoke encrypt(Picture picture, int mode, String data);
			}
			else if mode = 0 (decrypt)
			{
				Invoke decrypt(Picture picture, int mode);
			}
			else
			{
				System.out.println("Error in mode selection occured.");
				System.exit(-1);
			}
			**********************************************************/
		}
		public static Picture encrypt(Picture picture, int mode, String data)
		{
			/**********************************************************
			Read/parse data into binary values
				Convert ascii letters into their 8 bit counterparts.
			Loop through all pixels
				Test if bits are even or odd to see what the least significant bit is.
				Change the bit if it doesn't match with the bit of the given string.
			A progress bar, if the steganography happens to take a long time, might be good to have.	
				Bit count can be guessed by using the dimensions of the given image.
			Return final image.
			**********************************************************/
		}
		public static String decrypt(Picture picture, int mode)
		{
			/**********************************************************
			Loop through all pixels.
			Read least significant bit and generate the string encrypted in the image.
			Progress bar if necessary.
			Return finished string.
				Dimensions could be a problem as the string in the image could be much shorter
				than what the image can accommodate.
				 -- GiberishDetection™ could be something we need.
			**********************************************************/
		}
	}
			