package net.collaud.fablab.api.service;

import net.collaud.fablab.api.data.MachineStateEO;
import net.collaud.fablab.api.service.global.ReadWriteService;
import net.collaud.fablab.api.service.global.SoftRemoveService;

/**
 * This is the Service interface for a <tt>MachineState</tt>.
 *
 * @author Fabien Vuilleumier
 */
public interface MachineStateService extends ReadWriteService<MachineStateEO>, SoftRemoveService<MachineStateEO>  {

}
