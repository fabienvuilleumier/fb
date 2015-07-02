package net.collaud.fablab.api.rest.v1;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import net.collaud.fablab.api.annotation.JavascriptAPIConstant;
import net.collaud.fablab.api.data.EventModuleEO;
import net.collaud.fablab.api.data.EventPersonEO;
import net.collaud.fablab.api.rest.v1.base.ReadWriteRestWebservice;
import net.collaud.fablab.api.exceptions.FablabException;
import net.collaud.fablab.api.service.EventModuleService;
import net.collaud.fablab.api.service.EventPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * This is the WS class for a <tt>EventPerson</tt>.
 *
 * @author Fabien Vuilleumier
 */
@RestController()
@RequestMapping("/v1/eventPerson")
@JavascriptAPIConstant("EVENT_PERSON_API")
public class EventPersonWS extends ReadWriteRestWebservice<EventPersonEO, EventPersonService> {

    @Autowired
    private EventPersonService eventPersonService;

    @Autowired
    private EventModuleService eventModuleService;

    @PostConstruct
    public void postConstruct() {
        super.setService(eventPersonService);
    }

    @Override
    public void softRemove(Integer id) throws FablabException {
        eventPersonService.softRemove(id);
    }

    @RequestMapping(value = "getId", method = RequestMethod.GET)
    public EventPersonEO getId(@RequestParam(value = "email") String email) {
        return eventPersonService.getId(email);
    }

    @RequestMapping(value = "failedModules", method = RequestMethod.GET)
    public List<String> failedModules(@RequestParam(value = "eventPersonId") Integer eventPersonId,
            @RequestParam(value = "eventModuleId") List<Integer> eventModuleId) {

        List<EventModuleEO> newEvm = new ArrayList<>();
        EventPersonEO ep = eventPersonService.getById(eventPersonId).get();
        List<String> names = new ArrayList<>();
        for (Integer evmId : eventModuleId) {
            newEvm.add(eventModuleService.getById(evmId).get());
        }
        for (EventModuleEO evm : newEvm) {
            if (!evm.getPrerequisites().isEmpty()) {
                if (!ep.getAcquiredModules().containsAll(evm.getPrerequisites())) {
                    names.add(evm.getName());
                }
            }
        }
        return names;
    }
}
