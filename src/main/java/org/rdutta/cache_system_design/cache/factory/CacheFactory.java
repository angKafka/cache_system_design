package org.rdutta.cache_system_design.cache.factory;


import org.rdutta.cache_system_design.cache.main.PSCache;
import org.rdutta.cache_system_design.cache.policy.PSEvictionPolicy;
import org.rdutta.cache_system_design.cache.storage.PSStorage;

public class CacheFactory<Key, Value> {
    public PSCache<Key, Value> defaultCache(final Integer capacity) {
        return new PSCache<>(new PSEvictionPolicy<>(100),
                new PSStorage<>(capacity));
    }
}