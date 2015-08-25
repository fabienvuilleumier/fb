package net.collaud.fablab.api.service;

import net.collaud.fablab.api.data.TicketStatusEO;
import net.collaud.fablab.api.service.global.ReadWriteService;
import net.collaud.fablab.api.service.global.SoftRemoveService;
/**
 *This is the Service interface for a <tt>TicketStatus</tt>.
* @author Fabien Vuilleumier
*/
public interface TicketStatusService extends ReadWriteService<TicketStatusEO>, SoftRemoveService<TicketStatusEO> {

    public TicketStatusEO findByLabel(String label);

}
