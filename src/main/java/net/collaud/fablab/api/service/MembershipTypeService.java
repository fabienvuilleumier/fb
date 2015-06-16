package net.collaud.fablab.api.service;

import net.collaud.fablab.api.data.MembershipTypeEO;
import net.collaud.fablab.api.service.global.ReadWriteService;
import net.collaud.fablab.api.service.global.SoftRemoveService;
/**
 *This is the Service interface for a <tt>MembershipType</tt>.
* @author Fabien Vuilleumier
*/
public interface MembershipTypeService extends ReadWriteService<MembershipTypeEO>, SoftRemoveService<MembershipTypeEO> {

}
