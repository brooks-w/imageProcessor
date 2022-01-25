package controller.commands;

import java.io.FileNotFoundException;

import model.ImageModel;

/**
 * to brighten or darken an image.
 */
public class BrightenCommand implements ImageCommand {
  private ImageModel model;
  private int shift;

  /**
   * the constructor that takes in necessary parameters.
   * @param model the model to be operated on
   * @param shift the
   */
  public BrightenCommand(ImageModel model, int shift) {
    this.model = model;
    this.shift = shift;
  }

  /**
   * to execute a command on an image.
   * @throws FileNotFoundException if the operation fails.
   */
  @Override
  public void execute() throws FileNotFoundException {
    this.model.changeBrightness(shift);
  }

  /**
   * a getter for the message each command should give.
   *
   * @return the message as a string
   */
  public String msg() {
    if (this.shift < 0) {
      return "Darken";
    }
    else {
      return "Brighten";
    }
  }
}
