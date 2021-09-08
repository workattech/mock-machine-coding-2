package com.sameerpandit.service;

import com.sameerpandit.business.SplitStrategy;
import com.sameerpandit.db.InMemRepository;
import com.sameerpandit.models.Expense;

import org.glassfish.hk2.api.IterableProvider;
import org.jvnet.hk2.annotations.Service;

import jakarta.inject.Inject;

@Service
public class ExpenseManagerService {

    @Inject
    InMemRepository repository;

    @Inject
    LedgerService ledgerService;

    @Inject
    IterableProvider<SplitStrategy> splitStrategies;

    private SplitStrategy getSplitStrategy(String expenceTye){
        return splitStrategies.named(expenceTye).get();
    }

    public void addExpense(Integer amount, String type, String initiatorId, String[] participants){
        Expense e = repository.addExpense(amount, type, initiatorId, participants);
        ledgerService.addTransaction(e,getSplitStrategy(e.getType()));
    }

    public void addExpense(Integer amount, String type, String initiatorId, String[] participants, Integer[] splits){
        Expense e = repository.addExpense(amount, type, initiatorId, participants,splits);
        ledgerService.addTransaction(e,getSplitStrategy(e.getType()));
    }

    public void show(){
        ledgerService.show();
    }

    public void show(String userId){
        ledgerService.show(userId);
    }

}
