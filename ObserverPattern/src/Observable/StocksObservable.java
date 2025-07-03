package Observable;

import Observer.NotificationAlertObserver;

public interface StocksObservable {
    void add(NotificationAlertObserver obj);
    void remove(NotificationAlertObserver obj);

    void notifySubscribers();
    void setStockCount(int stockCount);
    int getStockCount();
}
