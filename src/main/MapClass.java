package main;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import entities.Piece;


public class MapClass {

	private Map<ArrayList<Piece>, ArrayList<Piece>> map;
	
	public MapClass(){
		map = new HashMap<ArrayList<Piece>, ArrayList<Piece>>();
	}
	
}
