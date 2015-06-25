package net.collaud.fablab.api.service;

import java.util.List;
import net.collaud.fablab.api.data.CertificationEO;
import net.collaud.fablab.api.service.global.ReadWriteService;

/**
 * This is the Service interface for a <tt>Certification</tt>.
 *
 * @author Fabien Vuilleumier
 */
public interface CertificationService extends ReadWriteService<CertificationEO> {

    CertificationEO getId(String name);

    boolean canCertify(Integer trainingId, Integer userId);

    List<Integer> failedUser(Integer certificationId, List<Integer> userIds);

}
