package model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import utils.Pixel;

/**
 * to represent the operations that can be done on an image.
 * All except saveImage are mutator methods.
 */
public interface ImageModel {

  /**
   * Getter to access the d2 array of pixels that makes up an image.
   * @return the 2d array of pixel that makes up an image.
   */
  ArrayList<ArrayList<Pixel>> getPixels();

  /**
   * applies a matrix to each pixel in this model's image to
   * transform its data.
   * @param m the given matrix used to transform, as an N x N ArrayList
   */
  void applyTransformation(ArrayList<ArrayList<Double>> m);

  /**
   * to apply the given filter to the (pixels in) the model being
   * operated on.
   * @param f the given filter to be applied, as an N x N ArrayList
   */
  void applyFilter(ArrayList<ArrayList<Double>> f);

  /**
   * to flip an image vertically. The image to be flipped
   * is the one represented in the instance of the model this
   * method is called on.
   */
  void verticalFLip() throws FileNotFoundException;

  /**
   * to flip an image horizontally. The image to be flipped
   * is the one represented in the instance of the model this
   * method is called on.
   */
  void horizontalFlip() throws FileNotFoundException;

  /**
   * to save an image in a file.
   */
  void saveImage(String saveName) throws IOException;

  /**
   * to change the brightness of a given image by _shift_.
   * Negative values of _shift_ denote the darkening of the image,
   * while positive denote brightening of the image.
   *
   * @param shift the int value to brighten/darken by
   */
  void changeBrightness(int shift) throws FileNotFoundException;

  /**
   * to make a new image with a certain component (red, green, blue,
   * value, luma, or intensity) of the old image.
   *
   * @param comp the component to make the new image with
   */
  void colorComponent(String comp) throws FileNotFoundException;

  /**
   * To make a new image with a certain component (value, intensity, luma) of the old image.
   *
   * @param comp the component to make a new image with.
   */
  void vilComponent(String comp) throws FileNotFoundException;

  /**
   * to apply the sepia transformation to the image represented in the model.
   */
  void sepia();

  /**
   * to blur the given image represented in the model.
   */
  void blur();

  /**
   * to blur the given image represented in the model.
   */
  void sharpen();

  /**
   * a getter to return the pixel data.
   * @return the pixel data as a 2D array.
   */
  ArrayList<ArrayList<Pixel>> getPixelData();
}
