package net.collaud.fablab.api.service;

import java.util.Date;
import java.util.List;
import net.collaud.fablab.api.data.ReservationEO;
import net.collaud.fablab.api.service.global.ReadWriteService;
import net.collaud.fablab.api.service.global.SoftRemoveService;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
public interface ReservationService extends ReadWriteService<ReservationEO>, SoftRemoveService<ReservationEO> {

	List<ReservationEO> findReservations(Date dateStart, Date dateEnd);
	
}
