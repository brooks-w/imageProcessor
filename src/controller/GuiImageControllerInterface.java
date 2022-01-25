package controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;


/**
 * to represent operations to be run on a controller that is a delegate of the main and the GUI.
 * Allows the GUI to send commands to the controller and models without major interaction in
 * the Main function.
 */
public interface GuiImageControllerInterface {
  /**
   * to have the controller execute its specified functionality.
   * The GuiImageController manages multiple image controllers and the program as a whole,
   * while each ImageController manages 1 image (represented by the ImageModel it contains as
   * a field).
   *
   * @param args the arguments to be parsed and executed by the controller
   * @throws IOException if writes fail
   */
  boolean execute(String[] args) throws IOException;

  /**
   * gets the pixel data of the currently loaded image and returns it as a buffered
   * image so the SwingGUIView can render the changes to the currently loaded image.
   *
   * @return returns the currently loaded image data as a BufferedImage
   */
  BufferedImage pullData();

  /**
   * converts pixel data into a format that can be used to plot a histogram
   * of red, green, blue, and intensity values for the image represented in the current model.
   * @return a 2D arraylist of the four lists of data, in the order:
   *          red value frequencies, green value frequencies, blue value frequencies,
   *          and intensity frequencies
   */
  ArrayList<ArrayList<Integer>> getHistogramData();
}