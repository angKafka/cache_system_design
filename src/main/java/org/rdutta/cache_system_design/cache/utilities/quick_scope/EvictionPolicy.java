package org.rdutta.cache_system_design.cache.utilities.quick_scope;

public interface EvictionPolicy<Key> {
    void keyAccessed(Key key);
    /**
     * Evict key from eviction policy and return it.
     */
    Key evictKey();
}
