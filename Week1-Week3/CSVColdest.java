import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;

/**
 * Created by Hamit on 7/18/2016.
 */
public class CSVColdest {

    //exercice part 1
    public CSVRecord coldestHourInFile(CSVParser parser){
        CSVRecord smallestSoFar = null;
        for(CSVRecord currentRaw : parser){
            smallestSoFar = smallestOfTheTwo(currentRaw,smallestSoFar);
        }
        return smallestSoFar;
    }

    public CSVRecord smallestOfTheTwo (CSVRecord currentRaw, CSVRecord smallestSoFar){
        if(smallestSoFar==null){
            smallestSoFar = currentRaw;
        }
        else {
            double currentTemp = Double.parseDouble(currentRaw.get("TemperatureF"));
            double smallestTemp = Double.parseDouble(smallestSoFar.get("TemperatureF"));
            if(currentTemp != -9999) {
                if (currentTemp < smallestTemp) {
                    smallestSoFar = currentRaw;
                }
            }
        }
        return smallestSoFar;
    }

    public void testColdestHourInFile(){

        FileResource fr = new FileResource();
        CSVRecord cooldest = coldestHourInFile(fr.getCSVParser());
        System.out.println("coldest temperature was " + cooldest.get("TemperatureF") +
                " at " + cooldest.get("TimeEDT"));
    }

    //excersice part 2

    public void fileWithColdestTemperature() {
        CSVRecord smallestSoFar = null;
        String coldestFileName;
        File coldestFile = null;
        DirectoryResource dr = new DirectoryResource();

        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord currentRaw = coldestHourInFile(parser);
            smallestSoFar = smallestOfTheTwo(currentRaw, smallestSoFar);
            coldestFile = f;
        }

        coldestFileName = "The coldes hour is in file :" + coldestFile.getName()
                + " \n Coldest Temperature on that day was : " + smallestSoFar.get("TemperatureF");
        System.out.println(coldestFileName);
        FileResource fr = new FileResource(coldestFile);
        CSVParser parser = fr.getCSVParser();
        System.out.println("All the temperatures on that day were");
        for (CSVRecord record : parser){
            System.out.print(record.get("DateUTC"));
            System.out.print(" ");
            System.out.println(record.get("TemperatureF"));

        }
    }

    //part 3

    public CSVRecord smallestHumidtyofTwo(CSVRecord currentRow, CSVRecord lowestSoFar){
        if(lowestSoFar==null){
            lowestSoFar = currentRow;
        }
        else {
            if (!(currentRow.get("Humidity")).equals("N/A")) {
                int currentHumidity = Integer.parseInt(currentRow.get("Humidity"));
                int lowestHumidity = Integer.parseInt(lowestSoFar.get("Humidity"));
                if (currentHumidity < lowestHumidity) {
                    lowestSoFar = currentRow;
                }
            }
        }
        return lowestSoFar;
    }

    public CSVRecord lowestHumidityInFile(CSVParser parser){
        CSVRecord lowestHumidity = null;
        for (CSVRecord currentRow : parser){
            lowestHumidity = smallestHumidtyofTwo(currentRow,lowestHumidity);
        }
        return lowestHumidity;
    }
    public void testLowestHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        System.out.println("Lowest humidity was "+ csv.get("Humidity") + " at "+ csv.get("DateUTC"));
    }
    //part 4

    public CSVRecord lowestHumidityInManyFiles(){
        CSVRecord lowestHumiditySoFar = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f: dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord currentRaw = lowestHumidityInFile(parser);
            lowestHumiditySoFar = smallestHumidtyofTwo(currentRaw,lowestHumiditySoFar);
        }
        return lowestHumiditySoFar;
    }
    public void testLowestHumidityInManyFiles(){
        CSVRecord csv = lowestHumidityInManyFiles();
        System.out.println("Lowest humidity was "+ csv.get("Humidity") + " at "+ csv.get("DateUTC"));
    }

    //part5

    public double averageTemperatureInFilethat(CSVParser parser){
        double averageTemp = 0;
        double numberofRows = 0;
        double currentTemp = 0;
        FileResource fr = new FileResource();
        parser = fr.getCSVParser();
        for (CSVRecord record : parser){
            if(!(record.get("TemperatureF").equals("-9999"))) {
                currentTemp = currentTemp+ Double.parseDouble(record.get("TemperatureF"));
                numberofRows++;
            }
        }
        return averageTemp = currentTemp /numberofRows;
    }
    public void testAverageTemperatureInFile(){
        FileResource file = new FileResource();
        CSVParser parser = file.getCSVParser();
        double average = averageTemperatureInFilethat(parser);
        System.out.println("Average Temperature in file is " + average);
    }

    //part6
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value){
        double numberOfRows = 0;
        double currentTemp = 0;

        for (CSVRecord record : parser){
            int humidity = Integer.parseInt(record.get("Humidity"));
            if (humidity >= value && (!(record.get("Humidity")).equals("N/A"))){
                currentTemp += Double.parseDouble(record.get("TemperatureF"));
                numberOfRows++;
            }
        }
        if(numberOfRows == 0){
            return 0.0;
        }
        return currentTemp / numberOfRows;
    }

    public void testAverageTemperatureWithHighHumidityInFile(){
        FileResource f = new FileResource();
        CSVParser parser = f.getCSVParser();
        double average = averageTemperatureWithHighHumidityInFile(parser,80);
        if(average == 0) {
            System.out.println("No temperature with that humidity");
        }
        else {
            System.out.println("Average temperature when high humidity is "+ average);
        }
    }

// main method
    public static void main(String[] args) {
        CSVColdest csvColdest = new CSVColdest();
       // csvColdest.testColdestHourInFile();
       // csvColdest.fileWithColdestTemperature();


        csvColdest.testAverageTemperatureWithHighHumidityInFile();
    }
}

