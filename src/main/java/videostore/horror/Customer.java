package videostore.horror;
// coding kata derived from the Video Store example in Refactoring (1 ed) by Martin Fowler

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class Customer {
    private final String customerName;
    private final List<Rental> rentals = new ArrayList<>();

    public Customer(String customerName) {
        this.customerName = customerName;
    }

    public void addRental(Rental rental) {
        Objects.requireNonNull(rental, "rental");
        rentals.add(rental);
    }

    public String statement() {
        return statement(customerName, rentals);
    }

    public static String statement(String customerName, List<Rental> rentals) {
        String result = formatHeader(customerName);
        for (Rental rental : rentals) {
            result += formatBodyLine(rental);
        }
        result += formatFooter(
                calculateTotalPrice(rentals),
                calculateTotalFrequentRenterPoints(rentals));
        return result;
    }

    private static double calculateTotalPrice(List<Rental> rentals) {
        return rentals.stream()
                    .mapToDouble(Rental::calculatePrice)
                    .sum();
    }

    private static int calculateTotalFrequentRenterPoints(List<Rental> rentals) {
        return rentals.stream()
                .mapToInt(Rental::calculateFrequentRenterPoints)
                .sum();
    }

    private static String formatHeader(String customerName) {
        return "Rental Record for " + customerName + "\n";
    }

    private static String formatBodyLine(Rental rental) {
        return "\t" + rental.getMovie().getTitle() + "\t"
                + rental.calculatePrice() + "\n";
    }

    private static String formatFooter(double totalPrice, int totalFrequentRenterPoints) {
        return "Amount owed is " + totalPrice + "\n"
                + "You earned " + totalFrequentRenterPoints + " frequent renter points";
    }

}