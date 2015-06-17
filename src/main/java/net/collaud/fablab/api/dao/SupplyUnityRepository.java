package net.collaud.fablab.api.dao;

import java.util.List;
import net.collaud.fablab.api.data.SupplyUnityEO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
/**
 *This is the DAO interface for a <tt>SupplyUnity</tt>.
 * @author Fabien Vuilleumier
 */
public interface SupplyUnityRepository extends JpaRepository<SupplyUnityEO, Integer>{

}
