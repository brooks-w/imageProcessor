package controller.commands;

import java.io.FileNotFoundException;

/**
 * does nothing, in the case that a str slips through to the controller
 * that does not have an associated operation.
 * Essentially a redundancy, as a "bad" string should never pass from main to controller,
 * but in case one does, this stops a null pointer exception.
 */
public class NotFoundCommand implements ImageCommand {

  /**
   * an empty default constructor.
   */
  public NotFoundCommand() {
    //I don't do anything, I just exist to stop errors.
  }

  /**
   * does nothing, in the case that a str slips through that does not have an associated operation.
   *
   * @throws FileNotFoundException if the operation fails.
   */
  public void execute() throws FileNotFoundException {
    //I don't do anything, I just exist to stop errors.
  }

  /**
   * a getter for the message each command should give.
   *
   * @return the message as a string
   */
  public String msg() {
    return "No Operation Was Run, I Am In Place of An Error:";
  }
}
