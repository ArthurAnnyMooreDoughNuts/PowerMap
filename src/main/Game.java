package main;

import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.InputStream;
import entities.LevelClass;
import isel.leic.pg.Console;
import isel.leic.pg.Location;


public class Game {

    private final LevelClass level = new LevelClass();
    private final GameView view = new GameView();

    private int maxLines, maxCols;

    public static void main(String[] args){
        Game game = new Game();
        
        //String fileName = game.getMapName();
        String fileName = "Map.txt";
        
        
        if(game.loadLevel(fileName)){
            game.run();
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

    public void run(){
        maxLines = level.getLines()*3;
        maxCols = level.getCols()*3;
        Console.open("Power Map", maxLines, maxCols);

        int key;
        level.updatePower();
        view.draw(level);
        Console.mouseClick(true);

        do{
            key = Console.waitKeyPressed(0);
            if(key == Console.MOUSE_CLICKED){
                processClick(Console.getMouseClick());
                level.updatePower();
                view.draw(level);
            }



            if(level.checkPower()){

                Console.color(Console.BLACK,Console.WHITE);
                Console.cursor(0, level.getCols()*3/2-4);
                Console.print("YOU WON!");
                Console.waitChar(0);
                break;

            }

        }while(key != KeyEvent.VK_ESCAPE);

    }

    private void processClick(Location l){
        if(l.col>=0 && l.col<maxCols && l.lin>=0 && l.lin<maxLines) {               //Checks if the mouse click is within bounds
            level.pieces[l.lin / 3][l.col / 3].turn();                              //Turns the piece in those coordinates given by the mouse click
        }
    }

    private boolean loadLevel(String fileName) {
        try (InputStream file = new FileInputStream(fileName)) {
            level.load(file);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    private String getMapName(){
    	//TODO
    	return null;
    }
}