package net.collaud.fablab.api.service;

import net.collaud.fablab.api.data.PriceMachineEO;
import net.collaud.fablab.api.service.global.ReadWriteService;
/**
 *This is the Service interface for a <tt>PriceMachine</tt>.
* @author Fabien Vuilleumier
*/
public interface PriceMachineService extends ReadWriteService<PriceMachineEO>{

    public PriceMachineEO getPriceMachineType(Integer machineTypeId, Integer membershipTypeId);

}
