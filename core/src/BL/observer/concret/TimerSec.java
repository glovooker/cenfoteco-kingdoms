package BL.observer.concret;
import Model.GameState;
import BL.observer.interfaces_observer.Observer;
import BL.observer.interfaces_observer.Subject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class TimerSec extends TimerTask implements Subject {
    private int time;

    public static final int TIME_IN_GAME = 59;

    private final List<Observer> observers = new ArrayList<>();

    private Timer timerController;

    private final GameState state;

    public TimerSec(GameState state) {
        this.time = TIME_IN_GAME;
        this.state = state;
    }

    public void start() {
        this.timerController = new Timer();
        this.timerController.scheduleAtFixedRate(this, 1000, 1000);
    }

    public void stop() {
        this.timerController.cancel();
    }

    @Override
    public void run() {
        if(this.time == 0) {
            this.state.changePlayerInTurn();
        }

        notifyObserver();

        if(time < -1) {
            this.time = TIME_IN_GAME;
        }

        this.time--;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public void addObservers(Observer observer) {
        this.observers.add(observer);
    }

    @Override
    public void cleanObservers() {
        this.observers.clear();
    }

    @Override
    public void notifyObserver() {
        this.state.setTime(this.time);
        for (Observer observer: this.observers) {
             observer.update(state);
        }
    }
}
