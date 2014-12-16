import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Chieh Lee
 * @version 11.23.2013
 */
public class Movie {
    /**
     * name of this movie
     */
    String name;
    /**
     * HashMap has key theater and value ArrayList<ShowTime>,
     * which are theater showtime association of this movie
     */
    HashMap<Theater, ArrayList<ShowTime>> k;
    /**
     * duration of the movie
     */
    int runningTime;
    /**
     * assoication of price option and price of this movie
     */
    HashMap<String, Integer> priceOption;

    /**
     * constructor of Movie
     * @param name movie name
     * @param k theater showtime association of this movie
     * @param runningTime duration of this movie
     */
    Movie(String name, HashMap<Theater, ArrayList<ShowTime>> k,
            int runningTime) {
        this.name = name;
        this.k = k;
        this.runningTime = runningTime;
    }

    /**
     * overloaded constructor of Movie
     * @param name movie name
     * @param k theater showtime association of this movie
     * @param runningTime duration of this movie
     * @param priceOption assoication of price option and price of this movie
     */
    Movie(String name, HashMap<Theater, ArrayList<ShowTime>> k,
            int runningTime, HashMap<String, Integer> priceOption) {
        this.name = name;
        this.k = k;
        this.runningTime = runningTime;
        this.priceOption = priceOption;
    }

    /**
     * static method for creating a new movie instance with given
     * movie name String and given int movie duration
     * @param s movie name
     * @param i movie duration
     * @return Movie
     */
    public static Movie create(String s, int i) {
        return new Movie(s, new HashMap<Theater, ArrayList<ShowTime>>(), i,
                new HashMap<String, Integer>());
    }

    /**
     * put showtime to associated movie/theater accordingly
     * @param t this theater
     * @param s startTime
     * @param c remaining seats
     */
    public void putShow(Theater t, String s, int c) {
        Integer i = this.runningTime;
        ArrayList<ShowTime> slist = new ArrayList<ShowTime>();
        ShowTime st = new ShowTime(s, i.toString(), c);
        if (!k.containsKey(t)) {
            slist.add(st);
            k.put(t, slist);
        }
        else {
            k.get(t).add(st);
        }
    }

    /**
     * override toString method, return this movie name
     * @return String
     */
    public String toString() {
        return this.name;
    }

}
