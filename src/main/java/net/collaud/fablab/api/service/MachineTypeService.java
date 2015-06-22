package net.collaud.fablab.api.service;

import java.util.List;
import net.collaud.fablab.api.data.MachineTypeEO;
import net.collaud.fablab.api.data.PriceMachineEO;
import net.collaud.fablab.api.service.global.ReadWriteService;

/**
 * This is the Service interface for a <tt>MachineType</tt>.
 *
 * @author Fabien Vuilleumier
 */
public interface MachineTypeService extends ReadWriteService<MachineTypeEO> {

    public List<PriceMachineEO> getPrices(Integer id);

    MachineTypeEO getId(String technicalname);

}
