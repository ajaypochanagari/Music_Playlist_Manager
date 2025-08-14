package org.example;

/**
 * Node of a doubly linked list storing a Song.
 */
public class Node {
    Song song;
    Node next, prev;

    public Node(Song song) {
        this.song = song;
    }
}

