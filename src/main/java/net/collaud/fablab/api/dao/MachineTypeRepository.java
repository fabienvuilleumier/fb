package net.collaud.fablab.api.dao;

import net.collaud.fablab.api.data.MachineTypeEO;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
public interface MachineTypeRepository extends JpaRepository<MachineTypeEO, Integer>{
}
