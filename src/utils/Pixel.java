package utils;

import java.util.Objects;

/**
 * Represents a pixel that is made up of three color values; red, green and blue.
 */
public class Pixel implements IPixel {
  private int red;
  private int green;
  private int blue;

  /**
   * Represents the constructor to create a pixel from a picture that has three
   * corresponding colors to make up its color.
   * @param red represents the "value" or red in a pixel.
   * @param green represents the "value" or green in a pixel.
   * @param blue represents the "value" or blue in a pixel.
   */
  public Pixel(int red, int green, int blue) {
    if (red > 255 || red < 0 || green < 0 || green > 255 || blue < 0 || blue > 255 ) {
      throw new IllegalArgumentException("Invalid red, green, or blue value");
    }
    this.red = red;
    this.green = green;
    this.blue = blue;
  }

  /**
   * Gets the value of the red field.
   * @return returns the value of red.
   */
  @Override
  public int getRed() {
    return this.red;
  }

  /**
   * Gets the value of the green field.
   * @return returns the value of green.
   */
  @Override
  public int getGreen() {
    return this.green;
  }

  /**
   * Gets the value of the blue field.
   * @return returns the value of blue.
   */
  @Override
  public int getBlue() {
    return this.blue;
  }

  /**
   * Sets the red field to the specified value. If that value is above 255, red is set to 255.
   * If the value of below 0, red is set to 0.
   */
  @Override
  public void setRed(int r) {
    //making sure the bounds stay between 0 and 255
    this.red = Math.min(r, 255);
    this.red = Math.max(this.red, 0);
  }

  /**
   * Sets the green field to the specified value. If that value is above 255, green is set to 255.
   * If the value of below 0, green is set to 0.
   */
  @Override
  public void setGreen(int g) {
    //making sure the bounds stay between 0 and 255
    this.green = Math.min(g, 255);
    this.green = Math.max(this.green, 0);
  }

  /**
   * Sets the blue field to the specified value. If that value is above 255, blue is set to 255.
   * If the value of below 0, blue is set to 0.
   */
  @Override
  public void setBlue(int b) {
    //making sure the bounds stay between 0 and 255
    this.blue = Math.min(b, 255);
    this.blue = Math.max(this.blue, 0);
  }


  /**
   * to return a clone of this Pixel object.
   */
  @Override
  public IPixel clone() {
    return new Pixel(this.red, this.green, this.blue);
  }

  /**
   * Overriding equals/hashcode for testing.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Pixel pixel = (Pixel) o;
    return red == pixel.red && green == pixel.green && blue == pixel.blue;
  }

  /**
   * Overriding equals/hashcode for testing.
   */
  @Override
  public int hashCode() {
    return Objects.hash(red, green, blue);
  }
}
