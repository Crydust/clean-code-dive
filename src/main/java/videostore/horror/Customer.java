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
        String result = formatHeader();
        for (Rental rental : rentals) {
            result += formatBodyLine(rental);
        }
        result += formatFooter(
                calculateTotalPrice(),
                calculateTotalFrequentRenterPoints());
        return result;
    }

    private double calculateTotalPrice() {
        return rentals.stream()
                    .mapToDouble(Rental::calculatePrice)
                    .sum();
    }

    private int calculateTotalFrequentRenterPoints() {
        return rentals.stream()
                .mapToInt(Rental::calculateFrequentRenterPoints)
                .sum();
    }

    private String formatHeader() {
        return "Rental Record for " + name + "\n";
    }

    private String formatBodyLine(Rental rental) {
        return "\t" + rental.getMovie().getTitle() + "\t"
                + rental.calculatePrice() + "\n";
    }

    private static String formatFooter(double totalPrice, int totalFrequentRenterPoints) {
        return "Amount owed is " + totalPrice + "\n"
                + "You earned " + totalFrequentRenterPoints + " frequent renter points";
    }

}