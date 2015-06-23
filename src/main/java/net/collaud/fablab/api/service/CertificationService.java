package net.collaud.fablab.api.service;

import net.collaud.fablab.api.data.CertificationEO;
import net.collaud.fablab.api.service.global.ReadWriteService;
/**
 *This is the Service interface for a <tt>Certification</tt>.
* @author Fabien Vuilleumier
*/
public interface CertificationService extends ReadWriteService<CertificationEO>{

    public CertificationEO getId(String trainingName);

}
