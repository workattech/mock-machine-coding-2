package model;

import lombok.Data;
import lombok.NonNull;

@Data
public class ValuePair {
    @NonNull
    private final Identifier key;

    @NonNull
    private Double value;

}
