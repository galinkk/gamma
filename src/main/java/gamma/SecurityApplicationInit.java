package gamma;


import gamma.Repository.AccountRepository;
import gamma.Repository.UserRepository;
import gamma.model.entity.Account;
import gamma.model.entity.AuthorityEntity;
import gamma.model.entity.Role;
import gamma.model.entity.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class SecurityApplicationInit implements CommandLineRunner {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final AccountRepository accountRepository;

  public SecurityApplicationInit(UserRepository userRepository,
                                 PasswordEncoder passwordEncoder, AccountRepository accountRepository) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.accountRepository = accountRepository;
  }

  @Override
  public void run(String... args) throws Exception {
    if (userRepository.count()==0) {
      User accounting = new User();
      accounting.setUsername("gosho");
      accounting.setPassword(passwordEncoder.encode("gosho"));
      accounting.setEnabled(true);
      accounting.setEmail("gosho@gmail.com");

      AuthorityEntity authorityEntity = new AuthorityEntity();
      authorityEntity.setName(Role.ACCOUNTING.name());

      authorityEntity.setUser(accounting);
      accounting.setAuthorities(List.of(authorityEntity));
      userRepository.save(accounting);

      User controling = new User();
      controling.setUsername("tosho");
      controling.setPassword(passwordEncoder.encode("tosho"));
      controling.setEnabled(true);
      controling.setEmail("tosho@gmail.com");

      AuthorityEntity controlingauthorityEntity = new AuthorityEntity();
      controlingauthorityEntity.setName(Role.ACCOUNTING_MANAGER.name());
      controlingauthorityEntity.setUser(controling);
      controling.setAuthorities(List.of(controlingauthorityEntity));

      userRepository.save(controling);

      User admin = new User();
      admin.setUsername("pesho");
      admin.setPassword(passwordEncoder.encode("pesho"));
      admin.setEnabled(true);
      admin.setEmail("pesho@gmail.com");

      AuthorityEntity adminUserAuthorityEntity = new AuthorityEntity();
      adminUserAuthorityEntity.setName(Role.ADMIN.name());
      adminUserAuthorityEntity.setUser(admin);

      AuthorityEntity adminAdminAuthorityEntity = new AuthorityEntity();
      adminAdminAuthorityEntity.setName(Role.ACCOUNTING_MANAGER.name());
      adminAdminAuthorityEntity.setUser(admin);

      admin.setAuthorities(List.of(adminUserAuthorityEntity, adminAdminAuthorityEntity));

      userRepository.save(admin);
    }

    if (accountRepository.count()==0) {

      Account vendor=new Account();
      vendor.setAccountNumber("401");
      vendor.setName("Задължения към доставчици");
      vendor.setBalanceIncome("BALANCE");
      vendor.setActivePassive("PASSIVE");
      vendor.setCredit(BigDecimal.valueOf(0));
      vendor.setDebit(BigDecimal.valueOf(0));

      this.accountRepository.save(vendor);

      Account customer=new Account();
      customer.setAccountNumber("411");
      customer.setName("Вземания от клиенти");
      customer.setBalanceIncome("BALANCE");
      customer.setActivePassive("ACTIVE");
      customer.setCredit(BigDecimal.valueOf(0));
      customer.setDebit(BigDecimal.valueOf(0));

      this.accountRepository.save(customer);


      Account bank=new Account();
      bank.setAccountNumber("503");
      bank.setName("Разплащателна сметка");
      bank.setBalanceIncome("BALANCE");
      bank.setActivePassive("ACTIVE");
      bank.setCredit(BigDecimal.valueOf(0));
      bank.setDebit(BigDecimal.valueOf(0));

      this.accountRepository.save(bank);
    }


  }
}
