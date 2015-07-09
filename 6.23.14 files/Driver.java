import java.io.*;
import java.awt.*;
import java.util.*;
import java.applet.*;
import javax.swing.*;
import java.awt.event.*; 
import javax.swing.event.*;

/****************************************************************
Executes CRYPTICON.
****************************************************************/
public class Driver extends JApplet
{
    /****************************************************************
	Initializes the GUI. Post-initialization is handled elsewhere.
	****************************************************************/
	public static void main(String[] args)
	{
		Display.init();
	}
	public void init()
	{
		Display.init();
	}
	public void start()
	{
	}
	public void destroy()
	{
		System.exit(0);
	}
	public void stop()
	{
		System.exit(0);
	}
}