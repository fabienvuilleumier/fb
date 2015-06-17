package net.collaud.fablab.api.service;

import java.util.List;
import net.collaud.fablab.api.data.SupplyEO;
import net.collaud.fablab.api.service.global.ReadWriteService;
import net.collaud.fablab.api.service.global.SoftRemoveService;
/**
 *This is the Service interface for a <tt>Supply</tt>.
* @author Fabien Vuilleumier
*/
public interface SupplyService extends ReadWriteService<SupplyEO>, SoftRemoveService<SupplyEO>{

    List<SupplyEO> stock();

    void addQuantity(Integer id, Float quantity);

}
