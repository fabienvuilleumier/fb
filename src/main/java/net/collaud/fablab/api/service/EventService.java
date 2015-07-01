package net.collaud.fablab.api.service;

import net.collaud.fablab.api.data.EventEO;
import net.collaud.fablab.api.service.global.ReadWriteService;

/**
 * This is the Service interface for a <tt>Event</tt>.
 *
 * @author Fabien Vuilleumier
 */
public interface EventService extends ReadWriteService<EventEO> {

    EventEO getId(String title);
}
