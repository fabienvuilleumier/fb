package net.collaud.fablab.api.service;

import java.util.List;
import net.collaud.fablab.api.data.EventPersonEO;
import net.collaud.fablab.api.service.global.ReadWriteService;

/**
 * This is the Service interface for a <tt>EventPerson</tt>.
 *
 * @author Fabien Vuilleumier
 */
public interface EventPersonService extends ReadWriteService<EventPersonEO> {

    EventPersonEO getId(String email);

    List<Integer> failedModules(Integer eventPersonId, List<Integer> eventModuleId);
}
