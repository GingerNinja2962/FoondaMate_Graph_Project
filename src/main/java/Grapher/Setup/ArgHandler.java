package Grapher.Setup;

public class ArgHandler {
    private String[] orgArgs;
    private String APIURL = "http://sam-user-activity.eu-west-1.elasticbeanstalk.com/";
    private String graph;
    private String start;
    private String end;

    public ArgHandler(String[] args) {
        this.orgArgs = args;
        checkArgs();
    }

    private void checkArgs() {
        // TODO change to a DateTime check to checking for
        //  1. StartDate format (dd-mm-yyyy/dd-mmm-yyyy/dd-mmmm-yyyy) (optional)
        //  2. EndDate format (dd-mm-yyyy/dd-mmm-yyyy/dd-mmmm-yyyy) (optional)
        //  3. GraphType (3 word format) (optional) (REGEX) "^\\s*[((:i)bar)((:i)histogram)((:i)line)]{1}\\s*$"
        String previousArg = "";

        for (String arg : this.orgArgs) {
            switch (previousArg) {
                case "-s" -> this.start = arg.toLowerCase();
                case "-e" -> this.end = arg.toLowerCase();
                case "-g" -> this.graph = arg.toLowerCase();
                case "-a" -> this.APIURL = arg.toLowerCase();
            }
            previousArg = arg.toLowerCase();
        }
    }

    public String getAPIEndPoint() {
        return APIURL;
    }
}
