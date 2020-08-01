package gamma.Repository;

import gamma.model.entity.Contragent;
import net.minidev.json.JSONUtil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.w3c.dom.ls.LSOutput;

import java.util.*;

@Repository
public interface ContragentRepository extends JpaRepository<Contragent,String> {
    List<Contragent> findByType(String type);
    @Query(value = "select c.name from Contragent c where c.type = 'vendors' order by c.name ")
    List<String> vendor();
    @Query(value = "select c.name from Contragent c order by c.name")
    List<String> contragentsName();

    @Query(value = "select c.name from Contragent c where c.type = 'customer' order by c.name ")
    List<String> customer();

}
