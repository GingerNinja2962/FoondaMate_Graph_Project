package Grapher;

import Grapher.ApiDataRetriever.APIConnection;
import Grapher.ApiDataRetriever.Exceptions.ClientApiUrlException;
import Grapher.ApiDataRetriever.JSONHandler.DataStructure;
import Grapher.Printer.BarGraph.BarGraph;
import Grapher.Setup.ArgHandler;

import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class Grapher {
    private static ArgHandler argHandler;
    //region ====[ Example of APIConnection Package ]====
    private static APIConnection myAPIConnection;
    private static DataStructure APIData;

    public static void main(String[] args) {
        myAPIConnection = new APIConnection("http://sam-user-activity.eu-west-1.elasticbeanstalk.com/");
        try {
            APIData = myAPIConnection.SendRequest();
        } catch (InterruptedException e) {
        } catch (ClientApiUrlException e) {
        } catch (TimeoutException e) {
        } catch (ExecutionException e) {
        }
        SortedMap<String, String> graphData = new TreeMap<String, String>(APIData.getDateDataLookup());
//        System.out.println(graphData.subMap(graphData.firstKey(), "06-01-2022"));

        BarGraph graph = new BarGraph(graphData);
        graph.print();
    }
    //endregion

    //region ====[ Main ]====
//    public static void main(String[] args) {
//        argHandler = new ArgHandler(args);
//    }
    //endregion
}
