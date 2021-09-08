package com.sameerpandit.service;

import com.sameerpandit.business.Constants;
import com.sameerpandit.business.SplitStrategy;
import com.sameerpandit.db.InMemRepository;
import com.sameerpandit.models.Expense;
import com.sameerpandit.models.NoBalanceException;
import com.sameerpandit.models.Transaction;
import com.sameerpandit.models.User;

import org.jvnet.hk2.annotations.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import jakarta.inject.Inject;

@Service
public class LedgerService {
    @Inject
    InMemRepository repository;

    public void addTransaction(Expense e, SplitStrategy splitStrategy){
        Integer[] splitAmounts = splitStrategy.split(e);
        int start = 0;
        for(String participant: e.getParticipants()){
            Transaction tx = new Transaction(splitAmounts[start],e.getInitiatorId(), participant);
            repository.addCreditorTransaction(e.getInitiatorId(),tx);
            repository.addDebitorTransaction(participant,tx);
            start++;
        }
    }

    public void show(){
        HashSet<TreeSet<String>> userPairs = new HashSet<>();
        try{
            for(Map.Entry<String, Map<String,Integer>> entry : getUserBalancesForUsers().entrySet()){
                String userId = entry.getKey();
                for(Map.Entry<String, Integer> uEntry: entry.getValue().entrySet()){
                    TreeSet<String> pair = new TreeSet<>();
                    pair.add(userId);pair.add(uEntry.getKey());
                    User u1 = repository.getUserDetailsById(userId);
                    User u2 = repository.getUserDetailsById(uEntry.getKey());
                    if(userId.equals(u2.getUserId()) || userPairs.contains(pair))
                        continue;
                    logExpense(u1.getName(),u2.getName(),uEntry.getValue());
                    userPairs.add(pair);
                }
            }
        }catch (NoBalanceException e){
            System.out.println("No Balances");
        }

    }

    public void logExpense(String debitor, String creditor, Integer unscaledAmount){
        if(unscaledAmount<0)
            if(unscaledAmount%Constants.EXPENSE_SCALING_FACTOR==0)
                System.out.println(debitor + " owes " + creditor + " :" + (-1 * unscaledAmount / Constants.EXPENSE_SCALING_FACTOR));
            else
                System.out.println(debitor + " owes " + creditor + " :" + ((float)(-1 * unscaledAmount) / Constants.EXPENSE_SCALING_FACTOR));
        else
            if(unscaledAmount%Constants.EXPENSE_SCALING_FACTOR==0)
                System.out.println(creditor + " owes " + debitor + " :" + (unscaledAmount / Constants.EXPENSE_SCALING_FACTOR));
            else
                System.out.println(creditor + " owes " + debitor + " :" + ((float)unscaledAmount/ Constants.EXPENSE_SCALING_FACTOR));
    }

    public void show(String userId){
        try{
            User user = repository.getUserDetailsById(userId);
            for(Map.Entry<String, Integer> entry: getUserBalancesForUser(userId).entrySet()){
                User otherUser = repository.getUserDetailsById(entry.getKey());
                if(userId.equals(otherUser.getUserId()))
                    continue;
                logExpense(user.getName(),otherUser.getName(),entry.getValue());
            }
        }catch (NoBalanceException e){
            System.out.println("No Balances");
        }
    }

    private Map<String, Integer> getUserBalancesForUser(String userId) throws NoBalanceException {
        List<Transaction> creditorTxs = repository.getCreditTransactionsByUserId(userId);
        List<Transaction> debitorTxs = repository.getDebitTransactionsByUserId(userId);
        Map<String, Integer> balances = new HashMap<>();
        for(Transaction tx: creditorTxs){
            balances.put(tx.getDebitorId(), balances.getOrDefault(tx.getDebitorId(),0)+tx.getUnscaledAmount());
        }
        for(Transaction tx: debitorTxs){
            balances.put(tx.getCreditorId(), balances.getOrDefault(tx.getCreditorId(),0)-tx.getUnscaledAmount());
        }
        if(balances.isEmpty())
            throw new NoBalanceException();
        return balances;
    }

    private Map<String, Map<String,Integer>> getUserBalancesForUsers() throws NoBalanceException {
        List<User> users = repository.getUsers();
        Map<String, Map<String,Integer>> userBalances = new HashMap<>();
        for(User user: users){
            String userId = user.getUserId();
            Map<String,Integer> balance = getUserBalancesForUser(userId);
            userBalances.put(userId,balance);
        }
        if(userBalances.isEmpty())
            throw new NoBalanceException();
        return userBalances;
    }
}
