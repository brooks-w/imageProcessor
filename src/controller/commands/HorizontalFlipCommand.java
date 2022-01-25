package controller.commands;

import java.io.FileNotFoundException;

import model.ImageModel;

/**
 * to flip an image horizontally.
 */
public class HorizontalFlipCommand implements ImageCommand {
  private ImageModel model;

  /**
   * constructor to set the model.
   * @param model the model to be operated on
   */
  public HorizontalFlipCommand(ImageModel model) {
    this.model = model;
  }

  /**
   * to execute a command on an image.
   * @throws FileNotFoundException if the operation fails.
   */
  public void execute() throws FileNotFoundException {
    this.model.horizontalFlip();
  }

  /**
   * a getter for the message each command should give.
   *
   * @return the message as a string
   */
  public String msg() {
    return "Horizontal Flip";
  }
}
