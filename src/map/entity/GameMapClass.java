package map.entity;

import java.util.ArrayList;
import entities.Piece;


public class GameMapClass implements GameMap{

	private ArrayList<ArrayList<Piece>> map;
	private int numCols;
	private int numLines;
	
	
	public GameMapClass(int numCols, int numLines){
		map = new ArrayList<ArrayList<Piece>>();
		this.numCols = numCols;
		this.numLines = numLines;
	}
	
	public void populateLine(Piece[] line){
		ArrayList<Piece> pieces = new ArrayList<Piece>();
		
		for(int i = 0; i < numCols; i++){
			pieces.add(line[i]);
		}
		
		map.add(pieces);
	}
	
	public int getNumCols(){
		return numCols;
	}
	
	public int getNumLines(){
		return numLines;
	}
	
}
