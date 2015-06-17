package net.collaud.fablab.api.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import net.collaud.fablab.api.dao.SupplyRepository;
import net.collaud.fablab.api.data.SupplyEO;
import net.collaud.fablab.api.security.Roles;
import net.collaud.fablab.api.service.SupplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * This is the service implementation class for a <tt>Supply</tt>.
 *
 * @author Fabien Vuilleumier
 */
@Service
@Transactional
@Secured({Roles.ADMIN})
public class SupplyServiceImpl extends AbstractServiceImpl implements SupplyService {

    @Autowired
    private SupplyRepository supplyDAO;

    @Override
    @Secured({Roles.ADMIN})
    public List<SupplyEO> findAll() {
        return new ArrayList(new HashSet(supplyDAO.findAll()));
    }

    @Override
    @Secured({Roles.ADMIN})
    public Optional<SupplyEO> getById(Integer id) {
        return supplyDAO.findOneDetails(id);
    }

    @Override
    @Secured({Roles.ADMIN})
    public SupplyEO save(SupplyEO supply) {
        if (supply.getId() == null) {
            supply.setId(0);
        }
        if (supply.getId() > 0) {
            SupplyEO old = supplyDAO.findOne(supply.getId());
            old.setCode(supply.getCode());
            old.setLabel(supply.getLabel());
            old.setSellingPrice(supply.getSellingPrice());
            old.setUnityBuyingPrice(supply.getUnityBuyingPrice());
            old.setOrderAddress(supply.getOrderAddress());
            old.setSupplyType(supply.getSupplyType());
            old.setActive(supply.isActive());
            return supplyDAO.saveAndFlush(old);
        } else {
            return supplyDAO.saveAndFlush(supply);
        }
    }

    @Override
    @Secured({Roles.ADMIN})
    public void remove(Integer id) {
        supplyDAO.delete(id);
    }

    @Override
    @Secured({Roles.ADMIN})
    public void softRemove(Integer id) {
        SupplyEO current = supplyDAO.findOne(id);
        current.setActive(false);
        supplyDAO.saveAndFlush(current);
    }

    @Override
    public List<SupplyEO> stock() {
        return supplyDAO.stock();
    }

    @Override
    public void addQuantity(Integer id, Float quantity) {
        SupplyEO supply = supplyDAO.findOne(id);
        supply.setQuantityStock(supply.getQuantityStock() + quantity);
        supplyDAO.saveAndFlush(supply);
    }
}
