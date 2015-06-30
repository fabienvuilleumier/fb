package net.collaud.fablab.api.dao;

import java.util.Date;
import java.util.List;
import net.collaud.fablab.api.data.UserPaymentEO;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * This is the DAO interface for a <tt>UserPayment</tt>.
 *
 * @author Fabien Vuilleumier
 */
public interface UserPaymentRepository extends JpaRepository<UserPaymentEO, Integer> {

    @Query("SELECT u "
            + " FROM UserPaymentEO u  "
            + " LEFT JOIN FETCH u.user  "
            + " LEFT JOIN FETCH u.cashier  ")
    @Override
    List<UserPaymentEO> findAll();

    @Query("SELECT u "
            + " FROM UserPaymentEO u "
            + " LEFT JOIN FETCH u.user "
            + " LEFT JOIN FETCH u.cashier "
            + " WHERE u.id=:id")
    Optional<UserPaymentEO> findOneDetails(@Param("id") Integer id);

    @Query("SELECT u "
            + " FROM UserPaymentEO u "
            + " LEFT JOIN FETCH u.user "
            + " LEFT JOIN FETCH u.cashier "
            + " WHERE u.user.id=:userId")
    List<UserPaymentEO> findByUser(@Param("userId") Integer userId);

    @Query(" SELECT p "
            + " FROM UserPaymentEO p "
            + " WHERE p.datePayment>=:dateAfter AND p.datePayment <=:dateBefore")
    public List<UserPaymentEO> getAllBetween(@Param("dateAfter") Date dateAfter, @Param("dateBefore") Date dateBefore);
}
