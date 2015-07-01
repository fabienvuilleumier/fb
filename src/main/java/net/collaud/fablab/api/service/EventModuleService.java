package net.collaud.fablab.api.service;

import net.collaud.fablab.api.data.EventModuleEO;
import net.collaud.fablab.api.service.global.ReadWriteService;
/**
 *This is the Service interface for a <tt>EventModule</tt>.
* @author Fabien Vuilleumier
*/
public interface EventModuleService extends ReadWriteService<EventModuleEO>{

    EventModuleEO getId(String name);

}
