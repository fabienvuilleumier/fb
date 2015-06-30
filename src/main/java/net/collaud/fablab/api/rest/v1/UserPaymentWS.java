package net.collaud.fablab.api.rest.v1;

import javax.annotation.PostConstruct;
import net.collaud.fablab.api.annotation.JavascriptAPIConstant;
import net.collaud.fablab.api.data.UserPaymentEO;
import net.collaud.fablab.api.rest.v1.base.ReadWriteRestWebservice;
import net.collaud.fablab.api.exceptions.FablabException;
import net.collaud.fablab.api.service.UserPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This is the WS class for a <tt>UserPayment</tt>.
 *
 * @author Fabien Vuilleumier
 */
@RestController()
@RequestMapping("/v1/userPayment")
@JavascriptAPIConstant("USER_PAYMENT_API")
public class UserPaymentWS extends ReadWriteRestWebservice<UserPaymentEO, UserPaymentService> {

    @Autowired
    private UserPaymentService userPaymentService;

    @PostConstruct
    public void postConstruct() {
        super.setService(userPaymentService);
    }

    @Override
    public void softRemove(Integer id) throws FablabException {
        userPaymentService.softRemove(id);
    }
}
