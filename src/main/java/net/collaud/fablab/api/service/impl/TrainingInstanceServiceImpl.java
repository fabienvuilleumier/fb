package net.collaud.fablab.api.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import net.collaud.fablab.api.dao.TrainingInstanceRepository;
import net.collaud.fablab.api.data.TrainingInstanceEO;
import net.collaud.fablab.api.security.Roles;
import net.collaud.fablab.api.service.TrainingInstanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * This is the service implementation class for a <tt>TrainingInstance</tt>.
 * @author Fabien Vuilleumier
 */
@Service
@Transactional
    @Secured({Roles.TRAINING_MANAGE})
public class TrainingInstanceServiceImpl implements TrainingInstanceService {

    @Autowired
    private TrainingInstanceRepository trainingInstanceDAO;

    @Override
    @Secured({Roles.TRAINING_MANAGE})
    public List<TrainingInstanceEO> findAll() {
        return new ArrayList(new HashSet(trainingInstanceDAO.findAll()));
    }

    @Override
    @Secured({Roles.TRAINING_MANAGE})
    public Optional<TrainingInstanceEO> getById(Integer id) {
        return trainingInstanceDAO.findOneDetails(id);
    }

     @Override
    @Secured({Roles.TRAINING_MANAGE})
    public TrainingInstanceEO save(TrainingInstanceEO trainingInstance) {
        if (trainingInstance.getId() == null) {
            trainingInstance.setId(0);
        }
        if (trainingInstance.getId() > 0) {
            TrainingInstanceEO old = trainingInstanceDAO.findOne(trainingInstance.getId());
            old.setTrainingDate(trainingInstance.getTrainingDate());
            old.setTrainingPrice(trainingInstance.getTrainingPrice());
            old.setNote(trainingInstance.getNote());
            old.setTraining(trainingInstance.getTraining());
            old.setActive(trainingInstance.isActive());
            return trainingInstanceDAO.saveAndFlush(old);
        } else {
            return trainingInstanceDAO.saveAndFlush(trainingInstance);
        }
    }

    @Override
    @Secured({Roles.TRAINING_MANAGE})
    public void remove(Integer id) {
        trainingInstanceDAO.delete(id);
    }

    @Override
    @Secured({Roles.TRAINING_MANAGE})
    public void softRemove(Integer id) {
        TrainingInstanceEO current = trainingInstanceDAO.findOne(id);
        current.setActive(false);
        trainingInstanceDAO.saveAndFlush(current);
    }
}

