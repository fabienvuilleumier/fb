package net.collaud.fablab.api.dao;

import java.util.List;
import net.collaud.fablab.api.data.MachineTypeEO;
import net.collaud.fablab.api.data.PriceMachineEO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
public interface MachineTypeRepository extends JpaRepository<MachineTypeEO, Integer> {

    @Query("SELECT DISTINCT m "
            + " FROM MachineTypeEO m")
    @Override
    List<MachineTypeEO> findAll();

    @Query("SELECT p "
            + " FROM PriceMachineEO p "
            + " WHERE p.machineType.id = :id")
    List<PriceMachineEO> getPrices(@Param("id")Integer id);
}
