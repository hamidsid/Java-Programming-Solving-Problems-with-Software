import edu.duke.DirectoryResource;

import java.io.File;

/**
 * Created by Hamit on 7/14/2016.
 */
public class DirReader {
    public void checkDir(){
        DirectoryResource dr = new DirectoryResource();
        for(File f: dr.selectedFiles()){
            System.out.println(f);
        }
    }
}
