package gamma.Repository;

import gamma.model.entity.Debit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DebitRepository extends JpaRepository<Debit,String> {
}
