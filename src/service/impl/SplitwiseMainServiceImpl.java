package service.impl;

import exception.UserAlreadyExistsException;
import lombok.NonNull;
import model.TransactionInput;
import model.Identifier;
import model.User;
import service.SplitwiseMainService;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class SplitwiseMainServiceImpl implements SplitwiseMainService {
    private final Map<Identifier, User> userMap = new HashMap<>();

    @Override
    public void addExpense(@NonNull final TransactionInput transaction) {
        final Identifier payer = transaction.getPayer();
        final Map<Identifier, Double> payerExpenses = getBalanceForUser(payer);

        checkUserExists(payer);

        final Map<Identifier, Double> expenseAmounts = transaction.getSplitType().splitAmount(transaction.getAmount(),
            transaction.getShareParameters());
        expenseAmounts.keySet().forEach(this::checkUserExists);

        expenseAmounts.keySet().stream()
            .filter(userId -> !userId.equals(payer))
            .forEach(userId -> {
                addExpenseToMap(payerExpenses, userId, expenseAmounts.get(userId));
                addExpenseToMap(getBalanceForUser(userId), payer, -expenseAmounts.get(userId));
            });
    }

    @Override
    public Map<Identifier, Double> getBalanceForUser(@NonNull final Identifier userId) {
        if (!userMap.keySet().contains(userId)) {
            throw new RuntimeException(String.format("User %s does not exist", userId.getValue()));
        }
        return userMap.get(userId).getBalances();
    }

    private void checkUserExists(final Identifier userId) {
        if (!userMap.keySet().contains(userId)) {
            throw new RuntimeException(String.format("User %s does not exist", userId.getValue()));
        }
    }

    private void addExpenseToMap(final Map<Identifier, Double> expenseMap,
                                 final Identifier userId,
                                 final Double amount) {
        if(expenseMap.keySet().contains(userId)) {
            final Double updatedAmount = expenseMap.get(userId) + amount;
            if(updatedAmount == 0.0) {
                expenseMap.remove(userId);
            } else {
                expenseMap.put(userId, expenseMap.get(userId) + amount);
            }
        } else {
            expenseMap.put(userId, amount);
        }
    }

    @Override
    public void createUser(@NonNull final Identifier userId) {
        if(userMap.keySet().contains(userId)) {
            throw new UserAlreadyExistsException(String.format("User: %s already exists", userId.getValue()));
        }
        final User user =
            User.builder()
                .identifier(userId)
                .balances(new HashMap<>())
                .build();
        userMap.put(userId, user);
    }

    @Override
    public Map<Identifier, Map<Identifier, Double>> getBalances() {
        return userMap.keySet().parallelStream()
            .map(userMap::get)
            .collect(Collectors.toMap(User::getIdentifier, User::getBalances));
    }

}

