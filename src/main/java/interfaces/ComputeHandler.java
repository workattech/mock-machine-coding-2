package interfaces;

import models.SplitwiseAddRequest;

import java.util.HashMap;

public interface ComputeHandler {

    void compute(SplitwiseAddRequest request, HashMap<String, Double> balancesList);
}
