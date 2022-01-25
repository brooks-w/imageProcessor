Overview:
This file will provide an overview of our design choices as well as the purposes of each interface
and class. Homework 6 annotations have been provided at the top of their respective sections.

----------------------------------------------------------------------------------------------------

DESIGN JUSTIFICATIONS:

HOMEWORK 6:
A lot of the "guts" of the old main was moved to the larger controller called the GUIImageController
with its respective interface GuiImageControllerInterface. This was done to allow this controller
to be instantiated and execute code for the GUI, without having to rewrite mass amounts of the
backend or duplicate a lot of the HW5 main's code in order to run the program and GUI.
The main now runs all 3 operations, the GUI, the command line input, and the manual input, in
order to support both HW6 and HW4/5 legacy operations. The underlying architecture (the old
controller, ImageControllerImpl) has stayed the same, and it still runs the same processes, just
in the GuiImageController class instead of the main. Once again, this was a way to make the program
work with the GUI without rewriting a massive amount of code.

In order to adhere to MVC design patterns, we made sure the outer controller (GuiImageController)
passes data to the GuiView via a pullData() function - to transmit new BufferedImages to the
GuiView - and a getHistogramData() function for the histogram data. Both of these make sure the
GuiView can get the data it needs without having to go through the model directly.

We also made sure our program is as decoupled as possible, as only our view, and not our controller,
uses java Swing. That way, if another view comes along that doesn't use Swing, it can be used with
our controller without massive rewrites of the controller.

(HW 5):
First, the IPixel interface and Pixel class was created to represent objects called
'Pixels.' Considering that images are comprised of pixels, and each pixel can be represented by the
amount of red in each pixel, the amount of green in each pixel, as well as the amount of blue. These
values are integers from 0 to 255. We chose to use method like getRed() and setRed(int value) to be
able to look at the state of an individual pixel, and be able to change them accordingly based on
the operation done to them.

Next, our ImageUtil class is a class that is used by our model and
converts given images into 2D arrays of pixels (and contains a misc helper method)
but does not do anything other than that.

Other than the IPixel interface and Pixel class and ImageUtil class, we adhered to the MVC system
for designing the rest of our classes/interfaces. The ImageModel, PpmModel, and PngJpegBmpModel
classes consist of methods to change that pixels in an image. This is accomplished by the constructor
converting the given String file into a 2D ArrayList of our Pixel object where each
individual pixel from the image is now represented by our Pixel object according to the Pixel class.
From here, we are able to manipulate individual pixels according to the operations done to them. At
the end of each method, the 2D array of Pixel for the image operated on is mutated to the
post-operation 2D array. Our ImageView and ImageViewImpl are strictly used for printing information
to the console. They consist of methods that are used to print messages to the console to confirm
that images had successful operations done to them.

Each model class (PpmModel and PngJpegBmpModel) represent an image and the operations that can
be performed on said image. The type of the image is denoted in the name of the respective model
it was loaded in (ie. a png will be loaded in a PngJpegBmpModel, and a ppm will be loaded in a
PpmModel). We have not split the image itself up from a model, as we do not feel that the program
needs that to be done with the current functionality. However, we acknowledge there is a chance
this may need to happen in the future, but do not think it to be necessary at the moment.

Lastly, our ImageController interface and ImageControllerImpl class consist of a method capable of
taking user inputs and telling the model what operations to do on what image and tells the view
what to display to the user. All functionality is taken care of by the model however, and the
controller does not dictate how the screen displays our results. We then have a variety of test
classes used for testing the class discussed above.

No major changes had to be made for the purposes of this assignment (HW5). Much functionality was
lifted from concrete classes to abstract (for instance, most of the controller and model
functionality was lifted to abstract classes in the same package). While this wasn't done in the
previous assignment, we built the code around being able to do this easily (as we expected to do
so), like going from solitaire HW2 -> HW3. As our concrete model classes all store the pixel data
in the same way, the concrete implementations of PpmModel and PngJpegBmpModel are very similar,
with the only difference being how pixel data is loaded and saved into their respective file
formats. The parsing and subsequent running of the operation lists for each model was also
converted to command design pattern, as it seemed like it may have been expected. We were originally
expecting this to be messy, but it was able to be solved fairly cleanly, so we kept this refactored
implementation. Nothing changed on the frontend, but this did clean up the running of operations on
ImageModels on the backend.

Otherwise, we just added the required functionality for the assignment: greyscale,
sepia, blur, and sharpen. Greyscale/luma is now implemented as a linear transformation as opposed
to the HW4 way, but does the same thing.

Justification of Mutating Image Data:
In order for our program to properly support aliasing, mutations on images inputted directly via
methods in our model need to occur. This allows for us to create multiple aliases for an image, and
keep aliases from being duplicates of another so that multiple aliases are able to exist. For
example, if a client wants to load an image, and give that image multiple aliases, our mutations
allow for these aliases to be operated on without directly messing up the contents of one another.
Lastly, we use mutation to support the fact that multiple operations can be applied to an image
prior to saving it. With our syntax not requiring you to save an image immediately after performing
an operation on it, this allows for operations to easily carry over between operations.

----------------------------------------------------------------------------------------------------

COMPLETED ELEMENTS OF THE PROGRAM:

HOMEWORK 6:
Frontend:
 - a working GUI that supports all aspects of our image manipulation program from HW5.
   - can load, apply operations to, and save an image

Backend:
 - Interfaces and methods to support the GUI, including the GUI view along with a
    new overall controller to support delegating operations to the ImageModel being operated on
 - A DrawingPanel class for the histogram, as well as methods to report the requisite data for
    said histogram

(HW5): As this assignment has been migrated to a GUI for HW6, some of this functionality has been
modified, notably the text main function.
The completed parts of the program are as follows:
Backend:
- The main function (ImageMain)
- The Controller and its implementation (ImageControllerImpl)
- Various models (PpmModel and PngJpegBmpModel), to run operations on the image types
- The utils (ImageUtil) functions for useful misc/image data operations
   - This includes the pixel class and the getters and setters therein
- The view (ImageView) to render messages, mostly successes and failures of various operations

Frontend:
- Running main allows the user to type arguments into the program like a command line
  - A .txt file of commands can also be loaded by entering "cmdfile filename.txt"
- Success or failure messages will be printed upon successful operations on a loaded image
- Unless otherwise specified in the save filename, saved images will be saved to the same folder
  that the program is in

----------------------------------------------------------------------------------------------------

Image citation for testing purposes:
    “Pixel Dog.” PixelArtMaker.com, PixelArtMaker,
    http://pixelartmaker.com/art/e5cbab002710d69. Accessed 29 Oct. 2021.

    “8-Bit Mario.” Deviant Art, https://www.deviantart.com/raivcesleinadnayr/art/8-bit-
    mario-200177190. Accessed 6 Nov. 2021.

    “PNG Transparency Demonstration 24bit PNG with 8bit
    Alpha Layer.” Wikipedia , https://en.wikipedia.org/wiki/Portable_Network_Graphics. Accessed
    6 Nov. 2021.

Code Citation:
Some code in the readPPM() method was based off of:
    https://www.w3schools.com/java/java_files_read.asp

Some code in the readBufferedImage() method was based off of:
    https://stackoverflow.com/questions/7749895/java-loop-through-pixels-in-an-image







