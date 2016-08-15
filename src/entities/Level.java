package entities;

import java.io.InputStream;
import java.util.Scanner;


public class Level {

    public static final int LEFT = 0, UP = 1, RIGHT = 2, DOWN = 3;                  //Estas variaveis são posições no array dir[] que cada peça possui, sendo que a 1º posição corresponde
                                                                                    //á esquerda (em relação ao centro da peça) e a ultima a baixo do centro
    private int maxLines = 0, maxCols = 0;
    public Piece[][] pieces;

    public void load(InputStream file) {
        Scanner input = new Scanner(file);

        String line = input.nextLine();           //Primeira linha do ficheiro que indica o numero de linhas e colunas
        int y = line.length();
        boolean x = false;                        //Saber se estamos a obter o numero antes ou depois do 'x'(linhas ou colunas)
        char c;                                   //Variavel para ler os caracters
        for (int i = 0; i<y; i++){                //Loop que lê a 1ª linha
            c = line.charAt(i);
            if(c>='0' && c<='9'){
                if(x){
                    maxCols *=10;
                    maxCols += c - '0';
                }else{
                    maxLines *=10;
                    maxLines += c - '0';
                }
            }else if (c == 'x'){
                x = true;
            }
        }

        pieces = new Piece[maxCols][maxLines];

        for (int i = 0; i<maxLines; i++){        //Loop para ler as linhas do jogo
            line = input.nextLine();
            for(int j = 0; j<maxCols; j++){      //Loop para ler elementos de cada linha
                c = line.charAt(j);
                switch (c){
                    case 'H':
                        pieces[i][j] = new House();
                        break;
                    case 'P':
                        pieces[i][j] = new Power();
                        break;
                    case 'c':
                        pieces[i][j] = new Grid(c);
                        break;
                    case 'T':
                        pieces[i][j] = new Grid(c);
                        break;
                    case '.':
                        pieces[i][j] = new Grid(c);
                        break;
                    default:
                        pieces[i][j] = null;
                        break;
                }
            }
        }
    }

    private void updatePowerLine(int i, int j){                                                                //Este metodo faz update da energia nas peças atraves de ramos, começando na fonte

        pieces[i][j].setPower(true);

        if(pieces[i][j].getDir(LEFT) && j>0){                                                                  //Se a peça tiver uma perna virada para a esquerda, e á sua esquerda existir uma peça que ainda não tem
            if(pieces[i][j-1]!=null && !pieces[i][j-1].getPower() && pieces[i][j-1].getDir(RIGHT))             //energia, então vai fazer update desse ramo de energia, quando chegar ao fim do ramo volta a este ponto
                updatePowerLine(i,j-1);                                                                        //e repete o processo para todos os ramos
        }
        if(pieces[i][j].getDir(UP) && i>0){
            if(pieces[i-1][j]!=null && !pieces[i-1][j].getPower() && pieces[i-1][j].getDir(DOWN))
                updatePowerLine(i-1,j);
        }
        if(pieces[i][j].getDir(RIGHT) && j<maxCols-1){
            if(pieces[i][j+1]!=null && !pieces[i][j+1].getPower() && pieces[i][j+1].getDir(LEFT))
                updatePowerLine(i,j+1);
        }
        if(pieces[i][j].getDir(DOWN) && i<maxLines-1){
            if(pieces[i+1][j]!=null && !pieces[i+1][j].getPower() && pieces[i+1][j].getDir(UP))
                updatePowerLine(i+1,j);
        }
    }

    public void updatePower(){

        int x = 0, y = 0;

        for(int i = 0; i<maxLines; i++){
            for (int j = 0; j<maxCols; j++) {
                if (pieces[i][j] != null){
                    if(pieces[i][j].getSimb() != 'P') {                                                        //Passa a energia de todas as peças para false, menos da fonte
                        pieces[i][j].setPower(false);
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
        for(int i = 0; i<maxLines; i++){
            for (int j = 0; j<maxCols; j++){
                if (pieces[i][j]!=null  && pieces[i][j].getSimb()=='H' && !pieces[i][j].getPower()){
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
