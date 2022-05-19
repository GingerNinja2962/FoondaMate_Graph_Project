package Grapher.Printer.Graph.Histogram;

import Grapher.Printer.Graph.Graph;

import java.time.LocalDate;
import java.util.SortedMap;

/** This is a Histogram printing class.
 * It will print out a Histogram based on the data it has.
 *
 * @deprecated Not Finished.
 */
public class Histogram implements Graph {
    /** This is the Data to be used when printing the Histogram.
     * This needs to be set in the constructor.
     */
    private final SortedMap<LocalDate, Integer> Data;

    //region ====[ Constructor ]====

    /** This is the constructor for the Histogram class.
     * This constructor MUST set up the Data variable that will be used when printing the graph.
     *
     * @param data The SortedMap data that will be used when printing the Histogram.
     */
    public Histogram(SortedMap<LocalDate, Integer> data) {
        this.Data = data;
    }
    //endregion

    //region ====[ Print ]====
    /** The Print function that will print out the graph into terminal.
     * Using the data constructed on instantiation, the graph is printed
     * with the highest value being a full bar, and the lowest an almost
     * empty bar.
     */
    public void print() {
        System.out.println("====[ Histogram ]====\n"); // Add the logic for printing a histogram below  |.
//        for (LocalDate date : this.Data.keySet()) //                                                  V
//            System.out.println(generateBar(date));
        System.out.println("\n====[ End Of Graph ]====");
    }
    //endregion
}
