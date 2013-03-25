package org.bloodtorrent;

import com.google.common.base.Optional;

import java.util.Collections;
import java.util.HashSet;

/**
 * Created with IntelliJ IDEA.
 * User: sds
 * Date: 3/25/13
 * Time: 1:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class ResourceManager extends HashSet<Object> {

    private static final ResourceManager INSTANCE = new ResourceManager();

    public static ResourceManager getInstance() {
        return INSTANCE;
    }

    private ResourceManager() {
        Collections.synchronizedSet(this);
    }

    /**
     * Find the registered resource by parameter class type.
     * @param clazz A class type to be wanted to find.
     * @param <T>
     * @return
     */
    public <T> Optional<T> find(Class<T> clazz) {
        for (Object o : this) {
            if(o.getClass() == clazz)
                return Optional.of(clazz.cast(o));
        }
        return Optional.absent();
    }
}
