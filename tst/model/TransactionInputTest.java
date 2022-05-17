package model;

import org.junit.Test;

import java.util.ArrayList;

public class TransactionInputTest {

    @Test
    public void testTransactionInput() {
        TransactionInput.builder()
            .payer(new Identifier("Dummy"))
            .shareParameters(new ArrayList<>())
            .splitType(SplitType.EQUAL)
            .amount(120.1)
            .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTransactionInput_moreThatTwoDigits() {
        TransactionInput.builder()
            .payer(new Identifier("Dummy"))
            .shareParameters(new ArrayList<>())
            .splitType(SplitType.EQUAL)
            .amount(120.001)
            .build();
    }
}
