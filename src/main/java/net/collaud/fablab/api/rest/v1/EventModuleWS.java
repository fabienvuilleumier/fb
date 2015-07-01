package net.collaud.fablab.api.rest.v1;

import javax.annotation.PostConstruct;
import net.collaud.fablab.api.annotation.JavascriptAPIConstant;
import net.collaud.fablab.api.data.EventModuleEO;
import net.collaud.fablab.api.rest.v1.base.ReadWriteRestWebservice;
import net.collaud.fablab.api.exceptions.FablabException;
import net.collaud.fablab.api.service.EventModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *This is the WS class for a <tt>EventModule</tt>.
* @author Fabien Vuilleumier
*/
@RestController()
@RequestMapping("/v1/eventModule")
@JavascriptAPIConstant("EVENT_MODULE_API")
public class EventModuleWS extends ReadWriteRestWebservice<EventModuleEO, EventModuleService>{

    @Autowired
    private EventModuleService eventModuleService;

    @PostConstruct
    public void postConstruct(){
        super.setService(eventModuleService);
    }
    @Override
    public void softRemove(Integer id) throws FablabException {
        eventModuleService.softRemove(id);
    }
}

