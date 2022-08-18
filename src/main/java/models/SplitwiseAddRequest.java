package models;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SplitwiseAddRequest {

    private String requestAdderId;

    private Amount amount;

    private String expenseName;

    private List<String> metadata;

    private ExpenseType expenseType;

    private List<String> participants;

    private List<Double> values;

}
