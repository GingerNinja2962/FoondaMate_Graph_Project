package Grapher;

import Grapher.Printer.DataManager.DataManager;
import Grapher.ApiDataRetriever.APIConnection;
import Grapher.Printer.Graph.GraphPrinter;
import Grapher.Setup.ArgHandler;

/** This is the MAIN class in the CLI for FoondaMate_Graph_Project.<br>
 * This class holds the 'main' function that will execute functions that will:
 * <ol>
 *     <li>
 *          Setup the CLI Arguments from the Launch Arguments.
 *     </li>
 *     <li>
 *         Connect to the Remote API and Request the statistical Data.
 *     </li>
 *     <li>
 *         Print the Data from the API in the desired Graph format.
 *     </li>
 * </ol>
 */
public class Grapher {
    /** The connection to the Remote API.
     * This is managed by the APIConnection class.
     */
    private static APIConnection myAPIConnection;
    /** The launch arguments from the CLI.
     * This determines how the data will be formatted and displayed.
     * This is managed by the ArgHandler class.
     */
    private static ArgHandler argHandler;
    /** The APIs' Data.
     * This is the Data Retrieved from the API, and is displayed by the DataManager Class.
     */
    private static DataManager APIData;

    //region ====[ Main ]====

    /** The main function for the program.
     * This function will execute function that Connect to and retrieve data from a Remote API,
     * Format the data according to launch arguments, and then display the data in a graph format.
     *
     * @param args The launch Arguments for the program.
     */
    public static void main(String[] args) {
        // Setup Args handler and interpret the launch args.
        argHandler = new ArgHandler(args);

        // Start connection with the API and send/get request from API
        // Store data in the DataManagerClass.
        myAPIConnection = new APIConnection(argHandler.getAPIEndPoint());
        APIData = new DataManager(myAPIConnection.SendRequest());

        // Print out a graph given the data from the API and launch args.
        new GraphPrinter(argHandler, APIData).print();
    }
    //endregion

}
