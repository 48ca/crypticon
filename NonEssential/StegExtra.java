/****************************************************************
Steg contains methods for encrypting and decrypting messages in
images, as well as other methods that aid in those tasks.
 
@author Susanna Bradbury
@version 06/01/2014
****************************************************************/

/*

TO BE FIXED:
	LAST CHAR FLIPPED
	ISSUE: DIV BY 3 MAKING IT OFF BY 1 to 2 BITS
	
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
		//String message=Display.getText(1);
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
		int extraIter=extra;
		if (length%3==0){
			extraIter=extra;
		}
		//int extra=((length/3)+(length%3))%width;
		int startx=0;
		int starty=y-(rows/2);
		if (starty<5){
			//show popup notifying user that message is too long for image
			//ask user to alter message or select larger image
			int totalSize=(width*(height-2))*3;
			int charNum=(length-totalSize)/8;
			Display.lengthError(charNum);
			return picture;
		}
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
		if (extra==0){
			Color c=new Color(encryptedImage.getRGB(startx,starty-1));
			int red=c.getRed();
			int green=c.getGreen();
			int blue=c.getBlue();
			int redbit=(red&1);
			int greenbit=(green&1);
			int bluebit=(blue&1);
			int messagebit=bits[messloc];
			int dif=redbit-messagebit;
			int newred=red-dif;
			Color n=new Color(red,green,blue);
			if ((length-messloc)<2){
				if ((length-messloc)<1){
					n=new Color(newred,green,blue);
				}
				messagebit=bits[messloc+1];
				dif=greenbit-messagebit;
				int newgreen=green-dif;
				n=new Color(newred,newgreen,blue);
			}
			else{
				messagebit=bits[messloc+1];
				dif=greenbit-messagebit;
				int newgreen=green-dif;
				n=new Color(newred,newgreen,blue);
				messagebit=bits[messloc+2];
				dif=bluebit-messagebit;
				int newblue=blue-dif;
				n=new Color(newred,newgreen,newblue);
			}
			int newcolor=n.getRGB();
			encryptedImage.setRGB(startx,starty,newcolor);
			startx++;
		}
		startx=0;
		starty++;
		for (int j=0;j<extraIter;j++){
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
			Color n=new Color(red,green,blue);
			if ((length-messloc)<2){
					if ((length-messloc)<1){
						n=new Color(newred,green,blue);
					}
					messagebit=bits[messloc+1];
					dif=greenbit-messagebit;
					int newgreen=green-dif;
					n=new Color(newred,newgreen,blue);
			}
			else{
				messagebit=bits[messloc+1];
				dif=greenbit-messagebit;
				int newgreen=green-dif;
				n=new Color(newred,newgreen,blue);
				messagebit=bits[messloc+2];
				dif=bluebit-messagebit;
				int newblue=blue-dif;
				n=new Color(newred,newgreen,newblue);
			}
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
		if (length==0){
			return "";
		}
		System.out.println(""+length);
		int rows=(length/3)/width;
		int extra=(length/3)%width;
		//int extra=((length/3)+(length%3))%width;
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
				if ((length-messloc)<2){
					if ((length-messloc)<1){
						startx++;
						break;
					}
					bits[messloc+1]=greenbit;
					startx++;
					break;
				}
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
				if ((length-messloc)<2){
					if ((length-messloc)<1){
						startx++;
						break;
					}
					bits[messloc+1]=greenbit;
					startx++;
					break;
				}
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
		String value=(lengthVal+"stop");
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
		int iter=length/3;
		int iterPlus=length%3;
		int messloc=0;
		for (int i=0;i<(iter);i++){
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
		if (iterPlus==1){
			Color c=new Color(picture.getRGB(x,y));
			int red=c.getRed();
			int green=c.getGreen();
			int blue=c.getBlue();
			int redbit=(red&1);
			int valuebit=bits[messloc];
			int dif=redbit-valuebit;
			int newred=red-dif;
			Color n=new Color(newred,green,blue);
			int newcolor=n.getRGB();
			picture.setRGB(x,y,newcolor);
		}
		if (iterPlus==2){
			Color c=new Color(picture.getRGB(x,y));
			int red=c.getRed();
			int green=c.getGreen();
			int blue=c.getBlue();
			int redbit=(red&1);
			int greenbit=(green&1);
			int valuebit=bits[messloc];
			int dif=redbit-valuebit;
			int newred=red-dif;
			valuebit=bits[messloc+1];
			dif=greenbit-valuebit;
			int newgreen=green-dif;
			Color n=new Color(newred,newgreen,blue);
			int newcolor=n.getRGB();
			picture.setRGB(x,y,newcolor);
		}
		/*
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
		*/
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
		char[] bytes=new char[(width*3)/8];
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
			if (firstLine.contains("stop")){
				break;
			}
		}
		if (firstLine.length()>15){
			//show a popup stating that this image has not been encrypted
			Display.blankError();
			return 0;
		}
		int place=firstLine.length()-4;
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