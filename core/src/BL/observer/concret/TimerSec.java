package BL.observer.concret;
import Model.GameState;
import Model.Player;
import BL.observer.interfaces_observer.Observer;
import BL.observer.interfaces_observer.Subject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class TimerSec extends TimerTask implements Subject {
    private int time;

    private static final int TIME_IN_GAME = 6;

    private final List<Observer> observers = new ArrayList<>();

    private Timer timerController;

    private GameState state = GameState.getStateInstance();

    public TimerSec() {
        this.time = TIME_IN_GAME;
    }

    public void start() {
        this.timerController = new Timer();
        this.timerController.scheduleAtFixedRate(this, 1000, 1000);
    }

    @Override
    public void run() {
        this.time--;

        if(this.time == 0) {
            this.state.changePlayerInTurn();
        }

        notifyObserver();

        if(time == -3) {
            this.time = TIME_IN_GAME;
        }
    }


    @Override
    public void addObservers(Observer observer) {
        this.observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        this.observers.remove(observer);
    }

    @Override
    public void notifyObserver() {
        this.state.setTime(this.time);
        for (Observer observer: this.observers) {
             observer.update(state);
        }
    }

}
