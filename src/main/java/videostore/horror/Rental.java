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
        double price = 0;
        switch (movie.getPriceCode()) {
            case REGULAR:
                price += 2;
                if (getDaysRented() > 2)
                    price += (getDaysRented() - 2) * 1.5;
                break;
            case NEW_RELEASE:
                price += getDaysRented() * 3;
                break;
            case CHILDRENS:
                price += 1.5;
                if (getDaysRented() > 3)
                    price += (getDaysRented() - 3) * 1.5;
                break;
        }
        return price;
    }

    public Movie getMovie() {
        return movie;
    }

    public int getDaysRented() {
        return daysRented;
    }
}
