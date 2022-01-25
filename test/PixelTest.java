import org.junit.Before;
import org.junit.Test;

import utils.Pixel;

import static org.junit.Assert.assertEquals;


/**
 * Class to represent tests for the Pixel class.
 */
public class PixelTest {
  Pixel yellow;
  Pixel pink;
  Pixel orange;

  /**
   * Instances of Pixels that will have operations done on them.
   */
  @Before
  public void initData() {
    yellow = new Pixel(252, 255, 0);
    pink = new Pixel(255, 160, 223);
    orange = new Pixel(255, 169, 0);
  }

  /**
   * Tests the getRed() method to make sure the proper value is retrieved.
   */
  @Test
  public void getRed() {
    assertEquals(252, yellow.getRed());
    assertEquals(255, pink.getRed());
    assertEquals(255, orange.getRed());
  }

  /**
   * Tests the getGreen() method to make sure the proper value is retrieved.
   */
  @Test
  public void getGreen() {
    assertEquals(255, yellow.getGreen());
    assertEquals(160, pink.getGreen());
    assertEquals(169, orange.getGreen());
  }

  /**
   * Tests the getBlue() method to make sure the proper value is retrieved.
   */
  @Test
  public void getBlue() {
    assertEquals(0, yellow.getBlue());
    assertEquals(223, pink.getBlue());
    assertEquals(0, orange.getBlue());
  }

  /**
   * Tests to make the red field is set to the given value.
   */
  @Test
  public void setRed() {
    assertEquals(252, yellow.getRed());
    yellow.setRed(0);
    assertEquals(0, yellow.getRed());
  }

  /**
   * Tests to make the green field is set to the given value.
   */
  @Test
  public void setGreen() {
    assertEquals(255, yellow.getGreen());
    yellow.setGreen(0);
    assertEquals(0, yellow.getGreen());
  }

  /**
   * Tests to make the blue field is set to the given value.
   */
  @Test
  public void setBlue() {
    assertEquals(0, yellow.getBlue());
    yellow.setBlue(1);
    assertEquals(1, yellow.getBlue());
    yellow.setBlue(15);
    assertEquals(15, yellow.getBlue());
  }

  /**
   * Tests to make the red field is set to the given value when the value is over 252.
   */
  @Test
  public void setRedOver255() {
    assertEquals(252, yellow.getRed());
    yellow.setRed(300);
    assertEquals(255, yellow.getRed());
  }

  /**
   * Tests to make the red field is set to the given value when the value is below 0.
   */
  @Test
  public void setRedBelow0() {
    assertEquals(252, yellow.getRed());
    yellow.setRed(-1);
    assertEquals(0, yellow.getRed());
  }

  /**
   * Tests to make the green field is set to the given value when the value is over 252.
   */
  @Test
  public void setGreenOver255() {
    assertEquals(255, yellow.getGreen());
    yellow.setGreen(300);
    assertEquals(255, yellow.getGreen());
  }

  /**
   * Tests to make the green field is set to the given value when the value is below 0.
   */
  @Test
  public void setGreenBelow0() {
    assertEquals(255, yellow.getGreen());
    yellow.setGreen(-1);
    assertEquals(0, yellow.getGreen());
  }

  /**
   * Tests to make the blue field is set to the given value when the value is over 252.
   */
  @Test
  public void setBlueOver255() {
    assertEquals(0, yellow.getBlue());
    yellow.setBlue(300);
    assertEquals(255, yellow.getBlue());
  }

  /**
   * Tests to make the red field is set to the given value when the value is below 0.
   */
  @Test
  public void setBlueBelow0() {
    assertEquals(0, yellow.getBlue());
    yellow.setBlue(-1);
    assertEquals(0, yellow.getBlue());
  }

  /**
   * to test the clone() method.
   */
  @Test
  public void testClone() {
    assertEquals(yellow, yellow.clone());
    assertEquals(pink, pink.clone());
  }




}