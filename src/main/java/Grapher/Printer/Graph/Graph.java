package Grapher.Printer.Graph;

/** The Interface that all graphs will implement.
 *
 * @author Henry Wessels
 * @version 1.0
 * @since 2022-03-20
 */
public interface Graph {
    /** The Print function that will print out the graph into terminal.
     * Using the data constructed on instantiation, the graph is printed
     * with the highest value being a full measurement, and the lowest an almost
     * empty measurement.
     */
    void print();
}
