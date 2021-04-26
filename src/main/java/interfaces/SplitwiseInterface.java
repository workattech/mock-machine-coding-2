package interfaces;

import models.FetchBalanceResponse;
import models.SplitwiseAddRequest;
import models.SplitwiseAddResponse;


public interface SplitwiseInterface {

    SplitwiseAddResponse addSplitwiseTransaction(SplitwiseAddRequest request);

    FetchBalanceResponse fetchBalanceByUser(String userId);

    FetchBalanceResponse allBalances();


}
