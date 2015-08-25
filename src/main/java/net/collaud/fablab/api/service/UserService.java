package net.collaud.fablab.api.service;

import java.util.Optional;
import net.collaud.fablab.api.data.UserEO;
import net.collaud.fablab.api.service.global.ReadWriteService;
import net.collaud.fablab.api.service.global.SoftRemoveService;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
public interface UserService extends ReadWriteService<UserEO>, SoftRemoveService<UserEO> {

	Optional<UserEO> findByLogin(String login);
	
	void signup(UserEO user, String recaptcha);
	
	void forgotPassword(String email, String recaptchaResponse);

	public void updateMailingList();
}
