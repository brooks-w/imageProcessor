package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import javax.imageio.ImageIO;

import utils.Pixel;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;

/**
 * to represent an image in png, jpeg, or bmp format, and the operations
 * that can be performed on it.
 */
public class PngJpegBmpModel extends ImageModelAbs {

  /**
   * the normal constructor for PngJpegBmpModel.
   *
   * @param image represents the image operated on.
   */
  public PngJpegBmpModel(String image) throws IOException {
    this(image, utils.ImageUtil.readBufferedImage(image));
  }

  /**
   * the full constructor for PngJpegBmpModel.
   * Allows setting this.pixels manually.
   *
   * @param image represents the image operated on.
   */
  public PngJpegBmpModel(String image, ArrayList<ArrayList<Pixel>> pixels) {
    this.image = image;
    this.pixels = pixels;
  }

  /**
   * to save an image in a file.
   */
  protected void saveImageHelper(String saveName) throws IOException {
    //preliminary data
    int height = this.pixels.size();
    int width = this.pixels.get(0).size();
    BufferedImage writeBuffered = new BufferedImage(width, height, TYPE_INT_RGB);

    //write rgb data into the ImageBuffer
    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        int red = this.pixels.get(row).get(col).getRed();
        int rgb = red;
        rgb = (rgb << 8) + this.pixels.get(row).get(col).getGreen();
        rgb = (rgb << 8) + this.pixels.get(row).get(col).getBlue();

        writeBuffered.setRGB(col, row, rgb);
      }
    }

    //write the ImageBuffer to a file
    try {
      File outputfile = new File(saveName);
      ImageIO.write(writeBuffered, "jpeg", outputfile);
    } catch (IOException e) {
      throw e;
    }
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
    PngJpegBmpModel model = (PngJpegBmpModel) o;
    return Objects.equals(pixels, model.pixels);
  }

  /**
   * Overriding equals/hashcode for testing.
   */
  @Override
  public int hashCode() {
    return Objects.hash(pixels);
  }
}
