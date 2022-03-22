package Grapher.Printer.Graph.Histogram;

import Grapher.Printer.Graph.Graph;

import java.time.LocalDate;
import java.util.SortedMap;

public class Histogram implements Graph {
    private final SortedMap<LocalDate, Integer> Data;

    //region ====[ Constructor ]====
    public Histogram(SortedMap<LocalDate, Integer> data) {
        this.Data = data;
    }
    //endregion

    //region ====[ Print ]====
    /** The Print function that will print out the graph into terminal.
     * Using the data constructed on instantiation, the graph is printed
     * with the highest value being a full bar, and the lowest an almost
     * empty bar.
     *
     * @return Nothing.
     */
    public void print() {
        System.out.println("====[ Histogram ]====\n");
//        for (LocalDate date : this.Data.keySet())
//            System.out.println(generateBar(date));
        System.out.println("\n====[ End Of Graph ]====");
    }
    //endregion
}
