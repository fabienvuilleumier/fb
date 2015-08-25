package net.collaud.fablab.api.rest.v1;

import java.util.List;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import net.collaud.fablab.api.annotation.JavascriptAPIConstant;
import net.collaud.fablab.api.data.ReservationEO;
import net.collaud.fablab.api.exceptions.FablabException;
import net.collaud.fablab.api.rest.v1.base.ReadWriteRestWebservice;
import net.collaud.fablab.api.rest.v1.base.SoftRemoveWebService;
import net.collaud.fablab.api.rest.v1.criteria.PeriodSearchCriteria;
import net.collaud.fablab.api.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com> Collaud
 * <gaetancollaud@gmail.com>
 */
@RestController()
@RequestMapping("/v1/reservation")
@JavascriptAPIConstant("RESERVATION_API")
@Slf4j
public class ReservationWS extends ReadWriteRestWebservice<ReservationEO, ReservationService> implements SoftRemoveWebService {

    @Autowired
    private ReservationService reservationService;

    @PostConstruct
    private void postConstruct() {
        super.setService(reservationService);
    }

    @RequestMapping(value = "search", method = RequestMethod.POST)
    public List<ReservationEO> list(@RequestBody PeriodSearchCriteria criteria) {
        List<ReservationEO> list = reservationService.findReservations(
                criteria.getDateFrom(),
                criteria.getDateTo());
        return list;
    }

    @Override
    public void softRemove(Integer id) throws FablabException {
        reservationService.softRemove(id);
    }

}
