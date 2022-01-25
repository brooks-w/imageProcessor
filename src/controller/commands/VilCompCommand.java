package controller.commands;

import java.io.FileNotFoundException;

import model.ImageModel;

/**
 * to run a luma, value, or intensity operation on an image.
 */
public class VilCompCommand implements ImageCommand {
  private ImageModel model;
  private String type;

  /**
   * constructor to set the model and type of vilCommand.
   * @param model the model to be operated on
   * @param type the type of command to run
   */
  public VilCompCommand(ImageModel model, String type) {
    this.model = model;
    this.type = type;
  }

  /**
   * to execute a command on an image.
   * @throws FileNotFoundException if the operation fails.
   */
  public void execute() throws FileNotFoundException {
    this.model.vilComponent(type);
  }

  /**
   * a getter for the message each command should give.
   *
   * @return the message as a string
   */
  public String msg() {
    if (this.type.equals("intensity")) {
      return "Intensity Component";
    }

    else if (this.type.equals("luma")) {
      return "Luma Component";
    }

    else {
      return "Value Component";
    }
  }
}
