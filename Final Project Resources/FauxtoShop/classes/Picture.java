import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

/**
 * A class that represents a picture.  This class inherits from 
 * SimplePicture and allows the student to add functionality to
 * the Picture class.  
 * 
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture 
{
///////////////////// constructors //////////////////////////////////

/**
* Constructor that takes no arguments 
*/
   public Picture ()
   {
   /* not needed but use it to show students the implicit call to super()
   * child constructors always call a parent constructor 
   */
      super();  
   }

/**
* Constructor that takes a file name and creates the picture 
* @param fileName the name of the file to create the picture from
*/
   public Picture(String fileName)
   {
   // let the parent class handle this fileName
      super(fileName);
   }

/**
* Constructor that takes the width and height
* @param height the height of the desired picture
* @param width the width of the desired picture
*/
   public Picture(int height, int width)
   {
   // let the parent class handle this width and height
      super(width,height);
   }

/**
* Constructor that takes a picture and creates a 
* copy of that picture
* @param copyPicture the picture to copy
*/
   public Picture(Picture copyPicture)
   {
   // let the parent class do the copy
      super(copyPicture);
   }

/**
* Constructor that takes a buffered image
* @param image the buffered image to use
*/
   public Picture(BufferedImage image)
   {
      super(image);
   }

////////////////////// methods ///////////////////////////////////////

   public Picture getPicture()
   {
      return this;
   }	

/**
* Method to return a string with information about this picture.
* @return a string with information about the picture such as fileName,
* height and width.
*/
   public String toString()
   {
      String output = "Picture, filename " + getFileName() + 
         " height " + getHeight() 
         + " width " + getWidth();
      return output;
   }

/** 
* Method to set the blue to 0 
* 
*/
   public void zeroBlue()
   {
      Pixel[][] pixels = this.getPixels2D();
      for (Pixel[] rowArray : pixels)
      {
         for (Pixel pixelObj : rowArray)
         {
            pixelObj.setBlue(0);
         }
      }
   }

 
/** 
* Method to keep just the blue 
* 
*/       
   public void keepOnlyBlue()
   {
   
   }

/** 
* Method to negate the picture
* 
*/
   public void negate()
   {
   
   }		

/** 
* Method to grayscale the picture
*/
   public void grayscale()
   {
   
   }

   public void fixUnderwater()
   {
   
   } 
	  /** Method that mirrors the picture around a 
 * vertical mirror in the center of the picture
 * from left to right */
   public void mirrorVertical()
   {
      Pixel[][] pixels = this.getPixels2D();
      Pixel leftPixel = null;
      Pixel rightPixel = null;
      int width = pixels[0].length;
      for (int row = 0; row < pixels.length; row++)
      {
         for (int col = 0; col < width / 2; col++)
         {
            leftPixel = pixels[row][col];
            rightPixel = pixels[row][width - 1 - col];
            rightPixel.setColor(leftPixel.getColor());
         }
      } 
   }

/** Mirror just part of a picture of a temple */
   public void mirrorTemple()
   {
   
   }

/** copy from the passed fromPic to the
 * specified startRow and startCol in the
 * current picture
 * @param fromPic the picture to copy from
 * @param startRow the start row to copy to
 * @param startCol the start col to copy to
 */
   public void copy(Picture fromPic, 
              int startRow, int startCol)
   {
      Pixel fromPixel = null;
      Pixel toPixel = null;
      Pixel[][] toPixels = this.getPixels2D();
      Pixel[][] fromPixels = fromPic.getPixels2D();
      for (int fromRow = 0, toRow = startRow; 
      fromRow < fromPixels.length &&
      toRow < toPixels.length; 
      fromRow++, toRow++)
      {
         for (int fromCol = 0, toCol = startCol; 
         fromCol < fromPixels[0].length &&
         toCol < toPixels[0].length;  
         fromCol++, toCol++)
         {
            fromPixel = fromPixels[fromRow][fromCol];
            toPixel = toPixels[toRow][toCol];
            toPixel.setColor(fromPixel.getColor());
         }
      }   
   }

/** Method to create a collage of several pictures */
   public void createCollage()
   {
      Picture flower1 = new Picture("flower1.jpg");
      Picture flower2 = new Picture("flower2.jpg");
      this.copy(flower1,0,0);
      this.copy(flower2,100,0);
      this.copy(flower1,200,0);
      Picture flowerNoBlue = new Picture(flower2);
      flowerNoBlue.zeroBlue();
      this.copy(flowerNoBlue,300,0);
      this.copy(flower1,400,0);
      this.copy(flower2,500,0);
      this.mirrorVertical();
      this.write("collage.jpg");
   }


/** Method to show large changes in color 
 * @param edgeDist the distance for finding edges
 */
   public void edgeDetection(int edgeDist)
   {
      Pixel leftPixel = null;
      Pixel rightPixel = null;
      Pixel[][] pixels = this.getPixels2D();
      Color rightColor = null;
      for (int row = 0; row < pixels.length; row++)
      {
         for (int col = 0; 
         col < pixels[0].length-1; col++)
         {
            leftPixel = pixels[row][col];
            rightPixel = pixels[row][col+1];
            rightColor = rightPixel.getColor();
            if (leftPixel.colorDistance(rightColor) > edgeDist)
               leftPixel.setColor(Color.BLACK);
            else
               leftPixel.setColor(Color.WHITE);
         }
      }
   }

    /** 
* Method to posterize the picture
* 
*/
   public void posterize()
   {
     
   }
	
/** 
* Method to pixelate the picture (Donky Kong effect)
* 
*/
   public void pixelate()
   {
   
   }
	
	
/** 
* Method to sepia tone the picture
* 
*/
   public void sepiatone()
   {
     
   }		
	
/** 
* Method to blur the picture
* 
*/
   public void blur()
   {
   }			


/** 
* Method to calculate the distance between two colors
* 
*/        
   public double distance(Pixel p, Color c)
   {
   
      return Math.sqrt( Math.pow ( Math.abs (p.getRed()-c.getRed() ),2 ) +
                   Math.pow ( Math.abs (p.getGreen()-c.getGreen() ),2 ) +
             		 Math.pow ( Math.abs (p.getBlue()-c.getBlue() ),2 ) );	
   }

/** 
* Method to color splash a picture
* 
*/  	
   public void colorSplash(Color c)//253 83 50
   {
   	
   }	


     /** 
* Method to modify red by some parameter 
* 
*/  
   public void modifyRed(int x)
   {
      Pixel[][] pixels = this.getPixels2D();
      for (Pixel[] rowArray : pixels)
      {
         for (Pixel pixelObj : rowArray)
         {
            pixelObj.setRed((int)(255*(x/100.0)));
         }
      }
   }

 /** Hide a black and white message in the current
    * picture by changing the red to even and then
    * setting it to odd if the message pixel is black 
    * @param messagePict the picture with a message
    */
   public void encode(Picture messagePict)
   {
   }
 /**
   * Method to decode a message hidden in the
   * red value of the current picture
   * @return the picture with the hidden message
   */
   public Picture decode()
   {
      return null;
   }

/* Main method for testing - each class in Java can have a main 
* method 
*/
   public static void main(String[] args) 
   {
   // Picture beach = new Picture("beach.jpg");
   //     beach.explore();
   //     beach.zeroBlue();
   //     beach.explore();
   }

} // this } is the end of class Picture, put all new methods before this
