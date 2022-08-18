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

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PercentComputeHandler implements ComputeHandler {
    private UserServiceImpl userService;


    @Override
    public void compute(SplitwiseAddRequest request, HashMap<String, Double> balancesList) {
        List<User> participants = userService.fetchUserByIds(request.getParticipants());
        compute(participants, request.getRequestAdderId(), request.getAmount().getValue(), request.getValues(), request.getParticipants(), balancesList);

    }

    private void compute(List<User> participants, String requesterId,
                         Double totalValue, List<Double> values, List<String> requestParticipants, HashMap<String, Double> balancesList) {

        int percentCount = 0;
        //Based on the share each of them will have a value and then it is the almost exact
        // For the rest of the users also the same has to be updated
        for (User participant : participants) {
            Double valueSpent = values.get(percentCount) / 100 * totalValue;
            String userId = requestParticipants.get(percentCount);
            percentCount++;

            if (userId.equals(requesterId)) {
                Double newBalance = computeRequestorBalance(participant.getTotalBalance(), totalValue - valueSpent);
                participant.setTotalBalance(newBalance);
                balancesList.put(participant.getUserId(), newBalance);

            } else {
                Double newBalance = computeParticipantsBalance(participant.getTotalBalance(), valueSpent);

                participant.setTotalBalance(newBalance);
                balancesList.put(participant.getUserId(), newBalance);
            }
        }
    }

    private Double computeParticipantsBalance(Double totalbalance, Double equalShare) {
        return (totalbalance != null ? totalbalance : 0.0) + -1 * equalShare;
    }

    private Double computeRequestorBalance(Double balance, Double equalShare) {
        return (balance != null ? balance : 0.0) + equalShare;
    }

}
