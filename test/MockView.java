

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpinnerModel;
import javax.swing.JSpinner;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import view.DrawingPanel;

import controller.GuiImageControllerInterface;

/**
 * to respresent and display the GUI interface and allow for operations to be run on it.
 */
public class MockView extends JFrame implements ActionListener {
  private GuiImageControllerInterface controller;
  private BufferedImage currentImage;
  private JScrollPane currentImagePanel;
  private JSpinner brightenAmount;
  private JSpinner darkenAmount;

  /**
   * a constructor for the GuiView.
   * @param controller the controller to perform operations on
   */
  public MockView(GuiImageControllerInterface controller) {
    super();
    this.controller = controller;

    JPanel mainPanel;
    JPanel operationPanels;
    JPanel loadSavePanel;
    JPanel darkenBrightenPanel;
    JPanel histogramPanel;

    mainPanel = new JPanel();
    mainPanel.setLayout(new BorderLayout());

    histogramPanel = new DrawingPanel(this.controller);
    histogramPanel.setPreferredSize(new Dimension(20, 200));
    histogramPanel.setFocusable(true);


    operationPanels = new JPanel();
    operationPanels.setLayout(new BoxLayout(operationPanels, BoxLayout.PAGE_AXIS));

    loadSavePanel = new JPanel();
    loadSavePanel.setLayout(new FlowLayout());

    darkenBrightenPanel = new JPanel();
    darkenBrightenPanel.setLayout(new BoxLayout(darkenBrightenPanel, BoxLayout.PAGE_AXIS));


    currentImagePanel = new JScrollPane();
    currentImagePanel.setSize(10, 10);

    JButton horizontalFlip;
    JButton verticalFlip;
    JButton blur;
    JButton sepia;
    JButton sharpen;
    JButton value;
    JButton intensity;
    JButton luma;
    JButton redComp;
    JButton greenComp;
    JButton blueComp;
    JButton greyScale;
    JButton brighten;
    JButton darken;

    //Handling the buttons for basic operations
    horizontalFlip = new JButton("Horizontal Flip");
    horizontalFlip.setActionCommand("hflip");
    horizontalFlip.addActionListener(this);

    verticalFlip = new JButton("Vertical Flip");
    verticalFlip.setActionCommand("vflip");
    verticalFlip.addActionListener(this);

    blur = new JButton("Blur");
    blur.setActionCommand("blur");
    blur.addActionListener(this);

    sepia = new JButton("Sepia");
    sepia.setActionCommand("sepia");
    sepia.addActionListener(this);

    sharpen = new JButton("Sharpen");
    sharpen.setActionCommand("sharpen");
    sharpen.addActionListener(this);


    //handling the component operations
    value = new JButton("Value Component");
    value.setActionCommand("value");
    value.addActionListener(this);

    intensity = new JButton("Intensity Component");
    intensity.setActionCommand("intensity");
    intensity.addActionListener(this);

    luma = new JButton("Luma Component");
    luma.setActionCommand("luma");
    luma.addActionListener(this);

    redComp = new JButton("Red Component");
    redComp.setActionCommand("r");
    redComp.addActionListener(this);

    greenComp = new JButton("Green Component");
    greenComp.setActionCommand("g");
    greenComp.addActionListener(this);

    blueComp = new JButton("Blue Component");
    blueComp.setActionCommand("b");
    blueComp.addActionListener(this);


    brighten = new JButton("Brighten");
    brighten.setActionCommand("brighten");
    brighten.addActionListener(this);

    darken = new JButton("Darken");
    darken.setActionCommand("darken");
    darken.addActionListener(this);

    greyScale = new JButton("Greyscale");
    greyScale.setActionCommand("greyscale");
    greyScale.addActionListener(this);


    SpinnerModel bSpinner = new SpinnerNumberModel(0, 0, 100, 1);
    this.brightenAmount = new JSpinner(bSpinner);

    SpinnerModel dSpinner = new SpinnerNumberModel(0, 0, 100, 1);
    this.darkenAmount = new JSpinner(dSpinner);

    JPanel brightenPanel = new JPanel();
    brightenPanel.setLayout(new FlowLayout());

    JPanel darkenPanel = new JPanel();
    darkenPanel.setLayout(new FlowLayout());

    darkenPanel.add(this.darkenAmount);
    darkenPanel.add(darken);

    brightenPanel.add(this.brightenAmount);
    brightenPanel.add(brighten);

    JPanel spacePanel = new JPanel();
    spacePanel.setLayout(new FlowLayout());

    darkenBrightenPanel.add(brightenPanel);
    darkenBrightenPanel.add(darkenPanel);
    darkenBrightenPanel.add(spacePanel);


    operationPanels.add(verticalFlip);
    operationPanels.add(Box.createRigidArea(new Dimension(10, 25)));
    operationPanels.add(horizontalFlip);
    operationPanels.add(Box.createRigidArea(new Dimension(0, 25)));
    operationPanels.add(blur);
    operationPanels.add(Box.createRigidArea(new Dimension(0, 25)));
    operationPanels.add(sepia);
    operationPanels.add(Box.createRigidArea(new Dimension(0, 25)));
    operationPanels.add(sharpen);
    operationPanels.add(Box.createRigidArea(new Dimension(0, 25)));
    operationPanels.add(value);
    operationPanels.add(Box.createRigidArea(new Dimension(0, 25)));
    operationPanels.add(intensity);
    operationPanels.add(Box.createRigidArea(new Dimension(0, 25)));
    operationPanels.add(luma);
    operationPanels.add(Box.createRigidArea(new Dimension(0, 25)));
    operationPanels.add(redComp);
    operationPanels.add(Box.createRigidArea(new Dimension(0, 25)));
    operationPanels.add(greenComp);
    operationPanels.add(Box.createRigidArea(new Dimension(0, 25)));
    operationPanels.add(blueComp);
    operationPanels.add(Box.createRigidArea(new Dimension(0, 25)));
    operationPanels.add(greyScale);


    //file open
    JPanel fileopenPanel = new JPanel();
    fileopenPanel.setLayout(new FlowLayout());
    JButton fileOpenButton = new JButton("Open a file");
    fileOpenButton.setActionCommand("Open file");
    fileOpenButton.addActionListener(this);
    fileopenPanel.add(fileOpenButton);
    loadSavePanel.add(fileopenPanel);

    //file save
    JPanel filesavePanel = new JPanel();
    filesavePanel.setLayout(new FlowLayout());
    JButton fileSaveButton = new JButton("Save a file");
    fileSaveButton.setActionCommand("Save file");
    fileSaveButton.addActionListener(this);
    filesavePanel.add(fileSaveButton);
    loadSavePanel.add(filesavePanel);


    mainPanel.add(histogramPanel, BorderLayout.PAGE_END);
    mainPanel.add(darkenBrightenPanel, BorderLayout.LINE_END);
    mainPanel.add(operationPanels, BorderLayout.LINE_START);
    mainPanel.add(loadSavePanel, BorderLayout.PAGE_START);
    mainPanel.add(this.currentImagePanel, BorderLayout.CENTER);
    this.add(mainPanel);
  }

  private void refresh() {
    ImageIcon img = new ImageIcon(this.currentImage);
    JLabel label = new JLabel(img);
    this.currentImagePanel.setViewportView(label);
    this.repaint();
  }

  private void addCommand(String command) {
    try {
      String[] arr = new String[1];
      arr[0] = command;
      controller.execute(arr);
      this.currentImage = controller.pullData();
      refresh();
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  private void darkenBrightenCommand(String command, JSpinner spinner) {
    try {
      String value = spinner.getValue().toString();
      String[] arr = new String[2];
      arr[0] = command;
      arr[1] = value;
      controller.execute(arr);
      this.currentImage = controller.pullData();
      refresh();
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }


  @Override
  public void actionPerformed(ActionEvent e) {
    try {
      switch (e.getActionCommand()) {
        case "Open file":
          final JFileChooser fchooser = new JFileChooser(".");
          FileNameExtensionFilter filter = new FileNameExtensionFilter(
                  "PPM, JPEG, PNG, BMP", "ppm", "jpeg", "png", "bmp");
          fchooser.setFileFilter(filter);
          int retvalue = fchooser.showOpenDialog(MockView.this);
          if (retvalue == JFileChooser.APPROVE_OPTION) {
            File f = fchooser.getSelectedFile();
            try {
              String[] arr = new String[2];
              arr[0] = "load";
              arr[1] = f.getAbsolutePath();
              controller.execute(arr);
              //displays the histogram
              this.repaint();
            } catch (IOException ex) {
              ex.printStackTrace();
            }
            ImageIcon img = new ImageIcon(controller.pullData());
            JLabel label = new JLabel(img);
            currentImagePanel.setViewportView(label);
          }
          break;
        case "hflip":
          addCommand("horizontal-flip");
          break;
        case "vflip":
          addCommand("vertical-flip");
          break;
        case "value":
          addCommand("value");
          break;
        case "intensity":
          addCommand("intensity");
          break;
        case "luma":
          addCommand("luma");
          break;
        case "r":
          addCommand("r");
          break;
        case "g":
          addCommand("g");
          break;
        case "b":
          addCommand("b");
          break;
        case "sepia":
          addCommand("sepia");
          break;
        case "blur":
          addCommand("blur");
          break;
        case "sharpen":
          addCommand("sharpen");
          break;
        case "brighten":
          darkenBrightenCommand("brighten", this.brightenAmount);
          break;
        case "darken":
          darkenBrightenCommand("darken", this.darkenAmount);
          break;
        case "greyscale":
          addCommand("greyscale");
          break;
        case "Save file":
          final JFileChooser fchooser2 = new JFileChooser(".");
          int retvalue2 = fchooser2.showSaveDialog(MockView.this);
          if (retvalue2 == JFileChooser.APPROVE_OPTION) {
            File f = fchooser2.getSelectedFile();
            try {
              controller.execute(("save " + f.getAbsolutePath()).
                      split(" "));
            } catch (IOException ignored) {
              //don't need to do anything, just ignore error.
            }
          }
          JOptionPane.showMessageDialog(this, "Saved to " +
                  fchooser2.getSelectedFile(), "Save", JOptionPane.PLAIN_MESSAGE);
          break;
        default:
          throw new UnsupportedOperationException("Shouldn't get here");
      }
    } catch (IndexOutOfBoundsException ignored) { }
  }
}
