package utils;


/**
 * Represents the interface to create a Pixel object. A pixel is the smallest
 * image that a computer can display. The images you see online are made up of hundreds of thousands
 * or even millions of pixels. Each pixel is represented by a value of how much red, green,
 * or blue it has in it.
 */
public interface IPixel {

  /**
   * Getter to get the value of red in a pixel.
   * @return the value of red that makes up the pixel.
   */
  int getRed();

  /**
   * Getter to get the value of green in a pixel.
   * @return the value of green that makes up the pixel.
   */
  int getGreen();

  /**
   * Getter to get the value of blue in a pixel.
   * @return the value of blue that makes up the pixel.
   */
  int getBlue();

  /**
   * Changes the value of red to the value that is passed in.
   */
  void setRed(int r);

  /**
   * Changes the value of green to the value that is passed in.
   */
  void setGreen(int g);

  /**
   * Changes the value of blue to the value that is passed in.
   */
  void setBlue(int b);

  /**
   * to return a clone of this Pixel object.
   */
  IPixel clone() throws CloneNotSupportedException;
}
