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

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExactComputeHandler implements ComputeHandler {

    private UserServiceImpl userService;


    @Override
    public void compute(SplitwiseAddRequest request, HashMap<String, Double> balancesList) {
        List<User> participants = userService.fetchUserByIds(request.getParticipants());
        compute(participants, request.getRequestAdderId(), request.getAmount().getValue(), request.getValues(), balancesList);
    }


    private void compute(List<User> participants, String requesterId,
                         Double totalValue, List<Double> values, HashMap<String, Double> balancesList) {

        int exactCountIndex = 0;
        for (User participant : participants) {
            Double newBalance = computeParticipantsBalance(participant.getTotalBalance(), values.get(exactCountIndex++));
            participant.setTotalBalance(newBalance);
            balancesList.put(participant.getUserId(), newBalance);
        }
        User requestor = userService.fetchUserById(requesterId);
        Double newBalance = computeRequestorBalance(requestor.getTotalBalance(), totalValue);
        requestor.setTotalBalance(newBalance);
        balancesList.put(requestor.getUserId(), newBalance);

    }

    private Double computeParticipantsBalance(Double totalbalance, Double equalShare) {
        return (totalbalance != null ? totalbalance : 0.0) + -1 * equalShare;
    }

    private Double computeRequestorBalance(Double balance, Double equalShare) {
        return (balance != null ? balance : 0.0) + equalShare;
    }
}
