package view;

import java.io.IOException;

/**
 * to represent operations relating to the viewing of
 * images.
 */
public interface ImageView {
  /**
   * to render a message to the specified output. For instance,
   * would display that an operation that has no other visual feedback has
   * been performed successfully.
   * @param message the message to be rendered
   * @throws IOException if transmission of the message to the provided data destination fails
   */
  void renderMessage(String message) throws IOException;
}
