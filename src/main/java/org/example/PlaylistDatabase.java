package org.example;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles interaction with MySQL for playlist persistence.
 */
public class PlaylistDatabase {
    private static final String URL = "jdbc:mysql://localhost:3306/songplaylist?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "Ajay@99@123";

    private Connection conn;

    public PlaylistDatabase() throws SQLException, ClassNotFoundException {
        // Load JDBC driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        // Connect to DB
        conn = DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public List<String> loadSongs() throws SQLException {
        List<String> songs = new ArrayList<>();
        String query = "SELECT song_name FROM playlist_songs ORDER BY id ASC";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                songs.add(rs.getString("song_name"));
            }
        }
        return songs;
    }

    public void addSong(String songName) throws SQLException {
        String query = "INSERT IGNORE INTO playlist_songs (song_name) VALUES (?)";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, songName);
            pstmt.executeUpdate();
        }
    }

    public void deleteSong(String songName) throws SQLException {
        String query = "DELETE FROM playlist_songs WHERE song_name = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, songName);
            pstmt.executeUpdate();
        }
    }
    public void clearAllSongs() throws SQLException {
        String query = "TRUNCATE TABLE playlist_songs"; // or "DELETE FROM playlist_songs"
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(query);
        }
    }


    public void close() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println("Error closing DB connection: " + e.getMessage());
            }
        }
    }
}

