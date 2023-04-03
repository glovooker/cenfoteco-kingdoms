package Observer.Interface;

public interface Subject {
    public void addObservers(Observer observer);
    public void removeObserver();
    public void notifyObserver();
}
