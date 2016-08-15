package entities;

public class HouseClass extends PieceClass {

    public HouseClass(){
        setPower(false);
        setSimb('H');
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
