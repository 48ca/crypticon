import java.util.*;
import java.io.*;
import javax.swing.JOptionPane;
import java.awt.Desktop;

public class Driver1{
   public static void main(String[] args) throws Exception{
      Object[] options = { "ENCODE", "DECODE" };
      JOptionPane.showOptionDialog(null, "Encode or decode?", "Crypticon",
         JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
         null, options, options[0]);
   Desktop.getDesktop().open(new File("c:\\"));
   }
}