package org.rdutta.cache_system_design.cache.utilities.quick_scope;


public interface ICache<Key, Value> {
    /**
     * Adds an entry to the cache.
     *
     * @param key   the key of the entry
     * @param value the value of the entry
     */
     void put(Key key, Value value);

    /**
     * Retrieves an entry from the cache.
     *
     * @param key the key of the entry
     * @return the value associated with the key, or null if the key is not found
     */
    Value get(Key key);

    void remove(Key key);
}