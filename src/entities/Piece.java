package entities;

public class Piece {

    protected static final int LEFT = Level.LEFT;
    protected static final int UP = Level.UP;
    protected static final int RIGHT = Level.RIGHT;
    protected static final int DOWN = Level.DOWN;

    protected char simb;                             //Guarda o simbulo da peça para ser usado ao desenhar o jogo, e tbm para verificar que tipo de peça é
    protected boolean power;
    public boolean dir[] = new boolean[4];           //Array boolean que guarda em que posições(esquerda, cima, direita, baixo) existem ligações

    public void turn(){                              //Vira a peça 90º para a direita
        boolean state;

        state = dir[DOWN];

        for(int i = 3; i>0; i--){
            dir[i] = dir[i-1];
        }

        dir[LEFT] = state;
    }

    protected void setRandomPos(){                 //Vira a peça um numeo aleatório de vezes quando é criada
        int r = (int)(Math.random()*10) %4;
        for(int i = 0; i<r; i++) {
            turn();
        }
    }

    public char getSimb(){
        return simb;
    }

    public boolean getDir(int x) {
        return dir[x];
    }

    public boolean getPower(){
        return power;
    }
    public void setPower(boolean b){
        power = b;
    }
}
