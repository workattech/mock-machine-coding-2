package handlers;

import impl.UserServiceImpl;
import interfaces.ComputeHandler;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import models.SplitwiseAddRequest;
import models.User;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShareComputeHandler implements ComputeHandler {


    private ExactComputeHandler exactComputeHandler;

    // This is more like ratio pay
    // 2 1 1 1 -> 1200
    // 2/5*1200 1/5*1200 1/5*1200 1/5*1200
    // This is subset of exact Pay Where Values are computed based on share

    @Override
    public void compute(SplitwiseAddRequest request, HashMap<String, Double> balancesList) {
        List<Double> values = request.getValues();
        // Modify the values and call Exact handler
        Double total = values.stream().mapToDouble(Double::doubleValue).sum();

        values = values.stream()
                .map(value -> value * request.getAmount().getValue() / total)
                .collect(Collectors.toList());
        request.setValues(values);
        exactComputeHandler.compute(request, balancesList);

    }


}
