package gamma.Repository;

import gamma.model.entity.LedgerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface LedgerEntityRepository extends JpaRepository<LedgerEntity,String> {
    List<LedgerEntity> findAllByDocumentType(String type);
    Optional<LedgerEntity> findById(String id);
}
