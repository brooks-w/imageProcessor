package model;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import utils.Pixel;

/**
 * to model and execute operations on data from a .ppm image file.
 */
public class PpmModel extends ImageModelAbs {
  /**
   * the full constructor for PpmModel.
   *
   * @param image represents the image operated on.
   */
  public PpmModel(String image) throws FileNotFoundException {
    this(image, utils.ImageUtil.readPPM(image));
  }

  /**
   * the full constructor for PpmModel.
   * Allows setting this.pixels manually.
   *
   * @param image represents the image operated on.
   */
  public PpmModel(String image, ArrayList<ArrayList<Pixel>> pixels) {
    this.image = image;
    this.pixels = pixels;
  }

  /**
   * to save an image as a file.
   */
  protected void saveImageHelper(String saveName) throws IOException, IOException, IOException {
    //this code based off example code from https://www.w3schools.com/java/java_files_create.asp.
    //open the file to write to it
    FileWriter openedSaveImg = new FileWriter(saveName);

    int width = this.pixels.get(0).size();
    int height = this.pixels.size();

    //write P3, width, height at the top
    openedSaveImg.write("P3\n");
    openedSaveImg.write(width + " " + height + "\n");

    //write the max R, G, or B value at the top
    int curMax = 0;
    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        Pixel pix = this.pixels.get(row).get(col);
        curMax = Math.max(curMax, Math.max(pix.getBlue(), Math.max(pix.getGreen(), pix.getRed())));
      }
    }
    openedSaveImg.write(curMax + "\n");

    //writing pixels
    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        //write the pixel values into the file
        Pixel pix = this.pixels.get(row).get(col);
        openedSaveImg.write(pix.getRed() + "\n");
        openedSaveImg.write(pix.getGreen() + "\n");
        openedSaveImg.write(pix.getBlue() + "\n");
      }
    }
    openedSaveImg.close();
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
    PpmModel ppmModel = (PpmModel) o;
    return Objects.equals(pixels, ppmModel.pixels);
  }

  /**
   * Overriding equals/hashcode for testing.
   */
  @Override
  public int hashCode() {
    return Objects.hash(pixels);
  }
}
