import java.util.ArrayList;

/**
 * @author Chieh Lee
 * @version 11.23.2013
 */
public class Cinema {
    /**
     * ArrayList of movies that this cinema have;
     */
    ArrayList<Movie> movies;
    /**
     * ArrayList of theater that this cinema has;
     */
    ArrayList<Theater> theaters = new ArrayList<Theater>();

    /**
     * constructor of Cinema
     * 
     * @param movies
     *            ArrayList of movies
     */
    Cinema(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    /**
     * overloaded constructor of Cinema
     * 
     * @param movies
     *            ArrayList of movies
     * @param theaters
     *            ArrayList of theaters
     */
    Cinema(ArrayList<Movie> movies, ArrayList<Theater> theaters) {
        this.movies = movies;
        this.theaters = theaters;
    }

    /**
     * get this movies of this cinema
     * 
     * @return ArrayList<Movies>
     */
    public ArrayList<Movie> getMovie() {
        return this.movies;
    }

    /**
     * return a ArrayList<String> with all movie names
     * 
     * @return ArrayList<String>
     */
    ArrayList<String> movieNameList() {
        ArrayList<String> slist = new ArrayList<String>();
        for (int i = 0; i < movies.size(); i++) {
            slist.add(movies.get(i).name);
        }
        return slist;
    }

    /**
     * put price of each price option in the HashMap priceOption of each movie.
     * One key (price option) can only give one price, however, different movies
     * can have different price options, namely, each movie has its own HashMap
     * priceOption
     * 
     * @param s
     *            price option (ex Adult, Children)
     * @param i
     *            price of this price option
     */
    void priceMap(String s, Integer i) {
        for (int j = 0; j < movies.size(); j++) {
            this.movies.get(j).priceOption.put(s, i);
        }
    }

    /**
     * return a ArrayList<String> with all theater names
     * 
     * @return ArrayList<String>
     */
    protected ArrayList<String> theaterNameList() {
        ArrayList<String> slist = new ArrayList<String>();
        for (int i = 0; i < theaters.size(); i++) {
            slist.add(theaters.get(i).name);
        }
        return slist;
    }
}
