package net.collaud.fablab.api.dao;

import java.util.List;
import net.collaud.fablab.api.data.TrainingLevelEO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
/**
 *This is the DAO interface for a <tt>TrainingLevel</tt>.
 * @author Fabien Vuilleumier
 */
public interface TrainingLevelRepository extends JpaRepository<TrainingLevelEO, Integer>{

}
