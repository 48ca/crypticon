import java.lang.Math;
import java.awt.image.BufferedImage;
import java.nio.charset.Charset;
import java.awt.Color;

public class Test{
	public static void main(String[] args){
	
		//definitions
		String stop="stop";
		char[] bytes_in=stop.toCharArray();
		int[] bits_in=new int[bytes_in.length*8];
		
		//read bytes to bits
		for (int c=0;c<(bytes_in.length);c++){
			for (int d=7;d>=0;d--){
				bits_in[(c*8)+d]=(bytes_in[c]&1);
				bytes_in[c]>>>=1;
			}
		}
		
		//read bits to bytes
		char[] bytes_out=new char[bytes_in.length];
		for (int c=0;c<bytes_out.length;c++){
			bytes_out[c]=0;
			for(int i=0;i<8;i++){
				bytes_out[c]<<=1;
				bytes_out[c]+=(char)bits_in[c*8+i];
			}
		}
		
		//create string
		String message="";
		for(int i=0;i<bytes_out.length;i++)
		{
			message+=bytes_out[i];
		}
		
		//print string
		System.out.println(message);
	}
}