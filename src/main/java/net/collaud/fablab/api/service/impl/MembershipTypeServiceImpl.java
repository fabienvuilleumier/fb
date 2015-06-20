package net.collaud.fablab.api.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import net.collaud.fablab.api.dao.MembershipTypeRepository;
import net.collaud.fablab.api.data.MembershipTypeEO;
import net.collaud.fablab.api.data.PriceMachineEO;
import net.collaud.fablab.api.security.Roles;
import net.collaud.fablab.api.service.MembershipTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * This is the service implementation class for a <tt>MembershipType</tt>.
 *
 * @author Fabien Vuilleumier
 */
@Service
@Transactional
@Secured({Roles.MACHINE_MANAGE})
public class MembershipTypeServiceImpl implements MembershipTypeService {

    @Autowired
    private MembershipTypeRepository membershipTypeDAO;

    @Override
    @Secured({Roles.MACHINE_MANAGE})
    public List<MembershipTypeEO> findAll() {
        return new ArrayList(new HashSet(membershipTypeDAO.findAll()));
    }

    @Override
    @Secured({Roles.MACHINE_MANAGE})
    public Optional<MembershipTypeEO> getById(Integer id) {
        return Optional.ofNullable(membershipTypeDAO.findOne(id));
    }

    @Override
    @Secured({Roles.MACHINE_MANAGE})
    public MembershipTypeEO save(MembershipTypeEO membershipType) {
        if (membershipType.getId() == null) {
            membershipType.setId(0);
        }
        if (membershipType.getId() > 0) {
            MembershipTypeEO old = membershipTypeDAO.findOne(membershipType.getId());
            old.setName(membershipType.getName());
            old.setDuration(membershipType.getDuration());
            old.setPrice(membershipType.getPrice());
            return membershipTypeDAO.saveAndFlush(old);
        } else {
            return membershipTypeDAO.saveAndFlush(membershipType);
        }
    }

    @Override
    @Secured({Roles.MACHINE_MANAGE})
    public void remove(Integer id) {
        membershipTypeDAO.delete(id);
    }

    @Override
    @Secured({Roles.MACHINE_MANAGE})
    public void softRemove(Integer id) {
        MembershipTypeEO current = membershipTypeDAO.findOne(id);
        current.setActive(false);
        membershipTypeDAO.saveAndFlush(current);
    }

    @Override
    public List<PriceMachineEO> getPrices(Integer id) {
        return membershipTypeDAO.getPrices(id);
    }
}
