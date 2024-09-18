package org.rdutta.cache_system_design;

import org.rdutta.cache_system_design.cache.factory.CacheFactory;
import org.rdutta.cache_system_design.cache.main.PSCache;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CacheSystemDesignApplication {

    public static void main(String[] args) {
        SpringApplication.run(CacheSystemDesignApplication.class, args);
        cacheSystemDesignTesting();
    }

    public static void cacheSystemDesignTesting() {
        CacheFactory<String, String> factory = new CacheFactory<>();

        // Create a default cache with a specified capacity
        PSCache<String, String> cache = factory.defaultCache(3);

        // Use the cache
        cache.put("1", "A");
        cache.put("2", "B");
        cache.put("3", "C");

        // Access some elements
        System.out.println("Cache value for key 1: " + cache.get("1"));
        System.out.println("Cache value for key 2: " + cache.get("2"));

        // Insert a new element, which should evict the least recently used entry
        cache.put("4", "D");
        System.out.println("Cache value for key 3: " + cache.get("3"));
    }

}
