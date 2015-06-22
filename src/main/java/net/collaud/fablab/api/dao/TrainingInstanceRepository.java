package net.collaud.fablab.api.dao;

import java.util.List;
import net.collaud.fablab.api.data.TrainingInstanceEO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
/**
 *This is the DAO interface for a <tt>TrainingInstance</tt>.
 * @author Fabien Vuilleumier
 */
public interface TrainingInstanceRepository extends JpaRepository<TrainingInstanceEO, Integer>{

    @Query("SELECT t "
        + " FROM TrainingInstanceEO t  " 
        + " LEFT JOIN FETCH t.training  " )
    @Override
    List<TrainingInstanceEO> findAll();
    @Query("SELECT t "
        + " FROM TrainingInstanceEO t "
        + " LEFT JOIN FETCH t.training "
        + " WHERE t.id=:id")
    Optional<TrainingInstanceEO> findOneDetails(@Param("id")Integer id);
}
