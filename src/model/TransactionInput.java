package model;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.util.List;

@Builder(toBuilder = true)
@Getter
public class TransactionInput {
    @NonNull
    private final Identifier payer;

    @NonNull
    private final SplitType splitType;

    @NonNull
    private final List<ValuePair> shareParameters;

    @NonNull
    private final Double amount;

    public static class TransactionInputBuilder {
        public TransactionInputBuilder amount(Double amount) {
            final Double truncatedAmount = Math.floor(amount * 100)/100;
            if(!truncatedAmount.equals(amount)) {
                throw new IllegalArgumentException("More than two digits specified in input amount");
            }
            this.amount = amount;
            return this;
        }
    }
}
