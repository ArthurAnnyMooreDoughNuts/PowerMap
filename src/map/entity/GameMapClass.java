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
	
	public void genCables(int[] posP, int[][] posH, int nHouse){
		
		boolean connected;
		int nCable = 0;
		char[][] cables = new char[numLines*numCols - nHouse - 1][3];
		int x = posP[0], y = posP[1];
		
		for(int i = 0; i<nHouse; i++){
			connected = false;
			
			while(!connected){
				
				if( posH[i][0]-1 != x || posH[i][0]+1 != x ){
					
					while( posH[i][0]+1<x ){
						x--;
					}
					while( posH[i][0]-1>x){
						x++;
					}
					cables[nCable][0] = (char)x;
					cables[nCable][1] = (char)y;
					if(posH[i][0]-1 != x || posH[i][0]+1 != x){
						cables[nCable][2] = '.';
					}else if(posH[i][1]-1 != y || posH[i][1]+1 != y){
						cables[nCable][2] = 'c';
					}
					nCable++;
					
				}else if(posH[i][1]-1 != y || posH[i][1]+1 != y){
					while( posH[i][1]+1<y ){
						y--;
					}
					while( posH[i][1]-1>y){
						y++;
					}
					cables[nCable][0] = (char)x;
					cables[nCable][1] = (char)y;
					cables[nCable][2] = '.';
					nCable++;
				}else{
					connected = true;
					x = setXY(cables, nCable);
					y = setXY(cables, nCable);
				}
			}
		}
	}
	
	private static int setXY(char[][] cables, int nCable){
		return 0;
	}
	
	private void setGenMap(int[] posP, int[][] posH, int nHouse, char[][] cables, int nCable){
		
		map.get(posP[0]).add(posP[1], new PowerClass());
		
		for(int i = 0; i<nHouse; i++){
			map.get(posH[i][0]).add(posH[i][1], new HouseClass());
		}
		
		for(int i = 0; i<nCable; i++){
			map.get( cables[i][0] ).add( cables[i][1], new GridClass( cables[i][2] ) );
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
