import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;

/**
 * algorithm class for data initialization
 * 
 * @author Chieh Lee
 * @version 12.4.2013
 */
public class InitialData {

    /**
     * constructor of InitialData
     */
    InitialData() { // this constructor is empty
    }

    /**
     * instance of TicketSales class
     */
    static TicketSales prog = new TicketSales();

    /**
     * temp variable for saving current movie;
     */
    static Movie movieSave = TicketSales.movieSave;
    /**
     * temp variable for saving current theater;
     */
    static Theater theaterSave = TicketSales.theaterSave;
    /**
     * temp variable for saving current ShowTime;
     */
    static ShowTime showTimeSave = TicketSales.showTimeSave;
    /**
     * temp variable for saving this order information;
     */
    static String currentOrder = TicketSales.currentOrder;

    
    /**
     * first step for initialing a data from a scanner, it will check the first
     * line of given scanner equals to "Movies", if yes then keep processing if
     * not then stop the program and give error messages.
     * 
     * @param sc
     *            given scanner
     * @throws FileNotFoundException
     */
    static void initData(Scanner sc) throws FileNotFoundException {
        // initial the global data;
        TicketSales.mlist = new ArrayList<Movie>();
        TicketSales.ctemp = new Cinema(TicketSales.mlist);

        /*
         * System.out.print("Please enter file name: "); Scanner fileinput = new
         * Scanner(System.in); File file = new File(fileinput.nextLine());
         * Scanner sc = new Scanner(file);
         */

        try {
            while (sc.hasNextLine()) {
                String k = sc.nextLine();
                if (k.equals("Movies")) {
                    initialMovies(sc);
                    break;
                }
                else {
                    System.out.println("bad initialiazation file (-1)");
                    // UI.intro();
                }
            }
        }
        catch (NoSuchElementException e) {
            System.out.println("bad initialization file (0)");
        }
    }

    /**
     * the second step of initializing data, it will save all the movie and the
     * movie duration associate with the movie, and write them into the ctemp,
     * and pass a scanner with the rest information to next method.
     * 
     * @param sc
     *            given scanner from last method
     */
    private static void initialMovies(Scanner sc) {
        try {
            while (sc.hasNextLine()) {
                String k = sc.nextLine();
                if (k.equals("Theaters")) {
                    break;
                }
                Scanner sc2 = new Scanner(k).useDelimiter(":");
                String s = sc2.next();
                int i = Integer.valueOf(sc2.next());
                TicketSales.ctemp.movies.add(Movie.create(s, i));
            }
            initialTheaters(sc);
        }
        catch (NoSuchElementException e) {
            System.out.println("bad initialization file (2)");
            // UI.intro();
        }
        catch (StringIndexOutOfBoundsException e) {
            System.out.println("bad initialization file (3)");
            // UI.intro();
        }
        catch (NumberFormatException e) {
            System.out.println("bad initialization file (3)");
            // UI.intro();
        }

    }

    /**
     * initializing the theater data and its capacity and write them into ctemp,
     * give error message if there are any bad inputs, and pass a scanner with
     * rest information to the next method.
     * 
     * @param sc
     *            given scanner from last method
     */
    private static void initialTheaters(Scanner sc) {
        try {
            while (sc.hasNextLine()) {
                String k = sc.nextLine();
                if (k.equals("Shows")) {
                    break;
                }
                Scanner sc2 = new Scanner(k).useDelimiter(":");
                String s = sc2.next();
                int i = Integer.valueOf(sc2.next());
                if (goodTheaterEntry(TicketSales.c, Theater.create(s, i))
                        && goodTheaterEntry(TicketSales.ctemp, Theater.create(s, i))) {
                    TicketSales.ctemp.theaters.add(Theater.create(s, i));
                }
                else {
                    System.out.println("Theater capacity confilict: " + k);
                    System.out.print("Please try again or use different file");
                    System.out.println();
                    break;
                }
            }
            initialShows(sc);
        }
        catch (NoSuchElementException e) {
            System.out.println("bad initialization file (4)");
            // UI.intro();
        }
        catch (StringIndexOutOfBoundsException e) {
            System.out.println("bad initialization file (5)");
            // UI.intro();
        }
    }

    /**
     * this method checks whether the new theater entry is good new entry of
     * theater can have a name is not contained in the list with any capacity,
     * if the the theater name is contained in the theater list, check its
     * capacity is the same and return a boolean
     * 
     * @param c2
     *            this cinema
     * @param t
     *            this theater going to be added
     * @return boolean
     */
    private static boolean goodTheaterEntry(Cinema c2, Theater t) {
        boolean b = true;
        for (int i = 0; i < c2.theaters.size(); i++) {
            Theater tt = c2.theaters.get(i);
            if (tt.name.equals(t.name)) {
                b = (tt.capacity == t.capacity);
            }
        }
        return b;
    }

    /**
     * initializing the show times, check for any conflicts and throw error
     * message if there are some, and pass the scanner with rest information to
     * next method
     * 
     * @param sc
     *            this scanner from last method
     */
    private static void initialShows(Scanner sc) {
        try {
            while (sc.hasNextLine()) {
                String k = sc.nextLine();
                if (k.equals("Prices")) {
                    break;
                }
                Scanner sc2 = new Scanner(k).useDelimiter(",");
                int movieidx = Integer.valueOf(sc2.next()) - 1;
                int theateridx = Integer.valueOf(sc2.next()) - 1;
                String startTime = sc2.next();
                Theater thisTheater = TicketSales.ctemp.theaters.get(theateridx);
                if (noTimeConflict(TicketSales.c, startTime, thisTheater)
                        && noTimeConflict(TicketSales.ctemp, startTime, thisTheater)) {
                    TicketSales.ctemp.movies.get(movieidx).putShow(
                            TicketSales.ctemp.theaters.get(theateridx), startTime,
                            TicketSales.ctemp.theaters.get(theateridx).capacity);
                }
                else {
                    System.out.println("Show time confilicted: " + k);
                    System.out
                            .println("Please try again or use different file");
                    System.out.println();
                    break;
                }
            }
            initialPrice(sc);
        }
        catch (NoSuchElementException e) {
            System.out.println("bad initialization file (6)");
            // UI.intro();
        }
        catch (StringIndexOutOfBoundsException e) {
            System.out.println("bad initialization file (7)");
            // UI.intro();
        }
        catch (NumberFormatException e) {
            System.out.println("bad initialization file (8)");
            // UI.intro();
        }
    }

    /**
     * check the showtime we adding has no conflict to the every showtimes are
     * already there. this method will check base on HashMap k, so theater
     * associated with showtime, if there is a conflict, even though they are
     * differnt movies the method will still return false
     * 
     * @param c2
     *            this cinema
     * @param st
     *            startTime of this showtime
     * @param thisTheater
     *            theater associated with st
     * @return
     */
    private static boolean noTimeConflict(Cinema c2, String st,
            Theater thisTheater) {
        boolean boo = true;
        for (int i = 0; i < c2.movies.size(); i++) {
            Set<Theater> stt = c2.movies.get(i).k.keySet();
            for (int j = 0; j < stt.size(); j++) {
                Movie currentMovie = c2.movies.get(i);
                Theater currentKey = ((Theater) stt.toArray()[j]);
                ArrayList<ShowTime> stlist = currentMovie.k.get(currentKey);
                if (thisTheater.equals(currentKey)) {
                    for (int k = 0; k < stlist.size(); k++) {
                        Integer thisEndTime = c2.movies.get(i).runningTime
                                + Integer.valueOf(st);
                        Integer thisStartTime = Integer.valueOf(st);
                        Integer thatEndTime = Integer.valueOf(ShowTime
                                .endTime(stlist.get(k)));
                        Integer thatStartTime = Integer
                                .valueOf(stlist.get(k).startTime);
                        if ((thisStartTime > thatStartTime && 
                                thisStartTime < thatEndTime)
                                || (thisStartTime < thatStartTime && 
                                        thisEndTime > thatStartTime)
                                || thisStartTime.equals(thatStartTime)) {
                            boo = false;
                            break;
                        }
                    }
                }
            }
        }
        return boo;
    }

    /**
     * initializing the all price option by putting them into HashMap, return
     * error message if bad inputs. pass a scanner to next method when done.
     * 
     * @param sc
     *            given scanner from last method
     */
    private static void initialPrice(Scanner sc) {
        try {
            while (sc.hasNextLine()) {
                String k = sc.nextLine();
                if (k.equals("End")) {
                    break;
                }
                Scanner sc2 = new Scanner(k).useDelimiter(":");
                String s = sc2.next();
                int i = Integer.valueOf(sc2.next());
                TicketSales.ctemp.priceMap(s, i);
            }
            TicketSales.c.movies.addAll(TicketSales.mlist);
            TicketSales.c.theaters.addAll(TicketSales.ctemp.theaters);
            System.out.println();
            System.out.println("Data successfully update!");
            sc.close();
        }
        catch (NoSuchElementException e) {
            System.out.println("bad initialization file (9)");
            // UI.intro();
        }
        catch (StringIndexOutOfBoundsException e) {
            System.out.println("bad initialization file (10)");
            // UI.intro();
        }
        // UI.intro();
    }

}
