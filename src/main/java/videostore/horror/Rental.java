package videostore.horror;

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
        switch (type) {
            case REGULAR:
                return calculateRegularPrice();
            case NEW_RELEASE:
                return calculateNewReleasePrice();
            case CHILDRENS:
                return calculateChildrensPrice();
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
    }

    private double calculateRegularPrice() {
        double price = 2.0;
        if (daysRented > 2) {
            // add late fee
            price += (daysRented - 2) * 1.5;
        }
        return price;
    }

    private double calculateNewReleasePrice() {
        return daysRented * 3;
    }

    private double calculateChildrensPrice() {
        double price = 1.5;
        if (daysRented > 3) {
            // add late fee
            price += (daysRented - 3) * 1.5;
        }
        return price;
    }

    public Movie getMovie() {
        return movie;
    }

}
