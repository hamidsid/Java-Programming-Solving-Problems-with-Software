import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

/**
 * Created by Hamit on 7/21/2016.
 */
public class BabyBirths {
    public void printNames() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser(false);
        for (CSVRecord record : parser) {
            int numBorn = Integer.parseInt(record.get(2));
            if (numBorn <= 100) {
                System.out.println("Name: " + record.get(0) + " Gender: " + record.get(1) +
                        " Num Born: " + record.get(2));
            }
        }
    }

    public void totalBirths (FileResource fileResource){
        int totalBirths = 0;
        int totalBoysBirths = 0;
        int totalGirlsBirths = 0;
        int sumTotalNames = 0;
        int boyNames = 0;
        int girlNames = 0;
        for (CSVRecord record : fileResource.getCSVParser(false)){
            int numBirths = Integer.parseInt(record.get(2));
            totalBirths += numBirths;
            String gender = record.get(1);
            if (gender.equals("F")){
                totalGirlsBirths +=numBirths;
            }
            else {
                totalBoysBirths +=numBirths;
            }
            String names = record.get(0);
            if(gender.equals("F")){
                girlNames++;
            }
            else {
                boyNames++;
            }
                sumTotalNames++;
        }
        System.out.println("total births = " + totalBirths);
        System.out.println("total boy births = " + totalBoysBirths);
        System.out.println("total girl births = " + totalGirlsBirths);
        System.out.println("total names = " + sumTotalNames);
        System.out.println("total boy names = " + boyNames);
        System.out.println("total girl names = " + girlNames);
    }


    public static void main(String[] args) {
        BabyBirths births = new BabyBirths();
        FileResource fileResource = new FileResource("testing/yob2014short.csv");
        births.totalBirths(fileResource);
    }
}
