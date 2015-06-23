package net.collaud.fablab.api.dao;

import net.collaud.fablab.api.data.RoleEO;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 *This is the DAO interface for a <tt>Role</tt>.
 * @author Fabien Vuilleumier
 */
public interface RoleRepository extends JpaRepository<RoleEO, Integer>{

}
