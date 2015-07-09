/****************************************************************
Effects contains methods for editing pictures in the
steganography GUI.

@author Pranav Ramanan
@version 0.0
****************************************************************/

import java.util.*; 
import java.awt.image.BufferedImage; 
import java.io.*; 

public class Effects
{
   public static int FindColor(BufferedImage img, int x, int y)
   {
      
	  int rgb=img.getRGB(x, y);
      return rgb;
      /****************************************************************
      Returns the RGB colors of the chosen pixel.
      ****************************************************************/
	  
   }
      
   public static BufferedImage Resize(BufferedImage img, int h, int w, int color)
   {
      
	  BufferedImage newImage = new BufferedImage(h, w, BufferedImage.TYPE_INT_RGB);
      newImage.setRGB(h, w, color);
      return newImage;
      /****************************************************************
      Creates new image and sets color of pixels in new image.
      ****************************************************************/
      
   }
   
   public static BufferedImage Inverse(BufferedImage img, int h, int w, int color)
   {
      
	  BufferedImage newImage = new BufferedImage(h, w, BufferedImage.TYPE_INT_RGB);
      int invColor=0;
      newImage.setRGB(h, w, invColor);
      return newImage;
      /****************************************************************
      Creates new image that is opposite color of old image
      ****************************************************************/
      
   }
   
   public static BufferedImage Fade(BufferedImage img, int h, int w, int color)
   {
      
	  BufferedImage newImage = new BufferedImage(h, w, BufferedImage.TYPE_INT_RGB);
      int shade=0;
      color=color*shade;
      newImage.setRGB(h, w, shade);
      return newImage;
      /****************************************************************
      Takes the image and slightly changes the color of the 
      pixels to give it an opaque look.
      ****************************************************************/
	  
   }
   
   public static BufferedImage Tint(BufferedImage img, int h, int w, int tint)
   {
      
	  BufferedImage newImage = new BufferedImage(h, w, BufferedImage.TYPE_INT_RGB);
      int color=0;
      int newColor=color+tint;
      newImage.setRGB(h, w, newColor);
      return newImage;
      /****************************************************************
      Takes the image and adds a color to each of the pixels 
      to tint the image.
      ****************************************************************/
      
   }




}