package net.collaud.fablab.api.service;

import net.collaud.fablab.api.data.TrainingEO;
import net.collaud.fablab.api.service.global.ReadWriteService;
/**
 *This is the Service interface for a <tt>Training</tt>.
* @author Fabien Vuilleumier
*/
public interface TrainingService extends ReadWriteService<TrainingEO>{

    public TrainingEO getId(String name);

}
