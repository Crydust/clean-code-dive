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
        CHILDRENS {
            @Override
            double calculatePrice(int daysRented) {
                double price = 1.5;
                if (daysRented > 3) {
                    // add late fee
                    price += (daysRented - 3) * 1.5;
                }
                return price;
            }
        },
        REGULAR {
            @Override
            double calculatePrice(int daysRented) {
                double price = 2.0;
                if (daysRented > 2) {
                    // add late fee
                    price += (daysRented - 2) * 1.5;
                }
                return price;
            }
        },
        NEW_RELEASE {
            @Override
            double calculatePrice(int daysRented) {
                return daysRented * 3;
            }
        };

        abstract double calculatePrice(int daysRented);
    }
}