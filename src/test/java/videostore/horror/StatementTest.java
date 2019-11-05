package videostore.horror;

import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static videostore.horror.Movie.Type.CHILDRENS;
import static videostore.horror.Movie.Type.NEW_RELEASE;
import static videostore.horror.Movie.Type.REGULAR;

public class StatementTest {

    @Test
    public void characterizationTest() {
        final List<Rental> rentals = asList(
                new Rental(new Movie("Star Wars", NEW_RELEASE), 6),
                new Rental(new Movie("Sofia", CHILDRENS), 7),
                new Rental(new Movie("Inception", REGULAR), 5));

        String expected = "Rental Record for John Doe\n"
                + "	Star Wars	18.0\n"
                + "	Sofia	7.5\n"
                + "	Inception	6.5\n"
                + "Amount owed is 32.0\n"
                + "You earned 4 frequent renter points";
        
        assertEquals(expected, new Statement("John Doe", rentals).toString());
    }
}
