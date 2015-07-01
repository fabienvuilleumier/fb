package net.collaud.fablab.api.service;

import java.util.List;
import net.collaud.fablab.api.data.UserEO;
import net.collaud.fablab.api.data.virtual.HistoryEntry;
import net.collaud.fablab.api.data.virtual.UserAccountEntry;
import net.collaud.fablab.api.rest.v1.criteria.PeriodSearchCriteria;

/**
 *
 * @author Fabien Vuilleumier
 */
public interface AccountingService {

    List<HistoryEntry> getAccountingEntries(PeriodSearchCriteria search);
    List<UserAccountEntry> getAccountingEntries(Integer userId);
}
