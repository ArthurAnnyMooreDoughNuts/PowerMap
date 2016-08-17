package main;

import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import entities.LevelClass;
import frame.FrameClass;
import frame.FrameCreatorClass;
import frame.FrameMenuClass;
import isel.leic.pg.Console;
import isel.leic.pg.Location;


public class Game {

    private final LevelClass level = new LevelClass();
    private final GameView view = new GameView();
    private FrameClass frame = new FrameClass();

    private int maxLines, maxCols;

    public static void main(String[] args){
        Game game = new Game();
        game.menu();
    }
    
    private void menu(){
    	FrameMenuClass menu = new FrameMenuClass();
    	int option = menu.getOption();
    	menu.dispose();
    	
    	switch(option){
    		case 1: play(); break;
    		case 2: load(); break;
    		case 3: create(); break;
    	}
    	
    }
    
    private void play(){
    	InputStream file = null;
    	
    	try{
    		file = new FileInputStream("Map.txt");
    	} catch(Exception e){
    		e.printStackTrace();
    	}
    	
    	if(loadLevel(file)){
            run();
        }else{
            Console.open("Error",3,17);
            Console.cursor(0,0);
            Console.mouseClick(true);
            Console.color(Console.WHITE,Console.RED);
            Console.print("\n NO MAP DETECTED ");
            Console.waitChar(0);
        }
        
        Console.close();
    }
    
    private void load(){
    	InputStream file = getMapFile();
    	
    	if(loadLevel(file)){
            run();
        }else{
            Console.open("Error",3,17);
            Console.cursor(0,0);
            Console.mouseClick(true);
            Console.color(Console.WHITE,Console.RED);
            Console.print("\n NO MAP DETECTED ");
            Console.waitChar(0);
        }
        
        Console.close();
    }
    
    private void create(){
    	FrameCreatorClass creator = new FrameCreatorClass();
    	
    	creator.dispose();
    	
    	menu();
    }

    public void run(){
        maxLines = level.getLines()*3;
        maxCols = level.getCols()*3;
        Console.open("Power Map", maxLines, maxCols);

        int key;
        level.updatePower();
        view.draw(level);
        Console.mouseClick(true);
        
        do{
        	if(level.checkPower()){

                Console.color(Console.BLACK,Console.WHITE);
                Console.cursor(0, level.getCols()*3/2-4);
                Console.print("YOU WON!");
                Console.waitChar(0);
                break;

            }
        	
            key = Console.waitKeyPressed(0);
            if(key == Console.MOUSE_CLICKED){
                processClick(Console.getMouseClick());
                level.updatePower();
                view.draw(level);
            }

        }while(key != KeyEvent.VK_ESCAPE);

    }

    private void processClick(Location l){
        if(l.col>=0 && l.col<maxCols && l.lin>=0 && l.lin<maxLines) {               //Checks if the mouse click is within bounds
            if(level.map.getPiece(l.lin / 3, l.col / 3) != null){
            	level.map.getPiece(l.lin / 3, l.col / 3).turn();                             //Turns the piece in those coordinates given by the mouse click
            }
        }
    }

    private boolean loadLevel(InputStream file) {
        try{
            level.load(file);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    private InputStream getMapFile(){
    	String fileName = "";
    	InputStream file = null;
    	
    	boolean b = false;
    	
    	while(!b){
	    	try {
	    		fileName = frame.getMapFileName();
	    		file = new FileInputStream(fileName);
	    		b = true;
	    		frame.dispose();
	    	} catch(FileNotFoundException e){
	    		frame.getContentPane().removeAll();
	    		frame.mapNotFound(fileName);
	    		b = false;
	    		frame.getContentPane().removeAll();
	    	} catch(SecurityException e){
	    		e.printStackTrace();
	    	}
    	}
	    	
    	return file;
    	
    }
    
}