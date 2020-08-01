package gamma.service;

import gamma.model.entity.Account;
import gamma.model.service.AccountServiceModel;
import java.util.*;

import java.util.Arrays;

public interface AccountService {
    AccountServiceModel addAccount(AccountServiceModel map);
    List<AccountServiceModel> findAll();
    List<String> name();
    public boolean existsAccount(String accountNumber);
}