package gamma.service.impl;

import gamma.Repository.AccountRepository;
import gamma.model.entity.Account;
import gamma.model.service.AccountServiceModel;
import gamma.service.AccountService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {

    private AccountService serviceToTest;
    private ModelMapper modelMapperToTest;
    private Account account;

    @Mock
    AccountRepository mockAccountRepository;


    @BeforeEach
    public void setUp()
    {
        serviceToTest = new AccountServiceImpl(modelMapperToTest,mockAccountRepository);
        account = new Account();
        account.setId("SOME_UUID");
        account.setName("Test account");
        account.setAccountNumber("999");
        account.setActivePassive("ACTIVE");
        account.setBalanceIncome("BALANCE");
        account.setCredit(BigDecimal.valueOf(0));
        account.setDebit(BigDecimal.valueOf(0));

    }


    @Test
    void addAccountTest() {
        AccountServiceModel accountServiceModel = new AccountServiceModel();
        accountServiceModel.setBalance(account.getDebit().subtract(account.getCredit()));
        accountServiceModel.setAccountNumber(account.getAccountNumber());
        accountServiceModel.setActivePassive(account.getActivePassive());
        accountServiceModel.setBalanceIncome(account.getBalanceIncome());
        accountServiceModel.setCredit(account.getCredit());
        accountServiceModel.setDebit(account.getDebit());
        accountServiceModel.setName(account.getName());

        when(mockAccountRepository.saveAndFlush(Mockito.any(Account.class))).
               thenAnswer(invocationOnMock -> invocationOnMock.getArguments()[0]);

        AccountServiceModel accountServiceModel1 = serviceToTest.addAccount(accountServiceModel);
        Assertions.assertEquals("Test account", accountServiceModel1.getName());

    }

    @Test
    void findAllTest() {

        when(mockAccountRepository.findAll()).
                thenReturn(List.of(account));
        List<AccountServiceModel> all = serviceToTest.findAll();
        Assertions.assertEquals(1, all.size());
        AccountServiceModel actualAccountServiceModel = all.get(0);
        Assertions.assertEquals(account.getAccountNumber(), actualAccountServiceModel.getAccountNumber());
        Assertions.assertEquals(account.getName(), actualAccountServiceModel.getName());
    }

    @Test
    void nameTest() {

        when(mockAccountRepository.accountsName()).
                thenReturn(List.of(account.getName()));
        List<String> name = serviceToTest.name();
        Assertions.assertEquals(account.getName(), name.get(0));
    }


    @Test
    void existsAccountTest() {
        Account account = new Account();
        account.setName("Test account");
        account.setAccountNumber("999");
        account.setActivePassive("ACTIVE");
        account.setBalanceIncome("BALANCE");
        account.setCredit(BigDecimal.valueOf(0));
        account.setDebit(BigDecimal.valueOf(0));

        when(mockAccountRepository.findByAccountNumber("999")).
                thenReturn(Optional.of(account));

        boolean isTrue = true;
        boolean actualIsTrue = serviceToTest.existsAccount("999");
        Assertions.assertEquals(isTrue, actualIsTrue);
    }
}