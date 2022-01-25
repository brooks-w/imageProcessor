
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import model.PngJpegBmpModel;
import model.PpmModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


/**
 * Class to test methods from the PngJpegBmpModel class.
 */
public class PngJpegBmpModelTest {
  PngJpegBmpModel originalMario;
  PngJpegBmpModel mario;
  PngJpegBmpModel allFiltersMario;

  PngJpegBmpModel originalDice;
  PngJpegBmpModel dice;
  PngJpegBmpModel allFiltersDice;

  PngJpegBmpModel originalBmpDice;
  PngJpegBmpModel bmpDice;
  PngJpegBmpModel allFiltersBmpDice;

  PpmModel dog;

  @Before
  public void initData() throws IOException {
    originalMario = new PngJpegBmpModel("res/mario.jpeg");
    mario = new PngJpegBmpModel("res/mario.jpeg");
    allFiltersMario = new PngJpegBmpModel("res/allFiltersMario.jpeg");

    originalDice = new PngJpegBmpModel("res/dice.png");
    dice = new PngJpegBmpModel("res/dice.png");
    allFiltersDice = new PngJpegBmpModel("res/allFiltersDice.png");

    originalBmpDice = new PngJpegBmpModel("res/bmpDice.bmp");
    bmpDice = new PngJpegBmpModel("res/bmpDice.bmp");
    allFiltersBmpDice = new PngJpegBmpModel("res/allFiltersBmpDice.bmp");

    dog = new PpmModel("res/dog.ppm");
  }

  /**
   * Test conversion from Jpeg to ppm.
   *
   * @throws IOException if file cannot be found.
   */
  @Test
  public void JpegToPPM() throws IOException {
    //catch the exception because bmpDice.jpeg does not exist yet
    try {
      PpmModel marioPPM = new PpmModel("res/mario.ppm");
    } catch (FileNotFoundException e) {
      assertEquals(e.getMessage(), "Can't read input file!");
    }
    //create mario.ppm
    mario.saveImage("res/mario.ppm");

    //confirm mario.ppm NOW exists
    PpmModel marioPPM = new PpmModel("res/mario.ppm");
  }

  /**
   * Test conversion from png to ppm.
   *
   * @throws IOException if file cannot be found.
   */
  @Test
  public void PngToPPM() throws IOException {
    //catch the exception because dice.PPM does not exist yet
    try {
      PpmModel dicePPM = new PpmModel("res/dice.ppm");
    } catch (FileNotFoundException e) {
      assertEquals(e.getMessage(),
              "res\\dice.ppm (The system cannot find the file specified)");
    }
    //create dice.ppm
    dice.saveImage("res/dice.ppm");

    //confirm dice.ppm NOW exists
    PpmModel dicePPM = new PpmModel("res/dice.ppm");
  }

  /**
   * Test conversion from bmp to ppm.
   *
   * @throws IOException if file cannot be found.
   */
  @Test
  public void BmpToPPM() throws IOException {
    //catch the exception because bmpDice.PPM does not exist yet
    try {
      PpmModel bmpDicePPM = new PpmModel("res/bmpDice.ppm");
    } catch (FileNotFoundException e) {
      assertEquals(e.getMessage(), "Can't read input file!");
    }
    //create bmpDice.ppm
    bmpDice.saveImage("res/bmpDice.ppm");

    //confirm bmpDice.ppm NOW exists
    PpmModel bmpDicePPM = new PpmModel("res/bmpDice.ppm");
  }

  /**
   * Test jpeg to png conversion.
   *
   * @throws IOException if file cannot be found.
   */
  @Test
  public void JpegToPng() throws IOException {
    //catch the exception because mario.png does not exist yet
    try {
      PngJpegBmpModel marioPng = new PngJpegBmpModel("res/mario.png");
    } catch (IOException e) {
      assertEquals(e.getMessage(), "Can't read input file!");
    }
    //create mario.png
    mario.saveImage("res/mario.png");

    //confirm mario.png NOW exists
    PngJpegBmpModel marioPng = new PngJpegBmpModel("res/mario.png");
  }

  /**
   * Test jpeg to bmp conversion.
   *
   * @throws IOException if file cannot be found.
   */
  @Test
  public void JpegToBmp() throws IOException {
    //catch the exception because mario.bmp does not exist yet
    try {
      PngJpegBmpModel marioBmp = new PngJpegBmpModel("res/mario.bmp");
    } catch (IOException e) {
      assertEquals(e.getMessage(), "Can't read input file!");
    }
    //create mario.png
    mario.saveImage("res/mario.bmp");

    //confirm mario.png NOW exists
    PngJpegBmpModel marioBmp = new PngJpegBmpModel("res/mario.bmp");
  }


  /**
   * Test conversion from png to jpeg.
   *
   * @throws IOException if file cannot be found.
   */
  @Test
  public void pngToJpeg() throws IOException {
    //catch the exception because dice.jpeg does not exist yet
    try {
      PngJpegBmpModel diceJpeg = new PngJpegBmpModel("res/dice.jpeg");
    } catch (IOException e) {
      assertEquals(e.getMessage(), "Can't read input file!");
    }
    //create dog.png
    dice.saveImage("res/dice.jpeg");

    //confirm dog.png NOW exists
    PngJpegBmpModel diceJpeg = new PngJpegBmpModel("res/dice.jpeg");
  }

  /**
   * Test conversion from png to bmp.
   *
   * @throws IOException if file cannot be found.
   */
  @Test
  public void pngTBmp() throws IOException {
    //catch the exception because dice.bmp does not exist yet
    try {
      PngJpegBmpModel diceBmp = new PngJpegBmpModel("res/dice.bmp");
    } catch (IOException e) {
      assertEquals(e.getMessage(), "Can't read input file!");
    }
    //create dice.bmp
    dice.saveImage("res/dice.bmp");

    //confirm dice.bmp NOW exists
    PngJpegBmpModel diceBmp = new PngJpegBmpModel("res/dice.bmp");
  }

  /**
   * Test conversion from bmp to jpeg.
   *
   * @throws IOException if file cannot be found.
   */
  @Test
  public void bmpToJpeg() throws IOException {
    //catch the exception because bmpDice.jpeg does not exist yet
    try {
      PngJpegBmpModel diceBmpJpeg = new PngJpegBmpModel("res/bmpDice.jpeg");
    } catch (IOException e) {
      assertEquals(e.getMessage(), "Can't read input file!");
    }
    //create dog.png
    bmpDice.saveImage("res/bmpDice.jpeg");

    //confirm bmpDice.jpeg NOW exists
    PngJpegBmpModel diceBmp = new PngJpegBmpModel("res/bmpDice.jpeg");
  }

  /**
   * Test conversion from bmp to png.
   *
   * @throws IOException if file cannot be found.
   */
  @Test
  public void bmpToPng() throws IOException {
    //catch the exception because bmpDice.png does not exist yet
    try {
      PngJpegBmpModel diceBmpPng = new PngJpegBmpModel("res/bmpDice.png");
    } catch (IOException e) {
      assertEquals(e.getMessage(), "Can't read input file!");
    }
    //create bmpDice.png
    bmpDice.saveImage("res/bmpDice.png");

    //confirm bmpDice.png NOW exists
    PngJpegBmpModel diceBmpPng = new PngJpegBmpModel("res/bmpDice.png");
  }


  /**
   * Test converting from ppmToJpeg.
   *
   * @throws IOException if file cannot be found.
   */
  @Test
  public void ppmToJpeg() throws IOException {
    //catch the exception because dog.jpeg does not exist yet
    try {
      PngJpegBmpModel dogJpeg = new PngJpegBmpModel("res/dog.jpeg");
    } catch (IOException e) {
      assertEquals(e.getMessage(), "Can't read input file!");
    }
    //create dog.jpeg
    dog.saveImage("res/dog.jpeg");

    //confirm dog.jpeg NOW exists
    PngJpegBmpModel dogJpeg = new PngJpegBmpModel("res/dog.jpeg");
  }

  /**
   * Test the conversion from PPM to png.
   *
   * @throws IOException if this file does not exist.
   */
  @Test
  public void ppmToPng() throws IOException {
    //catch the exception because dog.png does not exist yet
    try {
      PngJpegBmpModel dogJpeg = new PngJpegBmpModel("res/dog.png");
    } catch (IOException e) {
      assertEquals(e.getMessage(), "Can't read input file!");
    }
    //create dog.png
    dog.saveImage("res/dog.png");

    //confirm dog.png NOW exists
    PngJpegBmpModel dogJpeg = new PngJpegBmpModel("res/dog.png");
  }

  /**
   * Test the conversion from PPM to BMP.
   *
   * @throws IOException if the file does not exist.
   */
  @Test
  public void ppmToBmp() throws IOException {
    //catch the exception because dog.png does not exist yet
    try {
      PngJpegBmpModel dogBmp = new PngJpegBmpModel("res/dog.bmp");
    } catch (IOException e) {
      assertEquals(e.getMessage(), "Can't read input file!");
    }
    //create dog.bmp
    dog.saveImage("res/dog.bmp");

    //confirm dog.png NOW exists
    PngJpegBmpModel dogBmp = new PngJpegBmpModel("res/dog.bmp");
  }

  /**
   * Convert between PPM to bmp while calling an operation in between.
   */
  @Test
  public void convertBetweenFormatsAndOperations() throws IOException {
    //catch the exception because dogSharpen.bmp does not exist yet
    try {
      PngJpegBmpModel dogBmp = new PngJpegBmpModel("res/dogSharpen.bmp");
    } catch (IOException e) {
      assertEquals(e.getMessage(), "Can't read input file!");
    }
    //create dogSharpen.bmp
    dog.sharpen();
    dog.saveImage("res/dogSharpen.bmp");

    //confirm dogSharpen.bmp NOW exists
    PngJpegBmpModel dogBmp = new PngJpegBmpModel("res/dogSharpen.bmp");
  }


  /**
   * Confirm that the all operations can be applied to a jpeg image.
   *
   * @throws IOException throw exception if image cannot be written.
   */
  @Test
  public void testFiltersOnJpeg() throws IOException {
    assertNotEquals(mario, allFiltersMario);
    mario.sepia();
    mario.sharpen();
    mario.blur();
    mario.vilComponent("luma");
    mario.horizontalFlip();
    mario.verticalFLip();
    mario.changeBrightness(100);
    mario.changeBrightness(-10);
    mario.saveImage("res/newAllFiltersMario.jpeg");
    assertEquals(mario.getPixels().size(), allFiltersMario.getPixels().size());
    PngJpegBmpModel newAllFilters = new PngJpegBmpModel("res/newAllFiltersMario.jpeg");
    assertEquals(allFiltersMario, newAllFilters);
    assertNotEquals(mario, originalMario);
  }

  /**
   * Confirm that the all operations can be applied to a png image.
   *
   * @throws IOException throw exception if image cannot be written.
   */
  @Test
  public void testFiltersOnPng() throws IOException {
    assertNotEquals(dice, allFiltersDice);
    dice.sepia();
    dice.sharpen();
    dice.blur();
    dice.vilComponent("luma");
    dice.horizontalFlip();
    dice.verticalFLip();
    dice.changeBrightness(100);
    dice.changeBrightness(-10);
    dice.saveImage("res/newAllFiltersDice.png");
    assertEquals(dice.getPixels().size(), allFiltersDice.getPixels().size());
    PngJpegBmpModel newAllFilters2 = new PngJpegBmpModel("res/newAllFiltersDice.png");
    assertEquals(allFiltersDice, newAllFilters2);
    assertNotEquals(dice, originalDice);
  }

  /**
   * Confirm that the all operations can be applied to a bmp image.
   *
   * @throws IOException throw exception if image cannot be written.
   */
  @Test
  public void testFiltersOnBmp() throws IOException {
    assertNotEquals(bmpDice, allFiltersBmpDice);
    bmpDice.sepia();
    bmpDice.sharpen();
    bmpDice.blur();
    bmpDice.vilComponent("luma");
    bmpDice.horizontalFlip();
    bmpDice.verticalFLip();
    bmpDice.changeBrightness(100);
    bmpDice.changeBrightness(-10);
    bmpDice.saveImage("res/newAllFiltersBmpDice.png");
    assertEquals(bmpDice.getPixels().size(), allFiltersBmpDice.getPixels().size());
    PngJpegBmpModel newAllFilters3 = new PngJpegBmpModel("res/newAllFiltersBmpDice.png");
    assertEquals(allFiltersBmpDice, newAllFilters3);
    assertNotEquals(bmpDice, allFiltersBmpDice);
  }

  /**
   * Test that the constructor throws an exception when the jpeg file given does not exist.
   *
   * @throws IOException if the specified file cannot be found.
   */
  @Test
  public void testConstructorException() throws IOException {
    try {
      PngJpegBmpModel missingModel = new PngJpegBmpModel("Missing.jpeg");
    } catch (IOException e) {
      assertEquals(e.getMessage(), "Can't read input file!");
    }
  }

  /**
   * Test that the constructor throws an exception when the png file given does not exist.
   *
   * @throws IOException if the specified file cannot be found.
   */
  @Test
  public void testConstructorException2() throws IOException {
    try {
      PngJpegBmpModel missingModel = new PngJpegBmpModel("Missing.png");
    } catch (IOException e) {
      assertEquals(e.getMessage(), "Can't read input file!");
    }
  }

  /**
   * Test that the constructor throws an exception when the bmp file given does not exist.
   *
   * @throws IOException if the specified file cannot be found.
   */
  @Test
  public void testConstructorException3() throws IOException {
    try {
      PngJpegBmpModel missingModel = new PngJpegBmpModel("Missing.bmp");
    } catch (IOException e) {
      assertEquals(e.getMessage(), "Can't read input file!");
    }
  }


}


