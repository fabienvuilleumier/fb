package net.collaud.fablab.api.service;

import net.collaud.fablab.api.data.SupplyUnityEO;
import net.collaud.fablab.api.service.global.ReadWriteService;
import net.collaud.fablab.api.service.global.SoftRemoveService;
/**
 *This is the Service interface for a <tt>SupplyUnity</tt>.
* @author Fabien Vuilleumier
*/
public interface SupplyUnityService extends ReadWriteService<SupplyUnityEO>, SoftRemoveService<SupplyUnityEO>{

}
