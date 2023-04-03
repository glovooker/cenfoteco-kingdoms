package Model;
import Observer.Interface.Observer;
import Observer.Interface.Subject;

import java.util.TimerTask;



public class TimerSec extends TimerTask implements Subject {
    private int time;

    private static final int TIME_IN_GAME = 6;

    private Observer observer;

    private Player playerInTurn;



    public TimerSec(Player playerInTurn) {
        this.time = TIME_IN_GAME;
        this.playerInTurn = playerInTurn;
    }

    @Override
    public void run() {
        this.time--;
        System.out.println(this.time);
        if(this.time == 0){
           notifyObserver();
           this.time = TIME_IN_GAME;
        }
    }

    public void stopTime(){
        this.cancel();
    }


    @Override
    public void addObservers(Observer observer) {
        this.observer = observer;
    }

    @Override
    public void removeObserver() {
        this.observer = null;
    }


    @Override
    public void notifyObserver() {
        this.observer.update();
    }
}
