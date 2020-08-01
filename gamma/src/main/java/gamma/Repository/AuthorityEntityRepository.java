package gamma.Repository;

import gamma.model.entity.AuthorityEntity;
import gamma.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

import java.util.Optional;

@Repository
public interface AuthorityEntityRepository extends JpaRepository<AuthorityEntity,String> {
    Optional<AuthorityEntity> findByName(String name);
    List<AuthorityEntity> findAllByUser(User user);
}
