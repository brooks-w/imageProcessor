package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import controller.commands.BlurCommand;
import controller.commands.BrightenCommand;
import controller.commands.ColorCompCommand;
import controller.commands.HorizontalFlipCommand;
import controller.commands.ImageCommand;
import controller.commands.NotFoundCommand;
import controller.commands.SaveCommand;
import controller.commands.SepiaCommand;
import controller.commands.SharpenCommand;
import controller.commands.VerticalFlipCommand;
import controller.commands.VilCompCommand;
import model.ImageModel;
import view.ImageView;

/**
 * to represent a controller for a general image (ie. one without a specific type),
 * and to allow for the running of operations on that image.
 * more of a command now, as it doesn't take a view
 */
public abstract class ImageControllerAbs<T> implements ImageController<T> {
  protected ImageModel model;
  protected ImageView view;
  protected Appendable opList;
  //args for a few of the various commands that can be performed on this model,
  //specifically the ones that may change depending on user input
  protected String saveName = "";
  protected int shift = 0;
  //a HashMap of all commands that can be run on the associated ImageModel
  protected final HashMap<String, ImageCommand> commandHash = new HashMap<>();

  /**
   * to run the specified operations on the provided controller.
   * This controller contains the model and view, which in turn provide
   * implementations for the operations and the viewing of the model.
   * This is provided as a separate method instead of part of the addCommands
   * so that it can be in the interface as it does not care what type of image
   * is being operated on.
   * For each line in the this.opList appendable,
   * an operation is to be run on the specified image
   * @throws IOException if a status message fails to write
   */
  public void goProgram() throws IOException {
    //parsing the arguments from the appendable into a list of Strings
    String[] args = this.opList.toString().split("\n");

    //adding the commands that don't require shifting parameters
    //IntelliJ doesn't like this outside of the func, which is a bit frustrating
    //could copy into each concrete impl to avoid doing this every run of goProgram, but
    //that has more duplicate code
    this.commandHash.put("vflip", new VerticalFlipCommand(this.model));
    this.commandHash.put("sharpen", new SharpenCommand(this.model));
    this.commandHash.put("blur", new BlurCommand(this.model));
    this.commandHash.put("hflip", new HorizontalFlipCommand(this.model));
    this.commandHash.put("sepia", new SepiaCommand(this.model));
    this.commandHash.put("r", new ColorCompCommand(this.model, "red-component"));
    this.commandHash.put("g", new ColorCompCommand(this.model, "green-component"));
    this.commandHash.put("b", new ColorCompCommand(this.model, "blue-component"));
    this.commandHash.put("value", new VilCompCommand(this.model, "value"));
    this.commandHash.put("intensity", new VilCompCommand(this.model, "intensity"));
    this.commandHash.put("luma", new VilCompCommand(this.model, "luma"));

    //for each part of the command Appendable, what operation is it asking for?
    for (String str: args) {
      //getting saveName string, if it exists
      if (str.split(" ")[0].equals("Save")) {
        this.saveName = str.split(" ")[1];

        //we need to re-add the commands that take other parameters
        //there are other ways to do this but I think this is the cleanest without
        //using lambdas
        this.commandHash.put("Save", new SaveCommand(this.model, this.saveName));
      }

      //getting the bright/dark shift, if it exists
      if (str.split(" ")[0].equals("Dark") || str.split(" ")[0].equals("Bright")) {
        try {
          this.shift = Integer.parseInt(str.split(" ")[1]);

          //we need to re-add the commands that take other parameters
          //there are other ways to do this but I think this is the cleanest without
          //using lambdas
          this.commandHash.put("Bright", new BrightenCommand(this.model, this.shift));
          this.commandHash.put("Dark", new BrightenCommand(this.model, -1 * this.shift));
        } catch (NumberFormatException ignored) { }
      }

      //running the command, and printing success/failure msgs
      try {
        ImageCommand cmd = this.commandHash.
                getOrDefault(str.split(" ")[0], new NotFoundCommand());
        cmd.execute();
        this.view.renderMessage(cmd.msg() + " Succeeded");
      } catch (FileNotFoundException e) {
        ImageCommand cmd = this.commandHash.
                getOrDefault(str.split(" ")[0], new NotFoundCommand());
        this.view.renderMessage(cmd.msg() + " Failed");
      }
    }
  }

  /**
   * To send the assigned commands to this controller so they can be run by
   * the go() method. Essentially a setter than then returns this object
   * so that it can be ran afterward.
   * @param opList an appendable containing the list of operations to be performed
   *               on this object
   */
  public ImageController<T> addCommands(Appendable opList) {
    this.opList = opList;
    //returns itself so we can then call .go() on it, somewhat like a builder
    return this;
  }
}

