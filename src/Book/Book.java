package Book;

public class Book {
    private int id,bookId, bestBookId, workId, ratingsCount, ratings1, ratings2, ratings3, ratings4, ratings5;
    private String isbn, isbn13, authors, originalPublicationYear, originalTitle, title, languageCode, imageUrl, smallImageUrl;
    private double averageRating;
    private boolean active = true;

    public Book(int id, int bookId, int bestBookId, int workId, int ratingsCount, int ratings1, int ratings2, int ratings3,
                int ratings4, int ratings5, String isbn, String isbn13, String authors, String originalPublicationYear,
                String originalTitle, String title, String languageCode, String imageUrl, String smallImageUrl,
                double averageRating, boolean active) {
        this.id=id;
        this.bookId = bookId;
        this.bestBookId = bestBookId;
        this.workId = workId;
        this.ratingsCount = ratingsCount;
        this.ratings1 = ratings1;
        this.ratings2 = ratings2;
        this.ratings3 = ratings3;
        this.ratings4 = ratings4;
        this.ratings5 = ratings5;
        this.isbn = isbn;
        this.isbn13 = isbn13;
        this.authors = authors;
        this.originalPublicationYear = originalPublicationYear;
        this.originalTitle = originalTitle;
        this.title = title;
        this.languageCode = languageCode;
        this.imageUrl = imageUrl;
        this.smallImageUrl = smallImageUrl;
        this.averageRating = averageRating;
        this.active = active;
    }

    public Book() {
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getBestBookId() {
        return bestBookId;
    }

    public void setBestBookId(int bestBookId) {
        this.bestBookId = bestBookId;
    }

    public String getOriginalPublicationYear() {
        return originalPublicationYear;
    }

    public void setOriginalPublicationYear(String originalPublicationYear) {
        this.originalPublicationYear = originalPublicationYear;
    }

    public int getWorkId() {
        return workId;
    }

    public void setWorkId(int workId) {
        this.workId = workId;
    }

    public int getRatingsCount() {
        return ratingsCount;
    }

    public void setRatingsCount(int ratingsCount) {
        this.ratingsCount = ratingsCount;
    }

    public int getRatings1() {
        return ratings1;
    }

    public void setRatings1(int ratings1) {
        this.ratings1 = ratings1;
    }

    public int getRatings2() {
        return ratings2;
    }

    public void setRatings2(int ratings2) {
        this.ratings2 = ratings2;
    }

    public int getRatings3() {
        return ratings3;
    }

    public void setRatings3(int ratings3) {
        this.ratings3 = ratings3;
    }

    public int getRatings4() {
        return ratings4;
    }

    public void setRatings4(int ratings4) {
        this.ratings4 = ratings4;
    }

    public int getRatings5() {
        return ratings5;
    }

    public void setRatings5(int ratings5) {
        this.ratings5 = ratings5;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getIsbn13() {
        return isbn13;
    }

    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getSmallImageUrl() {
        return smallImageUrl;
    }

    public void setSmallImageUrl(String smallImageUrl) {
        this.smallImageUrl = smallImageUrl;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
