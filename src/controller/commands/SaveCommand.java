package controller.commands;

import java.io.FileNotFoundException;
import java.io.IOException;

import model.ImageModel;

/**
 * to save an image.
 */
public class SaveCommand implements ImageCommand {
  private ImageModel model;
  private String name;

  /**
   * constructor to set the model and name to save the image as.
   * @param model the model to be operated on
   * @param name the name that the image will be saved as
   */
  public SaveCommand(ImageModel model, String name) {
    this.model = model;
    this.name = name;
  }

  /**
   * to execute a command on an image.
   * @throws FileNotFoundException if the operation fails.
   */
  public void execute() throws IOException {
    this.model.saveImage(name);
  }

  /**
   * a getter for the message each command should give.
   *
   * @return the message as a string
   */
  public String msg() {
    return "Saving of " + this.name;
  }
}
