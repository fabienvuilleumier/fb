package net.collaud.fablab.api.dao;

import java.util.List;
import net.collaud.fablab.api.data.MotionStockEO;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
/**
 *This is the DAO interface for a <tt>MotionStock</tt>.
 * @author Fabien Vuilleumier
 */
public interface MotionStockRepository extends JpaRepository<MotionStockEO, Integer>{

    @Query("SELECT m "
        + " FROM MotionStockEO m  " 
        + " LEFT JOIN FETCH m.supply  " 
        + " LEFT JOIN FETCH m.user  " )
    @Override
    List<MotionStockEO> findAll();
    @Query("SELECT m "
        + " FROM MotionStockEO m "
        + " LEFT JOIN FETCH m.supply "
        + " LEFT JOIN FETCH m.user "
        + " WHERE m.id=:id")
    Optional<MotionStockEO> findOneDetails(@Param("id")Integer id);
}
