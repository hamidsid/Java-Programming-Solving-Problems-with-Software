import edu.duke.DirectoryResource;
import edu.duke.ImageResource;

import java.io.File;

/**
 * Created by Hamit on 7/14/2016.
 */
public class ImageSaver {
    public void doSave(){
        DirectoryResource dr = new DirectoryResource();
        for (File f: dr.selectedFiles()){
            ImageResource img = new ImageResource(f);
            String fname = img.getFileName();
            String newName = "copy-"+fname;
            img.setFileName(newName);
            img.save();
            img.draw();

        }
    }

    public static void main(String[] args) {
        ImageSaver myimage= new ImageSaver();
        myimage.doSave();
    }
}
