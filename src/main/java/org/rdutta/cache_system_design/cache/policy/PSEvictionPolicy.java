package org.rdutta.cache_system_design.cache.utilities.quick_scope.custom;

import org.rdutta.cache_system_design.cache.algorithm.linkedlist.DoublyLinkedList;
import org.rdutta.cache_system_design.cache.algorithm.linkedlist.Node;
import org.rdutta.cache_system_design.cache.utilities.quick_scope.EvictionPolicy;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class PSEvictionPolicy<Key> implements EvictionPolicy<Key> {

    private final DoublyLinkedList<Key> dll;
    private final Map<Key, Node<Key>> mapper;

    public PSEvictionPolicy() {
        dll = new DoublyLinkedList<>();
        mapper = new ConcurrentHashMap<>();
    }

    @Override
    public void keyAccessed(Key key) {
        if (mapper.containsKey(key)) {
            dll.detachNode(mapper.get(key));
            dll.attachNodeAtLast(mapper.get(key));
        }else{
            Node<Key> node = new Node<>(key);
            mapper.put(key, node);
        }
    }

    @Override
    public Key evictKey() {
        Node<Key> head = dll.getHeadNode();
        if(head == null){
            return null;
        }
        dll.detachNode(head);
        return head.getElement();
    }
}
