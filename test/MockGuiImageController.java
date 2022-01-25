
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

import controller.GuiImageControllerInterface;
import model.ImageModel;
import utils.ImageUtil;
import utils.Pixel;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;

/**
 * a Mock of the GuiImageController class for testing.
 */
public class MockGuiImageController implements GuiImageControllerInterface {
  //flags and scanner
  private boolean loaded = false;
  private boolean quit = false;
  private boolean cmdline = false;
  Scanner scanner = new Scanner(System.in);

  //THE NAME OF THE FILE MUST BE SPECIFIED BEFORE ANY OPERATIONS
  //YOU CANNOT TRY TO RUN OPERATIONS BEFORE LOADING AN IMAGE
  private String imgPathName = "";
  private String saveName = "";

  //these cannot be less than 0; as if you are trying to brighten
  //something by less than 0 you should be darkening it
  int bright = -1;
  int dark = -1;

  //paired arraylists containing ImageModels, operations to be ran on each
  //ImageModel, and the filepath the ImageModel has.
  ArrayList<ImageModel> modelList = new ArrayList<ImageModel>();

  //operations are always applied to the most recently loaded image
  //be it an image explicitly loaded (either by "load/ image.ppm img" or by stating
  //its alias (ie. typing "load img" on the command line after "img"
  // has been specified as an alias))
  ArrayList<Appendable> operationList = new ArrayList<Appendable>();

  //contains the file path of every image that has been loaded
  // in order for indexing to give these images operations
  ArrayList<String> pathList = new ArrayList<String>();

  //a hashmap with the key as an alias and the value being the path that alias points to
  HashMap<String, String> aliasHash = new HashMap<String, String>();

  //for utility
  ImageUtil<String> strUtils = new ImageUtil<String>();

  //a log for mock testing
  public StringBuilder log = new StringBuilder("");

  /**
   * a helper that runs all of the main functionality for the project, both for the GUI and
   * the command line/main function. Built this way so that data can be sent to the controller
   * (and therefore model) from the view without having to go through a main function.
   * Returns a boolean so anything calling this method can know to quit the program.
   *
   * @param args arguments for the program to execute
   * @return true if the program has been quit
   * @throws IOException if writes fail
   */
  public boolean execute(String[] args) throws IOException {
    //initializing data
    String[] arguments;

    if (args.length != 0 && !this.cmdline) {
      arguments = args;
      this.log.append("command line input\n");
    } else if (this.scanner.hasNext()) {
      arguments = this.scanner.nextLine().split("\\s+");
      this.log.append("manual input\n");
    } else {
      arguments = new String[0];
    }

    //iterating thru given arguments
    for (int i = 0; i < arguments.length; i++) {
      //current arg
      String s = arguments[i];
      //System.out.println("Current arg: " + s);

      //is the user trying to load a txt file of commands
      if (s.equals("cmdfile")) {
        this.log.append("command file loading\n");
      }

      //just loading an image, or loading a previously aliased image
      if (s.equals("load")) {
        this.loaded = true;
        this.log.append("loading an image\n");
        i += 1;
      }

      //loading an image and giving it an alias, or overwriting the alias with another file
      if (s.equals("load/")) {
        this.loaded = true;
        this.log.append("loading an image with alias\n");
        i += 2;
      }

      //quitting the program
      if (s.equalsIgnoreCase("Quit")) {
        this.log.append("quitting\n");
        return this.quit;
      }

      //did the user load an image before trying anything else?
      if (!this.loaded && !s.equals("cmdfile")) {
        this.log.append("trying operation before loads\n");
        break;
      }

      if (s.equals("brighten")) {
        this.log.append("brightening\n");
        i += 1;
      }

      if (s.equals("darken")) {
        this.log.append("darkening\n");
        i += 1;
      }

      //saving to the filepath specified after the save argument, or to the filepath
      //associated with the alias after save
      if (s.equals("save")) {
        this.log.append("saving\n");
        i += 1;
      }

      //various grayscale operations
      if (s.equals("r")) {
        this.log.append("r\n");
      }

      if (s.equals("g")) {
        this.log.append("g\n");
      }

      if (s.equals("b")) {
        this.log.append("b\n");
      }

      if (s.equals("value")) {
        this.log.append("value\n");
      }

      if (s.equals("luma")) {
        this.log.append("luma\n");
      }

      if (s.equals("intensity")) {
        this.log.append("intensity\n");
      }

      if (s.equals("horizontal-flip")) {
        this.log.append("hflip\n");
      }

      if (s.equals("vertical-flip")) {
        this.log.append("vflip\n");
      }

      if (s.equals("sepia")) {
        this.log.append("sepia\n");
      }

      if (s.equals("greyscale")) {
        this.log.append("greyscale\n");
      }

      if (s.equals("blur")) {
        this.log.append("blur\n");
      }

      if (s.equals("sharpen")) {
        this.log.append("sharpen\n");
      }
    }

    //the program has not been quit
    return false;
  }

  /**
   * gets the pixel data of the currently loaded image and returns it as a buffered
   * image so the SwingGUIView can render the changes to the currently loaded image.
   *
   * @return returns the currently loaded image data as a BufferedImage
   */
  @Override
  public BufferedImage pullData() {
    ArrayList<ArrayList<Pixel>> curData =
            this.modelList.get(this.modelList.size() - 1).getPixelData();
    //preliminary data
    int height = curData.size();
    int width = curData.get(0).size();
    BufferedImage writeBuffered = new BufferedImage(width, height, TYPE_INT_RGB);

    //write rgb data into the ImageBuffer
    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        int red = curData.get(row).get(col).getRed();
        int rgb = red;
        rgb = (rgb << 8) + curData.get(row).get(col).getGreen();
        rgb = (rgb << 8) + curData.get(row).get(col).getBlue();

        writeBuffered.setRGB(col, row, rgb);
      }
    }
    return writeBuffered;
  }

  /**
   * converts pixel data into a format that can be used to plot a histogram
   * of red, green, blue, and intensity values for the image represented in the current model.
   *
   * @return a 2D arraylist of the four lists of data, in the order:
   *        red value frequencies, green value frequencies, blue value frequencies,
   *        and intensity frequencies
   */
  public ArrayList<ArrayList<Integer>> getHistogramData() {
    //arrayLists containing the four sets of data needed to make the histogram
    //the index of the item corresponds to its x-position on the histogram
    //the value at that index is the y-value or frequency
    ArrayList<Integer> redList = new ArrayList<>();
    ArrayList<Integer> greenList = new ArrayList<>();
    ArrayList<Integer> blueList = new ArrayList<>();
    ArrayList<Integer> intensityList = new ArrayList<>();

    for (int j = 0; j < 256; j++) {
      redList.add(0);
      greenList.add(0);
      blueList.add(0);
      intensityList.add(0);
    }

    ArrayList<ArrayList<Pixel>> pixList =
            this.modelList.get(this.modelList.size() - 1).getPixelData();

    //reds, for each pixel
    for (int row = 0; row < pixList.size(); row++) {
      for (int col = 0; col < pixList.get(row).size(); col++) {
        //increase count by 1
        int index = pixList.get(row).get(col).getRed();
        redList.set(index, redList.get(index) + 1);
      }
    }

    //greens, for each pixel
    for (int row = 0; row < pixList.size(); row++) {
      for (int col = 0; col < pixList.get(row).size(); col++) {
        //increase count by 1
        int index = pixList.get(row).get(col).getGreen();
        greenList.set(index, greenList.get(index) + 1);
      }
    }

    //blues, for each pixel
    for (int row = 0; row < pixList.size(); row++) {
      for (int col = 0; col < pixList.get(row).size(); col++) {
        //increase count by 1
        int index = pixList.get(row).get(col).getBlue();
        blueList.set(index, blueList.get(index) + 1);
      }
    }

    //(rounded) intensity, for each pixel
    for (int row = 0; row < pixList.size(); row++) {
      for (int col = 0; col < pixList.get(row).size(); col++) {
        //increase count by 1
        int index = (pixList.get(row).get(col).getRed() +
                pixList.get(row).get(col).getGreen() +
                pixList.get(row).get(col).getBlue()) / 3;

        intensityList.set(index, intensityList.get(index) + 1);
      }
    }

    //returns the four lists of data in 1 2D array
    return new ArrayList<>(Arrays.asList(redList, greenList, blueList,
            intensityList));
  }
}
