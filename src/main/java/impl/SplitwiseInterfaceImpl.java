package impl;

import handlers.EqualComputeHandler;
import handlers.ExactComputeHandler;
import handlers.PercentComputeHandler;
import handlers.ShareComputeHandler;
import interfaces.ComputeHandler;
import interfaces.SplitwiseInterface;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import models.*;
import models.Error;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class SplitwiseInterfaceImpl implements SplitwiseInterface {

    private static HashMap<String, Double> balancesList;

    private UserServiceImpl userService;

    @Override
    public SplitwiseAddResponse addSplitwiseTransaction(SplitwiseAddRequest request) {

        if (balancesList == null) {
            balancesList = new HashMap<>();
        }

        List<User> participants = userService.fetchUserByIds(request.getParticipants());
        if (participants.size() != request.getParticipants().size()) {
            return userNotFoundWhileAddingResponse();
        }

        fetchComputeHandlerByExpenseType(request.getExpenseType()).compute(request,balancesList);
        return splitAddSuccessResponse();
    }

    private ComputeHandler fetchComputeHandlerByExpenseType(ExpenseType expenseType) {
        if (expenseType == ExpenseType.EQUAL) {
            return new EqualComputeHandler(userService);
        } else if (expenseType == ExpenseType.EXACT) {
            return new ExactComputeHandler(userService);
        } else if (expenseType == ExpenseType.PERCENT) {
            return new PercentComputeHandler(userService);
        } else if (expenseType == ExpenseType.SHARE) {
            return new ShareComputeHandler(new ExactComputeHandler(userService));
        }
        return null;
    }

    @Override
    public FetchBalanceResponse fetchBalanceByUser(String userId) {
        if (balancesList != null && balancesList.containsKey(userId)) {
            return FetchBalanceResponse.builder()
                    .balanceList(null)
                    .build();
        }
        System.out.println("No Balances");
        return noBalanceResponse();
    }

    @Override
    public FetchBalanceResponse allBalances() {
        if (balancesList != null) {
            List<Balance> balances = reconcileAndReturn();
            if (balances.size() == 0) {
                System.out.println("No Balances");
            }
            return FetchBalanceResponse.builder()
                    .balanceList(balances)
                    .build();
        }
        System.out.println("No Balances");
        return noBalanceResponse();
    }

    private FetchBalanceResponse noBalanceResponse() {
        List<Error> errors = new ArrayList<>();
        errors.add(Error.builder().message("No Balances Found").build());
        return FetchBalanceResponse.builder()
                .balanceList(new ArrayList<>())
                .errors(errors)
                .build();
    }

    private SplitwiseAddResponse splitAddSuccessResponse() {


        return SplitwiseAddResponse.builder()
                .status(0)
                .overAllNetWithEachUser(balancesList)
                .balances(reconcileAndReturn())
                .message("SPLITWISE TRANSACTION ADDED SUCCESSFULLY")
                .build();
    }

    private SplitwiseAddResponse userNotFoundWhileAddingResponse() {
        return SplitwiseAddResponse.builder()
                .status(1)
                .message("user Not Found While Adding Split")
                .build();
    }


    // Smart Reconciliation
    private List<Balance> reconcileAndReturn() {
        List<Balance> reconciledBalances = new ArrayList<>();
        // We know the net worth of each
        // Pick the max Positive
        // Pick the max Negative
        // Separate positive into one bucket and negative into other
        List<User> positive = new ArrayList<>();
        List<User> negative = new ArrayList<>();

        List<User> allUsers = userService.fetchAllUsers();

        Double totalPositiveSum = 0D;
        for (User allUser : allUsers) {
            if (allUser.getTotalBalance() != null && allUser.getTotalBalance() > 0) {
                positive.add(allUser);
                totalPositiveSum = totalPositiveSum + allUser.getTotalBalance();
            } else if (allUser.getTotalBalance() != null && allUser.getTotalBalance() < 0) {
                negative.add(allUser);
            }
            allUser.setTempBalance(allUser.getTotalBalance());
        }

        int i = 0;
        int j = 0;


        Double remainingBalance = 0.0;
        while (totalPositiveSum != 0D) {
            // Pick each Negative
            User userN = negative.get(i);
            User userP = positive.get(j);

            Balance balance = null;

            if (Math.abs(userN.getTempBalance()) >= userP.getTempBalance()) {
                j++;
                balance = Balance.builder()
                        .from(userN.getUserId())
                        .to(userP.getUserId())
                        .balance(userP.getTempBalance() * -1)
                        .build();
                userN.setTempBalance(userP.getTempBalance() + userN.getTempBalance());

            } else if (userP.getTempBalance() > Math.abs(userN.getTempBalance())) {
                i++;
                balance = Balance.builder()
                        .from(userN.getUserId())
                        .to(userP.getUserId())
                        .balance(userN.getTotalBalance())
                        .build();
                userP.setTempBalance(userP.getTotalBalance() + userN.getTotalBalance());
            }
            reconciledBalances.add(balance);
            updateBalancesToAUser(balance, userN);
            totalPositiveSum += balance.getBalance();
        }
        return reconciledBalances;
    }

    private void updateBalancesToAUser(Balance balance, User user) {
        List<Balance> balancesList = user.getBalanceList();
        if (balancesList == null) {
            balancesList = new ArrayList<>();
        }
        balancesList.add(balance);
        user.setBalanceList(balancesList);
    }


}
