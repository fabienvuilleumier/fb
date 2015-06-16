package net.collaud.fablab.api.rest.v1;

import javax.annotation.PostConstruct;
import net.collaud.fablab.api.annotation.JavascriptAPIConstant;
import net.collaud.fablab.api.data.TicketEO;
import net.collaud.fablab.api.exceptions.FablabException;
import net.collaud.fablab.api.rest.v1.base.ReadWriteRestWebservice;
import net.collaud.fablab.api.rest.v1.base.SoftRemoveWebService;
import net.collaud.fablab.api.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This is the WS class for a <tt>Ticket</tt>.
 *
 * @author Fabien Vuilleumier
 */
@RestController()
@RequestMapping("/v1/ticket")
@JavascriptAPIConstant("TICKET_API")
public class TicketWS extends ReadWriteRestWebservice<TicketEO, TicketService> implements SoftRemoveWebService {

    @Autowired
    private TicketService ticketService;

    @PostConstruct
    public void postConstruct() {
        super.setService(ticketService);
    }

    @Override
    public void softRemove(Integer id) throws FablabException {
        ticketService.softRemove(id);
    }
}
