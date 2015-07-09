/**
 * This class contains class (static) methods
 * that will help you test the Picture class 
 * methods.  Uncomment the methods and the code
 * in the main to test.
 * 
 * @author Barbara Ericson 
 */
   public class PictureTester
   {
   /** Method to test zeroBlue */
      public static void testZeroBlue()
      {
         Picture beach = new Picture("beach.jpg");
         beach.explore();
         Picture beach2 = new Picture("beach.jpg");
         beach2.zeroBlue();
         beach2.explore();
      }
   
      public static void testKeepOnlyBlue()
      {
         Picture beach = new Picture("beach.jpg");

      }
   
      public static void testFixUnderwater()
      {
         Picture water = new Picture("water.jpg");
      
      }
   /** Method to test mirrorVertical */
      public static void testMirrorVertical()
      {
         Picture caterpillar = new Picture("caterpillar.jpg");
      
      }
   
   /** Method to test mirrorTemple */
      public static void testMirrorTemple()
      {
         Picture temple = new Picture("temple.jpg");
      
      }
   
   /** Method to test the collage method */
      public static void testCollage()
      {
         Picture canvas = new Picture("640x480.jpg");

      }
     /** Method to test copy */
      public static void testCopy()
      {
         Picture katie = new Picture("KatieFancy.jpg");
         Picture blank = new Picture("640x480.jpg");
  
      }
   /** Method to test edgeDetection */
      public static void testEdgeDetection()
      {
         Picture swan = new Picture("swan.jpg");
      
      }
        /** Method to test edgeDetection2 */
      public static void testEdgeDetection2()
      {
         Picture swan = new Picture("swan.jpg");
      
      }
     /** Method to test chromakey */
      public static void testChromakey()
      {
         Picture mark = new Picture("blue-mark.jpg");
         Picture moon = new Picture("moon-surface.jpg");
      
      }
       /** Method to test encode and decode */
      public static void testEncodeAndDecode()
      {
         Picture beach = new Picture("beach.jpg");
         beach.explore();  //original
         Picture message = new Picture("msg.jpg");
         beach.encode(message);
         beach.explore();  //original plus message
         Picture decoded = beach.decode();
         decoded.explore();  //message
      }
   
   
   /** Main method for testing.  Every class can have a main
    * method in Java */
      public static void main(String[] args)
      {
      // uncomment a call here to run a test
      // and comment out the ones you don't want
      // to run
      //testZeroBlue();
      //testKeepOnlyBlue();
      //testKeepOnlyRed();
      //testKeepOnlyGreen();
      //testNegate();
      //testGrayscale();
      //testFixUnderwater();
      //testMirrorVertical();
      //testMirrorTemple();
      //testMirrorArms();
      //testMirrorGull();
      //testMirrorDiagonal();
      //testCollage();
      //testCopy();
      //testEdgeDetection();
      //testEdgeDetection2();
      //testChromakey();
         testEncodeAndDecode();
      }
   }