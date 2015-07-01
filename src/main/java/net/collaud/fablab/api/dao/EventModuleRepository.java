package net.collaud.fablab.api.dao;

import java.util.List;
import net.collaud.fablab.api.data.EventModuleEO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
/**
 *This is the DAO interface for a <tt>EventModule</tt>.
 * @author Fabien Vuilleumier
 */
public interface EventModuleRepository extends JpaRepository<EventModuleEO, Integer>{

}
