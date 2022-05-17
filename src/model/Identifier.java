package model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class Identifier {
    private final String value;

    public Identifier(@NonNull final String value) {
        this.value = value;
    }
}
