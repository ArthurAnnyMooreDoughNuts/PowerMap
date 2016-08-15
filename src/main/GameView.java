package main;

import isel.leic.pg.Console;
import entities.LevelClass;

public class GameView {

    private static final int LEFT = Level.LEFT;
    private static final int UP = Level.UP;
    private static final int RIGHT = Level.RIGHT;
    private static final int DOWN = Level.DOWN;

    public void draw(Level level){
        int lines = level.getLines();
        int cols = level.getCols();

        int x, y;

        for(int i = 0; i<lines; i++){

            for(int j = 0; j<cols; j++){
                if (level.pieces[i][j] != null) {

                    setColor(level.pieces[i][j].getSimb(), level.pieces[i][j].getPower());
                    x = i * 3;
                    y = j * 3;
                    Console.cursor(x + 1, y + 1);
                    Console.print(level.pieces[i][j].getSimb());

                    Console.cursor(x + 1, y);
                    if (level.pieces[i][j].getDir(LEFT)) {
                        setWireColor(level.pieces[i][j].getPower());
                        Console.print('-');
                    }else {
                        Console.setBackground(Console.BLACK);
                        Console.print(' ');
                    }

                    Console.cursor(x, y + 1);
                    if (level.pieces[i][j].getDir(UP)) {
                        setWireColor(level.pieces[i][j].getPower());
                        Console.print('|');
                    }else {
                        Console.setBackground(Console.BLACK);
                        Console.print(' ');
                    }

                    Console.cursor(x + 1, y + 2);
                    if (level.pieces[i][j].getDir(RIGHT)) {
                        setWireColor(level.pieces[i][j].getPower());
                        Console.print('-');
                    }else {
                        Console.setBackground(Console.BLACK);
                        Console.print(' ');
                    }

                    Console.cursor(x + 2, y + 1);
                    if (level.pieces[i][j].getDir(DOWN)) {
                        setWireColor(level.pieces[i][j].getPower());
                        Console.print('|');
                    }else {
                        Console.setBackground(Console.BLACK);
                        Console.print(' ');
                    }
                }

            }

        }
    }

    private void setColor(char piece, boolean b){                                  //Muda a cor do centro da peça dependendo de se tem eneriga, e do tipo de peça que é
        if (piece == 'o'){
            if(!b) {
                Console.color(Console.WHITE, Console.BLACK);
            }else{
                Console.color(Console.BLACK, Console.YELLOW);
            }
        }else if(piece == 'H'){
            if(!b) {
                Console.color(Console.WHITE, Console.RED);
            }else{
                Console.color(Console.RED, Console.YELLOW);
            }
        }else if(piece == 'P'){
            Console.color(Console.BLACK, Console.YELLOW);
        }
    }

    private void setWireColor(boolean b){                                           //Muda a cor do fio dependendo de se tem energia ou não
        if (b){
            Console.color(Console.BLACK, Console.YELLOW);
        }else{
            Console.color(Console.WHITE, Console.BLACK);
        }
    }
}
