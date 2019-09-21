package model;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.util.Map;

@Builder
@Getter
public class User {
    @NonNull
    private Identifier identifier;

    private String name;

    private String email;

    @NonNull
    private Map<Identifier, Double> balances;
}
