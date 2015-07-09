//this is Lab09's panel that I'm just gonna edit a bit.
   import javax.swing.*;
   import java.awt.*;
   import java.awt.event.*;
   public class Panel09 extends JPanel
   {
      JLabel output;
      public Panel09()
      {
         setLayout(new BorderLayout());
      
         output = new JLabel("------", SwingConstants.CENTER);
         add(output, BorderLayout.NORTH);
      
         JPanel panel = new JPanel();
         panel.setLayout(new FlowLayout());
         //null -> name
         addButton(panel, null, new Listener1());
         addButton(panel, null, new Listener2());
         addButton(panel, null, new Listener3());
         addButton(panel, null, new Listener4());
         add(panel, BorderLayout.SOUTH);
      }
      private void addButton(JPanel p, String s, ActionListener a)
      {
         JButton b = new JButton(s);
         b.addActionListener(a);
         p.add(b);
      }
      private class Listener1 implements ActionListener
      {
         public void actionPerformed(ActionEvent e)
         {
            //
         }
      }
      private class Listener2 implements ActionListener
      {
         public void actionPerformed(ActionEvent e)
         {
            //
         }
      }
      private class Listener3 implements ActionListener
      {
         public void actionPerformed(ActionEvent e)
         {
            //
         }
      }
      private class Listener4 implements ActionListener
      {
         public void actionPerformed(ActionEvent e)
         {
            //
         }
      }
   }