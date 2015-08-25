package net.collaud.fablab.api.service.global;

/**
 *
 * @author Fabien Vuilleumier
 * @param <T>
 */
public interface SoftRemoveService<T> {
    void softRemove(Integer id);
}
