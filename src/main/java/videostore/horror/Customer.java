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
        int totalFrequentRenterPoints = 0;
        String result = "Rental Record for " + name + "\n";
        for (Rental rental : rentals) {
            Movie movie = rental.getMovie();
            // determine amounts for each line
            int daysRented = rental.getDaysRented();
            double price = rental.calculatePrice();
            int frequentRenterPoints = calculateFrequentRenterPoints(rental);
            totalFrequentRenterPoints += frequentRenterPoints;
            // show figures line for this rental
            result += "\t" + movie.getTitle() + "\t"
                    + price + "\n";
            totalPrice += price;
        }
        // add footer lines
        result += "Amount owed is " + totalPrice + "\n";
        result += "You earned " + totalFrequentRenterPoints
                + " frequent renter points";
        return result;
    }

    private static int calculateFrequentRenterPoints(Rental rental) {
        // add frequent renter points
        int frequentRenterPoints = 1;
        // add bonus for a two day new release rental
        if ((rental.getMovie().getPriceCode() == NEW_RELEASE)
                && rental.getDaysRented() > 1)
            frequentRenterPoints++;
        return frequentRenterPoints;
    }

}