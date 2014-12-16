import java.util.Comparator;

/**
 * @author Chieh Lee
 * @version 12.4.2013
 */
public class TheaterComparator implements Comparator<Theater> {

    /**
     * override compare method, base on comparing two theaters by their names
     * 
     * @param t1 theater 1
     * @param t2 theater 2
     * @return int
     */
    public int compare(Theater t1, Theater t2) {
        String k1 = t1.name;
        String k2 = t2.name;
        return k1.compareTo(k2);

    }

}