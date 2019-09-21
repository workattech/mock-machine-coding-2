package service;

import model.Identifier;
import model.TransactionInput;

import java.util.Map;

public interface SplitwiseMainService {
    public void createUser(Identifier userId);

    public void addExpense(TransactionInput transaction);

    public Map<Identifier, Map<Identifier, Double>> getBalances();

    public Map<Identifier, Double> getBalanceForUser(Identifier userId);
}
