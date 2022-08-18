package models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Amount {

    private Double value;
    private Currency currency = Currency.INR;

}
