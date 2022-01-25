package controller.commands;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * to contain commands to preform operations on an image.
 */
public interface ImageCommand {
  /**
   * to execute a command on an image.
   * @throws FileNotFoundException if the operation fails.
   */
  void execute() throws IOException;

  /**
   * a getter for the message each command should give.
   * @return the message as a string
   */
  String msg();
}
