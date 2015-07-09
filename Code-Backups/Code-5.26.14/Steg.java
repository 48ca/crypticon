/****************************************************************
Steg contains methods for encrypting and decrypting messages in
images, as well as other methods that aid in those tasks.
 
@author Susanna Bradbury
@version 05/26/2014
****************************************************************/

/*

TO BE FIXED:
	METHOD OF GETTING BYTES FROM MESSAGE STRING
		CURRENTLY: MULTIPLE NUMBERS PER BYTE ARRAY LOCATION
		(BYTES[3]=8 OR SO BITS, NEEDS TO BE 1
	CURRENTLY ENCRYPTING ONLY ON B, NEEDS TO USE ALGORITHM
	WRITE LENGTH TO FIRST LINE OF IMAGE
		NEED STOP KEY IN ORDER TO READ LENGTH
	
*/

import java.lang.Math;
import java.awt.image.BufferedImage;
import java.nio.charset.Charset;
import java.awt.Color;

public class Steg{
    public BufferedImage encrypt(BufferedImage picture){
      
		/****************************************************************
		Finds writeable regions of the image, then encodes on them.
		****************************************************************/
		String message=Display.getText();
		int width=picture.getWidth();
		int height=picture.getHeight();
		int imageType=picture.getType();
		BufferedImage encryptedImage = picture;
		int[] pix=findWriteable(picture);
		int x=pix[0];
		int y=pix[1];
		char[] bytes=message.toCharArray();
		boolean[] bits=new boolean[bytes.length*7];
		for (int c=0;c<(bytes.length);c++){
			for (int d=0;d<8;d++){
				bits[(c*8)+d]=(boolean)(bytes[c]&1);
				bytes[c]=bytes[c]>>1;
			}
		}
		int length=bits.length;
		int rows=length/width;
		int extra=length%width;
		int startx=0;
		//int startx=x-(extra/2)
		int starty=y-(rows/2);
		for (int k=0;k<rows;k++){
			for (int i=0;i<width;i++){
				Color c = new Color(encryptedImage.getRGB(startx,starty));
				int red = c.getRed();
				int green = c.getGreen();
				int blue = c.getBlue();
				boolean redbit=(boolean)red&1;
				boolean greenbit=(boolean)green&1;
				boolean bluebit=(boolean)blue&1;
				boolean messagebit=bits[(k*width)+i];
				int dif=(int)redbit-(int)messagebit;
				int newred=red-dif;
				boolean messagebit=bits[(k*width)+i+1];
				int dif=(int)greenbit-(int)messagebit;
				int newgreen=green-dif;
				boolean messagebit=bits[(k*width)+i+2];
				int dif=(int)bluebit-(int)messagebit;
				int newblue=blue-dif;
				encryptedImage.setRGB(startx,starty,newcolor);
				startx++;
				//fix iteration of messagebits
			}
			starty++;
		}
		startx=0;
		starty++;
		for (int j=0;j<extra;j++){
			int color=encryptedImage.getRGB(startx,starty);
			//RGB encryption key
			boolean colorbit=(boolean)color&1;
			boolean messagebit=bits[((starty-(y-(rows/2)))*width)+j];
			int dif=(int)colorbit-(int)messagebit;
			int newcolor=color-dif;
			encryptedImage.setRGB(startx,starty,newcolor);
			startx++;
		}
		writeLength(encryptedImage,length);
		return encryptedImage;
    }
    public String decrypt(BufferedImage picture){
      
		/****************************************************************
		Finds writeable regions of the image, then decodes from them.
		****************************************************************/
		//int length=Display.getLength();
		int rows=length/width;
		int extra=length%width;
		String decryptedMessage = new String("");
		byte[] bytes=new byte[length];
		int[] pix=findWriteable(picture);
		int x=pix[0];
		int y=pix[1];
		int width=picture.getWidth();
		int height=picture.getHeight();
		int startx=0;
		int starty=y-(rows/2);
		for (int k=0;k<rows;k++){
			for (int i=0;i<width;i++){
				int color=picture.getRGB(startx,starty);
				//RGB encryption key
				int colorbit=color%2;
				bytes[(k*width)+i]=colorbit;
				startx++;
			}
			starty++;
		}
		startx=0;
		starty++;
		for (int j=0;j<extra;j++){
			int color=picture.getRGB(startx,starty);
			//RGB
			int colorbit=color%2;
			bytes[((starty-(y-(rows/2)))*width)+j]=colorbit;
			startx++;
		}
		return decryptedMessage;
      
    }
 
    public double findMiddleHorizontal(BufferedImage picture){
      
		/****************************************************************
		Finds the median horizontal value.
		****************************************************************/
		int width=picture.getWidth();
		double x=width/2;
		return x;
	  
    }
    public double findMiddleVertical(BufferedImage picture){
      
		/****************************************************************
		Finds the median vertical value.
		****************************************************************/
		int height=picture.getHeight();
		double y=height/2;
		return y;
	  
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
		Uses findMiddle(Horizontal & Vertical) and findBlank to determine
		the writeable regions of the image.
		****************************************************************/
		int[] pixelArray = new int[0];
		//findBlank(picture);
		findMiddleHorizontal(picture);
		findMiddleVertical(picture);
		return pixelArray;
	  
    }
	public int writeLength(BufferedImage picture, int length){
   
		/****************************************************************
		Writes the length of the encrypted message to the first row of
		the image to enable decryption.  Stop key: '???'
		****************************************************************/
		
	  
    }
	public int findLength(BufferedImage picture){
   
		/****************************************************************
		Scans the first row of the image to determine the length of the
		encrypted message.
		****************************************************************/
		
	  
    }
}

/****************************************************************
int width=picture.getWidth();
		int height=picture.getHeight();
		if (width%2==0)
			int[] x={(width/2)};
		else
			int[] x={(int)(width/2),(int)((width/2)+1)};
		if (height%2==0)
			int[] y={(height/2)};
		else
			int[] y={(int)(height/2),(int)((height/2)+1)};
		int[][] pixels=new int[4][2];
		for (int i=0;i<width.length;i++){
			pixels[i][1]=x[i];
		}
		for (int i=0;i<height.length;i++){
			pixels[i][2]=y[i];
		}
		return pixel;
****************************************************************/