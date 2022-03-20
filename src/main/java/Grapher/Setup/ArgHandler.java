package Grapher.Setup;

import Grapher.Printer.DataManager.LocalDateFormatter;

import java.time.LocalDate;
import java.util.regex.Pattern;

public class ArgHandler {
    private String[] orgArgs;
    private String APIURL = "http://sam-user-activity.eu-west-1.elasticbeanstalk.com/";
    private LocalDate start;
    private LocalDate end;
    private String graph;

    //region ====[ Constructors ]====
    public ArgHandler(String[] args) {
        this.orgArgs = args;
        splitArgs();
    }
    //endregion

    //region ====[ Getters ]====
    public String getAPIEndPoint() {
        return APIURL;
    }

    public LocalDate getEnd() {
        return end;
    }

    public LocalDate getStart() {
        return start;
    }

    public String getGraph() {
        return graph;
    }
    //endregion

    //region ====[ Private ]====
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

    private String checkGraphs(String graph) {
        if (Pattern.compile("^\\s*(?:(?i)bar|histogram|line)\\s*$").matcher(graph).find())
            return graph.replaceAll("\\s*", "");
        return "bar";
    }
    //endregion
}
