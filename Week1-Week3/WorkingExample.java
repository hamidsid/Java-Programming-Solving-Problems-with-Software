/**

 * Write a description of WorkingExample here.

 *

 * @author (your name)

 * @version (a version number or a date)

 */

import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import edu.duke.StorageResource;

import java.io.File;

public class WorkingExample {

    public int findStopIndex(String dna, int index) {

        int stop1 = dna.indexOf("TGA", index);

        if (stop1 == -1 || (stop1 - index) % 3 != 0) {

            stop1 = dna.length();

        }

        int stop2 = dna.indexOf("TAA", index);

        if (stop2 == -1 || (stop2 - index) % 3 != 0) {

            stop2 = dna.length();

        }

        int stop3 = dna.indexOf("TAG", index);

        if (stop3 == -1 || (stop3 - index) % 3 != 0) {

            stop3 = dna.length();

        }

        return Math.min(stop1, Math.min(stop2, stop3));

    }

    public StorageResource storeAll(String dna) {

        //CATGTAATAGATGAATGACTGATAGATATGCTTGTATGCTATGAAAATGTGAAATGACCCAdna = "CATGTAATAGATGAATGACTGATAGATATGCTTGTATGCTATGAAAATGTGAAATGACCCA";

        String sequence = dna.toUpperCase();

        StorageResource store = new StorageResource();

        int index = 0;

        while (true) {

            index = sequence.indexOf("ATG", index);

            if (index == -1)

                break;

            int stop = findStopIndex(sequence, index + 3);

            if (stop != sequence.length()) {

                String gene = dna.substring(index, stop + 3);

                store.add(gene);

                System.out.println("From: " + index + " to " + stop + " Gene: " + gene );//index = sequence.substring(index, stop + 3).length();

                index = stop + 3; // start at the end of the stop codon

            }else{ index = index + 3;

            }

        }

        return store;//System.out.println(sequence);

    }

    public void testStorageFinder() {

        DirectoryResource dr = new DirectoryResource();

        StorageResource dnaStore = new StorageResource();
        StorageResource ctgStore = new StorageResource();

        for (File f : dr.selectedFiles()) {

            FileResource fr = new FileResource(f);

            String s = fr.asString();

            dnaStore = storeAll(s);
            ctgStore = findCTG(s);

            printGenes(dnaStore);


        }

        System.out.println("size = " + dnaStore.size());
        System.out.println("ctg size = " + ctgStore.size());

    }

    public float calCGRatio(String gene){

        gene = gene.toUpperCase();

        int len = gene.length();

        int CGCount = 0;

        for(int i=0; i<len; i++){

            if(gene.charAt(i) == 'C' || gene.charAt(i) == 'G')
            {
                CGCount++;
            }

        }//end for loop

        System.out.println("CGCount " + CGCount + " Length: " + len + " Ratio: " + (float)CGCount/len);

        return (float)CGCount/len;

    }//end of calCGRatio() method;

    public void printGenes(StorageResource sr){
        int longerthan60 = 0;
        int CGGreaterthan35 = 0;
        for(String gene: sr.data()){

            if (gene.length() > 60) {

                System.out.println(gene.length()+"\t"+gene);
                longerthan60++;

            }

            if(calCGRatio(gene)> 0.35) {

                System.out.println(gene.length()+"\t"+gene);
                CGGreaterthan35++;

            }

        }

        System.out.println("\n There are " + sr.size() + " genes. ");

        System.out.println("There are " + longerthan60 + " genes longer than 60.");

        System.out.println("There are " + CGGreaterthan35 + " genes with CG ratio greater than 0.35.");

    }//end main();


    public StorageResource findCTG(String dna) {

        //CATGTAATAGATGAATGACTGATAGATATGCTTGTATGCTATGAAAATGTGAAATGACCCAdna = "CATGTAATAGATGAATGACTGATAGATATGCTTGTATGCTATGAAAATGTGAAATGACCCA";

        String sequence = dna.toUpperCase();

        StorageResource store = new StorageResource();

        int index = 0;


        while (true) {

            index = sequence.indexOf("CTG", index);

            if (index == -1){

                break;



            }else{
                store.add("CTG");
                index = index + 3;

            }

        }

        return store;//System.out.println(sequence);

    }

    public static void main(String[] args) {
        WorkingExample storedgenes = new WorkingExample();
        storedgenes.testStorageFinder();
    }

}

