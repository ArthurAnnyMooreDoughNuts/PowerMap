package map.reader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

import entities.Piece;
import main.*;

public class MapReader {
	
	private static final int MAX_COLUMNS = 10;
	private static final int MAX_LINES = 10;
	
	private static final char HOUSE = 'H';
	private static final char HOUSE_ = 'h';
	private static final char POWER = 'P';
	private static final char POWER_ = 'p';
	private static final char ELBOW = 'C';
	private static final char ELBOW_ = 'c';
	private static final char
	
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
		
		GameMap map = new GameMapClass(numCols, numLines);
		
		for(int i = 0; i < numLines; i++){
			String line = input.nextLine();
			Piece[] pieces = new Piece[numCols];
			
			for(int j = 0; j < numCols; j++){
				pieces[j] = resolvePiece(line.charAt(j));
			}
		}
		
		input.close();
	}
	
	private Piece resolvePiece(char c){
		switch(c){
			case 
		}
	}
	
}
