"# Music_Playlist_Manager" 
# 🎵 Playlist Manager (Java + SQL + LRU Cache)

A lightweight **playlist manager application** built with **Java**, backed by an **SQL database**, and optimized with an **LRU (Least Recently Used) cache** for fast song lookups.  

##  Features
-  Manage **songs** (add, update, delete, fetch)
- Create and organize **playlists**
- Add or remove songs from playlists
- Maintain playlist **order/positions**
- **LRU cache** for recently played songs
- Track **play history** (optional)
- Works with **SQLite / MySQL / PostgreSQL**

---

## 🛠 Tech Stack
- **Java 17+**
- **SQL database** (default: SQLite, but can adapt to MySQL/Postgres)
- **JDBC** for database access
- **LinkedHashMap** for LRU cache implementation

---

## 📂 Project Structure
src/
├── dao/ # Data Access Objects (JDBC logic)
├── model/ # Song, Playlist, PlaylistItem entities
├── service/ # Playlist + Cache management
└── Main.java # Entry point (demo / CLI)
resources/
└── schema.sql # Database schema
