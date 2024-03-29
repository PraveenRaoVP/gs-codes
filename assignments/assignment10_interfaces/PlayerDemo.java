package assignments.assignment10_interfaces;

import java.util.Scanner;

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
        System.out.println("Enter your choice: ");
        System.out.println("1. MP3 Player");
        System.out.println("2. CD Player");
        System.out.println("3. Streaming Player");
        Scanner sc = new Scanner(System.in);
        Playable player = null;

        switch (sc.nextInt()) {
            case 1:
                player = new MP3Player();
                break;
            case 2:
                player = new CDPlayer();
                break;
            case 3:
                player = new StreamingPlayer();
                break;
            default:
                System.out.println("Invalid choice");
                break;
        }
        showMenu();
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                player.play();
                break;
            case 2:
                player.pause();
                break;
            case 3:
                player.stop();
                break;
            case 4:
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice");
                break;
        }
        sc.close();
    }

    public static void showMenu() {
        System.out.println("Enter your choice: ");
        System.out.println("1. Play");
        System.out.println("2. Pause");
        System.out.println("3. Stop");
        System.out.println("4. Exit");
    }
}
