package net.collaud.fablab.api.rest.v1;

import java.util.List;
import javax.annotation.PostConstruct;
import net.collaud.fablab.api.annotation.JavascriptAPIConstant;
import net.collaud.fablab.api.data.SupplyEO;
import net.collaud.fablab.api.rest.v1.base.ReadWriteRestWebservice;
import net.collaud.fablab.api.rest.v1.base.SoftRemoveWebService;
import net.collaud.fablab.api.exceptions.FablabException;
import net.collaud.fablab.api.service.SupplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *This is the WS class for a <tt>Supply</tt>.
* @author Fabien Vuilleumier
*/
@RestController()
@RequestMapping("/v1/supply")
@JavascriptAPIConstant("SUPPLY_API")
public class SupplyWS extends ReadWriteRestWebservice<SupplyEO, SupplyService> implements SoftRemoveWebService {

    @Autowired
    private SupplyService supplyService;

    @PostConstruct
    public void postConstruct(){
        super.setService(supplyService);
    }
    @Override
    public void softRemove(Integer id) throws FablabException {
        supplyService.softRemove(id);
    }
    
    @RequestMapping(value = "stock", method = RequestMethod.GET)
    public List<SupplyEO> stock() throws FablabException {
        return supplyService.stock();
    }
    @RequestMapping(value = "addQuantity", method = RequestMethod.GET)
    public void stock(@RequestParam(value = "id")Integer id, 
            @RequestParam(value = "quantity") Float quantity) throws FablabException {
        supplyService.addQuantity(id, quantity);
    }
}
