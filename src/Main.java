import model.Identifier;
import model.InstructionType;
import model.SplitType;
import model.TransactionInput;
import model.ValuePair;
import service.SplitwiseMainService;
import service.impl.SplitwiseMainServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String args[]) throws IOException {
        final SplitwiseMainService splitwiseMainService = new SplitwiseMainServiceImpl();
        final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        splitwiseMainService.createUser(new Identifier("u3"));
        splitwiseMainService.createUser(new Identifier("u2"));
        splitwiseMainService.createUser(new Identifier("u1"));
        splitwiseMainService.createUser(new Identifier("u4"));

        while(true) {
            try {
                final String inputLine = reader.readLine();
                final String[] input = inputLine.split(" ");
                if (input[0].equals(InstructionType.EXPENSE.toString())) {
                    final Identifier payer = new Identifier(input[1]);
                    final Double amount = Double.parseDouble(input[2]);
                    final Integer number = Integer.parseInt(input[3]);
                    final List<ValuePair> valuePairs = new ArrayList<>();
                    for(int i = 4; i < 4 + number; i++) {
                        valuePairs.add(new ValuePair(new Identifier(input[i]), 0.0));
                    }

                    if (!input[4 + number].equals(SplitType.EQUAL.toString())) {
                        for (int i = 5 + number; i < 5 + 2 * number; i++) {
                            valuePairs.get(i - number - 5).setValue(Double.parseDouble(input[i]));
                        }
                    }
                    final TransactionInput transactionInput =
                        TransactionInput.builder()
                            .amount(amount)
                            .payer(payer)
                            .shareParameters(valuePairs)
                            .splitType(SplitType.valueOf(input[4 + number]))
                            .build();
                    splitwiseMainService.addExpense(transactionInput);
                } else if (input[0].equals(InstructionType.SHOW.toString())) {
                    if (input.length == 1) {
                        formatAndDisplayAll(splitwiseMainService.getBalances());
                    } else {
                        final Identifier user = new Identifier(input[1]);
                        formatAndDisplay(user, splitwiseMainService.getBalanceForUser(user), false);
                    }
                } else {
                    throw new RuntimeException(String.format("Did not match any command: %s", inputLine));
                }
            } catch (final Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void formatAndDisplay(final Identifier user, final Map<Identifier, Double> expenseMap, final Boolean allMode) {
        if(expenseMap.size() == 0) {
            System.out.println("No balances");
        }
        expenseMap.forEach(
            ((identifier, aDouble) -> {
                if(aDouble > 0) {
                    System.out.println(identifier.getValue() + " owes " + user.getValue() + " " + Math.abs(aDouble));
                } else if (!allMode) {
                    System.out.println(user.getValue() + " owes " + identifier.getValue() + " " + Math.abs(aDouble));
                }
            })
        );
    }

    private static void formatAndDisplayAll(final Map<Identifier, Map<Identifier, Double>> allExpenseMap) {
        if (allExpenseMap.keySet().stream()
            .allMatch(key -> allExpenseMap.get(key).size() == 0)) {
            System.out.println("No balances");
            return;
        }

        allExpenseMap.forEach(
            (userId, expenseMap)  -> {
                formatAndDisplay(userId, expenseMap, true);
            });
    }
}
