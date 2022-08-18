package models;

import lombok.Builder;
import lombok.Data;

import java.util.HashMap;
import java.util.List;

@Data
@Builder
public class SplitwiseAddResponse {

    private HashMap<String, Double> overAllNetWithEachUser;

    private List<Balance> balances;

    private Integer status;

    private String message;

    private List<Error> errorList;
}