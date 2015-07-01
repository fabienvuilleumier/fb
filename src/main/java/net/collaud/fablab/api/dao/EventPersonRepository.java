package net.collaud.fablab.api.dao;

import java.util.List;
import net.collaud.fablab.api.data.EventPersonEO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
/**
 *This is the DAO interface for a <tt>EventPerson</tt>.
 * @author Fabien Vuilleumier
 */
public interface EventPersonRepository extends JpaRepository<EventPersonEO, Integer>{

}
