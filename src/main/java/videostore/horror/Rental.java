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

    public double calculateRentalPrice() {
        switch (movie.getPriceCode()) {
            case REGULAR: {
                double price = 0;
                price += 2;
                if (daysRented > 2)
                    price += (daysRented - 2) * 1.5;
                return price;
            }
            case NEW_RELEASE: {
                double price = 0;
                price += daysRented * 3;
                return price;
            }
            case CHILDRENS: {
                double price = 0;
                price += 1.5;
                if (daysRented > 3)
                    price += (daysRented - 3) * 1.5;
                return price;
            }
            default:
                throw new IllegalStateException("Unexpected value: " + movie.getPriceCode());
        }
    }

    public Movie getMovie() {
        return movie;
    }

    public int getDaysRented() {
        return daysRented;
    }
}
