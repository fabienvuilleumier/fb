package net.collaud.fablab.api.rest.v1.base;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.collaud.fablab.api.data.AbstractDataEO;
import net.collaud.fablab.api.exceptions.FablabException;
import net.collaud.fablab.api.service.global.ReadWriteService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Fabien Vuilleumier
 */
public interface SoftRemoveWebService {

    @RequestMapping(value = "softRemove", method = RequestMethod.GET)
    public void softRemove(@RequestParam(value = "id") Integer id) throws FablabException;
}
