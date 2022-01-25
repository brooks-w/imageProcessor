package view;

import java.io.IOException;
import model.ImageModel;

/**
 * to represent operations related to the viewing of a general image.
 */
public abstract class ImageViewAbs implements ImageView {
  protected ImageModel model;
  protected Appendable out;

  /**
   * to render a message to the specified output. For instance,
   * would display that an operation that has no other visual feedback has
   * been performed successfully.
   * @param message the message to be rendered
   * @throws IOException if transmission of the message to the provided data destination fails
   */
  @Override
  public void renderMessage(String message) throws IOException {
    this.out.append(message + "\n");
  }
}
