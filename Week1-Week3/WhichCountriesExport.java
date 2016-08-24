/**
 * Reads a chosen CSV file of country exports and prints each country that exports coffee.
 *
 * @author Duke Software Team
 */
import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import static java.lang.String.format;

public class WhichCountriesExport {
    public void listExporters(CSVParser parser, String exportOfInterest) {
        //for each row in the CSV File
        for(CSVRecord record : parser) {
            String export = record.get("Exports");
            if (export.contains(exportOfInterest)) {
                String country = record.get("Country");
                System.out.println(country);
            }
        }
    }


    public String countryInfo(CSVParser parser, String country){
        for (CSVRecord record : parser) {
            String exports = record.get("Exports");
            String value = record.get("Value (dollars)");
            if(record.get("Country").equals(country)){
                if((exports != null) || (value!= null)){
                    return country + " : " + exports + " : " + value;

                }
            }
        }
        return  country;
    }


    public void  listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2){
        for (CSVRecord record : parser){ // for every record in parser
            String export = record.get("Exports"); // for every export record
            if ((export.contains(exportItem1) && (export.contains(exportItem2)))){ //item 1 and item 2 included
                String country = record.get("Country"); // the country that has bith items
                System.out.println(country);
            }
        }
    }

    public int numberOfExporters( CSVParser parser, String exportItem){
        int exporters = 0;
        for (CSVRecord record : parser){

            String exports = record.get("Exports");
            if (exports.contains(exportItem)) {
                String country = record.get("Country");
                exporters++;
            }
        }
        return exporters;
    }
    public void bigExporters (CSVParser parser, String amount){
        long n  =0;
        amount = format(amount,format("$"+"%,8d%n", n) );


    }


    public void tester(){

        FileResource fr =new FileResource("C:\\Users\\Hamit\\Desktop\\New Folder (3)\\exportdata.csv");
        CSVParser parser=fr.getCSVParser();
        String result = countryInfo(parser,"Nauru");
        System.out.println(result);
    }

    public static void main(String[] args) {
        WhichCountriesExport whichCountriesExport = new WhichCountriesExport();
        whichCountriesExport.tester();
    }
}