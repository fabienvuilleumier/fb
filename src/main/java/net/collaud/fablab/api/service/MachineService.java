package net.collaud.fablab.api.service;

import java.util.List;
import net.collaud.fablab.api.data.MachineEO;
import net.collaud.fablab.api.data.MachineStatusEO;
import net.collaud.fablab.api.service.global.ReadWriteService;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
public interface MachineService extends ReadWriteService<MachineEO> {

    List<MachineEO> getByStatusLabel(String Label);

    MachineEO findSimpleByCode(String code);
    
    MachineStatusEO saveStatus(Integer machineId, Integer machineStatusId);
}
