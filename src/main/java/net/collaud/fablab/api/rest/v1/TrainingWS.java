package net.collaud.fablab.api.rest.v1;

import javax.annotation.PostConstruct;
import net.collaud.fablab.api.annotation.JavascriptAPIConstant;
import net.collaud.fablab.api.data.TrainingEO;
import net.collaud.fablab.api.rest.v1.base.ReadWriteRestWebservice;
import net.collaud.fablab.api.rest.v1.base.SoftRemoveWebService;
import net.collaud.fablab.api.exceptions.FablabException;
import net.collaud.fablab.api.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *This is the WS class for a <tt>Training</tt>.
* @author Fabien Vuilleumier
*/
@RestController()
@RequestMapping("/v1/training")
@JavascriptAPIConstant("TRAINING_API")
public class TrainingWS extends ReadWriteRestWebservice<TrainingEO, TrainingService> implements SoftRemoveWebService {

    @Autowired
    private TrainingService trainingService;

    @PostConstruct
    public void postConstruct(){
        super.setService(trainingService);
    }
    @Override
    public void softRemove(Integer id) throws FablabException {
        trainingService.softRemove(id);
    }
}

