package net.collaud.fablab.api.rest.v1;

import javax.annotation.PostConstruct;
import net.collaud.fablab.api.annotation.JavascriptAPIConstant;
import net.collaud.fablab.api.data.GroupEO;
import net.collaud.fablab.api.rest.v1.base.ReadWriteRestWebservice;
import net.collaud.fablab.api.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This is the WS class for a <tt>Group</tt>.
 *
 * @author Fabien Vuilleumier
 */
@RestController()
@RequestMapping("/v1/group")
@JavascriptAPIConstant("GROUP_API")
public class GroupWS extends ReadWriteRestWebservice<GroupEO, GroupService> {

    @Autowired
    private GroupService groupService;

    @PostConstruct
    public void postConstruct() {
        super.setService(groupService);
    }
}
