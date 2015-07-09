   //Unit IV: Lab09 driver modified
   import javax.swing.JFrame;
   //
   import javax.swing.JOptionPane;
   
   //Dependencies found for Main Business
   import java.util.*;
   import java.io.*;
   import java.awt.Desktop;
   /*Dependencies found in PixLab Driver
   import java.awt.*;
   import java.awt.event.*;
   import javax.swing.*;
   import java.awt.image.*;
   import javax.swing.border.*;*/
   public class Driver09
   {
      public static void main(String[] args) throws Exception
      {
         /****************CODENAME:MAIN BUSINESS*************************/
         Object[] options = { "ENCODE", "DECODE" };
         int code=JOptionPane.showOptionDialog(null, "Encode or decode?", "Crypticon",
            JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
            null, options, options[0]);
            
         Desktop.getDesktop().open(new File("c:\\")); //(file=?)
   	  
      
         if (code==0){
            //encode
         }
         
         else{
            //decode
         }
         /****************************************************************/
         
         
         
         /**********Original Driver (Except the name was different)*******/
         //so I guess we would have this after our main business... idek
         //
         JFrame frame = new JFrame("CRYPTICON");
         frame.setSize(500, 150);
         frame.setLocation(150, 100);
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         //
         frame.setContentPane(new Panel09());
         //
         frame.setVisible(true);
         }
         /****************************************************************/
         
         
         
         
         
         
         //stegonography method I put into the pix lab that loops through the pixels and print out location and RGB
         //it doesn't work in this file.
         //missing dependencies.
         /*
           public void steg(DigitalPicture picture)
           {
           String tmp = new String("");
           String otmp = new String("");
           int width=(int)picture.getWidth();
           int height=(int)picture.getHeight();
             for (colIndex=0;colIndex<height;colIndex++){
               for (rowIndex=0;rowIndex<height;rowIndex++){
                  Pixel pixel = new Pixel(picture,colIndex,rowIndex);
                  tmp=(""+pixel.getRed()+" "+pixel.getGreen()+" "+pixel.getBlue()+" "+rowIndex+" "+colIndex);
                  if (tmp!=otmp)System.out.println(tmp);
                  otmp = tmp;
                  }
               }
          */
          
      
   }