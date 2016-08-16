package map.entity;

import entities.Piece;

public interface GameMap {

	public void populateLine(Piece[] pieces);
	public void genCables(int[] posP, int[][] posH, int nHouse);
	public int getNumCols();
	public int getNumLines();
	public Piece getPiece(int i, int j);
	
}
