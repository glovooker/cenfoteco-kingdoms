package BL.observer.interfaces_observer;

public interface Subject {
    public void addObservers(Observer observer);
    public void cleanObservers();
    public void notifyObserver();
}
