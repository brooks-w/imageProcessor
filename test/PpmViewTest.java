import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import model.PpmModel;
import view.ImageViewImpl;

import static org.junit.Assert.assertEquals;

/**
 * Class to test the ImageViewImpl class.
 */
public class PpmViewTest {

  StringBuilder builder;
  PpmModel defaultModel;

  /**
   * Initializes data used in testing.
   *
   * @throws FileNotFoundException if the specified file cannot be found.
   */
  @Before
  public void initData() throws FileNotFoundException {
    builder = new StringBuilder();
    defaultModel = new PpmModel("res/dog.ppm");
  }


  /**
   * Test to make sure an exception is thrown when given a null model.
   */
  @Test
  public void testConstructorException1() {
    try {
      ImageViewImpl nullView = new ImageViewImpl(null, System.out);
    } catch (IllegalArgumentException exception) {
      assertEquals(exception.getMessage(), "Args cannot be null");
    }
  }

  /**
   * Test to make sure an exception is thrown when given a null appendable.
   */
  @Test
  public void testConstructorException2() throws FileNotFoundException {
    PpmModel dog = new PpmModel("res/dog.ppm");
    try {
      ImageViewImpl nullView = new ImageViewImpl(dog, null);
    } catch (IllegalArgumentException exception) {
      assertEquals(exception.getMessage(), "Args cannot be null");
    }
  }


  /**
   * Test to make sure the renderMessage method works correctly.
   */
  @Test
  public void testRenderMessage() throws IOException {
    Appendable appendable = new StringBuilder("I am a test, ");
    PpmModel defaultModel = new PpmModel("res/dog.ppm");
    ImageViewImpl defaultView = new ImageViewImpl(defaultModel, appendable);
    //should append "hello" to the previous string
    defaultView.renderMessage("hello");
    assertEquals(appendable.toString(), "I am a test, hello\n");
  }

  /**
   * Test to make sure the renderMessage throws an IOException correctly.
   */
  @Test
  public void testRenderMessageException() throws IOException {
    try {
      ImageViewImpl view = new ImageViewImpl(defaultModel, builder);
      view.renderMessage(builder.toString());
    } catch (IOException exception) {
      assertEquals(exception.getMessage(), "Could not write.");
    }
  }


}