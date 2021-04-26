package models;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class User {

    private String userId;

    private String name;

    private String email;

    private String phoneNumber;

    private Double totalBalance;

    private List<Balance> balanceList;

    private Double tempBalance;

    private List<Transaction> transactions;
}
