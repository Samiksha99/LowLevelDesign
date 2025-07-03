package Observer;

import Observable.StocksObservable;

public class EmailAlertObserverImpl implements NotificationAlertObserver {

    String emailId;
    StocksObservable stocksObservable;
    public EmailAlertObserverImpl(String emailId, StocksObservable stocksObservable){
        this.emailId = emailId;
        this.stocksObservable = stocksObservable;
    }
    @Override
    public void update() {
        sendMail(emailId, "Product is in stock");
    }
    private void sendMail(String emailId, String msg){
        System.out.println(msg+ " Mail sent to:" + emailId);
    }
}
