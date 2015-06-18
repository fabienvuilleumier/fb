package net.collaud.fablab.api.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import net.collaud.fablab.api.dao.MotionStockRepository;
import net.collaud.fablab.api.data.MotionStockEO;
import net.collaud.fablab.api.security.Roles;
import net.collaud.fablab.api.service.MotionStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * This is the service implementation class for a <tt>MotionStock</tt>.
 * @author Fabien Vuilleumier
 */
@Service
@Transactional
    @Secured({Roles.SUPPLY_MANAGE})
public class MotionStockServiceImpl extends AbstractServiceImpl implements MotionStockService {

    @Autowired
    private MotionStockRepository motionStockDAO;

    @Override
    @Secured({Roles.SUPPLY_MANAGE})
    public List<MotionStockEO> findAll() {
        return new ArrayList(new HashSet(motionStockDAO.findAll()));
    }

    @Override
    @Secured({Roles.SUPPLY_MANAGE})
    public Optional<MotionStockEO> getById(Integer id) {
        return motionStockDAO.findOneDetails(id);
    }
}

