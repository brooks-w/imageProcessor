package controller.commands;

import java.io.FileNotFoundException;

import model.ImageModel;

/**
 * to run an r, g, or b operation on an image.
 */
public class ColorCompCommand implements ImageCommand {
  private ImageModel model;
  private String type;

  /**
   * constructor to set the model and type of operation for colorComponent to run.
   * @param model the model to be operated on
   * @param type the type of command to run
   */
  public ColorCompCommand(ImageModel model, String type) {
    this.model = model;
    this.type = type;
  }

  /**
   * to execute a command on an image.
   * @throws FileNotFoundException if the operation fails.
   */
  public void execute() throws FileNotFoundException {
    this.model.colorComponent(type);
  }

  /**
   * a getter for the message each command should give.
   *
   * @return the message as a string
   */
  public String msg() {
    if (this.type.equals("red-component")) {
      return "Red Component";
    }

    else if (this.type.equals("green-component")) {
      return "Green Component";
    }

    else {
      return "Blue Component";
    }
  }
}
