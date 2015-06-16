package net.collaud.fablab.api.dao;

import java.util.List;
import net.collaud.fablab.api.data.RevisionEO;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * This is the DAO interface for a <tt>Revision</tt>.
 *
 * @author Fabien Vuilleumier
 */
public interface RevisionRepository extends JpaRepository<RevisionEO, Integer> {

    @Query("SELECT r "
            + " FROM RevisionEO r "
            + " LEFT JOIN FETCH r.machine ")
    @Override
    List<RevisionEO> findAll();

    @Query("SELECT r "
            + " FROM RevisionEO r "
            + " LEFT JOIN FETCH r.machine "
            + " WHERE r.id=:id")
    Optional<RevisionEO> findOneDetails(@Param("id") Integer id);
}
