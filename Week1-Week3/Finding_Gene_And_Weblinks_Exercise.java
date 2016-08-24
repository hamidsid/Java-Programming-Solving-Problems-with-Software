import edu.duke.DirectoryResource;
import edu.duke.FileResource;

import java.io.File;

/**
 * Created by Hamit on 7/15/2016.
 */
public class Finding_Gene_And_Weblinks_Exercise {
    String findGene(String dna){
        dna = dna.toUpperCase();
        int start = dna.indexOf("ATG");
        if(start<0){
            return "";
        }

        int stop1 = dna.indexOf("TAG",start+3);
        if((stop1 - start) % 3 == 0){
            return dna.substring(start,stop1+3);
        }

        int stop2 = dna.indexOf("TGA",start+3);
        if((stop2-start) % 3 == 0){
            return dna.substring(start,stop2+3);
        }

        int stop3 = dna.indexOf("TAA",start+3);
        if((stop3-start) % 3 == 0){
            return dna.substring(start,stop3+3);
        }

        return "";
    }

    public String stopCodon(String dna) {
        String answer = findGene(dna);
        int size = answer.length();
        if ( size > 6 ) {
            return answer.substring(size - 3, size);
        }
        else {
            return "";
        }
    }

    public void realTesting2(){
        DirectoryResource dr = new DirectoryResource();
        for(File f: dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            String s = fr.asString();
            System.out.println("read "+s.length()+" characters");
            String result = findGene(s);
            if (result.isEmpty())
                System.out.println("No DNA match found");
            else
            System.out.println("found "+ result);
            System.out.println("now  the stop codon");
            result = stopCodon(s);
            System.out.println(result);
        }
    }


    public static void main(String[] args) {
        Finding_Gene_And_Weblinks_Exercise proteinFinder = new Finding_Gene_And_Weblinks_Exercise();
        proteinFinder.realTesting2();
    }

}
