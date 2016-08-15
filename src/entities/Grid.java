package entities;

public class Grid extends Piece {

    public Grid (char c){
        power = false;
        simb = 'o';
        create(c);
    }

    private void create(char c) {
        switch (c){
            case 'c':
                dir[LEFT]=true;
                dir[UP]=true;
                dir[RIGHT]=false;
                break;
            case '.':
                dir[LEFT]=true;
                dir[UP]=false;
                dir[RIGHT]=true;
                break;
            case 'T':
                dir[LEFT]=true;
                dir[UP]=true;
                dir[RIGHT]=true;
                break;
        }
        dir[DOWN]=false;

        setRandomPos();
    }
}
