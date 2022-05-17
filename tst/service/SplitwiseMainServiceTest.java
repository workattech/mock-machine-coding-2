package service;

import model.Identifier;
import model.SplitType;
import model.TransactionInput;
import model.ValuePair;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import service.impl.SplitwiseMainServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SplitwiseMainServiceTest {

    private SplitwiseMainService splitwiseMainService;
    private Identifier u1;
    private Identifier u2;
    private Identifier u3;

    @Before
    public void setUp() {
        splitwiseMainService = new SplitwiseMainServiceImpl();
        u1 = new Identifier("UserId1");
        u2 = new Identifier("UserId2");
        u3 = new Identifier("UserId3");
        splitwiseMainService.createUser(u1);
        splitwiseMainService.createUser(u2);
        splitwiseMainService.createUser(u3);
    }

    @Test
    public void testMainService_EQUAL() {
        final List<ValuePair> valuePairList = new ArrayList<>();
        valuePairList.add(new ValuePair(u1, 0.0));
        valuePairList.add(new ValuePair(u2, 0.0));
        valuePairList.add(new ValuePair(u3, 0.0));

        final List<ValuePair> valuePairList2 = new ArrayList<>();
        valuePairList2.add(new ValuePair(u1, 0.0));
        valuePairList2.add(new ValuePair(u2, 0.0));
        valuePairList2.add(new ValuePair(u3, 0.0));

        final List<ValuePair> valuePairList3 = new ArrayList<>();
        valuePairList3.add(new ValuePair(u1, 0.0));
        valuePairList3.add(new ValuePair(u2, 0.0));
        valuePairList3.add(new ValuePair(u3, 0.0));


        final TransactionInput transactionInput =
            TransactionInput.builder()
                .amount(120.0)
                .payer(u1)
                .splitType(SplitType.EQUAL)
                .shareParameters(valuePairList)
                .build();
        splitwiseMainService.addExpense(transactionInput);
        final Map<Identifier, Map<Identifier, Double>> allBalances = splitwiseMainService.getBalances();
        Assert.assertEquals(allBalances.get(u1).get(u3), new Double(40.0));
        Assert.assertEquals(allBalances.get(u1).get(u2), new Double(40.0));
        Assert.assertEquals(allBalances.get(u3).get(u1), new Double(-40.0));
        Assert.assertEquals(allBalances.get(u2).get(u1), new Double(-40.0));

        splitwiseMainService.addExpense(
            transactionInput
                .toBuilder()
                .payer(u2)
                .shareParameters(valuePairList2)
                .build());
        splitwiseMainService.addExpense(
            transactionInput
                .toBuilder()
                .payer(u3)
                .shareParameters(valuePairList3)
                .build());
        final Map<Identifier, Map<Identifier, Double>> allBalances2 = splitwiseMainService.getBalances();
        Assert.assertTrue(allBalances2.keySet().stream()
            .allMatch(
                key -> allBalances2.get(key).size() == 0));
    }

    @Test
    public void testMainService_SHARES() {
        final List<ValuePair> valuePairList = new ArrayList<>();
        valuePairList.add(new ValuePair(u1, 1.0));
        valuePairList.add(new ValuePair(u2, 1.0));
        valuePairList.add(new ValuePair(u3, 2.0));

        final TransactionInput transactionInput =
            TransactionInput.builder()
                .amount(100.0)
                .payer(u1)
                .splitType(SplitType.SHARES)
                .shareParameters(valuePairList)
                .build();
        splitwiseMainService.addExpense(transactionInput);
        final Map<Identifier, Map<Identifier, Double>> allBalances = splitwiseMainService.getBalances();
        Assert.assertEquals(allBalances.get(u1).get(u3), new Double(50.0));
        Assert.assertEquals(allBalances.get(u1).get(u2), new Double(25.0));
        Assert.assertEquals(allBalances.get(u3).get(u1), new Double(-50.0));
        Assert.assertEquals(allBalances.get(u2).get(u1), new Double(-25.0));
    }

    @Test
    public void testMainService_PERCENTAGE() {
        final List<ValuePair> valuePairList = new ArrayList<>();
        valuePairList.add(new ValuePair(u1, 25.0));
        valuePairList.add(new ValuePair(u2, 25.0));
        valuePairList.add(new ValuePair(u3, 50.0));

        final TransactionInput transactionInput =
            TransactionInput.builder()
                .amount(100.0)
                .payer(u1)
                .splitType(SplitType.PERCENTAGE)
                .shareParameters(valuePairList)
                .build();
        splitwiseMainService.addExpense(transactionInput);
        final Map<Identifier, Map<Identifier, Double>> allBalances = splitwiseMainService.getBalances();
        Assert.assertEquals(allBalances.get(u1).get(u3), new Double(50.0));
        Assert.assertEquals(allBalances.get(u1).get(u2), new Double(25.0));
        Assert.assertEquals(allBalances.get(u3).get(u1), new Double(-50.0));
        Assert.assertEquals(allBalances.get(u2).get(u1), new Double(-25.0));
    }

    @Test
    public void testMainService_EXACT() {
        final List<ValuePair> valuePairList = new ArrayList<>();
        valuePairList.add(new ValuePair(u1, 25.0));
        valuePairList.add(new ValuePair(u2, 25.0));
        valuePairList.add(new ValuePair(u3, 50.0));

        final TransactionInput transactionInput =
            TransactionInput.builder()
                .amount(100.0)
                .payer(u1)
                .splitType(SplitType.EXACT)
                .shareParameters(valuePairList)
                .build();
        splitwiseMainService.addExpense(transactionInput);
        final Map<Identifier, Map<Identifier, Double>> allBalances = splitwiseMainService.getBalances();
        Assert.assertEquals(allBalances.get(u1).get(u3), new Double(50.0));
        Assert.assertEquals(allBalances.get(u1).get(u2), new Double(25.0));
        Assert.assertEquals(allBalances.get(u3).get(u1), new Double(-50.0));
        Assert.assertEquals(allBalances.get(u2).get(u1), new Double(-25.0));
    }
}
