package PaymentMethodComputation;

import Interfaces.PaymentComputationStrategy;

public class PaymentComputationStrategyFactory {
    public PaymentComputationStrategy createPaymentComputationStrategy(String strategyType){
        switch (strategyType) {
            case "common":
                return new CommonPaymentComputationStrategy();
            default:
                throw new IllegalArgumentException("Invalid Strategy Type" + strategyType);
        }
    }
}
