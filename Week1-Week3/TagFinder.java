import edu.duke.DirectoryResource;
import edu.duke.FileResource;

import java.io.File;

/**
 * Created by Hamit on 7/15/2016.
 */
public class TagFinder {
    public String findProtein(String dna){
        dna = dna.toLowerCase();
        int start = dna.indexOf("gta");
        if(start == -1){
            return ""; //no start codon found
        }
        int stop = dna.indexOf("tag",start+3);
        if((stop - start) % 3 ==0){
            return dna.substring(start,stop+3);
        }
        else
            return "";
    }
    public void realTesting(){
        DirectoryResource dr = new DirectoryResource();
        for(File f: dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            String s = fr.asString();
            System.out.println("read "+s.length()+" characters");
            String result = findProtein(s);
            System.out.println("found "+ result);
        }
    }

    public static void main(String[] args) {
        TagFinder proteinFinder = new TagFinder();
        proteinFinder.realTesting();
    }
}
