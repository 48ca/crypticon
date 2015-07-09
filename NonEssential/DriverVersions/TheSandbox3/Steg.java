/****************************************************************
Steg contains methods for encrypting and decrypting messages in
images, as well as other methods that aid in those tasks.
 
@author Susanna Bradbury
@version 05/31/2014
****************************************************************/

/*

TO BE FIXED:
	STOP KEY (LAST CHAR FLIPPED CAUSING ISSUE)
	LONG MESSAGE ERRORS
	LAST CHAR FLIPPED
	
*/

import java.lang.Math;
import java.awt.image.BufferedImage;
import java.nio.charset.Charset;
import java.awt.Color;

public class Steg{
    public static BufferedImage encrypt(BufferedImage picture){
      
		/****************************************************************
		Encodes a message in an image.
		****************************************************************/
		String message=Display.getText();
		int width=picture.getWidth();
		int height=picture.getHeight();
		BufferedImage encryptedImage = picture;
		int x=width/2;
		int y=height/2;
		char[] bytes=message.toCharArray();
		int[] bits=new int[bytes.length*8];
		for (int c=0;c<(bytes.length);c++){
			for (int d=7;d>=0;d--){
				bits[(c*8)+d]=(bytes[c]&1);
				bytes[c]>>>=1;
			}
		}
		int length=bits.length;
		int rows=(length/3)/width;
		int extra=(length/3)%width;
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
		if (extra>0){
			startx=0;
			//starty++;
		}
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
    public static String decrypt(BufferedImage picture){
      
		/****************************************************************
		Decodes a message from an image.
		****************************************************************/
		String decryptedMessage = new String("");
		int width=picture.getWidth();
		int height=picture.getHeight();
		int x=width/2;
		int y=height/2;
		int length=readLength(picture);
		System.out.println(""+length);
		int rows=(length/3)/width;
		int extra=(length/3)%width;
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
		if (extra>0){
			startx=0;
			starty++;
		}
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
		for (int c=0;c<bytes.length;c++){
			bytes[c]=0;
			for(int i=0;i<8;i++){
				bytes[c]<<=1;
				bytes[c]+=(char)bits[c*8+i];
			}
		}
		for(int i=0;i<bytes.length;i++)
		{
			decryptedMessage=(decryptedMessage+bytes[i]);
		}
		return decryptedMessage;
      
    }
	public static BufferedImage writeLength(BufferedImage picture, int lengthVal){
   
		/****************************************************************
		Writes the length of the encrypted message to the first row of
		the image to enable decryption.
		****************************************************************/
		String value=(""+lengthVal);
		int size=value.length();
		int width=picture.getWidth();
		int x=0;
		int y=0;
		char[] bytes=value.toCharArray();
		int[] bits=new int[bytes.length*8];
		for (int c=0;c<(bytes.length);c++){
			for (int d=7;d>=0;d--){
				bits[(c*8)+d]=(bytes[c]&1);
				bytes[c]>>>=1;
			}
		}
		int length=bits.length;
		int messloc=0;
		for (int i=0;i<(bits.length/3);i++){
			Color c=new Color(picture.getRGB(x,y));
			int red=c.getRed();
			int green=c.getGreen();
			int blue=c.getBlue();
			int redbit=(red&1);
			int greenbit=(green&1);
			int bluebit=(blue&1);
			int valuebit=bits[messloc];
			int dif=redbit-valuebit;
			int newred=red-dif;
			valuebit=bits[messloc+1];
			dif=greenbit-valuebit;
			int newgreen=green-dif;
			valuebit=bits[messloc+2];
			dif=bluebit-valuebit;
			int newblue=blue-dif;
			Color n=new Color(newred,newgreen,newblue);
			int newcolor=n.getRGB();
			picture.setRGB(x,y,newcolor);
			x++;
			messloc+=3;
		}
		String stop="stop";
		bytes=stop.toCharArray();
		bits=new int[bytes.length*8];
		for (int c=0;c<(bytes.length);c++){
			for (int d=7;d>=0;d--){
				bits[(c*8)+d]=(bytes[c]&1);
				bytes[c]>>>=1;
			}
		}
		messloc=0;
		for (int i=0;i<8;i++){
			Color c=new Color(picture.getRGB(x,y));
			int red=c.getRed();
			int green=c.getGreen();
			int blue=c.getBlue();
			int redbit=(red&1);
			int greenbit=(green&1);
			int bluebit=(blue&1);
			int valuebit=bits[messloc];
			int dif=redbit-valuebit;
			int newred=red-dif;
			valuebit=bits[messloc+1];
			dif=greenbit-valuebit;
			int newgreen=green-dif;
			valuebit=bits[messloc+2];
			dif=bluebit-valuebit;
			int newblue=blue-dif;
			Color n=new Color(newred,newgreen,newblue);
			int newcolor=n.getRGB();
			picture.setRGB(x,y,newcolor);
			x++;
			messloc+=3;
		}
	return picture;
	}
	
    
	public static int readLength(BufferedImage picture){
   
		/****************************************************************
		Scans the first row of the image to determine the length of the
		encrypted message.
		****************************************************************/
		int decryptedLength=0;
		int width=picture.getWidth();
		int x=0;
		int y=0;
		int[] bits=new int[width*3];
		int messloc=0;
		for (int i=0;i<(width);i++){
			Color c=new Color(picture.getRGB(x,y));
			int red=c.getRed();
			int green=c.getGreen();
			int blue=c.getBlue();
			int redbit=(red&1);
			int greenbit=(green&1);
			int bluebit=(blue&1);
			bits[messloc]=redbit;
			bits[messloc+1]=greenbit;
			bits[messloc+2]=bluebit;
			x++;
			messloc+=3;
		}
		char[] bytes=new char[(width)/8];
		for (int c=0;c<bytes.length;c++){
			bytes[c]=0;
			for(int i=0;i<8;i++){
				bytes[c]<<=1;
				bytes[c]+=(char)bits[c*8+i];
			}
		}
		String firstLine="";
		String possibleStop="";
		for(int i=0;i<bytes.length;i++)
		{
			firstLine=(firstLine+bytes[i]);
			if (firstLine.contains("sto")){
				break;
			}
		}
		int place=firstLine.length()-3;
		String lineMinusStop=firstLine.substring(0,place);
		decryptedLength=Integer.parseInt(lineMinusStop);
		return decryptedLength;
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