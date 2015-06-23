package net.collaud.fablab.api.rest.v1;

import javax.annotation.PostConstruct;
import net.collaud.fablab.api.annotation.JavascriptAPIConstant;
import net.collaud.fablab.api.data.RoleEO;
import net.collaud.fablab.api.rest.v1.base.ReadRestWebservice;
import net.collaud.fablab.api.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *This is the WS class for a <tt>Role</tt>.
* @author Fabien Vuilleumier
*/
@RestController()
@RequestMapping("/v1/role")
@JavascriptAPIConstant("ROLE_API")
public class RoleWS extends ReadRestWebservice<RoleEO, RoleService>{

    @Autowired
    private RoleService roleService;

    @PostConstruct
    public void postConstruct(){
        super.setService(roleService);
    }
}

