package net.collaud.fablab.api.service.impl;

import java.util.List;
import java.util.Optional;
import net.collaud.fablab.api.dao.GroupRepository;
import net.collaud.fablab.api.data.GroupEO;
import net.collaud.fablab.api.security.Roles;
import net.collaud.fablab.api.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * This is the service implementation class for a <tt>Group</tt>.
 *
 * @author Fabien Vuilleumier
 */
@Service
@Transactional
@Secured({Roles.ADMIN})
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupRepository groupDAO;

    @Override
    @Secured({Roles.ADMIN})
    public List<GroupEO> findAll() {
        return groupDAO.findAll();
    }

    @Override
    @Secured({Roles.ADMIN})
    public Optional<GroupEO> getById(Integer id) {
        Optional<GroupEO> g = groupDAO.findOneDetails(id);
        return g;
    }

    @Override
    @Secured({Roles.ADMIN})
    public GroupEO save(GroupEO group) {
        if (group.getId() == null) {
            group.setId(0);
        }
        if (group.getId() > 0) {
            GroupEO old = groupDAO.findOneDetails(group.getId()).get();
            System.out.println("GROUP " + group);
            System.out.println("OLD (SAVE)" + old);
            old.setActive(group.isActive());
            old.setName(group.getName());
            old.setTechnicalname(group.getTechnicalname());
            old.setRoles(group.getRoles());
            System.out.println("OLD (SAVE bis)" + old.getRoles());
            return groupDAO.saveAndFlush(old);
        } else {
            return groupDAO.saveAndFlush(group);
        }
    }

    @Override
    @Secured({Roles.ADMIN})
    public void remove(Integer id) {
        groupDAO.delete(id);
    }

    @Override
    @Secured({Roles.ADMIN})
    public void softRemove(Integer id) {
        GroupEO current = groupDAO.findOne(id);
        current.setActive(false);
        groupDAO.saveAndFlush(current);
    }
}
