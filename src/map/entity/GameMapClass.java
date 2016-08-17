package map.entity;

import java.util.ArrayList;

import entities.GridClass;
import entities.HouseClass;
import entities.Piece;
import entities.PowerClass;


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
	
	public void placePieces(int[] posP, int[][] posH, int nHouse){
		
		Piece[][] table = new Piece[numLines][numCols];
		
		table[posP[0]][posP[1]] = new PowerClass();
		
		for(int i = 0; i<nHouse; i++){
			table[ posH[i][0] ][ posH[i][1] ] = new HouseClass();
		}
		
		createCables(table, nHouse);
	}
	
	private void createCables(Piece[][] table, int nHouse){
		
		boolean connected;
		
		for (int i = 0; i<numLines; i++){
			
		}
		
		setGenMap(table);
		
	}
	
	private void setGenMap(Piece[][] table){
		
		for(int i = 0; i< numLines; i++){
			populateLine(table[i]);
		}
		
	}
	
	public int getNumCols(){
		return numCols;
	}
	
	public int getNumLines(){
		return numLines;
	}
	
	public Piece getPiece(int i, int j){
		return map.get(i).get(j);
	}
	
}
