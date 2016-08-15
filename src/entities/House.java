package entities;

public class House extends Piece {

    public House(){
        power = false;
        simb = 'H';
        create();
    }

    private void create(){
        dir[LEFT]=true;
        dir[UP]=false;
        dir[RIGHT]=false;
        dir[DOWN]=false;

        setRandomPos();
    }
}
