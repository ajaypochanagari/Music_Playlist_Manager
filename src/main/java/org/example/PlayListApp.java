package org.example;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class PlayListApp {

    public static void main(String[] args) {
        DoublyLinkedList playlist = new DoublyLinkedList();
        RecentTracksLRU recentLRU = new RecentTracksLRU(10);
        PlaylistDatabase db = null;

        try {
            db = new PlaylistDatabase();

            // Load playlist from database
            List<String> songsFromDB = db.loadSongs();
            for (String song : songsFromDB) {
                playlist.addSong(song);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Database connection error: " + e.getMessage());
            return;
        }

        Scanner sc = new Scanner(System.in);

        System.out.println("🎵 Welcome to Playlist Manager with MySQL 🎵");
        System.out.println("Use underscore '_' for spaces in song names.");

        boolean running = true;

        while (running) {
            System.out.println("""
                    \n===== Playlist Menu =====
                    1. Add Song
                    2. Delete Song
                    3. Display Playlist
                    4. Total Songs
                    5. Search Song
                    6. Play Song
                    7. Recently Played
                    8. Last Played
                    9. Sort Playlist
                    10. Exit
                    11.clearAll Db and playlist
                    """);
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            try {
                switch (choice) {
                    case 1 -> {
                        System.out.print("Song name: ");
                        String song = sc.next().trim();
                        playlist.addSong(song);
                        db.addSong(song);
                        System.out.println("Song added.");
                    }
                    case 2 -> {
                        System.out.println("Delete by: 1. Name  2. Position");
                        int type = sc.nextInt();
                        if (type == 1) {
                            System.out.print("Song to delete: ");
                            String song = sc.next().trim();
                            if (playlist.deleteByName(song)) {
                                db.deleteSong(song);
                                System.out.println("Deleted.");
                            } else {
                                System.out.println("Song not found.");
                            }
                        } else {
                            System.out.print("Position: ");
                            int pos = sc.nextInt();
                            Node node = playlist.getNodeAt(pos);
                            if (node != null) {
                                String songName = node.song.name;
                                playlist.deleteByPosition(pos);
                                db.deleteSong(songName);
                                System.out.println("Deleted.");
                            } else {
                                System.out.println("Invalid position.");
                            }
                        }
                    }
                    case 3 -> playlist.display();
                    case 4 -> System.out.println("Total songs: " + playlist.size());
                    case 5 -> {
                        System.out.print("Search song: ");
                        String song = sc.next().trim();
                        System.out.println(playlist.contains(song) ? "Found!" : "Not found.");
                    }
                    case 6 -> { // Play Song
                        playlist.display();
                        System.out.print("Enter song to play: ");
                        String song = sc.next().trim();
                        if (playlist.contains(song)) {
                            System.out.println("Now playing: " + song);
                            recentLRU.push(song); // Changed to LRU
                        }
                    }
                    case 7 -> recentLRU.display(); // Now shows LRU list
                    case 8 -> recentLRU.lastPlayed();
                    case 9 -> {
                        playlist.sort();
                        System.out.println("Sorted playlist:");
                        playlist.display();
                    }
                    case 10 -> {
                        running = false;
                        System.out.println("Exiting Playlist Manager...");
                    }
                    case 11 -> {
                        System.out.print("Are you sure you want to clear all songs? (yes/no): ");
                        String confirm = sc.next().trim();
                        if (confirm.equalsIgnoreCase("yes")) {
                            try {
                                db.clearAllSongs();
                                playlist.clear(); // also clear the in-memory playlist if your DoublyLinkedList supports it
                                System.out.println("All songs cleared from the playlist and database.");
                            } catch (SQLException e) {
                                System.out.println("Failed to clear songs: " + e.getMessage());
                            }
                        } else {
                            System.out.println("Operation cancelled.");
                        }
                    }
                    default -> System.out.println("Invalid choice.");
                }
            } catch (SQLException e) {
                System.out.println("Database error: " + e.getMessage());
            }
        }

        sc.close();
        if (db != null) db.close();
    }
}
