package Memento;

import Model.Player;

public class Snapshot {
    private Player player1;
    private Player player2;

    public void newPhotoPlayer1(Player pLayer1){
        this.player1 = pLayer1;
    }

    public void newPhotoPlayer2(Player pLayer2){
        this.player2 = pLayer2;
    }

    public Player getStatePlayer1(){
        return this.player1;
    }

    public Player getStatePlayer2(){
        return this.player2;
    }
}
