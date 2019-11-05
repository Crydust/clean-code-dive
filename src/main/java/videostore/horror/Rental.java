package videostore.horror;

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
        double price = 0;
        price += 2;
        if (daysRented > 2)
            price += (daysRented - 2) * 1.5;
        return price;
    }

    private double calculateNewReleasePrice() {
        double price = 0;
        price += daysRented * 3;
        return price;
    }

    private double calculateChildrensPrice() {
        double price = 0;
        price += 1.5;
        if (daysRented > 3)
            price += (daysRented - 3) * 1.5;
        return price;
    }

    public Movie getMovie() {
        return movie;
    }

    public int getDaysRented() {
        return daysRented;
    }
}
