package Observer;

import Observable.StocksObservable;

public class MobileAlertObserverImpl implements NotificationAlertObserver {

    String userName;
    StocksObservable stocksObservable;
    public MobileAlertObserverImpl(String userName, StocksObservable observable){
        this.stocksObservable = observable;
        this.userName = userName;
    }

    @Override
    public void update() {
        sendMsgOnMobils(userName, "Product in stock");
    }

    private void sendMsgOnMobils(String userName, String msg){
        System.out.println(msg + " Msg sent to:" + userName);
    }
}
