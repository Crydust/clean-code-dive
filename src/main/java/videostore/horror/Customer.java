package videostore.horror;
// coding kata derived from the Video Store example in Refactoring (1 ed) by Martin Fowler

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static videostore.horror.Movie.Type.NEW_RELEASE;

class Customer {
    private final String name;
    private final List<Rental> rentals = new ArrayList<>();

    public Customer(String name) {
        this.name = name;
    }

    public void addRental(Rental rental) {
        Objects.requireNonNull(rental, "rental");
        rentals.add(rental);
    }

    public String statement() {
        double totalPrice = 0;
        int frequentRenterPoints = 0;
        String result = "Rental Record for " + name + "\n";
        for (Rental rental : rentals) {
            Movie movie = rental.getMovie();
            // determine amounts for each line
            int daysRented = rental.getDaysRented();
            double price = 0;
            switch (rental.getMovie().getPriceCode()) {
                case REGULAR:
                    price += 2;
                    if (rental.getDaysRented() > 2)
                        price += (rental.getDaysRented() - 2) * 1.5;
                    break;
                case NEW_RELEASE:
                    price += rental.getDaysRented() * 3;
                    break;
                case CHILDRENS:
                    price += 1.5;
                    if (rental.getDaysRented() > 3)
                        price += (rental.getDaysRented() - 3) * 1.5;
                    break;
            }
            // add frequent renter points
            frequentRenterPoints++;
            // add bonus for a two day new release rental
            if ((movie.getPriceCode() == NEW_RELEASE)
                    && daysRented > 1)
                frequentRenterPoints++;
            // show figures line for this rental
            result += "\t" + movie.getTitle() + "\t"
                    + price + "\n";
            totalPrice += price;
        }
        // add footer lines
        result += "Amount owed is " + totalPrice + "\n";
        result += "You earned " + frequentRenterPoints
                + " frequent renter points";
        return result;
    }
}