import org.junit.Test;

import java.io.IOException;


import static org.junit.Assert.assertEquals;

/**
 * to test the GUIImageController class.
 */
public class GuiImageControllerTest {

  @Test
  public void testGuiImageController() throws IOException {
    MockGuiImageController mock = new MockGuiImageController();

    mock.execute(("load res/dice.png sepia vertical-flip luma brighten 20 save nuts.jpeg")
            .split(" "));

    assertEquals(mock.log.toString(), new StringBuilder("command line input\n" +
            "loading an image\n" +
            "sepia\n" +
            "vflip\n" +
            "luma\n" +
            "brightening\n" +
            "saving\n").toString());
  }
}
