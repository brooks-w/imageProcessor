package controller;

import java.awt.Dimension;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.WindowConstants;

import view.SwingGuiView;

/**
 * to run operations on the image processor, and accept user input.
 */
public class ImageMain {

  /**
   * to run the program and prompt for user input. Split between accepting
   * command line arguments, user input via a scanner, and running the GUI.
   * Delegates most of the functionality to an "overlord" controller
   * that runs the program and manages multiple smaller controllers.
   */
  public static void main(String[] args) {
    GuiImageController controller = new GuiImageController();
    boolean quit = false;

    //run GUI
    if (args.length == 0) {
      SwingGuiView view = new SwingGuiView(controller);

      SwingGuiView.setDefaultLookAndFeelDecorated(false);
      view.setTitle("Homework 6: Image Processor Gui");

      view.setSize(new Dimension(1000, 1000));
      view.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      view.setVisible(true);
    }

    //else either support manual input or command line input;
    for (int i = 0; i < args.length; i++) {
      String s = args[i];

      //command line input
      if (s.equals("-file")) {
        String[] cmds = ("cmdfile " + args[i + 1]).split(" ");

        try {
          controller.execute(cmds);
        } catch (IOException e) {
          System.out.println("Args in command file failed.");
          break;
        }
      }

      //manual input
      if (s.equals("-text")) {
        Scanner sc = new Scanner(System.in);
        while (!quit) {

          String[] cmds;
          if (sc.hasNextLine()) {
            cmds = sc.nextLine().split(" ");

            try {
              quit = controller.execute(cmds);
            } catch (IOException e) {
              System.out.println("Error; likely you need to check the filepath for " +
                      "the image you are trying to load.");
              break;
            }
          }
        }
      }
    }
  }
}
