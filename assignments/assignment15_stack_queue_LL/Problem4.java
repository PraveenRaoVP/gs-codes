package assignments.assignment15_stack_queue_LL;

class Song {
    int id;
    String name;
    String artist;
    int duration;
    int noOfPlays;
    Song nextSong;

    public Song(int id,String name, String artist, int duration) {
        this.id = id;
        this.name = name;
        this.artist = artist;
        this.duration = duration;
        this.noOfPlays = 0;
        this.nextSong = null;
    }

    public void play() {
        System.out.println("Playing Song: " + name + " by " + artist);
        noOfPlays++;
    }

    public String toString() {
        return id+ ". " + name + " by " + artist + " (" + duration + "s)";
    }
}

class PlayList {
    Song head;
    Song tail;

    public PlayList() {
        head = null;
        tail = null;
    }

    public void addSongAtBeginning(String name, String artist, int duration) {
        Song newSong = new Song(1, name, artist, duration);

        if(head == null) {
            head = newSong;
            tail = newSong;
        } else {
            newSong.nextSong = head;
            tail = head;
            head = newSong;
        }
        adjustIds(head);
        System.out.println("Song added to the beginning of the playlist: " + newSong);
    }

    public void adjustIds(Song song) {
        Song temp = song;
        int startId = temp.id;

        while(temp!=null) {
            temp.id = startId;
            startId++;
            temp = temp.nextSong;
        }
    }

    public void addSongToEnd(int id, String name, String artist, int duration) {
        Song newSong = new Song(tail.id+1, name, artist, duration);

        if(head == null) {
            head = newSong;
        } else {
            tail.nextSong = newSong;
            tail = newSong;
        }
        System.out.println("Song added to the end of the playlist: " + newSong);
    }

    public void addSongAtMiddle(String name, String artist, int duration, int position) {
        Song newSong = new Song(position, name, artist, duration);

        if(position == 1) {
            addSongAtBeginning(name, artist, duration);
        } else {
            Song temp = head;
            for(int i = 1; i < position - 1; i++) {
                temp = temp.nextSong;
            }
            newSong.nextSong = temp.nextSong;
            temp.nextSong = newSong;
            adjustIds(temp.nextSong);
            System.out.println("Song added to the middle of the playlist: " + newSong);
        }
    }

    public void displayPlaylist() {
        Song temp = head;
        System.out.println("Playlist: ");
        while(temp != null) {
            System.out.println(temp);
            temp = temp.nextSong;
        }
    }
}

public class Problem4 {
    public static void main(String[] args) {
        PlayList playlist = new PlayList();
        playlist.addSongAtBeginning("Song1", "Artist1", 200);
        playlist.addSongAtBeginning("Song2", "Artist2", 300);
        playlist.displayPlaylist();
        playlist.addSongAtBeginning("Song3", "Artist3", 400);
        playlist.displayPlaylist();
        playlist.addSongAtMiddle("Song4", "Artist1", 300, 2);
        playlist.displayPlaylist();
    }    
}
