public class Book {

    private final String id;
    private final String title;
    private final String author;
    private final double rating;

    public Book(String id, String title, String author, double rating) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.rating = rating;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public double getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", rating=" + rating +
                '}';
    }
}
