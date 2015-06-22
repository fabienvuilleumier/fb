package net.collaud.fablab.api.rest.v1;

import javax.annotation.PostConstruct;
import net.collaud.fablab.api.annotation.JavascriptAPIConstant;
import net.collaud.fablab.api.data.TrainingInstanceEO;
import net.collaud.fablab.api.rest.v1.base.ReadWriteRestWebservice;
import net.collaud.fablab.api.rest.v1.base.SoftRemoveWebService;
import net.collaud.fablab.api.exceptions.FablabException;
import net.collaud.fablab.api.service.TrainingInstanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *This is the WS class for a <tt>TrainingInstance</tt>.
* @author Fabien Vuilleumier
*/
@RestController()
@RequestMapping("/v1/trainingInstance")
@JavascriptAPIConstant("TRAINING_INSTANCE_API")
public class TrainingInstanceWS extends ReadWriteRestWebservice<TrainingInstanceEO, TrainingInstanceService> implements SoftRemoveWebService {

    @Autowired
    private TrainingInstanceService trainingInstanceService;

    @PostConstruct
    public void postConstruct(){
        super.setService(trainingInstanceService);
    }
    @Override
    public void softRemove(Integer id) throws FablabException {
        trainingInstanceService.softRemove(id);
    }
}

