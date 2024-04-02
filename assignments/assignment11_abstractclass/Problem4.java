package assignments.assignment11_abstractclass;

abstract class Movie {
    private String name;
    private String director;
    private double duration;

    public Movie(String name, String director, double duration) {
        this.name = name;
        this.director = director;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public abstract void getMovieDetails();
}


class RomComMovie extends Movie {
    private String leadActor;
    private String leadActress;

    public RomComMovie(String name, String director, double duration, String leadActor, String leadActress) {
        super(name, director, duration);
        this.leadActor = leadActor;
        this.leadActress = leadActress;
    }

    @Override
    public void getMovieDetails() {
        System.out.println("Movie name: " + super.getName());
        System.out.println("Director: " + super.getDirector());
        System.out.println("Duration: " + super.getDuration() + " minutes");
        System.out.println("Lead Actor: " + leadActor);
        System.out.println("Lead Actress: " + leadActress);
    }
}

class ThrillerMovie extends Movie{
    private String villain;
    private String hero;

    public ThrillerMovie(String name, String director, int duration, String villain, String hero) {
        super(name, director, duration);
        this.villain = villain;
        this.hero = hero;
    }

    @Override
    public void getMovieDetails() {
        System.out.println("Movie name: " + super.getName());
        System.out.println("Director: " + super.getDirector());
        System.out.println("Duration: " + super.getDuration() + " minutes");
        System.out.println("Villain: " + villain);
        System.out.println("Hero: " + hero);
    }
}

public class Problem4 {
    public static void main(String[] args) {
        Movie movie = new RomComMovie("Date Night", "Shawn Levy", 2.52, "Steve Carell", "Tina Fey");
        movie.getMovieDetails();

        System.out.println();

        movie = new ThrillerMovie("The Dark Knight", "Christopher Nolan", 3, "Heath Leger", "Christan Bale");
        movie.getMovieDetails();

        System.out.println();
    }
}
