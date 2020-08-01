package gamma.Repository;

import gamma.model.entity.Account;
import gamma.model.entity.User;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface AccountRepository extends JpaRepository<Account,String> {

   @Query(value = "select a.name from Account a order by a.name")
   List<String> accountsName();

   Optional<Account> findByAccountNumber(String accountNumber);


}
