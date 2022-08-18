package Designs.Splitwise.parser;

import Designs.Splitwise.machine.Machine;

public class Parser {
    private final Machine machine;

    public Parser(Machine machine) {
        this.machine = machine;
    }

    public void parse(String cmd) {
        String[] tokens = cmd.split(" ");
        if (tokens[0].equals("SHOW")) {
            if (tokens.length == 1) {
                machine.showAll();
            } else if (tokens.length == 2) {
                machine.showBalanceFor(tokens[1]);
            }
        } else if (tokens[0].equals("EXPENSE")) {
            machine.addExpense(tokens);
        }
    }
}
