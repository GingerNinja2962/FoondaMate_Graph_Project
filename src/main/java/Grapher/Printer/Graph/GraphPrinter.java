package Grapher.Printer.Graph;

import Grapher.Printer.DataManager.DataManager;
import Grapher.Printer.Graph.BarGraph.BarGraph;
import Grapher.Printer.Graph.Histogram.Histogram;
import Grapher.Printer.Graph.LineGraph.LineGraph;
import Grapher.Setup.ArgHandler;
import Grapher.Setup.TrimData;

/** The class that handles the printing of Graphs based on the launch args.
 *
 * @author Henry Wessels
 * @version 1.0
 * @since 2022-03-21
 * @see Graph
 * @see BarGraph
 * @see LineGraph
 * @see Histogram
 */
public class GraphPrinter {
    /** The APIs' Data stored in a DataManager class.
     * This is both the formatted and filtered data and un-formatted data.
     */
    private final DataManager APIData;
    /** This is the Launch Arguments the CLI takes.
     * After being checked and sorted it is stored in a ArgHandler class.
     */
    private final ArgHandler args;
    /** This is a graph object, created using an interface of type 'graph'.
     * This is used to contain the graph that needs to be printed.<br>
     * Since more functionality is to be added this is a future proofing way of allowing that expansion in
     * features later.
     */
    private Graph graph;

    //region ====[ Constructors ]====
    /** The constructor for the Graph class, that handles the printing of other graphs.
     *
     * @param args The command line launch Args contained in a ArgHandler.
     * @param APIData DataManager containing the APIs response data as a HashMap.
     */
    public GraphPrinter(ArgHandler args, DataManager APIData) {
        this.APIData = APIData;
        this.args = args;
    }
    //endregion

    //region ====[ Print ]====
    /** The Print function that will print out the graph into terminal.
     * Using the data constructed on instantiation, the graph is printed
     * with the highest value being a full bar, and the lowest an almost
     * empty bar. The graph is based on the command line args,
     * if no graph type was given then a bar graph will be printed.
     */
    public void print() {
        switch (args.getGraph()) {
            case "bar" -> this.graph = new BarGraph(new TrimData(this.args, this.APIData).getTrimmedAPIData());
            case "line" -> this.graph = new LineGraph(new TrimData(this.args, this.APIData).getTrimmedAPIData());
            case "histogram" -> this.graph = new Histogram(new TrimData(this.args, this.APIData).getTrimmedAPIData());
        }
        this.graph.print();
    }
    //endregion
}
