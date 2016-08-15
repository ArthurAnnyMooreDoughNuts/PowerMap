package map.reader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class MapReader {
	
	private static final int MAX_COLUMNS = 10;
	private static final int MAX_LINES = 10;
	
	
	private String fileName;
	private boolean validMap;
	private InputStream file;
	private int numCols;
	private int numLines;
	
	
	public MapReader(String fileName) throws FileNotFoundException, SecurityException {
		this.fileName = fileName;
		file = new FileInputStream(fileName);
		createMap(file);
	}
	
	private void createMap(InputStream file){
		Scanner input = new Scanner(file);
        
		try{
			numLines = input.nextInt();
			input.next();	
			numCols = input.nextInt(); input.next();
		} catch(Exception e){
			e.printStackTrace();
		}
		
		
		
	}
	
}
