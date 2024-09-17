package org.rdutta.cache_system_design.cache.main;



import lombok.extern.slf4j.Slf4j;
import org.rdutta.cache_system_design.cache.exceptions.NotFoundException;
import org.rdutta.cache_system_design.cache.exceptions.StorageFullException;
import org.rdutta.cache_system_design.cache.utilities.messages.Messages;
import org.rdutta.cache_system_design.cache.utilities.quick_scope.EvictionPolicy;
import org.rdutta.cache_system_design.cache.utilities.quick_scope.ICache;
import org.rdutta.cache_system_design.cache.utilities.quick_scope.IStorage;

@Slf4j
public class PSCache<Key, Value>  implements ICache<Key, Value>  {

    private final EvictionPolicy<Key> evictionPolicy;
    private final IStorage<Key, Value> storage;

    public PSCache(EvictionPolicy<Key> evictionPolicy, IStorage<Key, Value> storage) {
        this.evictionPolicy = evictionPolicy;
        this.storage = storage;
    }

    @Override
    public void put(Key key, Value value) {
        try {
            if (this.storage.isStorageFull()) {
                Key keyToRemove = evictionPolicy.evictKey();
                if (keyToRemove == null) {
                    log.error(Messages.STORAGE_FULL, "No key available for eviction.");
                    return;
                }
                storage.remove(keyToRemove);
                log.warn("Evicted key: " + keyToRemove);
            }
            this.storage.add(key, value);
            this.evictionPolicy.keyAccessed(key);
        } catch (StorageFullException s) {
            log.error(Messages.STORAGE_FULL, s);
        } catch (NotFoundException e) {
            log.error(Messages.KEY_NOT_FOUND_EXCEPTION, e);
        }
    }

    @Override
    public Value get(Key key) {
        try {
            Value value = this.storage.get(key);
            this.evictionPolicy.keyAccessed(key);
            return value;
        }catch (NotFoundException n){
            log.error(Messages.KEY_NOT_FOUND_EXCEPTION, n);
            return null;
        }
    }
}
