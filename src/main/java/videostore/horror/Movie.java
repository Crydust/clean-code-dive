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
        CHILDRENS,
        REGULAR,
        NEW_RELEASE
    }
}