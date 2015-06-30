package net.collaud.fablab.api.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import net.collaud.fablab.api.dao.SubscriptionRepository;
import net.collaud.fablab.api.dao.UsageRepository;
import net.collaud.fablab.api.dao.UserPaymentRepository;
import net.collaud.fablab.api.data.SubscriptionEO;
import net.collaud.fablab.api.data.UsageEO;
import net.collaud.fablab.api.data.UserPaymentEO;
import net.collaud.fablab.api.data.virtual.HistoryEntry;
import net.collaud.fablab.api.exceptions.FablabException;
import net.collaud.fablab.api.rest.v1.criteria.PeriodSearchCriteria;
import net.collaud.fablab.api.security.Roles;
import net.collaud.fablab.api.service.UserPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * This is the service implementation class for a <tt>UserPayment</tt>.
 *
 * @author Fabien Vuilleumier
 */
@Service
@Transactional
@Secured({Roles.PAYMENT_VIEW})
public class UserPaymentServiceImpl implements UserPaymentService {

    @Autowired
    private UserPaymentRepository userPaymentDAO;
    
    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private UsageRepository usageRepository;

    @Override
    @Secured({Roles.PAYMENT_VIEW})
    public List<UserPaymentEO> findAll() {
        return new ArrayList(new HashSet(userPaymentDAO.findAll()));
    }

    @Override
    @Secured({Roles.PAYMENT_VIEW})
    public Optional<UserPaymentEO> getById(Integer id) {
        return userPaymentDAO.findOneDetails(id);
    }

    @Override
    @Secured({Roles.PAYMENT_VIEW})
    public UserPaymentEO save(UserPaymentEO userPayment) {
        if (userPayment.getId() == null) {
            userPayment.setId(0);
        }
        if (userPayment.getId() > 0) {
            UserPaymentEO old = userPaymentDAO.findOne(userPayment.getId());
            old.setTotal(userPayment.getTotal());
            old.setDatePayment(userPayment.getDatePayment());
            old.setDiscount(userPayment.getDiscount());
            old.setDiscountPercent(userPayment.isDiscountPercent());
            old.setAmount(userPayment.getAmount());
            old.setLabel(userPayment.getLabel());
            old.setNote(userPayment.getNote());
            old.setUser(userPayment.getUser());
            old.setCashier(userPayment.getCashier());
            old.setPayedForFabLab(userPayment.getPayedForFabLab());
            old.setActive(userPayment.isActive());
            return userPaymentDAO.saveAndFlush(old);
        } else {
            return userPaymentDAO.saveAndFlush(userPayment);
        }
    }

    @Override
    @Secured({Roles.PAYMENT_VIEW})
    public void remove(Integer id) {
        userPaymentDAO.delete(id);
    }

    @Override
    @Secured({Roles.PAYMENT_VIEW})
    public void softRemove(Integer id) {
        UserPaymentEO current = userPaymentDAO.findOne(id);
        current.setActive(false);
        userPaymentDAO.saveAndFlush(current);
    }

    @Override
    @Secured({Roles.PAYMENT_VIEW})
    public List<HistoryEntry> getPaymentEntries(PeriodSearchCriteria search) {
        if (search.isOneDateNull()) {
            throw new FablabException("Dates cannot be null");
        }
        List<UsageEO> listUsage = usageRepository.getAllBetween(search.getDateFrom(), search.getDateTo());
        List<UserPaymentEO> listPayment = userPaymentDAO.getAllBetween(search.getDateFrom(), search.getDateTo());
        List<SubscriptionEO> listSubscription = subscriptionRepository.getAllBetween(search.getDateFrom(), search.getDateTo());
        return convertToHistoryEntry(listUsage, listPayment, listSubscription);
    }
    
        protected List<HistoryEntry> convertToHistoryEntry(List<UsageEO> listUsage, List<UserPaymentEO> listPayment, List<SubscriptionEO> listSubscription) {
        final List<HistoryEntry> listHistory = Stream.concat(
                Stream.concat(
                        listUsage.stream()
                        .map(u -> new HistoryEntry(u)),
                        listPayment.stream()
                        .map(p -> new HistoryEntry(p))),
                listSubscription.stream()
                .map(s -> new HistoryEntry(s)))
                .sorted()
                .collect(Collectors.toList());
        return listHistory;
    }
}
