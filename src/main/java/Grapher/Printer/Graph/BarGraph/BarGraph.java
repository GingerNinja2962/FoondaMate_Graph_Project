package Grapher.Printer.Graph.BarGraph;

import Grapher.Printer.Graph.Graph;

import java.time.LocalDate;
import java.util.SortedMap;
import java.util.Collections;

/** The class that handles the printing of Bar Graph.
 * It is subtype of the Graph Interface.
 *
 * @author Henry Wessels
 * @version 1.0
 * @since 2022-03-20
 * @see Graph
 */
public class BarGraph implements Graph {
    /** This is a Sorted Map of the data retrieved from the remote API.
     * This is the final data after all the filters on what data must be displayed have run.
     */
    private final SortedMap<LocalDate, Integer> Data;
    /** This is the length of the largest value to be displayed in the bar graph.
     * This is set in the constructor using the Data SortedMap.
     */
    private final int Max;
    /** This is the length of the smallest value to be displayed in the bar graph.
     * This is set in the constructor using the Data SortedMap.
     */
    private final int Min;
    /** <p>This is the value each increment of a bar will represent.<br>
     * This is calculated by dividing the difference between Max and Min by 50.
     * This is used to scale a bar so any range of data can be displayed.<br>
     * (Max - Min) / 50
     * </p>
     */
    private final int Size;

    //region ====[ Constructor ]====
    /** The constructor for the BarGraph class.
     *
     * @param data The SortedMap containing LocalDate's as keys and Integers as values.
     */
    public BarGraph(SortedMap<LocalDate, Integer> data) {
        this.Data = data;
        this.Max = max();
        this.Min = min();
        this.Size = size();
    }
    //endregion

    //region ====[ Print ]====
    /** The Print function that will print out the graph into terminal.
     * Using the data constructed on instantiation, the graph is printed
     * with the highest value being a full bar, and the lowest an almost
     * empty bar.
     */
    public void print() {
        System.out.println("====[ Bar-Graph ]====\n");
        for (LocalDate date : this.Data.keySet())
            System.out.println(generateBar(date));
        System.out.println("\n====[ End Of Graph ]====");
    }
    //endregion

    //region ====[ Private ]====
    /** A function that will generate a single horizontal bar with the
     * given date as the key in the Data SortedMap.
     *
     * @param Date The date of the bar to be printed.
     * @return String containing the bar to be printed.
     */
    private String generateBar(LocalDate Date) {
        String bar = String.format("%s: \033[7m", Date);
        int value = this.Data.get(Date);

        for (int i = value - this.Min; i >= 0; i -= this.Size) {
            bar = bar + " ";
        }
        return String.format(bar+"\033[0m - %s", value);
    }

    /** A simple function that returns the max int in the values
     * of the SortedMap Data.
     *
     * @return int of the Max value in the Data SortedMaps values.
     */
    private int max() {
        return Collections.max(this.Data.values());
    }

    /** A simple function that returns the min int in the values
     * of the SortedMap Data.
     *
     * @return int of the Min value in the Data SortedMaps values.
     */
    private int min() {
        return Collections.min(this.Data.values());
    }

    /** A simple function that calculates what the size of each space
     * in the bars of the Bar Graph should be, by subtracting the lowest
     * value from the highest value in the SortedMaps values.
     *
     * @return int of the size each space in a bar represents.
     */
    private int size() {
        return (this.Max - this.Min)/50;
    }
    //endregion
}
