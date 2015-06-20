package net.collaud.fablab.api.dao;

import java.util.List;
import net.collaud.fablab.api.data.TicketEO;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import net.collaud.fablab.api.data.RevisionEO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * This is the DAO interface for a <tt>Ticket</tt>.
 *
 * @author Fabien Vuilleumier
 */
public interface TicketRepository extends JpaRepository<TicketEO, Integer> {

    @Query("SELECT t "
            + " FROM TicketEO t  "
            + " LEFT JOIN FETCH t.machine  "
            + " LEFT JOIN FETCH t.status  "
            + " LEFT JOIN FETCH t.creationUser  "
            + " LEFT JOIN FETCH t.closeUser  ")
    @Override
    List<TicketEO> findAll();

    @Query("SELECT t "
            + " FROM TicketEO t "
            + " LEFT JOIN FETCH t.machine "
            + " LEFT JOIN FETCH t.status "
            + " LEFT JOIN FETCH t.creationUser "
            + " LEFT JOIN FETCH t.closeUser "
            + " WHERE t.id=:id")
    Optional<TicketEO> findOneDetails(@Param("id") Integer id);
    
        @Query(" SELECT t "
            + " FROM TicketEO t "
            + " LEFT JOIN FETCH t.machine m "
            + " LEFT JOIN FETCH t.status "
            + " WHERE m.id = :id ")
    List<TicketEO> listByMachine(@Param("id") Integer id);
}
