package Grapher;

import Grapher.Printer.DataManager.DataManager;
import Grapher.ApiDataRetriever.APIConnection;
import Grapher.Printer.Graph.GraphPrinter;
import Grapher.Setup.ArgHandler;

public class Grapher {
    private static APIConnection myAPIConnection;
    private static ArgHandler argHandler;
    private static DataManager APIData;

    //region ====[ Main ]====
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
