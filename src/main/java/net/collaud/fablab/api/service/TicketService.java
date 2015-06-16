package net.collaud.fablab.api.service;

import net.collaud.fablab.api.data.TicketEO;
import net.collaud.fablab.api.service.global.ReadWriteService;
import net.collaud.fablab.api.service.global.SoftRemoveService;
/**
 *This is the Service interface for a <tt>Ticket</tt>.
* @author Fabien Vuilleumier
*/
public interface TicketService extends ReadWriteService<TicketEO>, SoftRemoveService<TicketEO> {

}
