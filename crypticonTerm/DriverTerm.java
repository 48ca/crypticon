import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Scanner;

public class DriverTerm
{
	public static void main(String args[])
	{
		if(args.length == 3)
		{
			BufferedImage image;
			try {
				image = ImageIO.read( new File(args[1]));
				if(args[0].toLowerCase().equals("encode"))
				{
					image = StegTerm.encrypt(image,args[2]);
					System.out.println("Saving encoded image as Encoded"+args[1]);
					File outputFile = new File("Encoded"+args[1]);
					ImageIO.write(image,"png",outputFile);
				}
				else if(args[0].toLowerCase().equals("decode"))
				{
					System.out.println(StegTerm.decrypt(image));
				}
			}
			catch(Exception e) { System.out.println("Error reading file!"); System.exit(-1);}
		}
		else if(args.length == 2)
		{
			if(args[0].toLowerCase().contains("ffect"))
			{
				BufferedImage image = null;
				try	{
					image = ImageIO.read( new File(args[1]));
					System.out.print("Choose effect: (inverse, bw, grey, colorize, resize) ");
					int h = image.getHeight();
					int w = image.getWidth();
					switch(new Scanner(System.in).nextLine().toLowerCase())
					{
						case "resize":
							System.out.print("Width: ");
							int nw = new Scanner(System.in).nextInt();
							System.out.print("Height: ");
							int nh = new Scanner(System.in).nextInt();
							BufferedImage output = new BufferedImage(nw,nh,BufferedImage.TYPE_INT_ARGB);
							Graphics2D g2d = output.createGraphics();
							g2d.drawImage(image,0,0, nw, nh, null);
							g2d.dispose();
							image = output;
							break;
						case "inverse":
							image = Effects.Inverse(image,h,w);
							break;
						case "bw":
							image = Effects.BlackWhite(image,h,w);
							break;
						case "grey":
							image = Effects.Grayscale(image,h,w);
							break;
						case "colorize":
							image = Effects.Colorize(image,h,w);
							break;
					}
					System.out.println("Saving encoded image as Encoded"+args[1]);
					File outputFile = new File("Encoded"+args[1]);
					ImageIO.write(image,"png",outputFile);
				}
				catch(Exception e) { System.out.println("Error reading file!"); System.exit(-1);}
				
			}
			else main(new String[]{args[0], args[1], ""});
		}
		else if(args.length > 3)
		{
			if(args[0].toLowerCase().equals("decode"))
			{
				for(int i=1;i<args.length;i++)
				{
					try
					{
						BufferedImage image = ImageIO.read(new File(args[i]));
						System.out.println(args[i] + ":  " + StegTerm.decrypt(image));
					}
					catch(Exception e) { System.out.println("Error reading file: " + args[i]); }
				}
			}
			else
			{
				String argsString = args[2];
				for(int i=3;i<args.length;i++)
					argsString+=" "+args[i];
				main(new String[]{args[0], args[1], argsString});
			}
		}
		else
		{
			System.out.println("usage: command[encode/decode/effects] file[PNG/JPG] [encoding: text]");
			System.exit(-1);
		}
	}
}
