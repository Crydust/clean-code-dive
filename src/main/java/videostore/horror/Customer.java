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
	};

	public void addRental(Rental rental) {
		Objects.requireNonNull(rental, "rental");
		rentals.add(rental);
	}

	public String getName() {
		return name;
	}

	public String statement() {
		double totalAmount = 0;
		int frequentRenterPoints = 0;
		String result = "Rental Record for " + getName() + "\n";
		for (Rental rental : rentals) {
			double thisAmount = 0;
			Movie each = (Movie) rental.getMovie();
			// determine amounts for each line
			int dr = rental.getDaysRented();
			switch (each.getPriceCode()) {
                case REGULAR:
                    thisAmount += 2;
                    if (dr > 2)
                        thisAmount += (dr - 2) * 1.5;
                    break;
                case NEW_RELEASE:
                    thisAmount += dr * 3;
                    break;
                case CHILDRENS:
                    thisAmount += 1.5;
                    if (dr > 3)
                        thisAmount += (dr - 3) * 1.5;
                    break;
			}
			// add frequent renter points
			frequentRenterPoints++;
			// add bonus for a two day new release rental
			if ((each.getPriceCode() == NEW_RELEASE)
					&& dr > 1)
				frequentRenterPoints++;
			// show figures line for this rental
			result += "\t" + each.getTitle() + "\t"
					+ String.valueOf(thisAmount) + "\n";
			totalAmount += thisAmount;
		}
		// add footer lines
		result += "Amount owed is " + String.valueOf(totalAmount) + "\n";
		result += "You earned " + String.valueOf(frequentRenterPoints)
				+ " frequent renter points";
		return result;
	}
}