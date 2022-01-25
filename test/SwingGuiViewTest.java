import org.junit.Before;
import org.junit.Test;

import java.awt.event.ActionEvent;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * Represents a class to test the commands in the view are given to the controller.
 */
public class SwingGuiViewTest {
  MockView view;
  MockGuiImageController mock;


  /**
   * Represents the initial state of the data.
   */
  @Before
  public void initData() {
    mock = new MockGuiImageController();
    view = new MockView(mock);
  }


  //Test by trigering events on a Mock View and checking if those are passed to the Mock controller

  /**
   * Testing the horizontal flip command.
   *
   * @throws IOException if transmission fails.
   */
  @Test
  public void testHFlip() throws IOException {
    mock.execute(("load res/dice.png")
            .split(" "));
    ActionEvent e = new ActionEvent("Does not matter", 21, "hflip");
    view.actionPerformed(e);
    assertEquals(mock.log.toString(),
            "command line input\n" +
                    "loading an image\n" +
                    "command line input\n" +
                    "hflip\n");
  }

  /**
   * Testing the vertical flip command.
   *
   * @throws IOException if transmission fails.
   */
  @Test
  public void testVFlip() throws IOException {
    mock.execute(("load res/dice.png")
            .split(" "));
    ActionEvent e = new ActionEvent("Does not matter", 21, "vflip");
    view.actionPerformed(e);
    assertEquals(mock.log.toString(),
            "command line input\n" +
                    "loading an image\n" +
                    "command line input\n" +
                    "vflip\n");
  }

  /**
   * Testing the value command.
   *
   * @throws IOException if transmission fails.
   */
  @Test
  public void testValue() throws IOException {
    mock.execute(("load res/dice.png")
            .split(" "));
    ActionEvent e = new ActionEvent("Does not matter", 21, "value");
    view.actionPerformed(e);
    assertEquals(mock.log.toString(),
            "command line input\n" +
                    "loading an image\n" +
                    "command line input\n" +
                    "value\n");
  }

  /**
   * Testing the intensity flip command.
   *
   * @throws IOException if transmission fails.
   */
  @Test
  public void testIntensity() throws IOException {
    mock.execute(("load res/dice.png")
            .split(" "));
    ActionEvent e = new ActionEvent("Does not matter", 21, "intensity");
    view.actionPerformed(e);
    assertEquals(mock.log.toString(),
            "command line input\n" +
                    "loading an image\n" +
                    "command line input\n" +
                    "intensity\n");
  }

  /**
   * Testing the luma flip command.
   *
   * @throws IOException if transmission fails.
   */
  @Test
  public void testLuma() throws IOException {
    mock.execute(("load res/dice.png")
            .split(" "));
    ActionEvent e = new ActionEvent("Does not matter", 21, "luma");
    view.actionPerformed(e);
    assertEquals(mock.log.toString(),
            "command line input\n" +
                    "loading an image\n" +
                    "command line input\n" +
                    "luma\n");
  }

  /**
   * Testing the red component flip command.
   *
   * @throws IOException if transmission fails.
   */
  @Test
  public void testR() throws IOException {
    mock.execute(("load res/dice.png")
            .split(" "));
    ActionEvent e = new ActionEvent("Does not matter", 21, "r");
    view.actionPerformed(e);
    assertEquals(mock.log.toString(),
            "command line input\n" +
                    "loading an image\n" +
                    "command line input\n" +
                    "r\n");
  }

  /**
   * Testing the green component flip command.
   *
   * @throws IOException if transmission fails.
   */
  @Test
  public void testG() throws IOException {
    mock.execute(("load res/dice.png")
            .split(" "));
    ActionEvent e = new ActionEvent("Does not matter", 21, "g");
    view.actionPerformed(e);
    assertEquals(mock.log.toString(),
            "command line input\n" +
                    "loading an image\n" +
                    "command line input\n" +
                    "g\n");
  }

  /**
   * Testing the blue component flip command.
   *
   * @throws IOException if transmission fails.
   */
  @Test
  public void testB() throws IOException {
    mock.execute(("load res/dice.png")
            .split(" "));
    ActionEvent e = new ActionEvent("Does not matter", 21, "b");
    view.actionPerformed(e);
    assertEquals(mock.log.toString(),
            "command line input\n" +
                    "loading an image\n" +
                    "command line input\n" +
                    "b\n");
  }

  /**
   * Testing the sepia command.
   *
   * @throws IOException if transmission fails.
   */
  @Test
  public void testSepia() throws IOException {
    mock.execute(("load res/dice.png")
            .split(" "));
    ActionEvent e = new ActionEvent("Does not matter", 21, "sepia");
    view.actionPerformed(e);
    assertEquals(mock.log.toString(),
            "command line input\n" +
                    "loading an image\n" +
                    "command line input\n" +
                    "sepia\n");
  }

  /**
   * Testing the blur command.
   *
   * @throws IOException if transmission fails.
   */
  @Test
  public void testBlur() throws IOException {
    mock.execute(("load res/dice.png")
            .split(" "));
    ActionEvent e = new ActionEvent("Does not matter", 21, "blur");
    view.actionPerformed(e);
    assertEquals(mock.log.toString(),
            "command line input\n" +
                    "loading an image\n" +
                    "command line input\n" +
                    "blur\n");
  }

  /**
   * Testing the sharpen command.
   *
   * @throws IOException if transmission fails.
   */
  @Test
  public void testSharpen() throws IOException {
    mock.execute(("load res/dice.png")
            .split(" "));
    ActionEvent e = new ActionEvent("Does not matter", 21, "sharpen");
    view.actionPerformed(e);
    assertEquals(mock.log.toString(),
            "command line input\n" +
                    "loading an image\n" +
                    "command line input\n" +
                    "sharpen\n");
  }

  /**
   * Testing the brighten command.
   *
   * @throws IOException if transmission fails.
   */
  @Test
  public void testBrighten() throws IOException {
    mock.execute(("load res/dice.png")
            .split(" "));
    ActionEvent e = new ActionEvent("Does not matter", 21, "brighten");
    view.actionPerformed(e);
    assertEquals(mock.log.toString(),
            "command line input\n" +
                    "loading an image\n" +
                    "command line input\n" +
                    "brightening\n");
  }

  /**
   * Testing the darken command.
   *
   * @throws IOException if transmission fails.
   */
  @Test
  public void testDarken() throws IOException {
    mock.execute(("load res/dice.png")
            .split(" "));
    ActionEvent e = new ActionEvent("Does not matter", 21, "darken");
    view.actionPerformed(e);
    assertEquals(mock.log.toString(),
            "command line input\n" +
                    "loading an image\n" +
                    "command line input\n" +
                    "darkening\n");
  }


}
