import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;

public class BalanceSheet {

    private Map<TradePair, List<Expense>> sheet;
    private ExpenseGraph expenseGraph;

    public BalanceSheet() {

        sheet = new HashMap<>();
        expenseGraph = new ExpenseGraph();
    }

    public void addExpense(String lender, String borrower, Expense expense) {

        TradePair pair = new TradePair(lender, borrower);


        if (sheet.containsKey(pair))
            sheet.get(pair).add(expense);
        else
            sheet.computeIfAbsent(pair, k -> new ArrayList<>()).add(expense);

        expenseGraph.add(lender, borrower, expense.getAmount());

    }

    public Map<TradePair, List<Expense>> getSheet() {
        return sheet;
    }

    public List<String> showExpense(String user) {
        return expenseGraph.generateFor(user);
    }


}

class ExpenseGraph {


    private Map<String, Map<String, Double>> graph;

    public ExpenseGraph() {
        this.graph = new HashMap<>();
    }


    public List<String> generateFor(String user) {
        List<String> logs = new ArrayList<>();


        graph.entrySet().stream().forEach(lender -> {

            lender.getValue().entrySet().stream().filter(entry -> {
                if (user.isEmpty() || lender.getKey().equals(user))
                    return true;
                return entry.getKey().equals(user);
            }).forEach(borrower -> {

                logs.add(new StringBuilder().append(borrower.getKey() + " owes " + lender.getKey() + ": ").append(borrower.getValue()).toString());

            });


        });
        if (logs.isEmpty())
            logs.add("No balances");

        return logs;
    }

    private void addEdge(String from, String to, final double amount) {
        if (graph.containsKey(from)) {

            Map<String, Double> fromToEdges = graph.get(from);

            fromToEdges.compute(to, (key, value) -> value == null ? amount : value + amount);


        } else {
            Map<String, Double> fromToEdges = new HashMap<>();
            fromToEdges.put(to, amount);
            graph.put(from, fromToEdges);
        }
    }

    private void removeEdge(String from, String to) {
        graph.get(from).remove(to);
    }

    public void add(String lender, String borrower, double amount) {


        if (graph.containsKey(borrower)) {

            Map<String, Double> toFromEdges = graph.get(borrower);


            double prevAmount = toFromEdges.getOrDefault(lender, -1.0);


            if (Double.compare(prevAmount, -1.0) == 0) {
                addEdge(lender, borrower, amount);
            } else {
                int compare = Double.compare(prevAmount, amount);
                if (compare <= 0) {
                    removeEdge(borrower, lender);
                }


                if (compare == -1) {
                    addEdge(lender, borrower, amount - prevAmount);
                } else if (compare == 1) {
                    addEdge(borrower, lender, -amount);
                }
            }

        } else {
            addEdge(lender, borrower, amount);
        }


    }
}
