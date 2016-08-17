package entities;

import java.io.InputStream;

import map.entity.GameMap;
import map.reader.MapReader;


public class LevelClass {

    public static final int LEFT = 0, UP = 1, RIGHT = 2, DOWN = 3;                  //Estas variaveis são posições no array dir[] que cada peça possui, sendo que a 1º posição corresponde
                                                                                    //á esquerda (em relação ao centro da peça) e a ultima a baixo do centro
    private int maxLines = 0, maxCols = 0;
    public GameMap map;

    public void load(InputStream file) {
    	MapReader read = new MapReader(file);
    	map = read.getMap();
    	maxLines = map.getNumLines();
    	maxCols = map.getNumCols();
    }

    private void updatePowerLine(int i, int j){                                                                //Este metodo faz update da energia nas peças atraves de ramos, começando na fonte

        map.getPiece(i, j).setPower(true);

        if(map.getPiece(i, j).getDir(LEFT) && j > 0){                                                                  //Se a peça tiver uma perna virada para a esquerda, e á sua esquerda existir uma peça que ainda não tem
            if(map.getPiece(i, j - 1) != null && !map.getPiece(i, j - 1).getPower() && map.getPiece(i, j - 1).getDir(RIGHT))             //energia, então vai fazer update desse ramo de energia, quando chegar ao fim do ramo volta a este ponto
                updatePowerLine(i,j - 1);                                                                        //e repete o processo para todos os ramos
        }
        if(map.getPiece(i, j).getDir(UP) && i > 0){
            if(map.getPiece(i - 1, j) != null && !map.getPiece(i - 1, j).getPower() && map.getPiece(i - 1, j).getDir(DOWN))
                updatePowerLine(i-1,j);
        }
        if(map.getPiece(i, j).getDir(RIGHT) && j < maxCols-1){
            if(map.getPiece(i, j + 1) != null && !map.getPiece(i, j + 1).getPower() && map.getPiece(i, j + 1).getDir(LEFT))
                updatePowerLine(i,j+1);
        }
        if(map.getPiece(i, j).getDir(DOWN) && i < (maxLines - 1)){
            if(map.getPiece(i + 1, j) != null && !map.getPiece(i + 1, j).getPower() && map.getPiece(i + 1, j).getDir(UP))
                updatePowerLine(i+1,j);
        }
    }

    public void updatePower(){

        int x = 0, y = 0;

        for(int i = 0; i < maxLines; i++){
            for (int j = 0; j < maxCols; j++) {
                if (map.getPiece(i, j) != null){
                    if(map.getPiece(i, j).getSimb() != 'P') {                                                        //Passa a energia de todas as peças para false, menos da fonte
                    	map.getPiece(i, j).setPower(false);
                    }else{
                        x=i;                                                                                   //Guarda as coordenadas da fonte
                        y=j;
                    }
                }
            }
        }

        updatePowerLine(x,y);                                                                                  //Faz update á linha de energia a parir da fonte

    }

    public boolean checkPower(){                                                                               //Verifica se todas as casas têm energia, se sim retorna True
        for(int i = 0; i < maxLines; i++){
            for (int j = 0; j < maxCols; j++){
                if (map.getPiece(i, j) != null  && map.getPiece(i, j).getSimb() == 'H' && !map.getPiece(i, j).getPower()){
                    return false;
                }
            }
        }
        return true;
    }

    public int getLines(){
        return maxLines;
    }
    public int getCols(){
        return maxCols;
    }
}
