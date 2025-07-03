import Observable.IphoneObservableImpl;
import Observable.StocksObservable;
import Observer.EmailAlertObserverImpl;
import Observer.MobileAlertObserverImpl;
import Observer.NotificationAlertObserver;

public class Main {
    public static void main(String[] args) {
        StocksObservable iphoneStocksObservable = new IphoneObservableImpl();

        NotificationAlertObserver observer1 = new EmailAlertObserverImpl("agrawalsam@gmail.com", iphoneStocksObservable);
        NotificationAlertObserver observer2 = new MobileAlertObserverImpl("samagraw", iphoneStocksObservable);

        iphoneStocksObservable.add(observer1);
        iphoneStocksObservable.add(observer2);

        iphoneStocksObservable.setStockCount(10);
        iphoneStocksObservable.setStockCount(0);
        iphoneStocksObservable.setStockCount(100);

    }
}