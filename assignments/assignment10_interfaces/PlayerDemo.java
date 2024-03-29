package assignments.assignment10_interfaces;

/*Define an interface named Playable with methods like play() , pause() , and stop() . Implement this interface in classes representing different types of music players such as MP3Player , CDPlayer , and StreamingPlayer . Allow the user to control the playback of these players through the implemented interface. */

interface Playable {
    void play();
    void pause();
    void stop();
}

class MP3Player implements Playable {
    @Override
    public void play() {
        System.out.println("Playing MP3 Player");
    }

    @Override
    public void pause() {
        System.out.println("Pausing MP3 Player");
    }

    @Override
    public void stop() {
        System.out.println("Stopping MP3 Player");
    }
}

class CDPlayer implements Playable {

    @Override
    public void play() {
        System.out.println("Playing CD Player");
    }

    @Override
    public void pause() {
        System.out.println("Pausing CD Player");
    }

    @Override
    public void stop() {
        System.out.println("Stopping CD Player");
    }
    
}

class StreamingPlayer implements Playable {

    @Override
    public void play() {
        System.out.println("Playing Streaming Player");
    }

    @Override
    public void pause() {
        System.out.println("Pausing Streaming Player");
    }

    @Override
    public void stop() {
        System.out.println("Stopping Streaming Player");
    }   
}

public class PlayerDemo {
    public static void main(String[] args) {
        Playable mp3Player = new MP3Player();
        mp3Player.play();
        mp3Player.pause();
        mp3Player.stop();

        Playable cdPlayer = new CDPlayer();
        cdPlayer.play();
        cdPlayer.pause();
        cdPlayer.stop();

        Playable streamingPlayer = new StreamingPlayer();
        streamingPlayer.play();
        streamingPlayer.pause();
        streamingPlayer.stop();
    }
}
