package map.reader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

import entities.*;
import main.*;
import map.entity.GameMap;
import map.entity.GameMapClass;

public class MapReader {
	
	private static final char HOUSE = 'H';
	private static final char HOUSE_ = 'h';
	private static final char POWER = 'P';
	private static final char POWER_ = 'p';
	private static final char ELBOW = 'C';
	private static final char ELBOW_ = 'c';
	private static final char T_TYPE = 'T';
	private static final char T_TYPE_ = 't';
	private static final char LINE = '.';
	
	private InputStream file;
	private int numCols;
	private int numLines;
	private GameMap map;
	
	
	public MapReader(String fileName) throws FileNotFoundException, SecurityException {
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
		
		map = new GameMapClass(numCols, numLines);
		Piece[] pieces = new Piece[numCols];
		
		for(int i = 0; i < numLines; i++){
			String line = input.nextLine();
			
			for(int j = 0; j < numCols; j++){
				pieces[j] = resolvePiece(line.charAt(j));
			}
			
			map.populateLine(pieces);
			
		}
		
		input.close();
	}
	
	private Piece resolvePiece(char c){
		Piece ret = new PieceClass();
		
		switch(c){
			case HOUSE:
			case HOUSE_: ret = new HouseClass(); break;
			case POWER:
			case POWER_: ret = new PowerClass();  break;
			case ELBOW:
			case ELBOW_: ret = new GridClass(c); break;
			case T_TYPE:
			case T_TYPE_: ret = new GridClass(c); break;
			case LINE: ret = new GridClass(c); break;
		}
		
		return ret;
	}
	

	public GameMap getMap(){
		return map;
	}
	
}
