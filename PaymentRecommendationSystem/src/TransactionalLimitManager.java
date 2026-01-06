import java.util.HashMap;
import java.util.Map;

public class TransactionalLimitManager {
    private static TransactionalLimitManager instance;
    private Map<String, Double> transactionLimit;

    private TransactionalLimitManager() {
        transactionLimit = new HashMap<>();
        transactionLimit.put("CREDIT_CARD", 50000.0);
        transactionLimit.put("DEBIT_CARD", 30000.0);
        transactionLimit.put("UPI", 10000.0);
        transactionLimit.put("NET_BANKING", 20000.0);
    }

    public static synchronized TransactionalLimitManager getInstance() {
        if(instance ==  null) {
            instance = new TransactionalLimitManager();
        }
        return instance;
    }

    public double getTransactionLimit(String paymentType){
        return transactionLimit.getOrDefault(paymentType, 0.0);
    }

    public void setTransactionLimit(String paymentType, double limit) {
        transactionLimit.put(paymentType, limit);
    }
}
