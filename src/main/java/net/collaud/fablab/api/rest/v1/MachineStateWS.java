package net.collaud.fablab.api.rest.v1;

import javax.annotation.PostConstruct;
import net.collaud.fablab.api.annotation.JavascriptAPIConstant;
import net.collaud.fablab.api.data.MachineStateEO;
import net.collaud.fablab.api.exceptions.FablabException;
import net.collaud.fablab.api.rest.v1.base.ReadWriteRestWebservice;
import net.collaud.fablab.api.rest.v1.base.SoftRemoveWebService;
import net.collaud.fablab.api.service.MachineStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This is the WS class for a <tt>MachineState</tt>.
 *
 * @author Fabien Vuilleumier
 */
@RestController()
@RequestMapping("/v1/machineState")
@JavascriptAPIConstant("MACHINE_STATE_API")
public class MachineStateWS extends ReadWriteRestWebservice<MachineStateEO, MachineStateService> implements SoftRemoveWebService {

    @Autowired
    private MachineStateService machineStateService;

    @PostConstruct
    public void postConstruct() {
        super.setService(machineStateService);
    }

    @Override
    public void softRemove(Integer id) throws FablabException {
        machineStateService.softRemove(id);
    }
}
