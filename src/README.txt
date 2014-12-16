to run the console: run the main method under UI.java
and then you will enter a interface looks like this

* * * * * * * * * * * * * * * * * *
*                                 *
*      Welcome to my Cinema       *
*     Please Enter a number to    *
*            continue             *
*                                 *
* * * * * * * * * * * * * * * * * *

1. Movie List
2. Show Time List(view only)
3. Initial Data
4. Read order request
5. Reset movie list
6. Manager Interface

1. Movie List allows you to view all the movies in this cinema and make one order per time.
   if the cinema have empty movies list, then this option will return a error message
2. Show Time List allows you to view all the movies and show time, but for view only, user
   can get information by choosing this option but a user cannot make any order from Show 
   Time List
3. Initial Data allows user to initail a external text file, if the file a user trying to use
   is not exist, the system will then return a error message and ask user to try to initial 
   another file again.
   If the file a user trying to initialize contains bad entry, the system will warn the user
   and ask the user to initial different file again
4. Read Order request basically does the same thing as Initial Data, however Read Order request
   will process exteranl order request file. Read Order request will return a error message if
   the file is not found or the external order file contains any bad entries.
5. Reset movie list allow a user to reset the current cinema state, basically empty the c.movies
6. Manager Interface will let the user enter the Manager interface, there are several other more
   option that can be interact with in the Manager Interface.

the Manager Interface will look like this

* * * * * * * * * * * * * * * * * *
*                                 *
*      Welcome to use Manager     *
*     Interface, please select    *
*       a option to continue      *
*                                 *
* * * * * * * * * * * * * * * * * *

1. add Movie
2. remove Movie
3. Request report
4. Reset Manager Report
5. Back to top


1. add Movie allows user to add movie by inserting information through console, it will ask user
   to insert multiple movie name, duration, theater name, theater capacity, wish start time and 
   price options. add Movie will generate a big String consist a fixed format and will be read
   by other method to process. The example of format will look like this 

Movies
Harry Potter:102
Great Expectations:115
Theaters
A:300
B:90
C:500
Shows
1,1,960
1,1,1200
2,2,990
1,1,1080
1,3,1020
1,3,1140
2,2,1210
Prices
Adult:10
Child:7
Senior:8
End

2. remove movie allow user to remove any movie in the list that the user wish to remove.
3. Request report allow user to view a manager report, report will be generate basically add-on the 
   previous report unless the user choose to Reset Manager Report
4. If a user reset manager report, the manager report will start counting from one and all previous 
   reports will be removed
5. back to the main menu

