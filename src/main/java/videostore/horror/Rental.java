package videostore.horror;

import static videostore.horror.Movie.Type.NEW_RELEASE;

public class Rental {
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
        final boolean isNewRelease = getMovie().getPriceCode() == NEW_RELEASE;
        if (isNewRelease && daysRented > 1) {
            // bonus for a two day new release rental
            frequentRenterPoints++;
        }
        return frequentRenterPoints;
    }

    public double calculatePrice() {
        return calculatePrice(movie.getPriceCode());
    }

    private double calculatePrice(Movie.Type priceCode) {
        switch (priceCode) {
            case REGULAR:
                return calculateRegularPrice();
            case NEW_RELEASE:
                return calculateNewReleasePrice();
            case CHILDRENS:
                return calculateChildrensPrice();
            default:
                throw new IllegalStateException("Unexpected value: " + priceCode);
        }
    }

    private double calculateRegularPrice() {
        if (daysRented <= 2) {
            return 2;
        }
        return 2 + (daysRented - 2) * 1.5;
    }

    private double calculateNewReleasePrice() {
        return daysRented * 3;
    }

    private double calculateChildrensPrice() {
        if (daysRented <= 3) {
            return 1.5;
        }
        return 1.5 + (daysRented - 3) * 1.5;
    }

    public Movie getMovie() {
        return movie;
    }

}
