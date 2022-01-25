package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JPanel;
import controller.GuiImageControllerInterface;

/**
 * to display the histogram of the image loaded in the controller.
 */
public class DrawingPanel extends JPanel {
  private GuiImageControllerInterface controller;

  /**
   * constructs the drawing panel.
   * @param controller a controller to run the drawing panel with.
   */
  public DrawingPanel(GuiImageControllerInterface controller) {
    super();
    this.controller = controller;
  }

  /**
   * to plot the red, green, blue, and intensity frequencies of an ImageModel in a histogram.
   */
  public void plotHistogram(Graphics g) throws IndexOutOfBoundsException {
    ArrayList<ArrayList<Integer>> histData = new ArrayList<ArrayList<Integer>>();

    try {
      histData = this.controller.getHistogramData();
    } catch (IndexOutOfBoundsException e) {
      return;
    }

    ArrayList<Integer> redData = histData.get(0);
    ArrayList<Integer> greenData = histData.get(1);
    ArrayList<Integer> blueData = histData.get(2);
    ArrayList<Integer> intensityData = histData.get(3);

    int maxY = Math.max(Collections.max(redData), Math.max(Collections.max(blueData),
            Math.max(Collections.max(greenData), Collections.max(intensityData))));


    //THE DRAWING
    //some constants
    int leftX = 160;
    int bottomY = 150;
    int rightX = 255 * 3 + leftX;
    int topY = 10;

    g.setFont(new Font("TimesRoman", Font.PLAIN, 12));

    //x/y axis
    g.setColor(Color.black);
    g.drawLine(leftX, bottomY, rightX, bottomY);
    g.drawLine(leftX, bottomY, leftX, topY);
    //labels
    g.drawString("0, 0", leftX - 25, bottomY + 15);
    g.drawString("255", rightX - 15, bottomY + 15);
    g.drawString(maxY + "", leftX - 4 - (("" + maxY).length() * 7), topY + 10);

    g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
    g.drawString("Value", (rightX - leftX) / 2 + 80, bottomY + 30);
    g.drawString("Frequency", leftX - 105, bottomY - 60);

    //code not put into helper because it is simple and the function would
    //have to accept a lot of local variables as arguments

    //red loop
    g.setColor(Color.red);
    for (int i = 0; i < redData.size(); i++) {
      double scalar = (redData.get(i) * 1.0 / maxY);
      int y = (int) (bottomY - ((bottomY - topY) * scalar));
      //the line
      if (i != redData.size() - 1) {
        int y2 = (int) (bottomY - ((bottomY - topY) * redData.get(i + 1) * 1.0 / maxY));
        g.drawLine(i * 3 + leftX, y - 1, (i + 1) * 3 + leftX, y2 - 1);
      }
    }

    //green loop
    g.setColor(Color.green);
    for (int i = 0; i < greenData.size(); i++) {
      double scalar = (greenData.get(i) * 1.0 / maxY);
      int y = (int) (bottomY - ((bottomY - topY) * scalar));
      //the line
      if (i != greenData.size() - 1) {
        int y2 = (int) (bottomY - ((bottomY - topY) * greenData.get(i + 1) * 1.0 / maxY));
        g.drawLine(i * 3 + leftX, y - 1, (i + 1) * 3 + leftX, y2 - 1);
      }
    }

    //blue loop
    g.setColor(Color.blue);
    for (int i = 0; i < blueData.size(); i++) {
      double scalar = (blueData.get(i) * 1.0 / maxY);
      int y = (int) (bottomY - ((bottomY - topY) * scalar));
      //the line
      if (i != blueData.size() - 1) {
        int y2 = (int) (bottomY - ((bottomY - topY) * blueData.get(i + 1) * 1.0 / maxY));
        g.drawLine(i * 3 + leftX, y - 1, (i + 1) * 3 + leftX, y2 - 1);
      }
    }

    //intensity loop
    g.setColor(Color.black);
    for (int i = 0; i < intensityData.size(); i++) {
      double scalar = (intensityData.get(i) * 1.0 / maxY);
      int y = (int) (bottomY - ((bottomY - topY) * scalar));
      //the line
      if (i != intensityData.size() - 1) {
        int y2 = (int) (bottomY - ((bottomY - topY) * intensityData.get(i + 1) * 1.0 / maxY));
        g.drawLine(i * 3 + leftX, y - 1, (i + 1) * 3 + leftX, y2 - 1);
      }
    }
  }

  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    this.plotHistogram(g);
  }



}
