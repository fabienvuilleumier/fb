package net.collaud.fablab.api.service;

import java.util.List;
import net.collaud.fablab.api.data.MachineEO;
import net.collaud.fablab.api.service.global.ReadWriteService;
import net.collaud.fablab.api.service.global.SoftRemoveService;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
public interface MachineService extends ReadWriteService<MachineEO>, SoftRemoveService<MachineEO> {

    List<MachineEO> getByStatusLabel(String Label);

    MachineEO findSimpleByCode(String code);
}
