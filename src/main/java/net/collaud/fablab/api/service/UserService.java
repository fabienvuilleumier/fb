package net.collaud.fablab.api.service;

import java.util.Optional;
import net.collaud.fablab.api.data.UserEO;
import net.collaud.fablab.api.service.global.ReadWriteService;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
public interface UserService extends ReadWriteService<UserEO> {

    Optional<UserEO> findByLogin(String login);

    void signup(UserEO user, String recaptcha);

    void forgotPassword(String email, String recaptchaResponse);

    void updateMailingList();

    boolean canUse(Integer machineTypeId, Integer userId);

    boolean hasRole(Integer userId, String role);

    Double balance(Integer userId);
}
