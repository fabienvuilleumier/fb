package net.collaud.fablab.api.rest.v1;

import javax.annotation.PostConstruct;
import net.collaud.fablab.api.annotation.JavascriptAPIConstant;
import net.collaud.fablab.api.data.SupplyTypeEO;
import net.collaud.fablab.api.rest.v1.base.ReadWriteRestWebservice;
import net.collaud.fablab.api.rest.v1.base.SoftRemoveWebService;
import net.collaud.fablab.api.exceptions.FablabException;
import net.collaud.fablab.api.service.SupplyTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *This is the WS class for a <tt>SupplyType</tt>.
* @author Fabien Vuilleumier
*/
@RestController()
@RequestMapping("/v1/supplyType")
@JavascriptAPIConstant("SUPPLY_TYPE_API")
public class SupplyTypeWS extends ReadWriteRestWebservice<SupplyTypeEO, SupplyTypeService> implements SoftRemoveWebService {

    @Autowired
    private SupplyTypeService supplyTypeService;

    @PostConstruct
    public void postConstruct(){
        super.setService(supplyTypeService);
    }
    @Override
    public void softRemove(Integer id) throws FablabException {
        supplyTypeService.softRemove(id);
    }
}

