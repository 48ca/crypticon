import java.util.*;
import java.io.*;
import javax.swing.JOptionPane;
import java.awt.Desktop;

public class Driver{
   public static void main(String[] args) throws Exception{
      Object[] options = { "ENCODE", "DECODE" };
      int code=JOptionPane.showOptionDialog(null, "Encode or decode?", "Crypticon",
         JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
         null, options, options[0]);
         
      //Desktop.getDesktop().open(new File("c:\\")); (file=?)
	  
   
      if (code==0){
         //encode
      }
      
      else{
         //decode
      }
   }
}
