package net.collaud.fablab.api.dao;

import java.util.List;
import net.collaud.fablab.api.data.CertificationEO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
/**
 *This is the DAO interface for a <tt>Certification</tt>.
 * @author Fabien Vuilleumier
 */
public interface CertificationRepository extends JpaRepository<CertificationEO, Integer>{

    @Query("SELECT c "
        + " FROM CertificationEO c  " 
        + " LEFT JOIN FETCH c.training  " )
    @Override
    List<CertificationEO> findAll();
    @Query("SELECT c "
        + " FROM CertificationEO c "
        + " LEFT JOIN FETCH c.training "
        + " WHERE c.id=:id")
    Optional<CertificationEO> findOneDetails(@Param("id")Integer id);
}
