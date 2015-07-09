   import java.util.*;
   import java.awt.image.BufferedImage;
   import java.io.*;

   public class  Steganography
   {
   
   	//start of application
      public static void main(String[] args) throws Exception
      {
         boolean encrypt=getEncryptDecrypt();
         String imgName= getImageName();
         BufferedImage img= LoadImage(imgName);
      
         if (encrypt)
         {
            String text= promptText();
            String newImageName= getImageName();
            BufferedImage newImage=Encrypt( img, text);
            SaveImage(newImage, newImageName);
         }
         else
         {
            System.out.println(Decrypt(img));
         }
      }
   	
      public static String getImageName() //prompts user for file name
      {
         return "";		
      }
   	
      public static boolean getEncryptDecrypt()//prompts user to choose E or D
      {
         return true;
      }
      public static String promptText() //text to encypt
      {
         return "";
      }
      public static BufferedImage Resize( BufferedImage image, int height, int width)// resizes the image
      {
         return null;
      }
   	
      public static String Decrypt(BufferedImage image)//method for decryption
      {
         return "";
      }
   	
      public static BufferedImage Encrypt(BufferedImage image, String text)//method for encryption
      {
         return null;
      }
   	
      public static BufferedImage LoadImage(String filename) throws IOException
      {
         return null;
      } 
   	
      public static void SaveImage(BufferedImage image, String filename) throws IOException
      {
      
      }
   }