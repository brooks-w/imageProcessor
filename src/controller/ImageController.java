package controller;

import java.io.IOException;

/**
 * to represent the operations used to control the image
 * processing functionality.
 */
public interface ImageController<T> {

  /**
   * to run the specified operations on the provided controller.
   * This controller contains the model and view, which in turn provide
   * implementations for the operations and the viewing of the model.
   * This is provided as a separate method instead of part of the addCommands
   * so that it can be in the interface as it does not care what type of image
   * is being operated on.
   * Commands are run based off of operations passed from the Main func (ImageProcessor)
   * to the controller.
   *
   */
  void goProgram() throws IOException;

  /**
   * To send the assigned commands to this controller so they can be run by
   * the go() method. Essentially a setter than then returns this object
   * so that it can be ran afterward.
   * @param opList an appendable containing the list of operations to be performed
   *               on this object
   */
  ImageController<T> addCommands(Appendable opList) throws IOException;
}
