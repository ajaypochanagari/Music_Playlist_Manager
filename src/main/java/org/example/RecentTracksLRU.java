package org.example;


import java.util.HashMap;
import java.util.Map;

public class RecentTracksLRU {
    private final int capacity;
    private final Map<String, Node> map;
    private Node head, tail;
    private int size;

    public RecentTracksLRU(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.size = 0;
    }

    public void push(String songName) {
        if (map.containsKey(songName)) {
            // Move existing node to head
            Node node = map.get(songName);
            removeNode(node);
            addToHead(node);
        } else {
            // Create new node
            Node newNode = new Node(new Song(songName));
            map.put(songName, newNode);
            addToHead(newNode);

            if (size > capacity) {
                // Remove LRU item
                map.remove(tail.song.name);
                removeNode(tail);
            }
        }
    }

    private void addToHead(Node node) {
        node.next = head;
        node.prev = null;
        if (head != null) {
            head.prev = node;
        }
        head = node;
        if (tail == null) {
            tail = node;
        }
        size++;
    }

    private void removeNode(Node node) {
        if (node.prev != null) {   // middle node
            node.prev.next = node.next;
        } else {
            head = node.next;     // head node
        }

        if (node.next != null) {
            node.next.prev = node.prev;  // prevents tail pointer removal
        } else {
            tail = node.prev;
        }
        size--;
    }

    public void display() {
        if (head == null) {
            System.out.println("No recently played tracks.");
            return;
        }
        System.out.println("\n--- Recently Played Songs (LRU) ---");
        Node temp = head;
        while (temp != null) {
            System.out.println(temp.song.name);
            temp = temp.next;
        }
    }

    public void lastPlayed() {
        if (head == null) System.out.println("No last played track.");
        else System.out.println("Last played: " + head.song.name);
    }
}
