package assignments.assignment7_classesandobjects_encapulation;

import java.util.List;

public class Movie {
    private int movieId;
    private String movieName;
    private String movieDirector;
    private List<String> castMembers;
    private String releaseDate;
    private String genre;
    private List<String> availableLanguages;
    private String duration;
    private String rating;

    public int getMovieId() {
        return movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public String getMovieDirector() {
        return movieDirector;
    }

    public List<String> getCastMembers() {
        return castMembers;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getGenre() {
        return genre;
    }

    public List<String> getAvailableLanguages() {
        return availableLanguages;
    }

    public String getDuration() {
        return duration;
    }

    public String getRating() {
        return rating;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public void setMovieDirector(String movieDirector) {
        this.movieDirector = movieDirector;
    }

    public void setCastMembers(List<String> castMembers) {
        this.castMembers = castMembers;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setAvailableLanguages(List<String> availableLanguages) {
        this.availableLanguages = availableLanguages;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
