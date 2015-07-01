package net.collaud.fablab.api.rest.v1;

import javax.annotation.PostConstruct;
import net.collaud.fablab.api.annotation.JavascriptAPIConstant;
import net.collaud.fablab.api.data.EventTypeEO;
import net.collaud.fablab.api.rest.v1.base.ReadWriteRestWebservice;
import net.collaud.fablab.api.exceptions.FablabException;
import net.collaud.fablab.api.service.EventTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *This is the WS class for a <tt>EventType</tt>.
* @author Fabien Vuilleumier
*/
@RestController()
@RequestMapping("/v1/eventType")
@JavascriptAPIConstant("EVENT_TYPE_API")
public class EventTypeWS extends ReadWriteRestWebservice<EventTypeEO, EventTypeService>{

    @Autowired
    private EventTypeService eventTypeService;

    @PostConstruct
    public void postConstruct(){
        super.setService(eventTypeService);
    }
    @Override
    public void softRemove(Integer id) throws FablabException {
        eventTypeService.softRemove(id);
    }
}

