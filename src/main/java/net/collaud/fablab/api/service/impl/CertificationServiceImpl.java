package net.collaud.fablab.api.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import net.collaud.fablab.api.dao.CertificationRepository;
import net.collaud.fablab.api.data.CertificationEO;
import net.collaud.fablab.api.security.Roles;
import net.collaud.fablab.api.service.CertificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * This is the service implementation class for a <tt>Certification</tt>.
 * @author Fabien Vuilleumier
 */
@Service
@Transactional
    @Secured({Roles.TRAINING_MANAGE})
public class CertificationServiceImpl implements CertificationService {

    @Autowired
    private CertificationRepository certificationDAO;

    @Override
    @Secured({Roles.TRAINING_MANAGE})
    public List<CertificationEO> findAll() {
        return new ArrayList(new HashSet(certificationDAO.findAll()));
    }

    @Override
    @Secured({Roles.TRAINING_MANAGE})
    public Optional<CertificationEO> getById(Integer id) {
        return certificationDAO.findOneDetails(id);
    }

     @Override
    @Secured({Roles.TRAINING_MANAGE})
    public CertificationEO save(CertificationEO certification) {
        if (certification.getId() == null) {
            certification.setId(0);
        }
        if (certification.getId() > 0) {
            CertificationEO old = certificationDAO.findOneDetails(certification.getId()).get();
            old.setCertificationDate(certification.getCertificationDate());
            old.setCertificationPrice(certification.getCertificationPrice());
            old.setNote(certification.getNote());
            old.setTraining(certification.getTraining());
            old.setUsers(certification.getUsers());
            old.setActive(certification.isActive());
            return certificationDAO.saveAndFlush(old);
        } else {
            return certificationDAO.saveAndFlush(certification);
        }
    }

    @Override
    @Secured({Roles.TRAINING_MANAGE})
    public void remove(Integer id) {
        certificationDAO.delete(id);
    }

    @Override
    @Secured({Roles.TRAINING_MANAGE})
    public void softRemove(Integer id) {
        CertificationEO current = certificationDAO.findOne(id);
        current.setActive(false);
        certificationDAO.saveAndFlush(current);
    }

    @Override
    public CertificationEO getId(String trainingName) {
        return certificationDAO.getId(trainingName);
    }
}

