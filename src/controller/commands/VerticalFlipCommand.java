package controller.commands;

import java.io.FileNotFoundException;

import model.ImageModel;

/**
 * to flip an image vertically.
 */
public class VerticalFlipCommand implements ImageCommand {
  ImageModel model;

  /**
   * constructor to set the model.
   * @param model the model to be operated on
   */
  public VerticalFlipCommand(ImageModel model) {
    this.model = model;
  }

  /**
   * to execute a command on an image.
   * @throws FileNotFoundException if the operation fails.
   */
  public void execute() throws FileNotFoundException {
    this.model.verticalFLip();
  }

  /**
   * a getter for the message each command should give.
   *
   * @return the message as a string
   */
  public String msg() {
    return "Vertical Flip";
  }
}
