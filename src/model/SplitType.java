package model;

import exception.ShareTypeValidationException;
import lombok.NonNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public enum SplitType {
    EQUAL {
        public Map<Identifier, Double> splitAmount(@NonNull final Double amount, @NonNull final List<ValuePair> shareParameters) {
            final Integer numberOfUsers = shareParameters.size();
            final Map<Identifier, Double> shareAmounts = new HashMap<>();
            final Double amountToBeShared = amount / numberOfUsers;
            shareParameters
                .forEach(
                    shareParameter -> shareAmounts.put(shareParameter.getKey(), truncateToTwoDecimalPlaces(amountToBeShared)));
            adjustToTwoDecimalPlaces(shareParameters, amount, shareAmounts);
            return shareAmounts;
        }
    },
    EXACT {
        public Map<Identifier, Double> splitAmount(@NonNull final Double amount, @NonNull final List<ValuePair> shareParameters) {
            SplitType.validateTotal(shareParameters, amount);
            final Map<Identifier, Double> shareAmounts = shareParameters.stream()
                .collect(Collectors.toMap(ValuePair::getKey, valuePair -> truncateToTwoDecimalPlaces(valuePair.getValue())));
            adjustToTwoDecimalPlaces(shareParameters, amount, shareAmounts);
            return shareAmounts;
        }
    },
    PERCENTAGE {
        public Map<Identifier, Double> splitAmount(@NonNull final Double amount, @NonNull final List<ValuePair> shareParameters) {
            final Map<Identifier, Double> shareAmounts = new HashMap<>();
            SplitType.validateTotal(shareParameters, 100.0);
            shareParameters.forEach(
                valuePair -> shareAmounts.put(valuePair.getKey(), truncateToTwoDecimalPlaces((valuePair.getValue() * amount)/100)));
            adjustToTwoDecimalPlaces(shareParameters, amount, shareAmounts);
            return shareAmounts;
        }
    },
    SHARES {
        public Map<Identifier, Double> splitAmount(@NonNull final Double amount, @NonNull final List<ValuePair> shareParameters) {
            final Map<Identifier, Double> shareAmounts = new HashMap<>();
            final Double shareSum = shareParameters.stream()
                .map(ValuePair::getValue)
                .reduce((a, b) -> a + b)
                .orElseThrow(() -> new ShareTypeValidationException("Error occurred in processing SHARES split"));
            shareParameters.forEach(
                valuePair -> shareAmounts.put(valuePair.getKey(), truncateToTwoDecimalPlaces((valuePair.getValue() * amount)/shareSum)));
            adjustToTwoDecimalPlaces(shareParameters, amount, shareAmounts);
            return shareAmounts;
        }
    };

    private static void validateTotal(final List<ValuePair> valuePairs, final Double amount) {
        valuePairs.stream()
            .map(ValuePair::getValue)
            .reduce((a, b) -> a + b)
            .filter(sumAmount -> sumAmount.equals(amount))
            .orElseThrow(() -> new ShareTypeValidationException("Values do not add for split"));
    }

    private static Double truncateToTwoDecimalPlaces(final Double amount) {
        return Math.floor(amount * 100)/100;
    }

    private static void adjustToTwoDecimalPlaces(final List<ValuePair> shareParameters,
                                                 final Double amount,
                                                 final Map<Identifier, Double> expenses) {
        final Double totalAmount = expenses.keySet().stream()
            .map(expenses::get)
            .reduce((a, b) -> a + b).get();

        if (!totalAmount.equals(amount)) {
            final Double diff = amount - totalAmount;
            expenses.put(shareParameters.get(0).getKey(), expenses.get(shareParameters.get(0).getKey()) + diff);
        }
    }

    public abstract Map<Identifier, Double> splitAmount(@NonNull final Double amount, @NonNull final List<ValuePair> shareParameters);

}
