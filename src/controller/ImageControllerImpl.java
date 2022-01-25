package controller;

import java.io.IOException;

import model.ImageModel;
import view.ImageView;

/**
 * to implement operations provided by the ImageController
 * interface, to allow for the running of operations on a
 * ImageModel.
 */
public class ImageControllerImpl extends ImageControllerAbs<ImageControllerImpl> {
  /**
   * the constructor for ImageControllerImpl.
   * @param model the model to be operated on
   * @param view the view to display any messages or other info
   */
  public ImageControllerImpl(ImageModel model, ImageView view) throws IOException {
    if (model == null || view == null) {
      throw new IllegalArgumentException("Args cannot be null");
    }
    this.model = model;
    this.view = view;
  }
}
