package net.collaud.fablab.api.dao;

import net.collaud.fablab.api.data.MachineStatusEO;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 *This is the DAO interface for a <tt>MachineStatus</tt>.
 * @author Fabien Vuilleumier
 */
public interface MachineStatusRepository extends JpaRepository<MachineStatusEO, Integer>{

}
