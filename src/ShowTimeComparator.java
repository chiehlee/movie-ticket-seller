import java.util.Comparator;

/**
 * @author Chieh Lee
 * @version 12.4.2013
 */
public class ShowTimeComparator implements Comparator<ShowTime> {

    /**
     * override compare method, compare with two showtime by their
     * converted-to-int Start time
     * 
     * @param st1 showtime 1
     * @param st2 showtime 2
     * @return int
     */
    public int compare(ShowTime st1, ShowTime st2) {
        int i1 = Integer.valueOf(st1.startTime);
        int i2 = Integer.valueOf(st2.startTime);
        return i1 - i2;

    }
}