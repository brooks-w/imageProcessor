import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

import utils.ImageUtil;
import utils.Pixel;

import static org.junit.Assert.assertEquals;


/**
 * Tests the ImageUtil class.
 */
public class ImageUtilTest {
  ImageUtil util;
  Pixel pixel;
  ArrayList<ArrayList<Pixel>> pixelAr;

  /**
   * Initialzes the data that will be used.
   */
  @Before
  public void initData() {
    util = new ImageUtil();
    pixel = new Pixel(255, 255, 255);
    pixelAr = new ArrayList<ArrayList<Pixel>>();
    pixelAr.add(new ArrayList<Pixel>());
    pixelAr.add(new ArrayList<Pixel>());
    pixelAr.add(new ArrayList<Pixel>());

    pixelAr.get(0).add(pixel);
    pixelAr.get(0).add(pixel);
    pixelAr.get(1).add(pixel);
    pixelAr.get(1).add(pixel);
    pixelAr.get(2).add(pixel);
    pixelAr.get(2).add(pixel);
  }

  /**
   * Tests the readPPM() method in the ImageUtil class.
   */
  @Test
  public void readPPM() throws FileNotFoundException {
    assertEquals(util.readPPM("res/imagesPPM.ppm"), pixelAr);
  }

  /**
   * Tests the getIndex method.
   */
  @Test
  public void testGetIndex() {
    assertEquals(util.getIndex(
            new ArrayList<String>(Arrays.asList("ad", "bc", "cd")), "bc"), 1);
    assertEquals(util.getIndex(
            new ArrayList<String>(Arrays.asList("ad", "bc", "cd")), "james"), -1);
  }



}