package net.collaud.fablab.api.rest.v1;

import java.util.Set;
import javax.annotation.PostConstruct;
import net.collaud.fablab.api.annotation.JavascriptAPIConstant;
import net.collaud.fablab.api.data.ConfigurationEO;
import net.collaud.fablab.api.rest.v1.base.ReadWriteRestWebservice;
import net.collaud.fablab.api.service.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com> Collaud
 * <gaetancollaud@gmail.com>
 */
@RestController()
@RequestMapping("/v1/configuration")
@JavascriptAPIConstant("CONFIGURATION_API")
public class ConfigurationWS extends ReadWriteRestWebservice<ConfigurationEO, ConfigurationService> {

    @Autowired
    private ConfigurationService configurationService;

    @PostConstruct
    public void postConstruct() {
        super.setService(configurationService);
    }
    
    @RequestMapping(value="findByKey", method = RequestMethod.GET)
    public String findByKey(@RequestParam(value="key") String key){
        return configurationService.findByKey(key).getValue();
    }

}
