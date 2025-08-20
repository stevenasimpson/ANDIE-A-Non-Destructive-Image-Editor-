## Getting Started

Welcome to ANDIE (A Non-Destructive Image Editor) software. The goal of ANDIE is to allow for easy image editing without making changes to the original file.

ANDIE works by keeping track of the editing operations performed on the original image without applying the operations to the original. Once the image has been finalized it can be exported into a seperate image file, therefore retaining the original file. 

ANDIE is not capable of small scale changes, instead performing operations to alter the whole of an image such as colour inversion, image rotation and resizing, or blurring and sharpen operations.

## Development

The starter code for the ANDIE project was part of a second-year University of Otago paper, which focussed on adding features to an existing program. These features in this code base were implemented by a small team of five students, which can be found attributed later in this README.

## Basic User Guide

The project can be built with a service such as Gradle into an application which can be run easily using the 'run' function within Gradle. 

Following running the ANDIE Build (ie opening the ANDIE application):

- To open/save/export an image, navigate to the File Menu on the ANDIE Toolbar, then select relevant option.
- To reverse or re-apply a particular action, navigate to the Edit Menu and select Undo/Redo.

- To resize an image, navigate to the Edit Menu and select Resize, then input a value between 50 & 200% of the original image.
- To flip an image horizontally or vertically, navigate to the Edit Menu and select the desired action. 
- To rotate the image left/right 90 degrees or 180 degrees, navigate to the Edit Menu and select the relevant rotate option.

- To zoom in/out, navigate to the View Menu and select the relevant zooming option.
- To zoom to the original image size (ie 100%), navigate to the View Menu and select the Full Zoom option.

- To access the five main filters, navigate to the Filter Menu and select the relevant option.
- The available filters are Mean, Soft Blur, Sharpen, Gaussian and Median. 

- To convert the image to greyscale, navigate to the Colour Menu and select the Greyscale option.
- To invert the red/green/blue colour channels of the image, navigate to the Colour Menu and select Invert. 
- To cycle the red/green/blue colour channels in the image, navigate to the Colour Menu and select Colour Channel Cycle, then select the relevant option from the five possible changes. 

- To change the display language of the editor, navigate to the Language Menu and select the desired language.
- When the desired language has been selected, restart ANDIE and the changes will be applied. 
- The supported languages are English and Maori. 

## Folder Structure

The workspace contains five folders, where:

- `src`: the folder to maintain sources
- `test`: the folder to maintain test sources
- `gradle`: the folder to maintain gradle


Meanwhile, the compiled output files will be generated in the `build` folder.

## Project Contributions

The following sections were worked on by Neil Flores:
- Export Function

The following sections were worked on by Liam Walls:
- Sharpen Filter
- Gaussian Filter
- Median Filter

The following sections were worked on by Jarrad Marshall:
- Colour Inversion
- Colour Channel Cycling

The following sections were worked on by Ashwin Ellis:
- Multilingual Support

The following sections were worked on by Steven Simpson:
- Image Resizing
- Imaging Flipping (Horizontal & Vertical)
- Image Rotation (Left, Right 90 & 180)

The following sections were worked on by all developers:
- Documentation
- Testing
- Exception handling

## Known Bugs

Small translation errors 
    {Translation key is being updated}  
Able to open non-image files
    {File constraints are being added}  

## Testing

- For the most part, basic testing was carried out via running ANDIE and performing the task in question on an imported image. 
- The team felt that the majority of the tests for the desired actions did not warrant or could not be easily tested with a JUnit test and that the visual testing was a simpler soluion.
