package net.collaud.fablab.api.service;

import net.collaud.fablab.api.data.MachineTypeEO;
import net.collaud.fablab.api.service.global.ReadWriteService;
import net.collaud.fablab.api.service.global.SoftRemoveService;

/**
 * This is the Service interface for a <tt>MachineType</tt>.
 *
 * @author Fabien Vuilleumier
 */
public interface MachineTypeService extends ReadWriteService<MachineTypeEO>, SoftRemoveService<MachineTypeEO>  {

}
