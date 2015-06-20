package net.collaud.fablab.api.rest.v1;

import javax.annotation.PostConstruct;
import net.collaud.fablab.api.annotation.JavascriptAPIConstant;
import net.collaud.fablab.api.data.PriceMachineEO;
import net.collaud.fablab.api.rest.v1.base.ReadWriteRestWebservice;
import net.collaud.fablab.api.rest.v1.base.SoftRemoveWebService;
import net.collaud.fablab.api.exceptions.FablabException;
import net.collaud.fablab.api.service.PriceMachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *This is the WS class for a <tt>PriceMachine</tt>.
* @author Fabien Vuilleumier
*/
@RestController()
@RequestMapping("/v1/priceMachine")
@JavascriptAPIConstant("PRICE_MACHINE_API")
public class PriceMachineWS extends ReadWriteRestWebservice<PriceMachineEO, PriceMachineService> implements SoftRemoveWebService {

    @Autowired
    private PriceMachineService priceMachineService;

    @PostConstruct
    public void postConstruct(){
        super.setService(priceMachineService);
    }
    @Override
    public void softRemove(Integer id) throws FablabException {
        priceMachineService.softRemove(id);
    }
    
    @RequestMapping(value = "getPriceMachineType", method = RequestMethod.GET)
    public PriceMachineEO getPriceMachineType(@RequestParam(value = "machineTypeId") Integer machineTypeId, 
            @RequestParam(value="membershipTypeId") Integer membershipTypeId) {
        return priceMachineService.getPriceMachineType(machineTypeId, membershipTypeId);
    }
}

