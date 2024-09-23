package org.rdutta.cache_system_design.user.config;


import org.rdutta.cache_system_design.cache.main.PSCache;
import org.rdutta.cache_system_design.cache.policy.PSEvictionPolicy;
import org.rdutta.cache_system_design.cache.storage.PSStorage;
import org.rdutta.cache_system_design.cache.utilities.quick_scope.EvictionPolicy;
import org.rdutta.cache_system_design.cache.utilities.quick_scope.IStorage;
import org.rdutta.cache_system_design.user.model.Employee;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Configuration
public class CacheConfig {

    @Bean
    public EvictionPolicy<UUID> evictionPolicy() {
        int maxSize = 100; // You can set this from a properties file or environment variable
        return new PSEvictionPolicy<>(maxSize);
    }

    @Bean
    public PSCache<UUID, Employee> psCache(EvictionPolicy<UUID> evictionPolicy, IStorage<UUID, Employee> storage) {
        return new PSCache<>(evictionPolicy, storage);
    }

    // Define the IStorage bean as well
    @Bean
    public IStorage<UUID, Employee> storage() {
        // Return your storage implementation
        return new PSStorage<>(100);
    }
}