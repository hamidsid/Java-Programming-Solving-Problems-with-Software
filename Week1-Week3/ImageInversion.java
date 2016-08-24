import edu.duke.DirectoryResource;
import edu.duke.ImageResource;
import edu.duke.Pixel;

import java.io.File;

/**
 * Created by Hamit on 7/14/2016.
 */
public class ImageInversion {
    public ImageResource makeInversion(ImageResource colorImage){
    //first we make an image of the same size
        ImageResource inverseImage = new ImageResource(colorImage.getWidth(),colorImage.getHeight());
        for (Pixel pixel: inverseImage.pixels()){
            Pixel inPixel = colorImage.getPixel(pixel.getX(),pixel.getY());
            int newRed = 255- (inPixel.getRed());
            int newBlue = 255- (inPixel.getBlue());
            int newGreen = 255- (inPixel.getGreen());
            pixel.setRed(newRed);
            pixel.setGreen(newBlue);
            pixel.setBlue(newGreen);
        }
    return inverseImage;
    }

    public void testGray(){
        ImageResource ir = new ImageResource();
        ImageResource gray = makeInversion(ir);
        gray.draw();
    }

    public void selectandInversion(){
        DirectoryResource dr = new DirectoryResource();
        for(File f: dr.selectedFiles()){
            ImageResource colorImages = new ImageResource(f);
            ImageResource invertImages = makeInversion(colorImages);
            String oldName = colorImages.getFileName();
            String newName = "inverted-"+oldName;
            invertImages.setFileName(newName);
            invertImages.draw();
            invertImages.save();
        }
    }
}
