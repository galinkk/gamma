package gamma.Repository;

import gamma.model.entity.Customer;
import gamma.model.entity.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,String> {
}
