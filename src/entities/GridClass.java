package entities;

public class GridClass extends PieceClass {

    public GridClass (char c){
        setPower(false);
        setSimb('o');
        create(c);
    }

    private void create(char c) {
        switch (c){
            case 'c':
            case 'C':
                setDir(LEFT, true);
                setDir(UP, true);
                setDir(RIGHT, false);
                break;
            case '.':
            	setDir(LEFT, true);
                setDir(UP, false);
                setDir(RIGHT, true);
                break;
            case 't':
            case 'T':
                setDir(LEFT, true);
                setDir(UP, true);
                setDir(RIGHT, true);
                break;
        }
        setDir(DOWN, false);

        setRandomPos();
    }
}
