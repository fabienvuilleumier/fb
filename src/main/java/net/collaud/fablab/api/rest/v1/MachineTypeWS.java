package net.collaud.fablab.api.rest.v1;

import javax.annotation.PostConstruct;
import net.collaud.fablab.api.annotation.JavascriptAPIConstant;
import net.collaud.fablab.api.data.MachineTypeEO;
import net.collaud.fablab.api.exceptions.FablabException;
import net.collaud.fablab.api.rest.v1.base.ReadWriteRestWebservice;
import net.collaud.fablab.api.rest.v1.base.SoftRemoveWebService;
import net.collaud.fablab.api.service.MachineTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com> Collaud
 * <gaetancollaud@gmail.com>
 */
@RestController()
@RequestMapping("/v1/machineType")
@JavascriptAPIConstant("MACHINE_TYPE_API")
public class MachineTypeWS extends ReadWriteRestWebservice<MachineTypeEO, MachineTypeService> implements SoftRemoveWebService {

    @Autowired
    private MachineTypeService machineTypeService;

    @PostConstruct
    public void postConstruct() {
        super.setService(machineTypeService);
    }

    @Override
    public void softRemove(Integer id) throws FablabException {
        machineTypeService.softRemove(id);
    }
}
