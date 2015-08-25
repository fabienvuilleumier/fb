package net.collaud.fablab.api.service;

import net.collaud.fablab.api.data.RevisionEO;
import net.collaud.fablab.api.service.global.ReadWriteService;
import net.collaud.fablab.api.service.global.SoftRemoveService;
/**
 *This is the Service interface for a <tt>Revision</tt>.
* @author Fabien Vuilleumier
*/
public interface RevisionService extends ReadWriteService<RevisionEO>, SoftRemoveService<RevisionEO> {

}
