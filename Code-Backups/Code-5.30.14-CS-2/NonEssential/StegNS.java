/****************************************************************
Steg contains methods for encrypting and decrypting messages in
images, as well as other methods that aid in those tasks.
 
@author Susanna Bradbury
@version 05/26/2014
****************************************************************/

/*

TO BE FIXED:
	WRITE LENGTH TO FIRST LINE OF IMAGE
		NEED STOP KEY IN ORDER TO READ LENGTH
	
*/

import java.lang.Math;
import java.awt.image.BufferedImage;
import java.nio.charset.Charset;
import java.awt.Color;

public class StegNS{
    public BufferedImage encrypt(BufferedImage picture){
      
		/****************************************************************
		Finds writeable regions of the image, then encodes on them.
		****************************************************************/
		String message=Display.getText();
		int width=picture.getWidth();
		int height=picture.getHeight();
		BufferedImage encryptedImage = picture;
		int x=width/2;
		int y=height/2;
		char[] bytes=message.toCharArray();
		int[] bits=new int[bytes.length*7];
		for (int c=0;c<(bytes.length);c++){
			for (int d=0;d<8;d++){
				bits[(c*8)+d]=(bytes[c]&1);
				bytes[c]>>=1;
			}
		}
		int length=bits.length;
		int rows=length/width;
		int extra=length%width;
		int startx=0;
		int starty=y-(rows/2);
		int messloc=0;
		for (int k=0;k<rows;k++){
			for (int i=0;i<width;i++){
				Color c=new Color(encryptedImage.getRGB(startx,starty));
				int red=c.getRed();
				int green=c.getGreen();
				int blue=c.getBlue();
				int redbit=(red&1);
				int greenbit=(green&1);
				int bluebit=(blue&1);
				int messagebit=bits[messloc];
				int dif=redbit-messagebit;
				int newred=red-dif;
				messagebit=bits[messloc+1];
				dif=greenbit-messagebit;
				int newgreen=green-dif;
				messagebit=bits[messloc+2];
				dif=bluebit-messagebit;
				int newblue=blue-dif;
				Color n=new Color(newred,newgreen,newblue);
				int newcolor=n.getRGB();
				encryptedImage.setRGB(startx,starty,newcolor);
				startx++;
				messloc+=3;
			}
			starty++;
		}
		startx=0;
		starty++;
		for (int j=0;j<extra;j++){
			Color c=new Color(encryptedImage.getRGB(startx,starty));
			int red=c.getRed();
			int green=c.getGreen();
			int blue=c.getBlue();
			int redbit=(red&1);
			int greenbit=(green&1);
			int bluebit=(blue&1);
			int messagebit=bits[messloc];
			int dif=redbit-messagebit;
			int newred=red-dif;
			messagebit=bits[messloc+1];
			dif=greenbit-messagebit;
			int newgreen=green-dif;
			messagebit=bits[messloc+2];
			dif=bluebit-messagebit;
			int newblue=blue-dif;
			Color n=new Color(newred,newgreen,newblue);
			int newcolor=n.getRGB();
			encryptedImage.setRGB(startx,starty,newcolor);
			startx++;
			messloc+=3;
		}
		writeLength(encryptedImage,length);
		return encryptedImage;
    }
    public String decrypt(BufferedImage picture){
      
		/****************************************************************
		Finds writeable regions of the image, then decodes from them.
		****************************************************************/
		String decryptedMessage = new String("");
		int width=picture.getWidth();
		int height=picture.getHeight();
		int x=width/2;
		int y=height/2;
		int length=readLength(picture);
		int rows=length/width;
		int extra=length%width;
		int startx=0;
		int starty=y-(rows/2);
		int[] bits=new int[length];
		int messloc=0;
		for (int k=0;k<rows;k++){
			for (int i=0;i<width;i++){
				Color c=new Color(picture.getRGB(startx,starty));
				int red=c.getRed();
				int green=c.getGreen();
				int blue=c.getBlue();
				int redbit=(red&1);
				int greenbit=(green&1);
				int bluebit=(blue&1);
				bits[messloc]=redbit;
				bits[messloc+1]=greenbit;
				bits[messloc+2]=bluebit;
				startx++;
				messloc+=3;
			}
			starty++;
		}
		startx=0;
		starty++;
		for (int j=0;j<extra;j++){
			Color c=new Color(picture.getRGB(startx,starty));
				int red=c.getRed();
				int green=c.getGreen();
				int blue=c.getBlue();
				int redbit=(red&1);
				int greenbit=(green&1);
				int bluebit=(blue&1);
				bits[messloc]=redbit;
				bits[messloc+1]=greenbit;
				bits[messloc+2]=bluebit;
				startx++;
				messloc+=3;
		}
		char[] bytes=new char[length/8];
		for (int c=0;c<(bytes.length);c++){
			int sum=0;
			for (int d=1;d<10000000;d+=d*10){
				sum+=bits[(c*8)+d]*d;
			}
			bytes[c]=(char)sum;
		}
		decryptedMessage=bytes.toString();
		return decryptedMessage;
      
    }
	public int writeLength(BufferedImage picture, int length){
   
		/****************************************************************
		Writes the length of the encrypted message to the first row of
		the image to enable decryption.  Stop key: '???'
		****************************************************************/
		return 0;
		
    }
	public int readLength(BufferedImage picture){
   
		/****************************************************************
		Scans the first row of the image to determine the length of the
		encrypted message.
		****************************************************************/
		return 0;
	  
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