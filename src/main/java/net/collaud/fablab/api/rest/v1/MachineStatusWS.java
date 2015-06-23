package net.collaud.fablab.api.rest.v1;

import javax.annotation.PostConstruct;
import net.collaud.fablab.api.annotation.JavascriptAPIConstant;
import net.collaud.fablab.api.data.MachineStatusEO;
import net.collaud.fablab.api.rest.v1.base.ReadWriteRestWebservice;
import net.collaud.fablab.api.service.MachineStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This is the WS class for a <tt>MachineStatus</tt>.
 *
 * @author Fabien Vuilleumier
 */
@RestController()
@RequestMapping("/v1/machineStatus")
@JavascriptAPIConstant("MACHINE_STATUS_API")
public class MachineStatusWS extends ReadWriteRestWebservice<MachineStatusEO, MachineStatusService>{

    @Autowired
    private MachineStatusService machineStatusService;

    @PostConstruct
    public void postConstruct() {
        super.setService(machineStatusService);
    }
}
