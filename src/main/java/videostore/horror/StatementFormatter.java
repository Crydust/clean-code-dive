package videostore.horror;

import java.util.List;

public class StatementFormatter {
    private final String customerName;
    private final List<Rental> rentals;

    public StatementFormatter(String customerName, List<Rental> rentals) {
        this.customerName = customerName;
        this.rentals = rentals;
    }

    public static String statement(StatementFormatter statementFormatter) {
        String result = formatHeader(statementFormatter.getCustomerName());
        for (Rental rental : statementFormatter.getRentals()) {
            result += formatBodyLine(rental);
        }
        result += formatFooter(
                calculateTotalPrice(statementFormatter.getRentals()),
                calculateTotalFrequentRenterPoints(statementFormatter.getRentals()));
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

    public String getCustomerName() {
        return customerName;
    }

    public List<Rental> getRentals() {
        return rentals;
    }
}
