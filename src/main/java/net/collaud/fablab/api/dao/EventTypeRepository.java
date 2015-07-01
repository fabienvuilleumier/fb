package net.collaud.fablab.api.dao;

import java.util.List;
import net.collaud.fablab.api.data.EventTypeEO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
/**
 *This is the DAO interface for a <tt>EventType</tt>.
 * @author Fabien Vuilleumier
 */
public interface EventTypeRepository extends JpaRepository<EventTypeEO, Integer>{

}
