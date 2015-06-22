package net.collaud.fablab.api.dao;

import java.util.List;
import net.collaud.fablab.api.data.PriceMachineEO;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * This is the DAO interface for a <tt>PriceMachine</tt>.
 *
 * @author Fabien Vuilleumier
 */
public interface PriceMachineRepository extends JpaRepository<PriceMachineEO, Integer> {

    @Query("SELECT p "
            + " FROM PriceMachineEO p  "
            + " LEFT JOIN FETCH p.machineType "
            + " LEFT JOIN FETCH p.membershipType ")
    @Override
    List<PriceMachineEO> findAll();

    @Query("SELECT p "
            + " FROM PriceMachineEO p "
            + " LEFT JOIN FETCH p.machineType "
            + " LEFT JOIN FETCH p.membershipType "
            + " WHERE p.id=:id")
    Optional<PriceMachineEO> findOneDetails(@Param("id") Integer id);

    @Query("SELECT p "
            + " FROM PriceMachineEO p "
            + " LEFT JOIN FETCH p.machineType mt "
            + " LEFT JOIN FETCH p.membershipType mst "
            + " WHERE mt.id = :machineTypeId AND "
            + " mst.id = :membershipTypeId")
    PriceMachineEO getPriceMachine(@Param("machineTypeId") Integer machineTypeId,
            @Param("membershipTypeId") Integer membershipTypeId);

    @Query("SELECT p "
            + " FROM PriceMachineEO p "
            + " LEFT JOIN FETCH p.membershipType mst "
            + " WHERE mst.id = :membershipTypeId")
    List<PriceMachineEO> getMembershipType(@Param("membershipTypeId") Integer membershipTypeId);

    @Query("SELECT p "
            + " FROM PriceMachineEO p "
            + " LEFT JOIN FETCH p.machineType mt "
            + " WHERE mt.id = :machineTypeId")
    List<PriceMachineEO> getMachineType(@Param("machineTypeId") Integer machineTypeId);
}
