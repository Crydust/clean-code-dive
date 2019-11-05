package videostore.horror;
// coding kata derived from the Video Store example in Refactoring (1 ed) by Martin Fowler

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
            // determine amounts for each line
            double price = rental.calculatePrice();
            totalFrequentRenterPoints += rental.calculateFrequentRenterPoints();
            // show figures line for this rental
            result += "\t" + rental.getMovie().getTitle() + "\t"
                    + price + "\n";
            totalPrice += price;
        }
        // add footer lines
        result += "Amount owed is " + totalPrice + "\n";
        result += "You earned " + totalFrequentRenterPoints
                + " frequent renter points";
        return result;
    }

}