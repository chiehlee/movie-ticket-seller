/**
 * @author Chieh Lee
 * @version 11.24.2013
 */
public class Theater {
    /**
     * name of this theater
     */
    String name;
    /**
     * capacity of this theater
     */
    int capacity;

    /**
     * constructor of this theater
     * 
     * @param name this theater name
     * @param capacity capacity of this theater
     */
    Theater(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }

    /**
     * overloaded constructor of this theater
     */
    Theater() { // this constructor is empty
    }

    /**
     * get the name of this theater
     * 
     * @return String
     */
    public String getName() {
        return this.name;
    }

    /**
     * static method to create a new instance of theater base on given name and
     * given capacity
     * 
     * @param s given name
     * @param i given capacity
     * @return Theater
     */
    public static Theater create(String s, int i) {
        return new Theater(s, i);
    }

    /**
     * override equals method
     * 
     * @param o object is going to be compare
     * @return boolean
     */
    public boolean equals(Object o) {
        if (o instanceof Theater) {
            Theater th2 = ((Theater) o);
            return this.name.equals(th2.name)
                    && (this.capacity == th2.capacity);
        }
        else {
            return false;
        }
    }

    /**
     * override toString method, return needed information
     * 
     * @return String
     */
    public String toString() {
        return "Theater: " + this.name + "\ncapacity: " + this.capacity;
    }

    /**
     * toString method that only return this theater name
     * 
     * @return String
     */
    public String toStringNameOnly() {
        return "Theater: " + this.name;
    }

}
