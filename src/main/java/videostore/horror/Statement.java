package videostore.horror;

import java.util.List;

public class Statement {
    private final String customerName;
    private final List<Rental> rentals;

    public Statement(String customerName, List<Rental> rentals) {
        this.customerName = customerName;
        this.rentals = rentals;
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

    public String getCustomerName() {
        return customerName;
    }

    public List<Rental> getRentals() {
        return rentals;
    }

    public String toString() {
        String result = formatHeader(getCustomerName());
        for (Rental rental : getRentals()) {
            result += formatBodyLine(rental);
        }
        result += formatFooter(
                calculateTotalPrice(getRentals()),
                calculateTotalFrequentRenterPoints(getRentals()));
        return result;
    }
}
