import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Set;

/**
 * this class runs the user interface
 * 
 * @author Chieh Lee
 * @version 12.4.2013
 */
public class UI {

    /**
     * instance of TicketSales
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
     * temp variable for sales report
     */
    static String salesReport = TicketSales.salesReport;
    /**
     * temp variable for this processing order
     */
    static String thisOrder = "";
    /**
     * temp variable for this current price
     */
    static Integer thisPrice;
    /**
     * temp variable for report count
     */
    static int reportCount = TicketSales.reportCount;
    /**
     * temp variable for manager report
     */
    static String manReport = TicketSales.manReport;

    /**
     * temp variable for saving ArrayList<Integer>
     */
    static ArrayList<Integer> plist = new ArrayList<Integer>();



    /**
     * the intro method will print the console and be able to read inputs from
     * user and response according to user inputs. the user is able to view
     * Movie list, view show time list, initial data from external file, process
     * order from external file, reset current movie data and go to manager
     * interface
     */
    static void intro() {
        System.out.println();
        System.out.println("* * * * * * * * * * * * * * * * * *");
        System.out.println("*                                 *");
        System.out.println("*      Welcome to bao2 Cinema     *");
        System.out.println("*     Please Enter a number to    *");
        System.out.println("*            continue             *");
        System.out.println("*                                 *");
        System.out.println("* * * * * * * * * * * * * * * * * *");
        System.out.println();
        System.out.println("1. Movie List");
        System.out.println("2. Show Time List(view only)");
        System.out.println("3. Initial Data");
        System.out.println("4. Read order request");
        System.out.println("5. Reset movie list");
        System.out.println("6. Manager Interface");
        System.out.println();

        Scanner sc = new Scanner(System.in);
        boolean intInput = sc.hasNextInt();

        if (intInput) {
            int input = sc.nextInt();
            if (input == 1) {
                if (TicketSales.c.movies.isEmpty()) {
                    System.out.println("Data Storage is empty!");
                    System.out.print("please initial information or ");
                    System.out.print("use manager interface");
                    System.out.println();
                    intro();
                }
                else {
                    printMovie();
                    System.out.println();
                    System.out
                            .println("Press enter to return to main menu...");
                    pause();
                    intro();
                }
            }
            else if (input == 2) {
                if (TicketSales.c.movies.isEmpty()) {
                    System.out.println("Data Storage is empty!");
                    System.out.print("please initial information or ");
                    System.out.print("use manager interface");
                    System.out.println();
                    intro();
                }
                else {
                    printShowTime();
                    System.out
                            .println("Press enter to return to main menu...");
                    pause();
                    intro();
                }
            }
            else if (input == 3) {
                System.out.print("Please enter file name: ");
                Scanner fileinput = new Scanner(System.in);
                String fileName = fileinput.nextLine();
                prog.initCinema(fileName);
                System.out.println();
                System.out.println("Press enter to return to main menu...");
                pause();
                intro();
            }
            else if (input == 4) {
                System.out.print("Please enter file name: ");
                Scanner fileinput = new Scanner(System.in);
                String fileName = fileinput.nextLine();
                prog.processOrders(fileName);
                System.out.println();
                System.out.println("Your sales report with total price.");
                System.out.println(prog.reportSales());
                System.out.println();
                System.out.println("Press enter to return to main menu...");
                pause();
                intro();
            }
            else if (input == 5) {
                if (TicketSales.c.movies.isEmpty()) {
                    System.out.println("Data Storage is empty!");
                    System.out.print("please initial information or ");
                    System.out.print("use manager interface");
                    System.out.println();
                    intro();
                }
                else {
                    TicketSales.resetMovies();
                    System.out.println("Movies have been reset!");
                    System.out.println();
                    System.out
                            .println("Press enter to return to main menu...");
                    pause();
                    intro();
                }
            }
            else if (input == 6) {
                managerUI();
            }
            else if (input == 7) {
                System.out.println("Thank you!");
                System.exit(0);
            }
            else {
                System.out.println();
                System.out.println("bad input, please enter again!");
                System.out.println();
                System.out.println();
                System.out.print("\f");
                intro();
                sc.close();
            }
        }
        else {
            System.out.println();
            System.out.println("bad input, please enter again!");
            System.out.println();
            System.out.println();
            System.out.println();
            intro();
            sc.close();
        }
    }

    /**
     * the method will print managerUI and interact with user according to the
     * user input the user is allow to addmovie, remove movie, request a manager
     * report or reset the current state of manager report and back to the
     * intro();
     */
    private static void managerUI() {
        System.out.println("* * * * * * * * * * * * * * * * * *");
        System.out.println("*                                 *");
        System.out.println("*      Welcome to use Manager     *");
        System.out.println("*     Interface, please select    *");
        System.out.println("*       a option to continue      *");
        System.out.println("*                                 *");
        System.out.println("* * * * * * * * * * * * * * * * * *");
        System.out.println();
        System.out.println("1. add Movie");
        System.out.println("2. remove Movie");
        System.out.println("3. Request report");
        System.out.println("4. Reset Manager Report");
        System.out.println("5. Back to top");
        System.out.println();

        Scanner sc = new Scanner(System.in);
        boolean intInput = sc.hasNextInt();

        if (intInput) {
            int input = sc.nextInt();
            if (input == 1) {
                TicketSales.addMovie();
                System.out.println();
                System.out.println("Press enter to return to manager UI...");
                pause();
                managerUI();
            }
            else if (input == 2) {
                if (TicketSales.c.movies.isEmpty()) {
                    System.out.println("Movie inventory is empty, ");
                    System.out.print("Please try again.");
                    System.out.println();
                    System.out
                            .println("Press enter to return to manager UI...");
                    pause();
                    managerUI();

                }
                else {
                    TicketSales.removeMovie();
                    System.out.println();
                    System.out
                            .println("Press enter to return to manager UI...");
                    pause();
                    managerUI();
                }
            }
            else if (input == 3) {
                prog.generateManagerReport();
                System.out.println(prog.managerReport());
                System.out.println();
                System.out.println("Press enter to return to manager UI...");
                pause();
                managerUI();
            }
            else if (input == 4) {
                TicketSales.manReport = "";
                TicketSales.reportCount = 1;
                System.out.println("Manager reports have been reset!");
                System.out.println();
                System.out.println("Press enter to return to manager UI...");
                pause();
                managerUI();
            }
            else if (input == 5) {
                intro();
            }

            else {
                System.out.println();
                System.out.println("bad input, please enter again!");
                System.out.println();
                System.out.println();
                System.out.println();
                intro();
                sc.close();
            }
        }
        else {
            System.out.println();
            System.out.println("bad input, please enter again!");
            System.out.println();
            System.out.println();
            System.out.println();
            intro();
            sc.close();
        }
    }

    /**
     * print every possiable show time of this cinema
     */
    private static void printShowTime() {
        for (int i = 0; i < TicketSales.c.movies.size(); i++) {
            Set<Theater> stt2 = TicketSales.c.movies.get(i).k.keySet();
            ArrayList<Theater> stt = new ArrayList<Theater>();
            stt.addAll(stt2);
            Collections.sort(stt, new TheaterComparator());
            for (int j = 0; j < stt.size(); j++) {
                Movie currentMovie = TicketSales.c.movies.get(i);
                Theater currentKey = ((Theater) stt.toArray()[j]);
                ArrayList<ShowTime> stlist = currentMovie.k.get(currentKey);
                Collections.sort(stlist, new ShowTimeComparator());
                for (int k = 0; k < stlist.size(); k++) {
                    System.out.println(stlist.get(k));
                    System.out.println(((Theater) stt.toArray()[j])
                            .toStringNameOnly());
                    System.out.println(currentMovie.name);
                    System.out.println("\n");
                }
            }
        }
    }

    /**
     * print every movies
     */
    private static void printMovie() {
        thisOrder = "";

        for (int i = 0; i < TicketSales.c.getMovie().size(); i++) {
            String movieName = TicketSales.c.getMovie().get(i).name;
            System.out.println(i + ": " + movieName);
        }

        System.out.println();
        System.out.println("enter r return to main menu");
        Scanner sc = new Scanner(System.in);
        System.out.printf("enter any movie number: ");
        boolean intInput = sc.hasNextInt();

        if (intInput) {
            int input = sc.nextInt();
            if (input < TicketSales.c.getMovie().size()) {
                thisOrder = thisOrder + (input + 1) + ",";
                printMovieDetail(TicketSales.c.getMovie().get(input));
            }
            else {
                System.out.println();
                System.out.println("out of range, please enter again!");
                System.out.println();
                System.out.println();
                System.out.println();
                printMovie();
                sc.close();
            }
        }
        else {
            String sinput = sc.next();
            if (sinput.equals("r")) {
                intro();
            }
            else {
                System.out.println();
                System.out.println("bad input, please enter again!");
                System.out.println();
                printMovie();
                sc.close();
            }
        }

    }

    /**
     * print every movie with detail information
     * 
     * @param m given movie
     */
    private static void printMovieDetail(Movie m) {
        movieSave = m;
        System.out.println();
        System.out.println(m.name);
        Set<Theater> stt2 = m.k.keySet();
        ArrayList<Theater> stt = new ArrayList<Theater>();
        stt.addAll(stt2);
        Collections.sort(stt, new TheaterComparator());
        // System.out.println("please select a theater by entering number!");
        System.out.println("**********************");
        for (int j = 0; j < stt.size(); j++) {
            System.out.println("[" + j + "]");
            System.out.println(((Theater) stt.toArray()[j]));
            System.out.println();
            ArrayList<ShowTime> slist = m.k.get(((Theater) stt.toArray()[j]));
            Collections.sort(slist, new ShowTimeComparator());
            for (int k = 0; k < slist.size(); k++) {
                System.out.println(k);
                System.out.println(slist.get(k));
                System.out.println();
            }
            System.out.println("**********************");
        }

        Scanner sc = new Scanner(System.in);
        System.out.println("b return to last page");
        System.out.println("r return to main");
        System.out.printf("please select a theater: ");
        boolean intInput = sc.hasNextInt();

        if (intInput) {
            int input = sc.nextInt();
            // System.out.println(stt.size());
            // System.out.println(((Theater)
            // stt.toArray()[0]).toStringNameOnly());
            if (input < stt.size()) {
                theaterSave = ((Theater) stt.toArray()[input]);
                int theaterIdx;
                theaterIdx = TicketSales.c.theaters.indexOf(theaterSave);
                thisOrder = thisOrder + (theaterIdx + 1) + ",";
                ArrayList<ShowTime> list = m.k
                        .get(((Theater) stt.toArray()[input]));
                confirmShowTime(list);
            }
            else {
                System.out.println();
                System.out.println("out of range, please enter again!");
                System.out.println();
                System.out.println();
                System.out.println();
                printMovieDetail(m);
                sc.close();
            }
        }
        else {
            String sinput = sc.next();
            if (sinput.equals("r")) {
                System.out.println();
                intro();
            }
            else if (sinput.equals("b")) {
                System.out.println();
                printMovie();
            }
            else {
                System.out.println();
                System.out.println("bad input, please enter again!");
                System.out.println();
                printMovieDetail(m);
                sc.close();
            }
        }

    }

    /**
     * confirm the show time
     * 
     * @param slist given show time arraylist
     */
    private static void confirmShowTime(ArrayList<ShowTime> slist) {
        Scanner sc = new Scanner(System.in);
        System.out.printf("please select wish movie time: ");
        boolean intInput = sc.hasNextInt();

        if (intInput) {
            int input = sc.nextInt();
            if (input < slist.size()) {
                ShowTime sss = slist.get(input);
                showTimeSave = sss;
                thisOrder = thisOrder + (slist.get(input).startTime) + ",";
                confirmNumPeople();
            }
            else {
                System.out.println();
                System.out.println("out of range, please enter again!");
                System.out.println();
                System.out.println();
                System.out.println();
                confirmShowTime(slist);
                sc.close();
            }
        }
        else {
            String sinput = sc.next();
            if (sinput.equals("r")) {
                System.out.println();
                intro();
            }
            else if (sinput.equals("b")) {
                System.out.println();
                printMovie();
            }
            else {
                System.out.println();
                System.out.println("bad input, please enter again!");
                System.out.println();
                confirmShowTime(slist);
                sc.close();
            }
        }

    }

    /**
     * confirm how many ticket is going to be sold, return error message if
     * there are any bad inputs
     */
    private static void confirmNumPeople() {
        plist = new ArrayList<Integer>();
        Set<String> ptt = movieSave.priceOption.keySet();
        for (int i = 0; i < ptt.size(); i++) {
            Scanner sc = new Scanner(System.in);
            System.out.printf("How many " + ptt.toArray()[i] + ": ");
            boolean intInput = sc.hasNextInt();
            if (intInput) {
                plist.add(sc.nextInt());
            }
            else {
                String sinput = sc.next();
                if (sinput.equals("r")) {
                    System.out.println();
                    intro();
                }
                else if (sinput.equals("b")) {
                    System.out.println();
                    printMovie();
                }
                else {
                    System.out.println();
                    System.out.println("bad input, please enter again!");
                    System.out.println();
                    confirmNumPeople();
                    sc.close();
                }
            }
        }

        for (int j = 0; j < plist.size(); j++) {
            if (j == (plist.size() - 1)) {
                thisOrder = thisOrder + plist.get(plist.size() - 1);
            }
            else {
                thisOrder = thisOrder + plist.get(j) + ",";
            }
        }
        showTimeSave.changeRemainingSeat(plist);
        Scanner scc = new Scanner(thisOrder);
        // System.out.println(thisOrder);
        prog.processing(scc);
        System.out.println();
        System.out.println("Your sales report: ");
        System.out.println(prog.reportSales());
        System.out.println("Total price is: ");
        System.out.println(thisPrice);
        scc.close();
        // boolean overload = theaterSave.capacity < 0;
        // printConclu(overload);

    }

    /*
     * public static void printConclu(boolean overload) { Logger logger =
     * Logger.getLogger("MyLog3"); FileHandler fh; try { fh = new
     * FileHandler("MyLog.log"); logger.addHandler(fh); SimpleFormatter
     * formatter = new SimpleFormatter(); fh.setFormatter(formatter); if
     * (overload) { logger.info("0"); } else { Integer totalPrice = 0;
     * Set<String> ptt = movieSave.priceOption.keySet(); for (int i = 0; i <
     * plist.size(); i++) { totalPrice = ((movieSave.priceOption
     * .get(ptt.toArray()[i])) * plist.get(i)) + totalPrice; } String
     * totalPriceS = totalPrice.toString(); logger.info(totalPriceS); } } catch
     * (SecurityException e) { e.printStackTrace(); } catch (IOException e) {
     * e.printStackTrace(); } intro(); }
     */

    /**
     * give a pause to a running program,
     */
    static void pause() {
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
    }

    /**
     * main method
     * @param args sd
     */
    public static void main(String[] args) {
        intro();
    }
}
