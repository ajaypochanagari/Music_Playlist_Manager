"# Music_Playlist_Manager" 
# 🎵 Playlist Manager (Java + SQL + LRU Cache)

A simple **Playlist Manager Application** built in **Java** using a custom **Doubly Linked List** data structure.  
This project demonstrates how to efficiently manage songs in a playlist with operations like add, delete, search, sort, and clear.  

---

## Features
-  **Add songs** to playlist
- **Delete songs** by position or by name
- **Check if a song exists**
- **Display all songs** in order
- **Sort playlist alphabetically** (via Merge Sort on linked list)
- **Clear all songs**
- **Get playlist size**
- Efficient **head/tail tracking**

---

## 🛠 Tech Stack
- **Java 17+** (Core Java concepts)
- **Object-Oriented Design** (Encapsulation, Abstraction)
- **Data Structures**: Doubly Linked List
- (Optional future extension: SQL persistence + LRU Cache)

---

## 📂 Project Structure
src/
├── org/example/
│ ├── DoublyLinkedList.java # Main playlist data structure
│ ├── Node.java # Node class for DLL
│ ├── Song.java # Song entity
│ └── Main.java # Demo / Runner class

arduino
Copy code

---

## 📘 Core Class: `DoublyLinkedList`
The `DoublyLinkedList` class manages songs in the playlist.  

### Key Methods
```java
addSong(String songName)         // Add song to end
deleteByPosition(int pos)        // Delete song at a given position
deleteByName(String songName)    // Delete song by name
contains(String songName)        // Check if playlist contains song
display()                        // Print all songs
sort()                           // Sort playlist alphabetically
clearAll()                       // Remove all songs
size()                           // Get total number of songs
▶️ How to Run
1. Clone the repo
bash
Copy code
git clone https://github.com/your-username/java-playlist-manager.git
cd java-playlist-manager
2. Compile
bash
Copy code
javac -d out src/org/example/*.java
3. Run
bash
Copy code
java -cp out org.example.Main
💡 Example Usage (Main.java)
java
Copy code
public class Main {
    public static void main(String[] args) {
        DoublyLinkedList playlist = new DoublyLinkedList();

        playlist.addSong("Shape of You");
        playlist.addSong("Believer");
        playlist.addSong("Perfect");

        playlist.display(); // Show all songs

        playlist.deleteByName("Believer");
        playlist.display();

        playlist.addSong("Attention");
        playlist.sort();
        playlist.display();
    }
}
Output

--- Playlist Songs ---
Shape of You
Believer
Perfect

