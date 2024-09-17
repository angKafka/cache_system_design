package org.rdutta.cache_system_design.cache.algorithm.linkedlist;

import org.rdutta.cache_system_design.cache.utilities.messages.Messages;

import java.util.NoSuchElementException;

public class DoublyLinkedList<E> {
     Node<E> head;
     Node<E> tail;

    public DoublyLinkedList()
    {
        head = new Node<>(null);
        tail = new Node<>(null);

        this.head.next = tail;
        this.tail.prev = head;
    }

    public void detachNode(Node<E> node) {

        if (node != null) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
    }

    public void attachNodeAtLast(Node<E> node){
        Node<E> lastNode = tail.prev;
        lastNode.next = node;
        node.next = tail;
        tail.prev = node;
        node.prev = lastNode;
    }

    public Node<E> attachElementAtLast(E element){
        if (element == null) {
            throw new IndexOutOfBoundsException(Messages.ELEMENT_EXCEPTION);
        }
        Node<E> newNode = new Node<>(element);
        attachNodeAtLast(newNode);
        return newNode;
    }

    public boolean isItemExists(){
        return head.next != tail;
    }

    public Node<E> getHeadNode() throws NoSuchElementException {
        Node<E> item = null;
        if(isItemExists()){
            return null;
        }
        return head.next;
    }

    public Node<E> getTailNode() throws NoSuchElementException {
        Node<E> item = null;
        if(isItemExists()){
            return null;
        }
        return tail.prev;
    }

}
