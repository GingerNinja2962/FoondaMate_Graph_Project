package Grapher.Printer.Graph.LineGraph;

import Grapher.Printer.Graph.Graph;

import java.time.LocalDate;
import java.util.SortedMap;

/** This is a class that will Print out a Line graph from that Data given to it.
 *
 * @deprecated Not Yet Implemented
 */
public class LineGraph implements Graph {
    /** This is the Data that will be used to print out the Graph.
     * This is a SortedMap that contains the filtered modified data.
     */
    private final SortedMap<LocalDate, Integer> Data;

    //region ====[ Constructor ]====

    /** This is a constructor for the line graph class.
     * This constructor MUST set the data variable that is used to print out the graph.
     *
     * @param data The sorted data.
     */
    public LineGraph(SortedMap<LocalDate, Integer> data) {
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
        System.out.println("====[ Line-Graph ]====\n");
//        for (LocalDate date : this.Data.keySet())
//            System.out.println(generateBar(date));
        System.out.println("\n====[ End Of Graph ]====");
    }
    //endregion
}
