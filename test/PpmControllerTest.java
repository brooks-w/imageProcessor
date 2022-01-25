import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import controller.MockController;
import controller.ImageControllerImpl;
import model.PpmModel;
import view.ImageViewImpl;

import static org.junit.Assert.assertEquals;

/**
 * Tests for the ImageControllerImpl class.
 */
public class PpmControllerTest {
  PpmModel dog;
  ImageViewImpl view;
  StringBuilder builder;
  ImageControllerImpl controller;
  StringBuilder log;
  MockController mockController;


  /**
   * Initializes data that will be used for testing.
   *
   * @throws FileNotFoundException throws an exception if the file cannot be found.
   */
  @Before
  public void initData() throws IOException {
    builder = new StringBuilder();
    dog = new PpmModel("res/dog.ppm");
    view = new ImageViewImpl(dog, builder);
    controller = new ImageControllerImpl(dog, view);
    this.log = new StringBuilder("");
    this.mockController = new MockController(this.log);
  }


  /**
   * testing if the controller works by feeding it an appendable of operations
   * and seeing if the mock controller "executes" them. Tests the go method
   * by running it on the controller.
   */
  @Test
  public void testControllerGo() throws IOException {
    this.mockController = this.mockController.addCommands(new StringBuilder("r\n" +
            "g\n" +
            "b\n" +
            "Bright:20\n" +
            "Dark:30\n" +
            "value\n" +
            "intensity\n" +
            "luma\n" +
            "vflip\n" +
            "hflip\n" +
            "Save:Koala2.ppm"));
    this.mockController.goProgram();
    assertEquals(this.log.toString(), "Adding to opList\n" +
            "red comp\n" +
            "green comp\n" +
            "blue comp\n" +
            "calling bright\n" +
            "calling dark\n" +
            "value\n" +
            "intensity\n" +
            "luma\n" +
            "vertical flip\n" +
            "horizontal flip\n" +
            "saving as Koala2.ppm\n");
  }

  /**
   * Test the exception thrown when given a null view.
   */
  @Test
  public void testConstructorExceptions() {
    try {
      ImageControllerImpl nullController = new ImageControllerImpl(dog, null);
    } catch (IllegalArgumentException | IOException exception) {
      assertEquals(exception.getMessage(), "Args cannot be null");
    }
  }

  /**
   * Test the exception thrown when given a null model.
   */
  @Test
  public void testConstructorExceptions2() {
    try {
      ImageControllerImpl nullController = new ImageControllerImpl(null, view);
    } catch (IllegalArgumentException | IOException exception) {
      assertEquals(exception.getMessage(), "Args cannot be null");
    }
  }
}