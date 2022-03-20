package Grapher.Setup;

import Grapher.Printer.DataManager.LocalDateFormatter;

import java.util.regex.Pattern;
import java.time.LocalDate;

/** A simple class that handles the command line Arguments, by checking
 * the -s, -e, -g and -a arguments and setting the data to the formatted data
 * following the -s, -e, -g and -a modifiers.
 *
 * @author Henry Wessels
 * @version 1.0
 * @since 2022-03-20
 */
public class ArgHandler {
    private String[] orgArgs;
    private String APIURL = "http://sam-user-activity.eu-west-1.elasticbeanstalk.com/";
    private LocalDate start;
    private LocalDate end;
    private String graph;

    //region ====[ Constructors ]====
    /** the constructor for the ArgHandler class.
     *
     * @param args the commands line startup arguments as String[].
     */
    public ArgHandler(String[] args) {
        this.orgArgs = args;
        splitArgs();
    }
    //endregion

    //region ====[ Getters ]====

    /** Get the APIs endpoint URL.
     *
     * @return String containing the API URL.
     */
    public String getAPIEndPoint() {
        return APIURL;
    }

    /** Get the end date specified in the command line args.
     *
     * @return LocalDate containing the end date specified
     * in the command line args.
     */
    public LocalDate getEnd() {
        return end;
    }

    /** Get the start date specified in the command line args.
     *
     * @return LocalDate containing the Start date specified
     * in the command line args.
     */
    public LocalDate getStart() {
        return start;
    }

    /** Get the graph type.
     *
     * @return String containing the graph type (bar, histogram, line).
     */
    public String getGraph() {
        return graph;
    }
    //endregion

    //region ====[ Private ]====
    /** A function that identifies and sets the
     * start, end, graph and api values by checking
     * the args by their specifiers (-s -e -g and -a).
     * @return Nothing.
     */
    private void splitArgs() {
        String previousArg = "";
        for (String arg : this.orgArgs) {
            switch (previousArg) {
                case "-s" -> this.start = LocalDateFormatter.parse(arg.toLowerCase());
                case "-e" -> this.end = LocalDateFormatter.parse(arg.toLowerCase());
                case "-g" -> this.graph = checkGraphs(arg.toLowerCase());
                case "-a" -> this.APIURL = arg.toLowerCase();
            }
            previousArg = arg.toLowerCase();
        }
    }

    /** A function that checks the graphs arg against compatible graphs
     * and sets up the graph variable to the correct graph.
     *
     * @param graph The graph argument specified in the command line args.
     * @return String containing a compatible graph type.
     */
    private String checkGraphs(String graph) {
        if (Pattern.compile("^\\s*(?:(?i)bar|histogram|line)\\s*$").matcher(graph).find())
            return graph.replaceAll("\\s*", "");
        return "bar";
    }
    //endregion
}
