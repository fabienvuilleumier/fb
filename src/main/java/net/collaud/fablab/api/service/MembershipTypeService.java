package net.collaud.fablab.api.service;

import java.util.List;
import net.collaud.fablab.api.data.MembershipTypeEO;
import net.collaud.fablab.api.data.PriceMachineEO;
import net.collaud.fablab.api.service.global.ReadWriteService;
/**
 *This is the Service interface for a <tt>MembershipType</tt>.
* @author Fabien Vuilleumier
*/
public interface MembershipTypeService extends ReadWriteService<MembershipTypeEO> {

    public List<PriceMachineEO> getPrices(Integer id);

}
