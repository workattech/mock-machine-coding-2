package models;


import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class FetchBalanceResponse {

    private List<Balance> balanceList;

    private List<Error> errors;
}
