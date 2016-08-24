import edu.duke.FileResource;

/**
 * Created by Hamit on 7/16/2016.
 */
public class FindAllGenes {

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


    public void printAll(String dna){
        dna = dna.toUpperCase();
        int lineNumber = 0;
        int start = 0;
        while(true){
            int loc = dna.indexOf("ATG",start);
            if(loc == -1){
                break;
            }
            int end = findStopIndex(dna,loc+3);
            if(end !=dna.length()){
                lineNumber = lineNumber + 1;
                System.out.println(lineNumber + " : "+ dna.substring(loc,end+3));
                start = end+3;
            }
            else {
                start = start + 3;
            }
        }
    }

    public void testFinder(){
        String s1 = "ATGAAATGAAAA";
        String s2 = "ccatgccctaataaatgtctgtaatgtaga";
        String s3 = "CATGTAATAGATGAATGACTGATAGATATGCTTGTATGCTATGAAAATGTGAAATGACCCA";


        System.out.println("DNA string is :");
        System.out.println(s1);
        System.out.println("Genes found are :");
        printAll(s1);

        System.out.println("DNA string is :");
        System.out.println(s2);
        System.out.println("Genes found are :");
        printAll(s2);

        System.out.println("DNA string is :");
        System.out.println(s3);
        System.out.println("Genes found are :");
        printAll(s3);


    }
    public void Brca1lineGenes(){
        FileResource myfile = new FileResource("C:\\Users\\Hamit\\Desktop\\dna\\brca1line.fa"); //Source file address
        String GenesFromFile = myfile.asString(); // change the file input to string
        System.out.println("Characters read : " + GenesFromFile.length() );
        System.out.println("The DNA from file:"); //display what the input is
        System.out.println(GenesFromFile);
        System.out.println();
        System.out.println();
        System.out.println("Genes found from DNA of the file:");
        printAll(GenesFromFile); // print all genes found from the input file
    }

    public static void main(String[] args) {
        FindAllGenes mygenes = new FindAllGenes();
        mygenes.Brca1lineGenes();
    }



}

