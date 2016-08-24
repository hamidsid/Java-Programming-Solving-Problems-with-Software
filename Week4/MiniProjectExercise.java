import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;

/**
 * Created by Hamit on 7/21/2016.
 */
public class MiniProjectExercise {


    public int getRank(int year, String name, String gender) {

        //get the file and parse it
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser(false);

        int pivotM = 0;
        int pivotF = 0;

        for (CSVRecord record : parser) {
            String nameOut = record.get(0);
            String genderOut = record.get(1);

            //for male records
            if (genderOut.equals("M")) {

                pivotM++;
                if (nameOut.equals(name)) return pivotM;
            }

            //for femal records
            if (genderOut.equals("F")) {

                pivotF++;
                if (nameOut.equals(name)) return pivotF;
            }
        }
        return -1;
    }


    public String getName(int year, int rank, String gender){

        //get the file and parse it
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser(false);

        int pivot= 0;

        for (CSVRecord record : parser){

            //for both male and female record
                if (record.get(1).equals(gender)) {
                    pivot++;

                    //when rank equals pivot get the name from the record
                    if (rank == pivot) return record.get(0);
                }
        }
        return "NO NAME";
    }

    public void whatIsNameInYear(String name, int year, int newYear, String gender){

        int rank = getRank(year,name,gender);
        String newName = getName(newYear,rank,gender);

        System.out.println( name + " born in " + year + " would be " + newName + " if she was born in " + newYear);
    }

    public int yearOfHighestRank(String name, String gender){
        int rank = 100000000;
        int yearHigh = 0;
        //get directory
        DirectoryResource dr = new DirectoryResource();

        //get file
        for (File file : dr.selectedFiles()){

            String fileName = file.getName();

            //get the year from the name of file
            int year = Integer.parseInt(fileName.replaceAll("\\D+",""));

            //rank = getRank(year,name,gender);  we cant use the method getRank;as its only one file usage.

            //but we can use the algorithm we use for the get rank


            // get the file (one from selected)
            FileResource fr = new FileResource(file);
            int currentRank = -1;
            int pivot = 0;

            for(CSVRecord record : fr.getCSVParser(false)){
                if (record.get(1).equals(gender)){
                    pivot++;
                    if (record.get(0).equals(name)){
                        currentRank = pivot;
                        break;
                    }
                }
            } // end for loop
            if (currentRank!= -1 && currentRank < rank){
                rank = currentRank;
                yearHigh = year;
            }
        }
        return yearHigh;
    }


    public double getAverageRank(String name, String gender){
        double totalRank = 0;
        double fileNum = 0;

        //get directory
        DirectoryResource dr = new DirectoryResource();

        //get file
        for (File file : dr.selectedFiles()){
            fileNum++;

            String yearString = file.getName();
            int year = Integer.parseInt(yearString.replaceAll("\\D+",""));

            // get the file (one from selected)
            FileResource fr = new FileResource(file);
            int currentRank = 0;
            int pivot = 0;

            for(CSVRecord record : fr.getCSVParser(false)){
                if (record.get(1).equals(gender)){
                    pivot++;
                    if (record.get(0).equals(name)){
                        currentRank = pivot;
                        break;
                    }
                }
            } // end for record loop
            totalRank += currentRank;
        } // end for file loop

        if (totalRank == 0) return -1.0;

        //return average
        return totalRank / fileNum;
    }


    public int getTotalBirthsRankedHigher(int year,String name, String gender){
        int sum = 0;

        FileResource fileResource = new FileResource();
        for (CSVRecord record : fileResource.getCSVParser(false)){
            if (record.get(1).equals(gender)){
               if (record.get(0).equals(name)){
                   return  sum;
               }
                   int totalBirth = Integer.parseInt(record.get(2));
                   sum += totalBirth;
               }
            }
        return sum;
    }


    public static void main(String[] args) {

        MiniProjectExercise test = new MiniProjectExercise();
        int year = 2012;
        //int newYear = 2014;
        String gender = "M";
        String name = "Ethan";
        //test.whatIsNameInYear(name,2012,2014,gender);
        //int highest = test.yearOfHighestRank(name,gender);
        //System.out.println("year of highest rank is " + highest);
        //double average = test.getAverageRank(name,gender);
        //System.out.println("average rank is " + average);
        int totalBirth = test.getTotalBirthsRankedHigher(year,name,gender);
        System.out.println("total birth rank higher is " + totalBirth);
    }
}
