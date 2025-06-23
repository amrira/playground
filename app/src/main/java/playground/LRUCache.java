package main.java.playground;

import java.util.HashMap;

public class LRUCache {
    class Node 
    {
        int key, value;
        Node prev, next;
    }

    private final int capacity;
    private final HashMap<Integer, Node> cache;
    private Node head, tail;

    public LRUCache(int capacity)
    {
        this.capacity = capacity;
        this.cache = new HashMap<Integer,Node>();
        head = new Node();
        tail = new Node();

        head.next = tail;
        tail.prev = head;
    }


    public void moveNodeToFront(Node node)
    {
        removeNodeconnections(node);   
        addNodeConnectionsAtFront(node);
    }

    public void removeNodeconnections(Node node)
    {
       node.prev.next =  node.next;
       node.next.prev = node.prev;

       node.next = null;
       node.prev = null;
    }

    public  void addNodeConnectionsAtFront(Node node)
    {
        Node temp = head.next;
        node.prev = head;
        node.next = temp;

        temp.prev = node;
        head.next = node;
    }


    public int get(int key)
    {
        if(!cache.containsKey(key))
        {
            return -1;
        }else{
            Node node = cache.get(key);
            if(node != null) 
            {
                moveNodeToFront(node);
                return node.value;
            }
            else return -1;
        }
    }

    public void put(int key, int value){
        if(cache.containsKey(key)){
            Node node = cache.get(key);
            node.value = value;
            moveNodeToFront(node);
            cache.put(key, node);
        }else{
            Node node = new Node();
            node.key = key;
            node.value = value;
            if(cache.size() == capacity){
                Node temp = this.tail.prev;
                cache.remove(temp.key);
                removeNodeconnections(temp);              
            }
                cache.put(key, node);
                addNodeConnectionsAtFront(node);
        }
    }

}
