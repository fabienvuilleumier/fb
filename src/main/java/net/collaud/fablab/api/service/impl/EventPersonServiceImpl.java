package net.collaud.fablab.api.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import net.collaud.fablab.api.dao.EventPersonRepository;
import net.collaud.fablab.api.data.EventPersonEO;
import net.collaud.fablab.api.security.Roles;
import net.collaud.fablab.api.service.EventPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * This is the service implementation class for a <tt>EventPerson</tt>.
 * @author Fabien Vuilleumier
 */
@Service
@Transactional
    @Secured({Roles.EVENT_VIEW})
public class EventPersonServiceImpl implements EventPersonService {

    @Autowired
    private EventPersonRepository eventPersonDAO;

    @Override
    @Secured({Roles.EVENT_VIEW})
    public List<EventPersonEO> findAll() {
        return new ArrayList(new HashSet(eventPersonDAO.findAll()));
    }

    @Override
    @Secured({Roles.EVENT_VIEW})
    public Optional<EventPersonEO> getById(Integer id) {
        return Optional.ofNullable(eventPersonDAO.findOne(id));
    }

     @Override
    @Secured({Roles.EVENT_VIEW})
    public EventPersonEO save(EventPersonEO eventPerson) {
        if (eventPerson.getId() == null) {
            eventPerson.setId(0);
        }
        if (eventPerson.getId() > 0) {
            EventPersonEO old = eventPersonDAO.findOne(eventPerson.getId());
            old.setLastname(eventPerson.getLastname());
            old.setFirstname(eventPerson.getFirstname());
            old.setEmail(eventPerson.getEmail());
            return eventPersonDAO.saveAndFlush(old);
        } else {
            return eventPersonDAO.saveAndFlush(eventPerson);
        }
    }

    @Override
    @Secured({Roles.EVENT_VIEW})
    public void remove(Integer id) {
        eventPersonDAO.delete(id);
    }

    @Override
    @Secured({Roles.EVENT_VIEW})
    public void softRemove(Integer id) {
        EventPersonEO current = eventPersonDAO.findOne(id);
        current.setActive(false);
        eventPersonDAO.saveAndFlush(current);
    }
}

