import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;

/**
 * @author Chieh Lee
 * @version 11.23.2013
 */
public class TicketSales {

    /**
     * constructor of TicketSales
     */
    TicketSales() { // this constructor is empty
    }

    /**
     * instance of TicketSales
     */
    static TicketSales prog = new TicketSales();

    /**
     * temp variable for saving current movie;
     */
    static Movie movieSave;
    /**
     * temp variable for saving current theater;
     */
    static Theater theaterSave;
    /**
     * temp variable for saving current ShowTime;
     */
    static ShowTime showTimeSave;
    /**
     * temp variable for saving this order information;
     */
    static String currentOrder;

    /**
     * temp variable for sales report
     */
    static String salesReport = "";
    /**
     * temp variable for report count
     */
    static int reportCount = 1;
    /**
     * temp variable for manager report
     */
    static String manReport = "";
    /**
     * temp variable for log report
     */
    static String logReport = "";
    /**
     * temp variable for add movie method
     */
    static String thisAddMovie = "";

    /**
     * temp variable for saving ArrayList<Movie>
     */
    static ArrayList<Movie> mlist = new ArrayList<Movie>();
    /**
     * temp variable for saving ArrayList<Integer>
     */
    static ArrayList<Integer> plist = new ArrayList<Integer>();
    /**
     * temp variable for this cinema
     */
    static Cinema ctemp = new Cinema(mlist);

    /**
     * actual cinema that this program running with, this cinema contains all
     * the data (it's important)
     */
    static Cinema c = new Cinema(new ArrayList<Movie>());

    /**
     * static method to reset c
     */
    static void resetMovies() {
        mlist = new ArrayList<Movie>();
        c = new Cinema(mlist);
    }

    /**
     * initial c base on the given fileName, this method will then call 
     * initData from class InitialData and process the initialization
     * 
     * @param fileName given String as file name
     */
    public void initCinema(String fileName) {
        try {
            File input = new File(fileName);
            Scanner sc = new Scanner(input);
            InitialData.initData(sc);
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found! Please retry (1)");
            System.out.println();
            System.out.println("Press enter to return to main menu...");
            UI.pause();
            UI.intro();
        }
    }

    /**
     * method to process order
     * 
     * @param fileName given string as file name
     */
    public void processOrders(String fileName) {
        try {
            File file = new File(fileName);
            Scanner sc = new Scanner(file);
            processing(sc);

            // UI.intro();
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found! Please retry (2)");
            System.out.println();
            System.out.println("Press enter to return to main menu...");
            UI.pause();
            UI.intro();
        }
        catch (InputMismatchException e) {
            System.out.println("yes");
        }
    }

    /**
     * method actually processing order
     * 
     * @param sc given scanner
     */
    void processing(Scanner sc) {
        while (sc.hasNextLine()) {
            String k = sc.nextLine();
            if (k.equals("report")) {
                generateManagerReport();
            }
            else {
                currentOrder = k;
                Scanner sc5 = new Scanner(k).useDelimiter(",");
                int movieIdx = sc5.nextInt() - 1;
                int theaterIdx = sc5.nextInt() - 1;
                int thisStartTime = sc5.nextInt();
                Set<Theater> stt2 = c.movies.get(movieIdx).k.keySet();
                ArrayList<Theater> stt = new ArrayList<Theater>();
                stt.addAll(stt2);
                Collections.sort(stt, new TheaterComparator());
                ArrayList<ShowTime> stlist = c.movies.get(movieIdx).k
                        .get(c.theaters.get(theaterIdx));
                ArrayList<Integer> statlist = new ArrayList<Integer>();
                ArrayList<Integer> plist2 = new ArrayList<Integer>();
                int showTimeIdx;
                for (int i = 0; i < stlist.size(); i++) {
                    statlist.add(stlist.get(i).getStartTime());
                }
                showTimeIdx = statlist.indexOf(thisStartTime);

                if (movieIdx >= (c.movies.size() + 1)) {
                    System.out
                            .println("bad order request, please try again.");
                    break;
                }
                else if (theaterIdx >= (c.theaters.size() + 1)
                        && !(stt.contains(c.theaters.get(theaterIdx)))) {
                    System.out.println("theater not found for this movie");
                    break;
                }
                else if (!(statlist.contains(thisStartTime))) {
                    System.out.println("show time not found for this movie");
                    break;
                }
                movieSave = c.movies.get(movieIdx);
                showTimeSave = stlist.get(showTimeIdx);
                Set<String> ptt = movieSave.priceOption.keySet();
                Integer totalPrice = 0;
                while (sc5.hasNext()) {
                    plist2.add(sc5.nextInt());
                }
                for (int i = 0; i < plist2.size(); i++) {
                    totalPrice = ((movieSave.priceOption
                            .get(ptt.toArray()[i])) * plist2.get(i))
                            + totalPrice;
                }
                UI.thisPrice = totalPrice;
                showTimeSave.changeRemainingSeat(plist2);
                showTimeSave.changeSoldList(plist2);
                boolean overload = showTimeSave.remainingSeat < 0;
                if (!overload) {
                    salesReport = salesReport + "\n" + k + ","
                            + totalPrice.toString();
                    logReport = logReport + "\n" + "no error occured";
                }
                else {
                    salesReport = salesReport + "\n" + k + "," + 0;
                    logReport = logReport + "\n" + "this theater is full";
                }
            }
        }
    }

    /**
     * return the sales report
     * 
     * @return String
     */
    public String reportSales() {
        String s = salesReport.substring(1);
        s = s + "\n";
        return s;
    }

    /**
     * method generating sales report
     */
    void generateManagerReport() {
        manReport = manReport + "Report " + reportCount + "\n";
        for (int i = 0; i < c.movies.size(); i++) {
            Set<Theater> stt2 = c.movies.get(i).k.keySet();
            ArrayList<Theater> stt = new ArrayList<Theater>();
            stt.addAll(stt2);
            Collections.sort(stt, new TheaterComparator());
            for (int j = 0; j < stt.size(); j++) {
                Movie currentMovie = c.movies.get(i);
                Theater currentKey = ((Theater) stt.toArray()[j]);
                ArrayList<ShowTime> stlist = currentMovie.k.get(currentKey);
                Collections.sort(stlist, new ShowTimeComparator());
                for (int k = 0; k < stlist.size(); k++) {
                    manReport = manReport + currentMovie.name + ",";
                    manReport = manReport + currentKey.name + ",";
                    manReport = manReport + stlist.get(k).startTime + ",";
                    manReport = manReport + currentKey.capacity + ",";
                    if (stlist.get(k).soldList.isEmpty()) {
                        for (int l = 0; l < currentMovie.priceOption.keySet()
                                .size(); l++) {
                            if (l == currentMovie.priceOption.keySet().size() 
                                    - 1) {
                                manReport = manReport + 0;
                            }
                            else {
                                manReport = manReport + 0 + ",";
                            }
                        }
                    }
                    else {
                        for (int m = 0; m < stlist.get(k).soldList.size(); 
                                m++) {
                            if (m == stlist.get(k).soldList.size() - 1) {
                                manReport = manReport
                                        + stlist.get(k).soldList.get(m);
                            }
                            else {
                                manReport = manReport
                                        + stlist.get(k).soldList.get(m) + ",";
                            }
                        }
                    }
                    manReport = manReport + "\n";
                }
            }
        }
        reportCount = reportCount + 1;

    }

    /**
     * return manager report
     * 
     * @return String
     */
    public String managerReport() {
        return manReport;
    }

    /**
     * return log report
     * 
     * @return String
     */
    public String logReport() {
        return logReport;
    }

    /**
     * method to help to read a input
     * 
     * @return String
     */
    private static String readInput() {
        Scanner sc = new Scanner(System.in);
        String k = sc.nextLine();
        return k;
    }

    /**
     * helper method to process initializing movie when trying to add movie
     * through console
     */
    public static void addMovieProcess() {
        String s = "";
        thisAddMovie = thisAddMovie + "Movies\n";
        while (!s.equals("...")) {
            System.out.println("Please enter movies name: ");
            thisAddMovie = thisAddMovie + readInput() + ":";
            System.out.println("Please enter during of this movie: ");
            thisAddMovie = thisAddMovie + readInput() + "\n";
            System.out.println();
            System.out.print("type \"...\" to input Theater or press ");
            System.out.print("enter to add more movies");
            System.out.println();
            s = readInput();
        }
        s = "";
        thisAddMovie = thisAddMovie + "Theaters\n";
        while (!s.equals("...")) {
            System.out.println("Please enter theater name: ");
            thisAddMovie = thisAddMovie + readInput() + ":";
            System.out.println("Please enter theater capacity: ");
            thisAddMovie = thisAddMovie + readInput() + "\n";
            System.out.println();
            System.out.print("type \"...\" to input show or press ");
            System.out.print("enter to add more theaters");
            System.out.println();
            s = readInput();
        }
        s = "";
        thisAddMovie = thisAddMovie + "Shows\n";
        while (!s.equals("...")) {
            System.out.println("Please enter the index of movie: ");
            thisAddMovie = thisAddMovie + readInput() + ",";
            System.out.println("Please enter the index of theater: ");
            thisAddMovie = thisAddMovie + readInput() + ",";
            System.out.println("Please enter the starting time: ");
            thisAddMovie = thisAddMovie + readInput() + "\n";
            System.out.println();
            System.out.print("type \"...\" to input price or press ");
            System.out.print("enter to add more shows");
            System.out.println();
            s = readInput();
        }
        s = "";
        thisAddMovie = thisAddMovie + "Prices\n";
        while (!s.equals("...")) {
            System.out.println("Please enter the price option: ");
            thisAddMovie = thisAddMovie + readInput() + ":";
            System.out.println("Please enter the price of this option: ");
            thisAddMovie = thisAddMovie + readInput() + "\n";
            System.out.println();
            System.out.print("type \"...\" if you finished or ");
            System.out.print("press enter to add more price options");
            System.out.println();
            s = readInput();
        }
        thisAddMovie = thisAddMovie + "End";
    }

    /**
     * method to process initializing movie when trying to add through console
     */
    static void addMovie() {
        thisAddMovie = "";
        addMovieProcess();
        Scanner sc = new Scanner(thisAddMovie);
        try {
            InitialData.initData(sc);
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }

    }

    /*
     * public static void addMovie() { // String s = readInput(); thisAddMovie =
     * thisAddMovie + "Movies\n";
     * System.out.println("Please enter movies name: "); thisAddMovie =
     * thisAddMovie + readInput() + ":";
     * System.out.println("Please enter during of this movie: "); thisAddMovie =
     * thisAddMovie + readInput() + "\n";
     * System.out.println("Please type \"...\" to input Theater"); thisAddMovie
     * = thisAddMovie + "End"; }
     */

    /**
     * method to process removing movie when trying to remove movie through
     * console
     */
    public static void removeMovie() {
        for (int i = 0; i < c.movies.size(); i++) {
            System.out.println(i + ". " + c.movies.get(i));
        }
        System.out.println();
        System.out
                .println("Please select a number of movie you wish to remove");
        Scanner sc = new Scanner(System.in);
        boolean intInput = sc.hasNextInt();
        if (intInput) {
            try {
                c.movies.remove(sc.nextInt());
                System.out.println("the movie has been removed!");
            }
            catch (IndexOutOfBoundsException e) {
                System.out.println("index out of bound, movie doesn't exist");
                System.out.println("please try again.");
                System.out.println();
                removeMovie();
            }
        }
        else {
            System.out.println("bad input, please try again.");
            System.out.println();
            removeMovie();
        }
    }

}
