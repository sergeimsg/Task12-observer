package com.task.observer;

public interface ArrayObservable {
    public void attach(ArrayObserver ob);
    public void detach(ArrayObserver ob);
    public void notifyObservers();

}
