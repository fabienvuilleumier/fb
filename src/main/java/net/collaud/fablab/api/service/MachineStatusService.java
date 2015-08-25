package net.collaud.fablab.api.service;

import net.collaud.fablab.api.data.MachineStatusEO;
import net.collaud.fablab.api.service.global.ReadWriteService;
import net.collaud.fablab.api.service.global.SoftRemoveService;
/**
 *This is the Service interface for a <tt>MachineStatus</tt>.
* @author Fabien Vuilleumier
*/
public interface MachineStatusService extends ReadWriteService<MachineStatusEO>, SoftRemoveService<MachineStatusEO> {

}
