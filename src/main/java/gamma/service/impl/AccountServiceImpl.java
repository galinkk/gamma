package gamma.service.impl;

import gamma.Repository.AccountRepository;
import gamma.model.entity.Account;
import gamma.model.entity.Contragent;
import gamma.model.entity.Vendor;
import gamma.model.service.AccountServiceModel;
import gamma.model.service.ContragentServiceModel;
import gamma.service.AccountService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class AccountServiceImpl implements AccountService {
    private final ModelMapper modelMapper;
    private final AccountRepository accountRepository;

    public AccountServiceImpl(ModelMapper modelMapper, AccountRepository accountRepository) {
        this.modelMapper = modelMapper;
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountServiceModel addAccount(AccountServiceModel map) {

        Account account = new Account();
        account.setCredit(BigDecimal.valueOf(0));
        account.setDebit(BigDecimal.valueOf(0));
        account.setName(map.getName());
        account.setAccountNumber(map.getAccountNumber());
        account.setActivePassive(map.getActivePassive());
        account.setBalanceIncome(map.getBalanceIncome());
        this.accountRepository.saveAndFlush(account);
        return map;
    }

    @Override
    public List<AccountServiceModel> findAll() {
        List<Account> allAccountsEntity = accountRepository.findAll();
        List<AccountServiceModel> allAccounts = new ArrayList<>();

        for (int i = 0; i < allAccountsEntity.size(); i++) {
            Account account = allAccountsEntity.get(i);
            AccountServiceModel asm  = new AccountServiceModel();
            asm.setBalance(account.getDebit().subtract(account.getCredit()));
            asm.setAccountNumber(account.getAccountNumber());
            asm.setActivePassive(account.getActivePassive());
            asm.setBalanceIncome(account.getBalanceIncome());
            asm.setCredit(account.getCredit());
            asm.setDebit(account.getDebit());
            asm.setName(account.getName());
            allAccounts.add(asm);
        }
        return allAccounts;
    }

    @Override
    public List<String> name() {
        List<String> name = this.accountRepository.accountsName();
        return name;
    }

    @Override
    public boolean existsAccount(String accountNumber) {
        Objects.requireNonNull(accountNumber);
        return accountRepository.findByAccountNumber(accountNumber).isPresent();
    }


}
