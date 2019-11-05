package videostore.horror;

import java.util.Objects;

import static videostore.horror.Movie.Type.NEW_RELEASE;

class Rental {
    private final Movie movie;
    private final int daysRented;

    public Rental(Movie movie, int daysRented) {
        if (daysRented <= 0) {
            throw new IllegalArgumentException("Negative days rented");
        }
        this.movie = movie;
        this.daysRented = daysRented;
    }

    public int calculateFrequentRenterPoints() {
        int frequentRenterPoints = 1;
        final boolean isNewRelease = getMovie().getType() == NEW_RELEASE;
        if (isNewRelease && daysRented > 1) {
            // bonus for a two day new release rental
            frequentRenterPoints++;
        }
        return frequentRenterPoints;
    }

    public double calculatePrice() {
        return calculatePrice(movie.getType());
    }

    private double calculatePrice(Movie.Type type) {
        Objects.requireNonNull(type, "type");
        return type.calculatePrice(daysRented);
    }

    public Movie getMovie() {
        return movie;
    }

}
