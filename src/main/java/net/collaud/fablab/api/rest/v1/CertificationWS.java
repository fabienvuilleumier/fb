package net.collaud.fablab.api.rest.v1;

import javax.annotation.PostConstruct;
import net.collaud.fablab.api.annotation.JavascriptAPIConstant;
import net.collaud.fablab.api.data.CertificationEO;
import net.collaud.fablab.api.rest.v1.base.ReadWriteRestWebservice;
import net.collaud.fablab.api.rest.v1.base.SoftRemoveWebService;
import net.collaud.fablab.api.exceptions.FablabException;
import net.collaud.fablab.api.service.CertificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *This is the WS class for a <tt>Certification</tt>.
* @author Fabien Vuilleumier
*/
@RestController()
@RequestMapping("/v1/certification")
@JavascriptAPIConstant("CERTIFICATION_API")
public class CertificationWS extends ReadWriteRestWebservice<CertificationEO, CertificationService> implements SoftRemoveWebService {

    @Autowired
    private CertificationService certificationService;

    @PostConstruct
    public void postConstruct(){
        super.setService(certificationService);
    }
    @Override
    public void softRemove(Integer id) throws FablabException {
        certificationService.softRemove(id);
    }
}

