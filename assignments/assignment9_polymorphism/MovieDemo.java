package assignments.assignment9_polymorphism;
// 5. Create hierarchy from Movie class with Child classes of different genre movies like RomComMovie, ThrillerMovie, etc. Demonstrate polymorphism using them.

class Movie {
    private String name;
    private String director;
    private double duration;

    public Movie(String name, String director, double duration) {
        this.name = name;
        this.director = director;
        this.duration = duration;
    }

    public void getMovieDetails() {
        System.out.println("Movie name: " + name);
        System.out.println("Director: " + director);
        System.out.println("Duration: " + duration + " minutes");
    }
}


class RomComMovie extends Movie {
    private String leadActor;
    private String leadActress;

    public RomComMovie(String name, String director, double duration, String leadActor, String leadActress) {
        super(name, director, duration);
        this.leadActor = leadActor;
        this.leadActress = leadActress;
    }

    public void getMovieDetails() {
        super.getMovieDetails();
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

    public void getMovieDetails() {
        super.getMovieDetails();
        System.out.println("Villain: " + villain);
        System.out.println("Hero: " + hero);
    }
}

public class MovieDemo {
    public static void main(String[] args) {
        Movie movie = new RomComMovie("Date Night", "Shawn Levy", 2.52, "Steve Carell", "Tina Fey");
        movie.getMovieDetails();

        System.out.println();

        movie = new ThrillerMovie("The Dark Knight", "Christopher Nolan", 3, "Heath Leger", "Christan Bale");
        movie.getMovieDetails();

        System.out.println();

    }
}