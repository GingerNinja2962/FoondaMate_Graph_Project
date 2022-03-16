package APIDataRetriver;

import Grapher.ApiDataRetriver.APIConnection;
import Grapher.ApiDataRetriver.Exceptions.ClientApiUrlException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

public class APIDataRetrieverTest {
    //region ====[ Setup ]====
    private final PrintStream standardOut = System.out;
    private final InputStream standardIn = System.in;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
        System.setIn(standardIn);
    }
    //endregion

    //region ====[ Test that the API data is formatted correctly ]====
    @Test
    void APIReturnsData() throws ExecutionException, InterruptedException, ClientApiUrlException, TimeoutException {
        APIConnection apiConnection = new APIConnection(
        "http://sam-user-activity.eu-west-1.elasticbeanstalk.com/");

        // The regex here will only match to a string that:
        // 1. Starts with a '{'
        // 2. Has 15 sets of the following format:
        //      a. '00-00-0000=1, ' where 1 can be any number equal to or greater than 0
        //      b. '00-00-0000=1' where 1 can be any number equal to or greater than 0
        // 3. Ends with a '}'
        // such as the following:
        // {06-01-2022=3000, 13-01-2022=46000, 14-01-2022=70000, 12-01-2022=35000, 08-01-2022=4000, 15-01-2022=90000, 11-01-2022=20000, 07-01-2022=3500, 02-01-2022=500, 10-01-2022=5000, 01-01-2022=300, 03-01-2022=700, 09-01-2022=4500, 04-01-2022=1300, 05-01-2022=2000}
        assertTrue(apiConnection.SendRequest().getDateDataLookup().toString()
                .matches("\\{(\\d{2}-\\d{2}-\\d{4}=\\d+(, )?){15}}"));
    }
    //endregion

}
