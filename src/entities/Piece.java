package entities;

public interface Piece {

	public static final int LEFT = LevelClass.LEFT;
    public static final int UP = LevelClass.UP;
    public static final int RIGHT = LevelClass.RIGHT;
    public static final int DOWN = LevelClass.DOWN;
    
    public char getSimb();
    public void setSimb(char simb);
    public boolean getPower();
    public void setPower(boolean b);
    public boolean getDir(int x);
    public void setDir(int x, boolean a);
    
    public void turn();
    public void setRandomPos();
	
}
