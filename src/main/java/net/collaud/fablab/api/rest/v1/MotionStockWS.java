package net.collaud.fablab.api.rest.v1;

import javax.annotation.PostConstruct;
import net.collaud.fablab.api.annotation.JavascriptAPIConstant;
import net.collaud.fablab.api.data.MotionStockEO;
import net.collaud.fablab.api.rest.v1.base.ReadWriteRestWebservice;
import net.collaud.fablab.api.service.MotionStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *This is the WS class for a <tt>MotionStock</tt>.
* @author Fabien Vuilleumier
*/
@RestController()
@RequestMapping("/v1/motionStock")
@JavascriptAPIConstant("MOTION_STOCK_API")
public class MotionStockWS extends ReadWriteRestWebservice<MotionStockEO, MotionStockService>{

    @Autowired
    private MotionStockService motionStockService;

    @PostConstruct
    public void postConstruct(){
        super.setService(motionStockService);
    }
    
    @RequestMapping(value="getById", method = RequestMethod.GET)
    public MotionStockEO getById(@RequestParam(value="id") Integer id){
        return motionStockService.getById(id).get();
    }
}

