/**
 * Find the highest (hottest) temperature in a file of CSV weather data.
 *
 * @author Duke Software Team
 */

import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;

public class CSVMax {
    public CSVRecord hottestHourInFile(CSVParser parser) {

        //start with largestSoFar as nothing
        CSVRecord largestSoFar = null;
        //For each row (currentRow) in the CSV File
        for (CSVRecord currentRow: parser) {
            //If largestSoFar is nothing
            largestSoFar = largestOfTheTwo(currentRow,largestSoFar);
        }
        //The largestSoFar is the answer
        return largestSoFar;
    }

    public void testHottestInDay () {
        FileResource fr = new FileResource("C:\\Users\\Hamit\\Desktop\\New Folder (4)\\data\\2015\\weather-2015-01-01.csv");
        CSVRecord largest = hottestHourInFile(fr.getCSVParser());
        System.out.println("hottest temperature was " + largest.get("TemperatureF") +
                " at " + largest.get("TimeEST"));
    }


    public CSVRecord hottestInManyDays(){
        CSVRecord largestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        for(File f: dr.selectedFiles() ){
            FileResource fr = new FileResource(f);
            CSVRecord current = hottestHourInFile(fr.getCSVParser());
            largestSoFar = largestOfTheTwo(current,largestSoFar);
        }
        return largestSoFar;
    }

    public CSVRecord largestOfTheTwo(CSVRecord currentRow, CSVRecord largestSoFar){

        if (largestSoFar == null) {
            largestSoFar = currentRow;
        }
        //Otherwise
        else {
            //Check if currentRow’s temperature > largestSoFar’s
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            double soFarTemp = Double.parseDouble(largestSoFar.get("TemperatureF"));
            if (currentTemp > soFarTemp) {
                //If so update largestSoFar to currentRow
                largestSoFar = currentRow;
            }
        }
        return largestSoFar;
    }
    public void testHottestInManyDays(){
        CSVRecord largest = hottestInManyDays();
        System.out.println("hottest temperature was "+largest.get("TemperatureF")
        + " at " + largest.get("DateUTC"));

    }


    public static void main(String[] args) {
        CSVMax csvMax = new CSVMax();
        csvMax.testHottestInManyDays();
    }
}
