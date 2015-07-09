/****************************************************************
Steg contains methods for encrypting and decrypting messages in
images, as well as other methods that aid in those tasks.
 
@author Susanna Bradbury
@version 1.0
****************************************************************/

import java.lang.Math;
import java.awt.image.BufferedImage;

public class Steg{
   public BufferedImage encrypt(BufferedImage picture, String message){
      
      /****************************************************************
      Finds writeable regions of the image, then encodes on them.
      ****************************************************************/
	  findWriteable(picture);
	  int width=0;
	  int height=0;
	  int imageType=0;
	  BufferedImage encryptedImage = new BufferedImage(width, height, imageType);
	  return encryptedImage;
      
   }
   public String decrypt(BufferedImage picture){
      
      /****************************************************************
      Finds writeable regions of the image, then decodes from them.
      ****************************************************************/
	  findWriteable(picture);
	  String decryptedMessage = new String("");
	  return decryptedMessage;
      
   }
   public int findMiddle(BufferedImage picture){
      
      /****************************************************************
      Finds the middle pixel(s) (horizontally and vertically).
      ****************************************************************/
      int pixel=0;
	  return pixel;
	  
   }
   public int[] findBlank(BufferedImage picture){
      
      /****************************************************************
      Finds the "blank" regions of the image, areas of identical color,
	  that should not be encoded on or read from.  This helps determine
	  writeable regions of the image.
      ****************************************************************/
	  int[] blankPixels = new int[0];
	  return blankPixels;
      
   }
   public int[] findWriteable(BufferedImage picture){
   
      /****************************************************************
      Uses findMiddle and findBlank to determine the writeable regions
	  of the image.
      ****************************************************************/
      int[] pixelArray = new int[0];
	  findBlank(picture);
	  findMiddle(picture);
	  return pixelArray;
	  
   }
}