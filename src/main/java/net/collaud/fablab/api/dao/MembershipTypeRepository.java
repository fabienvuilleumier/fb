package net.collaud.fablab.api.dao;

import net.collaud.fablab.api.data.MembershipTypeEO;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 *This is the DAO interface for a <tt>MembershipType</tt>.
 * @author Fabien Vuilleumier
 */
public interface MembershipTypeRepository extends JpaRepository<MembershipTypeEO, Integer>{

}
