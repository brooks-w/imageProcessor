package view;

import model.ImageModel;

/**
 * to represent operations related to the viewing of a .ppm image.
 */
public class ImageViewImpl extends ImageViewAbs {

  /**
   * the constructor for ImageViewImpl.
   * @param model the model to display various messages for
   * @param out the Appendable to write to
   */
  public ImageViewImpl(ImageModel model, Appendable out) {
    if (model == null || out  == null) {
      throw new IllegalArgumentException("Args cannot be null");
    }
    this.model = model;
    this.out = out;
  }
}
