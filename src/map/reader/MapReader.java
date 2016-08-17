package map.reader;

import java.io.InputStream;
import java.util.Scanner;

import entities.*;
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
	
	private int numCols;
	private int numLines;
	private GameMap map;
	
	
	public MapReader(InputStream file){
		createMap(file);
	}
	
	public MapReader(){
		genMap();
	}
	
	private void createMap(InputStream file){
 		Scanner input = new Scanner(file);
        
		try{
			numLines = input.nextInt();
			input.next();	
			numCols = input.nextInt(); input.nextLine(); 
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
			default: ret = null; break;
		}
		
		return ret;
	}
	
	private void genMap(){
		numLines = (int)(Math.random()*10);
		if(numLines<4)
			numLines = 10 - numLines;
		
		numCols = (int)(Math.random()*10);
		if(numCols<4)
			numCols = 10 - numCols;
		
		map = new GameMapClass(numCols, numLines);
		
		int nHouse = numLines*numCols / 5 ;
		int posH[][] = new int[nHouse][2];
		int posP[] = new int[2];
		
		int x = 0, y = 0;
		
		for(int i = 0; i<nHouse; i++){
			do{
				x = (int)(Math.random()*10%numLines);
				y = (int)(Math.random()*10%numCols);
			}while(!empty(posH,i,x,y));
			
			posH[i][0] = x;
			posH[i][1] = y;
		}
		
		do{
			x = (int)(Math.random()*10%numLines);
			y = (int)(Math.random()*10%numCols);
		}while(!empty(posH,nHouse,x,y));
		posP[0] = x;
		posP[1] = y;
		
		map.genCables(posP, posH, nHouse);
	}
	
	private static boolean empty(int[][] posH, int i, int x, int y){
		
		for(int k = 0; k<i; k++){
			if(posH[k][0]==x && posH[k][1]==y){
				return false;
			}
		}
		
		return true;
	}

	public GameMap getMap(){
		return map;
	}
	
}
