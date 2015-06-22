package net.collaud.fablab.api.service;

import java.util.List;
import net.collaud.fablab.api.data.PriceMachineEO;
import net.collaud.fablab.api.service.global.ReadWriteService;
/**
 *This is the Service interface for a <tt>PriceMachine</tt>.
* @author Fabien Vuilleumier
*/
public interface PriceMachineService extends ReadWriteService<PriceMachineEO>{

    public PriceMachineEO getPriceMachine(Integer machineTypeId, Integer membershipTypeId);

    public List<PriceMachineEO> getMachineType(Integer machineTypeId);

    public List<PriceMachineEO> getMembershipType(Integer membershipTypeId);

}
