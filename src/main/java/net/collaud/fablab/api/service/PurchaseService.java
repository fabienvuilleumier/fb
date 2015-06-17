package net.collaud.fablab.api.service;

import net.collaud.fablab.api.data.PurchaseEO;
import net.collaud.fablab.api.service.global.ReadWriteService;
import net.collaud.fablab.api.service.global.SoftRemoveService;
/**
 *This is the Service interface for a <tt>Purchase</tt>.
* @author Fabien Vuilleumier
*/
public interface PurchaseService extends ReadWriteService<PurchaseEO>, SoftRemoveService<PurchaseEO>{

}
