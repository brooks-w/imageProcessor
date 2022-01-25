package controller;

import java.io.IOException;

/**
 * to test the ImageControllerImpl via a mock.
 */
public class MockController implements ImageController {
  private Appendable opList;
  public Appendable log;

  /**
   * the constructor for MockController, just initializes the log.
   */
  public MockController(Appendable log) {
    this.log = log;
  }

  /**
   * A mock to test if the proper operations are called.
   */
  public void goProgram() throws IOException {
    //parsing the arguments from the appendable into a list of Strings
    String[] args = this.opList.toString().split("\n");

    //for each one, what operation is it asking for?
    for (String str: args) {

      if (str.equals("hflip")) {
        this.log.append("horizontal flip\n");
      }

      if (str.equals("vflip")) {
        this.log.append("vertical flip\n");
      }

      //brighten or darken the specified image
      if (str.split(":")[0].equals("Dark") || str.split(":")[0].equals("Bright")) {
        //get our ints
        if (str.split(":")[0].equals("Bright")) {
          this.log.append("calling bright\n");
        }

        if (str.split(":")[0].equals("Dark")) {
          this.log.append("calling dark\n");
        }
      }

      if (str.equals("r")) {
        this.log.append("red comp\n");
      }

      if (str.equals("g")) {
        this.log.append("green comp\n");
      }

      if (str.equals("b")) {
        this.log.append("blue comp\n");
      }

      if (str.equals("value")) {
        this.log.append("value\n");
      }

      if (str.equals("luma")) {
        this.log.append("luma\n");
      }

      if (str.equals("intensity")) {
        this.log.append("intensity\n");
      }

      if (str.split(":")[0].equals("Save")) {
        this.log.append("saving as " + str.split(":")[1] + "\n");
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
  public MockController addCommands(Appendable opList) throws IOException {
    this.opList = opList;
    this.log.append("Adding to opList\n");
    //returns itself so we can then call .go() on it, somewhat like a builder
    return this;
  }
}
