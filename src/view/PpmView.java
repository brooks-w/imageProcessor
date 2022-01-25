package view;

import java.io.IOException;

import model.ImageModel;

/**
 * to represent operations related to the viewing of a .ppm image.
 */
public class PpmView implements ImageView {
  ImageModel model;
  Appendable out;

  /**
   * the constructor for PpmView.
   * @param model the model to display various messages for
   * @param out the Appendable to write to
   */
  public PpmView(ImageModel model, Appendable out) {
    if (model == null || out  == null) {
      throw new IllegalArgumentException("Args cannot be null");
    }
    this.model = model;
    this.out = out;
  }

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
