package net.collaud.fablab.api.service;

import net.collaud.fablab.api.data.SupplyTypeEO;
import net.collaud.fablab.api.service.global.ReadWriteService;
import net.collaud.fablab.api.service.global.SoftRemoveService;
/**
 *This is the Service interface for a <tt>SupplyType</tt>.
* @author Fabien Vuilleumier
*/
public interface SupplyTypeService extends ReadWriteService<SupplyTypeEO>, SoftRemoveService<SupplyTypeEO>{

}
