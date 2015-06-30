package net.collaud.fablab.api.service;

import java.util.List;
import net.collaud.fablab.api.data.UserPaymentEO;
import net.collaud.fablab.api.data.virtual.HistoryEntry;
import net.collaud.fablab.api.rest.v1.criteria.PeriodSearchCriteria;
import net.collaud.fablab.api.service.global.ReadWriteService;

/**
 * This is the Service interface for a <tt>UserPayment</tt>.
 *
 * @author Fabien Vuilleumier
 */
public interface UserPaymentService extends ReadWriteService<UserPaymentEO> {

    List<HistoryEntry> getPaymentEntries(PeriodSearchCriteria search);
}
