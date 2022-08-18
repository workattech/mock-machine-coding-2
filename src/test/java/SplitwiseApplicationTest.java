import com.google.common.collect.Lists;
import impl.SplitwiseInterfaceImpl;
import impl.UserServiceImpl;
import models.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class SplitwiseApplicationTest {


    private UserServiceImpl userServiceImpl;

    private SplitwiseInterfaceImpl splitImpl;

    @Test
    public void testAddUser() {
        userServiceImpl = new UserServiceImpl();
        splitImpl = new SplitwiseInterfaceImpl(userServiceImpl);

        User user1 = User.builder()
                .userId("u1")
                .name("Nimesh")
                .email("nmred@flipkart.com")
                .phoneNumber("8888899999")
                .build();

        User user2 = User.builder()
                .userId("u2")
                .name("Nimesh")
                .email("nmreda@flipkart.com")
                .phoneNumber("8888899999")
                .build();

        User user3 = User.builder()
                .userId("u3")
                .name("Nimesh")
                .email("nmredb@flipkart.com")
                .phoneNumber("8888899999")
                .build();

        User user4 = User.builder()
                .userId("u4")
                .name("Mekala")
                .email("nmredc@flipkart.com")
                .phoneNumber("8888899999")
                .build();

        User user5 = User.builder()
                .userId("u5")
                .name("Reddy")
                .email("nmredd@flipkart.com")
                .phoneNumber("8888899999")
                .build();

        AddUserResponse response1 = userServiceImpl.addUser(user1);
        Assert.assertEquals(response1.getStatus(), Constants.SUCCESS_CODE);

        AddUserResponse response2 = userServiceImpl.addUser(user2);
        Assert.assertEquals(response2.getStatus(), Constants.SUCCESS_CODE);

        AddUserResponse response3 = userServiceImpl.addUser(user3);
        Assert.assertEquals(response3.getStatus(), Constants.SUCCESS_CODE);

        AddUserResponse response4 = userServiceImpl.addUser(user4);
        Assert.assertEquals(response4.getStatus(), Constants.SUCCESS_CODE);

        AddUserResponse response5 = userServiceImpl.addUser(user5);
        Assert.assertEquals(response5.getStatus(), Constants.SUCCESS_CODE);

        List<User> userList = userServiceImpl.fetchAllUsers();
        Assert.assertEquals(userList.size(), 5);


        testAddEqualSplitRequest();
    }

    private void testAddEqualSplitRequest() {

        // SHOW
        FetchBalanceResponse response = splitImpl.allBalances();
        Assert.assertEquals(response.getBalanceList().size(), 0);


        //SHOW U1
        response = splitImpl.fetchBalanceByUser("u1");
        Assert.assertEquals(response.getBalanceList().size(), 0);

        //EXPENSE u1 1000 4 u1 u2 u3 u4 EQUAL
        // Add Expense
        SplitwiseAddRequest request = SplitwiseAddRequest.builder()
                .expenseName("Grocery expense")
                .requestAdderId("u1")
                .amount(Amount.builder().value(1000.00).build())
                .participants(Lists.newArrayList("u1", "u2", "u3", "u4"))
                .expenseType(ExpenseType.EQUAL)
                .build();

        SplitwiseAddResponse splitwiseAddResponse = splitImpl.addSplitwiseTransaction(request);
        Assert.assertEquals(splitwiseAddResponse.getStatus(), Constants.SUCCESS_CODE);
        Assert.assertNull(splitwiseAddResponse.getErrorList());
        Assert.assertNotNull(splitwiseAddResponse.getOverAllNetWithEachUser());
        Assert.assertEquals(splitwiseAddResponse.getOverAllNetWithEachUser().get("u1"), Double.valueOf(750.00));
        Assert.assertEquals(splitwiseAddResponse.getOverAllNetWithEachUser().get("u2"), Double.valueOf(-250.00));
        Assert.assertEquals(splitwiseAddResponse.getOverAllNetWithEachUser().get("u3"), Double.valueOf(-250.00));
        Assert.assertEquals(splitwiseAddResponse.getOverAllNetWithEachUser().get("u4"), Double.valueOf(-250.00));
        Assert.assertEquals(splitwiseAddResponse.getBalances().size(), 3);
        print(splitwiseAddResponse.getBalances());

        fetchBalancesPerUser();
    }

    private void fetchBalancesPerUser() {
        User user = userServiceImpl.fetchUserById("u2");
        Assert.assertNotNull(user.getBalanceList());
        Assert.assertNotNull(user.getBalanceList().get(0));
        Assert.assertEquals(user.getBalanceList().size(), 1);
        Assert.assertEquals(user.getBalanceList().get(0).getBalance(), Double.valueOf(-250.00));
        Assert.assertEquals(user.getBalanceList().get(0).getFrom(), "u2");
        Assert.assertEquals(user.getBalanceList().get(0).getTo(), "u1");

        user = userServiceImpl.fetchUserById("u3");
        Assert.assertNotNull(user.getBalanceList());
        Assert.assertNotNull(user.getBalanceList().get(0));
        Assert.assertEquals(user.getBalanceList().size(), 1);
        Assert.assertEquals(user.getBalanceList().get(0).getBalance(), Double.valueOf(-250.00));
        Assert.assertEquals(user.getBalanceList().get(0).getFrom(), "u3");
        Assert.assertEquals(user.getBalanceList().get(0).getTo(), "u1");

        user = userServiceImpl.fetchUserById("u4");
        Assert.assertNotNull(user.getBalanceList());
        Assert.assertNotNull(user.getBalanceList().get(0));
        Assert.assertEquals(user.getBalanceList().size(), 1);
        Assert.assertEquals(user.getBalanceList().get(0).getBalance(), Double.valueOf(-250.00));
        Assert.assertEquals(user.getBalanceList().get(0).getFrom(), "u4");
        Assert.assertEquals(user.getBalanceList().get(0).getTo(), "u1");
        print(user.getBalanceList());

         user = userServiceImpl.fetchUserById("u1");
        Assert.assertNull(user.getBalanceList());
        print(user.getBalanceList());

        testSplitAddExactRequest();
    }

    private void testSplitAddExactRequest() {
        //EXPENSE u1 1250 2 u2 u3 EXACT 370 880
        // Add Expense
        SplitwiseAddRequest request = SplitwiseAddRequest.builder()
                .requestAdderId("u1")
                .amount(Amount.builder().value(1250.00).build())
                .participants(Lists.newArrayList("u2", "u3"))
                .values(Lists.newArrayList(370D, 880D))
                .expenseType(ExpenseType.EXACT)
                .build();
        SplitwiseAddResponse splitwiseAddResponse = splitImpl.addSplitwiseTransaction(request);

        Assert.assertEquals(splitwiseAddResponse.getStatus(), Constants.SUCCESS_CODE);
        Assert.assertNull(splitwiseAddResponse.getErrorList());
        Assert.assertNotNull(splitwiseAddResponse.getOverAllNetWithEachUser());
        Assert.assertEquals(splitwiseAddResponse.getOverAllNetWithEachUser().get("u1"), Double.valueOf(2000));
        Assert.assertEquals(splitwiseAddResponse.getOverAllNetWithEachUser().get("u2"), Double.valueOf(-620.00));
        Assert.assertEquals(splitwiseAddResponse.getOverAllNetWithEachUser().get("u3"), Double.valueOf(-1130.00));
        Assert.assertEquals(splitwiseAddResponse.getOverAllNetWithEachUser().get("u4"), Double.valueOf(-250.00));
        Assert.assertEquals(splitwiseAddResponse.getBalances().size(), 3);
        print(splitwiseAddResponse.getBalances());
        testSplitPercentageWise();
    }

    private void testSplitPercentageWise() {
        //EXPENSE u4 1200 4 u1 u2 u3 u4 PERCENT 40 20 20 20
        // Add Expense
        SplitwiseAddRequest request = SplitwiseAddRequest.builder()
                .requestAdderId("u4")
                .amount(Amount.builder().value(1200.00).build())
                .participants(Lists.newArrayList("u1", "u2", "u3", "u4"))
                .values(Lists.newArrayList(40D, 20D, 20D, 20D))
                .expenseType(ExpenseType.PERCENT)
                .build();

        SplitwiseAddResponse splitwiseAddResponse = splitImpl.addSplitwiseTransaction(request);

        Assert.assertEquals(splitwiseAddResponse.getStatus(), Constants.SUCCESS_CODE);
        Assert.assertNull(splitwiseAddResponse.getErrorList());
        Assert.assertNotNull(splitwiseAddResponse.getOverAllNetWithEachUser());
        Assert.assertEquals(splitwiseAddResponse.getOverAllNetWithEachUser().get("u1"), Double.valueOf(1520));
        Assert.assertEquals(splitwiseAddResponse.getOverAllNetWithEachUser().get("u2"), Double.valueOf(-860));
        Assert.assertEquals(splitwiseAddResponse.getOverAllNetWithEachUser().get("u3"), Double.valueOf(-1370.00));
        Assert.assertEquals(splitwiseAddResponse.getOverAllNetWithEachUser().get("u4"), Double.valueOf(710));
        Assert.assertEquals(splitwiseAddResponse.getBalances().size(), 3);
        print(splitwiseAddResponse.getBalances());

        testShareSplitHandler();
        printPassBook();
    }

    private void testShareSplitHandler() {
        // Add Expense
        //u4 1200 4 u1 u2 u3 u4 SHARE 2 1 1 1

        SplitwiseAddRequest request = SplitwiseAddRequest.builder()
                .requestAdderId("u4")
                .amount(Amount.builder().value(1200.00).build())
                .participants(Lists.newArrayList("u1", "u2", "u3", "u4"))
                .values(Lists.newArrayList(2D, 1D, 1D, 1D))
                .expenseType(ExpenseType.SHARE)
                .build();

        SplitwiseAddResponse splitwiseAddResponse = splitImpl.addSplitwiseTransaction(request);

        Assert.assertEquals(splitwiseAddResponse.getStatus(), Constants.SUCCESS_CODE);
        Assert.assertNull(splitwiseAddResponse.getErrorList());
        Assert.assertNotNull(splitwiseAddResponse.getOverAllNetWithEachUser());
        Assert.assertEquals(splitwiseAddResponse.getOverAllNetWithEachUser().get("u1"), Double.valueOf(1040));
        Assert.assertEquals(splitwiseAddResponse.getOverAllNetWithEachUser().get("u2"), Double.valueOf(-1100));
        Assert.assertEquals(splitwiseAddResponse.getOverAllNetWithEachUser().get("u3"), Double.valueOf(-1610));
        Assert.assertEquals(splitwiseAddResponse.getOverAllNetWithEachUser().get("u4"), Double.valueOf(1670));
        Assert.assertEquals(splitwiseAddResponse.getBalances().size(), 3);
        print(splitwiseAddResponse.getBalances());

    }

    private void printPassBook() {
        List<User> users = userServiceImpl.fetchAllUsers();

        users.forEach(user -> userServiceImpl.printPassBook(user));
    }

    private void print(List<Balance> balances) {
        System.out.println("========");
        if (balances != null) {
            balances.forEach(
                    balance -> System.out.println("User " + balance.getFrom() + " owes balance " + balance.getBalance() + " to User " + balance.getTo()));
        }
        System.out.println("========");
    }

}
