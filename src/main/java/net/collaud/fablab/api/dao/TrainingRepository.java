package net.collaud.fablab.api.dao;

import java.util.List;
import net.collaud.fablab.api.data.TrainingEO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * This is the DAO interface for a <tt>Training</tt>.
 *
 * @author Fabien Vuilleumier
 */
public interface TrainingRepository extends JpaRepository<TrainingEO, Integer> {

    @Query("SELECT t "
            + " FROM TrainingEO t  "
            + " LEFT JOIN FETCH t.trainingLevel  "
            + " LEFT JOIN FETCH t.machineType  ")
    @Override
    List<TrainingEO> findAll();

    @Query("SELECT t "
            + " FROM TrainingEO t "
            + " LEFT JOIN FETCH t.trainingLevel "
            + " LEFT JOIN FETCH t.machineType "
            + " WHERE t.id=:id")
    Optional<TrainingEO> findOneDetails(@Param("id") Integer id);

    @Query("SELECT t "
            + " FROM TrainingEO t"
            + " LEFT JOIN FETCH t.trainingLevel "
            + " LEFT JOIN FETCH t.machineType "
            + " WHERE UPPER(t.name)=UPPER(:name)")
    TrainingEO getId(@Param("name") String name);
}
