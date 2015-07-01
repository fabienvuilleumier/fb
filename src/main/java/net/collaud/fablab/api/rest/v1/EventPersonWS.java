package net.collaud.fablab.api.rest.v1;

import javax.annotation.PostConstruct;
import net.collaud.fablab.api.annotation.JavascriptAPIConstant;
import net.collaud.fablab.api.data.EventPersonEO;
import net.collaud.fablab.api.rest.v1.base.ReadWriteRestWebservice;
import net.collaud.fablab.api.exceptions.FablabException;
import net.collaud.fablab.api.service.EventPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *This is the WS class for a <tt>EventPerson</tt>.
* @author Fabien Vuilleumier
*/
@RestController()
@RequestMapping("/v1/eventPerson")
@JavascriptAPIConstant("EVENT_PERSON_API")
public class EventPersonWS extends ReadWriteRestWebservice<EventPersonEO, EventPersonService>{

    @Autowired
    private EventPersonService eventPersonService;

    @PostConstruct
    public void postConstruct(){
        super.setService(eventPersonService);
    }
    @Override
    public void softRemove(Integer id) throws FablabException {
        eventPersonService.softRemove(id);
    }
}

