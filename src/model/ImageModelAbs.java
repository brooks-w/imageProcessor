package model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import utils.Pixel;

/**
 * to model and execute operations on data from a general image file.
 */
public abstract class ImageModelAbs implements ImageModel {
  //the image data as a 2D array of pixels
  protected ArrayList<ArrayList<Pixel>> pixels = new ArrayList<ArrayList<Pixel>>();
  protected String image;

  /**
   * a getter to return the pixel data.
   * @return the pixel data as a 2D array.
   */
  public ArrayList<ArrayList<Pixel>> getPixelData() {
    return this.pixels;
  }

  /**
   * Getter to access the d2 array of pixels that makes up an image.
   * @return the 2d array of pixel that makes up an image.
   */
  public ArrayList<ArrayList<Pixel>> getPixels() {
    return this.pixels;
  }

  /**
   * applies a matrix to each pixel in this model's image to
   * transform its data.
   *
   * @param m the given matrix used to transform, as an N x N ArrayList
   */
  @Override
  public void applyTransformation(ArrayList<ArrayList<Double>> m) {
    //for now, the only filters should be 3x3
    //leaving the comment as N x N in case this changes later
    if (m.size() != 3 && m.get(0).size() != 3) {
      throw new IllegalArgumentException("Bad Matrix: not 3x3");
    }

    //for each pixel
    for (int row = 0; row < this.pixels.size(); row++) {
      for (int col = 0; col < this.pixels.get(row).size(); col++) {
        //starting data for the math
        Pixel pix = this.pixels.get(row).get(col);
        ArrayList<Integer> rgb =
                new ArrayList<>(Arrays.asList(pix.getRed(), pix.getGreen(), pix.getBlue()));
        ArrayList<Double> newColors = new ArrayList<Double>();
        for (int j = 0; j < m.size(); j++) {
          newColors.add(0.0);
        }

        //doing the matrix multiplication
        for (int color = 0; color < m.size(); color++) {
          for (int i = 0; i < m.get(color).size(); i++) {
            newColors.set(color, newColors.get(color) +
                    Double.valueOf(rgb.get(i)) * m.get(color).get(i));
          }
        }
        //setting the results
        pix.setRed(Math.toIntExact(Math.round(newColors.get(0))));
        pix.setGreen(Math.toIntExact(Math.round(newColors.get(1))));
        pix.setBlue(Math.toIntExact(Math.round(newColors.get(2))));
      }
    }
  }

  /**
   * to apply the given filter to the (pixels in) the model being
   * operated on.
   *
   * @param f the given filter to be applied, as an N x N ArrayList
   */
  @Override
  public void applyFilter(ArrayList<ArrayList<Double>> f) throws IllegalArgumentException {
    if (f.size() != f.get(0).size()) {
      throw new IllegalArgumentException("Bad Matrix: not N x N");
    }

    //cloning to solve aliasing issues
    ArrayList<ArrayList<Pixel>> newData;
    ArrayList<ArrayList<Pixel>> tempList = new ArrayList<ArrayList<Pixel>>();
    for (int i = 0; i < this.pixels.size(); i++) {
      ArrayList<Pixel> tempList2 = new ArrayList<Pixel>();
      for (int j = 0; j < this.pixels.get(i).size(); j++) {
        tempList2.add((Pixel) this.pixels.get(i).get(j).clone());
      }
      tempList.add(tempList2);
    }
    newData = tempList;

    int side = (f.size() - 1) / 2;

    //red
    //core loop: for each pixel
    for (int row = 0; row < this.pixels.size(); row++) {
      for (int col = 0; col < this.pixels.get(row).size(); col++) {
        int newRed = 0;
        int starty = (Math.min(Math.max(0, row - side), this.pixels.size() - 1));
        int endy = (Math.min(Math.max(0, row + side), this.pixels.size() - 1));
        int startx = (Math.min(Math.max(0, col - side), this.pixels.get(row).size() - 1));
        int endx = (Math.min(Math.max(0, col + side), this.pixels.get(row).size() - 1));

        //for each in matrix
        int x = 0;
        for (int maty = starty; maty <= endy; maty++) {
          int y = 0;
          for (int matx = startx; matx <= endx; matx++) {
            newRed += Math.toIntExact(Math.round(
                    this.pixels.get(maty).get(matx).getRed() * f.get(y).get(x)));
            y += 1;
          }
          x += 1;
        }
        newData.get(row).get(col).setRed(newRed);
      }
    }

    //green
    //core loop: for each pixel
    for (int row = 0; row < this.pixels.size(); row++) {
      for (int col = 0; col < this.pixels.get(row).size(); col++) {
        int newGreen = 0;
        int starty = (Math.min(Math.max(0, row - side), this.pixels.size() - 1));
        int endy = (Math.min(Math.max(0, row + side), this.pixels.size() - 1));
        int startx = (Math.min(Math.max(0, col - side), this.pixels.get(row).size() - 1));
        int endx = (Math.min(Math.max(0, col + side), this.pixels.get(row).size() - 1));

        //for each in matrix
        int x = 0;
        for (int maty = starty; maty <= endy; maty++) {
          int y = 0;
          for (int matx = startx; matx <= endx; matx++) {
            newGreen += Math.toIntExact(Math.round(
                    this.pixels.get(maty).get(matx).getGreen() * f.get(y).get(x)));
            y += 1;
          }
          x += 1;
        }
        newData.get(row).get(col).setGreen(newGreen);
      }
    }

    //blue
    //core loop: for each pixel
    for (int row = 0; row < this.pixels.size(); row++) {
      for (int col = 0; col < this.pixels.get(row).size(); col++) {
        int newBlue = 0;
        int starty = (Math.min(Math.max(0, row - side), this.pixels.size() - 1));
        int endy = (Math.min(Math.max(0, row + side), this.pixels.size() - 1));
        int startx = (Math.min(Math.max(0, col - side), this.pixels.get(row).size() - 1));
        int endx = (Math.min(Math.max(0, col + side), this.pixels.get(row).size() - 1));

        //for each in matrix
        int x = 0;
        for (int maty = starty; maty <= endy; maty++) {
          int y = 0;
          for (int matx = startx; matx <= endx; matx++) {
            newBlue += Math.toIntExact(Math.round(
                    this.pixels.get(maty).get(matx).getBlue() * f.get(y).get(x)));
            y += 1;
          }
          x += 1;
        }
        newData.get(row).get(col).setBlue(newBlue);
      }
    }
    this.pixels = newData;
  }

  /**
   * to flip an image vertically. The image to be flipped
   * is the one represented in the instance of the model this
   * method is called on.
   */
  @Override
  public void blur() {
    ArrayList<ArrayList<Double>> m =
            new ArrayList<ArrayList<Double>>();
    m.add(new ArrayList<Double>(Arrays.asList(0.0625, 0.125, 0.0625)));
    m.add(new ArrayList<Double>(Arrays.asList(0.125, 0.25, 0.125)));
    m.add(new ArrayList<Double>(Arrays.asList(0.0625, 0.125, 0.0625)));

    this.applyFilter(m);
  }

  /**
   * to flip an image vertically. The image to be flipped
   * is the one represented in the instance of the model this
   * method is called on.
   */
  @Override
  public void verticalFLip() {
    ArrayList<ArrayList<Pixel>> resList = new ArrayList<ArrayList<Pixel>>();
    for (int row = this.pixels.size() - 1; row > 0; row = row - 1) {
      resList.add(this.pixels.get(row));
    }
    this.pixels = resList;
  }

  /**
   * to flip an image horizontally. The image to be flipped
   * is the one represented in the instance of the model this
   * method is called on.
   */
  @Override
  public void horizontalFlip() {
    ArrayList<ArrayList<Pixel>> resList = new ArrayList<ArrayList<Pixel>>();
    for (int row = 0; row < this.pixels.size(); row++) {
      ArrayList<Pixel> tempList = new ArrayList<Pixel>();
      for (int col = this.pixels.get(row).size() - 1; col > 0; col--) {
        tempList.add(this.pixels.get(row).get(col));
      }
      resList.add(tempList);
    }
    this.pixels = resList;
  }

  /**
   * to save an image as a file.
   */
  @Override
  public void saveImage(String saveName) throws IOException {
    System.out.println("Savename: " + saveName);
    if (saveName.split("\\.")[1].equals("ppm")) {
      new PpmModel(this.image, this.pixels).saveImageHelper(saveName);
    }
    if (saveName.split("\\.")[1].equals("jpeg") ||
            saveName.split("\\.")[1].equals("png") ||
            saveName.split("\\.")[1].equals("bmp")) {
      new PngJpegBmpModel(this.image, this.pixels).saveImageHelper(saveName);
    }
  }

  /**
   * to change the brightness of a given image by shift.
   * Negative values of shift denote the darkening of the image,
   * while positive denote brightening of the image.
   *
   * @param shift the int value to brighten/darken by
   */
  @Override
  public void changeBrightness(int shift) {
    for (int row = 0; row < this.pixels.size(); row++) {
      for (int col = 0; col < this.pixels.get(row).size(); col++) {
        //set values
        this.pixels.get(row).get(col).setRed(this.pixels.get(row).get(col).getRed() + shift);
        this.pixels.get(row).get(col).setGreen(this.pixels.get(row).get(col).getGreen() + shift);
        this.pixels.get(row).get(col).setBlue(this.pixels.get(row).get(col).getBlue() + shift);
      }
    }
  }

  /**
   * to make a new image with a certain component (red, green, blue,
   * value, luma, or intensity) of the old image.
   *
   * @param comp the component to make the new image with
   */
  @Override
  public void colorComponent(String comp) {
    for (int row = 0; row < this.pixels.size(); row++) {
      for (int col = 0; col < this.pixels.get(row).size(); col++) {
        if (comp.equals("red-component")) {
          this.pixels.get(row).get(col).setGreen(this.pixels.get(row).get(col).getRed());
          this.pixels.get(row).get(col).setBlue(this.pixels.get(row).get(col).getRed());
        }

        if (comp.equals("green-component")) {
          this.pixels.get(row).get(col).setRed(this.pixels.get(row).get(col).getGreen());
          this.pixels.get(row).get(col).setBlue(this.pixels.get(row).get(col).getGreen());
        }

        if (comp.equals("blue-component")) {
          this.pixels.get(row).get(col).setRed(this.pixels.get(row).get(col).getBlue());
          this.pixels.get(row).get(col).setGreen(this.pixels.get(row).get(col).getBlue());
        }
      }
    }
  }

  /**
   * To make a new image with a certain component (value, intensity, luma) of the old image.
   *
   * @param comp the component to make a new image with.
   */
  @Override
  public void vilComponent(String comp) throws FileNotFoundException {
    int value;
    int intensity;
    double luma;

    //this is separate from the loop because it is the new HW5 implementation
    if (comp.equals("luma")) {
      ArrayList<ArrayList<Double>> m =
              new ArrayList<ArrayList<Double>>();
      m.add(new ArrayList<Double>(Arrays.asList(0.21260, 0.71520, 0.0722)));
      m.add(new ArrayList<Double>(Arrays.asList(0.21260, 0.71520, 0.0722)));
      m.add(new ArrayList<Double>(Arrays.asList(0.21260, 0.71520, 0.0722)));

      this.applyTransformation(m);
      return;
    }

    for (int row = 0; row < this.pixels.size(); row++) {
      for (int col = 0; col < this.pixels.get(row).size(); col++) {
        if (comp.equals("value")) {
          value = Math.max(this.pixels.get(row).get(col).getRed(),
                  Math.max(this.pixels.get(row).get(col).getGreen(),
                          this.pixels.get(row).get(col).getBlue()));
          this.pixels.get(row).get(col).setRed(value);
          this.pixels.get(row).get(col).setGreen(value);
          this.pixels.get(row).get(col).setBlue(value);
        }
        if (comp.equals("intensity")) {
          intensity = ((this.pixels.get(row).get(col).getRed()
                  + this.pixels.get(row).get(col).getGreen()
                  + this.pixels.get(row).get(col).getBlue()) / 3);

          this.pixels.get(row).get(col).setRed(intensity);
          this.pixels.get(row).get(col).setGreen(intensity);
          this.pixels.get(row).get(col).setBlue(intensity);
        }
      }
    }
  }

  /**
   * to apply the sepia transformation to the image represented in the model.
   */
  public void sepia() {
    ArrayList<ArrayList<Double>> m =
            new ArrayList<ArrayList<Double>>();
    m.add(new ArrayList<Double>(Arrays.asList(0.393, 0.769, 0.189)));
    m.add(new ArrayList<Double>(Arrays.asList(0.349, 0.686, 0.168)));
    m.add(new ArrayList<Double>(Arrays.asList(0.272, 0.534, 0.131)));

    this.applyTransformation(m);
  }

  /**
   * to blur the given image represented in the model.
   */
  public void sharpen() {
    ArrayList<ArrayList<Double>> m =
            new ArrayList<ArrayList<Double>>();
    m.add(new ArrayList<Double>(Arrays.asList(-0.125, -0.125, -0.125, -0.125, -0.125)));
    m.add(new ArrayList<Double>(Arrays.asList(-0.125, 0.25, 0.25, 0.25, -0.125)));
    m.add(new ArrayList<Double>(Arrays.asList(-0.125, 0.25, 1.0, 0.25, -0.125)));
    m.add(new ArrayList<Double>(Arrays.asList(-0.125, 0.25, 0.25, 0.25, -0.125)));
    m.add(new ArrayList<Double>(Arrays.asList(-0.125, -0.125, -0.125, -0.125, -0.125)));

    this.applyFilter(m);
  }

  /**
   * Overriding equals/hashcode for testing.
   */
  @Override
  public abstract int hashCode();

  /**
   * Overriding equals/hashcode for testing.
   */
  @Override
  public abstract boolean equals(Object o);
}
