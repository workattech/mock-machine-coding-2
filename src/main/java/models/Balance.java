package models;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Balance {

    private String from;

    private String to;

    private Double balance;
}
