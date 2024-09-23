package org.rdutta.cache_system_design.cache.policy;

import lombok.extern.slf4j.Slf4j;
import org.rdutta.cache_system_design.cache.algorithm.linkedlist.DoublyLinkedList;
import org.rdutta.cache_system_design.cache.algorithm.linkedlist.Node;
import org.rdutta.cache_system_design.cache.exceptions.NotFoundException;
import org.rdutta.cache_system_design.cache.exceptions.StorageFullException;
import org.rdutta.cache_system_design.cache.utilities.messages.Messages;
import org.rdutta.cache_system_design.cache.utilities.quick_scope.EvictionPolicy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Slf4j
public class PSEvictionPolicy<Key> implements EvictionPolicy<Key> {

    private final DoublyLinkedList<Key> dll;
    private final Map<Key, Node<Key>> mapper;
    private final Integer MAX_SIZE ;
    public PSEvictionPolicy(Integer MAX_SIZE) {
        dll = new DoublyLinkedList<>();
        mapper = new ConcurrentHashMap<>();
        this.MAX_SIZE = MAX_SIZE;
    }

    @Override
    public void keyAccessed(Key key) {
        try {
            if (mapper.containsKey(key)) {
                Node<Key> node = mapper.get(key);
                dll.detachNode(node);
                dll.attachNodeAtLast(node);
            } else {
                if (dll.isItemExists() && mapper.size() >= MAX_SIZE) {
                    Key keyToRemove = evictKey();
                    if (keyToRemove != null) {
                        mapper.remove(keyToRemove);
                    } else {
                        throw new RuntimeException(Messages.STORAGE_FULL);
                    }
                }
                Node<Key> node = dll.attachElementAtLast(key);
                mapper.put(key, node);
            }
        } catch (Exception e) {
            log.error("Exception occurred during key access: key={}, message={}", key, e.getMessage());
            throw e;
        }
    }

    @Override
    public Key evictKey() {
        try {
            Node<Key> head = dll.getHeadNode();
            if (head == null) {
                throw new NotFoundException(Messages.KEY_NOT_FOUND_EXCEPTION);
            }
            dll.detachNode(head);
            Key keyToRemove = head.getElement();
            mapper.remove(keyToRemove);
            return keyToRemove;
        } catch (NotFoundException nf) {
            log.info(Messages.KEY_NOT_FOUND_EXCEPTION);
            throw nf;
        }
    }
}
