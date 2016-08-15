package entities;

public class Power extends Piece {


    public Power(){
        power = true;
        simb = 'P';
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
