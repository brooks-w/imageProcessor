package utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileInputStream;

import javax.imageio.ImageIO;

/**
 * This class contains utility methods to read a PPM image from file and simply print its contents.
 * Feel free to change this method as required.
 */
public class ImageUtil<T> {

  /**
   * read an image in ppm format and output the RBG data to an arraylist for manipulation.
   *
   * @param filename the name of the file to be read
   * @return a 2D array containing Pixels in each slot
   */
  public static ArrayList<ArrayList<Pixel>> readPPM(String filename) throws FileNotFoundException {
    Scanner sc;
    ArrayList<ArrayList<Pixel>> outList = new ArrayList<ArrayList<Pixel>>();

    sc = new Scanner(new FileInputStream(filename));
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      throw new IllegalArgumentException("Bad filetype");
    }
    int width = sc.nextInt();
    int height = sc.nextInt();
    int maxValue = sc.nextInt();

    //adds each pixel to our result list with the correct RGB values
    for (int i = 0; i < height; i++) {
      ArrayList<Pixel> tempRow = new ArrayList<Pixel>();
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        //System.out.println("TempRow: " + tempRow.size());
        tempRow.add(new Pixel(r, g, b));
      }
      outList.add(tempRow);
    }
    return outList;
  }

  /**
   * to read data from an image file into a 2d array of pixels.
   *
   * @param file the name of the image file
   * @return a 2D array of Pixels with r,g,b values
   * @throws IOException if the data cannot be read from the file
   */
  public static ArrayList<ArrayList<Pixel>> readBufferedImage(String file) throws IOException {
    ArrayList<ArrayList<Pixel>> outList = new ArrayList<ArrayList<Pixel>>();
    File name = new File(file);
    BufferedImage image = ImageIO.read(name);

    for (int y = 0; y < image.getHeight(); y++) {
      ArrayList<Pixel> tempRow = new ArrayList<Pixel>();
      for (int x = 0; x < image.getWidth(); x++) {

        //Code based on stackoverflow question:
        //https://stackoverflow.com/questions/7749895/java-loop-through-pixels-in-an-image
        int clr = image.getRGB(x, y);
        int r = (clr & 0x00ff0000) >> 16;
        int g = (clr & 0x0000ff00) >> 8;
        int b = clr & 0x000000ff;

        tempRow.add(new Pixel(r, g, b));
      }
      outList.add(tempRow);
    }
    return outList;
  }

  /**
   * to get the index of an item from a list.
   *
   * @param list the list to search
   * @param item the item to find the index of
   * @return the index of item as an int
   */
  public int getIndex(ArrayList<T> list, T item) {
    for (int j = 0; j < list.size(); j++) {
      if (list.get(j).equals(item)) {
        return j;
      }
    }
    return -1;
  }
}

