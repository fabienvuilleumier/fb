package net.collaud.fablab.api.service;

import net.collaud.fablab.api.data.ConfigurationEO;
import net.collaud.fablab.api.service.global.ReadWriteService;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
public interface ConfigurationService extends ReadWriteService<ConfigurationEO> {

    ConfigurationEO findByKey(String key);
}
