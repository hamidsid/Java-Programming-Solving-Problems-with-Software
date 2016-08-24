import edu.duke.DirectoryResource;
import edu.duke.ImageResource;
import edu.duke.Pixel;

import java.io.File;

/**
 * Created by Hamit on 7/14/2016.
 */
public class GrayScaleConverter {
    //I strated with the image i wanted(inImage)
    public ImageResource makeGray(ImageResource inImage){
        //i made a blank image of the same size
        ImageResource outImage = new ImageResource(inImage.getWidth(),inImage.getHeight());
        for (Pixel pixel: outImage.pixels()){
            Pixel inPixel = inImage.getPixel(pixel.getX(),pixel.getY());
            int average =(inPixel.getRed()+inPixel.getBlue()+inPixel.getGreen())/3;
            pixel.setRed(average);
            pixel.setBlue(average);
            pixel.setGreen(average);
        }
        return outImage;
    }
    public void testGray(){
        ImageResource ir = new ImageResource();
        ImageResource gray = makeGray(ir);
        gray.draw();
    }
    public void selectAndConvertAndSave(){
        DirectoryResource dr = new DirectoryResource();
        for (File f: dr.selectedFiles()){
            ImageResource colorImages = new ImageResource(f);
            ImageResource grayImages = makeGray(colorImages);
            File newfile = new File("C:\\Users\\Hamit\\Desktop\\New Folder (2)\\Gray_Images_Of_Selected_Images2222");
            newfile.mkdirs();
            String oldName = colorImages.getFileName();
            String newName = "gray-"+ oldName;
            grayImages.setFileName(newName);
            grayImages.save();
            grayImages.draw();
        }

    }
}
