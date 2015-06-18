package net.collaud.fablab.api.dao;

import java.util.List;
import net.collaud.fablab.api.data.PurchaseEO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * This is the DAO interface for a <tt>Purchase</tt>.
 *
 * @author Fabien Vuilleumier
 */
public interface PurchaseRepository extends JpaRepository<PurchaseEO, Integer> {

    @Query("SELECT p "
            + " FROM PurchaseEO p  "
            + " LEFT JOIN FETCH p.supply sup "
            + " LEFT JOIN FETCH sup.supplyType "
            + " LEFT JOIN FETCH sup.supplyUnity "
            + " LEFT JOIN FETCH p.user ")
    @Override
    List<PurchaseEO> findAll();

    @Query("SELECT p "
            + " FROM PurchaseEO p "
            + " LEFT JOIN FETCH p.supply sup "
            + " LEFT JOIN FETCH sup.supplyType "
            + " LEFT JOIN FETCH sup.supplyUnity "
            + " LEFT JOIN FETCH p.user "
            + " WHERE p.id=:id")
    Optional<PurchaseEO> findOneDetails(@Param("id") Integer id);
}
