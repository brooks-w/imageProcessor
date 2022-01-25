package controller;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Scanner;

import model.ImageModel;
import model.PngJpegBmpModel;
import model.PpmModel;
import utils.ImageUtil;
import utils.Pixel;
import view.ImageView;
import view.ImageViewImpl;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;

/**
 * to represent a controller that runs the entire project and can manage multiple images.
 * runs all the main functionality for the project, both for the GUI and
 * the command line/main function. Built this way so that data can be sent to the controller
 * (and therefore model) from the view without having to go through a main function.
 */
public class GuiImageController implements GuiImageControllerInterface {
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
  private ArrayList<ImageModel> modelList = new ArrayList<ImageModel>();

  //operations are always applied to the most recently loaded image
  //be it an image explicitly loaded (either by "load/ image.ppm img" or by stating
  //its alias (ie. typing "load img" on the command line after "img"
  // has been specified as an alias))
  private ArrayList<Appendable> operationList = new ArrayList<Appendable>();

  //contains the file path of every image that has been loaded
  // in order for indexing to give these images operations
  private ArrayList<String> pathList = new ArrayList<String>();

  //a hashmap with the key as an alias and the value being the path that alias points to
  private HashMap<String, String> aliasHash = new HashMap<String, String>();

  //for utility
  private ImageUtil<String> strUtils = new ImageUtil<String>();

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
    } else if (this.scanner.hasNext()) {
      arguments = this.scanner.next().split("\\s+");
      System.out.println(Arrays.toString(args));
    } else {
      arguments = new String[0];
    }

    //iterating thru given arguments
    for (int i = 0; i < arguments.length; i++) {
      //current arg
      String s = arguments[i];

      //is the user trying to load a txt file of commands
      if (s.equals("cmdfile")) {
        // you didn't provide enough inputs
        if (i + 1 >= arguments.length) {
          throw new NoSuchElementException("No more arguments");
        }
        //set our arguments to what is contained within the file, separated by newlines
        Scanner sc = new Scanner(new FileInputStream(arguments[i + 1]));

        //read from the command file
        ArrayList<String> temp = new ArrayList<>();
        while (sc.hasNextLine()) {
          String[] line = sc.nextLine().split("\\s+");
          temp.addAll(Arrays.asList(line));
        }
        //set our arguments list to what was read from the file
        arguments = temp.toArray(arguments);
        //start from the beginning, this will be increased to 0 at the top of the loop
        i = -1;
      }

      //just loading an image, or loading a previously aliased image
      if (s.equals("load")) {
        this.loaded = true;
        // you didn't provide enough inputs
        if (i + 1 >= arguments.length) {
          throw new NoSuchElementException("No more arguments to read");
        }
        //getting the image path as a string
        this.imgPathName = arguments[i + 1];

        if (this.aliasHash.containsKey(this.imgPathName)) {
          int index = strUtils.getIndex(this.pathList, this.aliasHash.get(this.imgPathName));

          //queue the re-loaded alias at the front of the lists
          this.modelList.add(this.modelList.get(index));
          this.operationList.add(this.operationList.get(index));
          this.pathList.add(this.pathList.get(index));

          //cleaning up old data
          this.modelList.remove(index);
          this.operationList.remove(index);
          this.pathList.remove(index);
        }

        if (!this.aliasHash.containsKey(this.imgPathName)) {
          //now that we have loaded an image, add operations for it, and set its alias to
          //just the filepath, as it has no alias
          if (this.aliasHash.getOrDefault(this.imgPathName, this.imgPathName)
                  .split("\\.")[1].equals("ppm")) {
            this.modelList.add(new PpmModel(this.aliasHash.getOrDefault(this.imgPathName,
                    this.imgPathName)));
          }
          if (this.aliasHash.getOrDefault(this.imgPathName, this.imgPathName).
                  split("\\.")[1].equals("jpeg")
                  || this.imgPathName.split("\\.")[1].equals("png")
                  || this.imgPathName.split("\\.")[1].equals("bmp")) {
            this.modelList.add(new PngJpegBmpModel(this.aliasHash.getOrDefault(this.imgPathName,
                    this.imgPathName)));
          }
          this.pathList.add(this.aliasHash.getOrDefault(this.imgPathName, this.imgPathName));
          this.operationList.add(new StringBuilder(""));
        }

        //if the alias does already exist, we don't need to do anything, as it is already
        //created in our lists, so setting imgPathName to the alias is all we need
        i += 1;
      }

      //loading an image and giving it an alias, or overwriting the alias with another file
      if (s.equals("load/")) {
        this.loaded = true;
        // you didn't provide enough inputs
        if (i + 2 >= arguments.length) {
          throw new NoSuchElementException("No more arguments to read");
        }
        //getting the image path (the name) as a string
        this.imgPathName = arguments[i + 1];
        String alias = arguments[i + 2];

        //We only overwrite an alias (by putting the new alias in the place of the old
        // in aliasHash), and not anything else, that way if operations are done on the old
        // alias before that alias is replaced those operations are still run

        //now that we have loaded an image, give it a model
        if (this.imgPathName.split("\\.")[1].equals("ppm")) {
          this.modelList.add(new PpmModel(this.imgPathName));
        }
        if (this.imgPathName.split("\\.")[1].equals("jpeg")
                || this.imgPathName.split("\\.")[1].equals("png")
                || this.imgPathName.split("\\.")[1].equals("bmp")) {
          this.modelList.add(new PngJpegBmpModel(this.imgPathName));
        }
        //add it to the list of paths so its index can be found later
        this.pathList.add(this.imgPathName);
        //give it a list of operations
        this.operationList.add(new StringBuilder(""));
        //put the alias and path in the aliasHash, overwriting it if it already exists
        this.aliasHash.put(alias, this.imgPathName);

        i += 2;
      }

      //quitting the program
      if (s.equalsIgnoreCase("Quit")) {
        //RUN OPERATIONS BEFORE QUITTING
        //for each image (ImageModel) we have, send the operations over, and run them with .go()
        for (int j = 0; j < this.modelList.size(); j++) {
          ImageModel model = this.modelList.get(j);
          ImageView view = new ImageViewImpl(model, System.out);

          //run the operations if there are operations to be run, ie. the opList[j] isn't just ""
          if (!this.operationList.get(j).toString().equals("")) {
            new ImageControllerImpl(model, view).addCommands(this.operationList.get(j)).goProgram();
          }
        }
        //wipe operations clean as they have been performed
        for (int item = 0; item < this.operationList.size(); item++) {
          this.operationList.set(item, new StringBuilder(""));
        }

        System.out.println("Program quit.");
        this.quit = true;
        return this.quit;
      }

      //did the user load an image before trying anything else?
      if (!this.loaded && !s.equals("cmdfile")) {
        System.out.println("You need to load a file before performing any operations.");
        break;
      }

      if (s.equals("brighten")) {
        // you didn't provide enough inputs
        if (i + 1 >= arguments.length) {
          throw new NoSuchElementException("No more arguments");
        }

        //getting brightness as an int
        try {
          this.bright = Integer.parseInt(arguments[i + 1]);
          if (this.bright < 0) {
            throw new IllegalArgumentException("Cannot be less than 0.");
          }
          this.operationList.get(this.operationList.size() - 1)
                  .append("Bright " + this.bright + "\n");
        } catch (NumberFormatException e) {
          System.err.println("Argument " + arguments[i + 1] + " must be an integer.");
        }
        i += 1;
      }

      if (s.equals("darken")) {
        // you didn't provide enough inputs
        if (i + 1 >= arguments.length) {
          throw new NoSuchElementException("No more arguments");
        }

        //getting darkness as an int
        try {
          this.dark = Integer.parseInt(arguments[i + 1]);
          if (this.dark < 0) {
            throw new IllegalArgumentException("Cannot be less than 0.");
          }
          this.operationList.get(this.operationList.size() - 1).
                  append("Dark " + this.dark + "\n");
        } catch (NumberFormatException e) {
          System.err.println("Argument " + arguments[i + 1] + " must be an integer.");
        }
        i += 1;
      }

      //saving to the filepath specified after the save argument, or to the filepath
      //associated with the alias after save
      if (s.equals("save")) {
        // you didn't provide enough inputs
        if (i + 1 >= arguments.length) {
          throw new NoSuchElementException("No more arguments to read");
        }
        //getting the image path as a string
        this.saveName = arguments[i + 1];
        this.operationList.get(this.operationList.size() - 1).append("Save " +
                this.aliasHash.getOrDefault(this.saveName, this.saveName) + "\n");
        i += 1;
      }

      //various grayscale operations
      if (s.equals("r")) {
        this.operationList.get(this.operationList.size() - 1).append("r\n");
      }

      if (s.equals("g")) {
        this.operationList.get(this.operationList.size() - 1).append("g\n");
      }

      if (s.equals("b")) {
        this.operationList.get(this.operationList.size() - 1).append("b\n");
      }

      if (s.equals("value")) {
        this.operationList.get(this.operationList.size() - 1).append("value\n");
      }

      if (s.equals("luma")) {
        this.operationList.get(this.operationList.size() - 1).append("luma\n");
      }

      if (s.equals("intensity")) {
        this.operationList.get(this.operationList.size() - 1).append("intensity\n");
      }

      if (s.equals("horizontal-flip")) {
        this.operationList.get(this.operationList.size() - 1).append("hflip\n");
      }

      if (s.equals("vertical-flip")) {
        this.operationList.get(this.operationList.size() - 1).append("vflip\n");
      }

      if (s.equals("sepia")) {
        this.operationList.get(this.operationList.size() - 1).append("sepia\n");
      }

      if (s.equals("greyscale")) {
        this.operationList.get(this.operationList.size() - 1).append("luma\n");
      }

      if (s.equals("blur")) {
        this.operationList.get(this.operationList.size() - 1).append("blur\n");
      }

      if (s.equals("sharpen")) {
        this.operationList.get(this.operationList.size() - 1).append("sharpen\n");
      }
    }

    //this is the setup of smaller controllers (they only manage 1 image each)
    // that the "overlord" GuiImageController manages

    //for each image (ImageModel) we have, send the operations over, and run them with .go()
    for (int i = 0; i < this.modelList.size(); i++) {
      ImageModel model = this.modelList.get(i);
      ImageView view = new ImageViewImpl(model, System.out);

      //run the operations if there are operations to be run, ie. the opList[i] isn't just ""
      if (!this.operationList.get(i).toString().equals("")) {
        new ImageControllerImpl(model, view).addCommands(this.operationList.get(i)).goProgram();
      }
    }

    //wipe operations clean as they have been performed
    for (int item = 0; item < this.operationList.size(); item++) {
      this.operationList.set(item, new StringBuilder(""));
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
   *         and intensity frequencies
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
