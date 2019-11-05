package videostore.horror;

public class Movie {
    private final String title;
    private final Type type;

    public Movie(String title, Type type) {
        this.title = title;
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    enum Type {
        CHILDRENS(1.5, 3, 1.5),
        REGULAR(2.0, 2, 1.5),
        NEW_RELEASE(0.0, 0, 3.0);

        private final double basePrice;
        private final int baseDays;
        private final double feePerDay;

        Type(double basePrice, int baseDays, double feePerDay) {
            this.basePrice = basePrice;
            this.baseDays = baseDays;
            this.feePerDay = feePerDay;
        }

        double calculatePrice(int daysRented) {
            if (daysRented > baseDays) {
                return basePrice + (daysRented - baseDays) * feePerDay;
            }
            return basePrice;
        }
    }
}