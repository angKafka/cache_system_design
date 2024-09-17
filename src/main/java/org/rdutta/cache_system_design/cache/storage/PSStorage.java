package org.rdutta.cache_system_design.cache.utilities.quick_scope.custom;


import org.rdutta.cache_system_design.cache.exceptions.NotFoundException;
import org.rdutta.cache_system_design.cache.exceptions.StorageFullException;
import org.rdutta.cache_system_design.cache.utilities.messages.Messages;
import org.rdutta.cache_system_design.cache.utilities.quick_scope.IStorage;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class PSStorage<Key, Value> implements IStorage<Key, Value> {

    Map<Key, Value> storage;
    private final Integer capacity;

    public PSStorage(Integer capacity) {
        this.capacity = capacity;
        storage = new ConcurrentHashMap<>(capacity);
    }

    @Override
    public void add(Key key, Value value) throws StorageFullException {
        if(isStorageFull()) throw new StorageFullException(Messages.STORAGE_FULL_EXCEPTION);
        storage.put(key, value);
    }

    @Override
    public void remove(Key key) throws NotFoundException {
        if(!storage.containsKey(key)) throw new NotFoundException(Messages.KEY_NOT_FOUND_EXCEPTION);
        storage.remove(key);
    }

    @Override
    public Value get(Key key) throws NotFoundException {
        if(!storage.containsKey(key)) throw new NotFoundException(Messages.KEY_NOT_FOUND_EXCEPTION);
        return storage.get(key);
    }

    public boolean isStorageFull() {
        return storage.size() == capacity;
    }
}
