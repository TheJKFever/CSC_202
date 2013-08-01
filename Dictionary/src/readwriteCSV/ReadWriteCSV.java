package readwriteCSV;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

public class ReadWriteCSV {
	
	private CSVReader reader;
	private CSVWriter writer;
	
	public void read(String input){
		String [] nextLine = null;
		try {
			reader = new CSVReader(new FileReader(input));
			while ((nextLine = reader.readNext()) != null) {
				// nextLine[] is an array of values from the line
				System.out.println(nextLine[0] + nextLine[1] + "etc...");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void write(String[] output, String adress){
		try {
			writer = new CSVWriter(new FileWriter("yourfile.csv"), '\t');
			String[] entries = "first#second#third".split("#");
		     writer.writeNext(entries);
				writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}