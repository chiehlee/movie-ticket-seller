import java.util.ArrayList;

/**
 * @author Chieh Lee
 * @version 11.24.2013
 */
public class ShowTime {
    /**
     * startTime in string, startTime represents this number counts from 00:00
     * in minutes, (ex 960 = 16:00)
     */
    String startTime;
    /**
     * runningTime of the movie associated with this showtime in String.
     */
    String runningTime;
    /**
     * remaining seat of this showtime, will be mutated.
     */
    int remainingSeat;
    /**
     * ArrayList<Integer> track how many ticket are sold of different price
     * options.
     */
    ArrayList<Integer> soldList = new ArrayList<Integer>();

    /**
     * constructor of showtime
     * 
     * @param startTime start time
     * @param totalTime running time
     */
    ShowTime(String startTime, String totalTime) {
        this.startTime = startTime;
        this.runningTime = totalTime;
    }

    /**
     * overloaded constructor of showtime
     * 
     * @param startTime start time
     * @param totalTime running time
     * @param remainingSeat remaining seat
     */
    ShowTime(String startTime, String totalTime, int remainingSeat) {
        this.startTime = startTime;
        this.runningTime = totalTime;
        this.remainingSeat = remainingSeat;
    }

    /**
     * overloaded constructor of showtime
     * 
     * @param runningTime running time
     */
    ShowTime(String runningTime) {
        this.runningTime = runningTime;
    }

    /**
     * convert the time of minutes counted from 00:00 to more readable 24-hour
     * format. for example 960 will be converted as 16:00
     * 
     * @param s time as String to be converted
     * @return String
     */
    private static String convertTime(String s) {
        int i = Integer.valueOf(s);
        Integer hr = i / 60;
        hr = hr % 24;
        Integer mins = i % 60;

        if (hr < 10) {
            if (mins < 10) {
                return "0" + hr.toString() + "0" + mins.toString();
            }
            else {
                return "0" + hr.toString() + mins.toString();
            }
        }
        else if (mins < 10) {
            return hr.toString() + "0" + mins.toString();
        }
        else {
            return hr.toString() + mins.toString();
        }
    }

    /**
     * static method for running endTimeMethod()
     * 
     * @param st this showtime
     * @return String
     */
    protected static String endTime(ShowTime st) {
        return st.endTimeMethod();
    }

    /**
     * calculate the endtime base on the start time and the running time
     * 
     * @return String
     */
    private String endTimeMethod() {
        Integer endTimeI = Integer.valueOf(this.startTime)
                + Integer.valueOf(this.runningTime);
        return endTimeI.toString();

    }

    /**
     * get the start time in int
     * 
     * @return int
     */
    protected int getStartTime() {
        return Integer.valueOf(this.startTime);
    }

    /**
     * void method to change the remaining seat base on ArrayList<Integer>
     * plist2, which represents how many ticket were sold
     * 
     * @param plist2 how many ticket were sold
     */
    protected void changeRemainingSeat(ArrayList<Integer> plist2) {
        int total = 0;
        for (int i = 0; i < plist2.size(); i++) {
            total = total + plist2.get(i);
        }
        remainingSeat = remainingSeat - total;

    }

    /**
     * mutate the soldlist of this showtime base on how many ticket were sold if
     * the ticket going to be selling will exceed the capacity, then dont make
     * any change
     * 
     * @param plist2 how many ticket were sold
     */
    protected void changeSoldList(ArrayList<Integer> plist2) {
        if (soldList.isEmpty()) {
            if (remainingSeat > 0) {
                for (int i = 0; i < plist2.size(); i++) {
                    soldList.add(plist2.get(i));
                }
            }
        }
        else {
            if (remainingSeat - (totalTicket(plist2)) > 0) {
                for (int j = 0; j < plist2.size(); j++) {
                    soldList.set(j, soldList.get(j) + plist2.get(j));
                }
            }
        }
    }

    /**
     * calculate how many tickets were sold of all
     * 
     * @param plist2 how many ticket were sold
     * @return int
     */
    private static int totalTicket(ArrayList<Integer> plist2) {
        int x = 0;
        for (int i = 0; i < plist2.size(); i++) {
            x = x + plist2.get(i);
        }
        return x;
    }

    /**
     * override toString method, returning information needed
     * @return String
     */
    public String toString() {
        Integer remainI = remainingSeat;
        return "Start Time: " + convertTime(startTime).substring(0, 2)
                + " : " + convertTime(startTime).substring(2, 4)
                + "\nrunning Time: " + runningTime + " mins"
                + "\nremaing seats: " + remainI.toString();

    }

}
