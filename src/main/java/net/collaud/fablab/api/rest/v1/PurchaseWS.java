package net.collaud.fablab.api.rest.v1;

import javax.annotation.PostConstruct;
import net.collaud.fablab.api.annotation.JavascriptAPIConstant;
import net.collaud.fablab.api.data.PurchaseEO;
import net.collaud.fablab.api.rest.v1.base.ReadWriteRestWebservice;

import net.collaud.fablab.api.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *This is the WS class for a <tt>Purchase</tt>.
* @author Fabien Vuilleumier
*/
@RestController()
@RequestMapping("/v1/purchase")
@JavascriptAPIConstant("PURCHASE_API")
public class PurchaseWS extends ReadWriteRestWebservice<PurchaseEO, PurchaseService>{

    @Autowired
    private PurchaseService purchaseService;

    @PostConstruct
    public void postConstruct(){
        super.setService(purchaseService);
    }
}

