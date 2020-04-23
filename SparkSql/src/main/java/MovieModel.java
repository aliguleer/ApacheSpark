public class MovieModel {
    private String userId;
    private String movieId;
    private String ratings;
    private String timestamp;

    public MovieModel() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getRatings() {
        return ratings;
    }

    public void setRatings(String ratings) {
        this.ratings = ratings;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public MovieModel(String userId, String movieId, String ratings, String timestamp) {
        this.userId = userId;
        this.movieId = movieId;
        this.ratings = ratings;
        this.timestamp = timestamp;

    }
}
