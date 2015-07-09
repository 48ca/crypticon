import java.util.*;
import java.io.*;
import javax.swing.JOptionPane;
import java.awt.Desktop;

public class Driver{
   public static void main(String[] args) throws Exception{
      /*
	  Object[] options = { "ENCODE", "DECODE" };
      int code=JOptionPane.showOptionDialog(null, "Encode or decode?", "Crypticon",
         JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
         null, options, options[0]);
	
	ENCODING AND DECODING SELECTION TO BE DONE ELSEWHERE
	right?
		 */
         
      //Desktop.getDesktop().open(new File("c:\\")); (file=?)
	  int code=0;
	  //if you want, you can pass a file through here
      Display.init();
   }
}
