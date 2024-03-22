package assignments.assignment7_classesandobjects_encapulation;

class Player {
    private String name;
    private int jerseyNumber;
    private String position;

    public Player(String name, int jerseyNumber, String position) {
        this.name = name;
        this.jerseyNumber = jerseyNumber;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public int getJerseyNumber() {
        return jerseyNumber;
    }

    public String getPosition() {
        return position;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setJerseyNumber(int jerseyNumber) {
        this.jerseyNumber = jerseyNumber;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", jerseyNumber=" + jerseyNumber +
                ", position='" + position + '\'' +
                '}';
    }

    public void playGame() {
        System.out.println(this.getName() + " is playing");
    }
    
    public void train() {
        System.out.println(this.getName() + " is training");
    } 
}

public class Team {
    private String teamName;
    private String city;
    private String division;
    private Player[] players;

    public Team(String teamName, String city, String division, Player[] players) {
        this.teamName = teamName;
        this.city = city;
        this.division = division;
        this.players = players;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getCity() {
        return city;
    }

    public String getDivision() {
        return division;
    }

    public Player[] getPlayers() {
        return players;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }

    @Override
    public String toString() {
        return "Team{" +
                "teamName='" + teamName + '\'' +
                ", city='" + city + '\'' +
                ", division='" + division + '\'' +
                ", players=" + players +
                '}';
    }

    public void playGame() {
        for (Player player : players) {
            player.playGame();
        }
    }

    public void hireCoach() {
        System.out.println("Hiring coach for " + this.getTeamName());
    }
}
