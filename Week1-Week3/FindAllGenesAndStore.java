import edu.duke.FileResource;
import edu.duke.StorageResource;

/**
 * Created by Hamit on 7/17/2016.
 */
public class FindAllGenesAndStore {

    public int findStopIndex(String dna, int index){
        dna = dna.toUpperCase();
        int stop1 = dna.indexOf("TAG",index);
        if(stop1 == -1 || (stop1 - index) % 3 != 0){
            stop1 = dna.length();
        }

        int stop2 = dna.indexOf("TGA",index);
        if(stop2 == -1 || (stop2 - index) % 3 != 0){
            stop2 = dna.length();
        }
        int stop3 = dna.indexOf("TAA",index);
        if(stop3 == -1 || (stop3 - index) % 3 != 0){
            stop3 = dna.length();
        }
        return Math.min(stop1, Math.min(stop2,stop3));

    }

    StorageResource storeAll(String dna){
        dna = dna.toUpperCase();
        StorageResource mystore = new StorageResource();
        int lineNumber = 0;
        int index = 0;
        while(true){
            index = dna.indexOf("ATG",index);
            if(index == -1){
                break;
            }
            int end = findStopIndex(dna,index+3);

            if(end !=dna.length()){
                //lineNumber = lineNumber + 1;
                mystore.add(dna.substring(index, end + 3));
                index = end+3;
            }
            else {
                index = index + 3;
            }
        }
        return mystore;
    }

    public double cgRatio(String dna){
        dna = dna.toUpperCase();
        double numberofC = dna.length() - dna.replace("C", "").length();
        double numberofG = dna.length() - dna.replace("G", "").length();
        double cgRatio = (numberofC + numberofG) / dna.length();
        return cgRatio;
    }

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

    public void testFinder(){
        StorageResource s1 = new StorageResource();
        String mystring = "CATGTAATAGATGAATGACTGATAGATATGCTTGTATGCTATGAAAATGTGAAATGACCCA";


        System.out.println("DNA string is :");
        System.out.println(mystring);
        System.out.println("Genes found are :");
        s1 = storeAll(mystring);
        System.out.println(s1.size());
        for( String foundedGenes : s1.data()){
            System.out.println(foundedGenes);
        }
    }

    public void printGenes(StorageResource sr){
        int longerthan60 = 0;
        int CGGreaterthan35 = 0;
        for(String gene: sr.data()){

            if(gene.length() > 60){
                System.out.println(gene);
                longerthan60 ++;
            }

            if(cgRatio(gene) > 0.35){
                System.out.println(gene);
                CGGreaterthan35 ++;
            }
        }
        System.out.println("\n There are " + sr.size() + " genes. ");
        System.out.println("There are " + longerthan60 + " genes longer than 60.");
        System.out.println("There are " + CGGreaterthan35 + " genes with CG ratio greater than 0.35.");
    }

    public void testStorageFinder(){
        FileResource myfile = new FileResource("C:\\Users\\Hamit\\Desktop\\dna\\brca1line.fa"); //Source file address
        StorageResource storageResource = new StorageResource();
        StorageResource ctgstore = new StorageResource();
        String GenesFromFile = myfile.asString(); // change the file input to string
        System.out.println("Characters read : " + GenesFromFile.length() );
        System.out.println("The DNA from file:"); //display what the input is
        System.out.println(GenesFromFile);
        storageResource = storeAll(GenesFromFile);
        printGenes(storageResource);
        ctgstore = findCTG(GenesFromFile);
        System.out.println("ctg size = " + ctgstore.size());


    }

    public static void main(String[] args) {
        FindAllGenesAndStore storedgenes = new FindAllGenesAndStore();
        storedgenes.testStorageFinder();
    }
}
