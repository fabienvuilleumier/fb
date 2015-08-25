package net.collaud.fablab.api.dao;

import net.collaud.fablab.api.data.MachineStateEO;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 *This is the DAO interface for a <tt>MachineState</tt>.
 * @author Fabien Vuilleumier
 */
public interface MachineStateRepository extends JpaRepository<MachineStateEO, Integer>{

}
