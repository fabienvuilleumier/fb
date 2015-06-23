package net.collaud.fablab.api.rest.v1;

import javax.annotation.PostConstruct;
import net.collaud.fablab.api.annotation.JavascriptAPIConstant;
import net.collaud.fablab.api.data.SupplyUnityEO;
import net.collaud.fablab.api.rest.v1.base.ReadWriteRestWebservice;
import net.collaud.fablab.api.service.SupplyUnityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *This is the WS class for a <tt>SupplyUnity</tt>.
* @author Fabien Vuilleumier
*/
@RestController()
@RequestMapping("/v1/supplyUnity")
@JavascriptAPIConstant("SUPPLY_UNITY_API")
public class SupplyUnityWS extends ReadWriteRestWebservice<SupplyUnityEO, SupplyUnityService>{

    @Autowired
    private SupplyUnityService supplyUnityService;

    @PostConstruct
    public void postConstruct(){
        super.setService(supplyUnityService);
    }
}

