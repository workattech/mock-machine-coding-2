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
public class EqualComputeHandler implements ComputeHandler {

    private UserServiceImpl userService;

    @Override
    public void compute(SplitwiseAddRequest request, HashMap<String, Double> balancesList) {
        List<User> participants = userService.fetchUserByIds(request.getParticipants());
        compute(participants, request.getRequestAdderId(), request.getAmount().getValue(), balancesList);
    }


    private void compute(List<User> participants, String requesterId, Double totalValue, HashMap<String, Double> balancesList) {

        Double equalShare = totalValue / participants.size();
        // For the rest of the users also the same has to be updated
        participants
                .forEach(
                        participant -> {
                            if (!participant.getUserId().equals(requesterId)) {
                                Double newBalance = computeParticipantsBalance(participant.getTotalBalance(), equalShare);
                                participant.setTotalBalance(newBalance);
                                balancesList.put(participant.getUserId(), newBalance);
                            } else {
                                Double updatedRequestorBalance = computeRequestorBalance(participant.getTotalBalance(), equalShare, participants.size());
                                participant.setTotalBalance(updatedRequestorBalance);
                                balancesList.put(participant.getUserId(), updatedRequestorBalance);
                            }
                        }
                );

    }

    private Double computeRequestorBalance(Double balance, Double equalShare, Integer totalNumberOfParticipants) {
        return (balance != null ? balance : 0.0) + equalShare * (totalNumberOfParticipants - 1);
    }

    private Double computeParticipantsBalance(Double totalbalance, Double equalShare) {
        return (totalbalance != null ? totalbalance : 0.0) + -1 * equalShare;
    }
}
