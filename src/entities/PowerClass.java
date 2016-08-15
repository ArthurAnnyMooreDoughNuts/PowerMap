package entities;

public class PowerClass extends PieceClass {


    public PowerClass(){
        setPower(true);
        setSimb('P');
        create();
    }

    private void create(){
        setDir(LEFT, true);
        setDir(UP, false);
        setDir(RIGHT, false);
        setDir(DOWN, false);

        setRandomPos();
    }
}
