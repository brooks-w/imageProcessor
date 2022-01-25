import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;

import model.PpmModel;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertNotEquals;


/**
 * Class to test the PpmModel class.
 */
public class PpmModelTest {
  PpmModel dog;
  PpmModel originalDog;
  PpmModel verticalDog;
  PpmModel horizontalDog;
  PpmModel brighterBy50;
  PpmModel darkerBy50;
  PpmModel redComp;
  PpmModel greenComp;
  PpmModel blueComp;
  PpmModel valComp;
  PpmModel intComp;
  PpmModel lumaComp;
  PpmModel sepiaDog;
  PpmModel blurDog;
  PpmModel sharpenDog;

  /**
   * Initializes data that will be used in testing.
   *
   * @throws FileNotFoundException Throws exception if the file cannot be found.
   */
  @Before
  public void initData() throws FileNotFoundException {
    dog = new PpmModel("res/dog.ppm");
    originalDog = new PpmModel("res/dog.ppm");
    verticalDog = new PpmModel("res/verticalDog.ppm");
    horizontalDog = new PpmModel("res/horizontalDog.ppm");
    brighterBy50 = new PpmModel("res/brighterBy50.ppm");
    darkerBy50 = new PpmModel("res/darkenBy50.ppm");
    redComp = new PpmModel("res/redComp.ppm");
    greenComp = new PpmModel("res/greenComp.ppm");
    blueComp = new PpmModel("res/blueComp.ppm");
    valComp = new PpmModel("res/valComp.ppm");
    intComp = new PpmModel("res/intComp.ppm");
    lumaComp = new PpmModel("res/lumaComp.ppm");
    sepiaDog = new PpmModel("res/sepiaDog.ppm");
    blurDog = new PpmModel("res/blurDog.ppm");
    sharpenDog = new PpmModel("res/sharpenDog.ppm");
  }


  /**
   * Test that the constructor throws an exception when the file given to the model does not exist.
   *
   * @throws FileNotFoundException if the specified file cannot be found.
   */
  @Test
  public void testConstructorException() throws FileNotFoundException {
    try {
      PpmModel missingModel = new PpmModel("Missing.ppm");
    } catch (FileNotFoundException e) {
      assertEquals(e.getMessage(), "Missing.ppm (The system cannot find the file specified)");
    }
  }

  /**
   * Test to confirm that the sharpen method works.
   */
  @Test
  public void testSharpen() {
    assertNotEquals(dog, sharpenDog);
    dog.sharpen();
    assertEquals(dog, sharpenDog);
    assertNotEquals(dog, originalDog);
  }

  /**
   * Test to confirm that the blur method works.
   */
  @Test
  public void testBlur() {
    assertNotEquals(dog, blurDog);
    dog.blur();
    assertEquals(dog, blurDog);
    assertNotEquals(dog, originalDog);
  }

  /**
   * Test to confirm that the sepia method works.
   */
  @Test
  public void testSepia() {
    assertNotEquals(dog, sepiaDog);
    dog.sepia();
    assertEquals(dog, sepiaDog);
    assertNotEquals(dog, originalDog);
  }

  /**
   * Tests to make sure an image can be flipped vertically.
   */
  @Test
  public void verticalFLip() {
    assertNotEquals(dog, verticalDog);
    dog.verticalFLip();
    assertEquals(dog, verticalDog);
    assertNotEquals(dog, originalDog);
  }

  /**
   * Tests to make sure an image can be flipped horizontally.
   */
  @Test
  public void horizontalFlip() {
    assertNotEquals(dog, horizontalDog);
    dog.horizontalFlip();
    assertEquals(dog, horizontalDog);
    assertNotEquals(dog, originalDog);
  }

  /**
   * Tests the changeBrightness method by making an image brighter by a factor of 50.
   */
  @Test
  public void makeBrighter() {
    assertNotEquals(dog, brighterBy50);
    dog.changeBrightness(50);
    assertEquals(dog, brighterBy50);
    assertNotEquals(dog, originalDog);
  }

  /**
   * Tests the changeBrightness method by making an image darker by a factor of 50.
   */
  @Test
  public void makeDarker() {
    assertNotEquals(dog, darkerBy50);
    dog.changeBrightness(-50);
    assertEquals(dog, darkerBy50);
    assertNotEquals(dog, originalDog);
  }

  /**
   * Makes a new image with the red component.
   */
  @Test
  public void redComponent() {
    assertNotEquals(dog, redComp);
    dog.colorComponent("red-component");
    assertEquals(dog, redComp);
    assertNotEquals(dog, originalDog);
  }

  /**
   * Makes a new image with green component.
   */
  @Test
  public void greenComponent() {
    assertNotEquals(dog, greenComp);
    dog.colorComponent("green-component");
    assertEquals(dog, greenComp);
    assertNotEquals(dog, originalDog);
  }

  /**
   * Makes a new image with the blue component.
   */
  @Test
  public void blueComponent() {
    assertNotEquals(dog, blueComp);
    dog.colorComponent("blue-component");
    assertEquals(dog, blueComp);
    assertNotEquals(dog, originalDog);
  }


  @Test
  public void valueComponent() throws FileNotFoundException {
    assertNotEquals(dog, valComp);
    dog.vilComponent("value");
    assertEquals(dog, valComp);
    assertNotEquals(dog, originalDog);
  }

  @Test
  public void intensityComponent() throws FileNotFoundException {
    assertNotEquals(dog, intComp);
    dog.vilComponent("intensity");
    assertEquals(dog, intComp);
    assertNotEquals(dog, originalDog);
  }

  @Test
  public void lumaComponent() throws FileNotFoundException {
    assertNotEquals(dog, lumaComp);
    dog.vilComponent("luma");
    assertEquals(dog, lumaComp);
    assertNotEquals(dog, originalDog);
  }




}