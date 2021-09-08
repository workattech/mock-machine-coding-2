package com.sameerpandit.db;

import com.sameerpandit.models.Expense;
import com.sameerpandit.models.Transaction;
import com.sameerpandit.models.User;

import org.jvnet.hk2.annotations.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import jakarta.inject.Singleton;

@Service
@Singleton
public class InMemRepository {
    private ConcurrentHashMap<String, List<Transaction>> creditorMap         = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, List<Transaction>> debitorMap          = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, User>              userMap             = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, List<Expense>>     expenseInitiatorMap = new ConcurrentHashMap<>();
    private AtomicInteger                                userIdNumber        = new AtomicInteger(0);

    public void clear(){
        creditorMap         = new ConcurrentHashMap<>();
        debitorMap          = new ConcurrentHashMap<>();
        userMap             = new ConcurrentHashMap<>();
        expenseInitiatorMap = new ConcurrentHashMap<>();
        userIdNumber        = new AtomicInteger(0);
    }

    public List<Transaction> getCreditTransactionsByUserId(String userId) {
        return creditorMap.getOrDefault(userId, new LinkedList<>());
    }

    public List<Transaction> getDebitTransactionsByUserId(String userId) {
        return debitorMap.getOrDefault(userId, new LinkedList<>());
    }

    public List<Expense> getExpensesInitiatedByUserId(String userId) {
        return expenseInitiatorMap.get(userId);
    }

    public User getUserDetailsById(String userId) {
        return userMap.get(userId);
    }

    public List<User> getUsers(){
        LinkedList<User> users = new LinkedList<>();
        users.addAll(userMap.values());
        return users;
    }

    public String addUser(String name, String email, String phone) {
        String userId = "u" + userIdNumber.incrementAndGet();
        userMap.put(userId, new User(userId, name, email, phone));
        return userId;
    }

    public Expense addExpense(Integer amount, String type, String initiatorId, String[] participants) {
        Expense expense = new Expense(amount, type, initiatorId, participants);
        expenseInitiatorMap.computeIfAbsent(initiatorId, k -> new LinkedList<>()).add(expense);
        return expense;
    }

    public Expense addExpense(Integer amount, String type, String initiatorId, String[] participants, Integer[] splits) {
        Expense expense = new Expense(amount, type, initiatorId, participants,splits);
        expenseInitiatorMap.computeIfAbsent(initiatorId, k -> new LinkedList<>()).add(expense);
        return expense;
    }


    public void addCreditorTransaction(String creditorId, Transaction tx) {
        creditorMap.computeIfAbsent(creditorId, k -> new LinkedList<>()).add(tx);
    }

    public void addDebitorTransaction(String debitorId, Transaction tx) {
        debitorMap.computeIfAbsent(debitorId, k -> new LinkedList<>()).add(tx);
    }

}
