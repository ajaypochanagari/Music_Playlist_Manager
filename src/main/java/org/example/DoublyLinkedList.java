package org.example;

/**
 * Playlist managed as a doubly linked list.
 */
public class DoublyLinkedList {
    private Node head;
    private Node tail;
    private int size = 0;

    public Node getHead() { return head; }
    public Node getTail() { return tail; }

    public void addSong(String songName) {
        Node newNode = new Node(new Song(songName));
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    public void deleteByPosition(int pos) {
        if (pos < 1 || pos > size) return;
        Node target = getNodeAt(pos);

        if (target.prev != null) {
            target.prev.next = target.next;
        } else {
            head = target.next;
        }

        if (target.next != null) {
            target.next.prev = target.prev;
        } else {
            tail = target.prev; // Update tail when deleting last node
        }
        size--;
    }

    public boolean deleteByName(String songName) {
        Node temp = head;
        while (temp != null) {
            if (temp.song.name.equals(songName)) {
                if (temp.prev != null) temp.prev.next = temp.next;
                else head = temp.next;
                if (temp.next != null) temp.next.prev = temp.prev;
                size--;
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    public boolean contains(String songName) {
        Node temp = head;
        while (temp != null) {
            if (temp.song.name.equals(songName)) return true;
            temp = temp.next;
        }
        return false;
    }

    public void display() {
        System.out.println("\n--- Playlist Songs ---");
        if (head == null) {
            System.out.println("(empty playlist)");
            return;
        }
        Node temp = head;
        while (temp != null) {
            System.out.println(temp.song.name);
            temp = temp.next;
        }
    }

    public int size() {
        return size;
    }

    public void sort() {
        if (head == null || head.next == null) return; // Already sorted or empty
        head = mergeSort(head);

        // Update tail after sorting
        Node temp = head;
        while (temp.next != null) temp = temp.next;
        tail = temp;
    }

    // Merge Sort for Doubly Linked List
    private Node mergeSort(Node node) {
        if (node == null || node.next == null) return node;

        Node middle = getMiddle(node);
        Node nextOfMiddle = middle.next;

        // Split the list into two halves
        middle.next = null;
        if (nextOfMiddle != null) nextOfMiddle.prev = null;

        Node left = mergeSort(node);
        Node right = mergeSort(nextOfMiddle);

        return merge(left, right);
    }

    private Node merge(Node first, Node second) {
        if (first == null) return second;
        if (second == null) return first;

        if (first.song.name.compareTo(second.song.name) <= 0) {
            first.next = merge(first.next, second);
            if (first.next != null) first.next.prev = first;
            first.prev = null;
            return first;
        } else {
            second.next = merge(first, second.next);
            if (second.next != null) second.next.prev = second;
            second.prev = null;
            return second;
        }
    }

    // Utility: find middle of DLL (slow/fast pointer)
    private Node getMiddle(Node node) {
        if (node == null) return node;
        Node slow = node, fast = node;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public Node getNodeAt(int pos) {
        Node temp = head;
        for (int i = 1; i < pos && temp != null; i++) {
            temp = temp.next;
        }
        return temp;
    }
}
