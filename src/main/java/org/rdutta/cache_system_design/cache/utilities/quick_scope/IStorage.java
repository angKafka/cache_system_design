package org.rdutta.cache_system_design.cache.utilities.quick_scope;

import org.rdutta.cache_system_design.cache.exceptions.NotFoundException;
import org.rdutta.cache_system_design.cache.exceptions.StorageFullException;

public interface IStorage<Key, Value> {
    void add(Key key, Value value) throws StorageFullException;
    void remove(Key key) throws NotFoundException;
    Value get(Key key) throws NotFoundException;
    boolean isStorageFull();
}
