package controller.commands;

import java.io.FileNotFoundException;

import model.ImageModel;

/**
 * to apply the sepia transformation to an image.
 */
public class BlurCommand implements ImageCommand {
  ImageModel model;

  /**
   * constructor to set the model.
   * @param model the model to be operated on
   */
  public BlurCommand(ImageModel model) {
    this.model = model;
  }

  /**
   * to execute a command on an image.
   * @throws FileNotFoundException if the operation fails.
   */
  public void execute() throws FileNotFoundException {
    this.model.blur();
  }

  /**
   * a getter for the message each command should give.
   *
   * @return the message as a string
   */
  public String msg() {
    return "Blur";
  }
}
